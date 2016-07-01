package com.lisen.android.zhihunews;

import android.util.Log;

import com.lisen.android.zhihunews.util.HttpUtils;
import com.loopj.android.http.TextHttpResponseHandler;

import org.junit.Test;

import cz.msebera.android.httpclient.Header;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void httpGet() throws Exception {
        HttpUtils.get("/news/latest", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("Test", "获取数据失败");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                responseString = responseString.replaceAll("'", "''");
                Log.d("Test", responseString);
            }
        });
    }
}