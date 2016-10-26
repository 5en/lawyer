package com.ebaonet.lawyer.core.http.biz;

import android.content.Context;

import com.ebaonet.lawyer.configs.GlobalConfig;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;

import java.util.HashMap;

/**
 * 作者：yzb on 2016/10/18 16:47
 * 邮箱：280766447@qq.com
 */
public class KnowBiz {
    /**
     * 搜索知识列表
     */
    public static void getKnowList(Context context, String chk_stat_id,String crt_user,String inner_org_id,
                                     String doc_mod_id,String title,String start,String end,MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String ,String> params = new HashMap();
        params.put("chk_stat_id", chk_stat_id);//审核状态
        params.put("crt_user", crt_user);//创建人
        params.put("inner_org_id",inner_org_id);//所属机构
        params.put("doc_mod_id",doc_mod_id);//所属模块
        params.put("title",title);//搜索名称
        params.put("start",start );
        params.put("end",end);

        MyRequestClient.post(context, GlobalConfig.FIND_KNOWLEDGE, params, callback);
    }
}
