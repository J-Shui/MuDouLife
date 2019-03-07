package com.mudoulife.base;

import android.support.annotation.UiThread;

import com.mudoulife.data.net.response.ComplaintReason;
import com.mudoulife.data.net.response.JsonAfterDetails;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonBudgetDetails;
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.UserInfo;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.mvp
 * project : MuDouLife
 */
public interface MainView<M> extends BaseView {
    @UiThread
    public void showContent(M data);
    @UiThread
    public void showEmpty();

    void showOrderDetails(JsonBase<JsonOrderDetailsData> jsonBase);

    void showDeliverDetails(JsonBase<JsonDeliverDatails> jsonBase);

    void onCancelOrder(JsonBase jsonBase);

    void onConfirmReceipt(JsonBase jsonBase);

    void showInCome(JsonBase<Double> jsonBase);

    void showComplaint(JsonBase<List<ComplaintReason>> jsonBase);

    void showUserInfo(JsonBase<UserInfo> jsonBase);

    void showBudgetDetails(JsonBase<JsonBudgetDetails> jsonBase);

    void showAfterDetails(JsonBase<JsonAfterDetails> jsonBase);
}
