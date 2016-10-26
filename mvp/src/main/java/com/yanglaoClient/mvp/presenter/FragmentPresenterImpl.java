package com.yanglaoClient.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.yanglaoClient.mvp.helper.GenericHelper;
import com.yanglaoClient.mvp.util.window.IWindow;
import com.yanglaoClient.mvp.util.window.WindowWrapper;
import com.yanglaoClient.mvp.view.IDelegate;

import java.io.File;

/**
 * Fragment作为Presenter的基类 <br />
 */
public class FragmentPresenterImpl<T extends IDelegate> extends Fragment implements IPresenter<T>, IWindow {
    private WindowWrapper mWindowWrapper;
    protected T mView;
    private Toast mToast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        create(savedInstanceState);
        View view = null;
        try {
            if (!isConn(getActivity())) {
                Toast.makeText(getActivity(), "网络不稳定", Toast.LENGTH_LONG).show();
            }
            mWindowWrapper = new WindowWrapper(this);
            mView = getDelegateClass().newInstance();
            mView.bindPresenter(this);
            view = mView.create(inflater, container);
            mView.bindEvent();
            created(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;

    }

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

    public void alert(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    @Override
    public void onReturnImageUri(String imageUri) {

    }

    @Override
    public void onReturnBitmap(String strUri, ImageView imageView, Bitmap bitmap, File file) {

    }


    public void doCamera() {
        mWindowWrapper.doCamera(getActivity());
    }

    public void doGallery() {
        mWindowWrapper.doGallery(getActivity(), false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mWindowWrapper.onActivityResult(getActivity(), requestCode, resultCode,
                data);
    }
    public  boolean isConn(Context context){
        boolean bisConnFlag=false;
        ConnectivityManager conManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if(network!=null){
            bisConnFlag=conManager.getActiveNetworkInfo().isAvailable();
        }
        return bisConnFlag;
    }

}
