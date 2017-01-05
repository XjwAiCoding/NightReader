package com.example.pc.nightreader.logic.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.pc.nightreader.db.MyChannleDBHelper;
import com.example.pc.nightreader.entity.MyChannle;
import com.example.pc.nightreader.logic.MyChannleHelper;
import com.example.pc.nightreader.logic.listener.KLoadListener;

import java.util.List;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class AsyncMyChannle  extends AsyncTask<Void,Void,List<MyChannle>>{
    private Context mContext;
    private KLoadListener<List<MyChannle>> mKLoadListener;

    public AsyncMyChannle(Context mContext, KLoadListener<List<MyChannle>> mKLoadListener) {
        this.mContext = mContext;
        this.mKLoadListener = mKLoadListener;
    }


    @Override
    protected List<MyChannle> doInBackground(Void... params) {
        //解析入库
        MyChannleHelper.initMyChannleData(mContext);
        //查询数据库，返回数据集合
        List<MyChannle> _MyChannleList= new MyChannleDBHelper(mContext).queryAllMyChannle();
        return _MyChannleList;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<MyChannle> MyChannles) {
        super.onPostExecute(MyChannles);
        if (MyChannles!=null&&MyChannles.size()!=0){
            mKLoadListener.onSuccess(MyChannles);
        }else {
            //若返回的集合为空，则抛出相应异常
            mKLoadListener.onFail(null);
        }

    }
}
