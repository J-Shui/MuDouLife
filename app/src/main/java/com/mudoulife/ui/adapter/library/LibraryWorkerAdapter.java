package com.mudoulife.ui.adapter.library;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mudoulife.data.net.response.LibraryBaseData;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.adapter
 * project : MuDouLife
 */
public class LibraryWorkerAdapter extends LibraryBaseAdapter<LibraryBaseData> {
    private static final String STRING_PRICE = "元";
    public LibraryWorkerAdapter(List<LibraryBaseData> data, Context context) {
        super(data, context);
    }

    @Override
    protected void bindView(ViewHolder viewHolder,LibraryBaseData data) {
        initWorkerView(viewHolder, data);
    }

    @Override
    protected String getPrice(double price) {
        return price + STRING_PRICE;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
    }

    @Override
    protected void init(ViewHolder holder) {
//        holder.setFloorVisible();
    }
}
