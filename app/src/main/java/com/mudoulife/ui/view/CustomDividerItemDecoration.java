package com.mudoulife.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/28
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.view
 * project : MuDouLife
 */
public class CustomDividerItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private Context mContext;
    private int mOrientation;
    private Drawable mDivider;
    public CustomDividerItemDecoration(Context context){
        this(context,VERTICAL);
    }
    public CustomDividerItemDecoration(Context context,int orientation){
        mContext = context;
        mOrientation = orientation;
    }

    public int getOrientation() {
        return mOrientation;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

}
