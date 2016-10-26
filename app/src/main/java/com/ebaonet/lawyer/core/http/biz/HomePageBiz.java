package com.ebaonet.lawyer.core.http.biz;

import android.content.Context;

import com.ebaonet.lawyer.configs.GlobalConfig;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;

import java.util.HashMap;

/**
 * 作者：yzb on 2016/10/20 11:13
 * 邮箱：280766447@qq.com
 */
public class HomePageBiz {

    /**
     * 轮播图
     */
    public static void getBannerList(Context context, String count, String regn_name
            , MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String, String> params = new HashMap();
        params.put("count", count);//轮播条数
        params.put("regn_name", regn_name);//地区名称
        MyRequestClient.post(context, GlobalConfig.CAROUSEL_IMAGES, params, callback);
    }

    /**
     * 获取新闻列表
     */
    public static void getNewsList(Context context, String start,String count, String regn_name
            , MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String, String> params = new HashMap();
        params.put("start", start);
        params.put("count", count);
        params.put("regn_name", regn_name);//地区名称
        MyRequestClient.post(context, GlobalConfig.NEW_LISTS, params, callback);
    }
}
