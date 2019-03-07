package com.mudoulife.data.net.response;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/18
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.response
 * project : MuDouLife
 */
public class LoginData {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "token='" + token + '\'' +
                '}';
    }
}
