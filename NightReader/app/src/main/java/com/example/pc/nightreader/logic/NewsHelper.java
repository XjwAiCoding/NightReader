package com.example.pc.nightreader.logic;

import android.content.Context;

import com.example.pc.nightreader.db.NewsDBHelper;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.entity.parse.NewsParse;
import com.example.pc.nightreader.utils.OkHttpUtils;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2016/10/10.
 */
public class NewsHelper {

    private  static  final String url= "http://www.imooc.com/api/teacher?type=4&num=30";

    public static void initNewsData(final Context pContext){

        OkHttpUtils.ResultCallback<String> _resultCallback=new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                //解析
                ArrayList<News> _NewsList = NewsParse.parseNewsListFromJson(pContext,response);
                //入库
                NewsDBHelper _NewsDBHelper=new NewsDBHelper(pContext);
                long _result= _NewsDBHelper.insertNews(_NewsList);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };

        OkHttpUtils.get(url, _resultCallback);

    }


}
