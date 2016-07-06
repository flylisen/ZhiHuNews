package com.lisen.android.zhihunews.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.model.Before;
import com.lisen.android.zhihunews.model.Content;
import com.lisen.android.zhihunews.model.StoryEntry;
import com.lisen.android.zhihunews.util.Constant;
import com.lisen.android.zhihunews.util.HttpUtils;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

// TODO: 2016/7/6 添加新闻内容头部背景图片 
public class NewsContentActivity extends AppCompatActivity {

    private CoordinatorLayout mCL;
    private Toolbar mToolBar;
    private WebView mWebView;
    private StoryEntry mStoryEntry;
    private Content mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        intiView();
        loadData();
    }

    private void loadData() {
        mStoryEntry = (StoryEntry) getIntent().getSerializableExtra("story_entry");
        if (HttpUtils.isNetworkConnected(NewsContentActivity.this)) {
            //网络通，从网络加载
            HttpUtils.get(Constant.CONTENT + mStoryEntry.getId(), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    // TODO: 2016/7/6 存储进数据库
                    parseJson(responseString);
                }
            });
        } else {
            // TODO: 2016/7/6 网络不通，从数据库加载
        }
    }

    private void parseJson(String responseString) {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<Content>(){}.getType();
        mContent = gson.fromJson(responseString, type);
        if (mContent != null) {
            String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
            String html = "<html><head>" + css + "</head><body>" + mContent.getBody() + "</body></html>";
            html = html.replace("< div class=\"img-place-holder\">", "");
            mWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
        }
    }

    //初始化控件
    private void intiView() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
         mCL = (CoordinatorLayout) findViewById(R.id.cl_newsContentActivity);
        mWebView = (WebView) findViewById(R.id.web_view);
        //设置toolbar
        mToolBar.setBackgroundColor(getResources().getColor(R.color.menuHeader));
        mToolBar.setTitle("享受阅读的乐趣");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置domStorage
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
