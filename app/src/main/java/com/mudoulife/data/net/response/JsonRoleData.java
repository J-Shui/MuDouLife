package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

public class JsonRoleData implements Parcelable{
    private int id;
    private String name;

    protected JsonRoleData(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<JsonRoleData> CREATOR = new Creator<JsonRoleData>() {
        @Override
        public JsonRoleData createFromParcel(Parcel in) {
            return new JsonRoleData(in);
        }

        @Override
        public JsonRoleData[] newArray(int size) {
            return new JsonRoleData[size];
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
        }

}
