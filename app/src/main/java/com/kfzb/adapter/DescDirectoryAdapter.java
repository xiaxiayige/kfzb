package com.kfzb.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.kfzb.R;
import com.kfzb.bean.DescDirectoryBean;
import com.kfzb.utils.CommonAdapter;
import com.kfzb.utils.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/2/22 0022.
 */
public class DescDirectoryAdapter extends CommonAdapter<DescDirectoryBean> {

    public DescDirectoryAdapter(Context context, List<DescDirectoryBean> bean, int layoutId) {
        super(context, bean, layoutId);
    }

    @Override
    public void setItemValue(CommonViewHolder holder, DescDirectoryBean descDirectoryBean,int position) {
        TextView post_title=holder.getView(R.id.post_title);
        TextView post_excerpt=holder.getView(R.id.post_excerpt);
        TextView post_date=holder.getView(R.id.post_date);
        TextView tag=holder.getView(R.id.tag);
        tag.setText(descDirectoryBean.getTag());
        post_title.setText((position+1)+"."+descDirectoryBean.getTitle());
        post_excerpt.setText(descDirectoryBean.getDesc());
        post_date.setVisibility(View.GONE);


        tag.setVisibility(View.GONE);

        int nextPosition=position-1;
        if(position==0){
            tag.setVisibility(View.VISIBLE);
        }else{
            if(!descDirectoryBean.getTag().equals(mData.get(nextPosition).getTag())){ //当前tag和上一个tag不一样时 显示当前tag
                tag.setVisibility(View.VISIBLE);
            }else{
                tag.setVisibility(View.GONE);
            }
        }
    }
}
