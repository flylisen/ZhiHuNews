package com.lisen.android.zhihunews.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/6.
 */
public class FunctionFirstFragment extends BaseFragment {
    TextView textView;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         textView = new TextView(mMainActivity);
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams()
        textView.setGravity(Gravity.CENTER);
        textView.setText("第一个Fragment");
        textView.setBackgroundColor(Color.parseColor("red"));


        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        Log.d("TAG", "height = " +layoutParams.height);
    }
}
