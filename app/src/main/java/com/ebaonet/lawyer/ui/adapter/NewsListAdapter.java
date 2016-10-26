package com.ebaonet.lawyer.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.configs.GlobalConfig;
import com.ebaonet.lawyer.core.entity.NewsEntity;
import com.ebaonet.lawyer.ui.adapter.common.CommonAdapter;
import com.ebaonet.lawyer.ui.adapter.common.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 作者：yzb on 2016/9/21 15:00
 * 邮箱：280766447@qq.com
 */
public class NewsListAdapter extends CommonAdapter<NewsEntity> {
    private Context context;

    public NewsListAdapter(Context context, List<NewsEntity> data, int layId) {
        super(context, data, layId);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, NewsEntity newsEntity, int position) {
        Picasso.with(context).load(GlobalConfig.API_URL + "common/thumbimage/" + newsEntity.getImage_id() + ".htm").into((ImageView) holder.getView(R.id.itemImage));
        holder.setText(R.id.title, newsEntity.getTitle());
        holder.setText(R.id.from,newsEntity.getSrc_desc());
    }
}
