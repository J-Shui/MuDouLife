package com.mudoulife.ui.activity.account;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mudoulife.R;
import com.mudoulife.base.BaseActivity;
import com.mudoulife.base.LoginView;
import com.mudoulife.common.Globals;
import com.mudoulife.common.UserInfoManager;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.request.RegisterRequestInfo;
import com.mudoulife.di.ComponentHolder;
import com.mudoulife.di.HasComponent;
import com.mudoulife.di.component.AccountComponent;
import com.mudoulife.di.component.DaggerAccountComponent;
import com.mudoulife.di.module.AccountModule;
import com.mudoulife.di.module.ActivityModule;
import com.mudoulife.presenter.account.AccountPresenter;
import com.mudoulife.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;

public class RegisterActivity extends BaseActivity implements HasComponent<AccountComponent>, LoginView {
    private static final String TAG = "RegisterActivity";
    @Inject
    AccountPresenter mPresenter;
    @Inject
    UserInfoManager mUserInfoManager;
    @BindView(R.id.back_container)
    RelativeLayout mBackContainer;
    @BindView(R.id.tittle_name)
    TextView mTittle;
    @BindView(R.id.edit_user_name)
    EditText mEditUserName;
    @BindView(R.id.edit_name)
    EditText mEditName;
    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    @BindView(R.id.edit_idcard)
    EditText mEditIdcard;
    @BindView(R.id.send_code_btn)
    Button mSendCodeBtn;
    @BindView(R.id.edit_code)
    EditText mEditCode;
    @BindView(R.id.code_container)
    RelativeLayout mCodeContainer;
    @BindView(R.id.edit_password)
    EditText mEditPassword;
    @BindView(R.id.edit_sure_password)
    EditText mEditSurePassword;
    @BindView(R.id.choose)
    TextView choose;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;
    private ImageView mWorkerSelectIv;
    private ImageView mShanghuSelectIv;
    private TextView mWorkerTv;
    private TextView mShanghuTv;
    private ImageView mExpressSelectIv;
    private TextView mExpressTv;
    private ImageView mCarrySelectIv;
    private TextView mCarryTv;
    private boolean mIsWorkerSelected;
    private boolean mIsShanghuSelected;
    private boolean mIsExpressSelected;
    private boolean mIsCarrySelected;
    private SparseArray<Integer> mRoleIds;

    private long mTime = 60000;
    private TimeCount mTimeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode
                (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_register);
        getComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.attachView(this);
        initView();

    }

    private void initView() {
        mRoleIds = new SparseArray<>();
        mTittle.setText(getResources().getString(R.string.register));
        RelativeLayout workerContainer = findViewById(R.id.worker_container);
        RelativeLayout shanghuContainer = findViewById(R.id.shang_hu_container);
        RelativeLayout expressContainer = findViewById(R.id.express_container);
        RelativeLayout carryContainer = findViewById(R.id.carry_container);
        mWorkerSelectIv = workerContainer.findViewById(R.id.select_img);
        mWorkerTv = workerContainer.findViewById(R.id.role_text);
        mShanghuSelectIv = shanghuContainer.findViewById(R.id.select_img);
        mShanghuTv = shanghuContainer.findViewById(R.id.role_text);
        mExpressSelectIv = expressContainer.findViewById(R.id.select_img);
        mExpressTv = expressContainer.findViewById(R.id.role_text);
        mCarrySelectIv = carryContainer.findViewById(R.id.select_img);
        mCarryTv = carryContainer.findViewById(R.id.role_text);

        mCarryTv.setText(getResources().getString(R.string.bottom_mu_dou_carry));
        mExpressTv.setText(getResources().getString(R.string.bottom_mu_dou_express));
        mShanghuTv.setText(getResources().getString(R.string.bottom_mu_dou_shanghu));
//        mEditCode.addTextChangedListener(mTextWatcher);
//        mEditUserName.addTextChangedListener(mTextWatcher);
//        mEditName.addTextChangedListener(mTextWatcher);
//        mEditIdcard.addTextChangedListener(mTextWatcher);
//        mEditPassword.addTextChangedListener(mTextWatcher);
//        mEditPhone.addTextChangedListener(mTextWatcher);
//        mEditSurePassword.addTextChangedListener(mTextWatcher);
        mSendCodeBtn.setOnClickListener(mOnClickListener);
        mBackContainer.setOnClickListener(mOnClickListener);
        mRegisterBtn.setOnClickListener(mOnClickListener);
        mWorkerSelectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsWorkerSelected) {
                    mRoleIds.remove(0);
                    mWorkerSelectIv.setBackgroundResource(R.mipmap.register_not_select_img);
                } else {
                    mRoleIds.put(0, Globals.ROLE_WORKER_ID);
                    mWorkerSelectIv.setBackgroundResource(R.mipmap.register_selected_img);
                }
                mIsWorkerSelected = !mIsWorkerSelected;

            }
        });
        mShanghuSelectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsShanghuSelected) {
                    mRoleIds.remove(1);
                    mShanghuSelectIv.setBackgroundResource(R.mipmap.register_not_select_img);
                } else {
                    mRoleIds.put(1, Globals.ROLE_SHANG_HU_ID);
                    mShanghuSelectIv.setBackgroundResource(R.mipmap.register_selected_img);
                }
                mIsShanghuSelected = !mIsShanghuSelected;

            }
        });
        mExpressSelectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsExpressSelected) {
                    mRoleIds.remove(2);
                    mExpressSelectIv.setBackgroundResource(R.mipmap.register_not_select_img);
                } else {
                    mRoleIds.put(2, Globals.ROLE_EXPRESS_ID);
                    mExpressSelectIv.setBackgroundResource(R.mipmap.register_selected_img);
                }
                mIsExpressSelected = !mIsExpressSelected;
            }
        });
        mCarrySelectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsCarrySelected) {
                    mRoleIds.remove(3);
                    mCarrySelectIv.setBackgroundResource(R.mipmap.register_not_select_img);
                } else {
                    mRoleIds.put(3, Globals.ROLE_CARRY_ID);
                    mCarrySelectIv.setBackgroundResource(R.mipmap.register_selected_img);
                }
                mIsCarrySelected = !mIsCarrySelected;
            }
        });
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back_container:
                    finish();
                    break;
                case R.id.send_code_btn:
                    String phone = mEditPhone.getText().toString();
                    if (!phone.isEmpty() && phone.length() > 10) {
                        mTimeCount = new TimeCount(mTime, 1000);
                        mTimeCount.start();
                        mPresenter.sendCode(mEditPhone.getText().toString());
                    } else {
                        ToastUtil.makeText(RegisterActivity.this, "手机号格式不对");
                    }
                    break;

                case R.id.register_btn:
                    register();
                    break;

            }
        }
    };

    private void register() {
        String userName = mEditUserName.getText().toString();
        String name = mEditName.getText().toString();
        String phone = mEditPhone.getText().toString();
        String idCard = mEditIdcard.getText().toString();
        String vCode = mEditCode.getText().toString();
        String password = mEditPassword.getText().toString();
        String surePwd = mEditSurePassword.getText().toString();
        RegisterRequestInfo info = new RegisterRequestInfo();
        info.setIdCard(idCard);
        info.setName(name);
        info.setPhone(phone);
        info.setPassword(password);
        List<Integer> roleIds = new ArrayList<>();
        for (int i = 0; i < mRoleIds.size(); i++) {
            roleIds.add(mRoleIds.valueAt(i));
        }
        info.setRoleIds(roleIds);
        info.setUsername(userName);
        info.setvCode(vCode);
        Gson gson = new Gson();
        String jsonInfo = gson.toJson(info);
        Log.d(TAG, "register: jsonInfo = " + jsonInfo);
//        UserInfoManager.gUserPassword = password;
//        UserInfoManager.gUserName = userName;
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonInfo);
        UserInfoManager.gUserName = userName;
        UserInfoManager.gPhoneNum = phone;
        UserInfoManager.getInstance(this).saveUserInfo2Preferences(this);
        mPresenter.register(body);
