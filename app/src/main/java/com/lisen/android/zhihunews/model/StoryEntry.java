package com.lisen.android.zhihunews.model;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class StoryEntry {
    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    /**
      images: [
     "http://pic1.zhimg.com/84dadf360399e0de406c133153fc4ab8_t.jpg"
     ],
     type: 0,
     id: 4239728,
     title: "前苏联监狱纹身百科图鉴"
     */
    private String ga_prefix;
    private List<String> images;
    private int type;
    private String title;
    private int id;

    public List<String> getImages() {
        return images;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StoryEntry{" +
                "images=" + images +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
