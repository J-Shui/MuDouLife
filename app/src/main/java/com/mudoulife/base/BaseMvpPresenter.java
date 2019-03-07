package com.mudoulife.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.mvp
 * project : MuDouLife
 */
public class BaseMvpPresenter<V extends BaseView> implements BasePresenter<V> {
    private V mView;
    protected CompositeDisposable mDisposables;
    protected void addSubScribe(Disposable disposable){
        if (null == mDisposables){
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(disposable);
    }
    private void unSubscribe() {
        if (null != mDisposables) {
            mDisposables.clear();
            mDisposables = null;
        }
    }
    @Override
    public void attachView(V view) {
        mView = view;
    }
    @Override
    public void detachView(V view) {
        mView = null;
        unSubscribe();
    }
    public boolean isViewAttached(){
        return mView != null;
    }
    public V getView() {
        return mView;
    }
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
