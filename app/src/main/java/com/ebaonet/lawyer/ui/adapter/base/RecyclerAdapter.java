package com.ebaonet.lawyer.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ebaonet.lawyer.ui.adapter.viewholder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于RecyclerView的Adapter
 */
public abstract  class  RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected  List<T> mDataSet = new ArrayList<>();

    protected OnItemClickListener mOnItemClickListener;

    private int mItemLayoutId;

    public RecyclerAdapter(int layoutId) {
        mItemLayoutId = layoutId;
    }

    public RecyclerAdapter(int layoutId, List<T> datas) {
        mItemLayoutId = layoutId;
        addItems(datas);
    }

    public void addItem(T item) {
        mDataSet.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<T> items) {
        if (items != null) {
            mDataSet.addAll(items);
            notifyDataSetChanged();
        }
    }
    public void refreshItems(List<T> items){
        mDataSet=items;
        notifyDataSetChanged();
    }

    public void addItemToHead(T item) {
        mDataSet.add(0, item);
        notifyDataSetChanged();
    }

    public void addItemsToHead(List<T> items) {
        mDataSet.addAll(0, items);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDataSet.remove(position);
        notifyDataSetChanged();
    }

    public void remove(T item) {
        mDataSet.remove(item);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet==null?0: mDataSet.size();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final T item = getItem(position);
        // 绑定数据
        onBindData(holder, position, item);
        // 设置单击事件
        setupItemClickListener(holder, position);
    }

    protected View inflateItemView(ViewGroup viewGroup, int viewType) {
        int itemLayout = getItemLayout(viewType);
        Context context = viewGroup.getContext();
        return LayoutInflater.from(context).inflate(itemLayout,
                viewGroup, false);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(inflateItemView(parent, viewType));
    }

    protected int getItemLayout(int type) {
        return mItemLayoutId;
    }

    /**
     * 绑定数据到Item View上
     *
     * @param viewHolder
     * @param position   数据的位置
     * @param item       数据项
     */
    protected abstract void onBindData(RecyclerViewHolder viewHolder, int position, T item);

    protected void setupItemClickListener(RecyclerViewHolder viewHolder, final int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
