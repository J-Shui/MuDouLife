package com.mudoulife.di.module;

import com.mudoulife.data.api.LibraryDataApi;
import com.mudoulife.data.net.datasource.LibraryDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/11
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.module
 * project : MuDouLife
 */
@Module
public class LibraryModule {
    @Provides
    LibraryDataApi provideLibraryApi(LibraryDataSource dataSource){
        return dataSource;
    }
}
