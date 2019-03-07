package com.mudoulife.ui.adapter.library;

import android.content.Context;

import com.mudoulife.data.net.response.LibraryBaseData;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.adapter
 * project : MuDouLife
 */
public class LibrarySubscriptionAdapter extends LibraryBaseAdapter<LibraryBaseData> {
    private static final String STRING_PRICE = "0.5元/张/层";
    public LibrarySubscriptionAdapter(List<LibraryBaseData> data, Context context) {
        super(data, context);
    }

    @Override
    protected void bindView(ViewHolder viewHolder,LibraryBaseData data) {

    }


    @Override
    protected String getPrice(double price) {
        return STRING_PRICE;
    }

    @Override
    protected void init(ViewHolder holder) {
    }
}
