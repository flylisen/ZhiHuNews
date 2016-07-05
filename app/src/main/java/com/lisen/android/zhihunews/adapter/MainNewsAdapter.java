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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class MainNewsAdapter extends BaseAdapter {

    private Context mContext;
    private ImageLoader mImageLoader;
    private List<StoryEntry> mStoryEntryList;
    private LayoutInflater mInflater;
    private DisplayImageOptions mOptions;

    public MainNewsAdapter(Context context) {
        mContext = context;
        mImageLoader = ImageLoader.getInstance();
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        mInflater = LayoutInflater.from(context);
      mStoryEntryList = new ArrayList<>();
    }

    public void addDatas(List<StoryEntry> datas) {
        mStoryEntryList.addAll(datas);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mStoryEntryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStoryEntryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.lv_main_news_item, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.mvTitle = (ImageView) convertView.findViewById(R.id.iv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        mImageLoader.displayImage(mStoryEntryList.get(position).getImages().get(0), holder.mvTitle, mOptions);
        holder.tvTitle.setText(mStoryEntryList.get(position).getTitle());
        return convertView;
    }

    class ViewHolder {
        TextView tvTitle;
        ImageView mvTitle;
    }
}
