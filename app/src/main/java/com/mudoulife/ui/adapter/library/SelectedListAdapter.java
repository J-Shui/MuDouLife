package com.mudoulife.ui.adapter.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.LibraryBaseData;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/12
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.adapter.library
 * project : MuDouLife
 */
public class SelectedListAdapter<V extends LibraryBaseData> extends RecyclerView.Adapter<SelectedListAdapter.ViewHolder> {
    private List<V> mDatas;
    private Context mContext;
    private int mType;

    public SelectedListAdapter(List<V> mData, Context mContext, int mType) {
        this.mDatas = mData;
        this.mContext = mContext;
        this.mType = mType;
    }

    public void setDatas(List<V> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(View view);
        void onItemLongClick(View view);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_library_label_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        initView(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        LibraryBaseData data = mDatas.get(position);
        if (data != null) {
            int number = data.getNumber();
            double price = data.getPrice();
            int floor = data.getFloor();
            String name = data.getName();
            double total = data.getTotal();
            String priceUnit = data.getPriceUnit();

            switch (mType) {
                case Globals.LIBRARY_CARRY_TYPE:
                    viewHolder.mContent1ValueTv.setText(name);
                    viewHolder.mContent2ValueTv.setText(price+data.getUnit());
                    viewHolder.mContent3ValueTv.setText(number+"");
                    viewHolder.mContent4ValueTv.setText(floor+"");
                    break;
                case Globals.LIBRARY_EXPRESS_TYPE:
                    viewHolder.mContent1ValueTv.setText(data.getClassName());
                    viewHolder.mContent2ValueTv.setText(data.getStartPrice() + "/每公里");
                    viewHolder.mContent3ValueTv.setText(data.getExceedingPrice() + "/每公里");
                    viewHolder.mContent4ValueTv.setText(number + "");
                    break;
                case Globals.LIBRARY_SUBSCRIPTION_TYPE:
                    break;
                case Globals.LIBRARY_WORKER_SERVICE_TYPE:
                case Globals.LIBRARY_WORKER_MATERIAL_TYPE:
                    viewHolder.mContent1ValueTv.setText(name);
                    viewHolder.mContent2ValueTv.setText(price+priceUnit);
                    viewHolder.mContent3ValueTv.setText(number + "");
                    break;
            }

//            if (number > 0){
//                viewHolder.mContent3ValueTv.setVisibility(View.VISIBLE);
//                viewHolder.mContent3ValueTv.setText(String.valueOf(number));
//            }else{
//                viewHolder.mContent3ValueTv.setVisibility(View.INVISIBLE);
//            }
//            viewHolder.mContent4ValueTv.setText(floor+"");

//            viewHolder.mContent4ValueTv.setTag(position);
//            viewHolder.mFloorAddIv.setTag(position);
//            viewHolder.mNumberReduceIv.setTag(position);
//            viewHolder.mNumberAddIv.setTag(position);

        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v);
            }
        }
    };

    private void initView(ViewHolder holder) {
//        holder.mNumberReduceIv.setOnClickListener(mOnClickListener);
//        holder.mNumberAddIv.setOnClickListener(mOnClickListener);
//        holder.mContent4ValueTv.setOnClickListener(mOnClickListener);
//        holder.mFloorAddIv.setOnClickListener(mOnClickListener);
        switch (mType) {
            case Globals.LIBRARY_CARRY_TYPE:
                holder.setCarryView();
                break;
            case Globals.LIBRARY_EXPRESS_TYPE:
                holder.setExpressView();
                break;
            case Globals.LIBRARY_SUBSCRIPTION_TYPE:
                holder.setSubView();
                break;
            case Globals.LIBRARY_WORKER_SERVICE_TYPE:
                holder.setWorkerView();
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mContent1ValueTv;
        private TextView mContent2ValueTv;
//        private ImageView mNumberReduceIv;
        private TextView mContent3ValueTv;
//        private ImageView mNumberAddIv;
//        private ImageView mContent4ValueTv;
        private TextView mContent4ValueTv;
//        private ImageView mFloorAddIv;
//        private final LinearLayout mNumberContainer;
//        private LinearLayout mFloorContainer;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mContent1ValueTv = itemView.findViewById(R.id.content_1);
            mContent2ValueTv = itemView.findViewById(R.id.content_2);
            mContent3ValueTv = itemView.findViewById(R.id.content_3);
            mContent4ValueTv = itemView.findViewById(R.id.content_4);

//            mNumberContainer = itemView.findViewById(R.id.number_container);
//            mNumberReduceIv = mNumberContainer.findViewById(R.id.number_reduce);
//            mContent3ValueTv = mNumberContainer.findViewById(R.id.number_value);
//            mNumberAddIv = mNumberContainer.findViewById(R.id.number_add);

//            mFloorContainer = itemView.findViewById(R.id.floor_container);
//            mContent4ValueTv = mFloorContainer.findViewById(R.id.floor_reduce);
//            mContent4ValueTv = mFloorContainer.findViewById(R.id.floor_value);
//            mFloorAddIv = mFloorContainer.findViewById(R.id.floor_add);


        }
        void setFloorVisible(){
//            mFloorContainer.setVisibility(View.VISIBLE);
            mContent4ValueTv.setVisibility(View.VISIBLE);
        }
        void setNumberVisible(){
//            mNumberContainer.setVisibility(View.VISIBLE);
            mContent3ValueTv.setVisibility(View.VISIBLE);
        }

        void setWorkerView(){
            mContent3ValueTv.setVisibility(View.VISIBLE);
            mContent4ValueTv.setVisibility(View.GONE);
        }
        void setExpressView(){
//            mNumberContainer.setVisibility(View.VISIBLE);
            mContent3ValueTv.setVisibility(View.VISIBLE);
            mContent4ValueTv.setVisibility(View.VISIBLE);
        }
        void setCarryView(){
//            mNumberContainer.setVisibility(View.VISIBLE);
            mContent3ValueTv.setVisibility(View.VISIBLE);
            mContent4ValueTv.setVisibility(View.VISIBLE);
        }
        void setSubView(){

        }
    }

}
