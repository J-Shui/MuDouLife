package com.mudoulife.di.component;

import com.mudoulife.di.module.AccountModule;
import com.mudoulife.di.module.ActivityModule;
import com.mudoulife.di.scope.PerActivity;
import com.mudoulife.ui.activity.SplashActivity;
import com.mudoulife.ui.activity.account.LoginActivity;
import com.mudoulife.ui.activity.account.RegisterActivity;

import dagger.Component;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/17
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.component
 * project : MuDouLife
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class,AccountModule.class})
public interface AccountComponent extends ActivityComponent{
    void inject(RegisterActivity registerActivity);
    void inject(LoginActivity loginActivity);
    void inject(SplashActivity splashActivity);
}
