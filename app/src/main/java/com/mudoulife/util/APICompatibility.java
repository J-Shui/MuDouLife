package com.mudoulife.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/11/15.
 */

public  final class APICompatibility {

    /**
     * Sets the alpha value that should be applied to the image.
     *
     * @param imageView
     * @param alpha
     */
    public static void setImageAlpha(ImageView imageView, int alpha) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            imageView.setImageAlpha(alpha);
        else
            imageView.setAlpha(alpha);
    }

    public static void setImageBackground(View view, Drawable drawable) {
        if(view != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                view.setBackground(drawable);
            else
                view.setBackgroundDrawable(drawable);
        }
    }

    public static Drawable getThumbDrawable(SeekBar seekbar) {
        Drawable thumb = null;
        if(seekbar != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                seekbar.getThumb();
            else {
                try {
                    Class cls = seekbar.getClass();
                    Object obj = cls.getConstructor().newInstance();
                    Field thumbField = cls.getDeclaredField("mThumb");
                    thumbField.setAccessible(true);
                    thumb = (Drawable)thumbField.get(seekbar);
                } catch(Exception e) {
                    Log.e("APICompatibility", "get reflection field showError:"+e);
                }
            }
        }
        return thumb;
    }

    public static void getRealMetrics(Display display, DisplayMetrics dm) {
        if(display != null && dm != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                DisplayMetrics dm1 = new DisplayMetrics();
                DisplayMetrics dm2 = new DisplayMetrics();
                display.getRealMetrics(dm1);
                display.getMetrics(dm2);
                dm.widthPixels = Math.max(dm1.widthPixels, dm2.widthPixels);
                dm.heightPixels = Math.max(dm1.heightPixels, dm2.heightPixels);
                dm1 = null;
                dm2 = null;
            } else
                display.getMetrics(dm);
        }
    }

    public static void setScrollX(View view, int scrollX) {
        if(view != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                view.setScrollX(scrollX);
            else {
                int scrollY = view.getScrollY();
                view.scrollTo(scrollX, scrollY);
            }
        }
    }
}
