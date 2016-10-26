package com.ebaonet.lawyer.ui.adapter;

import android.content.Context;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.entity.KnowListEntity;
import com.ebaonet.lawyer.ui.adapter.common.CommonAdapter;
import com.ebaonet.lawyer.ui.adapter.common.ViewHolder;

import java.util.List;

/**
 * 作者：yzb on 2016/10/17 15:31
 * 邮箱：280766447@qq.com
 */
public class SearchListAdapter extends CommonAdapter<KnowListEntity> {
    public SearchListAdapter(Context context, List<KnowListEntity> data, int layId) {
        super(context, data, layId);
    }

    @Override
    public void convert(ViewHolder holder, KnowListEntity s, int position) {
        holder.setText(R.id.content,s.getContent());
    }
}
