package com.example.pc.nightreader.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xujiawei on 2016/7/19.
 */
public class News implements Parcelable {


   //标题
    private String title;
    // 内容来源
    private String description;
    //图片地址
    private String picUrl;
    //新闻地址
    private  String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.picUrl);
        dest.writeString(this.url);
    }

    public News() {
    }

    protected News(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.picUrl = in.readString();
        this.url = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
