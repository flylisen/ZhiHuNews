package com.lisen.android.zhihunews.model;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Content {
    private int id;

    private String body;
    private String title;
    private String ga_prefix;
    private String share_url;
    private String image;
    private int type;
    private List<String> css;
    private String image_source;
    private List<String> images;
    private List<String> js;

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    public List<String> getCss() {
        return css;
    }

    public String getImage_source() {
        return image_source;
    }

    public List<String> getImages() {
        return images;
    }

    public List<String> getJs() {
        return js;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", title='" + title + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", share_url='" + share_url + '\'' +
                ", image='" + image + '\'' +
                ", type=" + type +
                ", css=" + css +
                ", image_source='" + image_source + '\'' +
                ", images=" + images +
                ", js=" + js +
                '}';
    }
}
