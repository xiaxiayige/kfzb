package com.kfzb.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.kfzb.R;
import com.kfzb.bean.DirectoryBean;
import com.kfzb.utils.CommonAdapter;
import com.kfzb.utils.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/2/22 0022.
 */
public class DirectoryAdapter extends CommonAdapter<DirectoryBean> {

    public DirectoryAdapter(Context context, List<DirectoryBean> bean, int layoutId) {
        super(context, bean, layoutId);
    }

    @Override
    public void setItemValue(CommonViewHolder holder, DirectoryBean t,int position) {
        TextView post_title=holder.getView(R.id.post_title);
        TextView post_excerpt=holder.getView(R.id.post_excerpt);
        TextView post_date=holder.getView(R.id.post_date);

        TextView tag=holder.getView(R.id.tag);
        tag.setVisibility(View.GONE);
        post_title.setText((position+1)+"." +t.getPost_title());
        post_excerpt.setText(t.getPost_excerpt());
        post_date.setText(t.getPost_date());




    }
}
