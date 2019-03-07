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
public class OrderQueryAdapter extends OrderOuterAdapter {

    public OrderQueryAdapter(Context mContext, List<ResultInfo> mInfos, String mFragmentName) {
        super(mContext, mInfos, mFragmentName);
    }

    @Override
    protected boolean isEnableLeftButton(int status) {
        if (mFragmentName.equals(WorkerFragment.class.getName())) {
            if (status == Globals.CRAFTSMAN_RECEIPT){
                return true;
            }
        }else if (mFragmentName.equals(ShanghuFragment.class.getName())) {
            if (status == Globals.ALREADY_PAY){
                return true;
            }
        }
        else if (mFragmentName.equals(ExpressFragment.class.getName())){
            if (status <= Globals.EXPRESS_UPLOAD_PRICE && status > Globals.APPOINT_DELIVERY){
                return true;
            }
        }else if (mFragmentName.equals(CarryFragment.class.getName())){
            if (status <= Globals.EXPRESS_UPLOAD_PRICE && status > Globals.APPOINT_DELIVERY){
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean isEnableRightButton(ResultInfo info) {
        int status = info.getStatus();
        if (mFragmentName.equals(WorkerFragment.class.getName())) {
            if (status == Globals.CS_DISPATCH_CRAFTSMAN){
                return true;
            }
        } else if (mFragmentName.equals(ShanghuFragment.class.getName())) {
            if (status == Globals.ALREADY_PAY){
                return true;
            }
        }
        else if (mFragmentName.equals(ExpressFragment.class.getName())){
            if (status == Globals.APPOINT_DELIVERY && info.getExpressReceipt() == 1){
                return true;
            }
        }else if (mFragmentName.equals(CarryFragment.class.getName())){
            if (status == Globals.APPOINT_DELIVERY && info.getCarryReceipt() == 1){
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean isShowButtons(int status) {
        if (mFragmentName.equals(WorkerFragment.class.getName())) {
            if (status >= Globals.CRAFTSMAN_COMPLETE){
                return false;
            }
        }else {
            if (status >= Globals.COMPLETE){
                return false;
            }
        }

        /*if (mFragmentName.equals(ShanghuFragment.class.getName())) {
            return true;
        }else if (mFragmentName.equals(SubscriptionFragment.class.getName()))
            return true;
        else if (mFragmentName.equals(ExpressFragment.class.getName()))
            if (status == Globals.CRAFTSMAN_COMPLETE){
                return false;
            }
            return true;*/
        return true;
    }

    @Override
    protected boolean isQuery() {
        return true;
    }

    @Override
    protected boolean isShowLeftBtn() {
        if (mFragmentName.equals(SubscriptionFragment.class.getName())
                || mFragmentName.equals(ShanghuFragment.class.getName())){
            return false;
        }
        return true;
    }


    @Override
    protected String getLeftString() {
        return mContext.getResources().getString(R.string.cancel_order);
    }

    @Override
    protected String getRightString() {
        return mContext.getResources().getString(R.string.order_receive);
    }


}
