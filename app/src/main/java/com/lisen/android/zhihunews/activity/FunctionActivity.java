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

    public ImageLoader mImageLoader = ImageLoader.getInstance();
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
        mImageLoader.init(ImageLoaderConfiguration.createDefault(getBaseContext()));
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
        //String result = "{\"date\":\"20160705\",\"stories\":[{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/a8900f0d3047e40fd9ca618901099083.jpg\"],\"type\":0,\"id\":8521072,\"ga_prefix\":\"070511\",\"title\":\"最终我能把手机落在出租车上简直是一个奇迹\"},{\"title\":\"这个独自屠龙的勇士，终于迎来了他生死攸关的时刻\",\"ga_prefix\":\"070510\",\"images\":[\"http:\\/\\/pic3.zhimg.com\\/1ad715ddee84c94b70cc6d1198204522.jpg\"],\"multipic\":true,\"type\":0,\"id\":8526036},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/80e6b50a45784a43938d98369a2cb01c.jpg\"],\"type\":0,\"id\":8518953,\"ga_prefix\":\"070509\",\"title\":\"世界上有好多萌萌的迷你小国，比如「可爱国」\"},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/6e789aee0ea231fd57a2aa6ac1a351ef.jpg\"],\"type\":0,\"id\":8522556,\"ga_prefix\":\"070508\",\"title\":\"在多云多雨的贵州建了一个世界最大的射电望远镜，它能看得远吗？\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/9ec629e31357582bb0144b7f17269b24.jpg\"],\"type\":0,\"id\":8518689,\"ga_prefix\":\"070507\",\"title\":\"以前没有电脑手机的时候，大学生可没闲着\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/85e6c3f7c3a0b04e23dd22513c276b38.jpg\"],\"type\":0,\"id\":8523808,\"ga_prefix\":\"070507\",\"title\":\"一个人的教养如何体现？这个小孩子给出了一种答案\"},{\"title\":\"还有一个月，奥运场馆建咋样了，我们去看了看\",\"ga_prefix\":\"070507\",\"images\":[\"http:\\/\\/pic1.zhimg.com\\/04a21154a549eb4c6d72d413f1e9f934.jpg\"],\"multipic\":true,\"type\":0,\"id\":8525259},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/cca6c5d6661bcbdda70c42fab36229b3.jpg\"],\"type\":0,\"id\":8525544,\"ga_prefix\":\"070507\",\"title\":\"读读日报 24 小时热门 TOP 5 · 今年 NBA 砸钱多吗？\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/a740c0312368f668e3b983b46293f96c.jpg\"],\"type\":0,\"id\":8517157,\"ga_prefix\":\"070506\",\"title\":\"瞎扯 · 如何正确地吐槽\"}],\"top_stories\":[{\"image\":\"http:\\/\\/pic2.zhimg.com\\/b9f805caaaea6eeb21707e22acf5d961.jpg\",\"type\":0,\"id\":8526036,\"ga_prefix\":\"070510\",\"title\":\"这个独自屠龙的勇士，终于迎来了他生死攸关的时刻\"},{\"image\":\"http:\\/\\/pic3.zhimg.com\\/a6f7ee6a12e8587f507a4800e222b21e.jpg\",\"type\":0,\"id\":8518689,\"ga_prefix\":\"070507\",\"title\":\"以前没有电脑手机的时候，大学生可没闲着\"},{\"image\":\"http:\\/\\/pic4.zhimg.com\\/d6deec49e1348274900619e80a8422b7.jpg\",\"type\":0,\"id\":8523808,\"ga_prefix\":\"070507\",\"title\":\"一个人的教养如何体现？这个小孩子给出了一种答案\"},{\"image\":\"http:\\/\\/pic1.zhimg.com\\/7dc67d14ccf1dd6de83912758a993364.jpg\",\"type\":0,\"id\":8525544,\"ga_prefix\":\"070507\",\"title\":\"读读日报 24 小时热门 TOP 5 · 今年 NBA 砸钱多吗？\"},{\"image\":\"http:\\/\\/pic2.zhimg.com\\/c2f6751337dce709b129aeb6ebe5d8e9.jpg\",\"type\":0,\"id\":8525259,\"ga_prefix\":\"070507\",\"title\":\"还有一个月，奥运场馆建咋样了，我们去看了看\"}]}";
       HttpUtils.get(Constant.LATEST_NEWS, new TextHttpResponseHandler() {
           @Override
           public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

           }

           @Override
           public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("TAG", responseString);
           }
       });

    }


}
