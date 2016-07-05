package com.lisen.android.zhihunews.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lisen.android.zhihunews.R;
import com.lisen.android.zhihunews.model.TopStoryEntry;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
public class Kanner extends FrameLayout {

    private ImageLoader mImageLoader;
    private DisplayImageOptions mOptions;
    private List<TopStoryEntry> mListTopStoryEntry;
    private Context mContext;
    private List<View> mViews;
    private List<ImageView> mIvDots;
    private ViewPager mVP;
    private LinearLayout mLlDot;


    public Kanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        mContext = context;
        mListTopStoryEntry = new ArrayList<>();
        initView();
    }

    private void initView() {
        mViews = new ArrayList<>();
        mIvDots = new ArrayList<>();

    }

    public Kanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Kanner(Context context) {
        this(context, null);
    }

    public void setListTopStoryEntry(List<TopStoryEntry> datas) {
        mListTopStoryEntry = datas;
        reset();
    }

    private void reset() {
        mViews.clear();
        initUi();
    }

    private void initUi() {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.karner_layout, this, true);
        mVP = (ViewPager) view.findViewById(R.id.vp);
        mLlDot = (LinearLayout) view.findViewById(R.id.ll_dot);
        mLlDot.removeAllViews();

        int len = mListTopStoryEntry.size();

        //初始化点集合
        for (int i = 0; i < len; i++) {
            ImageView imageView = new ImageView(mContext);
            //设定参数
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            mLlDot.addView(imageView, layoutParams);
            mIvDots.add(imageView);
        }

       /* if (mImageLoader == null) {
            mImageLoader = ImageLoader.getInstance();
        }*/
        for (int i = 0; i <= len + 1 ; i++) {
            View fm = LayoutInflater.from(mContext).inflate(
                    R.layout.kanner_content_layout, null);
            ImageView iv = (ImageView) fm.findViewById(R.id.iv_top);
            TextView tv = (TextView) fm.findViewById(R.id.tv_top_title);

            if (i == len + 1) {
                //最后面的缓冲位置,将其设置为第一张
                mImageLoader.displayImage(mListTopStoryEntry.get(0).getImage(),
                        iv, mOptions);
                tv.setText(mListTopStoryEntry.get(0).getTitle());
            } else if (i == 0) {
                //最前面的缓冲位置，将其设置为最后一张
                mImageLoader.displayImage(mListTopStoryEntry.get(len - 1).getImage(),
                        iv, mOptions);
                tv.setText(mListTopStoryEntry.get(len - 1).getTitle());
            } else {
                mImageLoader.displayImage(mListTopStoryEntry.get(i - 1).getImage(),
                        iv, mOptions);
                tv.setText(mListTopStoryEntry.get(i - 1).getTitle());
            }
            mViews.add(fm);
        }

        mVP.setAdapter(new MyViewPagerAdapter());
        mVP.setOnPageChangeListener(new MyOnpageChangeListener());


    }

    class MyViewPagerAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    class MyOnpageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_IDLE:
                    if (mVP.getCurrentItem() == mListTopStoryEntry.size() + 1) {
                        //将其设为1
                        mVP.setCurrentItem(1, false);
                    } else if (mVP.getCurrentItem() == 0) {
                        mVP.setCurrentItem(mListTopStoryEntry.size(), false);
                    }
                    break;
            }
        }
    }
}
