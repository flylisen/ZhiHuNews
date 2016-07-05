package com.lisen.android.zhihunews.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/1.
 */
public class HttpUtils {
    private static AsyncHttpClient mClient = new AsyncHttpClient();

    public static void get (String url, ResponseHandlerInterface responseHandle) {
        mClient.get(Constant.BASE_URL + url, responseHandle);
    }

    public static void get(String url, MyResponse myResponse) {
        String fullUrl = Constant.BASE_URL + url;
        HttpURLConnection connection =null;
        try {
            URL myUrl = new URL(fullUrl);
            connection = (HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            if (is != null) {
               String result = readStream(is);
                if (result != null && result.length() > 0) {
                    myResponse.onSuccess(result);
                } else {
                    myResponse.onFaid("获取数据失败");
                }
            }  else {
                myResponse.onFaid("获取数据失败");
            }

        } catch (Exception e) {

        }
    }

    private  static String readStream(InputStream is) throws IOException {
        String result = "";
        InputStreamReader reader = new InputStreamReader(is, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        result = builder.toString();
        return result;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cnmg = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cnmg.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }
}
