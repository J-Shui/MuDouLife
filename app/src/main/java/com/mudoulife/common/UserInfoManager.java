package com.mudoulife.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/18
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.common
 * project : MuDouLife
 */
public class UserInfoManager {
    private static final String TAG = "UserInfoManager";

    public static boolean gIsUserLogin = false;

    //user id
    public static long gUserId = -1;
    //token
    public static String gUuid = null;
    //user type
    public static int gUserType = -1;
    //user password
    public static String gUserPassword = "";
    //user password
    public static String gUserName = "";
    // phone number
    public static String gPhoneNum = null;

    public static String gToken = "";

    //Version Code
    public static int gVersionCode = -1;

    //Version Name
    public static String gVersionName = "";

    private static UserInfoManager mInstance = null;

    private UserInfoManager(){}

    public static UserInfoManager getInstance(Context context) {
        if(mInstance == null)
            mInstance = new UserInfoManager();
        return mInstance;
    }

    public void saveUserInfo2Preferences(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("com.mudoulife.common.account_sp", 0).edit();
        editor.putInt("mudouUserType", gUserType);
//        editor.putString("lekanUserName", userName);
        editor.putBoolean("mudouUserStatus", gIsUserLogin);
        editor.putString("mudouPassword", gUserPassword);
        editor.putString("mudouUserName", gUserName);
        editor.putString("phoneNum", gPhoneNum);
        editor.putString("uuid", gUuid);
        editor.putString("mudouToken", gToken);
        editor.apply();
    }

    /*
     * 获取保存的用户信息
     */
    public void getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences(
                "com.mudoulife.common.account_sp", Context.MODE_PRIVATE);
//        Globals.leKanUserId= sp.getLong("leKanUserId", 1040613);//测试环境viennetta账号id
        gUserType = sp.getInt("mudouUserType", 0);// 用户类型
        gIsUserLogin = sp.getBoolean("mudouUserStatus", false);
//        Globals.lekanEndTime = sp.getString("lekanEndTime", null);// 服务结束时间
        gUserPassword = sp.getString("mudouPassword", null);// 用户密码
        gUserName = sp.getString("mudouUserName", null);// 用户昵称
        gPhoneNum = sp.getString("phoneNum", null);
        gUuid = sp.getString("uuid", null);
        gToken = sp.getString("mudouToken",null);
        Log.d(TAG, "getUserInfo: uid="+gUserId+", utype="+gUserType);
    }
}
