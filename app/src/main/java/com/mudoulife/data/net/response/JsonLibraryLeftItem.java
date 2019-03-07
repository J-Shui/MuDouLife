package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author : J-Shui[YJS]
 * DATE : 2019/1/12
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.response
 * project : MuDouLife
 */
public class JsonLibraryLeftItem implements Parcelable {
    private int id;
    private String name;
    private boolean canClick;

    public JsonLibraryLeftItem() {
    }

    protected JsonLibraryLeftItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<JsonLibraryLeftItem> CREATOR = new Creator<JsonLibraryLeftItem>() {
        @Override
        public JsonLibraryLeftItem createFromParcel(Parcel in) {
            return new JsonLibraryLeftItem(in);
        }

        @Override
        public JsonLibraryLeftItem[] newArray(int size) {
            return new JsonLibraryLeftItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    @Override
    public String toString() {
        return "JsonLibraryLeftItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
