package com.mudoulife.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.base.BaseActivity;
import com.mudoulife.base.LibraryView;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.JsonBase;
import com.mudoulife.data.net.response.JsonLibraryLeftItem;
import com.mudoulife.data.net.response.LibraryBaseData;
import com.mudoulife.di.ComponentHolder;
import com.mudoulife.di.HasComponent;
import com.mudoulife.di.component.DaggerLibraryComponent;
import com.mudoulife.di.component.LibraryComponent;
import com.mudoulife.di.module.ActivityModule;
import com.mudoulife.di.module.LibraryModule;
import com.mudoulife.presenter.library.LibraryBasePresenter;
import com.mudoulife.ui.activity.library.LibraryWorkerActivity;
import com.mudoulife.ui.adapter.library.LibraryBaseAdapter;
import com.mudoulife.ui.adapter.library.SelectedListAdapter;
import com.mudoulife.util.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class LibraryBaseActivity extends BaseActivity implements HasComponent<LibraryComponent>, LibraryView<JsonBase<List<LibraryBaseData>>> {

    @BindView(R.id.back_container)
    protected RelativeLayout mBackContainer;
    @BindView(R.id.library_top_container)
    protected LinearLayout mTopContainer;
    @BindView(R.id.library_top_service)
    protected Button mTopService;
    @BindView(R.id.library_top_material)
    protected Button mTopMaterial;
    @BindView(R.id.total_logo_img)
    protected ImageView mTotalIv;
    @BindView(R.id.total_text)
    protected TextView mTotalTv;
    @BindView(R.id.left_item_1)
    protected Button mLeftItem1;
    @BindView(R.id.left_item_2)
    protected Button mLeftItem2;
    @BindView(R.id.left_item_3)
    protected Button mLeftItem3;
    @BindView(R.id.left_item_4)
    protected Button mLeftItem4;
    @BindView(R.id.left_item_5)
    protected Button mLeftItem5;
    @BindView(R.id.left_item_6)
    protected Button mLeftItem6;
    @BindView(R.id.left_item_7)
    protected Button mLeftItem7;
    @BindView(R.id.left_item_8)
    protected Button mLeftItem8;
    @BindView(R.id.left_item_9)
    protected Button mLeftItem9;
    @BindView(R.id.left_item_10)
    protected Button mLeftItem10;
    @BindView(R.id.left_item_11)
    protected Button mLeftItem11;
    @BindView(R.id.left_item_12)
    protected Button mLeftItem12;
    @BindView(R.id.left_item_13)
    protected Button mLeftItem13;
    @BindView(R.id.left_item_14)
    protected Button mLeftItem14;
    @BindView(R.id.left_item_15)
    protected Button mLeftItem15;
    @BindView(R.id.left_item_16)
    protected Button mLeftItem16;

    @BindView(R.id.search_edit)
    protected EditText mSearchEdit;
    @BindView(R.id.search_container)
    protected RelativeLayout searchContainer;
    @BindView(R.id.label_item_1)
    protected TextView mLabelItem1;
    @BindView(R.id.label_item_2)
    protected TextView mLabelItem2;
    @BindView(R.id.label_item_3)
    protected TextView mLabelItem3;
    @BindView(R.id.label_item_4)
    protected TextView mLabelItem4;
    @BindView(R.id.library_recycler)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.shade_view)
    View mShadeView;
    @BindView(R.id.library_bottom_container)
    RelativeLayout mBottomContainer;
    @BindView(R.id.complete_btn)
    Button mCompleteBtn;
    private RecyclerView mWindowRecyclerView;
    //    protected LibraryBaseAdapter<LibraryBaseData> mWindowAdapter;
    protected SelectedListAdapter mWindowAdapter;
    protected TextView mWindowLabel1Tv;
    protected TextView mWindowLabel2Tv;
    protected TextView mWindowLabel3Tv;
    protected TextView mWindowLabel4Tv;

    private static final String TAG = "LibraryBaseActivity";
    public static final String FROM_WORKER = "from_worker";
    public static final String FROM_SHANGHU = "from_shanghu";
    public static final String FROM_SUBSCRIPTION = "from_subscription";
    public static final String FROM_EXPRESS = "from_express";
    public static final String FROM_CARRY = "from_carry";
    private String mCurrentPage = FROM_WORKER;
    protected static final int DEFAULT_STATE = 0;
    protected static final int SELECTED_STATE = 1;
    public static final int TOP_SERVICE = 1;
    public static final int TOP_MATERIAL = 2;
    protected int mTopCurrent;
    protected int mLeftServiceCurrent;
    protected int mLeftMaterialCurrent;
    protected int mLeftCurrent;

    protected LibraryBaseAdapter<LibraryBaseData> mAdapter;
    protected LinearLayoutManager mLinearLayoutManager;
    protected List<LibraryBaseData> mDatas;
    protected SparseArray<List<LibraryBaseData>> mAllDatas;
    protected List<JsonLibraryLeftItem> mLeftItemInfos;
    protected int mCurrentLeftClassId;
    protected int mServiceCurrentLeftClassId;
    protected int mMaterailCurrentLeftClassId;
    protected String mCurrentLeftClassName;
    protected boolean mIsFirstClick = true;
    protected int mTotalPrice;

    protected ArrayList<LibraryBaseData> mSelectedDatas;
    private PopupWindow mPopWindow;

    @Inject
    protected LibraryBasePresenter mPresenter;
    protected int mOrderId;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, LibraryWorkerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode
                (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_library_base);
        ButterKnife.bind(this);
        getComponent().inject(this);
