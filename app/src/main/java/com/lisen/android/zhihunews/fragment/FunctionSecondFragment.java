package com.lisen.android.zhihunews.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lisen.android.zhihunews.util.HttpUtils;

/**
 * Created by Administrator on 2016/7/6.
 */
public class FunctionSecondFragment extends BaseFragment {
    TextView textView;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         textView = new TextView(mMainActivity);
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams()
        textView.setGravity(Gravity.CENTER);
        textView.setText("第二个Fragment");
        textView.setBackgroundColor(Color.parseColor("blue"));
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        if (HttpUtils.isNetworkConnected(mMainActivity)) {
            textView.setText("网络连接");
        }
    }
}
