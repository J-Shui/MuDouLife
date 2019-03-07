package com.mudoulife.base;

import android.support.annotation.UiThread;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.mvp
 * project : MuDouLife
 */
public interface LoadView extends MvpView {
    @UiThread
    public void showLoading();

    @UiThread
    public void dismissLoading();

    @UiThread
    public void error(Throwable e);
}
