package com.mudoulife.data.net.datasource;

import android.util.Log;

import com.mudoulife.data.api.MainApi;
import com.mudoulife.data.net.LocalStatus;
import com.mudoulife.data.net.response.ComplaintReason;
import com.mudoulife.data.net.response.JsonAfterDetails;
import com.mudoulife.data.net.response.JsonBudgetDetails;
import com.mudoulife.data.net.response.JsonContents;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.data.net.response.UserInfo;
import com.mudoulife.data.net.service.MainService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/10
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net
 * project : MuDouLife
 */
public class MainDataSource implements MainApi {
    private static final String TAG = "MainDataSource";
    MainService mResultService;

    @Inject
    public MainDataSource(MainService service){
        this.mResultService = service;
    }
    public Single<JsonBase<List<ResultInfo>>> getResultData() {
        return  Single.create(new SingleOnSubscribe<JsonBase<List<ResultInfo>>>() {
            @Override
            public void subscribe(SingleEmitter<JsonBase<List<ResultInfo>>> emitter) throws Exception {
                Log.d(TAG, "subscribe: ");
                emitter.onSuccess(getDatas());
            }
        });
    }

    @Override
    public Single<JsonBase<UserInfo>> getUserInfo() {
        return mResultService.getUserInfo();
    }

    private JsonBase<List<ResultInfo>> getDatas() {
        Log.d(TAG, "getDatas: ");
        JsonBase<List<ResultInfo>> jsonBase = new JsonBase();
        Log.d(TAG, "getDatas: jsonBase = " + jsonBase);
        JsonContents<ResultInfo> jsonContents = new JsonContents<>();
        Log.d(TAG, "getDatas: jsonContents = " + jsonContents);
//        JsonContents<ResultInfo> resultInfos = new JsonContents<>();
//        jsonContents.setDatas(resultInfos);
        List<ResultInfo> resultInfos = new ArrayList<>();
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        for (int i = 0; i < 10; i++) {
            Log.d(TAG, "getDatas: for = ");
            ResultInfo data = new ResultInfo();
            data.setNumber(i + 50);
            data.setOrderNumber("DBF0000000000000000" + i);
            data.setServiceName("铝合金" + i);
            data.setStandard("4" + i + "x" + "4" + i);
            data.setCreateTime("2018年12月" + i + "日");
            data.setImgUrl(url);
            resultInfos.add(data);
            Log.d(TAG, "getDatas: jsonContents = " + jsonContents);
        }
        jsonBase.setData(resultInfos);
        Log.d("MainDataSource","getDatas = " + jsonContents);
        return jsonBase;
    }

    @Override
    public Single<JsonBase<Object>> applySettle(String role, int orderId) {
        Log.d(TAG, "applySettle: ");
        return mResultService.applySettle(role, orderId);
    }

    @Override
    public Single<JsonBase<Object>> beginService(int orderId) {
        Log.d(TAG, "beginService: ");
        return mResultService.beginService(orderId);
    }

    @Override
    public Single<JsonBase<Object>> startService(int orderId) {
        return mResultService.startService(orderId);
    }

    @Override
    public Single<JsonBase<List<ResultInfo>>> completeAndSettleList(String role) {
        Log.d(TAG, "completeAndSettleList: ");
        return mResultService.completeAndSettleList(role);
    }

    @Override
    public Single<JsonBase<List<ResultInfo>>> completeList() {
        return mResultService.completeList();
    }

    @Override
    public Single<JsonBase<Object>> confirmReceipt(String role, int orderId) {
        Log.d(TAG, "confirmReceipt: ");
        return mResultService.confirmReceipt(role, orderId);
    }

    @Override
    public Single<JsonBase<Object>> endService(String role,int orderId) {
        Log.d(TAG, "endService: ");
        return mResultService.endService(role,orderId);
    }

    @Override
    public Single<JsonBase<List<ResultInfo>>> getOrderList(String role) {
        Log.d(TAG, "getOrderList: ");
        return mResultService.getOrderList(role);
    }

    @Override
    public Single<JsonBase<JsonOrderDetailsData>> getOrderDetail(String role,int orderId) {
        Log.d(TAG, "getOrderDetail: ");
        return mResultService.getOrderDetail(role,orderId);
    }

    @Override
    public Single<JsonBase<JsonOrderDetailsData>> applySettleDetail(String role, int orderId) {
        Log.d(TAG, "applySettleDetail: ");
        return mResultService.applySettleDetail(role, orderId);
    }

    @Override
    public Single<JsonBase<List<ResultInfo>>> getPriceAndServiceList(String role) {
        Log.d(TAG, "getPriceAndServiceList: ");
        return mResultService.getPriceAndServiceList(role);
    }

