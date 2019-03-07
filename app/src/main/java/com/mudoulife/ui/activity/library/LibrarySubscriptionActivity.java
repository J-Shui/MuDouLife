package com.mudoulife.ui.activity.library;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.ui.activity.LibraryBaseActivity;
import com.mudoulife.ui.adapter.library.LibrarySubscriptionAdapter;

public class LibrarySubscriptionActivity extends LibraryBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        super.init();
        mTopContainer.setVisibility(View.GONE);
        mAdapter = new LibrarySubscriptionAdapter(mDatas,this);
    }

    @Override
    protected void loadLeftItemData() {

    }

    @Override
    protected void loadOnlineContentData() {

    }

    @Override
    protected Button[] getLeftButtons() {
        return new Button[]{mLeftItem1};
    }


    @Override
    protected String[] getLeftTexts() {
        return new String[]{getResources().getString(R.string.library_subscription_item_1)};
    }

    @Override
    protected void initWindowAdapter() {

    }

    @Override
    protected int getType() {
        return Globals.LIBRARY_CARRY_TYPE;
    }
}
