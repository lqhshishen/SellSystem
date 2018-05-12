package com.sell.liqihao.sellsystem.module.Util;

import android.app.Application;
import android.content.Context;

/**
 * Created by liqihao on 2017/12/4.
 */

public class GetContext extends Application{
    static Context  mContent;
    @Override
    public void onCreate() {
        super.onCreate();
        mContent = getApplicationContext();
    }

    public static Context getContext() {
        return mContent;
    }

}
