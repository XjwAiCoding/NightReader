package com.example.pc.nightreader.entity.parse;

import android.content.Context;

import com.example.pc.nightreader.entity.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class VideoParse {

    /**
     *
     * @param pContext
     * @param pJSONObject   JsonObject对象
     * @return
     */
    public static Video parseVideoFromJson(Context pContext, JSONObject pJSONObject) {
        Video _Video=new Video();
        _Video.setTitle(pJSONObject.optString("title"));
        _Video.setUrl(pJSONObject.optString("mp4Hd_url"));
        _Video.setCoverImg(pJSONObject.optString("cover"));
         _Video.setPlayCount(pJSONObject.optString("playCount"));
        _Video.setVideosource(pJSONObject.optString("videosource"));

        return _Video;
    }

    /**
     *
     * @param pContext
     * @param pJsonData  json字符串
     * @return
     */
    public static ArrayList<Video> parseVideoListFromJson(Context pContext, String pJsonData){

        ArrayList<Video> _VideoList=new ArrayList<>();
        try{
            JSONArray _JsonArray=new JSONArray(pJsonData);
            for(int i=0;i<_JsonArray.length();i++){
                JSONObject _object=_JsonArray.getJSONObject(i);
                //往list里添加实体类对象
                _VideoList.add(parseVideoFromJson(pContext,_object));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return  _VideoList;
    }
}