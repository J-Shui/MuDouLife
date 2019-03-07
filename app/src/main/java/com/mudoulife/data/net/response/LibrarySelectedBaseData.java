package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/12
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.response
 * project : MuDouLife
 */
public class LibrarySelectedBaseData extends LibraryBaseData implements Parcelable{
    private int selectedPosition;

    public LibrarySelectedBaseData(){}

    protected LibrarySelectedBaseData(Parcel in) {
        super(in);
        selectedPosition = in.readInt();
    }

    public static final Creator<LibrarySelectedBaseData> CREATOR = new Creator<LibrarySelectedBaseData>() {
        @Override
        public LibrarySelectedBaseData createFromParcel(Parcel in) {
            return new LibrarySelectedBaseData(in);
        }

        @Override
        public LibrarySelectedBaseData[] newArray(int size) {
            return new LibrarySelectedBaseData[size];
        }
    };

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(selectedPosition);
    }

    @Override
    public String toString() {
        return "LibrarySelectedBaseData{" +
                "selectedPosition=" + selectedPosition +
                '}';
    }
}
