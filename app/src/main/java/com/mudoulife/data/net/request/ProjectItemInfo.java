package com.mudoulife.data.net.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author : J-Shui[YJS]
 * DATE : 2019/1/12
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.request
 * project : MuDouLife
 */
public class ProjectItemInfo implements Parcelable {
    /**
     *       "name": "马桶维修",
     *         "price": 100,
     *         "number": 1
     */
    private int id;
    private int number;
    private String name;
    private double price;

    public ProjectItemInfo() {
    }

    protected ProjectItemInfo(Parcel in) {
        id = in.readInt();
        number = in.readInt();
        name = in.readString();
        price = in.readDouble();
    }

    public static final Creator<ProjectItemInfo> CREATOR = new Creator<ProjectItemInfo>() {
        @Override
        public ProjectItemInfo createFromParcel(Parcel in) {
            return new ProjectItemInfo(in);
        }

        @Override
        public ProjectItemInfo[] newArray(int size) {
            return new ProjectItemInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProjectItemInfo{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(number);
        dest.writeString(name);
        dest.writeDouble(price);
    }
}
