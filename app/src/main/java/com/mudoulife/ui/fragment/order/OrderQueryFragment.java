package com.mudoulife.ui.fragment.order;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.request.ProjectItemInfo;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.JsonOrderGoods;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.di.component.MainComponent;
import com.mudoulife.ui.adapter.order.OrderOuterAdapter;
import com.mudoulife.ui.adapter.order.OrderQueryAdapter;
import com.mudoulife.ui.fragment.main.ShanghuFragment;
import com.mudoulife.ui.fragment.main.SubscriptionFragment;
import com.mudoulife.util.DialogUtils;
import com.mudoulife.util.Utils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderQueryFragment extends OrderBaseFragment {
    private static final String TAG = "OrderQueryFragment";
    TextView mNameValueTv;
    TextView mAddressValueTv;
    TextView mPhoneValueTv;
    TextView mTimeValueTv;
    TextView mUserRemarkTv;
    RelativeLayout mProjectContainer;

    RelativeLayout mServiceContainer;
    TextView mServiceNameTv;
    TextView mServiceValueTv;
    TextView mNumberValueTv;
    TextView mPriceValueTv;
    private LinearLayout mMaterialLabelContainer;
    private LinearLayout mMaterialContentContainer;
    private TextView mProjectLabel1Tv;
    private TextView mProjectLabel2Tv;
    private TextView mProjectLabel3Tv;
    private TextView mProjectLabel4Tv;


    public OrderQueryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent(MainComponent.class).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void loadData() {
        mPresenter.getOrderList(getRoleName());
    }

    @Override
    protected void initAdapter() {
        mAdapter = new OrderQueryAdapter(mContext, mDatas,mMainFragmentName);
    }

    @Override
    protected void onClickRightBtn(int position) {
        mPresenter.getOrderDetail(getRoleName(), mDatas.get(position).getId());
    }

    @Override
    protected void onClickLeftBtn(int position) {
        initDialog();
    }

    @Override
    protected void onSureBtnClick() {
//        super.onSureBtnClick();
        ResultInfo data = mDatas.get(mCurrentPosition);
        mPresenter.confirmReceipt(getRoleName(), data.getId());
//        if (mMainFragmentName.equals(ShanghuFragment.class.getName())
//                || mMainFragmentName.equals(SubscriptionFragment.class.getName())) {
//            setOnItemSwitchListener();
//        }
//        setOnItemSwitchListener();
    }

    @Override
    protected int getNextItem() {
        if (mMainFragmentName.equals(ShanghuFragment.class.getName())) {
            return Globals.LEFT_ACCOUNT_ITEM;
        }
        return Globals.LEFT_PRICE_ITEM;
    }

    @Override
    protected void loadPopData() {

//        int roleId = getRoleId();
//        switch (roleId){
//            case Globals.ROLE_WORKER_ID:
//                mPresenter.getOrderList();
//                break;
//            case Globals.ROLE_SHANG_HU_ID:
//                break;
//            case Globals.ROLE_SUBSCRIPTION_ID:
//                break;
//            case Globals.ROLE_EXPRESS_ID:
//                break;
//            case Globals.ROLE_CARRY_ID:
//                break;
//        }
    }

    @Override
    protected void initPopView(View view) {
        initWorkerView(view);
    }

    private void initWorkerView(View view) {
        mCloseIv = view.findViewById(R.id.close_img);
        mNameValueTv = view.findViewById(R.id.content_1);
        mAddressValueTv = view.findViewById(R.id.address_value);
        mPhoneValueTv = view.findViewById(R.id.phone_value);
        mTimeValueTv = view.findViewById(R.id.appoint_time_value);
        mUserRemarkTv = view.findViewById(R.id.user_remark);
        mProjectContainer = view.findViewById(R.id.project_container);
        mServiceContainer = view.findViewById(R.id.service_container);

        mServiceNameTv = view.findViewById(R.id.server_name_id);
        if (getRoleId() == Globals.ROLE_SHANG_HU_ID){
            mServiceNameTv.setText("商品名称:");
        }
        mServiceValueTv = view.findViewById(R.id.server_value_id);
//        mNumberNameTv = view.findViewById(R.id.number_name_id);
        mNumberValueTv = view.findViewById(R.id.content_3);
//        mPriceNameTv = view.findViewById(R.id.price_name_id);
        mPriceValueTv = view.findViewById(R.id.content_2);
        mSureBtn = view.findViewById(R.id.sure_btn);
        mMaterialLabelContainer = mProjectContainer.findViewById(R.id.material_label_container);
        mProjectLabel1Tv = mMaterialLabelContainer.findViewById(R.id.label_item_1);
        mProjectLabel2Tv = mMaterialLabelContainer.findViewById(R.id.label_item_2);
        mProjectLabel3Tv = mMaterialLabelContainer.findViewById(R.id.label_item_3);
        mProjectLabel4Tv = mMaterialLabelContainer.findViewById(R.id.label_item_4);
        mProjectLabel1Tv.setText(getResources().getString(R.string.material_name));
        mProjectLabel2Tv.setText(getResources().getString(R.string.price));
        mProjectLabel3Tv.setText(getResources().getString(R.string.number));
        mProjectLabel4Tv.setVisibility(View.GONE);
        mMaterialContentContainer = mProjectContainer.findViewById(R.id.material_content_container);

    }

    private void initDialog() {
        DialogUtils.showChoiceDialog(mContext,
                getResources().getString(R.string.receive_dialog_tittle),
                getResources().getString(R.string.string_sure),
                getResources().getString(R.string.string_cancel),
                new DialogUtils.OnDialogListener() {
                    @Override
                    public void onPositiveListener() {
//                        确定
                        ResultInfo data = mDatas.get(mCurrentPosition);
                        mPresenter.cancelOrder(getRoleName(), data.getId());

                    }

                    @Override
                    public void onNegativeListener() {
                        //取消
                    }
                });
    }

    @Override
    public void showOrderDetails(JsonBase<JsonOrderDetailsData> jsonBase) {
        JsonOrderDetailsData data = (JsonOrderDetailsData) jsonBase.getData();
        Log.d(TAG, "showOrderDetails: data = " + data);
        if (data != null) {
            mRightPopWindow = initPopWindow(R.layout.layout_receive_sure);
            switch (getRoleId()) {
                case Globals.ROLE_WORKER_ID:
                    mServiceContainer.setVisibility(View.VISIBLE);
                    mProjectContainer.setVisibility(View.GONE);
                    mServiceValueTv.setText(data.getServiceName());
                    break;
                case Globals.ROLE_SHANG_HU_ID:
                    mServiceContainer.setVisibility(View.VISIBLE);
                    mProjectContainer.setVisibility(View.GONE);
                    StringBuilder builder = new StringBuilder();
                    List<JsonOrderGoods> orderGoods = data.getOrderGoods();
                    if (orderGoods != null){
                        for (int i = 0; i < orderGoods.size(); i++) {
                            JsonOrderGoods jsonOrderGoods = orderGoods.get(i);
                            String goodsName = jsonOrderGoods.getGoodsName();
                            if (i == orderGoods.size() - 1){
                                builder.append(goodsName);
                            }else {
                                builder.append(goodsName + ",");
                            }
                        }
                    }
                    Log.d(TAG, "showOrderDetails: builder = " + builder);
                    mServiceValueTv.setText(builder.toString());
                    break;
                case Globals.ROLE_CARRY_ID:
                case Globals.ROLE_EXPRESS_ID:
                    mServiceContainer.setVisibility(View.GONE);
                    mProjectContainer.setVisibility(View.VISIBLE);
                    addView(data);
                    break;
            }

            mNameValueTv.setText(data.getReceiverName());
            mAddressValueTv.setText(data.getReceiverAddress());
            mPhoneValueTv.setText(data.getReceiverPhone());
            mTimeValueTv.setText(data.getMakeTime());
            if (!data.getRemark().isEmpty() && !data.getRemark().equals("")) {
                mUserRemarkTv.setText(data.getRemark());
                mUserRemarkTv.setGravity(Gravity.START);
            }
        }

    }

    private void addView(JsonOrderDetailsData data) {
        List<JsonOrderGoods> orderGoods = data.getOrderGoods();
        Log.d(TAG, "addView: orderGoods = " + orderGoods);
        boolean isFirstMaterialVisible = true;
        mMaterialContentContainer.removeAllViews();
        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        float viewWidth = widthPixels - Utils.dp2px(16) * 2;
        int serviceIndex = 0;
        int materialIndex = 0;

        if (orderGoods != null && orderGoods.size() > 0) {
//            mAlreadyChooseTv.setVisibility(View.VISIBLE);
            for (int i = 0; i < orderGoods.size(); i++) {
                JsonOrderGoods info = orderGoods.get(i);
                if (isFirstMaterialVisible) {
                    mMaterialContentContainer.setVisibility(View.VISIBLE);
                    mMaterialLabelContainer.setVisibility(View.VISIBLE);
//                    mMaterialChooseTv.setVisibility(View.VISIBLE);
                    isFirstMaterialVisible = false;
                }
                LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_window_commit_label, null);
                TextView labelItem1 = view.findViewById(R.id.label_item_1);
                TextView labelItem2 = view.findViewById(R.id.label_item_2);
                TextView labelItem3 = view.findViewById(R.id.label_item_3);
                TextView labelItem4 = view.findViewById(R.id.label_item_4);
                labelItem4.setVisibility(View.GONE);
                labelItem1.setText(info.getGoodsName());
                labelItem2.setText(info.getPrice() + "元/个");
                labelItem3.setText(String.valueOf(info.getNumber()));
                labelItem4.setVisibility(View.GONE);
                mMaterialContentContainer.addView(view, materialIndex);
                materialIndex++;
            }
        }

    }

    @Override
    public void onCancelOrder(JsonBase jsonBase) {
//        ResultInfo data = mDatas.get(mCurrentPosition);
//        data.setStatus(Globals.SERVICE_ALREADY_PAY);
//        data.setStatusName("已支付定金");
////        data.setLocalStatus(LocalStatus.WAIT);
//        mAdapter.notifyItemChanged(mCurrentPosition);
        loadData();
    }

    @Override
    public void onConfirmReceipt(JsonBase jsonBase) {
//        ResultInfo data = mDatas.get(mCurrentPosition);
////        data.setLocalStatus(LocalStatus.FINISH);
//        mAdapter.notifyItemChanged(mCurrentPosition);
        loadData();
        mRightPopWindow.dismiss();
        if (mMainFragmentName.equals(ShanghuFragment.class.getName())
                || mMainFragmentName.equals(SubscriptionFragment.class.getName())) {
            setOnItemSwitchListener();
        }
    }
}
