package com.lisen.android.zhihunews.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * Created by Administrator on 2016/7/1.
 */
public class HttpUtils {
    private static AsyncHttpClient mClient = new AsyncHttpClient();

    public static void get (String url, ResponseHandlerInterface responseHandle) {
        mClient.get(Constant.BASE_URL + url, responseHandle);
    }
}
