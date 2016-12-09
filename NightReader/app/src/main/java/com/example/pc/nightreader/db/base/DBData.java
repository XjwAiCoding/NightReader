package com.example.pc.nightreader.db.base;

/**
 * Created by xujiawei  on 2016/9/12.
 */
public class DBData {

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
        public static final String TABLE_NAME="Photo";
        /**  ID */
        public static final String ID="_id";
        /**  时间 */
        public static final String DESC="desc";

        /**  图地址 */
        public static final String URL="url";
        /** 创建SQL表 */
        public static final String SQL_CREATE_Image="CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +DESC+" VARCHAR NOT NULL UNIQUE,"
                +URL+" VARCHAR NOT NULL)";

    }

    /**video表 */
    public static final class Video_COLUMNS{
        /**  表名 */
        public static final String TABLE_NAME="Video";
        /**  ID */
        public static final String ID="_id";
        /**  标题*/
        public static final String TITLE="title";
        /**  视频缩略图 */
        public static final String COVERIMG="coverimg";
        /**  视频地址 */
        public static final String URL="url";
        /**  视频来源 */
        public static final String VIDEOSOURCE="videosource";
        /** 播放次数 */
        public static final String PLAYCOUNT="playcount";

        /** 创建SQL表 */
        public static final String SQL_CREATE_VIDEO="CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE+" VARCHAR NOT NULL UNIQUE,"
                +COVERIMG+" VARCHAR NOT NULL)"
                +URL+" VARCHAR NOT NULL)"
                +VIDEOSOURCE+" VARCHAR NOT NULL)"
                +PLAYCOUNT+" VARCHAR NOT NULL)" ;

    }


    /* ====== SQL END =======*/

}
