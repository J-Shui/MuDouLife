package com.mudoulife.base;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mudoulife.di.ComponentHolder;
import com.mudoulife.di.component.ActivityComponent;
import com.mudoulife.di.component.DaggerActivityComponent;
import com.mudoulife.di.module.ActivityModule;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity{
//    @Inject
//    private ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    public ActivityComponent getActivityComponent() {
//        if (mActivityComponent == null) {
//           mActivityComponent = DaggerActivityComponent.builder()
//                   .activityModule(new ActivityModule(this))
//                   .applicationComponent(ComponentHolder.getAppComponent())
//                   .build();
//           return mActivityComponent;
//        }
//        return mActivityComponent;
//    }
}
