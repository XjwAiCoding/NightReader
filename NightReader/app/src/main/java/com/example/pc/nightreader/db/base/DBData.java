package com.example.pc.nightreader.db.base;

/**
 * Created by yuzhenbei on 2016/9/12.
 */
public class DBData {
    /* 数据库 */
    /** SDK 本地数据库名 */
    public static final String DB_NAME = "News_db";
    /** SDK 本地数据库的版本号,用于数据升级 */
    public static final int DB_VERSION = 1;

    /* ====== SQL BEGIN =======*/

    /** News表 */
    public static final class News_COLUMNS{
        /**  表名 */
        public static final String TABLE_NAME="News";
        /**  ID */
        public static final String ID="_id";
        /** 标题 */
        public static final String NAME="name";
        /**  内容 */
        public static final String DESCRIPTION="description";
        /**  小图地址 */
        public static final String PICSMALL="picSmall";
        /** 创建SQL表 */
        public static final String SQL_CREATE_News="CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME+" VARCHAR NOT NULL UNIQUE,"
                +DESCRIPTION+" INTEGER NOT NULL,"
                +PICSMALL+" VARCHAR NOT NULL)";

    }

    /**Image表 */
    public static final class Image_COLUMNS{
        /**  表名 */
        public static final String TABLE_NAME="Image";
        /**  ID */
        public static final String ID="_id";
        /**  标题 */
        public static final String NAME="name";

        /**  大图地址 */
        public static final String PICBIG="picBig";
        /** 创建SQL表 */
        public static final String SQL_CREATE_Image="CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME+" VARCHAR NOT NULL UNIQUE,"
                +PICBIG+" VARCHAR NOT NULL)";

    }
    /* ====== SQL END =======*/

}
