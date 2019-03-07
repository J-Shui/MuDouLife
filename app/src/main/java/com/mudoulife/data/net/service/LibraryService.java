package com.mudoulife.data.net.service;

import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonLibraryLeftItem;
import com.mudoulife.data.net.response.LibraryBaseData;
import com.mudoulife.data.net.response.ResultInfo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.service
 * project : MuDouLife
 */
public interface LibraryService {
    @GET("{role}/order/getServiceOrGoodsClass")
    Single<JsonBase<List<JsonLibraryLeftItem>>> getServiceOrGoodsClass(@Path("role") String role, @Query("type") int type);

    @POST("{role}/order/getItem")
    @FormUrlEncoded
    Single<JsonBase<List<LibraryBaseData>>> getItem(@Path("role") String role,@Query("type") int type,@Field("classId") int classId);

    @GET("carry/carryType")
    Single<JsonBase<List<JsonLibraryLeftItem>>> carryType(@Query("orderId") int orderId);

    @GET("carry/carryTypeDetail")
    Single<JsonBase<List<LibraryBaseData>>> carryTypeDetail(@Query("id") int id, @Query("orderId") int orderId);

    @GET("express/expressType")
    Single<JsonBase<List<JsonLibraryLeftItem>>> expressType(@Query("orderId") int orderId);

    @GET("express/expressTypeDetail")
    Single<JsonBase<LibraryBaseData>> expressTypeDetail(@Query("id") int id);
}
