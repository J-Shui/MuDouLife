package com.mudoulife.data.net.client.core;

import android.os.Build;

import com.mudoulife.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.client.core
 * project : MuDouLife
 */
public abstract class BaseOkHttpClient {
    private static final long TIMEOUT_CONNECT = 30 * 1000;

    public OkHttpClient get(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIMEOUT_CONNECT,TimeUnit.MILLISECONDS);
//                .addInterceptor(new HttpLoggingInterceptor()
//                .setLevel(BuildConfig.DEBUG ?
//                        HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));
        builder = customize(builder);

        return builder.build();
    }
    public abstract OkHttpClient.Builder customize(OkHttpClient.Builder builder);
}
