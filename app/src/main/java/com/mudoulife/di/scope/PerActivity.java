package com.mudoulife.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/4
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.di.scope
 * project : MuDouLife
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
