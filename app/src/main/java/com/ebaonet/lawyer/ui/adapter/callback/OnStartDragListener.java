package com.ebaonet.lawyer.ui.adapter.callback;

import android.support.v7.widget.RecyclerView;

/**
 * Created by tx on 2016/7/1.
 */
public interface OnStartDragListener  {
    /**
     * @param viewHolder  the viewHolder to drag.
     * */
    public void startToDrag(RecyclerView.ViewHolder viewHolder);
}
