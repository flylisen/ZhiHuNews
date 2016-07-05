package com.lisen.android.zhihunews.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.adapter.MainNewsAdapter;
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

    private Kanner kanner;
    private List<StoryEntry> storyEntries;
    private List<TopStoryEntry> topStoryEntries;
   /* @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMainActivity = getActivity();
        return initView(inflater, container, savedInstanceState);
    }*/

    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("TAG", "initview");
        //loadFirst();
        View view = inflater.inflate(R.layout.main_news_fragment, container, false);
        mLvMainNews = (ListView) view.findViewById(R.id.lv_mainNews_fragment);
        //添加显示顶部新闻
        View header = inflater.inflate(R.layout.karner, mLvMainNews, false);
        kanner = (Kanner) header.findViewById(R.id.kaner);
       // kanner.setListTopStoryEntry(mLatest.getTop_stories());
        mLvMainNews.addHeaderView(header);
        mAdapter = new MainNewsAdapter(mMainActivity);
        mLvMainNews.setAdapter(mAdapter);
        return view;
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("dd", "onActivitycreate");
        //loadFirst();
    }*/

    @Override
    public void initData() {
        super.initData();
        loadFirst();
    }

    private void loadFirst() {
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
      //  String result = "{\"date\":\"20160705\",\"stories\":[{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/a8900f0d3047e40fd9ca618901099083.jpg\"],\"type\":0,\"id\":8521072,\"ga_prefix\":\"070511\",\"title\":\"最终我能把手机落在出租车上简直是一个奇迹\"},{\"title\":\"这个独自屠龙的勇士，终于迎来了他生死攸关的时刻\",\"ga_prefix\":\"070510\",\"images\":[\"http:\\/\\/pic3.zhimg.com\\/1ad715ddee84c94b70cc6d1198204522.jpg\"],\"multipic\":true,\"type\":0,\"id\":8526036},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/80e6b50a45784a43938d98369a2cb01c.jpg\"],\"type\":0,\"id\":8518953,\"ga_prefix\":\"070509\",\"title\":\"世界上有好多萌萌的迷你小国，比如「可爱国」\"},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/6e789aee0ea231fd57a2aa6ac1a351ef.jpg\"],\"type\":0,\"id\":8522556,\"ga_prefix\":\"070508\",\"title\":\"在多云多雨的贵州建了一个世界最大的射电望远镜，它能看得远吗？\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/9ec629e31357582bb0144b7f17269b24.jpg\"],\"type\":0,\"id\":8518689,\"ga_prefix\":\"070507\",\"title\":\"以前没有电脑手机的时候，大学生可没闲着\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/85e6c3f7c3a0b04e23dd22513c276b38.jpg\"],\"type\":0,\"id\":8523808,\"ga_prefix\":\"070507\",\"title\":\"一个人的教养如何体现？这个小孩子给出了一种答案\"},{\"title\":\"还有一个月，奥运场馆建咋样了，我们去看了看\",\"ga_prefix\":\"070507\",\"images\":[\"http:\\/\\/pic1.zhimg.com\\/04a21154a549eb4c6d72d413f1e9f934.jpg\"],\"multipic\":true,\"type\":0,\"id\":8525259},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/cca6c5d6661bcbdda70c42fab36229b3.jpg\"],\"type\":0,\"id\":8525544,\"ga_prefix\":\"070507\",\"title\":\"读读日报 24 小时热门 TOP 5 · 今年 NBA 砸钱多吗？\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/a740c0312368f668e3b983b46293f96c.jpg\"],\"type\":0,\"id\":8517157,\"ga_prefix\":\"070506\",\"title\":\"瞎扯 · 如何正确地吐槽\"}],\"top_stories\":[{\"image\":\"http:\\/\\/pic2.zhimg.com\\/b9f805caaaea6eeb21707e22acf5d961.jpg\",\"type\":0,\"id\":8526036,\"ga_prefix\":\"070510\",\"title\":\"这个独自屠龙的勇士，终于迎来了他生死攸关的时刻\"},{\"image\":\"http:\\/\\/pic3.zhimg.com\\/a6f7ee6a12e8587f507a4800e222b21e.jpg\",\"type\":0,\"id\":8518689,\"ga_prefix\":\"070507\",\"title\":\"以前没有电脑手机的时候，大学生可没闲着\"},{\"image\":\"http:\\/\\/pic4.zhimg.com\\/d6deec49e1348274900619e80a8422b7.jpg\",\"type\":0,\"id\":8523808,\"ga_prefix\":\"070507\",\"title\":\"一个人的教养如何体现？这个小孩子给出了一种答案\"},{\"image\":\"http:\\/\\/pic1.zhimg.com\\/7dc67d14ccf1dd6de83912758a993364.jpg\",\"type\":0,\"id\":8525544,\"ga_prefix\":\"070507\",\"title\":\"读读日报 24 小时热门 TOP 5 · 今年 NBA 砸钱多吗？\"},{\"image\":\"http:\\/\\/pic2.zhimg.com\\/c2f6751337dce709b129aeb6ebe5d8e9.jpg\",\"type\":0,\"id\":8525259,\"ga_prefix\":\"070507\",\"title\":\"还有一个月，奥运场馆建咋样了，我们去看了看\"}]}";
      //  parseLatestJSon(result);
    }

    private void parseLatestJSon(String jsonString) {
        Log.d("TAG", "enter");
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<Latest>(){}.getType();
        mLatest = gson.fromJson(jsonString, type);
        kanner.setListTopStoryEntry(mLatest.getTop_stories());
        Log.d("TAG", mLatest.getTop_stories().size() + " ni");
        for (TopStoryEntry topStoryEntry : mLatest.getTop_stories()) {
            Log.d("TAG", topStoryEntry.getImage());
        }
        mAdapter.addDatas(mLatest.getStories());

    }


}
