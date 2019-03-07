package com.mudoulife.ui.activity.library;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonLibraryLeftItem;
import com.mudoulife.data.net.response.LibraryBaseData;
import com.mudoulife.ui.activity.LibraryBaseActivity;
import com.mudoulife.ui.adapter.library.LibraryExpressAdapter;

import java.util.ArrayList;
import java.util.List;

public class LibraryExpressActivity extends LibraryBaseActivity {
    private static final String TAG = "LibraryExpressActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void loadLeftItemData() {
        mPresenter.expressType(mOrderId);
    }

    @Override
    protected void loadOnlineContentData() {
        Log.d(TAG, "loadOnlineContentData: mCurrentLeftClassId = " + mCurrentLeftClassId);
        mPresenter.expressTypeDetail(mCurrentLeftClassId);
    }


    @Override
    protected void init() {
        super.init();
        mLabelItem1.setText(getResources().getString(R.string.starting_price));
        mLabelItem2.setText(getResources().getString(R.string.out_of_price));
        mLabelItem3.setText(getResources().getString(R.string.number));
        mLabelItem4.setVisibility(View.GONE);
        mTopContainer.setVisibility(View.GONE);
        mAdapter = new LibraryExpressAdapter(mDatas,this);
    }

    @Override
    protected Button[] getLeftButtons() {
        return new Button[]{mLeftItem1, mLeftItem2, mLeftItem3, mLeftItem4};
    }

    @Override
    protected void loadLocalContentData() {
//        mDatas.clear();
//        int id = mLeftItemInfos.get(mLeftCurrent).getId();
//        List<LibraryBaseData> dataList = mAllDatas.get(id);
//        mDatas.addAll(dataList);
//        mAdapter.setDatas(mDatas);
    }

    @Override
    protected String[] getLeftTexts() {
        return new String[]{getResources().getString(R.string.library_express_item_1),
                getResources().getString(R.string.library_express_item_2),
                getResources().getString(R.string.library_express_item_3),
                getResources().getString(R.string.library_express_item_4)};
    }

    @Override
    protected void initPopView(View view) {
        super.initPopView(view);
        mWindowLabel1Tv.setText(getResources().getString(R.string.car_model));
        mWindowLabel2Tv.setText(getResources().getString(R.string.starting_price));
        mWindowLabel3Tv.setText(getResources().getString(R.string.out_of_range));
        mWindowLabel4Tv.setText(getResources().getString(R.string.out_of_km));
        mWindowLabel4Tv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initWindowAdapter() {

    }

    @Override
    protected int getType() {
        return Globals.LIBRARY_EXPRESS_TYPE;
    }

    @Override
    public void showLeftItem(JsonBase<List<JsonLibraryLeftItem>> resultData) {
        List<JsonLibraryLeftItem> dataList = (List<JsonLibraryLeftItem>) resultData.getData();
        mLeftItemInfos.clear();
        mLeftItemInfos.addAll(dataList);
        Log.d(TAG, "showLeftItem: dataList = " + dataList);
        Button[] buttons = new Button[dataList.size()];
        setVisibility(View.GONE, getLeftButtons());
        setLeftSelectedState(DEFAULT_STATE, getLeftButtons());
        int index = 0;
        for (int i = 0; i < dataList.size(); i++) {
            buttons[i] = getLeftButtons()[i];
            JsonLibraryLeftItem leftItem = dataList.get(i);
            buttons[i].setText(leftItem.getName());
            boolean canClick = leftItem.isCanClick();
            if (canClick) index = i;
            buttons[i].setEnabled(canClick);
        }
        setVisibility(View.VISIBLE, buttons);
//        initLeftItem(buttons);
        setLeftSelectedState(SELECTED_STATE, getLeftButtons()[index]);

        mCurrentLeftClassName = mLeftItemInfos.get(0).getName();
        Log.d(TAG, "showLeftItem: mIsFirstClick = " + mIsFirstClick);
        mCurrentLeftClassId = mLeftItemInfos.get(0).getId();
        loadOnlineContentData();
//        if (mIsFirstClick) {
//            if (mTopCurrent == TOP_SERVICE) {
//                mServiceCurrentLeftClassId = mLeftItemInfos.get(0).getId();
//            } else {
//                mMaterailCurrentLeftClassId = mLeftItemInfos.get(0).getId();
//                mIsFirstClick = false;
//            }
//
//
//        } else {
//            if (mTopCurrent == TOP_SERVICE) {
//                mCurrentLeftClassId = mServiceCurrentLeftClassId;
//            } else mCurrentLeftClassId = mMaterailCurrentLeftClassId;
//            Log.d(TAG, "mCurrentLeftClassId = " + mCurrentLeftClassId);
//            loadLocalContentData();
//        }
    }

    @Override
    public void showDetails(JsonBase<LibraryBaseData> jsonBase) {
        Log.d(TAG, "showDetails: jsonbase = " + jsonBase);
        LibraryBaseData data = (LibraryBaseData) jsonBase.getData();
        int id = data.getId();
        if (data != null){
            mDatas.clear();
            mDatas.add(data);
            mAdapter.setDatas(mDatas);
        }

    }
    protected void onItemSelected(int position) {
        Log.d(TAG, "onItemRangeChanged: positionStart = " + position);
        double total = 0;
        mSelectedDatas.clear();
//        List<LibraryBaseData> dataList = new ArrayList<>();
//        dataList.clear();
//        Log.d(TAG, "onItemSelected: mAllDatas = " + mAllDatas);
//        for (int i = 0; i < mAllDatas.size(); i++) {
//            List<LibraryBaseData> serviceDatas = mAllDatas.valueAt(i);
//            dataList.addAll(serviceDatas);
//        }
        for (int i = 0; i < mDatas.size(); i++) {
            LibraryBaseData data = mDatas.get(i);
            if (data.isSelected()) {
                data.setClassName(mCurrentLeftClassName);
                data.setClassId(mCurrentLeftClassId);
                total += data.getStartPrice()  + data.getExceedingPrice() * data.getNumber();
                if (total > 0){
                    mSelectedDatas.add(data);
                }
            }
        }
        mTotalTv.setText(getResources().getString(R.string.total) + total);
        Log.d(TAG, "onItemRangeChanged: mSelectedDatas = " + mSelectedDatas);
    }
}
