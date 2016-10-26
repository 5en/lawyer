package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.view.activity.ModifyPasswordDelegate;

import org.json.JSONException;

import java.io.IOException;

/**
 *
 */
public class ModifyPasswordActivity extends BaseActivity<ModifyPasswordDelegate> implements View.OnClickListener {
    public static final int PAGE1= 1;
    public static final int PAGE2 = 2;
    private int jumpPage;
    public static void actionActivity(Context context,int jumpToPage){
        Intent intent = new Intent(context,ModifyPasswordActivity.class);
        intent.putExtra("jumpPage",jumpToPage);
        context.startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_modify:
                if(checkText()){

                    changePassword();
                }

                break;
        }
    }
@Override
public void created(Bundle b){
    jumpPage = getIntent().getIntExtra("jumpPage",PAGE1);
}

    private boolean checkText() {
        if (mView.getOld_password().getContent().isEmpty()) {
            alert("旧密码不能为空");
            return false;
        } else if (mView.getNew_password().getContent().isEmpty()) {
            alert("新密码不能为空");
            return false;
        } else if (!mView.getNew_password().getContent().equals(mView.getSure_new_password().getContent())) {
            alert("新密码两次输入错误");
            return false;
        }else if (mView.getNew_password().getContent().equals(mView.getOld_password().getContent())){
            alert("旧密码和新密码不能一致");
            return false;
        }
        return true;
    }

    private void changePassword() {
        showRequestDialog("",true,true);

    }


//    private void logout() {
//        LoginBiz.logout(this, new MyRequestClient.Callback<NetBaseBean>() {
//            @Override
//            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
//                if(data.isSuccess()){
//
//                    Intent intent = new Intent();
//                    intent.putExtra(CitizenFragment.LOGIN_STATE,CitizenFragment.LOGIN_OUT);
//                    intent.setAction(CitizenFragment.LOGIN_REFRESH);
//                    sendBroadcast(intent);
//                    alert("密码修改成功");
//                   // HomeActivity.actionActivity(ModifyPasswordActivity.this);
//
//                    Intent intent1 = new Intent(ModifyPasswordActivity.this,HomeActivity.class);
//                    intent1.putExtra(HomeActivity.RECEIVER_JUMP,jumpPage);
//                   // intent1.setAction(HomeActivity.RECEIVER_JUMP);
//                    startActivity(intent1);
//                    finish();
//
//                }else {
//                    alert(data.getMessage());
//                }
//            }
//
//            @Override
//            public void onError(NetBaseBean data) {
//                alert(data.getMessage());
//            }
//        });
//
//    }
}


