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
import com.mudoulife.ui.adapter.library.LibraryCarryAdapter;

import java.util.List;

public class LibraryCarryActivity extends LibraryBaseActivity {
    private static final String TAG = "LibraryCarryActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        super.init();
        mLabelItem1.setText(getResources().getString(R.string.material_name));
        mLabelItem2.setText(getResources().getString(R.string.per_price));
        mLabelItem3.setText(getResources().getString(R.string.number));
        mLabelItem4.setText(getResources().getString(R.string.floor));
        mLabelItem4.setVisibility(View.VISIBLE);
        mTopContainer.setVisibility(View.GONE);
        mAdapter = new LibraryCarryAdapter(mDatas,this);
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

    protected void onItemSelected(int position) {
        Log.d(TAG, "onItemRangeChanged: positionStart = " + position);
        double total = 0;
        mSelectedDatas.clear();
//        List<LibraryBaseData> dataList = new ArrayList<>();
//        dataList.clear();
////        for (int i = 0; i < mAllDatas.size(); i++) {
////            List<LibraryBaseData> serviceDatas = mAllDatas.valueAt(i);
////            dataList.addAll(serviceDatas);
////        }
        for (int i = 0; i < mDatas.size(); i++) {
            LibraryBaseData data = mDatas.get(i);
            if (data.isSelected()) {
//                LibraryBaseData data1 = new LibraryBaseData();
//                data1.setFloor(data.getFloor());
//                data1.setName(data.getName());
//                data1.setNumber(data.getNumber());
//                data1.setPrice(data.getPrice());
////                data1.setSelectedPosition(i);
                data.setClassId(mCurrentLeftClassId);
                data.setClassName(mCurrentLeftClassName);
                total += data.getPrice() * data.getNumber() * data.getFloor();
                if (total > 0){
                    data.setTotal(total);
                    mSelectedDatas.add(data);
                }
            }
        }
        mTotalTv.setText(getResources().getString(R.string.total) + total);
        Log.d(TAG, "onItemRangeChanged: mSelectedDatas = " + mSelectedDatas);
    }

    @Override
    protected void initPopView(View view) {
        super.initPopView(view);
        mWindowLabel1Tv.setText(getResources().getString(R.string.material_name));
        mWindowLabel2Tv.setText(getResources().getString(R.string.per_price));
        mWindowLabel3Tv.setText(getResources().getString(R.string.number));
        mWindowLabel4Tv.setText(getResources().getString(R.string.floor));
        mWindowLabel4Tv.setVisibility(View.VISIBLE);
    }
    @Override
    protected void loadLeftItemData() {
        mPresenter.carryType(mOrderId);
    }

    @Override
    protected void loadOnlineContentData() {
        mPresenter.carryTypeDetail(mCurrentLeftClassId,mOrderId);
    }

    @Override
    protected Button[] getLeftButtons() {
        return new Button[]{mLeftItem1, mLeftItem2};
    }

    @Override
    protected String[] getLeftTexts() {
        return new String[]{getResources().getString(R.string.library_carry_item_1),
                getResources().getString(R.string.library_carry_item_2)};
    }

    @Override
    protected void initWindowAdapter() {

    }


    @Override
    protected int getType() {
        return Globals.LIBRARY_CARRY_TYPE;
    }

}
