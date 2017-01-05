package com.example.pc.nightreader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.nightreader.db.base.DBData;
import com.example.pc.nightreader.db.base.DBHelper;
import com.example.pc.nightreader.entity.MyChannle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class MyChannleDBHelper {

    /** TAG */
    private final static String TAG = MyChannleDBHelper.class.getSimpleName();
    /** 创建一个特殊的instance变量来充当锁 */
    private final byte[] LOCK = new byte[0];
    private Context mContext;
    private DBHelper mDBHelper;

    /** 构造 */
    public MyChannleDBHelper(Context pContext) {
        this.mContext = pContext;
        mDBHelper=DBHelper.getInstance(pContext);
    }
    /** 添加MyChannle */
    public long insertMyChannle(List<MyChannle> pMyChannleList){
        long _result=0;
        ContentValues _CV = new ContentValues();
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            try {
                _DB.beginTransaction();
                if(null!=pMyChannleList&&pMyChannleList.size()>0){
                    for(MyChannle _MyChannle : pMyChannleList) {
                        String _Title = _MyChannle.getName();
                        String _Url =_MyChannle.getSource();
                        _CV=toContentValues(_CV,_Title,_Url);
                        if (isExist(_DB, _Title)) {
                            String _selection = DBData.MyChannle_COLUMNS.NAME + "=?";
                            String[] _whereArgs = new String[]{_Title};
                            _result = _DB.update(DBData.MyChannle_COLUMNS.TABLE_NAME, _CV, _selection, _whereArgs);
                        } else {
                            _result = _DB.insert(DBData.MyChannle_COLUMNS.TABLE_NAME, null, _CV);
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
    /** 查询所有MyChannle */
    public   List<MyChannle> queryAllMyChannle(){
        List<MyChannle> _MyChannleList=new ArrayList<>();
        MyChannle _MyChannle;
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            Cursor _cursor=_DB.query(DBData.MyChannle_COLUMNS.TABLE_NAME,null,null,null,null,null,null);
            if(isExist(_cursor)){
                for(_cursor.moveToFirst();!_cursor.isAfterLast();_cursor.moveToNext()){
                    _MyChannle=toMyChannle(_cursor);
                    _MyChannleList.add(_MyChannle);
                }
            }
            _cursor.close();
        }
        return _MyChannleList;
    }

    /** 封装MyChannle */
    public MyChannle toMyChannle(Cursor pCursor){
        MyChannle _MyChannle=new MyChannle();
        if(null!=pCursor&&pCursor.getCount()>0) {
            _MyChannle.setName(pCursor.getString(pCursor.getColumnIndex(DBData.MyChannle_COLUMNS.NAME)));
            _MyChannle.setSource(pCursor.getString(pCursor.getColumnIndex(DBData.MyChannle_COLUMNS.SOURCE)));
        }
        return _MyChannle;
    }

    /** 获得ContentValues */
    private ContentValues toContentValues(ContentValues pCV, String pTitle, String pUrl ){
        pCV.clear();
        pCV.put(DBData.MyChannle_COLUMNS.NAME, pTitle);
        pCV.put(DBData.MyChannle_COLUMNS.SOURCE, pUrl);
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
            String _selection= DBData.MyChannle_COLUMNS.NAME+"=?";
            String[] _whereArgs=new String[]{pTitle};
            Cursor _Cursor=pDB.query(DBData.MyChannle_COLUMNS.TABLE_NAME,null,_selection,_whereArgs,null,null,null);
            _IsExist =isExist(_Cursor);
            _Cursor.close();
        }
        return _IsExist;
    }
}
