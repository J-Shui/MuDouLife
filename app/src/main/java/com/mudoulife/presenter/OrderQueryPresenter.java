package com.mudoulife.presenter;

import com.mudoulife.data.api.MainApi;

import javax.inject.Inject;

/**
 * Author : J-Shui[YJS]
 * DATE : 2019/1/8
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.presenter
 * project : MuDouLife
 */
public class OrderQueryPresenter extends OrderBasePresenter {

    @Inject
    public OrderQueryPresenter(MainApi api) {
        super(api);
    }
}