//        ScreenTitleUtils.setScreenTitle(this, R.color.color_fec300,false,false);
        init();
        setRecyclerView();
        mPresenter.attachView(this);
        loadLeftItemData();
    }

    private void initOrderId() {
        mOrderId = getIntent().getIntExtra(Globals.ORDER_ID, -1);
    }

    private void setRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mAdapter.registerAdapterDataObserver(mSumObserver);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //是否有固定size，如果adapter的数据变化不会改变recyclerview的大小变化
//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

//    private void initLeftItem(int size){
//        LayoutInflater inflater = LayoutInflater.from(this);
//        for (int i = 0; i < size; i++) {
//            Button button = (Button) inflater.inflate(R.layout.layout_library_left_item,null);
//
//        }
//    }

    private LibraryBaseAdapter.OnItemClickListener mOnItemClickListener = new LibraryBaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            //这里的position不准确咋
            Object tag = view.getTag();
            if (tag instanceof Integer) {
                int index = (int) tag;
                Log.d(TAG, "index = " + index);
                Log.d(TAG, "position = " + position);
                LibraryBaseData data = mDatas.get(index);
                switch (view.getId()) {
                    case R.id.library_recycler_select:
                        boolean isSelected = data.isSelected();
                        data.setSelected(!isSelected);
                        break;
                    case R.id.number_reduce:
//                        data = mDatas.get(index);
                        int number = data.getNumber();
                        number--;
                        data.setNumber(number);
                        break;
                    case R.id.number_add:
//                        data = mDatas.get(position);
                        number = data.getNumber();
                        number++;
                        data.setNumber(number);
                        break;
                    case R.id.floor_reduce:
//                        data = mDatas.get(position);
                        int floor = data.getFloor();
                        floor--;
                        data.setFloor(floor);
                        break;
                    case R.id.floor_add:
//                        data = mDatas.get(position);
                        floor = data.getFloor();
                        floor++;
                        data.setFloor(floor);
                        break;
                }
                mAdapter.notifyItemChanged(index);
            }
        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    };
    private RecyclerView.AdapterDataObserver mSumObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            Log.d(TAG, "onChanged: ");
            onDataChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            onItemSelected(positionStart);
            Log.d(TAG, "onItemRangeChanged: positionStart = " + positionStart + ",itemCount = " + itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            onItemSelected(positionStart);
        }
    };

    protected void onDataChanged() {
        mTotalTv.setText(getResources().getString(R.string.total) );
    }

    protected void onItemSelected(int position) {
        Log.d(TAG, "onItemRangeChanged: positionStart = " + position);
        double total = 0;
        mSelectedDatas.clear();
//        List<LibraryBaseData> dataList = new ArrayList<>();
//        dataList.clear();
////        for (int i = 0; i < mAllDatas.size(); i++) {
////            List<LibraryBaseData> serviceDatas = mAllDatas.valueAt(i);
////            dataList.addAll(serviceDatas);
////        }
        for (int i = 0; i < mDatas.size(); i++) {
            LibraryBaseData data = mDatas.get(i);
            if (data.isSelected()) {
//                LibraryBaseData data1 = new LibraryBaseData();
//                data1.setFloor(data.getFloor());
//                data1.setName(data.getName());
//                data1.setNumber(data.getNumber());
//                data1.setPrice(data.getPrice());
////                data1.setSelectedPosition(i);
                data.setClassId(mCurrentLeftClassId);
                data.setClassName(mCurrentLeftClassName);
                total += data.getPrice() * data.getNumber();
                data.setTotal(total);
                mSelectedDatas.add(data);
            }
        }
        mTotalTv.setText(getResources().getString(R.string.total) + total);
        Log.d(TAG, "onItemRangeChanged: mSelectedDatas = " + mSelectedDatas);
    }

    protected void init() {

        initOrderId();
        mAllDatas = new SparseArray<>();
        mDatas = new ArrayList<>();
        mSelectedDatas = new ArrayList<>();
        mLeftItemInfos = new ArrayList<>();
//        mMaterialDatas = new JsonContents<>();
//        setVisibility(View.VISIBLE, getLeftButtons());
//        initLeftItem();
//        setLeftSelectedState(SELECTED_STATE, mLeftItem1);
//        mTopContainer.setVisibility(View.VISIBLE);
//        mTopCurrent = TOP_SERVICE;
//        setTopSelectedState(SELECTED_STATE,mTopService);

    }


    protected void setLeftSelectedState(int state, Button... buttons) {
        for (Button button : buttons) {
            if (state == DEFAULT_STATE) {
                button.setTextColor(getResources().getColor(R.color.color_000000));
                button.setBackgroundColor(getResources().getColor(R.color.color_e6e6e6));
            } else if (state == SELECTED_STATE) {
                button.setTextColor(getResources().getColor(R.color.color_ffffff));
                button.setBackgroundColor(getResources().getColor(R.color.color_fec300));
            }
        }
    }

    protected abstract void loadLeftItemData();

    protected abstract void loadOnlineContentData();

    protected abstract Button[] getLeftButtons();

    protected void resetLeftViewState() {
//        Button[] buttons = {mLeftItem1, mLeftItem2, mLeftItem3, mLeftItem4,
//                mLeftItem5, mLeftItem6, mLeftItem7, mLeftItem8};
        Button[] buttons = getLeftButtons();
        setLeftSelectedState(DEFAULT_STATE, buttons);
    }

    protected void setVisibility(int visibility, Button... buttons) {
        for (Button button : buttons) {
            button.setVisibility(visibility);
        }
    }

    protected void setTopSelectedState(int state, Button... buttons) {
        for (Button button : buttons) {
            if (state == DEFAULT_STATE) {
                button.setBackgroundColor(getResources().getColor(R.color.color_999999));
            } else if (state == SELECTED_STATE) {
                button.setBackgroundColor(getResources().getColor(R.color.color_fec300));
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(Throwable e) {
        Log.d(TAG, "showError: e = " + e);
    }

    @Override
    public void showTipMsg(String msg) {

    }

    @OnClick({R.id.back_container, R.id.left_item_1, R.id.left_item_2, R.id.left_item_3,
            R.id.left_item_4, R.id.left_item_5, R.id.left_item_6, R.id.left_item_7,
            R.id.left_item_8, R.id.left_item_9,R.id.left_item_10,R.id.left_item_11,R.id.left_item_12,
            R.id.left_item_13, R.id.left_item_14,R.id.left_item_15,R.id.left_item_16,
            R.id.search_container, R.id.total_logo_img, R.id.complete_btn})
    public void onViewClicked(View view) {
        resetLeftViewState();
        Button leftBtn = mLeftItem1;
        int index = -1;
        switch (view.getId()) {
            case R.id.back_container:
                finish();
                break;

            case R.id.left_item_1:
//                leftBtn = mLeftItem1;
                index = 0;
                break;
            case R.id.left_item_2:
                leftBtn = mLeftItem2;
                index = 1;
                break;
            case R.id.left_item_3:
                leftBtn = mLeftItem3;
                index = 2;
                break;
            case R.id.left_item_4:
                leftBtn = mLeftItem4;
                index = 3;
                break;
            case R.id.left_item_5:
                leftBtn = mLeftItem5;
                index = 4;
                break;
            case R.id.left_item_6:
                leftBtn = mLeftItem6;
                index = 5;
                break;
            case R.id.left_item_7:
                leftBtn = mLeftItem7;
                index = 6;
                break;
            case R.id.left_item_8:
                leftBtn = mLeftItem8;
                index = 7;
                break;
            case R.id.left_item_9:
                leftBtn = mLeftItem9;
                index = 8;
                break;
            case R.id.left_item_10:
                leftBtn = mLeftItem10;
                index = 9;
                break;
            case R.id.left_item_11:
                leftBtn = mLeftItem11;
                index = 10;
                break;
            case R.id.left_item_12:
                leftBtn = mLeftItem12;
                index = 11;
                break;
            case R.id.left_item_13:
                leftBtn = mLeftItem13;
                index = 12;
                break;
            case R.id.left_item_14:
                leftBtn = mLeftItem14;
                index = 13;
                break;
            case R.id.left_item_15:
                leftBtn = mLeftItem15;
                index = 14;
                break;
            case R.id.left_item_16:
                leftBtn = mLeftItem16;
                index = 15;
                break;
            case R.id.total_logo_img:
                initPopWindow(mBottomContainer);
                mShadeView.setVisibility(View.VISIBLE);
                break;
            case R.id.complete_btn:
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("selected_list", mSelectedDatas);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
        if (index > -1) {
            mCurrentLeftClassId = mLeftItemInfos.get(index).getId();
            mCurrentLeftClassName = mLeftItemInfos.get(index).getName();
            onLeftChanged(index);
            loadOnlineContentData();
        }

        setLeftSelectedState(SELECTED_STATE, leftBtn);
    }

    protected void onLeftChanged(int index) {
//        if (mTopCurrent == TOP_SERVICE) {
//            if(mLeftServiceCurrent != index) mLeftServiceCurrent = index;
//            mServiceCurrentLeftClassId = mLeftItemInfos.get(index).getId();
//            mCurrentLeftClassId = mLeftItemInfos.get(index).getId();
//        }else {
//            if(mLeftMaterialCurrent != index) mLeftMaterialCurrent = index;
//            mMaterailCurrentLeftClassId = mLeftItemInfos.get(index).getId();
//            mCurrentLeftClassId = mLeftItemInfos.get(index).getId();
//        }
    }

    private void loadPopData() {

    }

    protected void initLeftItem(Button[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(getLeftTexts()[i]);
        }
    }

    protected abstract String[] getLeftTexts();

    protected void initPopView(View view) {
        mWindowRecyclerView = view.findViewById(R.id.recycler_view);
        mWindowAdapter = new SelectedListAdapter(mSelectedDatas, this, getType());
//        mWindowAdapter.setOnItemClickListener(mWindowItemClickListener);
//        initWindowAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mWindowRecyclerView.setLayoutManager(layoutManager);
        mWindowRecyclerView.setAdapter(mWindowAdapter);
        mWindowLabel1Tv = view.findViewById(R.id.label_item_1);
        mWindowLabel2Tv = view.findViewById(R.id.label_item_2);
        mWindowLabel3Tv = view.findViewById(R.id.label_item_3);
        mWindowLabel4Tv = view.findViewById(R.id.label_item_4);

    }

    protected abstract void initWindowAdapter();

    protected abstract int getType();


    private SelectedListAdapter.OnItemClickListener mWindowItemClickListener = new SelectedListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view) {
            Object tag = view.getTag();
            if (tag instanceof Integer) {
                int position = (int) tag;
                Log.d(TAG, "position = " + position);
                LibraryBaseData data = mSelectedDatas.get(position);
//                int selectedPosition = data.getSelectedPosition();
                LibraryBaseData libraryBaseData = mDatas.get(0);
                switch (view.getId()) {
                    case R.id.number_reduce:
//                        data = mDatas.get(index);
                        int number = data.getNumber();
                        number--;
                        data.setNumber(number);
                        int number1 = libraryBaseData.getNumber();
                        number1--;
                        libraryBaseData.setNumber(number1);
                        break;
                    case R.id.number_add:
//                        data = mDatas.get(position);
                        number = data.getNumber();
                        number++;
                        data.setNumber(number);
                        number1 = libraryBaseData.getNumber();
                        number1++;
                        libraryBaseData.setNumber(number1);
                        break;
                    case R.id.floor_reduce:
//                        data = mDatas.get(position);
                        int floor = data.getFloor();
                        floor--;
                        data.setFloor(floor);
                        int floor1 = libraryBaseData.getFloor();
                        floor1--;
                        libraryBaseData.setFloor(floor1);
                        break;
                    case R.id.floor_add:
//                        data = mDatas.get(position);
                        floor = data.getFloor();
                        floor++;
                        data.setFloor(floor);
                        floor1 = libraryBaseData.getFloor();
                        floor1++;
                        libraryBaseData.setFloor(floor1);
                        break;
                }
                mWindowAdapter.notifyItemChanged(position);
//                mAdapter.notifyItemChanged(selectedPosition);
            }

        }

        @Override
        public void onItemLongClick(View view) {

        }
    };

    private void initPopWindow(View anchor) {
        Log.d(TAG, "initPopWindow anchor = " + anchor);
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.layout_choose_list, null);
//        RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.layout_pop_window_tablet, null);

        loadPopData();
        initPopView(view);

        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels;

        //获取view的坐标
        int[] location = new int[2];
//            anchor.getLocationInWindow(location);
        anchor.getLocationOnScreen(location);

        int height = location[1] + anchor.getHeight();

        mPopWindow = new PopupWindow(view, widthPixels - mLeftItem1.getMeasuredWidth(),
                (int) (heightPixels * 0.2), true);


        Log.d(TAG, "initPopWindow = " + mPopWindow.isShowing());


        if (mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        } else {

            ColorDrawable cd = new ColorDrawable(Color.BLACK);
            mPopWindow.setBackgroundDrawable(cd);
//            mLeftPopWindow.setBackgroundDrawable(Color.BLACK);
            final Window w = getWindow();

            WindowManager.LayoutParams params = w.getAttributes();
            //这里设置透明度与，为什么会显示桌面
            params.alpha = 1f;

//            w.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            w.setAttributes(params);


            mPopWindow.setFocusable(true);
//
            mPopWindow.setOutsideTouchable(false);

            mPopWindow.update();
//
//            view.removeView(parent);
//            mLeftPopWindow.setAnimationStyle(R.style.WindowAnimStyle);
//            int offsetY = (int)Utils.dp2px(getResources().getDimension(R.dimen.dimen_72));
            int bottomHeight = Utils.getBottomBarHeight(this);
            int offsetY = (int) Utils.dp2px(48);
            Log.d(TAG, "initPopWindow: offsetY = " + offsetY);
            Log.d(TAG, "initPopWindow: bottomHeight = " + bottomHeight);
            mPopWindow.showAtLocation(anchor, Gravity.BOTTOM | Gravity.END, 0, offsetY + bottomHeight);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//                mLeftPopWindow.showAsDropDown(anchor, 0, 0, Gravity.RIGHT);
//            else
//            mLeftPopWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, (int) (widthPixels * 0.15), height);


            //相对某个控件的正左下方，无偏移
//            mLeftPopWindow.showAsDropDown(anchor);
            //  相对某个控件的位置，有偏移，xoff表示x轴的偏移量，正直表示向左，负值表示向右；yoff表示y轴的偏移量，正值向下，负值向上。
//            mLeftPopWindow.showAsDropDown(anchor,0,0);
            //第四个参数表示坐标原点在屏幕右上角位置。
//            mLeftPopWindow.showAsDropDown(anchor, 0, 0, Gravity.RIGHT | Gravity.TOP);
            mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
//                    if (timer != null)
//                        timer.cancel();

                    WindowManager.LayoutParams attributes = w.getAttributes();

                    attributes.alpha = 1f;

                    w.setAttributes(attributes);
                    mShadeView.setVisibility(View.GONE);

                    mPopWindow = null;

                }
            });

        }
