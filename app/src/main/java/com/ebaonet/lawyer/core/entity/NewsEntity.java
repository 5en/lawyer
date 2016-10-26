package com.ebaonet.lawyer.core.entity;

/**
 * 作者：yzb on 2016/10/20 13:50
 * 邮箱：280766447@qq.com
 */
public class NewsEntity {

    /**
     * doc_news_id : 10
     * title : 百度遭撞库 售卖软件者被抓获归案
     * image_id : caa9a3f195da11e6aad900163e0118af
     * src_desc :
     * crt_time : null
     * content : null
     */

    private int doc_news_id;
    private String title;
    private String image_id;
    private String src_desc;
    private Object crt_time;
    private Object content;

    public int getDoc_news_id() {
        return doc_news_id;
    }

    public void setDoc_news_id(int doc_news_id) {
        this.doc_news_id = doc_news_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getSrc_desc() {
        return src_desc;
    }

    public void setSrc_desc(String src_desc) {
        this.src_desc = src_desc;
    }

    public Object getCrt_time() {
        return crt_time;
    }

    public void setCrt_time(Object crt_time) {
        this.crt_time = crt_time;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
