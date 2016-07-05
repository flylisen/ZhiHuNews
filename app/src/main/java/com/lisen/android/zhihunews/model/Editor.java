package com.lisen.android.zhihunews.model;

/**
 * Created by Administrator on 2016/7/2.
 */
public class Editor {
    /**
     * url: "http://www.zhihu.com/people/wezeit",
        bio: "微在 Wezeit 主编",
        id: 70,
       avatar: "http://pic4.zhimg.com/068311926_m.jpg",
     name: "益康糯米"
     */

    private String url;
    private String bio;
    int id;
    private String avatar;
    private String name;

    public String getUrl() {
        return url;
    }

    public String getBio() {
        return bio;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Editor{" +
                "url='" + url + '\'' +
                ", bio='" + bio + '\'' +
                ", id=" + id +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
