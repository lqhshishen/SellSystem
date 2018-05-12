package com.sell.liqihao.sellsystem.App;

import android.app.Application;
import android.content.Context;

import com.sell.liqihao.sellsystem.module.Login.bean.UserBean;
import com.sell.liqihao.sellsystem.utils.LogUtils;
import com.sell.liqihao.sellsystem.utils.SharedPreferencesUtil;

/**
 * Created by liqihao on 2017/12/6.
 */

public class app extends Application {
    public static Context AppContext;

    public static UserBean userBean;
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.isShow=true;
        AppContext = getApplicationContext();
        initPrefs();
    }
    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }
}
