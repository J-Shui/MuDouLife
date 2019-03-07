package com.mudoulife.ui.fragment.main;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.base.BaseFragment;
import com.mudoulife.common.Globals;
import com.mudoulife.eventbus.MessageEvent;
import com.mudoulife.ui.fragment.aftersale.AfterSaleDetailsFragment;
import com.mudoulife.ui.fragment.aftersale.AfterSaleHandleFragment;
import com.mudoulife.ui.fragment.listener.OnItemSwitchListener;
import com.mudoulife.ui.fragment.mine.MineInOutFragment;
import com.mudoulife.ui.fragment.mine.MineInfoFragment;
import com.mudoulife.ui.fragment.mine.MineQueryFragment;
import com.mudoulife.ui.fragment.order.OrderFinishFragment;
import com.mudoulife.ui.fragment.order.OrderPriceFragment;
import com.mudoulife.ui.fragment.order.OrderQueryFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseContentFragment extends Fragment {

    @BindView(R.id.top_order_container)
    protected RelativeLayout mTopOrderContainer;
    @BindView(R.id.top_help_container)
    protected RelativeLayout mTopHelpContainer;
    @BindView(R.id.top_mine_container)
    protected RelativeLayout mTopMineContainer;

    @BindView(R.id.left_order_container)
    protected LinearLayout mLeftOrderContainer;
    @BindView(R.id.left_price_container)
    protected LinearLayout mLeftPriceContainer;
    @BindView(R.id.left_account_Container)
    protected LinearLayout mLeftAccountContainer;

    @BindView(R.id.top_order_img)
    protected ImageView mTopOrderIv;
    @BindView(R.id.top_order_text)
    protected TextView mTopOrderTv;
    @BindView(R.id.top_help_img)
    protected ImageView mTopHelpIv;
    @BindView(R.id.top_help_text)
    protected TextView mTopHelpTv;
    @BindView(R.id.top_mine_img)
    protected ImageView mTopMineIv;
    @BindView(R.id.top_mine_text)
    protected TextView mTopMineTv;

    @BindView(R.id.left_order_text)
    protected TextView mLeftOrderTv;
    @BindView(R.id.left_look_text)
    protected TextView mLeftLookTv;
    @BindView(R.id.left_price_text)
    protected TextView mLeftPriceTv;
    @BindView(R.id.left_service_text)
    protected TextView mLeftServiceTv;
    @BindView(R.id.left_complete_text)
    protected TextView mLeftCompleteTv;
    @BindView(R.id.left_account_text)
    protected TextView mLeftAccountTv;

    @BindView(R.id.left_order_img)
    protected ImageView mLeftOrderIv;
    @BindView(R.id.left_order_line)
    protected ImageView mLeftOrderLineIv;
    @BindView(R.id.left_price_img)
    protected ImageView mLeftPriceIv;
    @BindView(R.id.left_price_line)
    protected ImageView mLeftPriceLineIv;
    @BindView(R.id.left_account_img)
    protected ImageView mLeftAccountIv;
    @BindView(R.id.left_account_line)
    protected ImageView mLeftAccountLineIv;

//    @BindView(R.id.scroll_view)
//    NestedScrollView mScrollView;
//    @BindView(R.id.recycler_view)
//    RecyclerView mRecyclerView;


    private static final String TAG = "BaseContentFragment";
    private static final int DEFAULT_STATE = 0;
    private static final int SELECTED_STATE = 1;
    private int mTopCurrentItem;
    private int mLeftCurrentItem;

    private Context mContext;

    public BaseContentFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base_content, container, false);
        ButterKnife.bind(this, view);
        mFragmentManager = getChildFragmentManager();
        init();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            Log.d(TAG, "onHiddenChanged: 11111111111111111" + getFragmentName());

        }else{
//            mTopCurrentItem = Globals.TOP_ORDER_ITEM;
//            mLeftCurrentItem = Globals.LEFT_ORDER_ITEM;
            Log.d(TAG, "onHiddenChanged: 222222222222222222" + getFragmentName());
//            EventBus.getDefault().post(new MessageEvent(getFragmentName()));
        }
    }

    private void init() {
        setSelectedState(SELECTED_STATE, mTopOrderIv, mLeftOrderIv, mLeftOrderLineIv);
        setSelectedState(SELECTED_STATE, mTopOrderTv, mLeftOrderTv, mLeftLookTv);
        mTopCurrentItem = Globals.TOP_ORDER_ITEM;
        mLeftCurrentItem = Globals.LEFT_ORDER_ITEM;
        initView();
        mTopHelpTv.setText(getTopMiddleName());
        switchTopViewState();
        mLeftOrderContainer.setOnClickListener(mOnClickListener);
        mLeftPriceContainer.setOnClickListener(mOnClickListener);
        mLeftAccountContainer.setOnClickListener(mOnClickListener);
        mTopOrderContainer.setOnClickListener(mOnClickListener);
        mTopHelpContainer.setOnClickListener(mOnClickListener);
        mTopMineContainer.setOnClickListener(mOnClickListener);
    }
    protected  void initView(){

    }

    private void setSelectedState(int state, ImageView... imageViews) {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageLevel(state);
        }
    }

    private void setSelectedState(int state, TextView... textViews) {
        for (TextView textView : textViews) {
            if (state == DEFAULT_STATE) {
                textView.setTextColor(getResources().getColor(R.color.color_ffffff));
            } else if (state == SELECTED_STATE) {
                textView.setTextColor(getResources().getColor(R.color.color_fec300));
            }
        }
    }

    private void initViewState() {
//        ImageView[] imageViews = {mTopOrderIv, mTopHelpIv, mTopMineIv, mLeftOrderIv,
//                mLeftOrderLineIv, mLeftPriceIv, mLeftPriceLineIv, mLeftAccountIv, mLeftAccountLineIv};
//        TextView[] textViews = {mTopOrderTv, mTopHelpTv, mTopMineTv, mLeftOrderTv, mLeftLookTv,
//                mLeftPriceTv, mLeftServiceTv, mLeftAccountTv, mLeftCompleteTv
//        };
//        setSelectedState(DEFAULT_STATE, imageViews);
//        setSelectedState(DEFAULT_STATE, textViews);
        initLeftViewState();
        initTopViewState();
    }
    protected abstract void setOrderLeftView();
    protected abstract void setAfterSaleLeftView();
    protected void setLeftCurrentItem4Price(){
        mLeftCurrentItem = Globals.LEFT_PRICE_ITEM;
    }
    private void setMineLeftView(){
        mLeftOrderTv.setText(getResources().getString(R.string.in_out));
        mLeftLookTv.setText(getResources().getString(R.string.ming_xi));
        mLeftPriceTv.setText(getResources().getString(R.string.jie_kuan));
        mLeftServiceTv.setText(getResources().getString(R.string.look));
        mLeftCompleteTv.setText(getResources().getString(R.string.personal));
        mLeftAccountTv.setText(getResources().getString(R.string.information));
    }
    protected void setDefaultOrderLeftView(){
        mLeftOrderTv.setText(getResources().getString(R.string.left_order));
        mLeftLookTv.setText(getResources().getString(R.string.look));
        mLeftPriceTv.setText(getResources().getString(R.string.left_price));
        mLeftServiceTv.setText(getResources().getString(R.string.service));
        mLeftCompleteTv.setText(getResources().getString(R.string.complete));
        mLeftAccountTv.setText(getResources().getString(R.string.left_account));
    }

    protected void setDefaultAfterSaleLeftView(){
        mLeftOrderTv.setText(getResources().getString(R.string.after_sale));
        mLeftLookTv.setText(getResources().getString(R.string.handler));
        mLeftPriceTv.setText(getResources().getString(R.string.complaint));
        mLeftServiceTv.setText(getResources().getString(R.string.details));
    }

    protected void switchTopViewState(){
        String fragmentName = getFragmentName();
        switch (mTopCurrentItem){
            case Globals.TOP_ORDER_ITEM:
                setVisibility(View.VISIBLE,getContainers());
                setOrderLeftView();
                setSelectedState(SELECTED_STATE, mTopOrderIv);
                setSelectedState(SELECTED_STATE, mTopOrderTv);
                switch (mLeftCurrentItem){
                    case Globals.LEFT_ORDER_ITEM:
                        setSelectedState(SELECTED_STATE, mLeftOrderIv, mLeftOrderLineIv);
                        setSelectedState(SELECTED_STATE, mLeftOrderTv, mLeftLookTv);
                        switchMenu(OrderQueryFragment.class.getName(), fragmentName);
                        break;
                    case Globals.LEFT_PRICE_ITEM:
                        setSelectedState(SELECTED_STATE, mLeftPriceIv, mLeftPriceLineIv);
                        setSelectedState(SELECTED_STATE, mLeftPriceTv, mLeftServiceTv);
                        switchMenu(OrderPriceFragment.class.getName(), fragmentName);
                        break;
                    case Globals.LEFT_ACCOUNT_ITEM:
                        setSelectedState(SELECTED_STATE, mLeftAccountIv, mLeftAccountLineIv);
                        setSelectedState(SELECTED_STATE, mLeftAccountTv, mLeftCompleteTv);
                        switchMenu(OrderFinishFragment.class.getName(), fragmentName);
                        break;
                }
                break;
            case Globals.TOP_HELP_ITEM:
                setVisibility(View.GONE,getContainers());
                setSelectedState(SELECTED_STATE, mTopHelpIv);
                setSelectedState(SELECTED_STATE, mTopHelpTv);
                setAfterSaleLeftView();
                switch (mLeftCurrentItem){
                    case Globals.LEFT_ORDER_ITEM:
                        setSelectedState(SELECTED_STATE, mLeftOrderIv, mLeftOrderLineIv);
                        setSelectedState(SELECTED_STATE, mLeftOrderTv, mLeftLookTv);
                        switchMenu(AfterSaleHandleFragment.class.getName(), fragmentName);
                        break;
                    case Globals.LEFT_PRICE_ITEM:
                        setSelectedState(SELECTED_STATE, mLeftPriceIv, mLeftPriceLineIv);
                        setSelectedState(SELECTED_STATE, mLeftPriceTv, mLeftServiceTv);
                        switchMenu(AfterSaleDetailsFragment.class.getName(), fragmentName);
                        break;
                }
                break;
            case Globals.TOP_MINE_ITEM:
//                mLeftAccountContainer.setVisibility(View.VISIBLE);
                setVisibility(View.VISIBLE,getContainers());
                setSelectedState(SELECTED_STATE, mTopMineIv);
                setSelectedState(SELECTED_STATE, mTopMineTv);
               setMineLeftView();
                switch (mLeftCurrentItem){
                    case Globals.LEFT_ORDER_ITEM:
                        setSelectedState(SELECTED_STATE, mLeftOrderIv, mLeftOrderLineIv);
                        setSelectedState(SELECTED_STATE, mLeftOrderTv, mLeftLookTv);
                        switchMenu(MineInOutFragment.class.getName(), fragmentName);
                        break;
                    case Globals.LEFT_PRICE_ITEM:
                        setSelectedState(SELECTED_STATE, mLeftPriceIv, mLeftPriceLineIv);
                        setSelectedState(SELECTED_STATE, mLeftPriceTv, mLeftServiceTv);
                        switchMenu(MineQueryFragment.class.getName(), fragmentName);
                        break;
                    case Globals.LEFT_ACCOUNT_ITEM:
                        setSelectedState(SELECTED_STATE, mLeftAccountIv, mLeftAccountLineIv);
                        setSelectedState(SELECTED_STATE, mLeftAccountTv, mLeftCompleteTv);
                        switchMenu(MineInfoFragment.class.getName(), fragmentName);
                        break;
                }
                break;
        }
    }
    protected void setVisibility(int visibility,LinearLayout...containers){
        for (LinearLayout container: containers) {
            container.setVisibility(visibility);
        }
    }
    protected abstract LinearLayout[] getContainers();
    protected abstract String getTopMiddleName();
    private void switchLeftViewState(){
        switch (mLeftCurrentItem){
            case Globals.LEFT_ORDER_ITEM:
                setSelectedState(SELECTED_STATE, mLeftOrderIv, mLeftOrderLineIv);
                setSelectedState(SELECTED_STATE, mLeftOrderTv, mLeftLookTv);
                switchMenu(OrderQueryFragment.class.getName(),getFragmentName());
                break;
            case Globals.LEFT_PRICE_ITEM:
                setSelectedState(SELECTED_STATE, mLeftPriceIv, mLeftPriceLineIv);
                setSelectedState(SELECTED_STATE, mLeftPriceTv, mLeftServiceTv);
                switchMenu(OrderPriceFragment.class.getName(),getFragmentName());
                break;
            case Globals.LEFT_ACCOUNT_ITEM:
                setSelectedState(SELECTED_STATE, mLeftAccountIv, mLeftAccountLineIv);
                setSelectedState(SELECTED_STATE, mLeftAccountTv, mLeftCompleteTv);
                switchMenu(OrderFinishFragment.class.getName(),getFragmentName());
                break;
        }
    }

    private Fragment mCurrentFragment;
    protected FragmentManager mFragmentManager;

    private void switchMenu(String fragmentName,String bottomFragmentName) {
        Log.d(TAG,"fragmentName = " + fragmentName);
        BaseFragment fragment = (BaseFragment) mFragmentManager.findFragmentByTag(fragmentName);
        Log.d(TAG,"fragment = " + fragment);
        if (fragment != null) {
            if (fragment == mCurrentFragment) return;

            mFragmentManager.beginTransaction().show(fragment).commit();
            fragment.onResume();
//            fragment.onHiddenChanged();
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(Globals.FORM_MAIN_FRAGMENT,getFragmentName());
            fragment = (BaseFragment) BaseFragment.instantiate(getActivity(), fragmentName,bundle);
            fragment.setOnItemSwitchListener(mOnItemSwitchListener);
//            fragment = new OrderQueryFragment();
            mFragmentManager.beginTransaction().add(R.id.content_container, fragment, fragmentName).commit();
        }

        if (mCurrentFragment != null) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
        }

        mCurrentFragment = fragment;
    }

    private OnItemSwitchListener mOnItemSwitchListener = new OnItemSwitchListener() {
        @Override
        public void onSwitch(int nextItem) {
            if (nextItem != -1){
                mLeftCurrentItem = nextItem;
                initLeftViewState();
                switchLeftViewState();
            }
//            switchTopViewState();
        }
    };
    private void initTopViewState() {
        ImageView[] imageViews = {mTopOrderIv, mTopHelpIv, mTopMineIv};
        TextView[] textViews = {mTopOrderTv, mTopHelpTv, mTopMineTv};
        setSelectedState(DEFAULT_STATE, imageViews);
        setSelectedState(DEFAULT_STATE, textViews);
    }
    private void initLeftViewState(){
        ImageView[] imageViews = { mLeftOrderIv, mLeftOrderLineIv, mLeftPriceIv,
                mLeftPriceLineIv, mLeftAccountIv, mLeftAccountLineIv};
        TextView[] textViews = {mLeftOrderTv, mLeftLookTv, mLeftPriceTv,
                mLeftServiceTv, mLeftAccountTv, mLeftCompleteTv};
        setSelectedState(DEFAULT_STATE, imageViews);
        setSelectedState(DEFAULT_STATE, textViews);
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v != null) {
                initViewState();
                int id = v.getId();
                int topItem = mTopCurrentItem;
                int leftItem = mLeftCurrentItem;
                switch (id) {
                    case R.id.top_order_container:
                        topItem = Globals.TOP_ORDER_ITEM;
                        break;
                    case R.id.top_help_container:
                        topItem = Globals.TOP_HELP_ITEM;
                        break;
                    case R.id.top_mine_container:
                        topItem = Globals.TOP_MINE_ITEM;
                        break;
                    case R.id.left_order_container:
                        leftItem = Globals.LEFT_ORDER_ITEM;
                        break;
                    case R.id.left_price_container:
                        leftItem = Globals.LEFT_PRICE_ITEM;
                        break;
                    case R.id.left_account_Container:
                        leftItem = Globals.LEFT_ACCOUNT_ITEM;
                        break;

                }
                if (mLeftCurrentItem != leftItem){
                    mLeftCurrentItem = leftItem;
                }
                if (mTopCurrentItem != topItem){
                    mTopCurrentItem = topItem;
                    mLeftCurrentItem = Globals.LEFT_ORDER_ITEM;
                }
//                switchLeftViewState();
                switchTopViewState();
            }
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract String getFragmentName();
}
