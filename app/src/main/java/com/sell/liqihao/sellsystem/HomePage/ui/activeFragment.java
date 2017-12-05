package com.sell.liqihao.sellsystem.HomePage.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.R;

/**
 * Created by liqihao on 2017/12/5.
 */

public class activeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activegoodsfragment,container,false);
        return view;
    }
}
