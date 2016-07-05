package com.lisen.android.zhihunews;

import android.content.Context;
import android.os.Bundle;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.lisen.android.zhihunews.activity.MainActivity;
import com.lisen.android.zhihunews.util.Constant;
import com.lisen.android.zhihunews.util.HttpUtils;
import com.lisen.android.zhihunews.util.MyResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/7/5.
 */

public class TestHttpUtils extends InstrumentationTestCase{
    private Context mContext;
    MainActivity ma;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    public void testisNetworkConnected() {

        boolean result = HttpUtils.isNetworkConnected(ma);

        assertEquals(true, result);
    }

    public void testGet() {
        HttpUtils.get(Constant.LATEST_NEWS, new MyResponse() {
            @Override
            public void onFaid(String respondString) {
                Log.d("Log_tag", respondString);
            }

            @Override
            public void onSuccess(String respondString) {
                Log.d("Log_tag", respondString);
            }
        });
    }


}
