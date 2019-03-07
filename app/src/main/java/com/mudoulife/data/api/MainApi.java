package com.mudoulife.data.api;

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
 * package_name : com.mudoulife.data.api
 * project : MuDouLife
 */
public interface MainApi {
    Single<JsonBase<Object>> applySettle(String role, int orderId);
    Single<JsonBase<Object>> beginService(int orderId);
    Single<JsonBase<Object>> startService(int orderId);
    Single<JsonBase<List<ResultInfo>>> completeAndSettleList(String role);
    Single<JsonBase<List<ResultInfo>>> completeList();
    Single<JsonBase<Object>> confirmReceipt(String role, int orderId);
    Single<JsonBase<Object>> endService(String role,int orderId);
    Single<JsonBase<List<ResultInfo>>> getOrderList(String role);
    Single<JsonBase<JsonOrderDetailsData>> getOrderDetail(String role,int orderId);
    Single<JsonBase<JsonOrderDetailsData>> applySettleDetail(String role,int orderId);
    Single<JsonBase<List<ResultInfo>>> getPriceAndServiceList(String role);
    Single<JsonBase<Object>> uploadPrice(String role, int orderId, String json);
    Single<JsonBase<Object>> uploadExpressPrice(int orderId, int expressTypeId,int numberKm);
    Single<JsonBase<Object>> uploadCarryPrice(int orderId,int carryTypeId,String jsonArray);
    Single<JsonBase<Object>> confirmDeliver(String role, int orderId);
    Single<JsonBase<List<ResultInfo>>> deliverList(String role);
    Single<JsonBase<JsonDeliverDatails>> deliverOrderDetail(String role, int orderId);
    Single<JsonBase<Object>> getGoods(int orderId);
    Single<JsonBase<List<ResultInfo>>> getGoodsAndPriceList();
    Single<JsonBase<List<ResultInfo>>> alreadyChoose(String role, int id, int orderId, int number);
    Single<JsonBase<Object>> cancelOrder(String role, int orderId);

    Single<JsonBase<JsonOrderDetailsData>> afterSaleDetail(String role,int orderId);
    Single<JsonBase<JsonAfterDetails>> afterSaleDetail(String role, int orderId, int goodsId);
    Single<JsonBase<Object>> processing(String role, int orderId);
    Single<JsonBase<Object>> processing(String role, int orderId,int goodsId);
    Single<JsonBase<Object>> processingEnd( String role, int orderId,int goodsId);
    Single<JsonBase<Object>> processingEnd( String role, int orderId);
    Single<JsonBase<List<ResultInfo>>> afterSaleList(String role);
    Single<JsonBase<List<ComplaintReason>>> complaintDetail(String role);
    Single<JsonBase<List<ResultInfo>>> historyMessage( String role);
    Single<JsonBase<Double>> monthIncome( String role);
    Single<JsonBase<List<ResultInfo>>> paymentView(String role);
    Single<JsonBase<JsonBudgetDetails>> budgetDetail(String role, int orderId);

    Single<JsonBase<List<ResultInfo>>> getResultData();
    Single<JsonBase<UserInfo>> getUserInfo();
}
