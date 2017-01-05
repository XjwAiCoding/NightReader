package com.example.pc.nightreader.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xujiawei on 2017/1/5.
 */

public class MyChannle implements Parcelable {
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

    public MyChannle() {
    }

    protected MyChannle(Parcel in) {
        this.name = in.readString();
        this.source = in.readString();
    }

    public static final Parcelable.Creator<MyChannle> CREATOR = new Parcelable.Creator<MyChannle>() {
        @Override
        public MyChannle createFromParcel(Parcel source) {
            return new MyChannle(source);
        }

        @Override
        public MyChannle[] newArray(int size) {
            return new MyChannle[size];
        }
    };
}
