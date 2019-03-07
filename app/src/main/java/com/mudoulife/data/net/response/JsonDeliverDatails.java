package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.mudoulife.data.net.request.ProjectItemInfo;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2019/1/14
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.response
 * project : MuDouLife
 */
public class JsonDeliverDatails implements Parcelable {
    /**
     *  "receiverName": "zhaozhen222",
     *     "receiverPhone": "18535365201",
     *     "receiverAddress": "西城11天安门北京 西城 天安门东",
     *     "makeTime": "2019-01-01 22:30",
     *     "remark": "备注",
     *     "goodsName": "不锈钢字母合页",
     *     "goodsSpec": "803固装",
     *     "price": 10,
     *     "number": 4,
     *     "storeName": "盛丰五金建材店铺",
     *     "storeAddress": "通州区建材城二层54号",
     *     "storePhone": "18612862000",
     *     "expressName": "张三",
     *     "expressPhone": "18612882711"
     */
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String makeTime;
    private String remark;
    private String goodsName;
    private String goodsSpec;
    private String storeName;
    private String storeAddress;
    private String storePhone;
    private String expressName;
    private String expressPhone;
    private double price;
    private int number;
    private List<JsonOrderGoods> orderGoods;
    private double total;

    public JsonDeliverDatails() {
    }

    protected JsonDeliverDatails(Parcel in) {
        receiverName = in.readString();
        receiverPhone = in.readString();
        receiverAddress = in.readString();
        makeTime = in.readString();
        remark = in.readString();
        goodsName = in.readString();
        goodsSpec = in.readString();
        storeName = in.readString();
        storeAddress = in.readString();
        storePhone = in.readString();
        expressName = in.readString();
        expressPhone = in.readString();
        price = in.readDouble();
        number = in.readInt();
        total = in.readDouble();
    }

    public static final Creator<JsonDeliverDatails> CREATOR = new Creator<JsonDeliverDatails>() {
        @Override
        public JsonDeliverDatails createFromParcel(Parcel in) {
            return new JsonDeliverDatails(in);
        }

        @Override
        public JsonDeliverDatails[] newArray(int size) {
            return new JsonDeliverDatails[size];
        }
    };

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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressPhone() {
        return expressPhone;
    }

    public void setExpressPhone(String expressPhone) {
        this.expressPhone = expressPhone;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public String   toString() {
        return "JsonDeliverDatails{" +
                "receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", makeTime='" + makeTime + '\'' +
                ", remark='" + remark + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSpec='" + goodsSpec + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", storePhone='" + storePhone + '\'' +
                ", expressName='" + expressName + '\'' +
                ", expressPhone='" + expressPhone + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", orderGoods=" + orderGoods +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(receiverName);
        dest.writeString(receiverPhone);
        dest.writeString(receiverAddress);
        dest.writeString(makeTime);
        dest.writeString(remark);
        dest.writeString(goodsName);
        dest.writeString(goodsSpec);
        dest.writeString(storeName);
        dest.writeString(storeAddress);
        dest.writeString(storePhone);
        dest.writeString(expressName);
        dest.writeString(expressPhone);
        dest.writeDouble(price);
        dest.writeInt(number);
        dest.writeDouble(total);
    }
}
