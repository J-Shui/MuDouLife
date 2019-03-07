package com.mudoulife.di.module;

import com.mudoulife.data.api.MainApi;
import com.mudoulife.data.net.datasource.MainDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/10
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.module
 * project : MuDouLife
 */
@Module
public class ResultModule {
    @Provides
    MainApi provideResultApi(MainDataSource dataSource){
        return dataSource;
    }
}
