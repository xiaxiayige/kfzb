package com.kfzb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.kfzb.adapter.DirectoryAdapter;
import com.kfzb.bean.DirectoryBean;
import com.kfzb.service.KFZBAPI;
import com.kfzb.service.KFZBService;
import com.kfzb.utils.APPContent;
import com.kfzb.utils.ViewUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String TAG=MainActivity.class.getSimpleName();
    ListView listView;
    private TextView textView;

    private List<DirectoryBean> listBean=new ArrayList<DirectoryBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData(1);
        initListener();
    }

    private void initListener() {
        listView.setOnItemClickListener(this);
    }

    private void getData(int pageNo) {
        KFZBAPI api= KFZBService.create();
        api.getMainDirectory4Html(pageNo)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, List<DirectoryBean>>() { //转换
                    @Override
                    public List<DirectoryBean> call(String s) {
                        String html=s;
                        html=html.replace("tag-androiddevspecialweekly", APPContent.ITEM_CLASSNAME);
                        Document document= Jsoup.parse(html);
                        Elements elements=document.getElementsByClass(APPContent.ITEM_CLASSNAME);
                        return getData(elements);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<DirectoryBean>>() {
                    @Override
                    public void call(List<DirectoryBean> directoryBeans) {
                        listView.setAdapter(new DirectoryAdapter(MainActivity.this, directoryBeans, R.layout.item));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        textView.setText("throwable:" + throwable.toString());
                    }
                });
    }

    private void initView() {
        listView=$(R.id.recyclerview);
        textView=$(R.id.emptyView);
        listView.setEmptyView(textView);
    }

    //解析数据
    private List<DirectoryBean> getData(Elements elements) {
        if(elements!=null&&elements.size()>0){
            for(int i=0;i<elements.size();i++){
                Element tag_androiddevweekly_element=elements.get(i);
                Element tag_a_element=tag_androiddevweekly_element.child(0).child(0).child(0); //获取到a 标签 --header---->H2---->a
                String  post_title=tag_androiddevweekly_element.child(0).child(0).text(); //title
                String  post_url=tag_a_element.attr("href"); //链接
                String  post_excerpt=tag_androiddevweekly_element.child(1).text();
                String post_date=tag_androiddevweekly_element.child(2).child(3).text();
                DirectoryBean directoryBean=new DirectoryBean();

                directoryBean.setPost_title(post_title);
                directoryBean.setPost_url(post_url.replace("/",""));
                directoryBean.setPost_excerpt(post_excerpt);
                directoryBean.setPost_date(post_date);
                listBean.add(directoryBean);
            }
        }
        return listBean;
      }

    public <T extends View> T $(int id){
        return (T) findViewById(id);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DirectoryBean directoryBean= (DirectoryBean) parent.getAdapter().getItem(position);
        ViewUtils.Toast(this, "url:" + directoryBean.getPost_url());
        Bundle b=new Bundle();
        b.putString("url", directoryBean.getPost_url());
        b.putString("title", directoryBean.getPost_title());
        Intent intent=new Intent(MainActivity.this,DescDirectoryActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}
