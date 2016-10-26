package com.yanglaoClient.mvp.presenter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.yanglaoClient.mvp.helper.GenericHelper;
import com.yanglaoClient.mvp.view.IDelegate;

/**
 * 将Activity作为Presenter的基类 <br />
 * Created by chao on 2015/11/15.
 */
public class ActivityPresenterImpl<T extends IDelegate> extends FragmentActivity implements IPresenter<T> {

    protected T mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create(savedInstanceState);
        BaseApplication.getApplication().getAppManager().addActivity(this);
        try {
            mView = getDelegateClass().newInstance();
            mView.bindPresenter(this);
            setContentView(mView.create(getLayoutInflater(), null));
            mView.bindEvent();
            created(savedInstanceState);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    /**
     * @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
     * super.onRestoreInstanceState(savedInstanceState);
     * if (mView == null) {
     * try {
     * mView = getDelegateClass().newInstance();
     * } catch (InstantiationException e) {
     * throw new RuntimeException("create IDelegate error");
     * } catch (IllegalAccessException e) {
     * throw new RuntimeException("create IDelegate error");
     * }
     * }
     * }
     */

    @Override
    public Class<T> getDelegateClass() {
        return GenericHelper.getViewClass(getClass());
    }

    @Override
    public void create(Bundle savedInstance) {

    }

    @Override
    public void created(Bundle savedInstance) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getApplication().getAppManager().removeActivity(this);
    }
}
