package com.example.pc.nightreader.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class OtherChannle implements Parcelable {
    String name;
    String source;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.source);
    }

    public OtherChannle() {
    }

    protected OtherChannle(Parcel in) {
        this.name = in.readString();
        this.source = in.readString();
    }

    public static final Parcelable.Creator<OtherChannle> CREATOR = new Parcelable.Creator<OtherChannle>() {
        @Override
        public OtherChannle createFromParcel(Parcel source) {
            return new OtherChannle(source);
        }

        @Override
        public OtherChannle[] newArray(int size) {
            return new OtherChannle[size];
        }
    };
}
