package com.lisen.android.zhihunews.util;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cz.msebera.android.httpclient.Header;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/4.
 */
@RunWith(MockitoJUnitRunner.class)
public class HttpUtilsTest {

    AsyncHttpClient mClient;
    boolean result;
    @Mock
    TextHttpResponseHandler mHandler;
    @Mock
    Context mContext;
    @Before
    public void setUp() throws Exception {
        mClient = new AsyncHttpClient();
        mClient.get(Constant.BASE_URL + Constant.LATEST_NEWS, mHandler) ;
        result = HttpUtils.isNetworkConnected(mContext);

    }

    @Test
    public void testGet() throws Exception {
       // assertEquals(false, result);

    }

    @Test
    public void testIsNetworkConnected() throws Exception {
         assertEquals(true, result);
    }
}