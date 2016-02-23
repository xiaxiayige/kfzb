package com.kfzb.utils;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/***
 * 通用ViewHolder类
* @ClassName: CommonViewHolder 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author qilin zhang 
* @date 2015年11月9日 上午11:40:48 
*
 */
public class CommonViewHolder {
	private SparseArray<View> mViews;
	private View convertView;

	/***
	 * 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param context 上下文对象
	* @param layoutId layout 文件 id
	* @param parent  parent
	 */
	private CommonViewHolder(Context context, int layoutId,ViewGroup parent) {
		this.convertView = LayoutInflater.from(context).inflate(layoutId,parent, false);
		mViews = new SparseArray<View>();
		convertView.setTag(this);
	}

	/***
	 * 根据view id 获取View
	* @Title: getView 
	* @param @param viewId
	* @return T    返回类型 
	 */
	public <T extends View > T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = convertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public static CommonViewHolder get(Context context, View convertView,
			int layoutID, int position, ViewGroup parent) {
		if (convertView == null) {
			return new CommonViewHolder(context, layoutID, parent);
		} else {
			CommonViewHolder holder = (CommonViewHolder) convertView.getTag();
			return holder;
		}
	}

	public View getConvertView() {
		return convertView;
	}
	
	
	public CommonViewHolder setText(int viewId,String text){
		TextView textView=getView(viewId);
		textView.setText(text);
		return this;
	}
	
}