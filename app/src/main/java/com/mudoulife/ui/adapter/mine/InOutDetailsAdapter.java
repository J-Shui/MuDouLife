package com.mudoulife.ui.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mudoulife.R;
import com.mudoulife.common.Globals;

import java.util.List;

public class InOutDetailsAdapter extends RecyclerView.Adapter<InOutDetailsAdapter.ViewHolder> {
    private List<String> mUrl;
    private Context mContext;

    public InOutDetailsAdapter(List<String> mUrl, Context mContext) {

        this.mUrl = mUrl;
        this.mContext = mContext;
    }

    public List<String> getmUrl() {
        return mUrl;
    }

    public void setmUrl(List<String> mUrl) {
        this.mUrl = mUrl;
        notifyDataSetChanged();
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.layout_in_out_details_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String url = mUrl.get(i);
        RequestOptions options = new RequestOptions();
        Glide.with(mContext)
                .asBitmap()
                .load(Globals.BASE_URL + url)
                .apply(options.placeholder(R.mipmap.default_img))
                .apply(options.error(R.mipmap.default_img))
                .into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        if (mUrl != null) {
            return mUrl.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.recycler_img);
        }
    }
}
