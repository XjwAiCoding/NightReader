package com.example.pc.nightreader.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class Video implements Parcelable {
    String  title;//标题
    String   coverImg;//缩略图
    String  playCount;//播放次数
    String url;//视频地址
   String  videosource;//视频来源

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideosource() {
        return videosource;
    }

    public void setVideosource(String videosource) {
        this.videosource = videosource;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.coverImg);
        dest.writeString(this.playCount);
        dest.writeString(this.url);
        dest.writeString(this.videosource);
    }

    public Video() {
    }

    protected Video(Parcel in) {
        this.title = in.readString();
        this.coverImg = in.readString();
        this.playCount = in.readString();
        this.url = in.readString();
        this.videosource = in.readString();
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
