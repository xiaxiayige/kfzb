package com.kfzb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kfzb.adapter.DescDirectoryAdapter;
import com.kfzb.bean.DescDirectoryBean;
import com.kfzb.service.KFZBAPI;
import com.kfzb.service.KFZBService;
import com.kfzb.utils.APPContent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/2/22 0022.
 */
public class DescDirectoryActivity extends AppCompatActivity {
    public static final String TAG = DescDirectoryActivity.class.getSimpleName();
    private ListView listView;
    private TextView textView;
    private String url;
    private List<DescDirectoryBean> listBean = new ArrayList<DescDirectoryBean>();
    private List<String> tags = new ArrayList<String>();
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }

    private void initView() {
        listView = $(R.id.recyclerview);
        textView = $(R.id.emptyView);
        listView.setEmptyView(textView);
    }

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    public void getData() {
        Bundle b = getIntent().getExtras();
        url = b.getString("url");
        title = b.getString("title");
        setTitle(title);
        KFZBAPI kfzb = KFZBService.create();
        kfzb.getDescDirectory4Html(url)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, List<DescDirectoryBean>>() {
                    @Override
                    public List<DescDirectoryBean> call(String s) {
                        return converStringToList(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<DescDirectoryBean>>() {
                    @Override
                    public void call(List<DescDirectoryBean> descDirectoryBeans) {
                        listView.setAdapter(new DescDirectoryAdapter(DescDirectoryActivity.this, descDirectoryBeans, R.layout.item));

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        textView.setText("throwable:" + throwable.toString());
                    }
                });
    }


    public List<DescDirectoryBean> converStringToList(String html) {
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass(APPContent.POST_CONTENT);
        Elements section = elements.tagName("section");
        Elements h3 = section.select("h3"); //tag
        Elements ol = section.select("ol"); // 具体列表

        int h3Length = h3.size();

        for (int i = 0; i < h3Length; i++) {
            tags.add(h3.get(i).text());
        }

        int olLength = ol.size(); //7

        DescDirectoryBean descDirectoryBean = null;
        Log.i(TAG, "ol:" + olLength);

        for (int i = 0; i < olLength; i++) {

            org.jsoup.nodes.Element olElement = ol.get(i);

            Log.i(TAG, "i:" + i);

            Elements lis = olElement.select("li");  //获取ol 下面的li

            for (int j = 0; j < lis.size(); j++) {

                descDirectoryBean = new DescDirectoryBean();

                org.jsoup.nodes.Element li = lis.get(j);

                Elements p = li.select("p");
                String desc = "";

                String title = li.child(0).child(0).text();
                String url = li.child(0).child(0).attr("href");
//              String desc=li.child(1).text();

                if (p.size() == 2) { //处理特殊情况  一般都是2个p标签   有的有一个p标签  desc在<br>标签后面  坑、
                    desc = li.child(1).text();
                } else if (p.size() == 1) {
                    desc = p.get(0).text().replace(title, ""); //替换掉里面的标题字符串
                    Log.i(TAG, "des:" + desc);
                }

                descDirectoryBean.setTag(tags.get(i));
                descDirectoryBean.setDesc(desc);
                descDirectoryBean.setTitle(title);
                descDirectoryBean.setUrl(url);
                Log.i(TAG, descDirectoryBean.toString());

                listBean.add(descDirectoryBean);
            }
        }

        return listBean;
    }
}
