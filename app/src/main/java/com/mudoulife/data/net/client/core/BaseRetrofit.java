package com.mudoulife.data.net.client.core;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.client.core
 * project : MuDouLife
 */
public abstract class BaseRetrofit {
    public Retrofit get(){

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(getApiEndpoint().getEndPoint())
                .client(getHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }
    protected abstract ApiEndpoint getApiEndpoint();
    protected abstract OkHttpClient getHttpClient();
}
