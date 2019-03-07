package com.mudoulife.eventbus;

/**
 * Author : J-Shui[YJS]
 * DATE : 2019/1/13
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.eventbus
 * project : MuDouLife
 */
public class MessageEvent {
    String fragmentName;
    int topItem;
    int leftItem;

    public MessageEvent(String fragmentName, int topItem, int leftItem) {
        this.fragmentName = fragmentName;
        this.topItem = topItem;
        this.leftItem = leftItem;
    }

    public int getTopItem() {
        return topItem;
    }

    public void setTopItem(int topItem) {
        this.topItem = topItem;
    }

    public int getLeftItem() {
        return leftItem;
    }

    public void setLeftItem(int leftItem) {
        this.leftItem = leftItem;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }
}
