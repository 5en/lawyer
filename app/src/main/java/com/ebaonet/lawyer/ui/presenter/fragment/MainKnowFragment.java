package com.ebaonet.lawyer.ui.presenter.fragment;

import android.os.Bundle;
import android.view.View;

import com.ebaonet.lawyer.core.entity.KnowListEntity;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.KnowBiz;
import com.ebaonet.lawyer.ui.presenter.activity.SearchActivity;
import com.ebaonet.lawyer.ui.view.frgament.MainKnowDelegate;
import com.ebaonet.lawyer.util.LogUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yzb on 2016/10/5 17:09
 * 邮箱：280766447@qq.com
 */
public class MainKnowFragment extends BaseFragment <MainKnowDelegate>{
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        getData();
        mView.getmTitleView().setRightImageOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.actionActivity(getContext());
            }
        });
    }

    private void getData() {
        KnowBiz.getKnowList(getContext(), "", "", "", "", "", "0", "30", new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                List<KnowListEntity> list=data.getArrayData("knowledgeList",KnowListEntity.class);
                mView.setData(getActivity(),list);
            }

            @Override
            public void onError(NetBaseBean data) {

            }
        });
    }
}
