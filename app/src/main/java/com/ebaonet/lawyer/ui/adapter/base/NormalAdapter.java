
package com.ebaonet.lawyer.ui.adapter.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.ebaonet.lawyer.ui.adapter.viewholder.NormalViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 用于ListView和GridView的Adapter
 */
public abstract class NormalAdapter<T> extends BaseAdapter {

    protected final List<T> mDataSet = new ArrayList<>();

    private int mItemLayoutId;

    public NormalAdapter(@LayoutRes int layoutId) {
        mItemLayoutId = layoutId;
    }

    public NormalAdapter(@LayoutRes int layoutId, @NonNull List<T> datas) {
        mItemLayoutId = layoutId;
        mDataSet.addAll(datas);
    }

    public void addItem(T item) {
        mDataSet.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<T> items) {
        mDataSet.addAll(items);
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

    public void clear() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public T getItem(int position) {
        return mDataSet.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    protected int getItemLayout(int type) {
        return mItemLayoutId;
    }

    /**
     * 封装getView逻辑,将根据viewType获取布局资源、解析布局资源、创建ViewHolder等逻辑封装起来,简化使用流程
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        NormalViewHolder viewHolder = NormalViewHolder.get(convertView, parent, getItemLayout(viewType));
        // 绑定数据
        onBindData(viewHolder, position, getItem(position));
        return viewHolder.getItemView();
    }

    /**
     * 绑定数据到Item View上
     *
     * @param viewHolder
     * @param position   数据的位置
     * @param itemData   数据项
     */
    protected abstract void onBindData(NormalViewHolder viewHolder, int position, T itemData);

}
