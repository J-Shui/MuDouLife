package com.mudoulife.data.rx;

import android.util.Log;

import org.reactivestreams.Subscriber;


/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.rx
 * project : MuDouLife
 */
public abstract class ResponseObserver<T> implements Subscriber<T> {
    private static final String TAG = "ResponseObserver";

    @Override
    public void onComplete() {
        Log.d(TAG, "onCompleted");
    }

    @Override
    public void onNext(T t) {
        Log.d(TAG, "onNext");
        onSuccess(t);
    }

    protected abstract void onSuccess(T t);
}
