package com.mudoulife.data.net.datasource;

import com.mudoulife.data.api.AccountApi;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonRoleData;
import com.mudoulife.data.net.response.LoginData;
import com.mudoulife.data.net.service.AccountService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.RequestBody;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/17
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net
 * project : MuDouLife
 */
public class AccountDataSource implements AccountApi {
//    @Inject
//    MuDouRetrofit mRetrofit;
    @Inject
    AccountService mAccountService;
//    @Inject
//    Application mContext;

    @Inject
    public AccountDataSource(AccountService service){
        this.mAccountService = service;
    }
    @Override
    public Single<JsonBase<LoginData>> login(String userName, String password) {
//        mRetrofit.get().create()
//        final AccountService accountService = ComponentHolder.getAppComponent().accountService();
        return mAccountService.login(userName, password);
    }

    @Override
    public Single<JsonBase<LoginData>> phoneLogin(String phone, String vCode) {
        return mAccountService.phoneLogin(phone,vCode);
    }

    @Override
    public Single<JsonBase> register(RequestBody body) {
        return mAccountService.register(body);
    }

    @Override
    public Single<JsonBase> sendCode(String phone) {
        return mAccountService.sendCode(phone);
    }

    @Override
    public Single<JsonBase<List<JsonRoleData>>> getRoles() {
        return mAccountService.getRoles();
    }
}
