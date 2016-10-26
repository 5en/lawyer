package com.ebaonet.lawyer.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.entity.KnowListEntity;
import com.ebaonet.lawyer.ui.adapter.common.CommonAdapter;
import com.ebaonet.lawyer.ui.adapter.common.ViewHolder;
import com.yanglaoClient.mvp.util.core.StringUtils;

import java.util.List;

/**
 * 作者：yzb on 2016/10/5 20:08
 * 邮箱：280766447@qq.com
 */
public class HomeKnowListAdapter extends CommonAdapter<KnowListEntity> {


    public HomeKnowListAdapter(Context context, List<KnowListEntity> data, int layId) {
        super(context, data, layId);
    }

    @Override
    public void convert(ViewHolder holder, KnowListEntity knowListEntity, int position) {
        holder.setText(R.id.title,knowListEntity.getTitle());
        holder.setText(R.id.content,knowListEntity.getContent());
        TextView textView=holder.getView(R.id.readNum);
        textView.setText(StringUtils.isEmpty(knowListEntity.getRead_num())?"0":knowListEntity.getRead_num());
    }
}
