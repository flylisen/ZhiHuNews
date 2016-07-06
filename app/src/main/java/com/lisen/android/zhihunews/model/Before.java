package com.lisen.android.zhihunews.model;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class Before {
    private String date;
    private List<StoryEntry> stories;

    public String getDate() {
        return date;
    }

    public List<StoryEntry> getStories() {
        return stories;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<StoryEntry> stories) {
        this.stories = stories;
    }
}
