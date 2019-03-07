package com.mudoulife.permission;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/27
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.permission
 * project : MuDouLife
 * 权限请求接口
 */
public interface PermissionInterface {
    /**
     * 可设置请求权限请求码
     * @return
     */
    int getPermissionRequestCode();

    /**
     * 设置需要请求的权限
     * @return
     */
    String[] getPermissions();

    /**
     * 请求权限成功回调
     */
    void requestPermissionsSuccess();

    /**
     * 请求权限失败回调
     */
    void requestPermissionsFailed();
}
