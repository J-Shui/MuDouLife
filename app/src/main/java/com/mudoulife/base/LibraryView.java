package com.mudoulife.base;

import android.support.annotation.UiThread;

import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonLibraryLeftItem;
import com.mudoulife.data.net.response.LibraryBaseData;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2019/1/12
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.base
 * project : MuDouLife
 */
public interface LibraryView<M> extends BaseView {
    @UiThread
    public void showContent(M data);
    @UiThread
    public void showEmpty();

    void showLeftItem(JsonBase<List<JsonLibraryLeftItem>> resultData);

    void showDetails(JsonBase<LibraryBaseData> jsonBase);
}
