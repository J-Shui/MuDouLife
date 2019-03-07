package com.mudoulife.data.net.service;

import com.mudoulife.data.net.response.ComplaintReason;
import com.mudoulife.data.net.response.JsonAfterDetails;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonBudgetDetails;
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.data.net.response.UserInfo;

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
 * DATE : 2018/12/10
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.service
 * project : MuDouLife
 */
public interface MainService {
    //    @Headers("Cache-Control: public, max-age=600")
    @GET("{role}/alreadyChoose")
    Single<JsonBase<List<ResultInfo>>> alreadyChoose(@Path("role") String role,
                                                     @Query("id") int id,
                                                     @Query("orderId") int orderId,
                                                     @Query("number") int number);

    @POST("{role}/order/applySettle")
    @FormUrlEncoded
    Single<JsonBase<Object>> applySettle(@Path("role") String role, @Field("orderId") int orderId);

    @POST("carry/order/beginService")
    @FormUrlEncoded
    Single<JsonBase<Object>> beginService(@Field("orderId") int orderId);

    @POST("craftsman/order/startService")
    @FormUrlEncoded
    Single<JsonBase<Object>> startService(@Field("orderId") int orderId);

    @GET("{role}/order/completeAndSettleList")
    Single<JsonBase<List<ResultInfo>>> completeAndSettleList(@Path("role") String role);

    @GET("craftsman/order/completeList")
    Single<JsonBase<List<ResultInfo>>> completeList();

    @POST("{role}/order/confirmReceipt")
    @FormUrlEncoded
    Single<JsonBase<Object>> confirmReceipt(@Path("role") String role, @Field("orderId") int orderId);

    @POST("{role}/order/endService")
    @FormUrlEncoded
    Single<JsonBase<Object>> endService(@Path("role") String role,@Field("orderId") int orderId);

    @GET("{role}/order/list")
    Single<JsonBase<List<ResultInfo>>> getOrderList(@Path("role") String role);

    @GET("{role}/order/orderDetail")
    Single<JsonBase<JsonOrderDetailsData>> getOrderDetail(@Path("role") String role,@Query("orderId") int orderId);

    @GET("{role}/order/getGoodsAndPriceList")
    Single<JsonBase<List<ResultInfo>>> getGoodsAndPriceList(@Path("role") String role);

    @GET("{role}/order/priceAndServiceList")
    Single<JsonBase<List<ResultInfo>>> getPriceAndServiceList(@Path("role") String role);

    @POST("{role}/order/uploadPrice")
    @FormUrlEncoded
    Single<JsonBase<Object>> uploadPrice(@Path("role") String role, @Field("orderId") int orderId,  @Field("json") String json);

    @POST("express/order/uploadPrice")
    @FormUrlEncoded
    Single<JsonBase<Object>> uploadExpressPrice( @Field("orderId") int orderId,
                                                 @Field("expressTypeId") int expressTypeId,
                                                 @Field("numberKm") int numberKm);

    @POST("carry/order/uploadPrice")
    @FormUrlEncoded
    Single<JsonBase<Object>> uploadCarryPrice( @Field("orderId") int orderId,
                                                 @Field("carryTypeId") int carryTypeId,
                                                 @Field("jsonArray") String jsonArray);

    @POST("{role}/order/confirmDeliver")
    @FormUrlEncoded
    Single<JsonBase<Object>> confirmDeliver(@Path("role") String role, @Field("orderId") int orderId);

    @GET("{role}/order/deliverList")
    Single<JsonBase<List<ResultInfo>>> deliverList(@Path("role") String role);

    @GET("{role}/order/deliverOrderDetail")
    Single<JsonBase<JsonDeliverDatails>> deliverOrderDetail(@Path("role") String role, @Query("orderId") int orderId);

    @GET("{role}/order/applySettleDetail")
    Single<JsonBase<JsonOrderDetailsData>> applySettleDetail(@Path("role") String role, @Query("orderId") int orderId);

    @POST("express/order/getGoods")
    @FormUrlEncoded
    Single<JsonBase<Object>> getGoods(@Field("orderId") int orderId);

    @GET("express/order/getGoodsAndPriceList")
    Single<JsonBase<List<ResultInfo>>> getGoodsAndPriceList();

    @GET("{role}/cancelOrder")
    Single<JsonBase<Object>> cancelOrder(@Path("role") String role, @Query("orderId") int orderId);


    @GET("{role}/order/afterSaleDetail")
    Single<JsonBase<JsonOrderDetailsData>> afterSaleDetail(@Path("role") String role, @Query("orderId") int orderId);

    @GET("{role}/order/afterSaleDetail")
    Single<JsonBase<JsonAfterDetails>> afterSaleDetail(@Path("role") String role, @Query("orderId") int orderId, @Query("goodsId") int goodsId);

    @POST("{role}/order/processing")
    @FormUrlEncoded
    Single<JsonBase<Object>> processing(@Path("role") String role, @Field("orderId") int orderId,@Field("goodsId") int goodsId);

    @POST("{role}/order/processing")
    @FormUrlEncoded
    Single<JsonBase<Object>> processing(@Path("role") String role, @Field("orderId") int orderId);

    @POST("{role}/order/processingEnd")
    @FormUrlEncoded
    Single<JsonBase<Object>> processingEnd(@Path("role") String role, @Field("orderId") int orderId,@Field("goodsId") int goodsId);

    @POST("{role}/order/processingEnd")
    @FormUrlEncoded
    Single<JsonBase<Object>> processingEnd(@Path("role") String role, @Field("orderId") int orderId);

    @GET("{role}/order/afterSaleList")
    Single<JsonBase<List<ResultInfo>>> afterSaleList(@Path("role") String role);

    @GET("{role}/order/complaintDetail")
    Single<JsonBase<List<ComplaintReason>>> complaintDetail(@Path("role") String role);

    @GET("{role}/order/historyMessage")
    Single<JsonBase<List<ResultInfo>>> historyMessage(@Path("role") String role);

    @GET("{role}/order/monthIncome")
    Single<JsonBase<Double>> monthIncome(@Path("role") String role);

    @GET("{role}/order/paymentView")
    Single<JsonBase<List<ResultInfo>>> paymentView(@Path("role") String role);

    @GET("{role}/order/budgetDetail")
    Single<JsonBase<JsonBudgetDetails>> budgetDetail(@Path("role") String role, @Query("id") int id);


    @GET("user/info")
    Single<JsonBase<UserInfo>> getUserInfo();

}
