package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

public class AfterSaleInfo implements Parcelable {
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
    private String name;
    private String spec;
    private int number;
    private String picture;
    private int status;
    private String statusName;
    private boolean canExchangeGoods;
    private boolean showLjcl;
    private String orderNumber;
    private String createTime;
    private boolean showClwc;
    private int orderId;

    public AfterSaleInfo() {
    }

    protected AfterSaleInfo(Parcel in) {
        id = in.readInt();
        orderId = in.readInt();
        name = in.readString();
        spec = in.readString();
        number = in.readInt();
        picture = in.readString();
        status = in.readInt();
        statusName = in.readString();
        canExchangeGoods = in.readByte() != 0;
        showLjcl = in.readByte() != 0;
        orderNumber = in.readString();
        createTime = in.readString();
    }

    public static final Creator<AfterSaleInfo> CREATOR = new Creator<AfterSaleInfo>() {
        @Override
        public AfterSaleInfo createFromParcel(Parcel in) {
            return new AfterSaleInfo(in);
        }

        @Override
        public AfterSaleInfo[] newArray(int size) {
            return new AfterSaleInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(orderId);
        dest.writeString(name);
        dest.writeString(spec);
        dest.writeInt(number);
        dest.writeString(picture);
        dest.writeInt(status);
        dest.writeString(statusName);
        dest.writeByte((byte) (canExchangeGoods ? 1 : 0));
        dest.writeByte((byte) (showLjcl ? 1 : 0));
        dest.writeString(orderNumber);
        dest.writeString(createTime);
    }

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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public boolean isCanExchangeGoods() {
        return canExchangeGoods;
    }

    public void setCanExchangeGoods(boolean canExchangeGoods) {
        this.canExchangeGoods = canExchangeGoods;
    }

    public boolean isShowLjcl() {
        return showLjcl;
    }

    public void setShowLjcl(boolean showLjcl) {
        this.showLjcl = showLjcl;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isShowClwc() {
        return showClwc;
    }

    public void setShowClwc(boolean showClwc) {
        this.showClwc = showClwc;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "AfterSaleInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", spec='" + spec + '\'' +
                ", number=" + number +
                ", picture='" + picture + '\'' +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", canExchangeGoods=" + canExchangeGoods +
                ", showLjcl=" + showLjcl +
                ", orderNumber='" + orderNumber + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
