package com.mudoulife.presenter.account;

import android.util.Log;

import com.mudoulife.base.BaseMvpPresenter;
import com.mudoulife.base.LoginView;
import com.mudoulife.data.api.AccountApi;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonRoleData;
import com.mudoulife.data.net.response.LoginData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.presenter.account
 * project : MuDouLife
 */
public class AccountPresenter extends BaseMvpPresenter<LoginView<LoginData>> {
    private static final String TAG = "AccountPresenter";
    private final AccountApi mApi;

    @Inject
    public AccountPresenter(AccountApi api) {
        this.mApi = api;
    }

    public void login(String userName, String password) {
        mApi.login(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<LoginData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                    }

                    @Override
                    public void onSuccess(JsonBase<LoginData> jsonBase) {
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
                        getView().loginSuccess(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e = " + e);
                        Log.d(TAG, "onError: e = " + e.getMessage());
                        getView().showError(e);
                    }
                });
    }

    public void phoneLogin(String phone, String vCode) {
        mApi.phoneLogin(phone, vCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<LoginData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                    }

                    @Override
                    public void onSuccess(JsonBase<LoginData> jsonBase) {
                        getView().loginSuccess(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void register(RequestBody body) {
        mApi.register(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                    }

                    @Override
                    public void onSuccess(JsonBase jsonBase) {
                        getView().loginSuccess(jsonBase);
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e = " + e);
                        getView().showError(e);
                    }
                });
    }

    public void registerAndLogin(RequestBody body, final String username, final String password) {
        mApi.register(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<JsonBase, SingleSource<JsonBase<LoginData>>>() {
                    @Override
                    public SingleSource<JsonBase<LoginData>> apply(JsonBase jsonBase) throws Exception {
                        Log.d(TAG, "apply: jsonBase = " + jsonBase);
                        return mApi.login(username, password);
                    }
                }).observeOn(Schedulers.io())
                .subscribe(new SingleObserver<JsonBase<LoginData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                    }

                    @Override
                    public void onSuccess(JsonBase<LoginData> jsonBase) {
                        getView().loginSuccess(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });

    }

    public void sendCode(String phone) {
        mApi.sendCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                    }

                    @Override
                    public void onSuccess(JsonBase jsonBase) {
                        getView().getCodeSuccess(jsonBase);
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: onError = " + e);
                    }
                });
    }

    public void getRoles() {
        mApi.getRoles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<JsonRoleData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<JsonRoleData>> jsonBase) {
                        getView().onGetRoles(jsonBase);
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: onError = " + e);
                    }
                });
    }
}
