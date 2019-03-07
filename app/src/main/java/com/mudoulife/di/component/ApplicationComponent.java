package com.mudoulife.di.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mudoulife.common.UserInfoManager;
import com.mudoulife.data.net.service.AccountService;
import com.mudoulife.data.net.service.LibraryService;
import com.mudoulife.data.net.service.MainService;
import com.mudoulife.di.module.ApplicationModule;
import com.mudoulife.di.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.component
 * project : MuDouLife
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    SharedPreferences sharedPreferences();

    MainService resultService();

    LibraryService libraryService();

    AccountService accountService();

    UserInfoManager userInfoManager();

}
