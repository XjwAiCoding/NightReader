package com.example.pc.nightreader.entity.parse;

import android.content.Context;

import com.example.pc.nightreader.entity.MyChannle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class MyChannleParse {

    /**
     *
     * @param pContext
     * @param pJSONObject   JsonObject对象
     * @return
     */
    public static MyChannle parseMyChannleFromJson(Context pContext, JSONObject pJSONObject) {
        MyChannle _MyChannle=new MyChannle();
        _MyChannle.setName(pJSONObject.optString("name"));
        _MyChannle.setSource(pJSONObject.optString("source"));
        return _MyChannle;
    }

    /**
     *
     * @param pContext
     * @param pJsonData  json字符串
     * @return
     */
    public static ArrayList<MyChannle> parseMyChannleListFromJson(Context pContext, String pJsonData){

        ArrayList<MyChannle> _MyChannleList=new ArrayList<>();
        try{
            JSONObject _JsonObject=new JSONObject(pJsonData);
            JSONArray _JsonArray=_JsonObject.getJSONArray("Channle");
            for(int i=0;i<_JsonArray.length();i++){
                JSONObject _object=_JsonArray.getJSONObject(i);
                //往list里添加实体类对象
                _MyChannleList.add(parseMyChannleFromJson(pContext,_object));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return  _MyChannleList;
    }
}
