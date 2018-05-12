package com.sell.liqihao.sellsystem.module.Util;

import android.app.ProgressDialog;
import android.content.Context;

import com.sell.liqihao.sellsystem.App.app;

public class ProgressDia {
    public static ProgressDialog pp(Context context) {
        ProgressDialog a = new ProgressDialog(context);
//        a.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        a.setCancelable(true);
        a.setCanceledOnTouchOutside(true);
        return a;
    }



}
