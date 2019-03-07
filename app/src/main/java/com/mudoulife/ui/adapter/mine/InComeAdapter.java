package com.mudoulife.ui.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.data.net.response.JsonOrderGoods;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.ui.fragment.main.WorkerFragment;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/13
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.adapter.mine
 * project : MuDouLife
 */
public class InComeAdapter extends RecyclerView.Adapter<InComeAdapter.ViewHolder> {
    private Context mContext;
    private List<ResultInfo> mDatas;
    private String mMainFragmentName;

    public InComeAdapter(Context context, List<ResultInfo> datas,String fragmentName) {
        this.mContext = context;
        this.mDatas = datas;
        mMainFragmentName = fragmentName;
    }

    public void setmDatas(List<ResultInfo> datas) {
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
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_mine_income_list, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(i);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ResultInfo data = mDatas.get(position);
        if (data != null) {
            viewHolder.mOrderNoTv.setText(data.getOrderNumber());
            if (mMainFragmentName.equals(WorkerFragment.class.getName())){
                viewHolder.mNameTv.setText(data.getServiceName());
                viewHolder.mPriceTv.setText("+" + data.getTotal());
            }else {
                StringBuilder builder = new StringBuilder();
                List<JsonOrderGoods> orderGoods = data.getOrderGoods();
                double total = 0;
                if (orderGoods != null && orderGoods.size() > 0){
                    for (int i = 0; i < orderGoods.size(); i++) {
                        total += orderGoods.get(i).getPrice() * orderGoods.get(i).getNumber();
                        if (i == orderGoods.size() - 1){
                            builder.append(orderGoods.get(i).getGoodsName());
                        }else {
                            builder.append(orderGoods.get(i).getGoodsName() + "、");
                        }
                    }
                    viewHolder.mNameTv.setText(builder.toString());
                }else {
                    viewHolder.mNameTv.setText(data.getServiceName());
                }
                viewHolder.mPriceTv.setText("+" + total);
            }

        }
    }

    @Override
    public int getItemCount() {
        if (mDatas != null)
            return mDatas.size();
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mOrderNoTv;
        private TextView mNameTv;
        private TextView mPriceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mOrderNoTv = itemView.findViewById(R.id.order_number_id);
            mNameTv = itemView.findViewById(R.id.name_id);
            mPriceTv = itemView.findViewById(R.id.content_2);
        }
    }
}
