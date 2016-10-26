package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.fragment.MainHomePageFragment;
import com.ebaonet.lawyer.ui.presenter.fragment.MainKnowFragment;
import com.ebaonet.lawyer.ui.presenter.fragment.MineFragment;
import com.ebaonet.lawyer.ui.view.activity.HomeDelegate;
import com.yanglaoClient.mvp.adapter.MyFragmentAdapter;
import com.yanglaoClient.mvp.entity.FragmentEntity;
import com.yanglaoClient.mvp.presenter.BaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yzb on 2016/9/8 15:13
 * 邮箱：280766447@qq.com
 */
public class HomeActivity extends BaseActivity<HomeDelegate> implements RadioGroup.OnCheckedChangeListener{
    private int index = 0;
    private long back_pressed;
    private int witchPage=0;

    public  static void actionActivity(Context context,int witchPage) {
        Intent intent = new Intent(context,HomeActivity.class);
        intent.putExtra("witchPage",witchPage);
        context.startActivity(intent);
    }
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);

        mView.getMainBottomBar().check(R.id.mMainPage);
        if( savedInstance!=null){
            witchPage = savedInstance.getInt("witchPage",0);

        }
        intoViews();
    }

    private void intoViews() {
        List<FragmentEntity> mListFragmentEntity = new ArrayList();
        mListFragmentEntity.add(getFragmentEntity(new MainHomePageFragment(), "1"));
        mListFragmentEntity.add(getFragmentEntity(new MainKnowFragment(), "2"));
        mListFragmentEntity.add(getFragmentEntity(new MineFragment(), "3"));
        mView.getViewPager().setAdapter(new MyFragmentAdapter(this, mListFragmentEntity));
        mView.getViewPager().setCurrentItem(witchPage);
        mView.getViewPager().setOffscreenPageLimit(4);
    }
    private FragmentEntity getFragmentEntity(Fragment fragment, String label) {
        FragmentEntity fe = new FragmentEntity();
        fe.setFragmentLabel(label);
        fe.setmFragment(fragment);

        return fe;
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        switch (checkId) {
            case R.id.mMainPage:
                index = 0;
                break;
            case R.id.mMainRadioKnowledge:
                index = 1;
                break;
            case R.id.mMainRadioMe:
                index = 2;
                break;

            default:
                break;
        }
        mView.getViewPager().setCurrentItem(index, true);
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            BaseApplication.getApplication().getAppManager().AppExit(getApplicationContext(), false);
            super.onBackPressed();
        } else {
            alert("再按一次退出");
        }
        back_pressed = System.currentTimeMillis();
    }
}
