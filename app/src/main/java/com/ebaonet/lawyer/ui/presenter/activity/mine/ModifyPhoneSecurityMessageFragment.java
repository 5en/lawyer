package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Intent;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.fragment.BaseFragment;
import com.ebaonet.lawyer.ui.view.frgament.ModifyPhoneSecurityMessageDelegate;

/**
 * Created by tx on 2016/10/14.
 */

public class ModifyPhoneSecurityMessageFragment extends BaseFragment<ModifyPhoneSecurityMessageDelegate> implements View.OnClickListener{
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.validate:
                Intent intent = new Intent(ModifyBindPhoneActivity.BIND_PHONE);
                getActivity().sendBroadcast(intent);
                break;
        }
    }
}
