package com.ebaonet.lawyer.core.entity;

import java.util.List;

/**
 * 作者：yzb on 2016/10/20 13:29
 * 邮箱：280766447@qq.com
 */
public class BannerEntity {


    private int cs_id;
    private String name;
    private String image_id;
    private String url;

    public int getCs_id() {
        return cs_id;
    }

    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
