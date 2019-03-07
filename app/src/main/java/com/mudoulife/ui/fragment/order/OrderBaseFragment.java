package com.mudoulife.ui.fragment.order;


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

import com.mudoulife.R;
import com.mudoulife.base.BaseFragment;
import com.mudoulife.base.MainView;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.LocalStatus;
import com.mudoulife.data.net.response.ComplaintReason;
import com.mudoulife.data.net.response.JsonAfterDetails;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonBudgetDetails;
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.data.net.response.UserInfo;
import com.mudoulife.eventbus.MessageEvent;
import com.mudoulife.presenter.OrderBasePresenter;
import com.mudoulife.ui.adapter.order.OrderBaseAdapter;
import com.mudoulife.ui.adapter.order.OrderOuterAdapter;
import com.mudoulife.ui.fragment.main.CarryFragment;
import com.mudoulife.ui.fragment.main.ExpressFragment;
import com.mudoulife.ui.fragment.main.ShanghuFragment;
import com.mudoulife.ui.fragment.main.SubscriptionFragment;
import com.mudoulife.ui.fragment.main.WorkerFragment;
import com.mudoulife.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class OrderBaseFragment extends BaseFragment implements MainView<JsonBase<List<ResultInfo>>> {
    private static final String TAG = "OrderBaseFragment";
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
    protected OrderOuterAdapter mAdapter;
    //    protected List<JsonBase<ResultInfo>> mDatas;
    protected List<ResultInfo> mDatas;

    protected ImageView mCloseIv;
    protected Button mSureBtn;

    @Inject
    protected OrderBasePresenter mPresenter;
    private Unbinder mUnbinder;
    private View mContainer;

    protected int mCurrentPosition;

    public OrderBaseFragment() {
        // Required empty public constructor
        Log.d(TAG, "OrderBaseFragment: OrderBaseFragment");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContainer = inflater.inflate(R.layout.fragment_order_base_layout, container, false);
        mUnbinder = ButterKnife.bind(this, mContainer);
        init();
        setRecyclerView();
        return mContainer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.d(TAG, "onHiddenChanged: hidden = " + hidden);
        super.onHiddenChanged(hidden);
        if (!hidden)
            loadData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent event) {
        String fragmentName = event.getFragmentName();
        int topItem = event.getTopItem();
        int leftItem = event.getLeftItem();
        Log.d(TAG, "Event: fragmentName = " + fragmentName);
        Log.d(TAG, "Event: mMainFragmentName = " + mMainFragmentName);
//        String parentName = getParentFragment().getClass().getName();
//        Log.d(TAG, "Event: parentName = " + parentName);
        if (fragmentName.equals(mMainFragmentName)) {
//            if (topItem == Globals)
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    protected void init() {
        mDatas = new ArrayList<>();
    }

    protected abstract void loadData();

    protected abstract void initAdapter();

    private void setRecyclerView() {
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)/* {
            @Override
            public boolean canScrollVertically() {
                return mIsScrollEnable && super.canScrollVertically();
            }
        }*/;
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration decor = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
//        BitmapDrawable drawable = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),R.mipmap.top_tab_background));
//        drawable.getIntrinsicHeight();
//        decor.setDrawable(drawable);
        mRecyclerView.addItemDecoration(decor);
        initAdapter();
//        mAdapter = new OrderBaseAdapter(mContext, mDatas,mMainFragmentName);
        mAdapter.setOnItemClickListener(new OrderOuterAdapter.OnItemClickListener() {
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
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
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

    protected void onSureBtnClick() {
        ResultInfo data = mDatas.get(mCurrentPosition);
        mPresenter.confirmReceipt(getRoleName(), data.getId());
//        data.setReceived(true);
//        data.setLocalStatus(LocalStatus.FINISH);
//        mAdapter.notifyItemChanged(mCurrentPosition);
//                    if (!data.isFirstShowLeft())
        dismissWindow();
    }

    protected void dismissWindow() {
        if (mIsLeftWindow){
            Log.d(TAG, "dismissWindow: 11111111111111");
            if (mLeftPopWindow != null && mLeftPopWindow.isShowing()){
                Log.d(TAG, "dismissWindow: 2222222222222222");
                mLeftPopWindow.dismiss();
            }
        }else {
            Log.d(TAG, "dismissWindow: 333333333333333333333");
            if (mRightPopWindow != null && mRightPopWindow.isShowing()){
                mRightPopWindow.dismiss();
                Log.d(TAG, "dismissWindow: 44444444444444444");
            }

        }
    }

    protected void setOnItemSwitchListener() {
        if (mOnItemSwitchListener != null) {
            mOnItemSwitchListener.onSwitch(getNextItem());
        }
    }

    protected PopupWindow initPopWindow(int layoutId) {
        mShadeView.setVisibility(View.VISIBLE);
        return initPopWindow(mContainer, layoutId);
    }

    protected abstract void onClickRightBtn(int position);

    protected abstract void onClickLeftBtn(int position);

    protected abstract int getNextItem();

    protected abstract void loadPopData();

    protected abstract void initPopView(View view);

    //    private void initPopView(View view) {
//        mCloseIv = view.findViewById(R.id.close_img);
//        mNameValueTv = view.findViewById(R.id.name_value);
//        mAddressValueTv = view.findViewById(R.id.address_value);
//        mPhoneValueTv = view.findViewById(R.id.phone_value);
//        mTimeValueTv = view.findViewById(R.id.appoint_time_value);
//        mUserRemarkTv = view.findViewById(R.id.user_remark);
//        mProjectContainer = view.findViewById(R.id.material_container);
//        mMaterialNameTv = view.findViewById(R.id.material_name_id);
//        mMaterialValueTv = view.findViewById(R.id.material_value_id);
//        mNumberNameTv = view.findViewById(R.id.number_name_id);
//        mNumberValueTv = view.findViewById(R.id.number_value_id);
//        mPriceNameTv = view.findViewById(R.id.price_name_id);
//        mPriceValueTv = view.findViewById(R.id.price_value_id);
//        mSureBtn = view.findViewById(R.id.sure_receive_btn);
//
//        mCloseIv.setOnClickListener(mOnClickListener);
//        mSureBtn.setOnClickListener(mOnClickListener);
//
//    }
    protected void setPopListeners() {
        mCloseIv.setOnClickListener(mOnClickListener);
        mSureBtn.setOnClickListener(mOnClickListener);
    }

    private PopupWindow initPopWindow(View anchor, int layoutId) {
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

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
//        final int screenHeight = ScreenUtils.getScreenHeight(anchorView.getContext());
//        final int screenWidth = ScreenUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showContent(JsonBase<List<ResultInfo>> jsonBase) {
        Log.d(TAG, "data = " + jsonBase);
        List<ResultInfo> dataList = (List<ResultInfo>) jsonBase.getData();


        if (dataList != null) {
            mDatas.clear();
            for (int i = 0; i < dataList.size(); i++) {
                ResultInfo data = dataList.get(i);
//            data.setNumber(i + 50);
//            data.setStandard("4" + i + "x" + "4" + i);
                mDatas.add(data);
            }
            mAdapter.setDatas(dataList);
        }
    }

    @Override
    public void showError(Throwable e) {
        Log.d(TAG, "showError: e = " + e);
    }

    @Override
    public void showTipMsg(String msg) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showDeliverDetails(JsonBase<JsonDeliverDatails> jsonBase) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView(this);
        EventBus.getDefault().unregister(this);
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
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

    }
}
