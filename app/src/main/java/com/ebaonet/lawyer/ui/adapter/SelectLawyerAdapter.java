package com.ebaonet.lawyer.ui.adapter;

import com.ebaonet.lawyer.ui.adapter.base.RecyclerAdapter;
import com.ebaonet.lawyer.ui.adapter.viewholder.RecyclerViewHolder;

import java.util.List;

/**
 * 作者：yzb on 2016/10/10 16:54
 * 邮箱：280766447@qq.com
 */
public class SelectLawyerAdapter extends RecyclerAdapter<String> {
    public SelectLawyerAdapter(int layoutId, List<String> datas) {
        super(layoutId, datas);
    }

    @Override
    protected void onBindData(RecyclerViewHolder viewHolder, int position, String item) {

    }
}
