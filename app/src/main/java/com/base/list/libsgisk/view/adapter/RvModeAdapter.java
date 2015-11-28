package com.base.list.libsgisk.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/11/28.
 */
public class RvModeAdapter extends RecyclerView.Adapter<RvModeAdapter.BaseViewHolder> {

    public RvModeAdapter() {

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * ViewHolder的基类
     */
    class BaseViewHolder extends RecyclerView.ViewHolder{

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 简单文本的ViewHolder
     */
    private class SingleTextViewHolder extends BaseViewHolder{

        public SingleTextViewHolder(View itemView) {
            super(itemView);
        }
    }

}
