package com.mudoulife.di.module;

import com.mudoulife.data.api.AccountApi;
import com.mudoulife.data.net.datasource.AccountDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/17
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.module
 * project : MuDouLife
 */
@Module
public class AccountModule {
    @Provides
    AccountApi provideAccountApi(AccountDataSource dataSource){
        return dataSource;
    }
}
