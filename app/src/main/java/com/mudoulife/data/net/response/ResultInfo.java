package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.mudoulife.data.net.LocalStatus;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/6
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.response
 * project : MuDouLife
 */
public class ResultInfo implements Parcelable {
    private int id;
    private String orderNumber;
    private String serviceName;
    private String standard;
    private int number;
    private String createTime;
    private String picture;
    private boolean isReceived;
    private int status;
    private String statusName;
    private String spec;
    private String goodsName;
    private List<JsonOrderGoods> orderGoods;
    private double total;
    private int expressReceipt;
    private int carryReceipt;
    private String unit;
    private boolean showLjcl;
    private boolean showClwc;

    public ResultInfo(){}


    protected ResultInfo(Parcel in) {
        id = in.readInt();
        orderNumber = in.readString();
        serviceName = in.readString();
        standard = in.readString();
        number = in.readInt();
        createTime = in.readString();
        picture = in.readString();
        isReceived = in.readByte() != 0;
        status = in.readInt();
        statusName = in.readString();
        spec = in.readString();
        goodsName = in.readString();
        orderGoods = in.createTypedArrayList(JsonOrderGoods.CREATOR);
        total = in.readDouble();
        expressReceipt = in.readInt();
        carryReceipt = in.readInt();
        unit = in.readString();
    }

    public static final Creator<ResultInfo> CREATOR = new Creator<ResultInfo>() {
        @Override
        public ResultInfo createFromParcel(Parcel in) {
            return new ResultInfo(in);
        }

        @Override
        public ResultInfo[] newArray(int size) {
            return new ResultInfo[size];
        }
    };

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImgUrl() {
        return picture;
    }

    public void setImgUrl(String imgUrl) {
        this.picture = imgUrl;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public List<JsonOrderGoods> getOrderGoods() {
        return orderGoods;
    }
    public void setOrderGoods(List<JsonOrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getExpressReceipt() {
        return expressReceipt;
    }

    public void setExpressReceipt(int expressReceipt) {
        this.expressReceipt = expressReceipt;
    }

    public int getCarryReceipt() {
        return carryReceipt;
    }

    public void setCarryReceipt(int carryReceipt) {
        this.carryReceipt = carryReceipt;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isShowLjcl() {
        return showLjcl;
    }

    public void setShowLjcl(boolean showLjcl) {
        this.showLjcl = showLjcl;
    }

    public boolean isShowClwc() {
        return showClwc;
    }

    public void setShowClwc(boolean showClwc) {
        this.showClwc = showClwc;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", standard='" + standard + '\'' +
                ", number=" + number +
                ", createTime='" + createTime + '\'' +
                ", picture='" + picture + '\'' +
                ", isReceived=" + isReceived +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", spec='" + spec + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", orderGoods=" + orderGoods +
                ", total=" + total +
                ", total=" + expressReceipt +
                ", total=" + carryReceipt +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(orderNumber);
        dest.writeString(serviceName);
        dest.writeString(standard);
        dest.writeInt(number);
        dest.writeString(createTime);
        dest.writeString(picture);
        dest.writeByte((byte) (isReceived ? 1 : 0));
        dest.writeInt(status);
        dest.writeString(statusName);
        dest.writeString(spec);
        dest.writeString(goodsName);
        dest.writeTypedList(orderGoods);
        dest.writeDouble(total);
        dest.writeInt(expressReceipt);
        dest.writeInt(carryReceipt);
        dest.writeString(unit);
    }
}
