package com.mudoulife.di.component;

import com.mudoulife.di.module.ActivityModule;
import com.mudoulife.di.module.LibraryModule;
import com.mudoulife.di.scope.PerActivity;
import com.mudoulife.ui.activity.LibraryBaseActivity;

import dagger.Component;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.component
 * project : MuDouLife
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class,LibraryModule.class})
public interface LibraryComponent extends ActivityComponent{
    void inject(LibraryBaseActivity activity);
}
