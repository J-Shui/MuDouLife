package com.mudoulife.ui.fragment.order;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.request.CarryCommitInfo;
import com.mudoulife.data.net.request.ProjectItemInfo;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.JsonOrderGoods;
import com.mudoulife.data.net.response.LibraryBaseData;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.di.component.MainComponent;
import com.mudoulife.ui.adapter.order.OrderOuterAdapter;
import com.mudoulife.ui.adapter.order.OrderPriceAdapter;
import com.mudoulife.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mudoulife.ui.activity.LibraryBaseActivity.TOP_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderPriceFragment extends OrderBaseFragment {
    private static final String TAG = "OrderPriceFragment";
    private ImageView mAddIv;
    private TextView mAlreadyChooseTv;
    private TextView mServiceChooseTv;
    private TextView mMaterialChooseTv;
    //    private RelativeLayout mProjectContainer;
    private LinearLayout mServiceLabelContainer;
    private LinearLayout mMaterialLabelContainer;

    private TextView mProjectLabel1Tv;
    private TextView mProjectLabel2Tv;
    private TextView mProjectLabel3Tv;
    private TextView mProjectLabel4Tv;
    private TextView mProjectItem1ValueTv;
    private TextView mProjectItem2ValueTv;
    private TextView mProjectItem3ValueTv;
    private TextView mProjectItem4ValueTv;
    private TextView mServiceLabel1Tv;
    private TextView mServiceLabel2Tv;
    private TextView mServiceLabel3Tv;
    private TextView mServiceLabel4Tv;
    private TextView mServiceItem1ValueTv;
    private TextView mServiceItem2ValueTv;
    private TextView mServiceItem3ValueTv;
    private TextView mServiceItem4ValueTv;
    private TextView mTotalTv;
    private boolean mHasProject;

    TextView mNameValueTv;
    TextView mAddressValueTv;
    TextView mPhoneValueTv;
    TextView mTimeValueTv;
    TextView mUserRemarkTv;
    RelativeLayout mProjectContainer;
    private RelativeLayout mServiceContainer;

    LinearLayout mServiceContentContainer;
    private LinearLayout mMaterialContentContainer;
    TextView mServiceNameTv;
    TextView mServiceValueTv;
    TextView mNumberValueTv;
    TextView mPriceValueTv;
    private List<LibraryBaseData> mCommitDatas;
    private int mTypeId;
    private int mOutKm;
    public static final int REQUEST_CODE = 111;
    private List<CarryCommitInfo> mCarryCommitInfos;
    //    private RecyclerView mServiceRecycler;
//    private RecyclerView mMaterialRecycler;
//    private LibraryWorkerAdapter mServiceAdapter;
//    private CommitPriceAdapter mMaterialAdapter;
//    private LinearLayoutManager mServiceManager;
//    private LinearLayoutManager mMaterialManager;
    private View mView;


    public OrderPriceFragment() {
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
        mCarryCommitInfos = new ArrayList<>();
    }

    @Override
    protected void loadData() {
        if (getRoleId() == Globals.ROLE_EXPRESS_ID) {
            mPresenter.getGoodsAndPriceList();
        } else {
            mPresenter.getPriceAndServiceList(getRoleName());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d(TAG, "onResume: mServiceRecycler = " + mServiceRecycler);
//        if (mServiceAdapter != null)
//            mServiceAdapter.setDatas(mCommitDatas);
    }

    @Override
    protected void initAdapter() {
        mAdapter = new OrderPriceAdapter(mContext, mDatas, mMainFragmentName);
    }

    @Override
    protected void onClickRightBtn(int position) {
        if (getRoleId() == Globals.ROLE_EXPRESS_ID) {
//            mPresenter.uploadPrice(getRoleName(),mDatas.get(position).getId());
            mRightPopWindow = initPopWindow(R.layout.layout_window_commit_price);
        } else if (getRoleId() == Globals.ROLE_CARRY_ID) {
            mPresenter.getOrderDetail(getRoleName(), mDatas.get(position).getId());
//            mRightPopWindow = initPopWindow(R.layout.layout_commit_price);
        } else {
            mPresenter.startService(mDatas.get(position).getId());
        }
    }

    @Override
    protected void onClickLeftBtn(int position) {
        if (getRoleId() == Globals.ROLE_EXPRESS_ID) {
            mPresenter.getGoods(mDatas.get(position).getId());
        } else {
            mIsLeftWindow = true;
            mLeftPopWindow = initPopWindow(R.layout.layout_window_commit_price);
        }


    }

    @Override
    protected void onSureBtnClick() {
//        super.onSureBtnClick();


        ResultInfo data = mDatas.get(mCurrentPosition);
        if (getRoleId() == Globals.ROLE_WORKER_ID) {
            uploadService(data);
        } else if (getRoleId() == Globals.ROLE_EXPRESS_ID) {
            mPresenter.uploadExpressPrice(data.getId(), mTypeId, mOutKm);
        } else if (getRoleId() == Globals.ROLE_CARRY_ID) {
            if (mIsLeftWindow){
                Log.d(TAG, "onSureBtnClick: " + data.getId());
                Log.d(TAG, "onSureBtnClick: " + mTypeId);
                Log.d(TAG, "onSureBtnClick: " + new Gson().toJson(mCarryCommitInfos));
                mPresenter.uploadCarryPrice(data.getId(), mTypeId,new Gson().toJson(mCarryCommitInfos));
            }

            else mPresenter.beginService(data.getId());
        }
//        data.setReceived(true);
//        data.setLocalStatus(LocalStatus.FINISH);

    }
    private void uploadService(ResultInfo data) {
        List<ProjectItemInfo> mServiceInfos = new ArrayList<>();
        List<ProjectItemInfo> mPartInfos = new ArrayList<>();
        Map<String, List<ProjectItemInfo>> map = new HashMap<>();
        for (int i = 0; i < mCommitDatas.size(); i++) {
            ProjectItemInfo info = new ProjectItemInfo();
            LibraryBaseData baseData = mCommitDatas.get(i);
            info.setId(baseData.getId());
            info.setNumber(baseData.getNumber());
            if (baseData.getType() == TOP_SERVICE) {
                mServiceInfos.add(info);
            } else {
                mPartInfos.add(info);
            }
        }
        map.put("service", mServiceInfos);
        map.put("parts", mPartInfos);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        Log.d(TAG, "onSureBtnClick: json = " + json);
        mPresenter.uploadPrice(getRoleName(), data.getId(), json);
    }

    @Override
    protected int getNextItem() {
        return Globals.LEFT_ACCOUNT_ITEM;
    }

    @Override
    protected void loadPopData() {
        mCommitDatas = new ArrayList<>();
    }

    private void initDefaultView(View view) {
        mCloseIv = view.findViewById(R.id.close_img);
        mSureBtn = view.findViewById(R.id.sure_btn);
        mAlreadyChooseTv = view.findViewById(R.id.already_choose_text);
        mServiceChooseTv = view.findViewById(R.id.service_text);
        mMaterialChooseTv = view.findViewById(R.id.material_text);

        mMaterialLabelContainer = view.findViewById(R.id.material_label_container);
        mProjectLabel1Tv = mMaterialLabelContainer.findViewById(R.id.label_item_1);
        mProjectLabel2Tv = mMaterialLabelContainer.findViewById(R.id.label_item_2);
        mProjectLabel3Tv = mMaterialLabelContainer.findViewById(R.id.label_item_3);
        mProjectLabel4Tv = mMaterialLabelContainer.findViewById(R.id.label_item_4);

        mMaterialContentContainer = view.findViewById(R.id.material_content_container);
        setEnabled(false);
        mAddIv = view.findViewById(R.id.add_project_img);
//        mProjectContainer.setVisibility(View.GONE);
//        mServiceContainer.setVisibility(View.GONE);
        mAlreadyChooseTv.setVisibility(View.GONE);

        mMaterialContentContainer.setVisibility(View.GONE);
        mMaterialLabelContainer.setVisibility(View.GONE);
        mMaterialChooseTv.setVisibility(View.GONE);
    }

    private void initWorkView(View view) {
//        mProjectContainer = view.findViewById(R.id.project_container);
        mServiceLabelContainer = view.findViewById(R.id.service_label_container);
        mServiceLabel1Tv = mServiceLabelContainer.findViewById(R.id.label_item_1);
        mServiceLabel2Tv = mServiceLabelContainer.findViewById(R.id.label_item_2);
        mServiceLabel3Tv = mServiceLabelContainer.findViewById(R.id.label_item_3);
        mServiceLabel4Tv = mServiceLabelContainer.findViewById(R.id.label_item_4);
        mServiceLabel3Tv.setVisibility(View.GONE);
        mServiceLabel4Tv.setVisibility(View.GONE);
        initDefaultView(view);
        mServiceContentContainer = view.findViewById(R.id.service_content_container);
        mServiceChooseTv.setVisibility(View.GONE);
        mServiceLabelContainer.setVisibility(View.GONE);
        mServiceContentContainer.setVisibility(View.GONE);
    }


    private void initExpressView(View view) {
        initDefaultView(view);
        mSureBtn.setText(getResources().getString(R.string.commit));
        TextView step1Tv = view.findViewById(R.id.step_1_id);
        TextView step2Tv = view.findViewById(R.id.step_2_id);
        step1Tv.setText(getResources().getString(R.string.commit_1_express));
        step2Tv.setText(getResources().getString(R.string.commit_2_express));
        mProjectLabel1Tv.setText(getResources().getString(R.string.car_model));
        mProjectLabel2Tv.setText(getResources().getString(R.string.starting_price));
        mProjectLabel3Tv.setText(getResources().getString(R.string.out_of_range));
        mProjectLabel4Tv.setText(getResources().getString(R.string.out_of_5));
    }

    private void initCarryLeftView(View view) {
        initDefaultView(view);
        mProjectLabel1Tv.setText(getResources().getString(R.string.material_name));
        mProjectLabel2Tv.setText(getResources().getString(R.string.starting_price));
        mProjectLabel3Tv.setText(getResources().getString(R.string.number));
        mProjectLabel4Tv.setText(getResources().getString(R.string.floor));
    }

    private void initCarryRightView(View view) {
        mCloseIv = view.findViewById(R.id.close_img);
        mNameValueTv = view.findViewById(R.id.content_1);
        mAddressValueTv = view.findViewById(R.id.address_value);
        mPhoneValueTv = view.findViewById(R.id.phone_value);
        mTimeValueTv = view.findViewById(R.id.appoint_time_value);
        mUserRemarkTv = view.findViewById(R.id.user_remark);
        mProjectContainer = view.findViewById(R.id.project_container);
        mServiceContainer = view.findViewById(R.id.service_container);
        mServiceNameTv = view.findViewById(R.id.server_name_id);
        mServiceValueTv = view.findViewById(R.id.server_value_id);
//        mNumberNameTv = view.findViewById(R.id.number_name_id);
        mNumberValueTv = view.findViewById(R.id.content_3);
//        mPriceNameTv = view.findViewById(R.id.price_name_id);
        mPriceValueTv = view.findViewById(R.id.content_2);
        mSureBtn = view.findViewById(R.id.sure_btn);
        mProjectContainer.setVisibility(View.VISIBLE);
        mServiceContainer.setVisibility(View.GONE);

        mMaterialLabelContainer = mProjectContainer.findViewById(R.id.material_label_container);
        mProjectLabel1Tv = mMaterialLabelContainer.findViewById(R.id.label_item_1);
        mProjectLabel2Tv = mMaterialLabelContainer.findViewById(R.id.label_item_2);
        mProjectLabel3Tv = mMaterialLabelContainer.findViewById(R.id.label_item_3);
        mProjectLabel4Tv = mMaterialLabelContainer.findViewById(R.id.label_item_4);
        mMaterialContentContainer = mProjectContainer.findViewById(R.id.material_content_container);
    }

    @Override
    protected void initPopView(View view) {
        Log.d(TAG, "initPopView: ");
        switch (getRoleId()) {
            case Globals.ROLE_WORKER_ID:
                initWorkView(view);
                break;
            case Globals.ROLE_EXPRESS_ID:
                initExpressView(view);
                break;
            case Globals.ROLE_CARRY_ID:
                if (mIsLeftWindow) {
                    initCarryLeftView(view);
                } else {
                    initCarryRightView(view);
                }
                break;
        }

        if (mIsLeftWindow) {

        } else {

        }

    }

    @Override
    protected void setPopListeners() {
        super.setPopListeners();
        if (mAddIv != null) {
            mAddIv.setOnClickListener(mOnClickListener);
        }

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(mContext, getActivityClass());
            intent.putExtra(Globals.ORDER_ID, mDatas.get(mCurrentPosition).getId());
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    private void setEnabled(boolean isEnabled) {
        mSureBtn.setEnabled(isEnabled);
        if (isEnabled) {
            mSureBtn.setBackgroundColor(getResources().getColor(R.color.color_fec300));
        } else {
            mSureBtn.setBackgroundColor(getResources().getColor(R.color.color_999999));

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        boolean isFirstServiceVisible = true;
        boolean isFirstMaterialVisible = true;

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            mProjectContainer.setVisibility(View.VISIBLE);
            if (mServiceContentContainer != null)
                mServiceContentContainer.removeAllViews();
            mMaterialContentContainer.removeAllViews();
            setEnabled(true);
            ArrayList<LibraryBaseData> datas = data.getParcelableArrayListExtra("selected_list");
            Log.d(TAG, "onActivityResult: datas = " + datas);
            if (mCommitDatas != null)
                mCommitDatas.clear();

            int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
            float viewWidth = widthPixels - Utils.dp2px(16) * 2;
            Log.d(TAG, "onActivityResult: viewWidth = " + viewWidth);
//            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            view.setLayoutParams(layoutParams);
            int serviceIndex = 0;
            int materialIndex = 0;
            if (datas != null && datas.size() > 0) {
                mAlreadyChooseTv.setVisibility(View.VISIBLE);
                if (mCarryCommitInfos != null)
                    mCarryCommitInfos.clear();
                for (int i = 0; i < datas.size(); i++) {
                    LibraryBaseData baseData = datas.get(i);
                    int type = baseData.getType();
                    mCommitDatas.add(baseData);
                    if (getRoleId() == Globals.ROLE_WORKER_ID && type == TOP_SERVICE) {
                        if (isFirstServiceVisible) {
                            mServiceContentContainer.setVisibility(View.VISIBLE);
                            mServiceChooseTv.setVisibility(View.VISIBLE);
                            mServiceLabelContainer.setVisibility(View.VISIBLE);
                            isFirstServiceVisible = false;
                        }

                        Log.d(TAG, "onActivityResult: type ===== " + type);
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
                        labelItem1.setText(baseData.getName());
                        labelItem2.setText(String.valueOf(baseData.getPrice()) + baseData.getPriceUnit());
                        labelItem3.setVisibility(View.GONE);
                        labelItem4.setVisibility(View.GONE);
                        mServiceContentContainer.addView(view, serviceIndex);
                        serviceIndex++;

                    } else {
                        if (isFirstMaterialVisible) {
                            mMaterialContentContainer.setVisibility(View.VISIBLE);
                            mMaterialLabelContainer.setVisibility(View.VISIBLE);
                            mMaterialChooseTv.setVisibility(View.VISIBLE);
                            isFirstMaterialVisible = false;
                        }
                        Log.d(TAG, "onActivityResult: type ---------- " + type);
                        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_window_commit_label, null);
                        TextView labelItem1 = view.findViewById(R.id.label_item_1);
                        TextView labelItem2 = view.findViewById(R.id.label_item_2);
                        TextView labelItem3 = view.findViewById(R.id.label_item_3);
                        TextView labelItem4 = view.findViewById(R.id.label_item_4);
                        switch (getRoleId()) {
                            case Globals.ROLE_EXPRESS_ID:
                                mTypeId = baseData.getClassId();
                                mOutKm = baseData.getNumber();
                                labelItem1.setText(baseData.getClassName());
                                labelItem2.setText(baseData.getStartPrice() + "/每公里");
                                labelItem3.setText(baseData.getExceedingPrice() + "/每公里");
                                labelItem4.setText(String.valueOf(baseData.getNumber()));
                                break;
                            case Globals.ROLE_WORKER_ID:
                                labelItem1.setText((materialIndex + 1) + "");
                                labelItem2.setText(baseData.getName());
                                labelItem3.setText(String.valueOf(baseData.getPrice()) + baseData.getPriceUnit());
                                labelItem4.setText(String.valueOf(baseData.getNumber()));
                                break;
                            case Globals.ROLE_CARRY_ID:

                                mTypeId = baseData.getClassId();
                                CarryCommitInfo info = new CarryCommitInfo();
                                info.setItemId(baseData.getId());
                                info.setNumber(baseData.getNumber());
                                mCarryCommitInfos.add(info);
                                labelItem1.setText(String.valueOf(baseData.getClassName()));
                                labelItem2.setText(String.valueOf(baseData.getPrice()) + baseData.getUnit());
                                labelItem3.setText(String.valueOf(baseData.getNumber()));
                                labelItem4.setText(String.valueOf(baseData.getFloor()));
                                break;
                        }

                        mMaterialContentContainer.addView(view, materialIndex);
                        materialIndex++;
                    }
                }
            }
            Log.d(TAG, "onActivityResult: commit = " + mCarryCommitInfos);

        }
    }

    @Override
    public void showContent(JsonBase<List<ResultInfo>> jsonBase) {
        super.showContent(jsonBase);
    }

    @Override
    public void showEmpty() {
        if (getRoleId() == Globals.ROLE_EXPRESS_ID) {
            loadData();
        } else if (getRoleId() == Globals.ROLE_CARRY_ID) {
            loadData();
            dismissWindow();
            setOnItemSwitchListener();
        } else {
            setOnItemSwitchListener();
        }
    }

    @Override
    public void showOrderDetails(JsonBase<JsonOrderDetailsData> jsonBase) {
        JsonOrderDetailsData data = (JsonOrderDetailsData) jsonBase.getData();
        Log.d(TAG, "showOrderDetails: data = " + data);
        if (data != null) {
            mRightPopWindow = initPopWindow(R.layout.layout_receive_sure);
            mNameValueTv.setText(data.getReceiverName());
            mAddressValueTv.setText(data.getReceiverAddress());
            mPhoneValueTv.setText(data.getReceiverPhone());
            mTimeValueTv.setText(data.getMakeTime());
            List<JsonOrderGoods> orderGoods = data.getOrderGoods();
            StringBuilder builder = new StringBuilder();
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
            mServiceValueTv.setText(builder.toString());
            addView(data);
            mUserRemarkTv.setText(data.getRemark());
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
                if (getRoleId() == Globals.ROLE_CARRY_ID){
                    labelItem1.setText((materialIndex + 1 )+"");
                    labelItem2.setText(info.getGoodsName());
                    labelItem3.setText(info.getPrice() + "元/个");
                    labelItem4.setText(String.valueOf(info.getNumber()));
                }else {
                    labelItem4.setVisibility(View.GONE);
                    labelItem1.setText(info.getGoodsName());
                    labelItem2.setText(info.getPrice() + "元/个");
                    labelItem3.setText(String.valueOf(info.getNumber()));
                }
                mMaterialContentContainer.addView(view, materialIndex);
                materialIndex++;
            }
        }

    }

    @Override
    public void onCancelOrder(JsonBase jsonBase) {

    }

    @Override
    public void onConfirmReceipt(JsonBase jsonBase) {
//        ResultInfo data = mDatas.get(mCurrentPosition);
//
//        mAdapter.notifyItemChanged(mCurrentPosition);
//                    if (!data.isFirstShowLeft())
        loadData();
        dismissWindow();
    }
}
