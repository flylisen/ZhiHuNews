package com.lisen.android.zhihunews.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.db.CacheDbHelper;
import com.lisen.android.zhihunews.fragment.MainNewsFragment;
import com.lisen.android.zhihunews.util.HttpUtils;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private CacheDbHelper mCacheDbHelper;
    private SwipeRefreshLayout mSR;
    private String curId = "";
    //private ImageLoader mImageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mCacheDbHelper = new CacheDbHelper(this, 1);

        /*mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(this));*/
        loadLatest();
    }

    public void loadLatest() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainNewsFragment(),
               "latest").commit();
        curId = "latest";
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_mainActivity);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.menuHeader));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,
                mDrawerLayout, mToolbar, R.string.close_name, R.string.close_name);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mSR = (SwipeRefreshLayout) findViewById(R.id.refresh_mainActivity);
        mSR.setColorSchemeColors(android.R.color.holo_blue_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark);
        mSR.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                replaceFragment();
                mSR.setRefreshing(false);
            }
        });
    }

    private void replaceFragment() {
        if (curId.equals("latest")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new MainNewsFragment(),
                    "latest").commit();
        } else {
            // do nothing;
        }

    }

    public CacheDbHelper getCacheDbHelper() {
        return mCacheDbHelper;
    }

    public void setSwipeFreshEnalbe(boolean enalbe) {
        mSR.setEnabled(enalbe);
    }

    public boolean isContect() {
        return HttpUtils.isNetworkConnected(this);
    }

    public void setCurId(String id) {
        curId = id;
    }

    public void closeMenu() {
        mDrawerLayout.closeDrawers();
    }
}
