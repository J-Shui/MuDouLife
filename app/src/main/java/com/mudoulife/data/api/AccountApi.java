package com.mudoulife.data.api;

import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonRoleData;
import com.mudoulife.data.net.response.LoginData;
import com.mudoulife.data.net.response.ResultInfo;

import java.util.List;

import io.reactivex.Single;
import okhttp3.RequestBody;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/17
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.api
 * project : MuDouLife
 */
public interface AccountApi {
    Single<JsonBase<LoginData>> login(String userName, String password);
    Single<JsonBase<LoginData>> phoneLogin(String phone,String vCode);
    Single<JsonBase> register(RequestBody body);
    Single<JsonBase> sendCode(String phone);
    Single<JsonBase<List<JsonRoleData>>> getRoles();
}
