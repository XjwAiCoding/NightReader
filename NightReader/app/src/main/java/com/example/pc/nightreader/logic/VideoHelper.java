package com.example.pc.nightreader.logic;

import android.content.Context;

import com.example.pc.nightreader.db.VideoDBHelper;
import com.example.pc.nightreader.entity.Video;
import com.example.pc.nightreader.entity.parse.VideoParse;
import com.example.pc.nightreader.utils.file.FileAssetsUtil;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class VideoHelper {

    //private  static  final String url= "http://gank.io/api/data/休息视频/20/3";
    /** 需要解析的json文件名*/
    public static final  String Video_FileName = "video.txt";
    public static void initVideoData(final Context pContext){

        //解析
        String _VideoJsonData = FileAssetsUtil.getStringFromAssert(pContext, Video_FileName);
        ArrayList<Video> _VideoList = VideoParse.parseVideoListFromJson(pContext,_VideoJsonData);
        //入库
        VideoDBHelper _VideoDBHelper=new VideoDBHelper(pContext);
        long _result= _VideoDBHelper.insertVideo(_VideoList);

/*
        OkHttpUtils.ResultCallback<String> _resultCallback=new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                //解析
                ArrayList<Video> _VideoList = VideoParse.parseVideoListFromJson(pContext,response);
                //入库
                VideoDBHelper _VideoDBHelper=new VideoDBHelper(pContext);
                long _result= _VideoDBHelper.insertVideo(_VideoList);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };

        OkHttpUtils.get(url, _resultCallback);*/

    }

}
