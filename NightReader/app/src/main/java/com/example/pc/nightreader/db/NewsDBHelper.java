package com.example.pc.nightreader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.nightreader.db.base.DBData;
import com.example.pc.nightreader.db.base.DBHelper;
import com.example.pc.nightreader.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/10/10.
 */
public class NewsDBHelper {
    /** TAG */
    private final static String TAG = NewsDBHelper.class.getSimpleName();
    /** 创建一个特殊的instance变量来充当锁 */
    private final byte[] LOCK = new byte[0];
    private Context mContext;
    private DBHelper mDBHelper;

    /** 构造 */
    public NewsDBHelper(Context pContext) {
        this.mContext = pContext;
        mDBHelper=DBHelper.getInstance(pContext);
    }
    /** 添加news */
    public long insertNews(List<News> pNewsList){
        long _result=0;
        ContentValues _CV = new ContentValues();
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            try {
                _DB.beginTransaction();
                if(null!=pNewsList&&pNewsList.size()>0){
                    for(News _News : pNewsList) {
                        String _Name = _News.getTitle();
                       String _Description = _News.getDescription();
                        String _PicSmall = _News.getPicUrl();
                        String _url=_News.getUrl();
                        _CV=toContentValues(_CV,_Name,_Description,_PicSmall,_url);
                        if (isExist(_DB, _Name)) {
                            String _selection = DBData.News_COLUMNS.NAME + "=?";
                            String[] _whereArgs = new String[]{_Name};
                            _result = _DB.update(DBData.News_COLUMNS.TABLE_NAME, _CV, _selection, _whereArgs);
                        } else {
                            _result = _DB.insert(DBData.News_COLUMNS.TABLE_NAME, null, _CV);
                        }
                    }
                }
                _DB.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(_DB!=null) {
                    _DB.endTransaction();
                }
            }
        }
        return _result;
    }
    /** 查询所有news */
    public   List<News> queryAllNews(){
        List<News> _newsList=new ArrayList<>();
        News _News;
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            Cursor _cursor=_DB.query(DBData.News_COLUMNS.TABLE_NAME,null,null,null,null,null,null);
            if(isExist(_cursor)){
                for(_cursor.moveToFirst();!_cursor.isAfterLast();_cursor.moveToNext()){
                    _News=toNews(_cursor);
                    _newsList.add(_News);
                }
            }
            _cursor.close();
        }
        return _newsList;
    }



    /** 封装news */
    public News toNews(Cursor pCursor){
        News _News=new News();
        if(null!=pCursor&&pCursor.getCount()>0) {

            _News.setTitle(pCursor.getString(pCursor.getColumnIndex(DBData.News_COLUMNS.NAME)));
            _News.setDescription(pCursor.getString(pCursor.getColumnIndex(DBData.News_COLUMNS.DESCRIPTION)));
            _News.setPicUrl(pCursor.getString(pCursor.getColumnIndex(DBData.News_COLUMNS.PICSMALL)));
            _News.setUrl(pCursor.getString(pCursor.getColumnIndex(DBData.News_COLUMNS.URL)));
        }
        return _News;
    }

    /** 获得ContentValues */
    private ContentValues toContentValues(ContentValues pCV, String pName,String pDescription,String pPicSmall,String url){
        pCV.clear();
        pCV.put(DBData.News_COLUMNS.NAME, pName);
        pCV.put(DBData.News_COLUMNS.DESCRIPTION, pDescription);
        pCV.put(DBData.News_COLUMNS.PICSMALL, pPicSmall);
        pCV.put(DBData.News_COLUMNS.URL, url);
        return pCV;
    }

    /** 查询结果是否存在 */
    public boolean isExist(Cursor pCursor) {
        if (null == pCursor){
            return false;
        }
        Boolean _IsExist=pCursor.getCount() > 0 ? true : false;
        return _IsExist;
    }
    public boolean isExist(SQLiteDatabase pDB, String pName) {
        Boolean _IsExist=false;
        synchronized (LOCK){
            String _selection= DBData.News_COLUMNS.NAME+"=?";
            String[] _whereArgs=new String[]{pName};
            Cursor _Cursor=pDB.query(DBData.News_COLUMNS.TABLE_NAME,null,_selection,_whereArgs,null,null,null);
            _IsExist =isExist(_Cursor);
            _Cursor.close();
        }
        return _IsExist;
    }
}
