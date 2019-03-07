package com.mudoulife.data.net.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2019/1/12
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.response
 * project : MuDouLife
 */
public class JsonContents<T> {
    private ArrayList<T> mDatas;

    public JsonContents() {
        mDatas = new ArrayList<>();
    }

    public void setDatas(ArrayList<T> datas){
        this.mDatas = datas;
    }
    public void addData(T t){
        mDatas.add(t);
    }
    public void remove(T t){
        mDatas.remove(t);
    }
    public List<T> getDatas(){
        return Collections.unmodifiableList(mDatas);
    }

    @Override
    public String toString() {
        return "JsonContents{" +
                "mDatas=" + mDatas +
                '}';
    }
}
