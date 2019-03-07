package com.mudoulife.ui.fragment.mine;


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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.base.BaseFragment;
import com.mudoulife.base.MainView;
import com.mudoulife.data.net.response.ComplaintReason;
import com.mudoulife.data.net.response.InOutGoods;
import com.mudoulife.data.net.response.JsonAfterDetails;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonBudgetDetails;
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.JsonOrderGoods;
import com.mudoulife.data.net.response.LoginData;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.data.net.response.UserInfo;
import com.mudoulife.di.component.MainComponent;
import com.mudoulife.presenter.OrderBasePresenter;
import com.mudoulife.ui.adapter.mine.InComeAdapter;
import com.mudoulife.ui.adapter.mine.InOutDetailsAdapter;
import com.mudoulife.util.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineInOutFragment extends BaseFragment implements MainView<JsonBase<List<ResultInfo>>> {
    private static final String TAG = "MineInOutFragment";
    @BindView(R.id.income_name)
    TextView mIncomeNameTv;
    @BindView(R.id.income_value)
    TextView mIncomeValueTv;
    @BindView(R.id.history_income)
    Button mHistoryIncomeIv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.shade_view)
    View mShadeView;
    private Unbinder mUnbinder;

    private InComeAdapter mAdapter;
    private Context mContext;
    private Activity mActivity;
    private List<ResultInfo> mDatas;
    @Inject
    OrderBasePresenter mPresenter;

    private PopupWindow mPopWindow;
    private ImageView mCloseIv;
//    private ImageView mImgIv;
    private RecyclerView mWindowRecyclerView;
    private TextView mPriceTv;
    private TextView mDealSuccessTv;
    private TextView mPaymentNameTv;
    private TextView mPaymentValueTv;
    private TextView mOrderNoNameTv;
    private TextView mOrderNoValueTv;
    private TextView mDealObjNameTv;
    private TextView mDealObjValueTv;
    private TextView mTransTimeNameTv;
    private TextView mTransTimeValueTv;
    private TextView mReceiveTimeNameTv;
    private TextView mReceiveTimeValueTv;
    private String mCurrentOrderNo;
    private List<String> mUrls;
    private InOutDetailsAdapter mWindowAdapter;

    public MineInOutFragment() {
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine_in_out, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        init();
        loadData();
        return view;
    }
    private void loadData(){
        mPresenter.monthIncome(getRoleName());
    }

    private void init() {
        mDatas = new ArrayList<>();
        mUrls = new ArrayList<>();
        mAdapter = new InComeAdapter(mContext, mDatas,mMainFragmentName);
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mHistoryIncomeIv.setOnClickListener(mOnClickListener);
    }

    private InComeAdapter.OnItemClickListener mOnItemClickListener = new InComeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view) {
            Object tag = view.getTag();
            if (tag instanceof Integer) {
                int position = (int) tag;
                mCurrentOrderNo = mDatas.get(position).getOrderNumber();
//                mUrls.clear();
//                List<JsonOrderGoods> orderGoods = mDatas.get(position).getOrderGoods();
//                for (int i = 0; i < orderGoods.size(); i++) {
//                    mUrls.add(orderGoods.get(i).getPicture());
//                }
                Log.d(TAG, "onItemClick: id = " + mDatas.get(position).getId());
                mPresenter.budgetDetail(getRoleName(),mDatas.get(position).getId());

            }
        }

        @Override
        public void onItemLongClick(View view) {

        }
    };
    private void loadPopData(){

    }
    private void initPopView(View view){
        mCloseIv = view.findViewById(R.id.close_img);
//        mImgIv = view.findViewById(R.id.img_id);
        mWindowRecyclerView = view.findViewById(R.id.recycler_image_view);
        mPriceTv = view.findViewById(R.id.content_2);
        mDealSuccessTv = view.findViewById(R.id.deal_success);
        mPaymentNameTv = view.findViewById(R.id.payment_name);
        mPaymentValueTv = view.findViewById(R.id.payment_value);
        mOrderNoNameTv = view.findViewById(R.id.order_number_name);
        mOrderNoValueTv = view.findViewById(R.id.order_number_value);
        mDealObjNameTv = view.findViewById(R.id.deal_object_name);
        mDealObjValueTv = view.findViewById(R.id.deal_object_value);
        mTransTimeNameTv = view.findViewById(R.id.trans_time_name);
        mTransTimeValueTv = view.findViewById(R.id.trans_time_value);
        mReceiveTimeNameTv = view.findViewById(R.id.receive_time_name);
        mReceiveTimeValueTv = view.findViewById(R.id.receive_time_value);

        mWindowRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        mWindowRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.HORIZONTAL));
        mWindowAdapter = new InOutDetailsAdapter(mUrls,mContext);
        mWindowRecyclerView.setAdapter(mWindowAdapter);

    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.history_income:
                    historyMessage();
                    break;
                case R.id.close_img:
                    mPopWindow.dismiss();
                    break;
            }
        }
    };
    private void historyMessage(){
        mPresenter.historyMessage(getRoleName());
    }
    private void setPopListeners(){
        mCloseIv.setOnClickListener(mOnClickListener);
    }

    private void initPopWindow(View anchor, int layoutId) {
        Log.d(TAG, "initPopWindow anchor = " + anchor);
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(layoutId, null);
//        RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.layout_pop_window_tablet, null);

        loadPopData();
        initPopView(view);
        setPopListeners();

        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels;

        //获取view的坐标
        int[] location = new int[2];
//            anchor.getLocationInWindow(location);
        anchor.getLocationOnScreen(location);

        int height = location[1] + anchor.getHeight();

        mPopWindow = new PopupWindow(view, widthPixels - (int) Utils.dp2px(49),
                heightPixels - (int) Utils.dp2px(300), true);


        Log.d(TAG, "initPopWindow = " + mPopWindow.isShowing());


        if (mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        } else {


            ColorDrawable cd = new ColorDrawable(Color.BLACK);
            mPopWindow.setBackgroundDrawable(cd);
//            mLeftPopWindow.setBackgroundDrawable(Color.BLACK);
            final Window w = mActivity.getWindow();

            WindowManager.LayoutParams params = w.getAttributes();
            //这里设置透明度与，为什么会显示桌面
            params.alpha = 1f;

//            w.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            w.setAttributes(params);


            mPopWindow.setFocusable(true);
//
            mPopWindow.setOutsideTouchable(false);

            mPopWindow.update();
//
//            view.removeView(parent);
//            mLeftPopWindow.setAnimationStyle(R.style.WindowAnimStyle);
//            int offsetY = (int)Utils.dp2px(getResources().getDimension(R.dimen.dimen_72));
            int bottomHeight = Utils.getBottomBarHeight(mContext);
            int offsetY = (int) Utils.dp2px(73);
            Log.d(TAG, "initPopWindow: offsetY = " + offsetY);
            Log.d(TAG, "initPopWindow: bottomHeight = " + bottomHeight);
            mPopWindow.showAtLocation(anchor, Gravity.BOTTOM | Gravity.END, 0, offsetY + bottomHeight);
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
            mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
//                    if (timer != null)
//                        timer.cancel();

                    WindowManager.LayoutParams attributes = w.getAttributes();

                    attributes.alpha = 1f;

                    w.setAttributes(attributes);
                    mShadeView.setVisibility(View.GONE);

                    mPopWindow = null;

                }
            });

        }
