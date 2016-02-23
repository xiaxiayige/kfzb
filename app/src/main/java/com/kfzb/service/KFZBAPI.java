package com.kfzb.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 开发周报api
 * Created by Administrator on 2016/2/22 0022.
 */
public interface KFZBAPI {
    /***
     * 获取主目录页html源码
     * @param pageNo 页数
     * @return
     */
    @GET("/page/{pageNo}")
    Observable<String> getMainDirectory4Html(@Path("pageNo") int pageNo);

    @GET("/")
    Observable<String> getMainDirectory4Html();

    @GET("/{descUrl}/")
    Observable<String> getDescDirectory4Html(@Path("descUrl") String url);

}
