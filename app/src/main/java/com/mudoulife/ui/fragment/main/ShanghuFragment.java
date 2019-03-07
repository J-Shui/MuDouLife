package com.mudoulife.ui.fragment.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mudoulife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShanghuFragment extends BaseContentFragment {


    public ShanghuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setOrderLeftView() {
        setVisibility(View.GONE,mLeftPriceContainer);
        mLeftOrderTv.setText(getResources().getString(R.string.left_order));
        mLeftLookTv.setText(getResources().getString(R.string.look));
        mLeftCompleteTv.setText(getResources().getString(R.string.delivery));
        mLeftAccountTv.setText(getResources().getString(R.string.left_account));
    }

    @Override
    protected void setAfterSaleLeftView() {
        setVisibility(View.VISIBLE,mLeftPriceContainer);
        setDefaultAfterSaleLeftView();
    }

    @Override
    protected LinearLayout[] getContainers() {
        return new LinearLayout[]{mLeftAccountContainer};
    }

    @Override
    protected String getTopMiddleName() {
        return getResources().getString(R.string.after_sale);
    }

    @Override
    protected String getFragmentName() {
        return ShanghuFragment.class.getName();
    }

}
