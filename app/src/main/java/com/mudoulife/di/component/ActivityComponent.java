package com.mudoulife.di.component;

import android.app.Activity;

import com.mudoulife.di.module.ActivityModule;
import com.mudoulife.di.scope.PerActivity;
import com.mudoulife.permission.PermissionHelper;

import dagger.Component;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.component
 * project : MuDouLife
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
//    PermissionHelper permissionHelper();
}
