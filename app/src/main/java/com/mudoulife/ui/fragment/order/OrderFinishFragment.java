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
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.JsonOrderGoods;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.di.component.MainComponent;
import com.mudoulife.ui.adapter.order.OrderFinishAdapter;
import com.mudoulife.ui.adapter.order.OrderOuterAdapter;
import com.mudoulife.util.Utils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFinishFragment extends OrderBaseFragment {
    private static final String TAG = "OrderQueryFragment";
    TextView mNameValueTv;
    TextView mAddressValueTv;
    TextView mPhoneValueTv;
    TextView mTimeValueTv;
    TextView mUserRemarkTv;
    RelativeLayout mProjectContainer;
    RelativeLayout mServiceContainer;
    TextView mMaterialNameTv;
    TextView mMaterialValueTv;
    TextView mNumberNameTv;
    TextView mNumberValueTv;
    TextView mPriceNameTv;
    TextView mPriceValueTv;
    private TextView mServiceNameTv;
    private TextView mServiceValueTv;
    private LinearLayout mServiceLabelContainer;
    private LinearLayout mMaterialLabelContainer;
    private TextView mServiceLabel1Tv;
    private TextView mServiceLabel2Tv;
    private TextView mServiceLabel3Tv;
    private TextView mServiceLabel4Tv;
    private TextView mProjectLabel1Tv;
    private TextView mProjectLabel2Tv;
    private TextView mProjectLabel3Tv;
    private TextView mProjectLabel4Tv;
    private LinearLayout mServiceContentContainer;
    private LinearLayout mMaterialContentContainer;
    private TextView mAlreadyChooseTv;
    private TextView mServiceChooseTv;
    private TextView mMaterialChooseTv;
    private TextView mStoreAddressTv;
    private TextView mExpressNameTv;
    private TextView mExpressPhoneTv;
    private RelativeLayout mAcceptContainer;
    private TextView mTotalTv;
    private TextView mStoreNameTv;
    private TextView mStorePhoneTv;


    public OrderFinishFragment() {
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
//        mPresenter.loadData();
        if (getRoleId() == Globals.ROLE_SHANG_HU_ID) {
            mPresenter.deliverList(getRoleName());
        } else if (getRoleId() == Globals.ROLE_WORKER_ID) {
            mPresenter.completeList();
        } else if (getRoleId() == Globals.ROLE_EXPRESS_ID || getRoleId() == Globals.ROLE_CARRY_ID) {
            mPresenter.completeAndSettleList(getRoleName());
        }
    }

    @Override
    protected void initAdapter() {
        mAdapter = new OrderFinishAdapter(mContext, mDatas, mMainFragmentName);
    }

    @Override
    protected void onClickRightBtn(int position) {
        mIsLeftWindow = false;
        //TODO
        if (getRoleId() == Globals.ROLE_SHANG_HU_ID || getRoleId() == Globals.ROLE_EXPRESS_ID) {
            Log.d(TAG, "onClickRightBtn: id = " + mDatas.get(position).getId());
            mPresenter.deliverOrderDetail(getRoleName(), mDatas.get(position).getId());
        } else if (getRoleId() == Globals.ROLE_WORKER_ID) {
//            mPresenter.endService(mDatas.get(position).getId());
            mPresenter.applySettleDetail(getRoleName(), mDatas.get(position).getId());
//            mPresenter.applySettle(getRoleName(),mDatas.get(position).getId());
        } else if (getRoleId() == Globals.ROLE_CARRY_ID) {
            mPresenter.getOrderDetail(getRoleName(), mDatas.get(position).getId());
//            mPresenter.endService(mDatas.get(position).getId());
        }
//        initPopWindow(R.layout.layout_receive_sure);
    }

    @Override
    protected void onClickLeftBtn(int position) {
        mIsLeftWindow = true;
        Log.d(TAG, "onClickLeftBtn: position = " + position);
        //TODO
        if (getRoleId() == Globals.ROLE_SHANG_HU_ID || getRoleId() == Globals.ROLE_EXPRESS_ID) {
            mPresenter.deliverOrderDetail(getRoleName(), mDatas.get(position).getId());
        } else if (getRoleId() == Globals.ROLE_WORKER_ID) {
            mPresenter.getOrderDetail(getRoleName(), mDatas.get(position).getId());
//            mPresenter.endService(mDatas.get(position).getId());
        } else if (getRoleId() == Globals.ROLE_CARRY_ID) {
            mPresenter.getOrderDetail(getRoleName(), mDatas.get(position).getId());
//            mPresenter.endService(mDatas.get(position).getId());
        }

//        mLeftPopWindow = initPopWindow(R.layout.layout_receive_sure);
    }

    @Override
    protected int getNextItem() {
        return Globals.LEFT_ORDER_ITEM;
    }

    @Override
    protected void loadPopData() {

    }

    @Override
    protected void onSureBtnClick() {
        if (mIsLeftWindow) {
            if (getRoleId() == Globals.ROLE_WORKER_ID || getRoleId() == Globals.ROLE_CARRY_ID) {
                mPresenter.endService(getRoleName(), mDatas.get(mCurrentPosition).getId());
            } else if (getRoleId() == Globals.ROLE_SHANG_HU_ID || getRoleId() == Globals.ROLE_EXPRESS_ID) {
                mPresenter.confirmDeliver(getRoleName(), mDatas.get(mCurrentPosition).getId());
            } else {
                super.onSureBtnClick();
            }
        } else {
            if (getRoleId() == Globals.ROLE_WORKER_ID) {
                mPresenter.applySettle(getRoleName(), mDatas.get(mCurrentPosition).getId());
            } else if (getRoleId() == Globals.ROLE_SHANG_HU_ID) {
                mPresenter.applySettle(getRoleName(), mDatas.get(mCurrentPosition).getId());
            } else if (getRoleId() == Globals.ROLE_EXPRESS_ID) {
                mPresenter.applySettle(getRoleName(), mDatas.get(mCurrentPosition).getId());
            } else if (getRoleId() == Globals.ROLE_CARRY_ID) {
                mPresenter.applySettle(getRoleName(), mDatas.get(mCurrentPosition).getId());
            }
//            mRightPopWindow.dismiss();
//            setOnItemSwitchListener();
        }

    }

    @Override
    protected void initPopView(View view) {

        mCloseIv = view.findViewById(R.id.close_img);
        mNameValueTv = view.findViewById(R.id.content_1);
        mAddressValueTv = view.findViewById(R.id.address_value);
        mPhoneValueTv = view.findViewById(R.id.phone_value);
        mTimeValueTv = view.findViewById(R.id.appoint_time_value);
        mUserRemarkTv = view.findViewById(R.id.user_remark);

        mSureBtn = view.findViewById(R.id.sure_btn);
        if (getRoleId() == Globals.ROLE_WORKER_ID) {
            initWorkView(view);
            if (mIsLeftWindow) {
                mSureBtn.setText(getResources().getString(R.string.finish_service));
            } else {
                mSureBtn.setText(getResources().getString(R.string.apply_close_account));
            }
        } else if (getRoleId() == Globals.ROLE_SHANG_HU_ID) {
            initStoreView(view);
            if (mIsLeftWindow) {
                mSureBtn.setText(getResources().getString(R.string.sure_deliver));
            } else mSureBtn.setText(getResources().getString(R.string.apply_close_account));
        } else if (getRoleId() == Globals.ROLE_EXPRESS_ID) {
            initExpressView(view);
            if (mIsLeftWindow) {
                mSureBtn.setText(getResources().getString(R.string.already_delivery));
            } else mSureBtn.setText(getResources().getString(R.string.apply_close_account));
        } else if (getRoleId() == Globals.ROLE_CARRY_ID) {
            initCarryView(view);
        }

    }

    private void initStoreView(View view) {
        mStoreAddressTv = view.findViewById(R.id.store_address_value);
        mExpressNameTv = view.findViewById(R.id.express_name_value);
        mExpressPhoneTv = view.findViewById(R.id.express_phone_value);
        mProjectContainer = view.findViewById(R.id.project_container);
        mMaterialLabelContainer = mProjectContainer.findViewById(R.id.material_label_container);
        mProjectLabel1Tv = mMaterialLabelContainer.findViewById(R.id.label_item_1);
        mProjectLabel2Tv = mMaterialLabelContainer.findViewById(R.id.label_item_2);
        mProjectLabel3Tv = mMaterialLabelContainer.findViewById(R.id.label_item_3);
        mProjectLabel4Tv = mMaterialLabelContainer.findViewById(R.id.label_item_4);
        mMaterialContentContainer = mProjectContainer.findViewById(R.id.material_content_container);
        mAcceptContainer = view.findViewById(R.id.user_accept_container);

        mProjectLabel1Tv.setText(getResources().getString(R.string.material_name));
        mProjectLabel2Tv.setText(getResources().getString(R.string.price));
        mProjectLabel3Tv.setText(getResources().getString(R.string.number));
        mProjectLabel4Tv.setVisibility(View.GONE);
    }

    private void initCarryView(View view) {
        mProjectContainer = view.findViewById(R.id.project_container);
        mServiceContainer = view.findViewById(R.id.service_container);

        mServiceValueTv = view.findViewById(R.id.server_value_id);
        mServiceNameTv = view.findViewById(R.id.server_name_id);
        mServiceNameTv.setText("商品名称:");
        mMaterialLabelContainer = mProjectContainer.findViewById(R.id.material_label_container);
        mProjectLabel1Tv = mMaterialLabelContainer.findViewById(R.id.label_item_1);
        mProjectLabel2Tv = mMaterialLabelContainer.findViewById(R.id.label_item_2);
        mProjectLabel3Tv = mMaterialLabelContainer.findViewById(R.id.label_item_3);
        mProjectLabel4Tv = mMaterialLabelContainer.findViewById(R.id.label_item_4);
        mMaterialContentContainer = mProjectContainer.findViewById(R.id.material_content_container);

        mProjectLabel1Tv.setText(getResources().getString(R.string.bian_hao));
        mProjectLabel2Tv.setText(getResources().getString(R.string.material_name));
        mProjectLabel3Tv.setText(getResources().getString(R.string.starting_price));
        mProjectLabel4Tv.setText(getResources().getString(R.string.number));
    }

    private void initExpressView(View view) {
        mProjectContainer = view.findViewById(R.id.project_container);
        mMaterialLabelContainer = mProjectContainer.findViewById(R.id.material_label_container);
        mProjectLabel1Tv = mMaterialLabelContainer.findViewById(R.id.label_item_1);
        mProjectLabel2Tv = mMaterialLabelContainer.findViewById(R.id.label_item_2);
        mProjectLabel3Tv = mMaterialLabelContainer.findViewById(R.id.label_item_3);
        mProjectLabel4Tv = mMaterialLabelContainer.findViewById(R.id.label_item_4);
        mMaterialContentContainer = mProjectContainer.findViewById(R.id.material_content_container);
        mTotalTv = mProjectContainer.findViewById(R.id.total_value);
        mStoreNameTv = view.findViewById(R.id.store_name_value);
        mStoreAddressTv = view.findViewById(R.id.store_address_value);
        mStorePhoneTv = view.findViewById(R.id.store_phone_value);

        mProjectLabel1Tv.setText(getResources().getString(R.string.material_name));
        mProjectLabel2Tv.setText(getResources().getString(R.string.price));
        mProjectLabel3Tv.setText(getResources().getString(R.string.number));
        mProjectLabel4Tv.setVisibility(View.GONE);

    }

    private void initWorkView(View view) {
        mProjectContainer = view.findViewById(R.id.project_container);
        if (mIsLeftWindow) {
            mServiceContainer = view.findViewById(R.id.service_container);
            mServiceValueTv = mServiceContainer.findViewById(R.id.server_value_id);
        } else {
            mAlreadyChooseTv = mProjectContainer.findViewById(R.id.already_choose_text);
            mServiceChooseTv = mProjectContainer.findViewById(R.id.service_text);
            mMaterialChooseTv = mProjectContainer.findViewById(R.id.material_text);

            mServiceLabelContainer = mProjectContainer.findViewById(R.id.service_label_container);
            mMaterialLabelContainer = mProjectContainer.findViewById(R.id.material_label_container);

            mServiceLabel1Tv = mServiceLabelContainer.findViewById(R.id.label_item_1);
            mServiceLabel2Tv = mServiceLabelContainer.findViewById(R.id.label_item_2);
            mServiceLabel3Tv = mServiceLabelContainer.findViewById(R.id.label_item_3);
            mServiceLabel4Tv = mServiceLabelContainer.findViewById(R.id.label_item_4);
            mServiceLabel1Tv.setText(getResources().getString(R.string.material_name));
            mServiceLabel2Tv.setText(getResources().getString(R.string.price));
            mServiceLabel3Tv.setVisibility(View.GONE);
            mServiceLabel4Tv.setVisibility(View.GONE);

            mProjectLabel1Tv = mMaterialLabelContainer.findViewById(R.id.label_item_1);
            mProjectLabel2Tv = mMaterialLabelContainer.findViewById(R.id.label_item_2);
            mProjectLabel3Tv = mMaterialLabelContainer.findViewById(R.id.label_item_3);
            mProjectLabel4Tv = mMaterialLabelContainer.findViewById(R.id.label_item_4);

            mProjectLabel1Tv.setText(getResources().getString(R.string.bian_hao));
            mProjectLabel2Tv.setText(getResources().getString(R.string.name));
            mProjectLabel3Tv.setText(getResources().getString(R.string.price));
            mProjectLabel4Tv.setText(getResources().getString(R.string.number));

            mServiceContentContainer = mProjectContainer.findViewById(R.id.service_content_container);
            mMaterialContentContainer = mProjectContainer.findViewById(R.id.material_content_container);
        }
    }

    @Override
    public void showContent(JsonBase<List<ResultInfo>> jsonBase) {
        super.showContent(jsonBase);
    }

    @Override
    public void showOrderDetails(JsonBase<JsonOrderDetailsData> jsonBase) {
        JsonOrderDetailsData data = (JsonOrderDetailsData) jsonBase.getData();
        Log.d(TAG, "showOrderDetails: data = " + data);
        if (data != null) {
            if (mIsLeftWindow) {
                mLeftPopWindow = initPopWindow(R.layout.layout_receive_sure);
                mServiceContainer.setVisibility(View.VISIBLE);
                mProjectContainer.setVisibility(View.GONE);
            } else {
                if (getRoleId() == Globals.ROLE_WORKER_ID) {
                    mRightPopWindow = initPopWindow(R.layout.layout_window_finish_worker);
                    mProjectContainer.setVisibility(View.VISIBLE);
                    addView(data);

                } else {
                    mRightPopWindow = initPopWindow(R.layout.layout_receive_sure);
                    mServiceContainer.setVisibility(View.GONE);
                    mProjectContainer.setVisibility(View.VISIBLE);
                    addView(data.getOrderGoods());
                }


            }
            mNameValueTv.setText(data.getReceiverName());
            mAddressValueTv.setText(data.getReceiverAddress());
            mPhoneValueTv.setText(data.getReceiverPhone());
            mTimeValueTv.setText(data.getMakeTime());
            if (getRoleId() == Globals.ROLE_WORKER_ID) {
                if (mServiceValueTv != null)
                    mServiceValueTv.setText(data.getServiceName());
            } else if (getRoleId() == Globals.ROLE_CARRY_ID) {
                StringBuilder builder = new StringBuilder();
                List<JsonOrderGoods> orderGoods = data.getOrderGoods();
                if (orderGoods != null) {
                    for (int i = 0; i < orderGoods.size(); i++) {
                        JsonOrderGoods jsonOrderGoods = orderGoods.get(i);
                        String goodsName = jsonOrderGoods.getGoodsName();
                        if (i == orderGoods.size() - 1) {
                            builder.append(goodsName);
                        } else {
                            builder.append(goodsName + ",");
                        }
                    }
                }
                Log.d(TAG, "showOrderDetails: builder = " + builder);
                if (mServiceValueTv != null)
                    mServiceValueTv.setText(builder.toString());

            }
            if (!data.getRemark().isEmpty() && !data.getRemark().equals("")) {
                mUserRemarkTv.setText(data.getRemark());
                mUserRemarkTv.setGravity(Gravity.START);
            }
        }
//        if (getRoleId() == Globals.ROLE_WORKER_ID){
//
//        }else if (getRoleId() == Globals.ROLE_CARRY_ID){
//
//        }
    }

    private void addView(List<JsonOrderGoods> orderGoodsList) {
        Log.d(TAG, "addView: orderGoods = " + orderGoodsList);
        boolean isFirstMaterialVisible = true;
        mMaterialContentContainer.removeAllViews();
        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        float viewWidth = widthPixels - Utils.dp2px(16) * 2;
        int serviceIndex = 0;
        int materialIndex = 0;
        double total = 0;
        if (orderGoodsList != null && orderGoodsList.size() > 0) {
//            mAlreadyChooseTv.setVisibility(View.VISIBLE);
            for (int i = 0; i < orderGoodsList.size(); i++) {
                JsonOrderGoods info = orderGoodsList.get(i);
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
                if (getRoleId() == Globals.ROLE_CARRY_ID){
                    labelItem1.setText((materialIndex+1) + "");
                    labelItem2.setText(info.getGoodsName());
                    labelItem3.setText(info.getPrice() + "元/个");
                    labelItem4.setText(String.valueOf(info.getNumber()));
                }else {
                    labelItem4.setVisibility(View.GONE);
                    labelItem1.setText(info.getGoodsName());
                    labelItem2.setText(info.getPrice() + "元/个");
                    labelItem3.setText(String.valueOf(info.getNumber()));
                }
                total += info.getPrice() * info.getNumber();
                mMaterialContentContainer.addView(view, materialIndex);
                materialIndex++;
            }
            if (getRoleId() == Globals.ROLE_EXPRESS_ID){
                if (mTotalTv != null) {
                    mTotalTv.setText(mContext.getResources().getString(R.string.total) + total);
                }
            }
        }

    }

    private void addView(JsonOrderDetailsData data) {
        List<ProjectItemInfo> pjList = data.getPjList();
        List<ProjectItemInfo> jsfwList = data.getJsfwList();
        boolean isFirstServiceVisible = true;
        boolean isFirstMaterialVisible = true;
        if (mServiceContentContainer != null)
            mServiceContentContainer.removeAllViews();
        mMaterialContentContainer.removeAllViews();
        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        float viewWidth = widthPixels - Utils.dp2px(16) * 2;
        int serviceIndex = 0;
        int materialIndex = 0;

        if (jsfwList != null && jsfwList.size() > 0) {
            mAlreadyChooseTv.setVisibility(View.VISIBLE);
            for (int i = 0; i < jsfwList.size(); i++) {
                ProjectItemInfo info = jsfwList.get(i);
                if (isFirstServiceVisible) {
                    mServiceContentContainer.setVisibility(View.VISIBLE);
                    mServiceChooseTv.setVisibility(View.VISIBLE);
                    mServiceLabelContainer.setVisibility(View.VISIBLE);
                    isFirstServiceVisible = false;
                }
                LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_window_commit_label, null);
                TextView labelItem1 = view.findViewById(R.id.label_item_1);
                TextView labelItem2 = view.findViewById(R.id.label_item_2);
                TextView labelItem3 = view.findViewById(R.id.label_item_3);
                TextView labelItem4 = view.findViewById(R.id.label_item_4);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) labelItem1.getLayoutParams();
                params.width = (int) (viewWidth / 2);
                params.height = (int) Utils.dp2px(36);
                params.gravity = Gravity.CENTER_HORIZONTAL;
                labelItem1.setLayoutParams(params);
                params = (LinearLayout.LayoutParams) labelItem2.getLayoutParams();
                params.width = (int) (viewWidth / 2);
                params.height = (int) Utils.dp2px(36);
                params.gravity = Gravity.CENTER_HORIZONTAL;
                labelItem2.setLayoutParams(params);
                labelItem1.setText(info.getName());
                labelItem2.setText(String.valueOf(info.getPrice()) + "元");
                labelItem3.setVisibility(View.GONE);
                labelItem4.setVisibility(View.GONE);
                mServiceContentContainer.addView(view, serviceIndex);
                serviceIndex++;
            }

        }
        if (pjList != null && pjList.size() > 0) {
            mAlreadyChooseTv.setVisibility(View.VISIBLE);
            for (int i = 0; i < pjList.size(); i++) {
                ProjectItemInfo info = pjList.get(i);
                if (isFirstMaterialVisible) {
                    mMaterialContentContainer.setVisibility(View.VISIBLE);
                    mMaterialLabelContainer.setVisibility(View.VISIBLE);
                    mMaterialChooseTv.setVisibility(View.VISIBLE);
                    isFirstMaterialVisible = false;
                }
                LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_window_commit_label, null);
                TextView labelItem1 = view.findViewById(R.id.label_item_1);
                TextView labelItem2 = view.findViewById(R.id.label_item_2);
                TextView labelItem3 = view.findViewById(R.id.label_item_3);
                TextView labelItem4 = view.findViewById(R.id.label_item_4);
                labelItem1.setText(String.valueOf(i + 1));
                labelItem2.setText(info.getName());
                labelItem3.setText(String.valueOf(info.getPrice()));
                labelItem4.setText(String.valueOf(info.getNumber()));
                mMaterialContentContainer.addView(view, materialIndex);
                materialIndex++;

            }
        }

    }

    @Override
    public void showDeliverDetails(JsonBase<JsonDeliverDatails> jsonBase) {
        JsonDeliverDatails data = (JsonDeliverDatails) jsonBase.getData();
        Log.d(TAG, "showOrderDetails: data = " + data);
        if (data != null) {
            switch (getRoleId()) {
                case Globals.ROLE_SHANG_HU_ID:
                    if (mIsLeftWindow) {
                        mLeftPopWindow = initPopWindow(R.layout.layout_deliver_details);
                        mAcceptContainer.setVisibility(View.GONE);
                    } else {
                        mRightPopWindow = initPopWindow(R.layout.layout_deliver_details);
                        mAcceptContainer.setVisibility(View.VISIBLE);
                    }
                    mStoreAddressTv.setText(data.getStoreAddress());
                    mExpressNameTv.setText(data.getExpressName());
                    mExpressPhoneTv.setText(data.getExpressPhone());
//            mServiceValueTv.setText(data.getServiceName());
                    if (!data.getRemark().isEmpty() && !data.getRemark().equals("")) {
                        mUserRemarkTv.setText(data.getRemark());
                        mUserRemarkTv.setGravity(Gravity.START);
                    }
                    break;
                case Globals.ROLE_EXPRESS_ID:
                    if (mIsLeftWindow) {
                        mLeftPopWindow = initPopWindow(R.layout.layout_apply_settle_details);
                    } else {
                        mRightPopWindow = initPopWindow(R.layout.layout_apply_settle_details);
                    }
                    mStoreAddressTv.setText(data.getStoreAddress());
                    mStoreNameTv.setText(data.getStoreName());
                    mStorePhoneTv.setText(data.getStorePhone());
                    //TODO

                    break;
            }
            //TODO
            mNameValueTv.setText(data.getReceiverName());
            mAddressValueTv.setText(data.getReceiverAddress());
            mPhoneValueTv.setText(data.getReceiverPhone());
            mTimeValueTv.setText(data.getMakeTime());

            addView(data.getOrderGoods());

        }
    }

    @Override
    public void showEmpty() {
        Log.d(TAG, "showEmpty: ");
        loadData();
        dismissWindow();
    }

    @Override
    public void onCancelOrder(JsonBase jsonBase) {

    }

    @Override
    public void onConfirmReceipt(JsonBase jsonBase) {
//        if (getRoleId() == Globals.ROLE_WORKER_ID){
        loadData();
        dismissWindow();
        setOnItemSwitchListener();
//        }
    }
}
