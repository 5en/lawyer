package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ccwant.photo.selector.activity.CCwantPhotoBrowserActivity;
import com.ccwant.photo.selector.activity.CCwantSelectAlbumActivity;
import com.ccwant.photo.selector.adapter.CCwantPublishAdapter;
import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.view.activity.SubmitConsultDelegate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yzb on 2016/10/12 09:51
 * 邮箱：280766447@qq.com
 */
public class SubmitConsultActivity extends BaseActivity<SubmitConsultDelegate> implements View.OnClickListener {

    /**
     * 打开相册选择activity
     */
    private final int OPEN_SELECT_ALBUM = 1;
    /**
     * 打开图片浏览器activity
     */
    private final int OPEN_PHOTO_BROWSER = 2;
    /**
     * 你选择的所有图片路径
     */
    private List<String> mData = new ArrayList<String>();

    private GridView mGrvContent;
    private CCwantPublishAdapter mAdapter;

    public static void actionActivity(Context context) {
        context.startActivity(new Intent(context, SubmitConsultActivity.class));
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mAdapter = new CCwantPublishAdapter(this, mData);
        mGrvContent = mView.getGridView();
        mGrvContent.setAdapter(mAdapter);
        mGrvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //如果点击了+则打开相册选择器，否则打开相片浏览界面
                if (position == mAdapter.getMaxPosition() - 1) {
                    Intent intent = new Intent(SubmitConsultActivity.this, CCwantSelectAlbumActivity.class);
                    startActivityForResult(intent, OPEN_SELECT_ALBUM);
                } else {
                    Intent intent = new Intent(SubmitConsultActivity.this, CCwantPhotoBrowserActivity.class);
                    intent.putExtra("CCwantPhotoList", (Serializable) mData);
                    intent.putExtra("CCwantPhotoPosition", (Serializable) position);
                    startActivityForResult(intent, OPEN_PHOTO_BROWSER);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //处理选择图片后的返回值
        if (requestCode == OPEN_SELECT_ALBUM) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                List<String> list = new ArrayList<String>();
                list.addAll(mData);
                list.addAll(bundle.getStringArrayList("result"));
                mData.clear();
                mData.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        }
        //处理图片浏览的返回值
        if (requestCode == OPEN_PHOTO_BROWSER) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                List<String> list = bundle.getStringArrayList("result");
                mData.clear();
                mData.addAll(list);
                mAdapter.notifyDataSetChanged();


            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                SuccessActivity.actionActivity(this);
                break;
        }
    }
}
