package com.example.pc.nightreader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.nightreader.db.base.DBData;
import com.example.pc.nightreader.db.base.DBHelper;
import com.example.pc.nightreader.entity.OtherChannle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class OtherChannleDBHelper {

    /** TAG */
    private final static String TAG = OtherChannleDBHelper.class.getSimpleName();
    /** 创建一个特殊的instance变量来充当锁 */
    private final byte[] LOCK = new byte[0];
    private Context mContext;
    private DBHelper mDBHelper;

    /** 构造 */
    public OtherChannleDBHelper(Context pContext) {
        this.mContext = pContext;
        mDBHelper=DBHelper.getInstance(pContext);
    }
    /** 添加OtherChannle */
    public long insertOtherChannle(List<OtherChannle> pOtherChannleList){
        long _result=0;
        ContentValues _CV = new ContentValues();
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            try {
                _DB.beginTransaction();
                if(null!=pOtherChannleList&&pOtherChannleList.size()>0){
                    for(OtherChannle _OtherChannle : pOtherChannleList) {
                        String _Title = _OtherChannle.getName();
                        String _Url =_OtherChannle.getSource();
                        _CV=toContentValues(_CV,_Title,_Url);
                        if (isExist(_DB, _Title)) {
                            String _selection = DBData.OtherChannle_COLUMNS.NAME + "=?";
                            String[] _whereArgs = new String[]{_Title};
                            _result = _DB.update(DBData.OtherChannle_COLUMNS.TABLE_NAME, _CV, _selection, _whereArgs);
                        } else {
                            _result = _DB.insert(DBData.OtherChannle_COLUMNS.TABLE_NAME, null, _CV);
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
    /** 查询所有OtherChannle */
    public   List<OtherChannle> queryAllOtherChannle(){
        List<OtherChannle> _OtherChannleList=new ArrayList<>();
        OtherChannle _OtherChannle;
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            Cursor _cursor=_DB.query(DBData.OtherChannle_COLUMNS.TABLE_NAME,null,null,null,null,null,null);
            if(isExist(_cursor)){
                for(_cursor.moveToFirst();!_cursor.isAfterLast();_cursor.moveToNext()){
                    _OtherChannle=toOtherChannle(_cursor);
                    _OtherChannleList.add(_OtherChannle);
                }
            }
            _cursor.close();
        }
        return _OtherChannleList;
    }

    /** 封装OtherChannle */
    public OtherChannle toOtherChannle(Cursor pCursor){
        OtherChannle _OtherChannle=new OtherChannle();
        if(null!=pCursor&&pCursor.getCount()>0) {
            _OtherChannle.setName(pCursor.getString(pCursor.getColumnIndex(DBData.OtherChannle_COLUMNS.NAME)));
            _OtherChannle.setSource(pCursor.getString(pCursor.getColumnIndex(DBData.OtherChannle_COLUMNS.SOURCE)));
        }
        return _OtherChannle;
    }

    /** 获得ContentValues */
    private ContentValues toContentValues(ContentValues pCV, String pTitle, String pUrl ){
        pCV.clear();
        pCV.put(DBData.OtherChannle_COLUMNS.NAME, pTitle);
        pCV.put(DBData.OtherChannle_COLUMNS.SOURCE, pUrl);
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
            String _selection= DBData.OtherChannle_COLUMNS.NAME+"=?";
            String[] _whereArgs=new String[]{pTitle};
            Cursor _Cursor=pDB.query(DBData.OtherChannle_COLUMNS.TABLE_NAME,null,_selection,_whereArgs,null,null,null);
            _IsExist =isExist(_Cursor);
            _Cursor.close();
        }
        return _IsExist;
    }
}
