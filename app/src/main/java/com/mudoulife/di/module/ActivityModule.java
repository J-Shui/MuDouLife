package com.mudoulife.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.transition.Transition;
import android.support.v4.app.Fragment;

import com.mudoulife.di.qualifier.ActivityContext;
import com.mudoulife.permission.PermissionHelper;
import com.mudoulife.permission.PermissionInterface;

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
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        mActivity = activity;
    }
    public ActivityModule(Fragment fragment){
        mActivity = fragment.getActivity();
    }
    @Provides
    Activity provideActivity(){
        return mActivity;
    }
    @Provides
    @ActivityContext
    Context provideContext(){
        return mActivity;
    }

//    @Provides
//    PermissionHelper providePermissionHelper(Activity activity, PermissionInterface permissionInterface){
//        return new PermissionHelper(activity,permissionInterface);
//    }
}
