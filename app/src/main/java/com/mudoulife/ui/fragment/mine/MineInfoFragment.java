package com.mudoulife.ui.fragment.mine;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.base.BaseFragment;
import com.mudoulife.base.MainView;
import com.mudoulife.common.UserInfoManager;
import com.mudoulife.data.net.response.ComplaintReason;
import com.mudoulife.data.net.response.JsonAfterDetails;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonBudgetDetails;
import com.mudoulife.data.net.response.JsonDeliverDatails;
import com.mudoulife.data.net.response.JsonOrderDetailsData;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.data.net.response.UserInfo;
import com.mudoulife.di.component.MainComponent;
import com.mudoulife.presenter.OrderBasePresenter;
import com.mudoulife.ui.activity.account.LoginActivity;
import com.mudoulife.ui.fragment.order.OrderBaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineInfoFragment extends BaseFragment implements MainView<JsonBase<List<ResultInfo>>> {
    private static final String TAG = "MineInfoFragment";

    ImageView mAvatarIv;
    TextView mUserNameTv;
    ImageView mNameArrowRighIv;
    TextView mUserNameValueTv;
    TextView mPasswordNameTv;
    ImageView mPasswordArrowRightIv;
    TextView mPasswordValueTv;
    TextView mPhoneNameTv;
    ImageView mPhoneArrowRightIv;
    TextView mPhoneValueTv;
    TextView mWeiXinNameTv;
    ImageView mWeiXinArrowRightIv;
    TextView mWeiXinValueTv;
    TextView mLogoutNameTv;
    ImageView mLogoutArrowRightIv;
    TextView mLogoutValueTv;

    RelativeLayout mUserNameContainer;
    RelativeLayout mPhoneContainer;
    RelativeLayout mPasswordContainer;
    RelativeLayout mWeiXinContainer;
    RelativeLayout mLogoutContainer;
    private Context mContext;
    private Button mLogoutBtn;

    @Inject
    OrderBasePresenter mPresenter;

    public MineInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent(MainComponent.class).inject(this);
        mPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine_info, container, false);
        initView(view);
        loadData();
        return view;
    }

    protected void loadData() {
        mPresenter.getUserInfo();
    }



    private void initView(View view){
        mAvatarIv = view.findViewById(R.id.avatar_img);
        mUserNameContainer = view.findViewById(R.id.user_name_container);
        mPhoneContainer = view.findViewById(R.id.phone_container);
        mPasswordContainer = view.findViewById(R.id.password_container);
        mWeiXinContainer = view.findViewById(R.id.weixin_container);
        mLogoutContainer = view.findViewById(R.id.logout_container);

        mUserNameTv = mUserNameContainer.findViewById(R.id.user_name_text);
        mNameArrowRighIv = mUserNameContainer.findViewById(R.id.arrow_right_img);
        mUserNameValueTv = mUserNameContainer.findViewById(R.id.user_name_value);
        mUserNameTv.setText(getResources().getString(R.string.user_name));
//        mUserNameValueTv.setText(UserInfoManager.gUserName);

        mPasswordNameTv = mPasswordContainer.findViewById(R.id.user_name_text);
        mPasswordArrowRightIv = mPasswordContainer.findViewById(R.id.arrow_right_img);
        mPasswordValueTv = mPasswordContainer.findViewById(R.id.user_name_value);
        mPasswordNameTv.setText(getResources().getString(R.string.password));
        mPasswordValueTv.setText(getResources().getString(R.string.amend));

        mPhoneNameTv = mPhoneContainer.findViewById(R.id.user_name_text);
        mPhoneArrowRightIv = mPhoneContainer.findViewById(R.id.arrow_right_img);
        mPhoneValueTv = mPhoneContainer.findViewById(R.id.user_name_value);
        mPhoneNameTv.setText(getResources().getString(R.string.phone));


        mWeiXinNameTv = mWeiXinContainer.findViewById(R.id.user_name_text);
        mWeiXinArrowRightIv = mWeiXinContainer.findViewById(R.id.arrow_right_img);
        mWeiXinValueTv = mWeiXinContainer.findViewById(R.id.user_name_value);
        mWeiXinNameTv.setText(getResources().getString(R.string.weixin_account));
        mWeiXinValueTv.setText(getResources().getString(R.string.unbind));

        mLogoutNameTv = mLogoutContainer.findViewById(R.id.user_name_text);
        mLogoutArrowRightIv = mLogoutContainer.findViewById(R.id.arrow_right_img);
        mLogoutValueTv = mLogoutContainer.findViewById(R.id.user_name_value);
        mLogoutNameTv.setText(getResources().getString(R.string.logout));
        mLogoutValueTv.setText(getResources().getString(R.string.logout_message));
        mLogoutValueTv.setVisibility(View.GONE);

        mLogoutBtn = view.findViewById(R.id.logout_btn);



        mUserNameContainer.setOnClickListener(mOnClickListener);
        mPhoneContainer.setOnClickListener(mOnClickListener);
        mPasswordContainer.setOnClickListener(mOnClickListener);
        mWeiXinContainer.setOnClickListener(mOnClickListener);
        mLogoutContainer.setOnClickListener(mOnClickListener);
        mLogoutBtn.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.user_name_container:
                    break;
                case R.id.phone_container:
                    break;
                case R.id.password_container:
                    break;
                case R.id.weixin_container:
                    break;
                case R.id.logout_container:
                    break;
                case R.id.logout_btn:
                    onLogout();
                    break;
            }
        }
    };
    private void onLogout(){
        UserInfoManager.gIsUserLogin = false;
        UserInfoManager.gPhoneNum = null;
        UserInfoManager.gUserName = null;
        UserInfoManager.gToken = null;
        UserInfoManager.getInstance(mContext).saveUserInfo2Preferences(mContext);
        startActivity(new Intent(mContext,LoginActivity.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showContent(JsonBase<List<ResultInfo>> data) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showOrderDetails(JsonBase<JsonOrderDetailsData> jsonBase) {

    }

    @Override
    public void showDeliverDetails(JsonBase<JsonDeliverDatails> jsonBase) {

    }

    @Override
    public void onCancelOrder(JsonBase jsonBase) {

    }

    @Override
    public void onConfirmReceipt(JsonBase jsonBase) {

    }

    @Override
    public void showInCome(JsonBase<Double> jsonBase) {

    }

    @Override
    public void showComplaint(JsonBase<List<ComplaintReason>> jsonBase) {

    }

    @Override
    public void showUserInfo(JsonBase<UserInfo> jsonBase) {
        UserInfo info = (UserInfo) jsonBase.getData();
        if (info != null) {
            String username = info.getUsername();
            String phone = info.getPhone();
            mPhoneValueTv.setText(phone);
            mUserNameValueTv.setText(username);
        }

    }

    @Override
    public void showBudgetDetails(JsonBase<JsonBudgetDetails> jsonBase) {

    }

    @Override
    public void showAfterDetails(JsonBase<JsonAfterDetails> jsonBase) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
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
}
