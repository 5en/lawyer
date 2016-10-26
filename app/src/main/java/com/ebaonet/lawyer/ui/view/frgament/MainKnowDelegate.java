package com.ebaonet.lawyer.ui.view.frgament;

import android.content.Context;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.entity.KnowListEntity;
import com.ebaonet.lawyer.ui.adapter.HomeKnowListAdapter;
import com.ebaonet.lawyer.ui.adapter.KnowTypeAdapter;
import com.ebaonet.lawyer.ui.weight.DraggableGridViewPager;
import com.ebaonet.lawyer.ui.weight.ListViewForScrollView;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.yanglaoClient.mvp.view.AppDelegate;

import java.util.List;

/**
 * 作者：yzb on 2016/10/5 17:15
 * 邮箱：280766447@qq.com
 */
public class MainKnowDelegate extends AppDelegate {
    private TitleView mTitleView;
    private ListViewForScrollView mListView;
    private HomeKnowListAdapter adapter;
    private DraggableGridViewPager draggableGridViewPager;
    private KnowTypeAdapter knowTypeAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_know;
    }

    @Override
    public void created() {
        super.created();
        mTitleView = findViewById(R.id.mTitleView);
        mTitleView.setRightImage(R.mipmap.sousuo);
        mListView=findViewById(R.id.mListView);
        draggableGridViewPager=findViewById(R.id.mGridView);
        draggableGridViewPager.setFocusable(true);
        draggableGridViewPager.setFocusableInTouchMode(true);
        draggableGridViewPager.requestFocus();
    }
    public void setData(Context context,List<KnowListEntity> list){
        if(adapter==null){
            adapter=new HomeKnowListAdapter(context,list,R.layout.list_item_know);
        }
        mListView.setAdapter(adapter);
        draggableGridViewPager.setAdapter(new KnowTypeAdapter(context,list,R.layout.grid_know));
    }

    public TitleView getmTitleView() {
        return mTitleView;
    }
}
