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
public class CarryFragment extends BaseContentFragment {


    public CarryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        setDefaultOrderLeftView();
    }

    @Override
    protected void setOrderLeftView() {
        mLeftOrderTv.setText(getResources().getString(R.string.left_order));
        mLeftLookTv.setText(getResources().getString(R.string.look));
        setDefaultOrderLeftView();
    }

    @Override
    protected void setAfterSaleLeftView() {
        mLeftPriceTv.setText(getResources().getString(R.string.complaint));
        mLeftServiceTv.setText(getResources().getString(R.string.look));
        setLeftCurrentItem4Price();
    }

    @Override
    protected LinearLayout[] getContainers() {
        return new LinearLayout[]{mLeftAccountContainer,mLeftOrderContainer};
    }

    @Override
    protected String getTopMiddleName() {
        return getResources().getString(R.string.complaint);
    }


    @Override
    protected String getFragmentName() {
        return CarryFragment.class.getName();
    }


}
