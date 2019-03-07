package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.response
 * project : MuDouLife
 */
public class LibraryBaseData implements Parcelable {
    private String name;
    private double price;
    private int number;
    private int floor;
    private boolean isSelected;
    private double total;
    private int id;
    private int classId;
    private String createTime;
    private String className;
    private int type;
    private double startPrice;
    private double exceedingPrice;
    private String priceUnit;
    private String unit;

    public LibraryBaseData() {
    }


    protected LibraryBaseData(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        number = in.readInt();
        floor = in.readInt();
        isSelected = in.readByte() != 0;
        total = in.readDouble();
        id = in.readInt();
        classId = in.readInt();
        type = in.readInt();
        startPrice = in.readDouble();
        exceedingPrice = in.readDouble();
        createTime = in.readString();
        className = in.readString();
        priceUnit = in.readString();
        unit = in.readString();
    }

    public static final Creator<LibraryBaseData> CREATOR = new Creator<LibraryBaseData>() {
        @Override
        public LibraryBaseData createFromParcel(Parcel in) {
            return new LibraryBaseData(in);
        }

        @Override
        public LibraryBaseData[] newArray(int size) {
            return new LibraryBaseData[size];
        }
    };

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {

        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getExceedingPrice() {
        return exceedingPrice;
    }

    public void setExceedingPrice(double exceedingPrice) {
        this.exceedingPrice = exceedingPrice;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "LibraryBaseData{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", floor=" + floor +
                ", isSelected=" + isSelected +
                ", total=" + total +
                ", id=" + id +
                ", classId=" + classId +
                ", type=" + type +
                ", startPrice=" + startPrice +
                ", exceedingPrice=" + exceedingPrice +
                ", className=" + className +
                ", createTime=" + createTime +
                ", priceUnit=" + priceUnit +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(number);
        dest.writeInt(floor);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeDouble(total);
        dest.writeInt(id);
        dest.writeInt(classId);
        dest.writeInt(type);
        dest.writeDouble(startPrice);
        dest.writeDouble(exceedingPrice);
        dest.writeString(createTime);
        dest.writeString(className);
        dest.writeString(priceUnit);
        dest.writeString(unit);
    }
}
