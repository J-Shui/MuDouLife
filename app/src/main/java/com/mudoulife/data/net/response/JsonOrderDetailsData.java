package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.mudoulife.data.net.request.ProjectItemInfo;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2019/1/12
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.response
 * project : MuDouLife
 */
public class JsonOrderDetailsData implements Parcelable {
    private int orderId;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String makeTime;
    private String remark;
    private String serviceName;
    private String goodsName;
    private String goodsSpec;
    private int price;
    private int number;
    private int id;
    private List<ProjectItemInfo> jsfwList;
    private List<ProjectItemInfo> pjList;
    private List<JsonOrderGoods> orderGoods;
    private String reason;



    public JsonOrderDetailsData() {
    }


    protected JsonOrderDetailsData(Parcel in) {
        orderId = in.readInt();
        receiverName = in.readString();
        receiverPhone = in.readString();
        receiverAddress = in.readString();
        makeTime = in.readString();
        remark = in.readString();
        serviceName = in.readString();
        goodsName = in.readString();
        goodsSpec = in.readString();
        price = in.readInt();
        number = in.readInt();
        id = in.readInt();
        jsfwList = in.createTypedArrayList(ProjectItemInfo.CREATOR);
        pjList = in.createTypedArrayList(ProjectItemInfo.CREATOR);
        orderGoods = in.createTypedArrayList(JsonOrderGoods.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(orderId);
        dest.writeString(receiverName);
        dest.writeString(receiverPhone);
        dest.writeString(receiverAddress);
        dest.writeString(makeTime);
        dest.writeString(remark);
        dest.writeString(serviceName);
        dest.writeString(goodsName);
        dest.writeString(goodsSpec);
        dest.writeInt(price);
        dest.writeInt(number);
        dest.writeInt(id);
        dest.writeTypedList(jsfwList);
        dest.writeTypedList(pjList);
        dest.writeTypedList(orderGoods);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JsonOrderDetailsData> CREATOR = new Creator<JsonOrderDetailsData>() {
        @Override
        public JsonOrderDetailsData createFromParcel(Parcel in) {
            return new JsonOrderDetailsData(in);
        }

        @Override
        public JsonOrderDetailsData[] newArray(int size) {
            return new JsonOrderDetailsData[size];
        }
    };

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<ProjectItemInfo> getJsfwList() {
        return jsfwList;
    }

    public void setJsfwList(List<ProjectItemInfo> jsfwList) {
        this.jsfwList = jsfwList;
    }

    public List<ProjectItemInfo> getPjList() {
        return pjList;
    }

    public void setPjList(List<ProjectItemInfo> pjList) {
        this.pjList = pjList;
    }

    public List<JsonOrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<JsonOrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "JsonOrderDetailsData{" +
                "orderId=" + orderId +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", makeTime='" + makeTime + '\'' +
                ", remark='" + remark + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSpec='" + goodsSpec + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", jsfwList=" + jsfwList +
                ", pjList=" + pjList +
                ", orderGoods=" + orderGoods +
                ", id=" + id +
                ", reason=" + reason +
                '}';
    }
}
