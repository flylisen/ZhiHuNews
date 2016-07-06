package com.lisen.android.zhihunews.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.activity.MainActivity;
import com.lisen.android.zhihunews.activity.NewsContentActivity;
import com.lisen.android.zhihunews.adapter.MainNewsAdapter;
import com.lisen.android.zhihunews.model.Before;
import com.lisen.android.zhihunews.model.Latest;
import com.lisen.android.zhihunews.model.StoryEntry;
import com.lisen.android.zhihunews.model.TopStoryEntry;
import com.lisen.android.zhihunews.util.Constant;
import com.lisen.android.zhihunews.util.HttpUtils;
import com.lisen.android.zhihunews.view.Kanner;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/7/3.
 */
public class MainNewsFragment extends BaseFragment {

    private ListView mLvMainNews;
    private Latest mLatest;
    private MainNewsAdapter mAdapter;
    private String date;
    private boolean mIsLoading;
    private Kanner kanner;
    private boolean mLoamore = true;
    private Handler mHanle = new Handler();
    private Before mBefore;
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_news_fragment, container, false);
        mLvMainNews = (ListView) view.findViewById(R.id.lv_mainNews_fragment);
        //添加显示顶部新闻
        View header = inflater.inflate(R.layout.karner, mLvMainNews, false);
        kanner = (Kanner) header.findViewById(R.id.kaner);
        mLvMainNews.addHeaderView(header);
        mAdapter = new MainNewsAdapter(mMainActivity);
        mLvMainNews.setAdapter(mAdapter);
        mLvMainNews.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mLvMainNews != null && mLvMainNews.getChildCount() > 0) {
                    //解决冲突
                    boolean enable = (firstVisibleItem == 0) && (view.getChildAt(firstVisibleItem).getTop() == 0);
                    ((MainActivity) mMainActivity).setSwipeFreshEnalbe(enable);

                    if ((firstVisibleItem + visibleItemCount == totalItemCount) && !mIsLoading) {
                        //滑到最底端
                        loadMore(Constant.BEFORE + date);
                    }
                }

            }
        });
        
        // TODO: 2016/7/6 添加listview item点击事件
        mLvMainNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                StoryEntry entry = (StoryEntry) parent.getAdapter().getItem(position);
                Intent intent = new Intent(mMainActivity, NewsContentActivity.class);
                intent.putExtra("story_entry", entry);
                startActivity(intent);
                // TODO: 2016/7/6 添加动画细节 
            }
        });
        return view;
    }

    private void loadMore(String url) {
        mIsLoading = true;
        // TODO: 2016/7/6 加进网络是否通的判断 
        if (HttpUtils.isNetworkConnected(mMainActivity)) {
            
            //从网络中加载
            HttpUtils.get(url, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    // TODO: 2016/7/6 存储进数据库  
                    parseBeforeJSon(responseString);
                }
            });
        } else {
            // TODO: 2016/7/6 网络不通，从数据库中加载 
        }
    }

    private void parseBeforeJSon(String responseString) {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<Before>(){}.getType();
        mBefore = gson.fromJson(responseString, type);

        if (mBefore == null) {
           mIsLoading = false;
            return;
        }

        date = mBefore.getDate();
        mHanle.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.addDatas(mBefore.getStories());
                mIsLoading = false;
            }
        });
    }


    @Override
    public void initData() {
        super.initData();
        loadFirst();
    }

    private void loadFirst() {
        mIsLoading = true;
        if (true) {

            //从网络中加载
            HttpUtils.get(Constant.LATEST_NEWS, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    parseLatestJSon(responseString);
                }
            });
        }
    }

    private void parseLatestJSon(String jsonString) {

        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<Latest>(){}.getType();
        mLatest = gson.fromJson(jsonString, type);
        date = mLatest.getDate();
        kanner.setListTopStoryEntry(mLatest.getTop_stories());
        mHanle.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.addDatas(mLatest.getStories());
                mIsLoading = false;
            }
        });


    }


}