    @Override
    public Single<JsonBase<Object>> uploadPrice(String role, int orderId, String json) {
        Log.d(TAG, "uploadPrice: ");
        return mResultService.uploadPrice(role, orderId,json);
    }

    @Override
    public Single<JsonBase<Object>> uploadExpressPrice(int orderId, int expressTypeId, int numberKm) {
        Log.d(TAG, "uploadExpressPrice: ");
        return mResultService.uploadExpressPrice(orderId, expressTypeId, numberKm);
    }

    @Override
    public Single<JsonBase<Object>> uploadCarryPrice(int orderId, int carryTypeId, String jsonArray) {
        Log.d(TAG, "uploadCarryPrice: ");
        return mResultService.uploadCarryPrice(orderId, carryTypeId, jsonArray);
    }

    @Override
    public Single<JsonBase<Object>> confirmDeliver(String role, int orderId) {
        Log.d(TAG, "confirmDeliver: ");
        return mResultService.confirmDeliver(role, orderId);
    }

    @Override
    public Single<JsonBase<List<ResultInfo>>> deliverList(String role) {
        Log.d(TAG, "deliverList: ");
        return mResultService.deliverList(role);
    }

    @Override
    public Single<JsonBase<JsonDeliverDatails>> deliverOrderDetail(String role, int orderId) {
        Log.d(TAG, "deliverOrderDetail: ");
        return mResultService.deliverOrderDetail(role, orderId);
    }

    @Override
    public Single<JsonBase<Object>> getGoods(int orderId) {
        Log.d(TAG, "getGoods: ");
        return mResultService.getGoods(orderId);
    }

    @Override
    public Single<JsonBase<List<ResultInfo>>> getGoodsAndPriceList() {
        Log.d(TAG, "getGoodsAndPriceList: ");
        return mResultService.getGoodsAndPriceList();
    }


    @Override
    public Single<JsonBase<List<ResultInfo>>> alreadyChoose(String role, int id, int orderId, int number) {
        Log.d(TAG, "alreadyChoose: ");
        return mResultService.alreadyChoose(role, id, orderId, number);
    }

    @Override
    public Single<JsonBase<Object>> cancelOrder(String role, int orderId) {
        Log.d(TAG, "cancelOrder: ");
        return mResultService.cancelOrder(role,orderId);
    }

    @Override
    public Single<JsonBase<JsonOrderDetailsData>> afterSaleDetail(String role, int orderId) {
        Log.d(TAG, "afterSaleDetail: ");
        return mResultService.afterSaleDetail(role, orderId);
    }

    @Override
    public Single<JsonBase<JsonAfterDetails>> afterSaleDetail(String role, int orderId, int goodsId) {
        return mResultService.afterSaleDetail(role, orderId, goodsId);
    }

    @Override
    public Single<JsonBase<Object>> processing(String role, int orderId) {
        Log.d(TAG, "processing: ");
        return mResultService.processing(role, orderId);
    }

    @Override
    public Single<JsonBase<Object>> processing(String role, int orderId, int goodsId) {
        return mResultService.processing(role, orderId, goodsId);
    }

    @Override
    public Single<JsonBase<Object>> processingEnd(String role, int orderId, int goodsId) {
        return mResultService.processingEnd(role, orderId, goodsId);
    }

    @Override
    public Single<JsonBase<Object>> processingEnd(String role, int orderId) {
        Log.d(TAG, "processingEnd: ");
        return mResultService.processingEnd(role, orderId);
    }

    @Override
    public Single<JsonBase<List<ResultInfo>>> afterSaleList(String role) {
        Log.d(TAG, "afterSaleList: ");
        return mResultService.afterSaleList(role);
    }

    @Override
    public Single<JsonBase<List<ComplaintReason>>> complaintDetail(String role) {
        Log.d(TAG, "complaintDetail: ");
        return mResultService.complaintDetail(role);
    }

    @Override
    public Single<JsonBase<List<ResultInfo>>> historyMessage(String role) {
        Log.d(TAG, "historyMessage: ");
        return mResultService.historyMessage(role);
    }

    @Override
    public Single<JsonBase<Double>> monthIncome(String role) {
        Log.d(TAG, "monthIncome: ");
        return mResultService.monthIncome(role);
    }

    @Override
    public Single<JsonBase<List<ResultInfo>>> paymentView(String role) {
        Log.d(TAG, "paymentView: ");
        return mResultService.paymentView(role);
    }

    @Override
    public Single<JsonBase<JsonBudgetDetails>> budgetDetail(String role, int orderId) {
        return mResultService.budgetDetail(role,orderId);
    }
}
