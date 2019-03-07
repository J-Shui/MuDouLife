package com.mudoulife.data.net.client;

import android.app.Application;
import android.util.Log;

import com.mudoulife.common.Globals;
import com.mudoulife.common.UserInfoManager;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.client
 * project : MuDouLife
 */
public class AuthHttpClient extends CacheHttpClient {
    private static final String TAG= "AuthHttpClient";
//    @Inject
//    public Application application;

    @Inject
    public AuthHttpClient() {
    }

    @Override
    public OkHttpClient.Builder customize(OkHttpClient.Builder builder) {
        //TODO 这里可以实现自定义的builder
        Log.d(TAG, "customize: gIsUserLogin = " + UserInfoManager.gIsUserLogin);
        if (UserInfoManager.gIsUserLogin){
            return super.customize(builder).addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
                    requestBuilder.header("Authorization", UserInfoManager.gToken);
                    Log.d(TAG, "intercept: token = " + UserInfoManager.gToken);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }
        return super.customize(builder);
    }
}
