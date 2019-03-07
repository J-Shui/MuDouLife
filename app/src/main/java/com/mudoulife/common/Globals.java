package com.mudoulife.common;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/5
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.common
 * project : MuDouLife
 */
public class Globals {
    //designed screen resolution
    //phone landscape
    public static final float DESIGNED_SCREEN_LAND_WIDTH = 2208.0f;
    //    public static final float DESIGNED_SCREEN_LAND_WIDTH = 2048.0f;
    //phone portrait
    public static final float DESIGNED_SCREEN_PORT_WIDTH = 1242.0f;
    //    public static final float DESIGNED_SCREEN_PORT_WIDTH = 1152.0f;
    //tablet
    public static final float DESIGNED_TABLET_SCREEN_WIDTH = 2048.0f;
    //tv
    public static final float DESIGNED_TV_SCREEN_WIDTH = 1920.f;

    //screen info
    public static int gScreenWidth = 0;
    public static int gScreenHeight = 0;
    //    public static int gWindowDisplayHeight = 0;
    public static float gScreebDensity = 0;
    public static float gScreenScale = 0;

    public static final String APP_FOLDER = "mudou/";
    public static final String APP_CRASH_FOLDER = APP_FOLDER + "crash/";
    public static final String APP_CRASH_FILE_TEMP_NAME = "/modou_crash_";

    public static final int TOP_ORDER_ITEM = 1;
    public static final int TOP_HELP_ITEM = TOP_ORDER_ITEM + 1;
    public static final int TOP_MINE_ITEM = TOP_HELP_ITEM + 1;
    public static final int LEFT_ORDER_ITEM = 11;
    public static final int LEFT_PRICE_ITEM = LEFT_ORDER_ITEM + 11;
    public static final int LEFT_ACCOUNT_ITEM = LEFT_PRICE_ITEM + 11;


    public static final String FORM_MAIN_FRAGMENT = "form_main_fragment";


    public static final int LIBRARY_CARRY_TYPE = 1;
    public static final int LIBRARY_WORKER_SERVICE_TYPE = 2;
    public static final int LIBRARY_WORKER_MATERIAL_TYPE = 3;
    public static final int LIBRARY_EXPRESS_TYPE = 4;
    public static final int LIBRARY_SUBSCRIPTION_TYPE = 5;


    public static final int ROLE_WORKER_ID = 1;
    public static final int ROLE_SHANG_HU_ID = 2;
    public static final int ROLE_SUBSCRIPTION_ID = 4;
    public static final int ROLE_EXPRESS_ID = 7;
    public static final int ROLE_CARRY_ID = 3;

    public static final String ROLE_WORKER_NAME = "craftsman";
    public static final String ROLE_SHANG_HU_NAME = "store";
    public static final String ROLE_SUBSCRIPTION_NAME = "subscription";
    public static final String ROLE_EXPRESS_NAME = "express";
    public static final String ROLE_CARRY_NAME = "carry";


    public static final String ORDER_ID = "order_id";

    public static final int ORDER_CARRY_TYPE = 1;
    public static final int ORDER_WORKER_TYPE = 2;
    public static final int ORDER_EXPRESS_TYPE = 3;
    public static final int ORDER_SUBSCRIPTION_TYPE = 4;
    public static final int ORDER_SHANGHU_TYPE = 5;

    public static final int ORDER_QUERY_TYPE = 6;
    public static final int ORDER_PRICE_TYPE = 7;
    public static final int ORDER_FINISH_TYPE = 8;
    public static final int ORDER_AFTER_SALE_TYPE = 9;
    public static final int ORDER_MINE_QUERY_TYPE = 10;

    /**
     * UN_PAY(1, "未支付"),
     * ALREADY_PAY(2, "已支付"),
     * STORE_RECEIPT(3, "商户已接单"),
     * CS_DISPATCH_EXPRESS(4, "已指定速递"),
     * DELIVERY_RECEIPT(5, "速递已接单"),
     * STORE_DELIVER(6, "商户已交付"),
     * EXPRESS_PICK(7, "速递已取货"),
     * EXPRESS_UPLOAD_PRICE(8, "速递已上传报价"),
     * USER_CONFIRM_EXPRESS_PRICE(9, "用户已确认速递报价"),
     * CS_DISPATCH_CARRY(10, "已指定搬运"),
     * USER_CHECK_EXPRESS(11, "搬运已接单"),
     * CARRY_UPLOAD_PRICE(12, "搬运已上传报价"),
     * CARRY_END_SERVICE(13, "用户已确认搬运报价"),
     * EXPRESS_DELIVER(14,"速递已交付"),
     * CARRY_START_SERVICE(15, "搬运开始服务"),
     * CARRY_END_SERVICE(16, "搬运结束服务"),
     * USER_CHECK_CARRY(17,"用户已验收搬运"),
     * COMPLETE(19, "订单完成"),
     * CANCEL(20, "订单已取消");
     */
    public static final int UN_PAY = 1;
    public static final int ALREADY_PAY = 2;
    public static final int STORE_RECEIPT = 3;
    public static final int APPOINT_DELIVERY = 4;
    public static final int DELIVERY_RECEIPT = 5;
    public static final int STORE_DELIVER = 6;
    public static final int EXPRESS_PICK = 7;
    public static final int EXPRESS_DELIVER = 8;
    public static final int EXPRESS_UPLOAD_PRICE = 9;
    public static final int USER_AGREE_EXPRESS_PRICE = 10;
    public static final int USER_CHECK_EXPRESS = 11;
    public static final int CARRY_START_SERVICE = 12;
    public static final int CARRY_END_SERVICE = 13;
    public static final int CARRY_UPLOAD_PRICE = 14;
    public static final int USER_AGREE_CARRY_PRICE = 15;
    public static final int COMPLETE = 17;
    public static final int CANCEL = 18;


    public static final int SERVICE_UN_PAY = 1;
    public static final int SERVICE_ALREADY_PAY = 2;
    public static final int CS_DISPATCH_CRAFTSMAN = 3;
    public static final int CRAFTSMAN_RECEIPT = 4;
    public static final int CRAFTSMAN_UPLOAD_PRICE = 5;
    public static final int USER_CONFIRM_CRAFTSMAN_PRICE = 6;
    public static final int CRAFTSMAN_START_SERVICE = 7;
    public static final int CRAFTSMAN_END_SERVICE = 8;
    public static final int USER_CHECK_CRAFTSMAN = 9;
    public static final int CRAFTSMAN_COMPLETE = 10;
    public static final int CRAFTSMAN_CANCEL = 11;

    public static final int AFTER_SALE_1_STATUS = 1;
    public static final int AFTER_SALE_2_STATUS = 2;
    public static final int AFTER_SALE_3_STATUS = 3;
    public static final int AFTER_SALE_4_STATUS = 4;

    public static final String BASE_URL = "https://mdapi.vertxjava.com";

    public static final String ROLES_DATA = "roles_data";

}
