package com.mudoulife.ui.adapter;

import android.os.Parcel;
import android.os.Parcelable;

import com.mudoulife.ui.fragment.main.CarryFragment;
import com.mudoulife.ui.fragment.main.ExpressFragment;
import com.mudoulife.ui.fragment.main.ShanghuFragment;
import com.mudoulife.ui.fragment.main.SubscriptionFragment;
import com.mudoulife.ui.fragment.main.WorkerFragment;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/17
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.ui.adapter
 * project : MuDouLife
 */
public class AdapterInfo implements Parcelable {
    private String status;
    private boolean isShowLeftBtn;
    private String leftString;
    private String rightString;
    private String fragmentName;
    private boolean has4Item;
    private boolean isFirstShowLeft;

    public AdapterInfo() {
    }

    public AdapterInfo(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    protected AdapterInfo(Parcel in) {
        status = in.readString();
        isShowLeftBtn = in.readByte() != 0;
        leftString = in.readString();
        rightString = in.readString();
        fragmentName = in.readString();
        has4Item = in.readByte() != 0;
        isFirstShowLeft = in.readByte() != 0;
    }

    public static final Creator<AdapterInfo> CREATOR = new Creator<AdapterInfo>() {
        @Override
        public AdapterInfo createFromParcel(Parcel in) {
            return new AdapterInfo(in);
        }

        @Override
        public AdapterInfo[] newArray(int size) {
            return new AdapterInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isShowLeftBtn() {
        return isShowLeftBtn;
    }

    public void setShowLeftBtn(boolean showLeftBtn) {
        isShowLeftBtn = showLeftBtn;
    }

    public String getLeftString() {
        return leftString;
    }

    public void setLeftString(String leftString) {
        this.leftString = leftString;
    }

    public String getRightString() {
        return rightString;
    }

    public void setRightString(String rightString) {
        this.rightString = rightString;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public boolean isHas4Item() {
        return has4Item;
    }

    public void setHas4Item(boolean has4Item) {
        this.has4Item = has4Item;
    }

    public boolean isFirstShowLeft() {
        return isFirstShowLeft;
    }

    public void setFirstShowLeft(boolean firstShowLeft) {
        isFirstShowLeft = firstShowLeft;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeByte((byte) (isShowLeftBtn ? 1 : 0));
        dest.writeString(leftString);
        dest.writeString(rightString);
        dest.writeString(fragmentName);
        dest.writeByte((byte) (has4Item ? 1 : 0));
        dest.writeByte((byte) (isFirstShowLeft ? 1 : 0));
    }

    @Override
    public String toString() {
        return "AdapterInfo{" +
                "status='" + status + '\'' +
                ", isShowLeftBtn=" + isShowLeftBtn +
                ", leftString='" + leftString + '\'' +
                ", rightString='" + rightString + '\'' +
                ", fragmentName='" + fragmentName + '\'' +
                ", has4Item=" + has4Item +
                ", isFirstShowLeft=" + isFirstShowLeft +
                '}';
    }

}
