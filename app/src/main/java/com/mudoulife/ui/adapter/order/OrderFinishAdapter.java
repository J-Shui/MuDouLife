package com.mudoulife.ui.adapter.order;

import android.content.Context;

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
public class OrderFinishAdapter extends OrderOuterAdapter {


    public OrderFinishAdapter(Context mContext, List<ResultInfo> mInfos, String mFragmentName) {
        super(mContext, mInfos, mFragmentName);
    }

    @Override
    protected boolean isEnableLeftButton(int status) {
        if (mFragmentName.equals(WorkerFragment.class.getName())) {
            if (status == Globals.CRAFTSMAN_START_SERVICE){
                return true;
            }
        }else if (mFragmentName.equals(ShanghuFragment.class.getName())) {
            if (status == Globals.DELIVERY_RECEIPT){
                return true;
            }
        }else if (mFragmentName.equals(ExpressFragment.class.getName())) {
            if (status <= Globals.EXPRESS_PICK){
                return true;
            }
        }else if (mFragmentName.equals(CarryFragment.class.getName())) {
            if (status <= Globals.CARRY_START_SERVICE){
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
        if (mFragmentName.equals(ShanghuFragment.class.getName())) {
            return mContext.getResources().getString(R.string.delivery);
        }else if (mFragmentName.equals(SubscriptionFragment.class.getName()))
            return mContext.getResources().getString(R.string.delivery_goods);
        else if (mFragmentName.equals(ExpressFragment.class.getName()))
            return mContext.getResources().getString(R.string.already_delivery);
        return mContext.getResources().getString(R.string.finish_service);
    }

    @Override
    protected String getRightString() {
        if (mFragmentName.equals(ShanghuFragment.class.getName())) {
            return mContext.getResources().getString(R.string.left_account);
        }
        return mContext.getResources().getString(R.string.apply_close_account);
    }


}
