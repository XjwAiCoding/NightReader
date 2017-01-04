package com.example.pc.nightreader.logic;

import android.content.Context;

import com.example.pc.nightreader.db.PhotoDBHelper;
import com.example.pc.nightreader.entity.Photo;
import com.example.pc.nightreader.entity.parse.PhotoParse;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class PhotoHelper {

    private  static  final String url= "http://gank.io/api/data/福利/20/1";

    public static void initPhotoData(final Context pContext) throws Exception {

      /*  OkHttpUtils.ResultCallback<String> _resultCallback=new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                //解析
                ArrayList<Photo> _PhotoList = PhotoParse.parsePhotoListFromJson(pContext,response);
                //入库
                PhotoDBHelper _PhotoDBHelper=new PhotoDBHelper(pContext);
                long _result= _PhotoDBHelper.insertPhoto(_PhotoList);
            }

            @Override
            public void onFailure(Exception e) {

            }
        };

        OkHttpUtils.get(url, _resultCallback);*/
        // 异步任务中应该放置同步方法，否则数据不同步，可能第一次取不到数据,第二次取第一次的数据
        PhotoHelper.run(pContext);
    }

    //  okhttp同步方法
    public static void run(final Context pContext) throws Exception {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String responseContent=response.body().string();
        //解析
        ArrayList<Photo> _PhotoList = PhotoParse.parsePhotoListFromJson(pContext,responseContent);
        //入库
        PhotoDBHelper _PhotoDBHelper=new PhotoDBHelper(pContext);
        long _result= _PhotoDBHelper.insertPhoto(_PhotoList);

    }
}
