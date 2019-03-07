package com.mudoulife.ui.fragment.aftersale;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.base.BaseFragment;
import com.mudoulife.base.MainView;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.AfterSaleInfo;
import com.mudoulife.data.net.response.ComplaintReason;
import com.mudoulife.data.net.response.JsonAfterDetails;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonBudgetDetails;
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.JsonOrderGoods;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.data.net.response.UserInfo;
import com.mudoulife.di.component.MainComponent;
import com.mudoulife.presenter.OrderBasePresenter;
import com.mudoulife.ui.adapter.AfterSaleHandlerAdapter;
import com.mudoulife.util.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AfterSaleHandleFragment extends BaseFragment implements MainView<JsonBase<List<ResultInfo>>> {
    private static final String TAG = "AfterSaleHandleFragment";
    TextView mNameValueTv;
    TextView mAddressValueTv;
    TextView mPhoneValueTv;
    TextView mTimeValueTv;
    TextView mUserRemarkTv;
    RelativeLayout mMaterialContainer;
    TextView mMaterialNameTv;
    TextView mMaterialValueTv;
    TextView mNumberNameTv;
    TextView mNumberValueTv;
    TextView mPriceNameTv;
    TextView mPriceValueTv;
    private TextView mServiceValueTv;
    private TextView mSpecificReasonTv;
    private TextView mStoreAddressTv;
    private TextView mExpressNameTv;
    private TextView mExpressPhoneTv;

    private LinearLayout mMaterialLabelContainer;
    private TextView mProjectLabel1Tv;
    private TextView mProjectLabel2Tv;
    private TextView mProjectLabel3Tv;
    private TextView mProjectLabel4Tv;
    private LinearLayout mMaterialContentContainer;
    RelativeLayout mProjectContainer;
    private AfterSaleHandlerAdapter mHandlerAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.shade_view)
    View mShadeView;

    protected PopupWindow mLeftPopWindow;
    protected PopupWindow mRightPopWindow;
    protected boolean mIsLeftWindow;

    private LinearLayoutManager mLayoutManager;
    protected Context mContext;
    protected Activity mActivity;
    protected List<AfterSaleInfo> mAdapterDatas;
//    protected List<ResultInfo> mDatas;

    protected ImageView mCloseIv;
    protected Button mSureBtn;
    protected int mCurrentPosition;

    @Inject
    protected OrderBasePresenter mPresenter;
    private Unbinder mUnbinder;
    private View mContainer;

    public AfterSaleHandleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent(MainComponent.class).inject(this);
        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContainer = inflater.inflate(R.layout.fragment_order_base_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mContainer);
        init();
        setRecyclerView();
//        loadData();
        return mContainer;
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


    protected void init() {
//        mDatas = new ArrayList<>();
        mAdapterDatas = new ArrayList<>();
    }

    private void setRecyclerView() {
        mHandlerAdapter = new AfterSaleHandlerAdapter(mContext, null, mMainFragmentName);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration decor = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decor);