//        mPresenter.registerAndLogin(body,userName,password);
        if (!TextUtils.isEmpty(password) && surePwd.equals(password)) {

        }
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void loginSuccess(JsonBase jsonBase) {
//        int code = jsonBase.getCode();
//        LoginData data = (LoginData) jsonBase.getData();
//        Log.d(TAG, "loginSuccess: jsonbase = " + jsonBase);
//        if (code == 0) {
//            //成功
//            ToastUtil.makeText(getApplicationContext(),"登录成功");
//            UserInfoManager.gIsUserLogin = true;
//            String token = data.getToken();
//            UserInfoManager.gToken = token;
//            Log.d(TAG, "loginSuccess: token = " + token);
//            mUserInfoManager.saveUserInfo2Preferences(getApplicationContext());
//            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
//            finish();
//        } else {
//            //失败
//            ToastUtil.makeText(getApplicationContext(),"登录失败");
//        }
        int code = jsonBase.getCode();
        Log.d(TAG, "jsonBase = " + jsonBase);
        if (code == 0) {
            //成功
            ToastUtil.makeText(getApplicationContext(), "注册成功");
            finish();
        } else {
            //失败
            String msg = jsonBase.getMsg();
            ToastUtil.makeText(getApplicationContext(), msg);
        }

    }

    @Override
    public void getCodeSuccess(JsonBase jsonBase) {
        ToastUtil.makeText(getApplicationContext(), "验证码发送成功");
    }

    @Override
    public void onGetRoles(JsonBase jsonBase) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(Throwable e) {
        ToastUtil.makeText(getApplicationContext(), "服务器异常");
    }

    @Override
    public void showTipMsg(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
    }

    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long countDownTimer) {
            //计时过程
            mSendCodeBtn.setEnabled(false);
            mSendCodeBtn.setClickable(false);
            mSendCodeBtn.setTextColor(getResources().getColor(R.color.color_999999));
            mSendCodeBtn.setText(countDownTimer / 1000 + "s后重新获取");
//            if (mTimerContainer.getVisibility() == View.INVISIBLE)
//                mTimerContainer.setVisibility(View.VISIBLE);
//            mTimeTv.setText();
            mTime = countDownTimer;

        }

        @Override
        public void onFinish() {
            //计时完毕
//            mTimerContainer.setVisibility(View.INVISIBLE);
            mSendCodeBtn.setEnabled(true);
            mSendCodeBtn.setClickable(true);
            mSendCodeBtn.setBackground(getResources().getDrawable(R.mipmap.register_code_bg));
            mSendCodeBtn.setText("点击获取验证码");
            if (mTimeCount != null) {
                mTimeCount.cancel();
                mTimeCount = null;
            }


        }
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
