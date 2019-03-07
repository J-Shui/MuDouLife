package com.mudoulife.data.net.service;

import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonRoleData;
import com.mudoulife.data.net.response.LoginData;
import com.mudoulife.data.net.response.ResultInfo;

import java.util.List;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/17
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.service
 * project : MuDouLife
 */
public interface AccountService {
    /**
     * http://localhost:8002/api/v1/enginee/user/login?username=jshui&password=123456'
     */
    @POST("user/login")
    @FormUrlEncoded
    Single<JsonBase<LoginData>> login(@Field("username") String userName, @Field("password")String password);
    /**
     * http://localhost:8002/api/v1/enginee/user/phoneLogin?phone=17807077645&vCode=456123
     */
    @POST("user/phoneLogin")
    @FormUrlEncoded
    Single<JsonBase<LoginData>> phoneLogin(@Field("phone") String phone,@Field("vCode")String vCode);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("user/register")
    Single<JsonBase> register(@Body RequestBody body);

    /**
     * https://mdapi.vertxjava.com/api/v1/enginee/user/sendCode?phone=17801077645
     * @return
     */
    @POST("user/sendCode")
    @FormUrlEncoded
    Single<JsonBase> sendCode(@Field("phone") String phone);

    @GET("role/all")
    Single<JsonBase<List<JsonRoleData>>> getRoles();
}
