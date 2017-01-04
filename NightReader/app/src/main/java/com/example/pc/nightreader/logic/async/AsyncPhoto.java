package com.example.pc.nightreader.logic.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.pc.nightreader.db.PhotoDBHelper;
import com.example.pc.nightreader.entity.Photo;
import com.example.pc.nightreader.logic.PhotoHelper;
import com.example.pc.nightreader.logic.listener.KLoadListener;

import java.util.List;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class AsyncPhoto extends AsyncTask<Void,Void,List<Photo>> {
    private Context mContext;
    private KLoadListener<List<Photo>> mKLoadListener;

    public AsyncPhoto(Context mContext, KLoadListener<List<Photo>> mKLoadListener) {
        this.mContext = mContext;
        this.mKLoadListener = mKLoadListener;
    }

    @Override
    protected List<Photo> doInBackground(Void... params) {
        //解析入库
        try {
            PhotoHelper.initPhotoData(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查询数据库，返回数据集合
        List<Photo> _PhotoList= new PhotoDBHelper(mContext).queryAllPhoto();
        return _PhotoList;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Photo> photos) {
        super.onPostExecute(photos);
        if (photos!=null&&photos.size()!=0){
            mKLoadListener.onSuccess(photos);
        }else {
            //若返回的集合为空，则抛出相应异常
            mKLoadListener.onFail(null);
        }
    }
}
