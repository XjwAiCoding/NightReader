package com.example.pc.nightreader.db.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/9/12.
 */
public class DBHelper extends SQLiteOpenHelper {
    /** TAG */
    private static final String TAG = DBHelper.class.getSimpleName();
    /** NewsDBHelper */
    private volatile static DBHelper mDBHelper = null;

    /**
     * NewsDBHelper 单例模式调用
     *
     * @param pContext Context
     *
     * @return SQLiteOpenHelper
     */
    public static DBHelper getInstance(Context pContext) {
        if (mDBHelper == null) {
            synchronized(DBHelper.class){
                if (mDBHelper == null) {
                    mDBHelper = new DBHelper(pContext.getApplicationContext());
                }
            }
        }
        return mDBHelper;
    }


    private DBHelper(Context pContext) {
        super(pContext, DBData.DB_NAME, null, DBData.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase pDB) {

        //SQL
        pDB.execSQL(DBData.News_COLUMNS.SQL_CREATE_News);
        pDB.execSQL(DBData.Image_COLUMNS.SQL_CREATE_Image);
//      pDB.execSQL(DBData.Video_COLUMNS.SQL_CREATE_VIDEO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase pDB, int pOldVersion, int pNewVersion) {
        for (int i = pOldVersion; i < pNewVersion; i++) {
            List<String> _UpgrateSQLList = getUpgradeSQL(i + 1);
            for (String _UpgrateSQL : _UpgrateSQLList) {
                try {
                    pDB.execSQL(_UpgrateSQL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 各个版本更新数据的SQL语句
     *
     * @param pVersion 版本号
     *
     * @return 各个版本需要更新的SQL语句的集合
     */
    private List<String> getUpgradeSQL(int pVersion) {
        //数据库所做的升级SQL语句
        List<String> _UpgrateSQLList = new ArrayList<String>();
        switch (pVersion) {
            case 2:
                break;
            case 3:
                break;
        }
        return _UpgrateSQLList;
    }
}
