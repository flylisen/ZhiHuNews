package com.lisen.android.zhihunews;

import android.support.v4.app.FragmentActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisen.android.zhihunews.activity.FunctionActivity;
import com.lisen.android.zhihunews.model.Latest;
import com.lisen.android.zhihunews.model.StoryEntry;
import com.lisen.android.zhihunews.model.TopStoryEntry;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class FunctionActivityTest extends ActivityInstrumentationTestCase2<FunctionActivity> {

    //DisplayImageOptions displayImageOptions;
    ImageView iv;
    TextView tv;
    public FunctionActivityTest() {
        super(FunctionActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        iv = (ImageView) getActivity().findViewById(R.id.iv);
        tv = (TextView) getActivity().findViewById(R.id.tv);

    }

    @SmallTest
    public void testLoad()  throws Exception  {
        String result = "{\"date\":\"20160705\",\"stories\":[{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/a8900f0d3047e40fd9ca618901099083.jpg\"],\"type\":0,\"id\":8521072,\"ga_prefix\":\"070511\",\"title\":\"最终我能把手机落在出租车上简直是一个奇迹\"},{\"title\":\"这个独自屠龙的勇士，终于迎来了他生死攸关的时刻\",\"ga_prefix\":\"070510\",\"images\":[\"http:\\/\\/pic3.zhimg.com\\/1ad715ddee84c94b70cc6d1198204522.jpg\"],\"multipic\":true,\"type\":0,\"id\":8526036},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/80e6b50a45784a43938d98369a2cb01c.jpg\"],\"type\":0,\"id\":8518953,\"ga_prefix\":\"070509\",\"title\":\"世界上有好多萌萌的迷你小国，比如「可爱国」\"},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/6e789aee0ea231fd57a2aa6ac1a351ef.jpg\"],\"type\":0,\"id\":8522556,\"ga_prefix\":\"070508\",\"title\":\"在多云多雨的贵州建了一个世界最大的射电望远镜，它能看得远吗？\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/9ec629e31357582bb0144b7f17269b24.jpg\"],\"type\":0,\"id\":8518689,\"ga_prefix\":\"070507\",\"title\":\"以前没有电脑手机的时候，大学生可没闲着\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/85e6c3f7c3a0b04e23dd22513c276b38.jpg\"],\"type\":0,\"id\":8523808,\"ga_prefix\":\"070507\",\"title\":\"一个人的教养如何体现？这个小孩子给出了一种答案\"},{\"title\":\"还有一个月，奥运场馆建咋样了，我们去看了看\",\"ga_prefix\":\"070507\",\"images\":[\"http:\\/\\/pic1.zhimg.com\\/04a21154a549eb4c6d72d413f1e9f934.jpg\"],\"multipic\":true,\"type\":0,\"id\":8525259},{\"images\":[\"http:\\/\\/pic4.zhimg.com\\/cca6c5d6661bcbdda70c42fab36229b3.jpg\"],\"type\":0,\"id\":8525544,\"ga_prefix\":\"070507\",\"title\":\"读读日报 24 小时热门 TOP 5 · 今年 NBA 砸钱多吗？\"},{\"images\":[\"http:\\/\\/pic1.zhimg.com\\/a740c0312368f668e3b983b46293f96c.jpg\"],\"type\":0,\"id\":8517157,\"ga_prefix\":\"070506\",\"title\":\"瞎扯 · 如何正确地吐槽\"}],\"top_stories\":[{\"image\":\"http:\\/\\/pic2.zhimg.com\\/b9f805caaaea6eeb21707e22acf5d961.jpg\",\"type\":0,\"id\":8526036,\"ga_prefix\":\"070510\",\"title\":\"这个独自屠龙的勇士，终于迎来了他生死攸关的时刻\"},{\"image\":\"http:\\/\\/pic3.zhimg.com\\/a6f7ee6a12e8587f507a4800e222b21e.jpg\",\"type\":0,\"id\":8518689,\"ga_prefix\":\"070507\",\"title\":\"以前没有电脑手机的时候，大学生可没闲着\"},{\"image\":\"http:\\/\\/pic4.zhimg.com\\/d6deec49e1348274900619e80a8422b7.jpg\",\"type\":0,\"id\":8523808,\"ga_prefix\":\"070507\",\"title\":\"一个人的教养如何体现？这个小孩子给出了一种答案\"},{\"image\":\"http:\\/\\/pic1.zhimg.com\\/7dc67d14ccf1dd6de83912758a993364.jpg\",\"type\":0,\"id\":8525544,\"ga_prefix\":\"070507\",\"title\":\"读读日报 24 小时热门 TOP 5 · 今年 NBA 砸钱多吗？\"},{\"image\":\"http:\\/\\/pic2.zhimg.com\\/c2f6751337dce709b129aeb6ebe5d8e9.jpg\",\"type\":0,\"id\":8525259,\"ga_prefix\":\"070507\",\"title\":\"还有一个月，奥运场馆建咋样了，我们去看了看\"}]}";
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<Latest>(){}.getType();
        Latest latest = gson.fromJson(result, type);
        System.out.println(latest.toString());
        List<TopStoryEntry> topStoryEntryList = latest.getTop_stories();
        System.out.println(topStoryEntryList.get(0).getTitle());
        System.out.println(topStoryEntryList.get(0).getImage());
        tv.setText(topStoryEntryList.get(0).getTitle());
        getActivity().mImageLoader.displayImage(topStoryEntryList.get(0).getImage(), iv);
    }
}
