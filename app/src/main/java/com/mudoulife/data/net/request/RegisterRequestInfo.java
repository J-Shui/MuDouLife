package com.mudoulife.data.net.request;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/18
 * email : jshui_920124@163.com
 * package_name : com.mudoulife.data.net.request
 * project : MuDouLife
 */
public class RegisterRequestInfo implements Parcelable {
    private String username;
    private String name;
    private String phone;
    private String idCard;
    private String vCode;
    private String password;
    private List<Integer> roleIds;

    public RegisterRequestInfo() {
    }


    protected RegisterRequestInfo(Parcel in) {
        username = in.readString();
        name = in.readString();
        phone = in.readString();
        idCard = in.readString();
        vCode = in.readString();
        password = in.readString();
    }

    public static final Creator<RegisterRequestInfo> CREATOR = new Creator<RegisterRequestInfo>() {
        @Override
        public RegisterRequestInfo createFromParcel(Parcel in) {
            return new RegisterRequestInfo(in);
        }

        @Override
        public RegisterRequestInfo[] newArray(int size) {
            return new RegisterRequestInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "RegisterRequestInfo{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", vCode='" + vCode + '\'' +
                ", password='" + password + '\'' +
                ", roleIds=" + roleIds +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(idCard);
        dest.writeString(vCode);
        dest.writeString(password);
    }
}
