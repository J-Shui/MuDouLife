package com.mudoulife.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mudoulife.R;
import com.mudoulife.data.net.response.LibraryBaseData;
import com.mudoulife.ui.adapter.library.CommitPriceAdapter;
import com.mudoulife.ui.adapter.library.LibraryWorkerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private List<LibraryBaseData> mDatas;
    private LibraryWorkerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mDatas = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LibraryWorkerAdapter(mDatas,this);
        recyclerView.setAdapter(adapter);

        mDatas.clear();
        for (int i = 0; i < 10; i++) {
            LibraryBaseData data = new LibraryBaseData();
            data.setId(i);
            mDatas.add(data);
        }

        adapter.setDatas(mDatas);
    }
}
