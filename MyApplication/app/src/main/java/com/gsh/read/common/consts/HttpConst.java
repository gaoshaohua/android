package com.gsh.read.common.consts;

/**
 * http请求相关常量
 */
public class HttpConst {
    private static final String URL_FORMAT_HTTP="http://%s:%s%s";
    //private static final String SERVER_HOST="192.168.1.100";
    private static final String SERVER_HOST="www.baidu.com";
    private static final String SERVER_PORT="";
    private static final String SERVER_PATH="";
    private static String BASE_URL=String.format(URL_FORMAT_HTTP, SERVER_HOST,SERVER_PORT,SERVER_PATH);

    /**
     * 请求地址：登录
     */
    public static String URL_LOGIN=BASE_URL+"/user/login";
    /**
     * 请求地址：获取表册
     */
    public static String URL_QUERY_BOOKFORMS=BASE_URL+"/user/login";
    /**
     * 请求地址：用水户详情
     */
    public static String URL_QUERY_USER_INFO=BASE_URL+"/user/login";
    /**
     * 请求地址：录入抄表信息
     */
    public static String URL_METER_READ=BASE_URL+"/user/login";
    /**
     * 请求地址：抄表记录互换
     */
    public static String URL_EXCHANGE_READ=BASE_URL+"/user/login";
}
