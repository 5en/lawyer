package com.ebaonet.lawyer.ui.presenter.fragment;

import android.content.Intent;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.activity.mine.ModifyBindPhoneActivity;
import com.ebaonet.lawyer.ui.view.frgament.BindPhoneDelegate;

/**
 * Created by tx on 2016/10/14.
 */

public class BinPhoneFragment extends BaseFragment<BindPhoneDelegate> implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.validate:
                //todo 最后一步验证手机
              //  Intent intent = new Intent(ModifyBindPhoneActivity.)
                break;
        }
    }
}
