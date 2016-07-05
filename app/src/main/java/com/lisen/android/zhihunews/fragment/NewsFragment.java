package com.lisen.android.zhihunews.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.activity.MainActivity;
import com.lisen.android.zhihunews.adapter.NewsItemAdapter;
import com.lisen.android.zhihunews.db.CacheDbHelper;
import com.lisen.android.zhihunews.model.News;
import com.lisen.android.zhihunews.util.Constant;
import com.lisen.android.zhihunews.util.HttpUtils;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/7/2.
 */
@SuppressLint("ValidFragment")
public class NewsFragment extends BaseFragment {


    ListView mLvNews;
    private String urlId;
    private News mNews;
    private ImageLoader mImageLoader;
    private ImageView mIvTitle;
    private TextView mTvTitle;

    public NewsFragment(String urlId) {
        this.urlId = urlId;
    }
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        mLvNews = (ListView) view.findViewById(R.id.lv_fragment_news);
        mImageLoader = ImageLoader.getInstance();
        View header = LayoutInflater.from(mMainActivity).inflate(R.layout.news_header,
                mLvNews, false);
        mIvTitle = (ImageView) header.findViewById(R.id.iv_title);
        mTvTitle = (TextView) header.findViewById(R.id.tv_title);
        mLvNews.addHeaderView(header);
        return view;
    }

    @Override
    public void initData() {
        if (HttpUtils.isNetworkConnected(mMainActivity)) {
            //发起请求
            HttpUtils.get(Constant.THEME + urlId, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    //保存进数据库
                    SQLiteDatabase db = ((MainActivity) mMainActivity).getCacheDbHelper().getWritableDatabase();
                    db.execSQL("replace into CacheList (date, json) values (" +
                            (Constant.BASE_COLUMN + Integer.parseInt(urlId)) + "," +
                                responseString +
                            ")");
                    db.close();
                    parseJson(responseString);

                }
            });
        } else {
            //从数据库中查询缓存
            SQLiteDatabase db = ((MainActivity) mMainActivity).getCacheDbHelper().getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from CacheList where date = " +
                    (Constant.BASE_COLUMN + Integer.parseInt(urlId)), null);
            if (cursor.moveToFirst()) {
                String json = cursor.getString(cursor.getColumnIndex("json"));
                parseJson(json);
            }
            cursor.close();
            db.close();
        }

    }

    private void parseJson(String jsonString) {
        Gson gson = new Gson();
        mNews = gson.fromJson(jsonString, News.class);
        //加载图片
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        mTvTitle.setText(mNews.getDescription());
        mImageLoader.displayImage(mNews.getImage(), mIvTitle, options);
        mLvNews.setAdapter(new NewsItemAdapter(mMainActivity, mNews.getStories()));
    }
}
