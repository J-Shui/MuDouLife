package com.mudoulife.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mudoulife.common.UserInfoManager;
import com.mudoulife.data.net.client.MuDouRetrofit;
import com.mudoulife.data.net.service.AccountService;
import com.mudoulife.data.net.service.LibraryService;
import com.mudoulife.data.net.service.MainService;
import com.mudoulife.di.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.module
 * project : MuDouLife
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application){
        mApplication = application;
    }
    @Provides
    Application provideApplication(){
        return mApplication;
    }
    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }
    @Provides
    @Singleton
    SharedPreferences provideSharePreferences(){
        return mApplication.getSharedPreferences("mudou_sp", Context.MODE_PRIVATE);
    }
    //TODO
    @Provides
    @Singleton
    MainService provideResultService(MuDouRetrofit retrofit){
        return retrofit.get().create(MainService.class);
    }

    @Provides
    @Singleton
    LibraryService provideLibraryService(MuDouRetrofit retrofit){
        return retrofit.get().create(LibraryService.class);
    }

    @Provides
    @Singleton
    AccountService provideAccountService(MuDouRetrofit retrofit){
        return retrofit.get().create(AccountService.class);
    }

    @Provides
    @Singleton
    UserInfoManager provideUserInfoManager(){
        return UserInfoManager.getInstance(mApplication);
    }


}
