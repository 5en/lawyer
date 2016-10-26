package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.ebaonet.lawyer.core.entity.KnowListEntity;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.KnowBiz;
import com.ebaonet.lawyer.ui.view.activity.SearchDelegate;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yzb on 2016/10/17 10:43
 * 邮箱：280766447@qq.com
 */
public class SearchActivity extends BaseActivity<SearchDelegate> {
    public static void actionActivity(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            list.add("asa");
//        }
//        mView.setData(SearchActivity.this,list);
        mView.getEdit().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getData(s.toString());
            }
        });
    }

    private void getData(String s) {
        KnowBiz.getKnowList(this, "", "", "", "", s, "0", "30", new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                List<KnowListEntity> list = data.getArrayData("knowledgeList", KnowListEntity.class);
                mView.setData(SearchActivity.this, list);
                mView.getNoSearch().setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(NetBaseBean data) {

            }
        });
    }
}
