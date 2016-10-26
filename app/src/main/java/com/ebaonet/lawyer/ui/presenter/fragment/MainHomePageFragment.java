package com.ebaonet.lawyer.ui.presenter.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.configs.GlobalConfig;
import com.ebaonet.lawyer.core.entity.BannerEntity;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.entity.NewsEntity;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.HomePageBiz;
import com.ebaonet.lawyer.ui.dialog.TwoSelectDialog;
import com.ebaonet.lawyer.ui.presenter.activity.CallActivity;
import com.ebaonet.lawyer.ui.presenter.activity.MessageActivity;
import com.ebaonet.lawyer.ui.presenter.activity.NewsDetailActivity;
import com.ebaonet.lawyer.ui.presenter.activity.WrittenAdviceActivity;
import com.ebaonet.lawyer.ui.view.frgament.MainHomePageDelegate;
import com.ebaonet.lawyer.util.LogUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yzb on 2016/9/8 16:17
 * 邮箱：280766447@qq.com
 */
public class MainHomePageFragment extends BaseFragment<MainHomePageDelegate> implements View.OnClickListener,AdapterView.OnItemClickListener {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private TwoSelectDialog twoSelectDialog;
    private String cityName = "杭州市";

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mLocationClient = new LocationClient(getContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
        initLocation();

        getBanner();
        getNewsList();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mCall:
                CallActivity.actionActivity(getContext());
                break;
            case R.id.write:
                WrittenAdviceActivity.actionActivity(getContext());
                break;
            case R.id.message:
                MessageActivity.actionActivity(getContext());
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsDetailActivity.actionNewsDetailActivity(getContext(), GlobalConfig.API_URL+"/index/querynewsbyid.htm?doc_news_id="+mView.getAdapter().getItem(position).getDoc_news_id());
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation location) {
            LogUtil.getLogger().e(location.getCity());
            if (!mView.getCity().getText().toString().equals(location.getCity())) {
                if (twoSelectDialog == null) {
                    twoSelectDialog = new TwoSelectDialog(getContext());
                }
                twoSelectDialog.setText("定位", "当前定位到" + location.getCity() + ",是否切换");
                twoSelectDialog.setOnClickSelect(new TwoSelectDialog.OnDialogClickListener() {
                    @Override
                    public void onOneClick(View v) {
                        cityName = location.getCity();
                        mView.getCity().setText(cityName);
                    }

                    @Override
                    public void onTwoClick(View v) {
                        mView.getCity().setText(cityName);
                    }
                });
                twoSelectDialog.show();
            }
        }
    }

    /**
     * 定位相关
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /**
     * 获取轮播图
     */
    private void getBanner() {
        HomePageBiz.getBannerList(getContext(), "5", cityName, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                List<BannerEntity> list = data.getArrayData("carouselList", BannerEntity.class);
                mView.setBanner(getContext(), list, new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                    }
                });
            }

            @Override
            public void onError(NetBaseBean data) {

            }
        });
    }

    /**
     * 得到新闻列表
     */
    private void getNewsList() {
        HomePageBiz.getNewsList(getContext(), "0", "10", cityName, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                List<NewsEntity> list = data.getArrayData("adviceList", NewsEntity.class);
                if(data.isSuccess()){
                    mView.setNewsData(getContext(),list);
                }
            }

            @Override
            public void onError(NetBaseBean data) {

            }
        });
    }


}