//        mAdapter = new OrderBaseAdapter(mContext, mDatas,mMainFragmentName);
        mHandlerAdapter.setOnItemClickListener(new AfterSaleHandlerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Log.d(TAG, "onItemClick: view");
                Object tag = view.getTag();
                Log.d(TAG, "onItemClick: tag111111 = " + tag);
                if (tag instanceof Integer) {
                    int position = (int) tag;
                    Log.d(TAG, "onItemClick: tag22222222222 = " + tag);
                    mCurrentPosition = position;
                    switch (view.getId()) {
                        case R.id.button_left:
                            Log.d(TAG, "onClick: button_left");
                            onClickLeftBtn(position);
                            break;
                        case R.id.button_right:
                            Log.d(TAG, "onClick: button_right");
                            onClickRightBtn(position);
                            break;
                        case R.id.content_recycler_container:
                            Log.d(TAG, "onClick: content_recycler_container");
                            break;
                        case R.id.recycler_view:
                            Log.d(TAG, "onClick: recycler_view");
                            break;
                    }
                }

            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
        mRecyclerView.setAdapter(mHandlerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    protected void loadData() {
        mPresenter.afterSaleList(getRoleName());
//        mPresenter.getOrderList(getRoleName());
    }


    protected void onClickRightBtn(int position) {
        mIsLeftWindow = false;
//        mRightPopWindow = initPopWindow(R.layout.layout_receive_sure);
        if (getRoleId() == Globals.ROLE_WORKER_ID) {
            mPresenter.afterSaleDetail(getRoleName(), mAdapterDatas.get(mCurrentPosition).getId());
        }else {
            mPresenter.afterSaleDetail(getRoleName(),mAdapterDatas.get(mCurrentPosition).getOrderId(), mAdapterDatas.get(mCurrentPosition).getId());
        }
        Log.d(TAG, "onClickRightBtn: id = " +  mAdapterDatas.get(mCurrentPosition).getId());

    }

    protected void onClickLeftBtn(int position) {
        mIsLeftWindow = true;
        Log.d(TAG, "onClickLeftBtn: id = " +  mAdapterDatas.get(mCurrentPosition).getId());
        Log.d(TAG, "onClickLeftBtn: orderID = " +  mAdapterDatas.get(mCurrentPosition).getOrderId());
        if (getRoleId() == Globals.ROLE_WORKER_ID) {
            mPresenter.afterSaleDetail(getRoleName(), mAdapterDatas.get(mCurrentPosition).getId());
        }else {
            mPresenter.afterSaleDetail(getRoleName(),mAdapterDatas.get(mCurrentPosition).getOrderId(), mAdapterDatas.get(mCurrentPosition).getId());
        }
    }

    protected void onSureBtnClick() {
        if (mIsLeftWindow) {

            if (getRoleId() == Globals.ROLE_WORKER_ID) {
                mPresenter.processing(getRoleName(), mAdapterDatas.get(mCurrentPosition).getId());
            } else {
                mPresenter.processing(getRoleName(),mAdapterDatas.get(mCurrentPosition).getOrderId(), mAdapterDatas.get(mCurrentPosition).getId());
            }
        } else {

            if (getRoleId() == Globals.ROLE_WORKER_ID) {
                mPresenter.processingEnd(getRoleName(), mAdapterDatas.get(mCurrentPosition).getId());
            } else {
                mPresenter.processingEnd(getRoleName(),mAdapterDatas.get(mCurrentPosition).getOrderId(), mAdapterDatas.get(mCurrentPosition).getId());
            }
        }
    }

    protected void dismissWindow() {
        if (mIsLeftWindow) {
            Log.d(TAG, "dismissWindow: 11111111111111");
            if (mLeftPopWindow != null && mLeftPopWindow.isShowing()) {
                Log.d(TAG, "dismissWindow: 2222222222222222");
                mLeftPopWindow.dismiss();
            }
        } else {
            Log.d(TAG, "dismissWindow: 333333333333333333333");
            if (mRightPopWindow != null && mRightPopWindow.isShowing()) {
                mRightPopWindow.dismiss();
                Log.d(TAG, "dismissWindow: 44444444444444444");
            }

        }
    }

    protected void initPopView(View view) {

        mCloseIv = view.findViewById(R.id.close_img);
        mNameValueTv = view.findViewById(R.id.content_1);
        mAddressValueTv = view.findViewById(R.id.address_value);
        mPhoneValueTv = view.findViewById(R.id.phone_value);
        mTimeValueTv = view.findViewById(R.id.appoint_time_value);
        mUserRemarkTv = view.findViewById(R.id.user_remark);
        mSpecificReasonTv = view.findViewById(R.id.specific_desc);
        mSureBtn = view.findViewById(R.id.sure_btn);
        if (getRoleId() == Globals.ROLE_WORKER_ID) {
            initWorkerView(view);
        } else if (getRoleId() == Globals.ROLE_SHANG_HU_ID) {
            initStoreView(view);
        }
        if (mIsLeftWindow)
            mSureBtn.setText(getResources().getString(R.string.quickly_handle));
        else mSureBtn.setText(getResources().getString(R.string.finish_handle));
    }

    private void initWorkerView(View view) {
        mServiceValueTv = view.findViewById(R.id.service_value);

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

        mProjectLabel1Tv.setText(getResources().getString(R.string.material_name));
        mProjectLabel2Tv.setText(getResources().getString(R.string.price));
        mProjectLabel3Tv.setText(getResources().getString(R.string.number));
        mProjectLabel4Tv.setVisibility(View.GONE);
    }

    @Override
    public void showContent(JsonBase<List<ResultInfo>> jsonBase) {
        Log.d(TAG, "data = " + jsonBase);
        List<ResultInfo> dataList = (List<ResultInfo>) jsonBase.getData();
        if (dataList != null) {
            mAdapterDatas.clear();
            for (int i = 0; i < dataList.size(); i++) {
                ResultInfo resultInfo = dataList.get(i);
                AfterSaleInfo info = new AfterSaleInfo();
                info.setCreateTime(resultInfo.getCreateTime());
                info.setOrderNumber(resultInfo.getOrderNumber());
                info.setOrderId(resultInfo.getId());
                if (getRoleId() == Globals.ROLE_WORKER_ID) {
                    info.setId(resultInfo.getId());
                    info.setPicture(resultInfo.getImgUrl());
                    info.setSpec(resultInfo.getSpec());
                    info.setStatus(resultInfo.getStatus());
                    info.setStatusName(resultInfo.getStatusName());
                    info.setName(resultInfo.getServiceName());
                    info.setShowClwc(resultInfo.isShowClwc());
                    info.setShowLjcl(resultInfo.isShowLjcl());
                    mAdapterDatas.add(info);
                } else if (getRoleId() == Globals.ROLE_SHANG_HU_ID) {
                    List<JsonOrderGoods> orderGoods = resultInfo.getOrderGoods();
                    for (int j = 0; j < orderGoods.size(); j++) {
                        JsonOrderGoods jsonOrderGoods = orderGoods.get(j);
                        info.setId(jsonOrderGoods.getId());
                        info.setSpec(jsonOrderGoods.getGoodsSpec());
                        info.setName(jsonOrderGoods.getGoodsName());
                        info.setPicture(jsonOrderGoods.getPicture().split(",")[0]);
                        info.setCanExchangeGoods(jsonOrderGoods.isCanExchangeGoods());
                        info.setShowLjcl(jsonOrderGoods.isShowLjcl());
                        info.setShowClwc(jsonOrderGoods.isShowClwc());
                        mAdapterDatas.add(info);
                    }
                }
            }
            Log.d(TAG, "showContent: mAdapterDatas = " + mAdapterDatas);
            mHandlerAdapter.setDatas(mAdapterDatas);
        }

    }

    @Override
    public void showEmpty() {

    }

    private PopupWindow initPopWindow(int layoutId) {
        mShadeView.setVisibility(View.VISIBLE);
        return initPopWindow(mContainer, layoutId);
    }

    protected void setPopListeners() {
        mCloseIv.setOnClickListener(mOnClickListener);
        mSureBtn.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.close_img:
                    dismissWindow();

                    break;
                case R.id.sure_btn:
//                    LibraryBaseActivity.launch(mContext);
                    Log.d(TAG, "mCurrentPosition = " + mCurrentPosition);
                    onSureBtnClick();
                    break;
            }
        }
    };

    private PopupWindow initPopWindow(View anchor, int layoutId) {
        Log.d(TAG, "initPopWindow anchor = " + anchor);
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(layoutId, null);
//        RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.layout_pop_window_tablet, null);

        initPopView(view);
        setPopListeners();

        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels;

        //获取view的坐标
        int[] location = new int[2];
//            anchor.getLocationInWindow(location);
        anchor.getLocationOnScreen(location);

        int height = location[1] + anchor.getHeight();

        PopupWindow popupWindow = new PopupWindow(view, widthPixels - (int) Utils.dp2px(49),
                heightPixels - (int) Utils.dp2px(300), true);


        Log.d(TAG, "initPopWindow = " + popupWindow.isShowing());


        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {


            ColorDrawable cd = new ColorDrawable(Color.BLACK);
            popupWindow.setBackgroundDrawable(cd);
//            mLeftPopWindow.setBackgroundDrawable(Color.BLACK);
            final Window w = mActivity.getWindow();

            WindowManager.LayoutParams params = w.getAttributes();
            //这里设置透明度与，为什么会显示桌面
            params.alpha = 1f;

//            w.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            w.setAttributes(params);


            popupWindow.setFocusable(true);
//
            popupWindow.setOutsideTouchable(false);

            popupWindow.update();
//
//            view.removeView(parent);
//            mLeftPopWindow.setAnimationStyle(R.style.WindowAnimStyle);
//            int offsetY = (int)Utils.dp2px(getResources().getDimension(R.dimen.dimen_72));
            int bottomHeight = Utils.getBottomBarHeight(mContext);
            int offsetY = (int) Utils.dp2px(73);
            Log.d(TAG, "initPopWindow: offsetY = " + offsetY);
            Log.d(TAG, "initPopWindow: bottomHeight = " + bottomHeight);
            popupWindow.showAtLocation(anchor, Gravity.BOTTOM | Gravity.END, 0, offsetY + bottomHeight);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//                mLeftPopWindow.showAsDropDown(anchor, 0, 0, Gravity.RIGHT);
//            else
//            mLeftPopWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, (int) (widthPixels * 0.15), height);


            //相对某个控件的正左下方，无偏移
//            mLeftPopWindow.showAsDropDown(anchor);
            //  相对某个控件的位置，有偏移，xoff表示x轴的偏移量，正直表示向左，负值表示向右；yoff表示y轴的偏移量，正值向下，负值向上。
//            mLeftPopWindow.showAsDropDown(anchor,0,0);
            //第四个参数表示坐标原点在屏幕右上角位置。
//            mLeftPopWindow.showAsDropDown(anchor, 0, 0, Gravity.RIGHT | Gravity.TOP);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
//                    if (timer != null)
//                        timer.cancel();

                    WindowManager.LayoutParams attributes = w.getAttributes();

                    attributes.alpha = 1f;

                    w.setAttributes(attributes);
                    mShadeView.setVisibility(View.GONE);

//                    popupWindow = null;

                }
            });

        }
        return popupWindow;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
        mUnbinder.unbind();
    }

    @Override
    public void showOrderDetails(JsonBase<JsonOrderDetailsData> jsonBase) {
        JsonOrderDetailsData data = (JsonOrderDetailsData) jsonBase.getData();
        Log.d(TAG, "showOrderDetails: data = " + data);
        if (data != null) {
            if (getRoleId() == Globals.ROLE_WORKER_ID) {
                if (mIsLeftWindow) {
                    mLeftPopWindow = initPopWindow(R.layout.layout_after_sale_worker);
                } else {
                    mRightPopWindow = initPopWindow(R.layout.layout_after_sale_worker);
                }

            }
            mNameValueTv.setText(data.getReceiverName());
            mAddressValueTv.setText(data.getReceiverAddress());
            mPhoneValueTv.setText(data.getReceiverPhone());
            mTimeValueTv.setText(data.getMakeTime());
            mSpecificReasonTv.setText(data.getReason());
            if (mServiceValueTv != null) {
                mServiceValueTv.setText(data.getServiceName());
            }

            if (!data.getRemark().isEmpty() && !data.getRemark().equals("")) {
                mUserRemarkTv.setText(data.getRemark());
                mUserRemarkTv.setGravity(Gravity.START);
            }

        }
    }

    @Override
    public void showDeliverDetails(JsonBase<JsonDeliverDatails> jsonBase) {

    }

    private void addView(JsonAfterDetails data) {
        boolean isFirstServiceVisible = true;
        boolean isFirstMaterialVisible = true;
        List<JsonOrderGoods> orderGoods = data.getOrderGoods();
        mMaterialContentContainer.removeAllViews();
        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        float viewWidth = widthPixels - Utils.dp2px(16) * 2;
        Log.d(TAG, "onActivityResult: viewWidth = " + viewWidth);
//            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            view.setLayoutParams(layoutParams);
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
//        if (getRoleId() == Globals.ROLE_SHANG_HU_ID) {
//            AfterSaleInfo info = mAdapterDatas.get(mCurrentPosition);
//            info.setShowClwc(true);
//            mHandlerAdapter.notifyItemChanged(mCurrentPosition);
//        }else {
//
//        }
        Log.d(TAG, "onCancelOrder: jsonBase = " + jsonBase);
        loadData();
        dismissWindow();
    }

    @Override
    public void onConfirmReceipt(JsonBase jsonBase) {
        Log.d(TAG, "onConfirmReceipt: jsonBase = " + jsonBase);
        loadData();
        dismissWindow();
    }

    @Override
    public void showInCome(JsonBase<Double> jsonBase) {

    }

    @Override
    public void showComplaint(JsonBase<List<ComplaintReason>> jsonBase) {

    }

    @Override
    public void showUserInfo(JsonBase<UserInfo> jsonBase) {

    }

    @Override
    public void showBudgetDetails(JsonBase<JsonBudgetDetails> jsonBase) {

    }

    @Override
    public void showAfterDetails(JsonBase<JsonAfterDetails> jsonBase) {
        JsonAfterDetails data = (JsonAfterDetails) jsonBase.getData();
        if (data != null) {
             if (getRoleId() == Globals.ROLE_SHANG_HU_ID) {
                if (mIsLeftWindow) {
                    mLeftPopWindow = initPopWindow(R.layout.layout_after_sale_store);
                } else {
                    mRightPopWindow = initPopWindow(R.layout.layout_after_sale_store);
                }
                //TODO
                addView(data);
                mStoreAddressTv.setText(data.getStoreAddress());
                mExpressNameTv.setText(data.getExpressName());
                mExpressPhoneTv.setText(data.getExpressPhone());
                mSpecificReasonTv.setText(data.getReason());
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

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showTipMsg(String msg) {

    }
}
