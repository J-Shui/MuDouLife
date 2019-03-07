package com.mudoulife.ui.activity.library;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonLibraryLeftItem;
import com.mudoulife.data.net.response.LibraryBaseData;
import com.mudoulife.data.net.response.LibrarySelectedBaseData;
import com.mudoulife.ui.activity.LibraryBaseActivity;
import com.mudoulife.ui.adapter.library.LibraryWorkerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LibraryWorkerActivity extends LibraryBaseActivity implements View.OnClickListener {
    private static final String TAG = "LibraryWorkerActivity";
    private SparseArray<List<LibraryBaseData>> mMaterialDatas;
    private SparseArray<List<LibraryBaseData>> mServiceDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadLeftItemData() {
        mPresenter.getServiceOrGoodsClass(Globals.ROLE_WORKER_NAME, mTopCurrent);
    }

    @Override
    protected void loadOnlineContentData() {
        Log.d(TAG, "loadOnlineContentData: mTopCurrent = " + mTopCurrent);
        Log.d(TAG, "loadOnlineContentData: mCurrentLeftClassId = " + mCurrentLeftClassId);
        mPresenter.getItem(Globals.ROLE_WORKER_NAME, mTopCurrent, mCurrentLeftClassId);
    }

    protected void onLeftChanged(int index) {
        if (mTopCurrent == TOP_SERVICE) {
            if (mLeftServiceCurrent != index) mLeftServiceCurrent = index;
            mServiceCurrentLeftClassId = mLeftItemInfos.get(index).getId();
            mCurrentLeftClassId = mLeftItemInfos.get(index).getId();
        } else {
            if (mLeftMaterialCurrent != index) mLeftMaterialCurrent = index;
            mMaterailCurrentLeftClassId = mLeftItemInfos.get(index).getId();
            mCurrentLeftClassId = mLeftItemInfos.get(index).getId();
        }
    }

    @Override
    protected void loadLocalContentData() {
        if (mTopCurrent == TOP_SERVICE) {
            mDatas.clear();
            int id = mLeftItemInfos.get(mLeftServiceCurrent).getId();
            List<LibraryBaseData> dataList = mServiceDatas.get(id);
            mDatas.addAll(dataList);
            mAdapter.setDatas(mDatas);
        } else {
            mDatas.clear();
            int id = mLeftItemInfos.get(mLeftMaterialCurrent).getId();
            List<LibraryBaseData> dataList = mMaterialDatas.get(id);
            mDatas.addAll(dataList);
            mAdapter.setDatas(mDatas);
        }
    }

    @Override
    protected void init() {
        super.init();
        mServiceDatas = new SparseArray<>();
        mMaterialDatas = new SparseArray<>();

        mTopContainer.setVisibility(View.VISIBLE);
        mTopCurrent = TOP_SERVICE;
        setTopSelectedState(SELECTED_STATE, mTopService);
        mAdapter = new LibraryWorkerAdapter(mDatas, this);
        mTopService.setOnClickListener(this);
        mTopMaterial.setOnClickListener(this);
    }

    @Override
    protected Button[] getLeftButtons() {
        Button[] buttons = {mLeftItem1, mLeftItem2, mLeftItem3, mLeftItem4,
                mLeftItem5, mLeftItem6, mLeftItem7, mLeftItem8, mLeftItem9,
                mLeftItem10, mLeftItem11, mLeftItem12,
                mLeftItem13, mLeftItem14, mLeftItem15, mLeftItem16};
        return buttons;
    }


    @Override
    protected String[] getLeftTexts() {
        if (mTopCurrent == TOP_SERVICE) {
            return new String[]{getResources().getString(R.string.library_worker_service_item_1),
                    getResources().getString(R.string.library_worker_service_item_2),
                    getResources().getString(R.string.library_worker_service_item_3),
                    getResources().getString(R.string.library_worker_service_item_4),
                    getResources().getString(R.string.library_worker_service_item_5),
                    getResources().getString(R.string.library_worker_service_item_6),
                    getResources().getString(R.string.library_worker_service_item_7),
                    getResources().getString(R.string.library_worker_service_item_8)};
        } else {
            return new String[]{getResources().getString(R.string.library_worker_material_item_1),
                    getResources().getString(R.string.library_worker_material_item_2),
                    getResources().getString(R.string.library_worker_material_item_3),
                    getResources().getString(R.string.library_worker_material_item_4),
                    getResources().getString(R.string.library_worker_material_item_5),
                    getResources().getString(R.string.library_worker_material_item_6),
                    getResources().getString(R.string.library_worker_material_item_7),
                    getResources().getString(R.string.library_worker_material_item_8)};
        }
    }

    @Override
    protected void initWindowAdapter() {
//        mWindowAdapter = new LibraryWorkerAdapter(mSelectedDatas,this);
    }

    @Override
    protected int getType() {
        return Globals.LIBRARY_WORKER_SERVICE_TYPE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.library_top_service:
                if (mTopCurrent != TOP_SERVICE) {
                    setTopSelectedState(SELECTED_STATE, mTopService);
                    setTopSelectedState(DEFAULT_STATE, mTopMaterial);
//                    setVisibility(View.GONE,getLeftButtons());
//                    setLeftSelectedState(DEFAULT_STATE, getLeftButtons());
                    mTopCurrent = TOP_SERVICE;
//                    mPresenter.getServiceOrGoodsClass(Globals.ROLE_WORKER_NAME,mTopCurrent);
                    mLeftCurrent = mLeftServiceCurrent;
                    loadLeftItemData();
//                    mMaterialDatas.clear();
//                    mMaterialDatas.put(mCurrentLeftClassId,mDatas);
//                    mDatas.clear();
//                    mDatas.addAll(mServiceDatas.get(mCurrentLeftClassId));
//                    mAdapter.setDatas(mDatas);
//                    mLeftCurrent =
                }
                break;
            case R.id.library_top_material:
                if (mTopCurrent != TOP_MATERIAL) {
                    setTopSelectedState(SELECTED_STATE, mTopMaterial);
                    setTopSelectedState(DEFAULT_STATE, mTopService);
                    mTopCurrent = TOP_MATERIAL;
                    mLeftCurrent = mLeftMaterialCurrent;
//                    setVisibility(View.GONE,getLeftButtons());
//                    setLeftSelectedState(DEFAULT_STATE, getLeftButtons());
//                    mPresenter.getServiceOrGoodsClass(Globals.ROLE_WORKER_NAME,mTopCurrent);
//                    initLeftItem();
//                    mLeftCurrent =
//                    if (mIsFirstClick) {
                    loadLeftItemData();
//                    } else {
//                    mServiceDatas.clear();
//                    mServiceDatas.put(mCurrentLeftClassId,mDatas);
//                    mDatas.clear();
//                    mDatas.addAll(mMaterialDatas.get(mCurrentLeftClassId));
//                    mAdapter.setDatas(mDatas);
//                    }

                }
                break;
        }

    }


    @Override
    public void showContent(JsonBase<List<LibraryBaseData>> data) {
        List<LibraryBaseData> dataList = (List<LibraryBaseData>) data.getData();
        if (dataList != null) {
            mDatas.clear();
            if (mTopCurrent == TOP_SERVICE) {
                List<LibraryBaseData> list = mServiceDatas.get(mCurrentLeftClassId);
                if (list != null && list.size() == dataList.size()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId() == dataList.get(i).getId()) {
                            dataList.get(i).setSelected(list.get(i).isSelected());
                        }
                    }
                }
                mServiceDatas.put(mCurrentLeftClassId, dataList);
            } else {
                List<LibraryBaseData> list = mMaterialDatas.get(mCurrentLeftClassId);
//                Log.d(TAG, "showContent: size = " + list.size());
                if (list != null && list.size() == dataList.size()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId() == dataList.get(i).getId()) {
                            dataList.get(i).setSelected(list.get(i).isSelected());
                        }
                    }
                }
                mMaterialDatas.put(mCurrentLeftClassId, dataList);
            }
            mDatas.addAll(dataList);
            mAdapter.setDatas(mDatas);
        }
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
        for (int i = 0; i < dataList.size(); i++) {
            buttons[i] = getLeftButtons()[i];
            JsonLibraryLeftItem leftItem = dataList.get(i);
            buttons[i].setText(leftItem.getName());
        }
        setVisibility(View.VISIBLE, buttons);
