package com.example.pc.nightreader.logic;

import android.content.Context;

import com.example.pc.nightreader.db.MyChannleDBHelper;
import com.example.pc.nightreader.entity.MyChannle;
import com.example.pc.nightreader.entity.parse.MyChannleParse;
import com.example.pc.nightreader.utils.file.FileAssetsUtil;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class MyChannleHelper {

    /** 需要解析的json文件名*/
    public static final  String MyChannle_FileName = "MyChannle.txt";
    public static void initMyChannleData(final Context pContext){
        //解析
        String _MyChannleJsonData = FileAssetsUtil.getStringFromAssert(pContext, MyChannle_FileName);
        ArrayList<MyChannle> _MyChannleList = MyChannleParse.parseMyChannleListFromJson(pContext,_MyChannleJsonData);
        //入库
        MyChannleDBHelper _MyChannleDBHelper=new MyChannleDBHelper(pContext);
        long _result= _MyChannleDBHelper.insertMyChannle(_MyChannleList);

    }
}
