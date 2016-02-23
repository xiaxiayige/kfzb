package com.kfzb.bean;

/**
 * 详细目录
 * Created by Administrator on 2016/2/22 0022.
 */
public class DescDirectoryBean {
    private String tag; //标签 H3新闻  //教程  //开源库 //项目 //工具   ……
    private String title ;// Android N或取消应用抽屉 设计更趋近iOS
    private String url;
    private String desc; //描述

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "DescDirectoryBean{" +
                "tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
