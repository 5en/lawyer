package com.yanglaoClient.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.yanglaoClient.mvp.helper.GenericHelper;
import com.yanglaoClient.mvp.util.window.IWindow;
import com.yanglaoClient.mvp.util.window.WindowWrapper;
import com.yanglaoClient.mvp.view.IDelegate;

import java.io.File;

/**
 * 将Activity作为Presenter的基类 <br />
 */
public class FragmentActivityPresenterImpl<T extends IDelegate> extends FragmentActivity implements IPresenter<T>,IWindow {
    private WindowWrapper mWindowWrapper;

    protected T mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create(savedInstanceState);
        BaseApplication.getApplication().getAppManager().addActivity(this);
        try {
            if(!isConn(getApplication())){
                Toast.makeText(getApplication(),"网络不稳定",Toast.LENGTH_LONG).show();
            }
            mWindowWrapper = new WindowWrapper(this);
            mView = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mView.bindPresenter(this);
        setContentView(mView.create(getLayoutInflater(), null));
        mView.bindEvent();
        created(savedInstanceState);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getApplication().getAppManager().removeActivity(this);
    }

    @Override
    public void onReturnImageUri(String imageUri) {

    }

    @Override
    public void onReturnBitmap(String strUri, ImageView imageView, Bitmap bitmap, File file) {

    }


    protected void doCamera() {
        mWindowWrapper.doCamera(this);
    }

    protected void doGallery() {
        mWindowWrapper.doGallery(this, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mWindowWrapper.onActivityResult(this, requestCode, resultCode,
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
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

}
