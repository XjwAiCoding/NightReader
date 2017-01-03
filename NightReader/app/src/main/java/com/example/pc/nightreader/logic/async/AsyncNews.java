package com.example.pc.nightreader.logic.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.pc.nightreader.db.NewsDBHelper;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.logic.NewsHelper;
import com.example.pc.nightreader.logic.listener.KLoadListener;

import java.util.List;

/**
 * Created by xujiawei on 2016/10/10.
 */
public class AsyncNews extends AsyncTask<Void,Void,List<News>> {
    private Context mContext;
    private KLoadListener<List<News>> mKLoadListener;
    private  int position;
    @Override
    protected List<News> doInBackground(Void... voids) {
        //解析入库
         NewsHelper.initNewsData(mContext,position);
        //查询数据库，返回数据集合
       List<News> _newsList= new NewsDBHelper(mContext).queryAllNews();
        return _newsList;
    }

    public AsyncNews(Context pContext, KLoadListener<List<News>> pKLoadListener,int position) {
        this.mContext=pContext;
        this.mKLoadListener=pKLoadListener;
        this.position=position;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<News> news) {
        super.onPostExecute(news);
        if (news!=null&&news.size()!=0){
            mKLoadListener.onSuccess(news);
        }else {
            //若返回的集合为空，则抛出相应异常
            mKLoadListener.onFail(null);
        }
    }
}
