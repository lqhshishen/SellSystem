package com.sell.liqihao.sellsystem.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liqihao on 2017/12/4.
 */

public class ToastUtil {
    public void Toast(String msg) {
        Toast.makeText(GetContext.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
