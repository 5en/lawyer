package com.yanglaoClient.mvp.presenter;

import android.app.Application;
import android.util.DisplayMetrics;

import com.yanglaoClient.mvp.util.Utils;
import com.yanglaoClient.mvp.util.config.IConfig;
import com.yanglaoClient.mvp.util.config.PreferenceConfig;
import com.yanglaoClient.mvp.util.config.PropertiesConfig;

/**
 * 作者：yzb on 2016/5/26 18:35
 * 邮箱：280766447@qq.com
 */
public abstract class BaseApplication extends Application {
    /**
     * 应用程序运行Activity管理器
     */
    /** 配置器 为Preference */
    public final static int PREFERENCECONFIG = 0;
    /** 配置器 为PROPERTIESCONFIG */
    public final static int PROPERTIESCONFIG = 1;

    /** 配置器 */
    private IConfig mCurrentConfig;
    private String cacheDirPath;
    private int[] screenSize;
    private AppManager mAppManager;
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        getAppManager();
        initScreenSize();
        application = this;
    }
    public int[] getScreenSize() {
        return this.screenSize;
    }
    private void initScreenSize() {
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenSize = new int[] { metrics.widthPixels, metrics.heightPixels };
    }
    public static BaseApplication getApplication() {
        return application;
    }

    public AppManager getAppManager() {
        if (mAppManager == null) {
            mAppManager = AppManager.getAppManager();
        }
        return mAppManager;
    }

    public IConfig getPreferenceConfig() {
        return getConfig(PREFERENCECONFIG);
    }

    public IConfig getPropertiesConfig() {
        return getConfig(PROPERTIESCONFIG);
    }

    public IConfig getConfig(int confingType) {
        if (confingType == PREFERENCECONFIG) {
            mCurrentConfig = PreferenceConfig.getPreferenceConfig(this);

        } else if (confingType == PROPERTIESCONFIG) {
            mCurrentConfig = PropertiesConfig.getPropertiesConfig(this);
        } else {
            mCurrentConfig = PropertiesConfig.getPropertiesConfig(this);
        }
        if (!mCurrentConfig.isLoadConfig()) {
            mCurrentConfig.loadConfig();
        }
        return mCurrentConfig;
    }

    public IConfig getCurrentConfig() {
        if (mCurrentConfig == null) {
            getPreferenceConfig();
        }
        return mCurrentConfig;
    }

    public String getCacheDirPath() {
        return cacheDirPath;
    }

    private void initCacheDirPath() {
        cacheDirPath = Utils.getDiskCacheDir(this, "global");
    }

}
