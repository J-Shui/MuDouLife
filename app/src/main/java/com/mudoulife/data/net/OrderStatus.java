package com.mudoulife.data.net;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/14
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net
 * project : MuDouLife
 */
public enum OrderStatus {
    STATUS1(1,"待处理"),STATUS2(2,"已处理"),STATUS3(3,"待支付"),STATUS4(4,"已支付");
    private int value;
    private String msg;

    OrderStatus() {
    }

    public String getMsg(int value){
        for (OrderStatus status:
             OrderStatus.values()) {
            if (status.getValue() == value){
                return status.msg;
            }
        }
        return null;
    }

    OrderStatus(int value, String msg) {
        this.value = value;
        this.msg = msg;

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "value=" + value +
                ", msg='" + msg + '\'' +
                '}';
    }
}
