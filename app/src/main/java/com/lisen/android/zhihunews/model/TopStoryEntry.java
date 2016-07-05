package com.lisen.android.zhihunews.model;

/**
 * Created by Administrator on 2016/7/3.
 */
public class TopStoryEntry {
    /**
     *  "image": "http://pic2.zhimg.com/22e67b517869b593c6dd46bbcb13be6d.jpg",
     "type": 0,
     "id": 8509901,
     "ga_prefix": "070311",
     "title": "又忙又累压力好大，我以为我会瘦……"
     */

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TopStoryEntry{" +
                "image='" + image + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
