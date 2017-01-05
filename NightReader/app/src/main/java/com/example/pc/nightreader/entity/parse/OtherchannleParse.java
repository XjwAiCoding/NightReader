package com.example.pc.nightreader.entity.parse;

import android.content.Context;

import com.example.pc.nightreader.entity.OtherChannle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class OtherchannleParse {

    /**
     *
     * @param pContext
     * @param pJSONObject   JsonObject对象
     * @return
     */
    public static OtherChannle parseOtherChannleFromJson(Context pContext, JSONObject pJSONObject) {
        OtherChannle _OtherChannle=new OtherChannle();
        _OtherChannle.setName(pJSONObject.optString("name"));
        _OtherChannle.setSource(pJSONObject.optString("source"));
        return _OtherChannle;
    }

    /**
     *
     * @param pContext
     * @param pJsonData  json字符串
     * @return
     */
    public static ArrayList<OtherChannle> parseOtherChannleListFromJson(Context pContext, String pJsonData){

        ArrayList<OtherChannle> _OtherChannleList=new ArrayList<>();
        try{
            JSONObject _JsonObject=new JSONObject(pJsonData);
            JSONArray _JsonArray=_JsonObject.getJSONArray("Channle");
            for(int i=0;i<_JsonArray.length();i++){
                JSONObject _object=_JsonArray.getJSONObject(i);
                //往list里添加实体类对象
                _OtherChannleList.add(parseOtherChannleFromJson(pContext,_object));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return  _OtherChannleList;
    }
}
