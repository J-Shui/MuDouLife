package com.mudoulife.ui.adapter.order;

import android.content.Context;
import android.util.Log;

import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.ui.fragment.main.CarryFragment;
import com.mudoulife.ui.fragment.main.ExpressFragment;
import com.mudoulife.ui.fragment.main.ShanghuFragment;
import com.mudoulife.ui.fragment.main.SubscriptionFragment;
import com.mudoulife.ui.fragment.main.WorkerFragment;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/12
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.adapter.order
 * project : MuDouLife
 */
public class OrderPriceAdapter extends OrderOuterAdapter {


    public OrderPriceAdapter(Context mContext, List<ResultInfo> mInfos, String mFragmentName) {
        super(mContext, mInfos, mFragmentName);
    }

    @Override
    protected boolean isEnableLeftButton(int status) {
        if (mFragmentName.equals(WorkerFragment.class.getName())) {
            if (status < Globals.USER_CONFIRM_CRAFTSMAN_PRICE){
                return true;
            }
        }else if (mFragmentName.equals(ShanghuFragment.class.getName())) {
            return false;
        } else if (mFragmentName.equals(ExpressFragment.class.getName())){
            if (status <= Globals.STORE_DELIVER){
                return true;
            }
        }else if (mFragmentName.equals(CarryFragment.class.getName())) {
            if (status == Globals.CARRY_END_SERVICE){
                return true;
            }
        }

        return false;
    }

    @Override
    protected boolean isShowButtons(int status) {
        return true;
    }

    @Override
    protected boolean isQuery() {
        return false;
    }

    @Override
    protected boolean isShowLeftBtn() {
        return true;
    }

    @Override
    protected String getLeftString() {
        Log.d(TAG, "getLeftString: ");
        if (mFragmentName.equals(ExpressFragment.class.getName())){
            return mContext.getResources().getString(R.string.yet_take_goods);
        }
        return mContext.getResources().getString(R.string.commit_price);
    }

    @Override
    protected String getRightString() {
        if (mFragmentName.equals(ExpressFragment.class.getName())){
            return mContext.getResources().getString(R.string.commit_price);
        }
        if (mFragmentName.equals(SubscriptionFragment.class.getName())){
            return mContext.getResources().getString(R.string.start_make);
        }
        return mContext.getResources().getString(R.string.start_service);
    }


}
