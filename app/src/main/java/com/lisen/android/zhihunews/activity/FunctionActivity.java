package com.lisen.android.zhihunews.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.model.Latest;
import com.lisen.android.zhihunews.model.TopStoryEntry;
import com.lisen.android.zhihunews.util.Constant;
import com.lisen.android.zhihunews.util.HttpUtils;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FunctionActivity extends AppCompatActivity {


    private ImageView iv;
    private TextView tv;
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0x111:
                   String result = (String) msg.obj;
                    Log.d("TAG", result);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
    }


}
