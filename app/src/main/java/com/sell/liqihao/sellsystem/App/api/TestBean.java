package com.sell.liqihao.sellsystem.App.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liqihao on 2018/1/10.
 */

public class TestBean {
    @SerializedName("user")
    private String username;
    @SerializedName("pass")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
