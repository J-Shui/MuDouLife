package com.mudoulife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.AfterSaleInfo;
import com.mudoulife.ui.fragment.main.CarryFragment;
import com.mudoulife.ui.fragment.main.ExpressFragment;
import com.mudoulife.ui.fragment.main.ShanghuFragment;
import com.mudoulife.ui.fragment.main.WorkerFragment;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/13
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.adapter
 * project : MuDouLife
 */
public class AfterSaleHandlerAdapter extends RecyclerView.Adapter<AfterSaleHandlerAdapter.ViewHolder> {
    protected static final String TAG = "AfterSaleHandlerAdapter";
    protected Context mContext;
    protected List<AfterSaleInfo> mDatas;
    protected String mFragmentName;

    public AfterSaleHandlerAdapter(Context context, List<AfterSaleInfo> data, String fragmentName) {
        Log.d(TAG, "data = " + data);
        this.mContext = context;
        this.mDatas = data;
        mFragmentName = fragmentName;
    }

    public void setDatas(List<AfterSaleInfo> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public AfterSaleHandlerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int position) {
        final View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_after_handle_item_view, viewGroup, false);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.onItemClick(v);
//                }
//            }
//        });
//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.onItemLongClick(v);
//                }
//                return true;
//            }
//        });

        ViewHolder holder = new ViewHolder(view);
        holder.mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: mLeftBtn");
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v);
                }
            }
        });
        holder.mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: mRightBtn");
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v);
                }
            }
        });
        initHolderView(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AfterSaleHandlerAdapter.ViewHolder viewHolder, int position) {
//        ResultInfo data = mData.get(position);
        Log.d(TAG, "onBindViewHolder: mData = " + mDatas);
        AfterSaleInfo info = mDatas.get(position);
        int status = info.getStatus();
        String imgUrl = info.getPicture();
        String time = info.getCreateTime();
        String statusName = info.getStatusName();
        String orderNumber = info.getOrderNumber();
        String name = info.getName();
        viewHolder.mItemValue1Tv.setText(name);

        viewHolder.mOrderNoTv.setText(orderNumber);
        viewHolder.mStatusTv.setText(statusName);
        if (mFragmentName.equals(WorkerFragment.class.getName())) {
            viewHolder.mItemValue2Tv.setVisibility(View.GONE);
            viewHolder.mItemName2Tv.setVisibility(View.GONE);
            viewHolder.mItemValue3Tv.setText(time);
            Log.d(TAG, "onBindViewHolder: isShowLjcl = " + info.isShowLjcl());
            Log.d(TAG, "onBindViewHolder: isShowClwc = " + info.isShowClwc());
            if (info.isShowLjcl()) {
                viewHolder.mLeftBtn.setBackground(mContext.getResources().getDrawable(R.drawable.clickable_button_bg));
                viewHolder.mRightBtn.setBackground(mContext.getResources().getDrawable(R.drawable.un_clickable_button_bg));
                viewHolder.mLeftBtn.setEnabled(true);
                viewHolder.mRightBtn.setEnabled(false);
            }
            if (info.isShowClwc()) {
                viewHolder.mLeftBtn.setBackground(mContext.getResources().getDrawable(R.drawable.un_clickable_button_bg));
                viewHolder.mRightBtn.setBackground(mContext.getResources().getDrawable(R.drawable.clickable_button_bg));
                viewHolder.mRightBtn.setEnabled(true);
                viewHolder.mLeftBtn.setEnabled(false);
            }
        } else {
            viewHolder.mItemValue2Tv.setText(info.getSpec());
            viewHolder.mItemValue3Tv.setText("x" + info.getNumber());
            viewHolder.mItemValue4Tv.setText(time);
            Log.d(TAG, "onBindViewHolder:  = " + info.isShowLjcl());
            if (info.isShowLjcl()) {
                viewHolder.mLeftBtn.setBackground(mContext.getResources().getDrawable(R.drawable.clickable_button_bg));
                viewHolder.mRightBtn.setBackground(mContext.getResources().getDrawable(R.drawable.un_clickable_button_bg));
                viewHolder.mLeftBtn.setEnabled(true);
                viewHolder.mRightBtn.setEnabled(false);
            }
            if (info.isShowClwc()) {
                viewHolder.mLeftBtn.setBackground(mContext.getResources().getDrawable(R.drawable.un_clickable_button_bg));
                viewHolder.mRightBtn.setBackground(mContext.getResources().getDrawable(R.drawable.clickable_button_bg));
                viewHolder.mRightBtn.setEnabled(true);
                viewHolder.mLeftBtn.setEnabled(false);
            }
            Log.d(TAG, "status = " + status);

        }
        viewHolder.mLeftBtn.setTag(position);
        viewHolder.mRightBtn.setTag(position);
        RequestOptions options = new RequestOptions();
        Log.d(TAG, "onBindViewHolder: url = " + Globals.BASE_URL + imgUrl);
        Glide.with(mContext)
                .asBitmap()
                .load(Globals.BASE_URL + imgUrl)
                .apply(options.placeholder(R.mipmap.default_img))
                .apply(options.error(R.mipmap.default_img))
                .into(viewHolder.mImgView);
//        if (viewHolder.mItemValue4Tv.getVisibility() == View.VISIBLE) {
//            viewHolder.mItemValue3Tv.setText(mData.getNumber() + "");
//            viewHolder.mItemValue4Tv.setText(mData.getCreateTime());
//        } else {
//            viewHolder.mItemValue3Tv.setText(mData.getCreateTime());
//        }


    }


    private boolean has4Item() {
        if (mFragmentName.equals(WorkerFragment.class.getName())) {
            return false;
        }
        if (mFragmentName.equals(ExpressFragment.class.getName())) {
            return true;
        }
        if (mFragmentName.equals(ShanghuFragment.class.getName())) {
            return true;
        }
        if (mFragmentName.equals(CarryFragment.class.getName())) {
            return true;
        }
        return false;
    }

    private void initHolderView(ViewHolder holder) {
        holder.initItemView(mContext, has4Item());
    }


    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mOrderNoTv;
        private TextView mStatusTv;
        private TextView mItemName2Tv;
        private TextView mItemName3Tv;
        private TextView mItemName4Tv;
        private TextView mItemValue1Tv;
        private TextView mItemValue2Tv;
        private TextView mItemValue3Tv;
        private TextView mItemValue4Tv;
        private ImageView mImgView;
        private Button mLeftBtn;
        private Button mRightBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mOrderNoTv = itemView.findViewById(R.id.order_number_id);
            mStatusTv = itemView.findViewById(R.id.order_status);
            mItemValue1Tv = itemView.findViewById(R.id.item_value_1);
            mItemValue2Tv = itemView.findViewById(R.id.item_value_2);
            mItemValue3Tv = itemView.findViewById(R.id.item_value_3);
            mItemValue4Tv = itemView.findViewById(R.id.item_value_4);
            mItemName2Tv = itemView.findViewById(R.id.item_name_2);
            mItemName3Tv = itemView.findViewById(R.id.item_name_3);
            mItemName4Tv = itemView.findViewById(R.id.item_name_4);
            mImgView = itemView.findViewById(R.id.img_id);
            mLeftBtn = itemView.findViewById(R.id.button_left);
            mRightBtn = itemView.findViewById(R.id.button_right);
        }

        void initItemView(Context context, boolean has4Item) {
            if (has4Item) {
                mItemName3Tv.setText(context.getResources().getString(R.string.number));
                mItemName4Tv.setVisibility(View.VISIBLE);
                mItemValue4Tv.setVisibility(View.VISIBLE);
                mItemName4Tv.setText(context.getResources().getString(R.string.createTime));
            } else {
                mItemName3Tv.setText(context.getResources().getString(R.string.createTime));
                mItemName4Tv.setVisibility(View.GONE);
                mItemValue4Tv.setVisibility(View.GONE);
            }
        }
    }
}
