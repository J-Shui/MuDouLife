package com.mudoulife.ui.fragment.aftersale;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.base.BaseFragment;
import com.mudoulife.base.MainView;
import com.mudoulife.data.net.response.ComplaintReason;
import com.mudoulife.data.net.response.JsonAfterDetails;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonBudgetDetails;
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.data.net.response.UserInfo;
import com.mudoulife.di.component.MainComponent;
import com.mudoulife.presenter.OrderBasePresenter;
import com.mudoulife.ui.fragment.order.OrderBaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AfterSaleDetailsFragment extends  BaseFragment implements MainView<JsonBase<List<ResultInfo>>> {
    private static final String TAG = "AfterSaleDetailsFragment";
    @BindView(R.id.star1_id)
    ImageView mStar1Iv;
    @BindView(R.id.star2_id)
    ImageView mStar2Iv;
    @BindView(R.id.star3_id)
    ImageView mStar3Iv;
    @BindView(R.id.star4_id)
    ImageView mStar4Iv;
    @BindView(R.id.star5_id)
    ImageView mStar5Iv;
    @BindView(R.id.evaluate_id)
    TextView mEvaluateTv;
    @BindView(R.id.complaint_reason_1)
    TextView mComplaintReason1Tv;
    @BindView(R.id.complaint_reason_2)
    TextView mComplaintReason2Tv;
    @BindView(R.id.complaint_reason_3)
    TextView mComplaintReason3Tv;
    private Unbinder mUnbinder;
    @Inject
    OrderBasePresenter mPresenter;

    public AfterSaleDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent(MainComponent.class).inject(this);
        mPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaint_details, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mStar1Iv.setImageLevel(1);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.d(TAG, "onHiddenChanged: hidden = " + hidden);
        super.onHiddenChanged(hidden);
        if (!hidden)
            loadData();
    }

    private void loadData() {
        mPresenter.complaintDetail(getRoleName());
    }

    @Override
    public void showComplaint(JsonBase<List<ComplaintReason>> jsonBase) {
        List<ComplaintReason> reasons = (List<ComplaintReason>) jsonBase.getData();
        Log.d(TAG, "showComplaint: reason = " + reasons);
//        if (reason != null) {
//            mComplaintReason1Tv.setText(reason.getReason());
//        }
    }

    @Override
    public void showUserInfo(JsonBase<UserInfo> jsonBase) {

    }

    @Override
    public void showBudgetDetails(JsonBase<JsonBudgetDetails> jsonBase) {

    }

    @Override
    public void showAfterDetails(JsonBase<JsonAfterDetails> jsonBase) {

    }


    @Override
    public void showContent(JsonBase<List<ResultInfo>> data) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showOrderDetails(JsonBase<JsonOrderDetailsData> jsonBase) {

    }

    @Override
    public void showDeliverDetails(JsonBase<JsonDeliverDatails> jsonBase) {

    }

    @Override
    public void onCancelOrder(JsonBase jsonBase) {

    }

    @Override
    public void onConfirmReceipt(JsonBase jsonBase) {

    }

    @Override
    public void showInCome(JsonBase<Double> jsonBase) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mPresenter.detachView(this);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(Throwable e) {
        Log.d(TAG, "showError: e = " + e);
    }

    @Override
    public void showTipMsg(String msg) {

    }
}
