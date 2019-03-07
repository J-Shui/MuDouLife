package com.mudoulife.di.component;

import com.mudoulife.di.module.ActivityModule;
import com.mudoulife.di.module.ResultModule;
import com.mudoulife.di.scope.PerActivity;
import com.mudoulife.ui.fragment.aftersale.AfterSaleDetailsFragment;
import com.mudoulife.ui.fragment.aftersale.AfterSaleHandleFragment;
import com.mudoulife.ui.fragment.mine.MineInOutFragment;
import com.mudoulife.ui.fragment.mine.MineInfoFragment;
import com.mudoulife.ui.fragment.mine.MineQueryFragment;
import com.mudoulife.ui.fragment.order.OrderFinishFragment;
import com.mudoulife.ui.fragment.order.OrderPriceFragment;
import com.mudoulife.ui.fragment.order.OrderQueryFragment;

import dagger.Component;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/10
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.component
 * project : MuDouLife
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class,ResultModule.class})
public interface MainComponent extends ActivityComponent{
    void inject(OrderQueryFragment fragment);
    void inject(OrderPriceFragment fragment);
    void inject(OrderFinishFragment fragment);
    void inject(AfterSaleHandleFragment fragment);
    void inject(MineInOutFragment fragment);
    void inject(MineQueryFragment fragment);
    void inject(MineInfoFragment fragment);
    void inject(AfterSaleDetailsFragment fragment);

}
