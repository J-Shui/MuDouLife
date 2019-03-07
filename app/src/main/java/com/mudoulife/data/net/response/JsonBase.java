package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/17
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.model
 * project : MuDouLife
 */
public class JsonBase<T> implements Parcelable {
    private int code;
    private String msg;
    private T data;

    protected JsonBase(Parcel in) {
        code = in.readInt();
        msg = in.readString();
    }

    public JsonBase() {
    }

    public static final Creator<JsonBase> CREATOR = new Creator<JsonBase>() {
        @Override
        public JsonBase createFromParcel(Parcel in) {
            return new JsonBase(in);
        }

        @Override
        public JsonBase[] newArray(int size) {
            return new JsonBase[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(msg);
    }

    @Override
    public String toString() {
        return "JsonBase{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
