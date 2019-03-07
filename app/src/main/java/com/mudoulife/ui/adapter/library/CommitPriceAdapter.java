package com.mudoulife.ui.adapter.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.LibraryBaseData;

import java.util.List;


public class CommitPriceAdapter extends RecyclerView.Adapter<CommitPriceAdapter.ViewHolder> {
    private static final String TAG = "CommitPriceAdapter";
    private Context mContext;
    private List<LibraryBaseData> mDatas;
    private int mType;

    public CommitPriceAdapter(Context context, List<LibraryBaseData> data,int type) {
        this.mContext = context;
        this.mDatas = data;
        this.mType = type;
        Log.d(TAG, "CommitPriceAdapter: ");
    }

    public void setmDatas(List<LibraryBaseData> mDatas) {
        this.mDatas = mDatas;
        Log.d(TAG, "setmDatas: mDatas = " + mDatas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_window_commit_label, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        LibraryBaseData data = mDatas.get(position);
        Log.d(TAG, "onBindViewHolder: data = " + data);
        if (data != null) {
            int number = data.getNumber();
            double price = data.getPrice();
            int floor = data.getFloor();
            String name = data.getName();
            String priceUnit = data.getPriceUnit();
            switch (mType){
                case Globals.LIBRARY_WORKER_SERVICE_TYPE:
                    viewHolder.mItem1.setText(name);
                    viewHolder.mItem2.setText(String.valueOf(price) + priceUnit);
                    viewHolder.mItem2.setText(number + "");
                    break;
                case Globals.LIBRARY_WORKER_MATERIAL_TYPE:
                    viewHolder.mItem1.setText(String.valueOf(name));
                    viewHolder.mItem2.setText(String.valueOf(price) + priceUnit);
                    viewHolder.mItem3.setText(String.valueOf(number));
                    break;
                case Globals.LIBRARY_EXPRESS_TYPE:
                    viewHolder.mItem1.setText(String.valueOf(data.getId()));
                    viewHolder.mItem2.setText(String.valueOf(name));
                    viewHolder.mItem3.setText(String.valueOf(price) + priceUnit);
                    viewHolder.mItem4.setText(String.valueOf(number));
                    break;
                case Globals.LIBRARY_CARRY_TYPE:
                    break;
            }
//            viewHolder.mItem1.setText(name);
//            viewHolder.mItem2.setText(price + "");
//            viewHolder.mItem4.setText(String.valueOf(number));
//            viewHolder.mItem3.setText(floor + "");

        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mDatas.size());

        return mDatas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mItem1;
        private TextView mItem2;
        private TextView mItem4;
        private TextView mItem3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mItem1 = itemView.findViewById(R.id.label_item_1);
            mItem2 = itemView.findViewById(R.id.label_item_2);
            mItem3 = itemView.findViewById(R.id.label_item_3);
            mItem4 = itemView.findViewById(R.id.label_item_4);

            switch (mType){
                case Globals.LIBRARY_WORKER_SERVICE_TYPE:
                    mItem1.setText(mContext.getResources().getString(R.string.material_name));
                    mItem2.setText(mContext.getResources().getString(R.string.price));
                    mItem3.setText(mContext.getResources().getString(R.string.number));
                    mItem4.setVisibility(View.GONE);
                    break;
                case Globals.LIBRARY_WORKER_MATERIAL_TYPE:
                    mItem1.setText(mContext.getResources().getString(R.string.material_name));
                    mItem2.setText(mContext.getResources().getString(R.string.price));
                    mItem3.setText(mContext.getResources().getString(R.string.number));
                    mItem4.setVisibility(View.GONE);
                    break;
                case Globals.LIBRARY_EXPRESS_TYPE:
                    mItem1.setText(mContext.getResources().getString(R.string.car_model));
                    mItem2.setText(mContext.getResources().getString(R.string.starting_price));
                    mItem3.setText(mContext.getResources().getString(R.string.out_of_range));
                    mItem4.setText(mContext.getResources().getString(R.string.out_of_km));
                    break;
                case Globals.LIBRARY_CARRY_TYPE:
                    mItem1.setText(mContext.getResources().getString(R.string.car_model));
                    mItem2.setText(mContext.getResources().getString(R.string.starting_price));
                    mItem3.setText(mContext.getResources().getString(R.string.out_of_range));
                    mItem4.setText(mContext.getResources().getString(R.string.out_of_km));
                    break;
            }
        }
    }
}
