package com.ebaonet.lawyer.ui.presenter.fragment;

import android.os.Bundle;

import com.ebaonet.lawyer.ui.view.frgament.LawyerServiceDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yzb on 2016/10/10 14:51
 * 邮箱：280766447@qq.com
 */
public class LawyerServiceFragment extends BaseFragment<LawyerServiceDelegate> {
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        List<String> list =new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add("asa");
        }
        mView.setData(getContext(),list);
    }
}
