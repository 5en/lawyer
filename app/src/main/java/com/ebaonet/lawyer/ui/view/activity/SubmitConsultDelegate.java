package com.ebaonet.lawyer.ui.view.activity;

import android.widget.GridView;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * 作者：yzb on 2016/10/12 09:52
 * 邮箱：280766447@qq.com
 */
public class SubmitConsultDelegate extends AppDelegate {
    private GridView gridView;
    private TextView submit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_consult_submit;
    }

    @Override
    public void created() {
        super.created();
        gridView = findViewById(R.id.girdView);
        submit=findViewById(R.id.submit);
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        EventHelper.click(mPresenter,submit);
    }

    public GridView getGridView() {
        return gridView;
    }

}
