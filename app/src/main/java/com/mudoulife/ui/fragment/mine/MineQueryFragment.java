package com.mudoulife.ui.fragment.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mudoulife.R;
import com.mudoulife.base.BaseFragment;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.di.component.MainComponent;
import com.mudoulife.ui.adapter.AfterSaleHandlerAdapter;
import com.mudoulife.ui.adapter.MineQueryAdapter;
import com.mudoulife.ui.adapter.order.OrderOuterAdapter;
import com.mudoulife.ui.fragment.order.OrderBaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineQueryFragment extends OrderBaseFragment {


    public MineQueryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent(MainComponent.class).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadData() {
        mPresenter.paymentView(getRoleName());
    }

    @Override
    protected void initAdapter() {
        mAdapter = new MineQueryAdapter(mContext, mDatas,mMainFragmentName);
    }

    @Override
    protected void onClickRightBtn(int position) {

    }

    @Override
    protected void onClickLeftBtn(int position) {

    }

    @Override
    protected int getNextItem() {
        return -1;
    }

    @Override
    protected void loadPopData() {

    }

    @Override
    protected void initPopView(View view) {

    }

    @Override
    public void showOrderDetails(JsonBase<JsonOrderDetailsData> jsonBase) {

    }

    @Override
    public void onCancelOrder(JsonBase jsonBase) {

    }

    @Override
    public void onConfirmReceipt(JsonBase jsonBase) {

    }
}
