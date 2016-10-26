package com.ebaonet.lawyer.ui.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;


import com.ebaonet.lawyer.ui.adapter.common.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lucky on 16/4/2.
 */
public abstract class ExpandableAdapter<T> extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mParents;
    private List<List<T>> mChilds;
    private Map<String, List<T>> mData;
    private int mParentLayId;
    private int mChildLayId;

    public ExpandableAdapter(Context context, Map<String, List<T>> data, int parentLayId, int childLayId) {
        this.mContext = context;
        this.mData = data;
        this.mParentLayId = parentLayId;
        this.mChildLayId = childLayId;
        this.mParents = new ArrayList();
        this.mChilds = new ArrayList();
        for (String key : this.mData.keySet()) {
            this.mParents.add(key);
            this.mChilds.add(this.mData.get(key));
        }
    }

    @Override
    public int getGroupCount() {
        return mParents.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = this.mParents.get(groupPosition);
        int size = this.mData.get(key).size();
        return size;
    }

    @Override
    public abstract T getGroup(int groupPosition);

    @Override
    public abstract T getChild(int groupPosition, int childPosition);

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        try {
            viewHolder = ViewHolder.get(mContext, convertView, parent,
                    mParentLayId, groupPosition);

            convertPatent(viewHolder, getGroup(groupPosition), groupPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewHolder.getConvertView();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        try {
            viewHolder = ViewHolder.get(mContext, convertView, parent,
                    mChildLayId, childPosition);

            convertChild(viewHolder, getChild(groupPosition, childPosition), groupPosition, childPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewHolder.getConvertView();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public abstract void convertPatent(ViewHolder holder, T t, int position);

    public abstract void convertChild(ViewHolder holder, T t, int groupPosition, int childPosition);

    public Map<String, List<T>> getmData() {
        return mData;
    }

    public List<String> getmParents() {
        return mParents;
    }

    public void setmParents(List<String> mParents) {
        this.mParents = mParents;
    }

    public List<List<T>> getmChilds() {
        return mChilds;
    }

    public void setmChilds(List<List<T>> mChilds) {
        this.mChilds = mChilds;
    }
}
