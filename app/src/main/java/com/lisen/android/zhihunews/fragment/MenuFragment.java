package com.lisen.android.zhihunews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.activity.MainActivity;
import com.lisen.android.zhihunews.model.MenuListItem;
import com.lisen.android.zhihunews.util.Constant;
import com.lisen.android.zhihunews.util.HttpUtils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/7/1.
 */
public class MenuFragment extends BaseFragment {

    private ListView mLvMenu;
    private List<MenuListItem> mItems;
    private TextView mTvHome;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu, container, false);
        mLvMenu = (ListView) view.findViewById(R.id.lv_menu);
        mLvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String mid = mItems.get(position).getId();
                getFragmentManager().beginTransaction().replace(R.id.fl_content,
                        new NewsFragment(mid), "news").commit();
                ((MainActivity) mMainActivity).setCurId(mid);
                ((MainActivity) mMainActivity).closeMenu();
            }
        });
        mTvHome = (TextView) view.findViewById(R.id.tv_menu_home);
        mTvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mMainActivity).loadLatest();
                ((MainActivity) mMainActivity).closeMenu();
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mItems = new ArrayList<>();
        if (HttpUtils.isNetworkConnected(mMainActivity)) {
            //网络通，从网络上加载
            HttpUtils.get(Constant.THEME, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                   parseJson(response);
                    // TODO: 2016/7/6 存储进 sharedPreferecnce
                }
            });
        } else {
            //网络不通
            // TODO: 2016/7/6 从 sharedPreferecnce 加载
        }
    }

    private void parseJson(JSONObject response) {
        try {
            JSONArray itemArray = response.getJSONArray("others");
            for (int i = 0; i < itemArray.length(); i++) {
                MenuListItem item = new MenuListItem();
                JSONObject jsonObject = itemArray.getJSONObject(i);
                item.setTitle(jsonObject.getString("name"));
                item.setId(String.valueOf(jsonObject.getInt("id")));
                mItems.add(item);
            }

            ArrayAdapter adapter = new ArrayAdapter(mMainActivity, -1, mItems) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(mMainActivity).inflate(R.layout.menu_item,
                                parent, false);
                        TextView tv = (TextView) convertView.findViewById(R.id.tv_title);
                        tv.setText(mItems.get(position).getTitle());
                    }
                    return convertView;
                }
            };
            mLvMenu.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
