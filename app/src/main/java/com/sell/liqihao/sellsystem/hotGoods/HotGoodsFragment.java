package com.sell.liqihao.sellsystem.hotGoods;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.R;

/**
 * Created by liqihao on 2017/12/4.
 */

public class HotGoodsFragment extends android.support.v4.app.Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hotgoodsfragment, container, false);
        return view;
    }
}
