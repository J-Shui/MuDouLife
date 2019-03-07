package com.mudoulife.data.net;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/14
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net
 * project : MuDouLife
 */
public enum LocalStatus implements Parcelable {
    WAIT,FINISH;


    public static final Creator<LocalStatus> CREATOR = new Creator<LocalStatus>() {
        @Override
        public LocalStatus createFromParcel(Parcel in) {
            return LocalStatus.values()[in.readInt()];
        }

        @Override
        public LocalStatus[] newArray(int size) {
            return new LocalStatus[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
