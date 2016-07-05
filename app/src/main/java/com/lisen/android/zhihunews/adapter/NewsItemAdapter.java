package com.lisen.android.zhihunews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.model.StoryEntry;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
public class NewsItemAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<StoryEntry> mDatas;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;
    public NewsItemAdapter(Context context, List<StoryEntry> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.lv_news_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivTitle = (ImageView) convertView.findViewById(R.id.iv_title);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        StoryEntry storyEntry = mDatas.get(position);
        viewHolder.tvTitle.setText(storyEntry.getTitle());
        if (storyEntry.getImages() != null) {
            viewHolder.ivTitle.setVisibility(View.VISIBLE);
            mImageLoader.displayImage(storyEntry.getImages().get(0), viewHolder.ivTitle, options);
        } else {
            viewHolder.ivTitle.setVisibility(View.GONE);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView tvTitle;
        ImageView ivTitle;
    }
}
