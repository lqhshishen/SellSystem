package com.sell.liqihao.sellsystem.module.Util;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.PopupWindow;

import com.sell.liqihao.sellsystem.R;

public class PopUtil extends PopupWindow {

    public static PopupWindow pop(View view) {
        PopupWindow pop = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT
        ,ActionBar.LayoutParams.WRAP_CONTENT,true);
        pop.setTouchable(true);
        pop.setOutsideTouchable(true);
        pop.setAnimationStyle(R.style.pop_anim);
        pop.update();
        return pop;
    }
}
