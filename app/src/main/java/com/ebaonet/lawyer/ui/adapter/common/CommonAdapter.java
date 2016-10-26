package com.ebaonet.lawyer.ui.adapter.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by dong on 2015/6/8.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mData;
    private LayoutInflater mInflater;
    private int mLayId;
    private int position = -1;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CommonAdapter(Context context, List<T> data, int layId) {
        this.mContext = context;
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
        this.mLayId = layId;
    }

    public void refresh(List<T> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void addList(List<T> mData,boolean isClear) {
        if(isClear){
          this.mData.clear();
        }
        this.mData.addAll(mData);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
                mLayId, position);
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }


    public abstract void convert(ViewHolder holder, T t, int position);


}
