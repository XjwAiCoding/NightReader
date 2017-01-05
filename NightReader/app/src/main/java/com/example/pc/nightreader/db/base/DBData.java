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
       //新闻来源
        public static final String URL="url";
        //新闻类别
        public static final String position="position";

        /** 创建SQL表 */
        public static final String SQL_CREATE_News="CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME+" VARCHAR,"
                +DESCRIPTION+" INTEGER,"
                +position+" INTEGER,"
                +URL+" VARCHAR,"
                +PICSMALL+" VARCHAR)";

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
                +DESC+" VARCHAR,"
                +URL+" VARCHAR)";

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
        /** 封面 */
        public static final String COVER="cover";

        /** 创建SQL表 */
        public static final String SQL_CREATE_VIDEO="CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE+" VARCHAR,"
                +URL+" VARCHAR,"
                +VIDEOSOURCE+" VARCHAR,"
                +COVER+" VARCHAR,"
                +PLAYCOUNT+" VARCHAR)" ;


    }

    /**MyChannle表 */
    public static final class MyChannle_COLUMNS{
        /**  表名 */
        public static final String TABLE_NAME="MyChannle";
        /**  ID */
        public static final String ID="_id";
        /**  名字*/
        public static final String NAME="name";
        /**  接口url源 */
        public static final String SOURCE="source";

        /** 创建SQL表 */
        public static final String SQL_CREATE_MYCHANNLE="CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME+" VARCHAR,"
                +SOURCE+" VARCHAR,";
    }


    /**OtherChannle表 */
    public static final class OtherChannle_COLUMNS{
        /**  表名 */
        public static final String TABLE_NAME="OtherChannle";
        /**  ID */
        public static final String ID="_id";
        /**  名字*/
        public static final String NAME="name";
        /**  接口url源 */
        public static final String SOURCE="source";

        /** 创建SQL表 */
        public static final String SQL_CREATE_MYCHANNLE="CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME+" VARCHAR,"
                +SOURCE+" VARCHAR,";
    }

    /* ====== SQL END =======*/

}
