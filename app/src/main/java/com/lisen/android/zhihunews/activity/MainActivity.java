package com.lisen.android.zhihunews.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.util.HttpUtils;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_mainActivity);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.menuHeader));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,
                mDrawerLayout, mToolbar, R.string.close_name, R.string.close_name);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }
}
