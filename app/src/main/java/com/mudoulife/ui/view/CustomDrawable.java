package com.mudoulife.ui.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/28
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.view
 * project : MuDouLife
 */
public class CustomDrawable extends Drawable {
    Paint paint  = new Paint(Paint.ANTI_ALIAS_FLAG);
    {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(101);
    }
    @Override
    public void draw( Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawRect(bounds,paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public int getAlpha() {
        return paint.getAlpha();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return paint.getAlpha() == 0 ? PixelFormat.TRANSPARENT :
                paint.getAlpha() == 0xff ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }
}
