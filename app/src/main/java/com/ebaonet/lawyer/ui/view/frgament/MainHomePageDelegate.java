package com.ebaonet.lawyer.ui.view.frgament;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.configs.GlobalConfig;
import com.ebaonet.lawyer.core.entity.BannerEntity;
import com.ebaonet.lawyer.core.entity.NewsEntity;
import com.ebaonet.lawyer.ui.adapter.NewsListAdapter;
import com.ebaonet.lawyer.ui.weight.ListViewForScrollView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

import java.util.List;

/**
 * 作者：yzb on 2016/9/8 16:18
 * 邮箱：280766447@qq.com
 */
public class MainHomePageDelegate extends AppDelegate {
    private SliderLayout sliderLayout;
    private ListViewForScrollView mListView;
    private NewsListAdapter adapter;
    private ImageView mCall, write, message;
    private TextView city;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void created() {
        super.created();
        sliderLayout = findViewById(R.id.slider);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        DescriptionAnimation animation = new DescriptionAnimation();
        sliderLayout.setCustomAnimation(animation);
        sliderLayout.setDuration(4000);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);
        mListView = findViewById(R.id.mListView);
        mCall = findViewById(R.id.mCall);
        write = findViewById(R.id.write);
        message = findViewById(R.id.message);
        city = findViewById(R.id.city);
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        EventHelper.click(mPresenter, mCall, write, message);
        EventHelper.itemClick(mPresenter,mListView);
    }

    public void setBanner(Context context, List<BannerEntity> list, BaseSliderView.OnSliderClickListener listener) {
        sliderLayout.removeAllSliders();
        for (int i = 0; i < list.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(context);
            textSliderView
                    .description("")
                    .image(GlobalConfig.API_URL+"common/image/"+list.get(i).getImage_id()+".htm")
                    .setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(listener);
            Bundle bundle = new Bundle();
            bundle.putString("serverUrl", list.get(i).getImage_id());
            textSliderView.bundle(bundle);
            sliderLayout.addSlider(textSliderView);
        }
    }

    public void setNewsData(final Context context, List<NewsEntity> list) {
        if (adapter == null) {
            adapter = new NewsListAdapter(context, list, R.layout.list_item_news);
        }
        mListView.setAdapter(adapter);
    }

    public TextView getCity() {
        return city;
    }

    public NewsListAdapter getAdapter() {
        return adapter;
    }
}