package com.mudoulife.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mudoulife.common.Globals;
import com.mudoulife.di.HasComponent;
import com.mudoulife.ui.activity.library.LibraryCarryActivity;
import com.mudoulife.ui.activity.library.LibraryExpressActivity;
import com.mudoulife.ui.activity.library.LibrarySubscriptionActivity;
import com.mudoulife.ui.activity.library.LibraryWorkerActivity;
import com.mudoulife.ui.fragment.listener.OnItemSwitchListener;
import com.mudoulife.ui.fragment.listener.OnRefreshListener;
import com.mudoulife.ui.fragment.main.CarryFragment;
import com.mudoulife.ui.fragment.main.ExpressFragment;
import com.mudoulife.ui.fragment.main.ShanghuFragment;
import com.mudoulife.ui.fragment.main.SubscriptionFragment;
import com.mudoulife.ui.fragment.main.WorkerFragment;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.base
 * project : MuDouLife
 * fragment预加载解决方案：
 *  1.可以懒加载的fragment
 *  2.切换到其他界面时停止加载数据(可选)
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    protected OnItemSwitchListener mOnItemSwitchListener;
    protected String mMainFragmentName;
    protected boolean isInit = false;
    protected boolean isLoad = false;

    public void setOnItemSwitchListener(OnItemSwitchListener listener) {
        this.mOnItemSwitchListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppLog.d("onCreate:" + this);
        if (getArguments() != null){
            mMainFragmentName = getArguments().getString(Globals.FORM_MAIN_FRAGMENT);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        AppLog.d("onDestroy:" + this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        AppLog.d("onActivityCreated:" + this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        AppLog.d("onViewCreated:" + this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        AppLog.d("onCreateView:" + this);
        isInit = true;
        //初始化时去加载数据
        isCanLoadData();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件
     *  1，视图已经初始化
     *  2，视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit){
            return;
        }
        if (getUserVisibleHint()){
            lazyLoad();
            isLoad = true;
        }else {
            if (isLoad)
                stopLoad();
        }
    }

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要再切换到其他页面停止加载数据
     */
    protected void stopLoad() {
    }

    /**
     * 当视图初始化并且对用户可见时去真正加载数据
     */
    protected void lazyLoad() {
        
    }

    /**
     * 通知用户，UI对用户是否可见
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(TAG, getClass().getName() + " setUserVisibleHint: isVisibleToUser = " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
//        AppLog.d("onDestroyView:" + this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        AppLog.d("onAttach:" + this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        AppLog.d("onDetach:" + this);
    }


    @Override
    public void onStart() {
        super.onStart();
//        AppLog.d("onStart:" + this);
    }

    @Override
    public void onResume() {
        super.onResume();
//        AppLog.d("onResume:" + this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        AppLog.d("onPause:" + this);
    }

    @Override
    public void onStop() {
        super.onStop();
//        AppLog.d("onStop:" + this);
    }
    public void load(){

    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
    protected Class<?> getActivityClass() {
        if (mMainFragmentName.equals(WorkerFragment.class.getName())) {
            return LibraryWorkerActivity.class;
        }
        if (mMainFragmentName.equals(SubscriptionFragment.class.getName())) {
            return LibrarySubscriptionActivity.class;
        }
        if (mMainFragmentName.equals(ExpressFragment.class.getName())) {
            return LibraryExpressActivity.class;
        }
        if (mMainFragmentName.equals(CarryFragment.class.getName())) {
            return LibraryCarryActivity.class;
        }
        return null;
    }

    protected String getRoleName() {
        if (mMainFragmentName.equals(WorkerFragment.class.getName())) {
            return Globals.ROLE_WORKER_NAME;
        } else if (mMainFragmentName.equals(SubscriptionFragment.class.getName())) {
            return Globals.ROLE_SUBSCRIPTION_NAME;
        } else if (mMainFragmentName.equals(ExpressFragment.class.getName())) {
            return Globals.ROLE_EXPRESS_NAME;
        } else if (mMainFragmentName.equals(CarryFragment.class.getName())) {
            return Globals.ROLE_CARRY_NAME;
        } else if (mMainFragmentName.equals(ShanghuFragment.class.getName())) {
            return Globals.ROLE_SHANG_HU_NAME;
        }
        return null;
    }

    protected int getRoleId() {
        if (mMainFragmentName.equals(WorkerFragment.class.getName())) {
            return Globals.ROLE_WORKER_ID;
        } else if (mMainFragmentName.equals(SubscriptionFragment.class.getName())) {
            return Globals.ROLE_SUBSCRIPTION_ID;
        } else if (mMainFragmentName.equals(ExpressFragment.class.getName())) {
            return Globals.ROLE_EXPRESS_ID;
        } else if (mMainFragmentName.equals(CarryFragment.class.getName())) {
            return Globals.ROLE_CARRY_ID;
        } else if (mMainFragmentName.equals(ShanghuFragment.class.getName())) {
            return Globals.ROLE_SHANG_HU_ID;
        }
        return Globals.ROLE_WORKER_ID;
    }
}
