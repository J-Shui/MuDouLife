package com.mudoulife.data.net.datasource;

import android.util.Log;

import com.mudoulife.data.api.LibraryDataApi;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonLibraryLeftItem;
import com.mudoulife.data.net.response.LibraryBaseData;
import com.mudoulife.data.net.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net
 * project : MuDouLife
 */
public class LibraryDataSource implements LibraryDataApi {
    private static final String TAG = "LibraryDataSource";
    private LibraryService mService;


    @Inject
    public LibraryDataSource(LibraryService mService) {
        this.mService = mService;
    }

//    @Override
//    public Single<ArrayList<LibraryBaseData>> getLibraryDatas(final int topCurrent) {
//        return  Single.create(new SingleOnSubscribe<ArrayList<LibraryBaseData>>() {
//            @Override
//            public void subscribe(SingleEmitter<ArrayList<LibraryBaseData>> emitter) throws Exception {
//                if (topCurrent == 0){
//                    emitter.onSuccess(getServiceDatas());
//                }else{
//                    emitter.onSuccess(getMaterialDatas());
//                }
//
//            }
//        });
//    }
    private ArrayList<LibraryBaseData> getServiceDatas() {
        ArrayList<LibraryBaseData> datas = new ArrayList<>();
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        for (int i = 0; i < 10; i++) {
            LibraryBaseData data = new LibraryBaseData();
            data.setName("马桶维修");
            data.setPrice(50 + i * 2);
            data.setNumber(i);
            datas.add(data);
        }
        Log.d("LibraryDataSource","getServiceDatas = " + datas);
        return datas;
    }
    private ArrayList<LibraryBaseData> getMaterialDatas() {
        ArrayList<LibraryBaseData> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LibraryBaseData data = new LibraryBaseData();
            data.setName("合页");
            data.setPrice(50 + i * 2);
            data.setNumber(i);
            datas.add(data);
        }
        Log.d("LibraryDataSource","getServiceDatas = " + datas);
        return datas;
    }

    @Override
    public Single<JsonBase<List<JsonLibraryLeftItem>>> getServiceOrGoodsClass(String role, int type) {
        Log.d(TAG, "getServiceOrGoodsClass: ");
        return mService.getServiceOrGoodsClass(role,type);
    }

    @Override
    public Single<JsonBase<List<LibraryBaseData>>> getItem(String role, int type, int classId) {
        Log.d(TAG, "getItem: ");
        return mService.getItem(role,type,classId);
    }

    @Override
    public Single<JsonBase<List<JsonLibraryLeftItem>>> carryType(int orderId) {
        Log.d(TAG, "carryType: ");
        return mService.carryType(orderId);
    }

    @Override
    public Single<JsonBase<List<LibraryBaseData>>> carryTypeDetail(int id, int orderId) {
        Log.d(TAG, "carryTypeDetail: ");
        return mService.carryTypeDetail(id, orderId);
    }

    @Override
    public Single<JsonBase<List<JsonLibraryLeftItem>>> expressType(int orderId) {
        Log.d(TAG, "expressType: ");
        return mService.expressType(orderId);
    }

    @Override
    public Single<JsonBase<LibraryBaseData>> expressTypeDetail(int id) {
        Log.d(TAG, "expressTypeDetail: ");
        return mService.expressTypeDetail(id);
    }
}
