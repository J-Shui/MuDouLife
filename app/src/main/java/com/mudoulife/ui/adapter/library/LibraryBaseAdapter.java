package com.mudoulife.ui.adapter.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.data.net.response.LibraryBaseData;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.adapter
 * project : MuDouLife
 */
public abstract class LibraryBaseAdapter<V extends LibraryBaseData> extends RecyclerView.Adapter<LibraryBaseAdapter.ViewHolder> {
    private static final String TAG = "LibraryBaseAdapter";
    private List<V> mDatas;
    private Context mContext;
    private int mCurrentPosition;

    public LibraryBaseAdapter(List<V> data, Context context) {
        this.mDatas = data;
        this.mContext = context;
        notifyDataSetChanged();
        Log.d(TAG, "data = " + data);
    }

    public void setDatas(List<V> datas) {
        this.mDatas = datas;
        Log.d(TAG, "mDatas = " + mDatas);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int position) {
        mCurrentPosition = position;
        Log.d(TAG, "onCreateViewHolder: position = " + position);
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_library_recycler_item, viewGroup, false);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.onItemClick(v,position);
//                }
//            }
//        });
        view.setOnClickListener(mOnClickListener);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLongClick(v,position);
                }
                return true;
            }
        });
        ViewHolder holder = new ViewHolder(view);
        holder.mContent3ReduceIv.setOnClickListener(mOnClickListener);
        holder.mContent3AddIv.setOnClickListener(mOnClickListener);
        holder.mSelectIv.setOnClickListener(mOnClickListener);
        holder.mContent4ReduceIv.setOnClickListener(mOnClickListener);
        holder.mContent4AddIv.setOnClickListener(mOnClickListener);
        init(holder);
        return holder;
    }
    protected void initWorkerView(ViewHolder viewHolder,V data){
        double price = data.getPrice();
        int number = data.getNumber();
        double total = data.getTotal();
        String priceUnit = data.getPriceUnit();
        viewHolder.mContent1Tv.setText(data.getName());
        viewHolder.mContent2Tv.setText(price + priceUnit);
    }
    protected void initExpressView(ViewHolder viewHolder,V data){
        double startPrice = data.getStartPrice();
        double exceedingPrice = data.getExceedingPrice();
        String priceUnit = data.getPriceUnit();
//        if (priceUnit.isEmpty() || priceUnit.equals("") || priceUnit.equals("null")){
//            priceUnit = "/每公里";
//        }
        viewHolder.mContent1Tv.setText(startPrice + "/每公里");
        viewHolder.mContent2Tv.setText(exceedingPrice + "/每公里");
    }
    protected void initCarrayView(ViewHolder viewHolder,V data){
        double price = data.getPrice();
        int number = data.getNumber();
        String name = data.getName();
        int floor = data.getFloor();
        viewHolder.mContent1Tv.setText(name);
        viewHolder.mContent2Tv.setText(price + "/" +data.getUnit());
        viewHolder.mContent4Container.setVisibility(View.VISIBLE);
        viewHolder.mContent4ValueTv.setText(String.valueOf(floor));
        viewHolder.mContent4ReduceIv.setEnabled(false);
        viewHolder.mContent4AddIv.setEnabled(false);
//        if (floor > 0){
//            viewHolder.mContent4ReduceIv.setEnabled(true);
//        } else{
//            viewHolder.mContent4ReduceIv.setEnabled(false);
//        }
    }
    protected abstract void bindView(ViewHolder viewHolder,V data);

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,int position) {
        V data = mDatas.get(position);
        Log.d(TAG,"data = " + data);
        final int adapterPosition = viewHolder.getAdapterPosition();
        mCurrentPosition = adapterPosition;
        if (data != null) {
            double price = data.getPrice();
            int number = data.getNumber();
            double total = data.getTotal();

//            viewHolder.mContent1Tv.setText(data.getName());
//            viewHolder.mContent2Tv.setText(getPrice(price));
            bindView(viewHolder,data);
            viewHolder.mContent3Container.setVisibility(View.VISIBLE);
            viewHolder.mContent3ValueTv.setText(String.valueOf(number));
            if (number > 0){
                viewHolder.mContent3ReduceIv.setEnabled(true);
            } else{
                viewHolder.mContent3ReduceIv.setEnabled(false);
            }

            viewHolder.mSelectIv.setTag(adapterPosition);
            viewHolder.mContent4ReduceIv.setTag(adapterPosition);
            viewHolder.mContent4AddIv.setTag(adapterPosition);
            viewHolder.mContent3ReduceIv.setTag(adapterPosition);
            viewHolder.mContent3AddIv.setTag(adapterPosition);


            if (data.isSelected()){
                viewHolder.mSelectIv.setImageLevel(1);
                total += price * number;
//
            }else{
                viewHolder.mSelectIv.setImageLevel(0);
                total -= price * number;
                if (total <= 0){
                    total = 0;
                }
            }
        }
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v,mCurrentPosition);
            }
        }
    };

    protected abstract String getPrice(double price);
    protected abstract void init(ViewHolder holder);

    @Override
    public int getItemCount() {
        if (mDatas != null){
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mContent1Tv;
        private TextView mContent2Tv;
        private ImageView mSelectIv;
        private ImageView mContent3ReduceIv;
        private TextView mContent3ValueTv;
        private ImageView mContent3AddIv;
        private ImageView mContent4ReduceIv;
        private TextView mContent4ValueTv;
        private ImageView mContent4AddIv;
        private final LinearLayout mContent3Container;
        private LinearLayout mContent4Container;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mContent1Tv = itemView.findViewById(R.id.content_1);
            mContent2Tv = itemView.findViewById(R.id.content_2);

            mContent3Container = itemView.findViewById(R.id.content_3_container);
            mContent3ReduceIv = mContent3Container.findViewById(R.id.number_reduce);
            mContent3ValueTv = mContent3Container.findViewById(R.id.number_value);
            mContent3AddIv = mContent3Container.findViewById(R.id.number_add);

            mSelectIv = itemView.findViewById(R.id.library_recycler_select);
            mContent4Container = itemView.findViewById(R.id.content_4_container);
            mContent4ReduceIv = mContent4Container.findViewById(R.id.floor_reduce);
            mContent4ValueTv = mContent4Container.findViewById(R.id.floor_value);
            mContent4AddIv = mContent4Container.findViewById(R.id.floor_add);


        }
        void setFloorVisible(){
            mContent4Container.setVisibility(View.VISIBLE);
        }
        void setNumberVisible(){
            mContent3Container.setVisibility(View.VISIBLE);
        }
    }

}