//
    }

    @OnClick(R.id.search_container)
    public void onViewClicked() {
    }

    @Override
    public LibraryComponent getComponent() {
        return DaggerLibraryComponent.builder()
                .applicationComponent(ComponentHolder.getAppComponent())
                .activityModule(new ActivityModule(this))
                .libraryModule(new LibraryModule())
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
        if (mAdapter != null)
            mAdapter.unregisterAdapterDataObserver(mSumObserver);
    }

    @Override
    public void showContent(JsonBase<List<LibraryBaseData>> data) {
        List<LibraryBaseData> dataList = (List<LibraryBaseData>) data.getData();
        Log.d(TAG, "showContent: dataList = " + dataList);
        if (dataList != null) {
            mDatas.clear();
//            List<LibraryBaseData> list = mAllDatas.get(mCurrentLeftClassId);
//            if (list != null && list.size() == dataList.size()) {
//                for (int i = 0; i < list.size(); i++) {
//                    if (list.get(i).getId() == dataList.get(i).getId()) {
//                        dataList.get(i).setSelected(list.get(i).isSelected());
//                    }
//                }
//            }
//            mAllDatas.put(mCurrentLeftClassId, dataList);
            mDatas.addAll(dataList);
            mAdapter.setDatas(mDatas);
        }

    }

    @Override
    public void showLeftItem(JsonBase<List<JsonLibraryLeftItem>> resultData) {
        List<JsonLibraryLeftItem> dataList = (List<JsonLibraryLeftItem>) resultData.getData();
        mLeftItemInfos.clear();
        mLeftItemInfos.addAll(dataList);
        Log.d(TAG, "showLeftItem: dataList = " + dataList);
        Button[] buttons = new Button[dataList.size()];
        setVisibility(View.GONE, getLeftButtons());
        setLeftSelectedState(DEFAULT_STATE, getLeftButtons());
        for (int i = 0; i < dataList.size(); i++) {
            buttons[i] = getLeftButtons()[i];
            JsonLibraryLeftItem leftItem = dataList.get(i);
            buttons[i].setText(leftItem.getName());
        }
        setVisibility(View.VISIBLE, buttons);
//        initLeftItem(buttons);
        setLeftSelectedState(SELECTED_STATE, getLeftButtons()[mLeftCurrent]);

        mCurrentLeftClassName = mLeftItemInfos.get(0).getName();
        Log.d(TAG, "showLeftItem: mIsFirstClick = " + mIsFirstClick);
        mCurrentLeftClassId = mLeftItemInfos.get(0).getId();
        loadOnlineContentData();
//        if (mIsFirstClick) {
//            if (mTopCurrent == TOP_SERVICE) {
//                mServiceCurrentLeftClassId = mLeftItemInfos.get(0).getId();
//            } else {
//                mMaterailCurrentLeftClassId = mLeftItemInfos.get(0).getId();
//                mIsFirstClick = false;
//            }
//
//
//        } else {
//            if (mTopCurrent == TOP_SERVICE) {
//                mCurrentLeftClassId = mServiceCurrentLeftClassId;
//            } else mCurrentLeftClassId = mMaterailCurrentLeftClassId;
//            Log.d(TAG, "mCurrentLeftClassId = " + mCurrentLeftClassId);
//            loadLocalContentData();
//        }
    }

    protected void loadLocalContentData() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showDetails(JsonBase<LibraryBaseData> jsonBase) {

    }
}
