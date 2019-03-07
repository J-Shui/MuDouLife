package com.mudoulife.data.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JsonBudgetDetails implements Parcelable {
    /**
     * "orderNumber": "G20190124194010001",
     *     "goods": [
     *       {
     *         "picture": "/20190124/3545608b-6183-447c-abd3-18fe8be1ac23.jpg,",
     *         "name": "自攻螺丝"
     *       },
     *       {
     *         "picture": "/20190124/11c9e882-ad57-4bd5-af71-9de33ffbcf7d.jpg,",
     *         "name": "娃哈哈"
     *       }
     *     ],
     *     "income": 0
     */
    private String orderNumber;
    private List<InOutGoods> goods;
    private double  income;

    public JsonBudgetDetails() {
    }

    protected JsonBudgetDetails(Parcel in) {
        orderNumber = in.readString();
        goods = in.createTypedArrayList(InOutGoods.CREATOR);
        income = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderNumber);
        dest.writeTypedList(goods);
        dest.writeDouble(income);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JsonBudgetDetails> CREATOR = new Creator<JsonBudgetDetails>() {
        @Override
        public JsonBudgetDetails createFromParcel(Parcel in) {
            return new JsonBudgetDetails(in);
        }

        @Override
        public JsonBudgetDetails[] newArray(int size) {
            return new JsonBudgetDetails[size];
        }
    };

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<InOutGoods> getGoods() {
        return goods;
    }

    public void setGoods(List<InOutGoods> goods) {
        this.goods = goods;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "JsonBudgetDetails{" +
                "orderNumber='" + orderNumber + '\'' +
                ", goods=" + goods +
                '}';
    }
}
