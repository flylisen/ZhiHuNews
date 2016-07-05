package com.lisen.android.zhihunews.model;

import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
public class Latest {

    private String date;
    private List<StoryEntry> stories;
    private List<TopStoryEntry> top_stories;

    public String getDate() {
        return date;
    }

    public List<StoryEntry> getStories() {
        return stories;
    }

    public List<TopStoryEntry> getTop_stories() {
        return top_stories;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<StoryEntry> stories) {
        this.stories = stories;
    }

    public void setTop_stories(List<TopStoryEntry> top_stories) {
        this.top_stories = top_stories;
    }
}
