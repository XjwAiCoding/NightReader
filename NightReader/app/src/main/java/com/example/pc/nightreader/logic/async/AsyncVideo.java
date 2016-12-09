package com.example.pc.nightreader.logic.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.pc.nightreader.db.VideoDBHelper;
import com.example.pc.nightreader.entity.Video;
import com.example.pc.nightreader.logic.VideoHelper;
import com.example.pc.nightreader.logic.listener.KLoadListener;

import java.util.List;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class AsyncVideo  extends AsyncTask<Void,Void,List<Video>>{
    private Context mContext;
    private KLoadListener<List<Video>> mKLoadListener;

    public AsyncVideo() {
        super();
    }

    public AsyncVideo(Context mContext, KLoadListener<List<Video>> mKLoadListener) {
        this.mContext = mContext;
        this.mKLoadListener = mKLoadListener;
    }

    @Override
    protected List<Video> doInBackground(Void... params) {
        //解析入库
        VideoHelper.initVideoData(mContext);
        //查询数据库，返回数据集合
        List<Video> _VideoList= new VideoDBHelper(mContext).queryAllVideo();
        return _VideoList;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Video> videos) {
        super.onPostExecute(videos);
        if (videos!=null&&videos.size()!=0){
            mKLoadListener.onSuccess(videos);
        }else {
            //若返回的集合为空，则抛出相应异常
            mKLoadListener.onFail(null);
        }

    }


}
