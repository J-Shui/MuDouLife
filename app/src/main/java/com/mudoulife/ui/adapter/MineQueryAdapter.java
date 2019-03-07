package com.mudoulife.ui.adapter;

import android.content.Context;

import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.ui.adapter.order.OrderBaseAdapter;
import com.mudoulife.ui.adapter.order.OrderOuterAdapter;

import java.util.List;

public class MineQueryAdapter extends OrderOuterAdapter {

    public MineQueryAdapter(Context mContext, List<ResultInfo> mInfos, String mFragmentName) {
        super(mContext, mInfos, mFragmentName);
    }

    @Override
    protected boolean isEnableLeftButton(int status) {
        return false;
    }

    @Override
    protected boolean isShowButtons(int status) {
        return false;
    }

    @Override
    protected boolean isQuery() {
        return false;
    }

    @Override
    protected boolean isShowLeftBtn() {
        return false;
    }

    @Override
    protected String getLeftString() {
        return null;
    }

    @Override
    protected String getRightString() {
        return null;
    }

}
