package com.example.pc.nightreader.logic;

import android.content.Context;

import com.example.pc.nightreader.db.OtherChannleDBHelper;
import com.example.pc.nightreader.entity.OtherChannle;
import com.example.pc.nightreader.entity.parse.OtherchannleParse;
import com.example.pc.nightreader.utils.file.FileAssetsUtil;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class OtherChannleHelper {

    /** 需要解析的json文件名*/
    public static final  String OtherChannle_FileName = "OtherChannle.txt";
    public static void initOtherChannleData(final Context pContext){
        //解析
        String _OtherChannleJsonData = FileAssetsUtil.getStringFromAssert(pContext, OtherChannle_FileName);
        ArrayList<OtherChannle> _OtherChannleList = OtherchannleParse.parseOtherChannleListFromJson(pContext,_OtherChannleJsonData);
        //入库
        OtherChannleDBHelper _OtherChannleDBHelper=new OtherChannleDBHelper(pContext);
        long _result= _OtherChannleDBHelper.insertOtherChannle(_OtherChannleList);

    }
}
