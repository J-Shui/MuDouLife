package com.mudoulife.di;

import com.mudoulife.di.component.ApplicationComponent;

public class ComponentHolder {
    private static ApplicationComponent mAppComponent;

    public static void setAppComponent(ApplicationComponent component){
        mAppComponent = component;
    }
    public static ApplicationComponent getAppComponent(){
        return mAppComponent;
    }
}
