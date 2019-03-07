package com.mudoulife.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/10
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.util
 * project : MuDouLife
 */
public class Utils {
//    public static float dp2px(float dp) {
//        final float scale = Resources.getSystem().getDisplayMetrics().density;
//        return dp * scale + 0.5f;
//    }
    public static float dp2px(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,metrics);
    }
    /**
     * 获取底部虚拟按键的高度
     *
     * @param context
     * @return
     */
    public static int getBottomBarHeight(Context context) {
        Resources resources = context.getApplicationContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
