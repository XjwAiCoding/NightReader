package com.example.pc.nightreader.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class Photo implements Parcelable {
    String publicDate;
    String url;

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
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
        dest.writeString(this.publicDate);
        dest.writeString(this.url);
    }

    public Photo() {
    }

    protected Photo(Parcel in) {
        this.publicDate = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
