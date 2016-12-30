package com.example.pc.nightreader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.nightreader.db.base.DBData;
import com.example.pc.nightreader.db.base.DBHelper;
import com.example.pc.nightreader.entity.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class VideoDBHelper {

    /** TAG */
    private final static String TAG = VideoDBHelper.class.getSimpleName();
    /** 创建一个特殊的instance变量来充当锁 */
    private final byte[] LOCK = new byte[0];
    private Context mContext;
    private DBHelper mDBHelper;

    /** 构造 */
    public VideoDBHelper(Context pContext) {
        this.mContext = pContext;
        mDBHelper=DBHelper.getInstance(pContext);
    }
    /** 添加Video */
    public long insertVideo(List<Video> pVideoList){
        long _result=0;
        ContentValues _CV = new ContentValues();
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            try {
                _DB.beginTransaction();
                if(null!=pVideoList&&pVideoList.size()>0){
                    for(Video _Video : pVideoList) {
                        String _Title = _Video.getTitle();
                        String _Url =_Video.getUrl();
                        String _Videosource=_Video.getVideosource();
                        String _PlayCount = _Video.getPlayCount();
                        String _cover=_Video.getCover();
                        _CV=toContentValues(_CV,_Title,_Url,_Videosource,_PlayCount,_cover);
                        if (isExist(_DB, _Title)) {
                            String _selection = DBData.Video_COLUMNS.TITLE + "=?";
                             String[] _whereArgs = new String[]{_Title};
                            _result = _DB.update(DBData.Video_COLUMNS.TABLE_NAME, _CV, _selection, _whereArgs);
                        } else {
                            _result = _DB.insert(DBData.Video_COLUMNS.TABLE_NAME, null, _CV);
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
    /** 查询所有Video */
    public   List<Video> queryAllVideo(){
        List<Video> _VideoList=new ArrayList<>();
        Video _Video;
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            Cursor _cursor=_DB.query(DBData.Video_COLUMNS.TABLE_NAME,null,null,null,null,null,null);
            if(isExist(_cursor)){
                for(_cursor.moveToFirst();!_cursor.isAfterLast();_cursor.moveToNext()){
                    _Video=toVideo(_cursor);
                    _VideoList.add(_Video);
                }
            }
            _cursor.close();
        }
        return _VideoList;
    }

    /** 封装Video */
    public Video toVideo(Cursor pCursor){
        Video _Video=new Video();
        if(null!=pCursor&&pCursor.getCount()>0) {
            _Video.setTitle(pCursor.getString(pCursor.getColumnIndex(DBData.Video_COLUMNS.TITLE)));
            _Video.setUrl(pCursor.getString(pCursor.getColumnIndex(DBData.Video_COLUMNS.URL)));
            _Video.setVideosource(pCursor.getString(pCursor.getColumnIndex(DBData.Video_COLUMNS.VIDEOSOURCE)));
            _Video.setPlayCount(pCursor.getString(pCursor.getColumnIndex(DBData.Video_COLUMNS.PLAYCOUNT)));
            _Video.setCover(pCursor.getString(pCursor.getColumnIndex(DBData.Video_COLUMNS.COVER)));
        }
        return _Video;
    }

    /** 获得ContentValues */
    private ContentValues toContentValues(ContentValues pCV, String pTitle,String pUrl, String pVideosource,String pPlayCount,String pCover ){
        pCV.clear();
        pCV.put(DBData.Video_COLUMNS.TITLE, pTitle);
        pCV.put(DBData.Video_COLUMNS.URL, pUrl);
        pCV.put(DBData.Video_COLUMNS.VIDEOSOURCE, pVideosource);
        pCV.put(DBData.Video_COLUMNS.PLAYCOUNT, pPlayCount);
        pCV.put(DBData.Video_COLUMNS.COVER,pCover);
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
    public boolean isExist(SQLiteDatabase pDB, String pTitle) {
        Boolean _IsExist=false;
        synchronized (LOCK){
            String _selection= DBData.Video_COLUMNS.TITLE+"=?";
            String[] _whereArgs=new String[]{pTitle};
            Cursor _Cursor=pDB.query(DBData.Video_COLUMNS.TABLE_NAME,null,_selection,_whereArgs,null,null,null);
            _IsExist =isExist(_Cursor);
            _Cursor.close();
        }
        return _IsExist;
    }
}
