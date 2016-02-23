package com.kfzb.service;

import com.kfzb.utils.ToStringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2016/2/22 0022.
 */
public class KFZBService {
    private  static final String BaseUrl="http://androidweekly.cn";

   private static Retrofit retrofit=new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(ToStringConverterFactory.create())
            .baseUrl(BaseUrl).build();

    private void KFZBService(){}

    public static KFZBAPI create(){
       return  retrofit.create(KFZBAPI.class);
    }
}
