package com.example.pc.nightreader.logic;

import android.content.Context;

import com.example.pc.nightreader.db.PhotoDBHelper;
import com.example.pc.nightreader.entity.Photo;
import com.example.pc.nightreader.entity.parse.PhotoParse;
import com.example.pc.nightreader.utils.OkHttpUtils;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class PhotoHelper {

    private  static  final String url= "http://gank.io/api/data/福利/20/1";

    public static void initPhotoData(final Context pContext){

        OkHttpUtils.ResultCallback<String> _resultCallback=new OkHttpUtils.ResultCallback<String>() {
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

        OkHttpUtils.get(url, _resultCallback);

    }
}
