package com.ebaonet.lawyer.ui.presenter.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.dialog.SelectCallDialog;
import com.ebaonet.lawyer.ui.view.activity.CallAppDelegate;

/**
 * 作者：yzb on 2016/10/8 16:07
 * 邮箱：280766447@qq.com
 */
public class CallActivity extends BaseActivity<CallAppDelegate> implements View.OnClickListener {
    private SelectCallDialog dialog;

    public static void actionActivity(Context context) {
        context.startActivity(new Intent(context, CallActivity.class));
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        dialog = new SelectCallDialog(this);
        dialog.setOnClickSelect(new SelectCallDialog.OnDialogClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 400382927));
                if (ActivityCompat.checkSelfPermission(CallActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mCall:
                dialog.show();
                break;
            case R.id.toWrite:
                WrittenAdviceActivity.actionActivity(this);
                break;
        }
    }
}
