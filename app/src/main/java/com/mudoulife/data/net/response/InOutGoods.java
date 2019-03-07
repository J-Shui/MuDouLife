package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  "picture": "/20190124/3545608b-6183-447c-abd3-18fe8be1ac23.jpg,",
 *         "name": "自攻螺丝"
 */
public class InOutGoods implements Parcelable {
    private String picture;
    private String name;

    public InOutGoods() {
    }

    protected InOutGoods(Parcel in) {
        picture = in.readString();
        name = in.readString();
    }

    public static final Creator<InOutGoods> CREATOR = new Creator<InOutGoods>() {
        @Override
        public InOutGoods createFromParcel(Parcel in) {
            return new InOutGoods(in);
        }

        @Override
        public InOutGoods[] newArray(int size) {
            return new InOutGoods[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(picture);
        dest.writeString(name);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InOutGoods{" +
                "picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
