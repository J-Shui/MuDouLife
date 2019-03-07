package com.mudoulife.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.mudoulife.common.Globals;

/**
 * Created by mingjun on 16/8/31.
 */
public class AppUtil {

    public static String getVersionName(Context context) {
        String versionName = "1.0";

        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }
    /*
     * 获取设备分辨率
     */
    public static void getScreenResolution(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //wm.getDefaultDisplay().getMetrics(dm);
        //wm.getDefaultDisplay().getRealMetrics(dm);
        APICompatibility.getRealMetrics(wm.getDefaultDisplay(), dm);
        Globals.gScreenWidth = dm.widthPixels;
        Globals.gScreenHeight = dm.heightPixels;

        if (Globals.gScreenWidth >= Globals.gScreenHeight) {
                Globals.gScreenScale = Globals.gScreenWidth / Globals.DESIGNED_SCREEN_LAND_WIDTH;
        } else if (Globals.gScreenWidth < Globals.gScreenHeight) {
            Globals.gScreenScale = Globals.gScreenWidth / Globals.DESIGNED_SCREEN_PORT_WIDTH;
        }
        dm = null;
    }

}
