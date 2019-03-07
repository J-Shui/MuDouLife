package com.mudoulife.data.api;

import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonLibraryLeftItem;
import com.mudoulife.data.net.response.LibraryBaseData;
import com.mudoulife.data.net.response.ResultInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.api
 * project : MuDouLife
 */
public interface LibraryDataApi {
    Single<JsonBase<List<JsonLibraryLeftItem>>> getServiceOrGoodsClass(String role, int type);
    Single<JsonBase<List<LibraryBaseData>>> getItem(String role, int type, int classId);
    Single<JsonBase<List<JsonLibraryLeftItem>>> carryType(int orderId);
    Single<JsonBase<List<LibraryBaseData>>> carryTypeDetail(int id, int orderId);
    Single<JsonBase<List<JsonLibraryLeftItem>>> expressType(int orderId);
    Single<JsonBase<LibraryBaseData>> expressTypeDetail(int id);
}
