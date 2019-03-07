package com.mudoulife.presenter;

import android.util.Log;

import com.mudoulife.base.BaseMvpPresenter;
import com.mudoulife.base.MainView;
import com.mudoulife.data.api.MainApi;
import com.mudoulife.data.model.User;
import com.mudoulife.data.net.response.ComplaintReason;
import com.mudoulife.data.net.response.JsonAfterDetails;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonBudgetDetails;
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.data.net.response.UserInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/10
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.presenter
 * project : MuDouLife
 */
public class OrderBasePresenter extends BaseMvpPresenter<MainView<JsonBase<List<ResultInfo>>>> {
    private static final String TAG = "OrderBasePresenter";
    private final MainApi mMainApi;

    @Inject
    public OrderBasePresenter(MainApi api) {
        this.mMainApi = api;
    }

    public void loadData() {
        mMainApi.getResultData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> resultData) {
                        Log.d(TAG, "onSuccess: resultData = " + resultData);
                        getView().showContent(resultData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void getOrderList(String role) {
        mMainApi.getOrderList(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> resultDataJsonBase) {
                        Log.d(TAG, "onSuccess: resultDataJsonBase = " + resultDataJsonBase);
                        getView().showContent(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e);
                    }
                });
    }

    public void alreadyChoose(String role,int id,int orderId,int number) {
        mMainApi.alreadyChoose(role, id, orderId, number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> resultDataJsonBase) {
                        getView().showContent(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void applySettle(String role,int orderId) {
        mMainApi.applySettle(role,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        getView().onConfirmReceipt(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void applySettleDetail(String role,int orderId) {
        mMainApi.applySettleDetail(role,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<JsonOrderDetailsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<JsonOrderDetailsData> resultDataJsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        getView().showOrderDetails(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void beginService(int orderId) {
        mMainApi.beginService(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        getView().showEmpty();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void startService(int orderId) {
        mMainApi.startService(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        getView().showEmpty();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void completeList() {
        mMainApi.completeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> resultDataJsonBase) {
                        getView().showContent(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void completeAndSettleList(String role) {
        mMainApi.completeAndSettleList(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> resultDataJsonBase) {
                        getView().showContent(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void confirmReceipt(String role,int orderId) {
        mMainApi.confirmReceipt(role, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> jsonBase) {
                        getView().onConfirmReceipt(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void endService(String role,int orderId) {
        mMainApi.endService(role,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        getView().showEmpty();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void getOrderDetail(String role,int orderId) {
        mMainApi.getOrderDetail(role,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<JsonOrderDetailsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<JsonOrderDetailsData> jsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        getView().showOrderDetails(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void getPriceAndServiceList(String role){
        mMainApi.getPriceAndServiceList(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> jsonBase) {
                        Log.d(TAG, "onSuccess: getPriceAndServiceList jsonBase = " + jsonBase);
                        getView().showContent(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e);
                    }
                });
    }

    public void uploadPrice(String role, int orderId, String json) {
        mMainApi.uploadPrice(role,orderId,json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> jsonBase) {
                        Log.d(TAG, "onSuccess: jsonbase = " + jsonBase);
                        getView().onConfirmReceipt(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e);
                    }
                });
    }
    public void uploadExpressPrice(int orderId, int expressTypeId, int numberKm) {
        mMainApi.uploadExpressPrice(orderId, expressTypeId, numberKm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> jsonBase) {
                        Log.d(TAG, "onSuccess: jsonbase = " + jsonBase);
                        getView().onConfirmReceipt(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e);
                    }
                });
    }
    public void uploadCarryPrice(int orderId, int carryTypeId, String jsonArray) {
        mMainApi.uploadCarryPrice(orderId, carryTypeId, jsonArray)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> jsonBase) {
                        Log.d(TAG, "onSuccess: jsonbase = " + jsonBase);
                        getView().onConfirmReceipt(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e);
                    }
                });
    }

    public void confirmDeliver(String role,int orderId) {
        mMainApi.confirmDeliver(role, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        getView().showEmpty();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void deliverList(String role) {
        mMainApi.deliverList(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> resultDataJsonBase) {
                        getView().showContent(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void deliverOrderDetail(String role,int orderId) {
        mMainApi.deliverOrderDetail(role, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<JsonDeliverDatails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<JsonDeliverDatails> resultDataJsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        Log.d(TAG, "onSuccess: resultDataJsonBase = " + resultDataJsonBase);
                        getView().showDeliverDetails(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e);
                    }
                });
    }

    public void getGoods(int orderId) {
        mMainApi.getGoods(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
                        getView().showEmpty();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void getGoodsAndPriceList() {
        mMainApi.getGoodsAndPriceList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> resultDataJsonBase) {
                        Log.d(TAG, "onSuccess: ");
                        getView().showContent(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: ");
                    }
                });
    }
    public void cancelOrder(String role,int orderId) {
        mMainApi.cancelOrder(role, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
                        getView().onCancelOrder(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void processing(String role,int orderId) {
        mMainApi.processing(role, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
                        getView().onCancelOrder(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void processingEnd(String role,int orderId) {
        mMainApi.processingEnd(role, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
                        getView().onConfirmReceipt(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void processing(String role,int orderId,int goodsId) {
        mMainApi.processing(role, orderId,goodsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
                        getView().onCancelOrder(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void processingEnd(String role,int orderId,int goodsId) {
        mMainApi.processingEnd(role, orderId,goodsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Object> resultDataJsonBase) {
                        getView().onConfirmReceipt(resultDataJsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
    public void afterSaleDetail(String role,int orderId) {
        mMainApi.afterSaleDetail(role,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<JsonOrderDetailsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<JsonOrderDetailsData> jsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
                        getView().showOrderDetails(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e);
                    }
                });
    }
    public void afterSaleDetail(String role,int orderId,int goodsId) {
        mMainApi.afterSaleDetail(role,orderId,goodsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<JsonAfterDetails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<JsonAfterDetails> jsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
//                        getView().showOrderDetails(jsonBase);
                        getView().showAfterDetails(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e);
                    }
                });
    }

    public void afterSaleList(String role) {
        mMainApi.afterSaleList(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> jsonBase) {
                        getView().showContent(jsonBase);
//                        getView().showOrderDetails(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void complaintDetail(String role) {
        mMainApi.complaintDetail(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ComplaintReason>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ComplaintReason>> jsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
                        getView().showComplaint(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void historyMessage(String role) {
        mMainApi.historyMessage(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> jsonBase) {
                        getView().showContent(jsonBase);
//                        getView().showOrderDetails(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }

    public void monthIncome(String role) {
        mMainApi.monthIncome(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<Double>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<Double> jsonBase) {
//                        getView().showContent(resultDataJsonBase);
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
                        getView().showInCome(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e );
                    }
                });
    }

    public void paymentView(String role) {
        mMainApi.paymentView(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<List<ResultInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<List<ResultInfo>> jsonBase) {
                        getView().showContent(jsonBase);
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e );
                    }
                });
    }
    public void budgetDetail(String role,int orderId) {
        mMainApi.budgetDetail(role,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<JsonBudgetDetails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<JsonBudgetDetails> jsonBase) {
                        getView().showBudgetDetails(jsonBase);
                        Log.d(TAG, "onSuccess: jsonBase = " + jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                        Log.d(TAG, "onError: e = " + e );
                    }
                });
    }
    public void getUserInfo() {
        mMainApi.getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JsonBase<UserInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubScribe(d);
                        getView().showLoading();
                    }

                    @Override
                    public void onSuccess(JsonBase<UserInfo> jsonBase) {
                        getView().showUserInfo(jsonBase);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }
                });
    }
}
