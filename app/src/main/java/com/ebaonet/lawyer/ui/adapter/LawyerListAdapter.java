package com.ebaonet.lawyer.ui.adapter;

import android.content.Context;

import com.ebaonet.lawyer.ui.adapter.common.CommonAdapter;
import com.ebaonet.lawyer.ui.adapter.common.ViewHolder;

import java.util.List;

/**
 * 作者：yzb on 2016/10/9 14:25
 * 邮箱：280766447@qq.com
 */
public class LawyerListAdapter  extends CommonAdapter<String>{
    public LawyerListAdapter(Context context, List<String> data, int layId) {
        super(context, data, layId);
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {

    }
}
