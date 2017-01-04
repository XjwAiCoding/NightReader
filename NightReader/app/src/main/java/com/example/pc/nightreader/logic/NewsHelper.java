package com.example.pc.nightreader.logic;

import android.content.Context;

import com.example.pc.nightreader.db.NewsDBHelper;
import com.example.pc.nightreader.db.base.CommonData;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.entity.parse.NewsParse;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xujiawei on 2016/10/10.
 */
public class NewsHelper {

   // private  static  final String url= "http://api.tianapi.com/social/?key=6e64995fdca7d5438533a964ae38b486&num=50 ";

    public static void initNewsData(final Context pContext, final int position) throws Exception {

       /* OkHttpUtils.ResultCallback<String> _resultCallback=new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                //解析
                ArrayList<News> _NewsList = NewsParse.parseNewsListFromJson(pContext,response);
                //入库
                NewsDBHelper _NewsDBHelper=new NewsDBHelper(pContext);
                long _result= _NewsDBHelper.insertNews(_NewsList,position);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        OkHttpUtils.get(CommonData.getUrlList().get(position), _resultCallback);*/

        // 异步任务中应该放置同步方法，否则数据不同步，可能第一次取不到数据，第二次取第一次的数据
        NewsHelper.run(pContext,position);
    }
     //  okhttp同步方法
    public static void run(final Context pContext, final int position) throws Exception {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CommonData.getUrlList().get(position))
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String responseContent=response.body().string();
        //解析
        ArrayList<News> _NewsList = NewsParse.parseNewsListFromJson(pContext,responseContent);
        //入库
        NewsDBHelper _NewsDBHelper=new NewsDBHelper(pContext);
        long _result= _NewsDBHelper.insertNews(_NewsList,position);

    }

}
