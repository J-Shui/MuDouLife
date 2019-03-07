package com.mudoulife.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/27
 * email : jshui_920124@163.com
 * package_name : com.mudoulife
 * project : MuDouLife
 * 动态权限帮助类
 */
public class PermissionHelper {
    private Activity mActivity;
    private PermissionInterface mPermissionInterface;

    public PermissionHelper(Activity activity, PermissionInterface permissionInterface) {
        this.mActivity = activity;
        this.mPermissionInterface = permissionInterface;
    }

    /**
     * 开始请求权限。方法内部已经对Android M或以上版本进行判断，外部使用不需要重复判断
     */
    public void requestPermissions(){
        String[] deniedPermissions = PermissionUtil.getDeniedPermissions(mActivity,mPermissionInterface.getPermissions());
        if (deniedPermissions != null && deniedPermissions.length > 0){
            PermissionUtil.requestPermissions(mActivity,deniedPermissions,mPermissionInterface.getPermissionRequestCode());
        }else{
            mPermissionInterface.requestPermissionsSuccess();
        }
    }

    /**
     * 在activity的onResultPermissionResult中调用
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @return
     */
    public boolean requestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if (requestCode == mPermissionInterface.getPermissionRequestCode()){
            boolean isAllGranted = true;
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_DENIED){
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted){
                //全部授权
                mPermissionInterface.requestPermissionsSuccess();
            }else {
                mPermissionInterface.requestPermissionsFailed();
            }
            return true;
        }
        return false;
    }
}
