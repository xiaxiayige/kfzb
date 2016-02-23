package com.kfzb.bean;

/**
 * 目录bean
* @ClassName: DirectoryBean 
* @author qilin zhang 
* @date 2016年2月18日 下午2:35:47 
 */
public class DirectoryBean {

	private String post_title; //Android开发技术周报 Issue#68
	private String post_excerpt;//，新闻 Kotlin 1.0 正式发布: JVM 和 Android 上更好用的语言 赶紧去学习一下 Android Marshmallow系统占比首次超百分之1.0 可喜可贺啊，你吃到 Marshmallow 系统了吗 教程 阅读Android源码的一些姿势 你是怎么阅读源码的 
	private String post_date; //2016年02月15日
	private String post_url;
	
	
	
	public String getPost_url() {
		return post_url;
	}
	public void setPost_url(String post_url) {
		this.post_url = post_url;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_excerpt() {
		return post_excerpt;
	}
	public void setPost_excerpt(String post_excerpt) {
		this.post_excerpt = post_excerpt;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	@Override
	public String toString() {
		return "DirectoryBean [post_title=" + post_title + ", post_excerpt="
				+ post_excerpt + ", post_date=" + post_date + ", post_url="
				+ post_url + "]";
	}
	
	
	
}
