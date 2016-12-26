package com.example.pc.nightreader.entity.parse;

import android.content.Context;

import com.example.pc.nightreader.entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2016/10/10.
 */
public class NewsParse {
    /**
     *
     * @param pContext
     * @param pJSONObject   JsonObject对象
     * @return
     */
    public static News parseNewsFromJson(Context pContext, JSONObject pJSONObject) {
         News _News=new News();
        _News.setTitle(pJSONObject.optString("title"));
        _News.setDescription(pJSONObject.optString("description"));
        _News.setPicUrl(pJSONObject.optString("picUrl"));
         _News.setUrl(pJSONObject.optString("url"));
        return _News;
    }

    /**
     *
     * @param pContext
     * @param pJsonData  json字符串
     * @return
     */
    public static ArrayList<News> parseNewsListFromJson(Context pContext,String pJsonData){

        ArrayList<News> _newsList=new ArrayList<>();
        try{
            JSONObject _JsonObject=new JSONObject(pJsonData);
            JSONArray _JsonArray=_JsonObject.getJSONArray("newslist");
            for(int i=0;i<_JsonArray.length();i++){
                JSONObject _object=_JsonArray.getJSONObject(i);
                //往list里添加实体类对象
                _newsList.add(parseNewsFromJson(pContext,_object));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return  _newsList;
    }


}
