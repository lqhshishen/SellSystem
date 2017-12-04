package com.sell.liqihao.sellsystem.Main.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.R;

/**
 * Created by liqihao on 2017/12/4.
 */

public class shoppingCartFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shoppingcartfragment, container, false);
        return view;
    }
}
