package com.mudoulife.presenter.library;

import android.util.Log;

import com.mudoulife.base.BaseMvpPresenter;
import com.mudoulife.base.LibraryView;
import com.mudoulife.data.api.LibraryDataApi;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonLibraryLeftItem;
import com.mudoulife.data.net.response.LibraryBaseData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.presenter.library
 * project : MuDouLife
 */
public class LibraryBasePresenter extends BaseMvpPresenter<LibraryView<JsonBase<List<LibraryBaseData>>>> {
    private static final String TAG = "LibraryBasePresenter";
    private final LibraryDataApi mApi;

    @Inject
    public LibraryBasePresenter(LibraryDataApi mApi) {
        this.mApi = mApi;
    }

    public void getServiceOrGoodsClass(String role,int type) {
        mApi.getServiceOrGoodsClass(role,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<JsonLibraryLeftItem>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<JsonLibraryLeftItem>> resultData) {
                        Log.d(TAG, "onSuccess: resultData = " + resultData);
                        getView().showLeftItem(resultData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void getItem(String role,int type,int classId){
        mApi.getItem(role,type,classId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<LibraryBaseData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<LibraryBaseData>> resultData) {
                        Log.d(TAG, "onSuccess: resultData = " + resultData);
                        getView().showContent(resultData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void expressType(int orderId){
        mApi.expressType(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<JsonLibraryLeftItem>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<JsonLibraryLeftItem>> resultData) {
                        Log.d(TAG, "onSuccess: resultData = " + resultData);
                        getView().showLeftItem(resultData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void carryType(int orderId){
        mApi.carryType(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<JsonLibraryLeftItem>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<JsonLibraryLeftItem>> resultData) {
                        Log.d(TAG, "onSuccess: resultData = " + resultData);
                        getView().showLeftItem(resultData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void carryTypeDetail(int id,int orderId){
        mApi.carryTypeDetail(id, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<LibraryBaseData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<LibraryBaseData>> resultData) {
                        Log.d(TAG, "onSuccess: resultData = " + resultData);
                        getView().showContent(resultData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void expressTypeDetail(int id){
        mApi.expressTypeDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<LibraryBaseData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<LibraryBaseData> resultData) {
                        Log.d(TAG, "onSuccess: resultData = " + resultData);
//                        getView().showContent(resultData);
                        getView().showDetails(resultData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e);
                    }
                });
    }
}
