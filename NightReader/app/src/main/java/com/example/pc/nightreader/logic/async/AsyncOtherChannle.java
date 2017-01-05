package com.example.pc.nightreader.logic.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.pc.nightreader.db.OtherChannleDBHelper;
import com.example.pc.nightreader.entity.OtherChannle;
import com.example.pc.nightreader.logic.OtherChannleHelper;
import com.example.pc.nightreader.logic.listener.KLoadListener;

import java.util.List;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class AsyncOtherChannle extends AsyncTask<Void,Void,List<OtherChannle>>{
    private Context mContext;
    private KLoadListener<List<OtherChannle>> mKLoadListener;

    public AsyncOtherChannle(Context mContext, KLoadListener<List<OtherChannle>> mKLoadListener) {
        this.mContext = mContext;
        this.mKLoadListener = mKLoadListener;
    }


    @Override
    protected List<OtherChannle> doInBackground(Void... params) {
        //解析入库
        OtherChannleHelper.initOtherChannleData(mContext);
        //查询数据库，返回数据集合
        List<OtherChannle> _OtherChannleList= new OtherChannleDBHelper(mContext).queryAllOtherChannle();
        return _OtherChannleList;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<OtherChannle> OtherChannles) {
        super.onPostExecute(OtherChannles);
        if (OtherChannles!=null&&OtherChannles.size()!=0){
            mKLoadListener.onSuccess(OtherChannles);
        }else {
            //若返回的集合为空，则抛出相应异常
            mKLoadListener.onFail(null);
        }

    }
}
