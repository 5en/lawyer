package com.ebaonet.lawyer.ui.view.activity;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.entity.KnowListEntity;
import com.ebaonet.lawyer.ui.adapter.SearchListAdapter;
import com.ebaonet.lawyer.ui.presenter.activity.SearchActivity;
import com.ebaonet.lawyer.ui.weight.AutoCompleteTextViewWithDelete;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

import java.util.List;

/**
 * 作者：yzb on 2016/10/17 10:45
 * 邮箱：280766447@qq.com
 */
public class SearchDelegate extends AppDelegate {
    private ListView mListView;
    private SearchListAdapter adapter;
    private AutoCompleteTextViewWithDelete edit;
    private TextView noSearch;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void created() {
        super.created();
        mListView = findViewById(R.id.mListView);
        edit=findViewById(R.id.edit);
        noSearch=findViewById(R.id.noSearch);
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        EventHelper.click(mPresenter);
    }

    public void setData(Context context, List<KnowListEntity> list) {
        if (adapter == null) {
            adapter = new SearchListAdapter(context, list, R.layout.list_item_search);
        }
        mListView.setAdapter(adapter);
    }

    public AutoCompleteTextViewWithDelete getEdit() {
        return edit;
    }

    public TextView getNoSearch() {
        return noSearch;
    }
}
