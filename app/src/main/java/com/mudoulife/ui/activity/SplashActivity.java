package com.mudoulife.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;

import com.mudoulife.R;
import com.mudoulife.base.BaseActivity;
import com.mudoulife.base.LoginView;
import com.mudoulife.common.Globals;
import com.mudoulife.common.UserInfoManager;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonRoleData;
import com.mudoulife.di.ComponentHolder;
import com.mudoulife.di.HasComponent;
import com.mudoulife.di.component.AccountComponent;
import com.mudoulife.di.component.DaggerAccountComponent;
import com.mudoulife.di.module.AccountModule;
import com.mudoulife.di.module.ActivityModule;
import com.mudoulife.permission.PermissionHelper;
import com.mudoulife.permission.PermissionInterface;
import com.mudoulife.presenter.account.AccountPresenter;
import com.mudoulife.ui.activity.account.LoginActivity;
import com.mudoulife.util.ToastUtil;

import java.util.ArrayList;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements PermissionInterface, HasComponent<AccountComponent>, LoginView {

    private static final String TAG = "SplashActivity";
    private static final int REQUEST_CODE = 10001;
    private PermissionHelper mPermissionHelper;
    @Inject
    AccountPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getComponent().inject(this);
        mPresenter.attachView(this);
//        requestPermission();
        mPermissionHelper = new PermissionHelper(this,this);
        mPermissionHelper.requestPermissions();

//        launch();
    }
    private void requestPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if ()
        if (mPermissionHelper.requestPermissionResult(requestCode, permissions, grantResults))
            return;
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void launch(){
//        UserInfoManager.gIsUserLogin = true;
        UserInfoManager.getInstance(getApplicationContext()).getUserInfo(getApplicationContext());
        if (!UserInfoManager.gIsUserLogin){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }else{
            mPresenter.getRoles();
        }
    }

    @Override
    public int getPermissionRequestCode() {
        return REQUEST_CODE;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
                /*,Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA*/};
    }

    @Override
    public void requestPermissionsSuccess() {
        Log.d(TAG, "requestPermissionsSuccess: ");
        launch();
    }

    @Override
    public void requestPermissionsFailed() {
        Log.d(TAG, "requestPermissionsFailed: ");
        finish();
    }

    @Override
    public void loginSuccess(JsonBase jsonBase) {

    }

    @Override
    public void getCodeSuccess(JsonBase jsonBase) {

    }

    @Override
    public void onGetRoles(JsonBase jsonBase) {
        ArrayList<JsonRoleData> datas = (ArrayList<JsonRoleData>) jsonBase.getData();
        Log.d(TAG, "onGetRoles: datas = " + datas);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra(Globals.ROLES_DATA,datas);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showTipMsg(String msg) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
    }
    @Override
    public AccountComponent getComponent() {
        return DaggerAccountComponent.builder()
                .applicationComponent(ComponentHolder.getAppComponent())
                .activityModule(new ActivityModule(this))
                .accountModule(new AccountModule())
                .build();
    }
}
