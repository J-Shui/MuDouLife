package com.mudoulife.data.net.response;

import java.util.List;

public class JsonAfterDetails {
    /**
     * {
     *   "code": 0,
     *   "data": {
     *     "receiverName": "赵震",
     *     "receiverPhone": "18535365201",
     *     "receiverAddress": "北京市东城区北京市东城天安门1号",
     *     "makeTime": "2019-01-27 00:40",
     *     "remark": "XP一组DIY",
     *     "orderGoods": [
     *       {
     *         "id": 86,
     *         "goodsName": "自攻螺丝",
     *         "goodsSpec": "自攻螺丝",
     *         "goodsPrice": 0.01,
     *         "number": 2,
     *         "picture": "/20190124/3545608b-6183-447c-abd3-18fe8be1ac23.jpg,",
     *         "canExchangeGoods": false,
     *         "canReturnGoods": false,
     *         "hasUntreatedAftersale": false
     *       }
     *     ],
     *     "storeName": "金水的店铺",
     *     "storeAddress": "松榆东里37号楼梦想加空间",
     *     "storePhone": "18612862012",
     *     "expressName": "金水的速递",
     *     "expressPhone": "18612862012",
     *     "reason": "质保期内出现问题",
     *     "detail": "你要温柔我累着我"
     *   },
     *   "msg": "成功"
     * }
     */
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String makeTime;
    private String remark;
    private String storeName;
    private String storeAddress;
    private String storePhone;
    private String expressName;
    private String expressPhone;
    private String reason;
    private String detail;
    private List<JsonOrderGoods> orderGoods;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<JsonOrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<JsonOrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public String getExpressPhone() {
        return expressPhone;
    }

    public void setExpressPhone(String expressPhone) {
        this.expressPhone = expressPhone;
    }

    @Override
    public String toString() {
        return "JsonAfterDetails{" +
                "receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", makeTime='" + makeTime + '\'' +
                ", remark='" + remark + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", storePhone='" + storePhone + '\'' +
                ", expressName='" + expressName + '\'' +
                ", reason='" + reason + '\'' +
                ", detail='" + detail + '\'' +
                ", orderGoods=" + orderGoods +
                '}';
    }
}
