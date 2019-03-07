package com.mudoulife.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mudoulife.R;
import com.mudoulife.base.BaseActivity;
import com.mudoulife.base.LoginView;
import com.mudoulife.common.Globals;
import com.mudoulife.common.UserInfoManager;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonRoleData;
import com.mudoulife.data.net.response.LoginData;
import com.mudoulife.di.ComponentHolder;
import com.mudoulife.di.HasComponent;
import com.mudoulife.di.component.AccountComponent;
import com.mudoulife.di.component.DaggerAccountComponent;
import com.mudoulife.di.module.AccountModule;
import com.mudoulife.di.module.ActivityModule;
import com.mudoulife.presenter.account.AccountPresenter;
import com.mudoulife.ui.activity.MainActivity;
import com.mudoulife.util.DialogUtils;
import com.mudoulife.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements HasComponent<AccountComponent>, LoginView {
    private static final String TAG = "LoginActivity";
    @Inject
    AccountPresenter mPresenter;
    @Inject
    UserInfoManager mUserInfoManager;
    @BindView(R.id.title_register)
    TextView mTitleRegister;
    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    @BindView(R.id.send_code_btn)
    Button mSendCodeBtn;
    @BindView(R.id.edit_code)
    EditText mEditCode;
    @BindView(R.id.use_password)
    TextView mUsePassword;
    @BindView(R.id.forget_password)
    TextView mForgetPassword;
    @BindView(R.id.login)
    Button mLoginBtn;
    private boolean mIsUsedPhoneLogin;
    private String mLoginName;
    private String mLoginPassword;
    private long mTime = 60000;
    private TimeCount mTimeCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getComponent().inject(this);
        mPresenter.attachView(this);
        init();
    }

    private void init() {
        initUseLoginType();
        mLoginBtn.setOnClickListener(mOnClickListener);
        mTitleRegister.setOnClickListener(mOnClickListener);
        mForgetPassword.setOnClickListener(mOnClickListener);
        mUsePassword.setOnClickListener(mOnClickListener);
        mSendCodeBtn.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login:
                    mLoginName = mEditPhone.getText().toString();
                    mLoginPassword = mEditCode.getText().toString();
                    if (mIsUsedPhoneLogin){
                        mPresenter.phoneLogin(mLoginName, mLoginPassword);
                    }else mPresenter.login(mLoginName, mLoginPassword);
                    break;
                case R.id.title_register:
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    break;
                case R.id.use_password:
                    mIsUsedPhoneLogin = !mIsUsedPhoneLogin;
                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                    break;
                case R.id.forget_password:
                    break;
                case R.id.send_code_btn:
                    String phone = mEditPhone.getText().toString();
                    if (!phone.isEmpty() && phone.length() > 10){
                        mTimeCount = new TimeCount(mTime, 1000);
                        mTimeCount.start();
                        mPresenter.sendCode(mEditPhone.getText().toString());
                    }else {
                        ToastUtil.makeText(LoginActivity.this,"手机号格式不对");
                    }
                    break;
            }
        }
    };

    @Override
    public AccountComponent getComponent() {
        return DaggerAccountComponent.builder()
                .applicationComponent(ComponentHolder.getAppComponent())
                .activityModule(new ActivityModule(this))
                .accountModule(new AccountModule())
                .build();
    }

    @Override
    public void loginSuccess(JsonBase jsonBase) {
        int code = jsonBase.getCode();
        LoginData data = (LoginData) jsonBase.getData();
        Log.d(TAG, "loginSuccess: jsonbase = " + jsonBase);
        if (code == 0) {
            //成功
            ToastUtil.makeText(getApplicationContext(),"登录成功");
            UserInfoManager.gIsUserLogin = true;
            if (mIsUsedPhoneLogin){
                UserInfoManager.gPhoneNum = mLoginName;
            }else {
                UserInfoManager.gUserName = mLoginName;
                UserInfoManager.gUserPassword = mLoginPassword;
            }

            String token = data.getToken();
            UserInfoManager.gToken = token;
            Log.d(TAG, "loginSuccess: token = " + token);
            mUserInfoManager.saveUserInfo2Preferences(getApplicationContext());
            mPresenter.getRoles();

        } else {
            //失败

            ToastUtil.makeText(getApplicationContext(),jsonBase.getMsg());
        }

    }

    @Override
    public void getCodeSuccess(JsonBase jsonBase) {
        ToastUtil.makeText(getApplicationContext(),"验证码发送成功");
    }

    @Override
    public void onGetRoles(JsonBase jsonBase) {
        ArrayList<JsonRoleData> datas = (ArrayList<JsonRoleData>) jsonBase.getData();
        Log.d(TAG, "onGetRoles: datas = " + datas);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: intent = " + intent);
        mEditPhone.setText("");
        mEditCode.setText("");
        initUseLoginType();
    }

    private void initUseLoginType() {
        if (mIsUsedPhoneLogin){
            mSendCodeBtn.setVisibility(View.VISIBLE);
            mEditPhone.setHint(R.string.input_phone);
            mEditPhone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
            mEditCode.setHint(R.string.input_code);
            mUsePassword.setText(R.string.use_password_login);
            mEditCode.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        }else{
            mSendCodeBtn.setVisibility(View.GONE);
            mEditPhone.setHint(R.string.input_account);
            mEditPhone.setInputType(EditorInfo.TYPE_CLASS_TEXT);
            mEditCode.setInputType(EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
            mEditCode.setHint(R.string.input_password);
            mUsePassword.setText(R.string.use_phone_login);
            mEditCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
        ToastUtil.recycle();
    }

    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long countDownTimer) {
            //计时过程
            if (mIsUsedPhoneLogin){
                mSendCodeBtn.setEnabled(false);
                mSendCodeBtn.setClickable(false);
                mSendCodeBtn.setTextColor(getResources().getColor(R.color.color_999999));
                mSendCodeBtn.setText(countDownTimer / 1000 + "s后重新获取");
//            if (mTimerContainer.getVisibility() == View.INVISIBLE)
//                mTimerContainer.setVisibility(View.VISIBLE);
//            mTimeTv.setText();
                mTime = countDownTimer;
            }

        }

        @Override
        public void onFinish() {
            //计时完毕
//            mTimerContainer.setVisibility(View.INVISIBLE);
            if (mIsUsedPhoneLogin){
                mSendCodeBtn.setEnabled(true);
                mSendCodeBtn.setClickable(true);
                mSendCodeBtn.setBackground(getResources().getDrawable(R.mipmap.register_code_bg));
                mSendCodeBtn.setText("点击获取验证码");
            }
            if (mTimeCount != null) {
                mTimeCount.cancel();
                mTimeCount = null;
            }


        }
    }
}
