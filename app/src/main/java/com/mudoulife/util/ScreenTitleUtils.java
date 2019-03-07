package com.mudoulife.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/10.
 * 改变通知栏颜色工具类
 */

public class ScreenTitleUtils {
    private static SystemBarTintManager mTintManager;
    private static Context mContext;
    private static final String TAG = "ScreenTitleUtils";
    public static Window window;



    public static void setScreenTitle2(Activity context, int colorId) {
        if (mTintManager != null)
            mTintManager = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(window == null){
                    window = context.getWindow();
                }
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(context.getResources().getColor(colorId));
                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setScreenTitle(Activity context, int colorId, boolean isSecondUse, boolean isTransparent) {
        Log.d(TAG, "context = " + context);
        Log.d(TAG, "mTintManager = " + mTintManager);
        mContext = context;
        if (context == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true, context);
            if (isSecondUse) {
                if (mTintManager == null)
                    mTintManager = new SystemBarTintManager(context);
            } else {
                if (mTintManager != null)
                    mTintManager = null;
                mTintManager = new SystemBarTintManager(context);
            }
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setStatusBarTintResource(colorId);
            Log.d(TAG, "mTintManager = " + mTintManager);
            if (isTransparent)
                mTintManager.setStatusBarAlpha(0);
            else mTintManager.setStatusBarAlpha(1);
        }
    }


    private static void setTranslucentStatus(boolean on, Activity activity) {
        if (activity == null) {
            return;
        }
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public static void recycle() {
        if (mTintManager != null) {
            mTintManager = null;
        }
        Log.d(TAG, "recycle == " + mTintManager);
        if (mContext != null) mContext = null;
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 修改小米手机状态栏字体的颜色
     *
     * @param activity
     * @param darkmode
     * @return
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
