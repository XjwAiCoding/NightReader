package com.example.pc.nightreader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.nightreader.db.base.DBData;
import com.example.pc.nightreader.db.base.DBHelper;
import com.example.pc.nightreader.entity.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/10/10.
 */
public class PhotoDBHelper {
    /** TAG */
    private final static String TAG = PhotoDBHelper.class.getSimpleName();
    /** 创建一个特殊的instance变量来充当锁 */
    private final byte[] LOCK = new byte[0];
    private Context mContext;
    private DBHelper mDBHelper;

    /** 构造 */
    public PhotoDBHelper(Context pContext) {
        this.mContext = pContext;
        mDBHelper=DBHelper.getInstance(pContext);
    }
    /** 添加Image*/
    public long insertPhoto(List<Photo> pPhotoList){
        long _result=0;
        ContentValues _CV = new ContentValues();
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            try {
                _DB.beginTransaction();
                if(null!=pPhotoList&&pPhotoList.size()>0){
                    for(Photo _Photo : pPhotoList) {
                        String _date = _Photo.getPublicDate();
                        String _URL = _Photo.getUrl();
                        _CV=toContentValues(_CV,_date,_URL);
                        //？？
                        if (isExist(_DB, _date)) {
                            String _selection = DBData.Image_COLUMNS.DESC + "=?";
                            String[] _whereArgs = new String[]{_date};
                            _result = _DB.update(DBData.Image_COLUMNS.TABLE_NAME, _CV, _selection, _whereArgs);
                        } else {
                            _result = _DB.insert(DBData.Image_COLUMNS.TABLE_NAME, null, _CV);
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
    /** 查询所有Image */
    public List<Photo> queryAllPhoto(){
        List<Photo> _PhotoList=new ArrayList<>();
        Photo _Photo;
        synchronized (LOCK){
            SQLiteDatabase _DB;
            _DB=mDBHelper.getReadableDatabase();
            Cursor _cursor=_DB.query(DBData.Image_COLUMNS.TABLE_NAME,null,null,null,null,null,null);
            if(isExist(_cursor)){
                for(_cursor.moveToFirst();!_cursor.isAfterLast();_cursor.moveToNext()){
                    _Photo=toPhoto(_cursor);
                    _PhotoList.add(_Photo);
                }
            }
            _cursor.close();
        }
        return _PhotoList;
    }



    /** 封装image */
    public Photo toPhoto(Cursor pCursor){
        Photo _Photo=new Photo();
        if(null!=pCursor&&pCursor.getCount()>0) {
            _Photo.setPublicDate(pCursor.getString(pCursor.getColumnIndex(DBData.Image_COLUMNS.DESC)));
            _Photo.setUrl(pCursor.getString(pCursor.getColumnIndex(DBData.Image_COLUMNS.URL)));
        }
        return _Photo;
    }

    /** 获得ContentValues */
    private ContentValues toContentValues(ContentValues pCV, String pDESC,String pURL){
        pCV.clear();
        pCV.put(DBData.Image_COLUMNS.DESC, pDESC);
        pCV.put(DBData.Image_COLUMNS.URL, pURL);

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
    public boolean isExist(SQLiteDatabase pDB, String pDESC) {
        Boolean _IsExist=false;
        synchronized (LOCK){
            String _selection= DBData.Image_COLUMNS.DESC+"=?";
            String[] _whereArgs=new String[]{pDESC};
            Cursor _Cursor=pDB.query(DBData.Image_COLUMNS.TABLE_NAME,null,_selection,_whereArgs,null,null,null);
            _IsExist =isExist(_Cursor);
            _Cursor.close();
        }
        return _IsExist;
    }
}
