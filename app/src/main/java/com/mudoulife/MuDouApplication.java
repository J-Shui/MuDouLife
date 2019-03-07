package com.mudoulife;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import com.mudoulife.di.ComponentHolder;
import com.mudoulife.di.component.ApplicationComponent;
import com.mudoulife.di.component.DaggerApplicationComponent;
import com.mudoulife.di.module.ApplicationModule;
import com.mudoulife.util.AppUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife
 * project : MuDouLife
 */
public class MuDouApplication extends Application {
    private static final String TAG = "MuDouApplication";
    private List<Activity> mActivityList = null;
    @Override
    public void onCreate() {
//        Debug.startMethodTracing("mudou");
        super.onCreate();
        inject();
//       Debug.stopMethodTracing();
        new MuDouCrashHandler(getApplicationContext(),this);
        //获取手机的分辨率等信息
        AppUtil.getScreenResolution(this);
    }

    public static MuDouApplication get(Context context){
        return (MuDouApplication) context.getApplicationContext();
    }
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            ActivityLifecycleCallbacks activityLifecycleCallbacks =
                    new ActivityLifecycleCallbacks() {

                        @Override
                        public void onActivityStopped(Activity activity) {
                            // TODO Auto-generated method stub
                            Log.d(TAG, "onActivityStopped: activity=" + activity);
                        }

                        @Override
                        public void onActivityStarted(Activity activity) {
                            // TODO Auto-generated method stub
                            Log.d(TAG, "onActivityStarted: activity=" + activity);
                        }

                        @Override
                        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                            // TODO Auto-generated method stub
                            Log.d(TAG, "onActivitySaveInstanceState: activity=" + activity +
                                    ", outState=" + outState.toString());
                        }

                        @Override
                        public void onActivityResumed(Activity activity) {
                            // TODO Auto-generated method stub
                            Log.d(TAG, "onActivityResumed: activity=" + activity);
                        }

                        @Override
                        public void onActivityPaused(Activity activity) {
                            // TODO Auto-generated method stub
                            Log.d(TAG, "onActivityPaused: activity=" + activity);
                        }

                        @Override
                        public void onActivityDestroyed(Activity activity) {
                            // TODO Auto-generated method stub
                            Log.d(TAG, "onActivityDestroyed: activity=" + activity);
                            removeActivityFromList(activity);
                        }

                        @Override
                        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                            // TODO Auto-generated method stub
                            Log.d(TAG, "onActivityCreated: activity=" + activity +
                                    ", savedInstanceState=" + savedInstanceState);
//                            clearOldTasksIfSplashCreate(activity);
//                            clearLastDetailsPageWhileCreateNew(activity);
                            addActivityIntoList(activity);
                        }
                    };
            return activityLifecycleCallbacks;
        } else
            return null;
    }


    private void addActivityIntoList(Activity activity) {
        if (mActivityList == null)
            mActivityList = new LinkedList<Activity>();
        mActivityList.add(activity);
    }

    private void removeActivityFromList(Activity activity) {
        if (mActivityList != null && activity != null) {
            mActivityList.remove(activity);
        }
    }
    private void stopAndKillApp() {
//        registerBroadcastReceiver(false);
//        Globals.gIsAppFinished = true;
        if (mActivityList != null) {
            for (Activity act : mActivityList) {
                if (act != null) {
                    act.finish();
                    act = null;
                }
            }
            mActivityList.clear();
            mActivityList = null;
        }
//        Updater.recycle();
//        ToastUtil.recycle();
        /**
         * 结束进程，关闭程序的方法。
         * 方法1.ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)
         * am.restartPackage(getPackageName());
         * 还要在manifest中配置：
         * <user-permission android:name="android.permission.RESTART_PACKAGES"></user-permission>
         *
         *  在android2.2版本之后则不能再使用restartPackage()方法，而应该使用killBackgroundProcesses()方法
         */
//        Updater.recycle();
//        ToastUtils.recycle();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    public void stopCrashedApp() {
        stopAndKillApp();
    }

    private void inject(){
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        ComponentHolder.setAppComponent(applicationComponent);
    }
}