//
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mPresenter.detachView(this);
    }

    @OnClick(R.id.history_income)
    public void onViewClicked() {
    }

    @Override
    public void showContent(JsonBase<List<ResultInfo>> jsonBase) {
        List<ResultInfo> datas = (List<ResultInfo>) jsonBase.getData();
        Log.d(TAG, "showContent: datas = " + datas);
        mDatas.clear();
        if (datas != null) {
            mDatas.addAll(datas);
            mAdapter.setmDatas(datas);
        }
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showOrderDetails(JsonBase<JsonOrderDetailsData> jsonBase) {
        JsonOrderDetailsData data = (JsonOrderDetailsData) jsonBase.getData();
        Log.d(TAG, "showOrderDetails: data = " + data);

    }

    @Override
    public void onCancelOrder(JsonBase jsonBase) {

    }

    @Override
    public void onConfirmReceipt(JsonBase jsonBase) {

    }

    @Override
    public void showInCome(JsonBase<Double> jsonBase) {
        Double data = (Double) jsonBase.getData();
        Log.d(TAG, "showInCome: data = " + data);
        if (data != null){
            mIncomeValueTv.setText( "￥ " + data);
        }

    }

    @Override
    public void showComplaint(JsonBase<List<ComplaintReason>> jsonBase) {

    }

    @Override
    public void showUserInfo(JsonBase<UserInfo> jsonBase) {

    }

    @Override
    public void showBudgetDetails(JsonBase<JsonBudgetDetails> jsonBase) {
        initPopWindow(mRecyclerView,R.layout.layout_income_details_window);
        mShadeView.setVisibility(View.VISIBLE);
        JsonBudgetDetails data = (JsonBudgetDetails) jsonBase.getData();
        String orderNumber = data.getOrderNumber();
        List<InOutGoods> goods = data.getGoods();
        for (int i = 0; i < goods.size(); i++) {
            InOutGoods inOutGoods = goods.get(i);
            String picture = inOutGoods.getPicture().split(",")[0];
            mUrls.add(picture);
        }
        Log.d(TAG, "showBudgetDetails: mUrls = " + mUrls);
        mOrderNoValueTv.setText(orderNumber);
        mWindowAdapter.setmUrl(mUrls);

    }

    @Override
    public void showAfterDetails(JsonBase<JsonAfterDetails> jsonBase) {

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

    @Override
    public void showDeliverDetails(JsonBase<JsonDeliverDatails> jsonBase) {

    }
}
