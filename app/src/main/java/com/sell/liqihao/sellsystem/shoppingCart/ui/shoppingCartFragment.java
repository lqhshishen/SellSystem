package com.sell.liqihao.sellsystem.shoppingCart.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.LogUtil;
import com.sell.liqihao.sellsystem.shoppingCart.adapter.ShoppingCartAdapter;
import com.sell.liqihao.sellsystem.shoppingCart.bean.CartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/4.
 */

public class shoppingCartFragment extends android.support.v4.app.Fragment{
    ShoppingCartAdapter mAdapter;
    RecyclerView recyclerView;
    List<CartBean> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shoppingcartfragment, container, false);
        initData();
        mAdapter = new ShoppingCartAdapter(data);
        LogUtil.e("dsadas",data.get(0).getName());
        recyclerView = view.findViewById(R.id.cart_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        return view;
    }
    private void initData() {
        data = new ArrayList<>();
        CartBean cartBean = new CartBean();
        cartBean.setImg(R.mipmap.tanhuang_heye);
        cartBean.setMoney("263");
        cartBean.setName("鸿瀛弹簧合页");
        cartBean.setSize("3mm");
        cartBean.setNumber("1");
        for(int i = 0; i < 10; i++){
            data.add(cartBean);
        }

    }
}
