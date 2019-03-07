package com.mudoulife.base;

import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonRoleData;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/17
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.base
 * project : MuDouLife
 */
public interface LoginView<T> extends BaseView{
    void loginSuccess(JsonBase<T> jsonBase);
    void getCodeSuccess(JsonBase<T> jsonBase);
    void onGetRoles(JsonBase<List<JsonRoleData>> jsonBase);
}
