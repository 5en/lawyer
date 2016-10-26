package com.ebaonet.lawyer;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.yanglaoClient.mvp.presenter.BaseApplication;


/**
 * 作者：yzb on 2016/6/15 13:37
 * 邮箱：280766447@qq.com
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        String libName = "weibosdkcore"; // 库名, 注意没有前缀lib和后缀.so
//        System.loadLibrary( libName );
//        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);
//        PlatformConfig.setWeixin("wx6fbdc1d745288ff9", "bb7f2353a46a22f0a71ba0898d913bc6");
//        PlatformConfig.setQQZone("1105548519", "SgOMqzVfEP6M7bqy");
//        PlatformConfig.setSinaWeibo("3529266427","9c44b514dbac7f851af5fa4746e9c49e");
        Fresco.initialize(this);
    }
}