//        initLeftItem(buttons);
        setLeftSelectedState(SELECTED_STATE, getLeftButtons()[mLeftCurrent]);

        mCurrentLeftClassName = mLeftItemInfos.get(0).getName();
        Log.d(TAG, "showLeftItem: mIsFirstClick = " + mIsFirstClick);
        if (mIsFirstClick) {
            if (mTopCurrent == TOP_SERVICE) {
                mServiceCurrentLeftClassId = mLeftItemInfos.get(0).getId();
            } else {
                mMaterailCurrentLeftClassId = mLeftItemInfos.get(0).getId();
                mIsFirstClick = false;
            }
            mCurrentLeftClassId = mLeftItemInfos.get(0).getId();
            loadOnlineContentData();

        } else {
            if (mTopCurrent == TOP_SERVICE) {
                mCurrentLeftClassId = mServiceCurrentLeftClassId;
            } else mCurrentLeftClassId = mMaterailCurrentLeftClassId;
            Log.d(TAG, "mCurrentLeftClassId = " + mCurrentLeftClassId);
            loadLocalContentData();
        }
    }

    @Override
    protected void onDataChanged() {

    }

    @Override
    protected void onItemSelected(int position) {
        Log.d(TAG, "onItemRangeChanged: positionStart = " + position);
        Log.d(TAG, "onItemRangeChanged: mTotalPrice = " + mTotalPrice);
//        LibraryBaseData data = mDatas.get(position);
//        Log.d(TAG, "onItemRangeChanged: data = " + data);
        double total = 0;

        mSelectedDatas.clear();
        List<LibraryBaseData> dataList = new ArrayList<>();
        dataList.clear();
        for (int i = 0; i < mServiceDatas.size(); i++) {
            List<LibraryBaseData> serviceDatas = mServiceDatas.valueAt(i);
            dataList.addAll(serviceDatas);
        }
        for (int i = 0; i < mMaterialDatas.size(); i++) {
            List<LibraryBaseData> materialDatas = mMaterialDatas.valueAt(i);
            dataList.addAll(materialDatas);
        }
//        dataList.addAll(mMaterialDatas);
        Log.d(TAG, "onItemSelected: dataList = " + dataList);
        for (int i = 0; i < dataList.size(); i++) {
            LibraryBaseData data = dataList.get(i);
            if (data.isSelected()) {
//                LibraryBaseData data1 = new LibraryBaseData();
//                data1.setFloor(data.getFloor());
//                data1.setName(data.getName());
//                data1.setNumber(data.getNumber());
//                data1.setPrice(data.getPrice());
////                data1.setSelectedPosition(i);
//                data1.setClassId(data.getClassId());
//                data1.setType(data.getType());
//                data1.setCreateTime(data.getCreateTime());
//                data1.setId(data.getId());
                total += data.getPrice() * data.getNumber();
                data.setClassName(mCurrentLeftClassName);
                if (total > 0)
                    mSelectedDatas.add(data);
            }
        }
//        LibraryBaseData data = mDatas.get(position);
//        if (data.isSelected()) {
//            LibrarySelectedBaseData data1 = new LibrarySelectedBaseData();
//            data1.setFloor(data.getFloor());
//            data1.setName(data.getName());
//            data1.setNumber(data.getNumber());
//            data1.setPrice(data.getPrice());
////            data1.setSelectedPosition(i);
//            data1.setClassId(data.getClassId());
//            data1.setType(data.getType());
//            data1.setCreateTime(data.getCreateTime());
//            data1.setId(data.getId());
//            total += data.getPrice() * data.getNumber();
//            mSelectedDatas.add(data1);
//        }
        mTotalTv.setText(getResources().getString(R.string.total) + total);
        Log.d(TAG, "onItemRangeChanged: mSelectedDatas = " + mSelectedDatas);
    }

}
