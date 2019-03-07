package com.mudoulife.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.base.BaseActivity;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.JsonRoleData;
import com.mudoulife.di.ComponentHolder;
import com.mudoulife.di.HasComponent;
import com.mudoulife.di.component.DaggerMainComponent;
import com.mudoulife.di.component.MainComponent;
import com.mudoulife.di.module.ActivityModule;
import com.mudoulife.ui.fragment.main.CarryFragment;
import com.mudoulife.ui.fragment.main.ExpressFragment;
import com.mudoulife.ui.fragment.main.ShanghuFragment;
import com.mudoulife.ui.fragment.main.SubscriptionFragment;
import com.mudoulife.ui.fragment.main.WorkerFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<MainComponent> {
    private static final String TAG = "MainActivity";
    @BindView(R.id.carry_container)
    RelativeLayout mCarryContainer;
    @BindView(R.id.worker_container)
    RelativeLayout mWorkerContainer;
    @BindView(R.id.subscription_container)
    RelativeLayout mSubscriptionContainer;
    @BindView(R.id.shanghu_container)
    RelativeLayout mShanghuContainer;
    @BindView(R.id.service_container)
    RelativeLayout mExpressContainer;

    @BindView(R.id.carry_img)
    ImageView mCarryIv;
    @BindView(R.id.carry_text)
    TextView mCarryTv;
    @BindView(R.id.worker_img)
    ImageView mWorkerIv;
    @BindView(R.id.worker_text)
    TextView mWorkerTv;
    @BindView(R.id.subscription_img)
    ImageView mSubscriptionIv;
    @BindView(R.id.subscription_text)
    TextView mSubscriptionTv;
    @BindView(R.id.shanghu_img)
    ImageView mShanghuIv;
    @BindView(R.id.shanghu_text)
    TextView mShanghuTv;
    @BindView(R.id.express_img)
    ImageView mExpressIv;
    @BindView(R.id.express_text)
    TextView mExpressTv;

    @BindView(R.id.content_container)
    FrameLayout mContentContainer;


    private static final int BOTTOM_ITEM_TEXT_SIZE = 24;
    private static final int DEFAULT_STATE = 0;
    private static final int SELECTED_STATE = 1;
    int[] roleIds = {Globals.ROLE_WORKER_ID,Globals.ROLE_SHANG_HU_ID,Globals.ROLE_CARRY_ID,
            Globals.ROLE_SUBSCRIPTION_ID,Globals.ROLE_EXPRESS_ID};
    String[] fragments = {WorkerFragment.class.getName(),
            ShanghuFragment.class.getName(),CarryFragment.class.getName(),
            SubscriptionFragment.class.getName(), ExpressFragment.class.getName()};
    private FragmentManager mFragmentManager;
    private ImageView[] imageViews;
    private TextView[] textViews;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ScreenTitleUtils.setScreenTitle(this, R.color.color_fec300,false,false);
        ButterKnife.bind(this);
        imageViews = new ImageView[]{mWorkerIv,mShanghuIv,mCarryIv,mSubscriptionIv,mExpressIv};
        textViews = new TextView[]{mWorkerTv,mShanghuTv,mCarryTv,mSubscriptionTv,mExpressTv};
        Intent intent = getIntent();
        mFragmentManager = getSupportFragmentManager();
        ArrayList<Parcelable> datas = intent.getParcelableArrayListExtra(Globals.ROLES_DATA);
        RelativeLayout[] mContainers = {mWorkerContainer,mShanghuContainer,mCarryContainer,
                mSubscriptionContainer,mExpressContainer};
        if (datas != null){
            for (int i = 0; i < datas.size(); i++) {
                JsonRoleData role = (JsonRoleData) datas.get(i);
                int id = role.getId();
                for (int j = 0; j < roleIds.length; j++) {
                    if (id == roleIds[j]){
                        mContainers[j].setVisibility(View.VISIBLE);
                        if (i == 0){
                            switchMenu(fragments[j]);
                            setSelectedState(SELECTED_STATE,imageViews[j]);
                            setSelectedState(SELECTED_STATE,textViews[j]);
                        }
                        break;
                    }

                }
            }
        }

        init();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v != null) {
                initViewState();
                int id = v.getId();
                switch (id) {
                    case R.id.carry_container:
                        setSelectedState(SELECTED_STATE,mCarryIv);
                        setSelectedState(SELECTED_STATE,mCarryTv);
                        break;
                    case R.id.worker_container:
                        setSelectedState(SELECTED_STATE,mWorkerIv);
                        setSelectedState(SELECTED_STATE,mWorkerTv);
                        break;
                    case R.id.subscription_container:
                        setSelectedState(SELECTED_STATE,mSubscriptionIv);
                        setSelectedState(SELECTED_STATE,mSubscriptionTv);
                        break;
                    case R.id.shanghu_container:
                        setSelectedState(SELECTED_STATE,mShanghuIv);
                        setSelectedState(SELECTED_STATE,mShanghuTv);
                        break;
                    case R.id.service_container:
                        setSelectedState(SELECTED_STATE,mExpressIv);
                        setSelectedState(SELECTED_STATE,mExpressTv);
                        break;
                }
                switchMenu(getFragmentName(id));
            }
        }
    };
    private void initViewState(){

        setSelectedState(DEFAULT_STATE, imageViews);
        setSelectedState(DEFAULT_STATE, textViews);
    }

    private void setSelectedState(int state, ImageView... imageViews) {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageLevel(state);
        }
    }

    private void setSelectedState(int state, TextView... textViews) {
        for (TextView textView : textViews) {
            if (state == DEFAULT_STATE) {
                textView.setTextColor(getResources().getColor(R.color.color_666666));
            } else if (state == SELECTED_STATE) {
                textView.setTextColor(getResources().getColor(R.color.color_fec300));
            }
        }
    }

    private void init() {
//        mCarryTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, Globals.gScreenScale * BOTTOM_ITEM_TEXT_SIZE);
//        mWorkerTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, Globals.gScreenScale * BOTTOM_ITEM_TEXT_SIZE);
//        mSubscriptionTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, Globals.gScreenScale * BOTTOM_ITEM_TEXT_SIZE);
//        mShanghuTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, Globals.gScreenScale * BOTTOM_ITEM_TEXT_SIZE);
//        mExpressTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, Globals.gScreenScale * BOTTOM_ITEM_TEXT_SIZE);

//        setSelectedState(SELECTED_STATE,mWorkerIv);
//        setSelectedState(SELECTED_STATE,mWorkerTv);
//        switchMenu(getFragmentName(R.id.worker_container));

        mCarryContainer.setOnClickListener(mOnClickListener);
        mWorkerContainer.setOnClickListener(mOnClickListener);
        mSubscriptionContainer.setOnClickListener(mOnClickListener);
        mShanghuContainer.setOnClickListener(mOnClickListener);
        mExpressContainer.setOnClickListener(mOnClickListener);
    }


    private Fragment mCurrentFragment;

    private void switchMenu(String fragmentName) {
        Log.d(TAG,"fragmentName = " + fragmentName);
        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentName);
        Log.d(TAG,"fragment = " + fragment);
        if (fragment != null) {
            if (fragment == mCurrentFragment) return;

            mFragmentManager.beginTransaction().show(fragment).commit();
            fragment.onResume();
        } else {
            fragment = Fragment.instantiate(this, fragmentName);
            mFragmentManager.beginTransaction().add(R.id.content_container, fragment, fragmentName).commit();
        }

        if (mCurrentFragment != null) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
        }

        mCurrentFragment = fragment;
    }

    private String getFragmentName(int menuId) {
        switch (menuId) {
            case R.id.carry_container:
                return CarryFragment.class.getName();
            case R.id.worker_container:
                return WorkerFragment.class.getName();
            case R.id.subscription_container:
                return SubscriptionFragment.class.getName();
            case R.id.shanghu_container:
                return ShanghuFragment.class.getName();
            case R.id.service_container:
                return ExpressFragment.class.getName();
            default:
                return null;
        }
    }

//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void dismissLoading() {
//
//    }
//
//    @Override
//    public void showError(Throwable e) {
//
//    }
//
//    @Override
//    public void showTipMsg(String msg) {
//
//    }

    @Override
    public MainComponent getComponent() {
        return DaggerMainComponent.builder()
                .applicationComponent(ComponentHolder.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }
}
