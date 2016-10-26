package com.ebaonet.lawyer.ui.adapter;

import android.content.Context;

import com.ebaonet.lawyer.core.entity.KnowListEntity;
import com.ebaonet.lawyer.ui.adapter.common.CommonAdapter;
import com.ebaonet.lawyer.ui.adapter.common.ViewHolder;

import java.util.List;
import java.util.Objects;

/**
 * 作者：yzb on 2016/10/17 16:52
 * 邮箱：280766447@qq.com
 */
public class KnowTypeAdapter extends CommonAdapter<KnowListEntity> {
    public KnowTypeAdapter(Context context, List<KnowListEntity> data, int layId) {
        super(context, data, layId);
    }

    @Override
    public void convert(ViewHolder holder, KnowListEntity s, int position) {

    }
}
