package com.mudoulife.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView{
    @Inject
    protected T mBasePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != mBasePresenter)
            mBasePresenter.attachView(this);
    }
    protected void initView(){

    }
    protected abstract void inject();

    @Override
    protected void onDestroy() {
        if (null != mBasePresenter){
            mBasePresenter.detachView(this);
            mBasePresenter = null;
        }
        super.onDestroy();
    }
}
