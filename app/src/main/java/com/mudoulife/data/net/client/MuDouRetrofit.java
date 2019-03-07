package com.mudoulife.data.net.client;

import com.mudoulife.data.net.client.core.ApiEndpoint;
import com.mudoulife.data.net.client.core.BaseRetrofit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.client
 * project : MuDouLife
 */
public class MuDouRetrofit extends BaseRetrofit {
    private static final String END_POINT = "https://mdapi.vertxjava.com/api/v1/enginee/";

    @Override
    protected ApiEndpoint getApiEndpoint() {
        return new ApiEndpoint() {
            @Override
            public String getEndPoint() {
                return END_POINT;
            }
        };
    }

    AuthHttpClient mClient;

    @Inject
    public MuDouRetrofit(AuthHttpClient client){
        this.mClient = client;
    }

    @Override
    protected OkHttpClient getHttpClient() {
        return mClient.get();
    }

}
