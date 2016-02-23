package com.kfzb.utils;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/***
 * 通用adapter
* @ClassName: CommonAdapter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author qilin zhang 
* @date 2015年11月9日 上午11:55:07 
* 
* @param <T>
 */
public abstract class CommonAdapter <T> extends BaseAdapter {
	public List<T> mData;
	private Context context;
	private int  layoutId;
	public CommonAdapter(Context context, List<T> bean,int layoutId) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.mData = bean;
		this.layoutId = layoutId;
	}

	@Override
	public int getCount() {
		if(mData == null || mData.size() <= 0) return 0;
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		CommonViewHolder holder = CommonViewHolder.get(context, convertView,layoutId, position, parent);
		setItemValue(holder, getItem(position),position);
		return holder.getConvertView();
	}
	
	/***
	 * 
	* @Title: setItemValue 
	* @Description: TODO(设置每个item内容) 
	 */
	public abstract void setItemValue(CommonViewHolder holder,T t,int position);
	
}