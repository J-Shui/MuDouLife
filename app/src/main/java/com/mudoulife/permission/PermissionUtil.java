package com.mudoulife.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/27
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.permission
 * project : MuDouLife
 * 动态权限工具类
 */
public class PermissionUtil {
    /**
     * 判断是否有某个权限
     * @param context
     * @param permission
     * @return
     */
    public static boolean hasPermission(Context context,String permission){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    /**
     * 弹出对话框请求权限
     * @param activity
     * @param permissions
     * @param requestCode
     */
    public static void requestPermissions(Activity activity,String[] permissions,int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            activity.requestPermissions(permissions,requestCode);
        }
    }
    public static String[] getDeniedPermissions(Context context,String[] permissions){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ArrayList<String> deniedPermissions = new ArrayList<>();
            for (String permission : permissions) {
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permission);
                }
            }
            int size = deniedPermissions.size();
            if (size > 0){
                return deniedPermissions.toArray(new String[deniedPermissions.size()]);
            }
        }
        return null;
    }
}
