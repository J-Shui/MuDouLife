package com.mudoulife.ui.adapter.order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.common.Globals;
import com.mudoulife.data.net.response.ResultInfo;
import com.mudoulife.ui.fragment.main.CarryFragment;
import com.mudoulife.ui.fragment.main.ExpressFragment;
import com.mudoulife.ui.fragment.main.ShanghuFragment;
import com.mudoulife.ui.fragment.main.SubscriptionFragment;
import com.mudoulife.ui.fragment.main.WorkerFragment;

import java.util.List;

public abstract class OrderOuterAdapter extends RecyclerView.Adapter<OrderOuterAdapter.ViewHolder> {
    protected static final String TAG = "OrderOuterAdapter";
    protected Context mContext;
    private List<ResultInfo> mInfos;
    protected String mFragmentName;
    private OrderBaseAdapter mAdapter;

    public OrderOuterAdapter(Context mContext, List<ResultInfo> mInfos, String mFragmentName) {
        this.mContext = mContext;
        this.mInfos = mInfos;
        this.mFragmentName = mFragmentName;
    }

    public void setDatas(List<ResultInfo> mInfos) {
        this.mInfos = mInfos;
        notifyDataSetChanged();
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_out_item, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);
        holder.mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: mLeftBtn");
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v);
                }
            }
        });
        holder.mRightButton.setOnClickListener(new View.OnClickListener() {
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
    private void initHolderView(ViewHolder holder) {
        holder.initItemView(mContext, has4Item(),isShowLeftBtn());
    }


    protected abstract boolean isShowLeftBtn();

    protected abstract String getLeftString();

    protected abstract String getRightString();


    protected abstract boolean isEnableLeftButton(int status);
    protected boolean isEnableRightButton(ResultInfo info){
//        if (mFragmentName.equals(WorkerFragment.class.getName())) {
//            if (info.getStatus() > Globals.CRAFTSMAN_RECEIPT){
//                return true;
//            }
//        }
        return true;
    }

    protected abstract boolean isShowButtons(int status);

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ResultInfo info = mInfos.get(position);
        if (info != null) {
            viewHolder.mStatusTv.setText(info.getStatusName());
            viewHolder.mOrderNoTv.setText(info.getOrderNumber());

            mAdapter = new OrderBaseAdapter(mContext, info, mFragmentName);
//            switch (mType) {
//                case Globals.ORDER_QUERY_TYPE:
//                    mAdapter = new OrderQueryAdapter(mContext, info, mFragmentName);
//                    break;
//                case Globals.ORDER_PRICE_TYPE:
//                    mAdapter = new OrderPriceAdapter(mContext, info, mFragmentName);
//                    break;
//                case Globals.ORDER_FINISH_TYPE:
//                    mAdapter = new OrderFinishAdapter(mContext, info, mFragmentName);
//                    break;
//                case Globals.ORDER_MINE_QUERY_TYPE:
//                    mAdapter = new MineQueryAdapter(mContext, info, mFragmentName);
//                    break;
//                case Globals.ORDER_AFTER_SALE_TYPE:
//                    mAdapter = new AfterSaleHandlerAdapter(mContext, info, mFragmentName);
//                    break;
//            }
            if (!isShowLeftBtn()) {
                viewHolder.mLeftButton.setVisibility(View.GONE);
            } else {
                viewHolder.mLeftButton.setVisibility(View.VISIBLE);
            }
            viewHolder.mRightButton.setVisibility(View.VISIBLE);
            initClickButton(viewHolder, info);
            viewHolder.mLeftButton.setText(getLeftString());
            viewHolder.mRightButton.setText(getRightString());

            viewHolder.mLeftButton.setTag(position);
            viewHolder.mRightButton.setTag(position);
            viewHolder.recyclerView.setAdapter(mAdapter);
//            viewHolder.recyclerView.addItemDecoration(new DividerItemDecoration(mContext,VERTICAL));

//            if (mFragmentName.equals(WorkerFragment.class.getName())){
//
//            }else {
//                if (position == orderGoods.size() - 1) {
//                    if (!isShowLeftBtn()) {
//                        viewHolder.mLeftButton.setVisibility(View.GONE);
//                    } else {
//                        viewHolder.mLeftButton.setVisibility(View.VISIBLE);
//                    }
//                    viewHolder.mRightButton.setVisibility(View.VISIBLE);
//                    initClickButton(viewHolder, status);
//                    viewHolder.mLeftButton.setText(getLeftString());
//                    viewHolder.mRightButton.setText(getRightString());
//
//                    viewHolder.mLeftButton.setTag(position);
//                    viewHolder.mRightButton.setTag(position);
//                } else {
//                    viewHolder.mLeftButton.setVisibility(View.GONE);
//                    viewHolder.mRightButton.setVisibility(View.GONE);
//                }
//            }



//            viewHolder.recyclerView.setTag(i);
        }

    }
    protected abstract boolean isQuery();
    private void initClickButton(@NonNull ViewHolder viewHolder, ResultInfo info) {
        boolean isShowLeft = isEnableLeftButton(info.getStatus());
        boolean isShowButtons = isShowButtons(info.getStatus());
        if (!isShowButtons) {
            viewHolder.mLeftButton.setVisibility(View.GONE);
            viewHolder.mRightButton.setVisibility(View.GONE);
        } else {
            if (!isShowLeftBtn()) {
                viewHolder.mLeftButton.setVisibility(View.GONE);
            } else {
                viewHolder.mLeftButton.setVisibility(View.VISIBLE);
            }
            viewHolder.mRightButton.setVisibility(View.VISIBLE);
        }
        if (isQuery()){
            if (isShowLeft){
                viewHolder.mLeftButton.setBackground(mContext.getResources().getDrawable(R.drawable.clickable_button_bg));
                viewHolder.mLeftButton.setEnabled(true);
            }else {
                viewHolder.mLeftButton.setBackground(mContext.getResources().getDrawable(R.drawable.un_clickable_button_bg));
                viewHolder.mLeftButton.setEnabled(false);
            }
            if (isEnableRightButton(info)){
                viewHolder.mRightButton.setBackground(mContext.getResources().getDrawable(R.drawable.clickable_button_bg));
                viewHolder.mRightButton.setEnabled(true);
            }else {
                viewHolder.mRightButton.setBackground(mContext.getResources().getDrawable(R.drawable.un_clickable_button_bg));
                viewHolder.mRightButton.setEnabled(false);
            }
        }else {
            if (isShowLeft) {
                viewHolder.mLeftButton.setBackground(mContext.getResources().getDrawable(R.drawable.clickable_button_bg));
                viewHolder.mLeftButton.setEnabled(true);
                viewHolder.mRightButton.setBackground(mContext.getResources().getDrawable(R.drawable.un_clickable_button_bg));
                viewHolder.mRightButton.setEnabled(false);
            } else {
                viewHolder.mLeftButton.setBackground(mContext.getResources().getDrawable(R.drawable.un_clickable_button_bg));
                viewHolder.mLeftButton.setEnabled(false);
                viewHolder.mRightButton.setBackground(mContext.getResources().getDrawable(R.drawable.clickable_button_bg));
                viewHolder.mRightButton.setEnabled(true);
            }
        }

    }

    private boolean has4Item() {
        if (mFragmentName.equals(WorkerFragment.class.getName())) {
            return false;
        }
        if (mFragmentName.equals(SubscriptionFragment.class.getName())) {
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

    @Override
    public int getItemCount() {
        if (mInfos != null && mInfos.size() > 0)
            return mInfos.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private TextView mOrderNoTv;
        private TextView mStatusTv;
        private Button mLeftButton;
        private Button mRightButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_view);
            mOrderNoTv = itemView.findViewById(R.id.order_number_id);
            mStatusTv = itemView.findViewById(R.id.order_status);
            mLeftButton = itemView.findViewById(R.id.button_left);
            mRightButton = itemView.findViewById(R.id.button_right);

            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        }
        void initItemView(Context context, boolean has4Item, boolean isShowLeftBtn) {
            if (!isShowLeftBtn) {
                mLeftButton.setVisibility(View.GONE);
            }
        }
    }
}
