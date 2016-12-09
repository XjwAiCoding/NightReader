package com.example.pc.nightreader.entity.parse;

import android.content.Context;

import com.example.pc.nightreader.entity.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class PhotoParse  {
    /**
     *
     * @param pContext
     * @param pJSONObject   JsonObject对象
     * @return
     */
    public static Photo  parsePhotoFromJson(Context pContext, JSONObject pJSONObject) {
        Photo _Photo=new Photo();
        _Photo.setPublicDate(pJSONObject.optString("desc"));
        _Photo.setUrl(pJSONObject.optString("url"));

        return _Photo;
    }

    /**
     *
     * @param pContext
     * @param pJsonData  json字符串
     * @return
     */
    public static ArrayList<Photo> parsePhotoListFromJson(Context pContext, String pJsonData){

        ArrayList<Photo> _PhotoList=new ArrayList<>();
        try{
            JSONObject _JsonObject=new JSONObject(pJsonData);
            JSONArray _JsonArray=_JsonObject.getJSONArray("results");
            for(int i=0;i<_JsonArray.length();i++){
                JSONObject _object=_JsonArray.getJSONObject(i);
                //往list里添加实体类对象
                _PhotoList.add(parsePhotoFromJson(pContext,_object));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return  _PhotoList;
    }
}
