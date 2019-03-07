package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

public class JsonOrderGoods implements Parcelable {
    /**
     * "id": 58,
     * 			"goodsName": "螺丝刀",
     * 			"goodsSpec": "小",
     * 			"goodsPrice": 0.01,
     * 			"number": 1,
     * 			"picture": "/20190123/0aa826a3-9c5e-4339-bdd3-1696631aa25c.jpg,",
     * 			"canExchangeGoods": false,
     * 			"showLjcl": false
     */
    private int id;
    private String goodsName;
    private String goodsSpec;
    private int number;
    private String picture;
    private boolean canExchangeGoods;
    private int status;
    private double goodsPrice;
    private boolean showLjcl;
    private boolean showClwc;

    public JsonOrderGoods() {
    }

    protected JsonOrderGoods(Parcel in) {
        goodsName = in.readString();
        goodsSpec = in.readString();
        number = in.readInt();
        picture = in.readString();
        canExchangeGoods = in.readByte() != 0;
        showLjcl = in.readByte() != 0;
        showClwc = in.readByte() != 0;
        status = in.readInt();
        goodsPrice = in.readDouble();
    }

    public static final Creator<JsonOrderGoods> CREATOR = new Creator<JsonOrderGoods>() {
        @Override
        public JsonOrderGoods createFromParcel(Parcel in) {
            return new JsonOrderGoods(in);
        }

        @Override
        public JsonOrderGoods[] newArray(int size) {
            return new JsonOrderGoods[size];
        }
    };

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isCanExchangeGoods() {
        return canExchangeGoods;
    }

    public void setCanExchangeGoods(boolean canExchangeGoods) {
        this.canExchangeGoods = canExchangeGoods;
    }

    public double getPrice() {
        return goodsPrice;
    }

    public void setPrice(double price) {
        this.goodsPrice = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean isShowLjcl() {
        return showLjcl;
    }

    public void setShowLjcl(boolean showLjcl) {
        this.showLjcl = showLjcl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isShowClwc() {
        return showClwc;
    }

    public void setShowClwc(boolean showClwc) {
        this.showClwc = showClwc;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(goodsName);
        dest.writeString(goodsSpec);
        dest.writeInt(number);
        dest.writeString(picture);
        dest.writeByte((byte) (canExchangeGoods ? 1 : 0));
        dest.writeByte((byte) (showLjcl ? 1 : 0));
        dest.writeByte((byte) (showClwc ? 1 : 0));
        dest.writeInt(status);
        dest.writeDouble(goodsPrice);
    }

    @Override
    public String toString() {
        return "JsonOrderGoods{" +
                "goodsName='" + goodsName + '\'' +
                ", goodsSpec='" + goodsSpec + '\'' +
                ", number=" + number +
                ", picture='" + picture + '\'' +
                ", canExchangeGoods=" + canExchangeGoods +
                ", status=" + status +
                ", price=" + goodsPrice +
                '}';
    }
}
