package com.sell.liqihao.sellsystem.hotGoods.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.GetContext;
import com.sell.liqihao.sellsystem.HomePage.adapter.RecommendAdapter;
import com.sell.liqihao.sellsystem.hotGoods.adapter.HotItemAdapter;
import com.sell.liqihao.sellsystem.hotGoods.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/4.
 */

public class HotGoodsFragment extends android.support.v4.app.Fragment{
    RecyclerView recyclerView;
    HotItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hotgoodsfragment, container, false);
        recyclerView = view.findViewById(R.id.hot_item);
        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(GetContext.getContext()));
        adapter = new HotItemAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;
    }

    List<ItemBean> list = new ArrayList<>();
    void initData () {
        ItemBean itemBean = new ItemBean();
        ItemBean itemBean1 = new ItemBean();
        itemBean.setImg(R.mipmap.sanweiketiao_heye);
        itemBean.setMoney("￥73");
        itemBean.setName("三维可调合页");

        itemBean1.setName("弹簧合页");
        itemBean1.setMoney("255");
        itemBean1.setImg(R.mipmap.tanhuang_heye);
        for(int i=0;i<10;i++) {
            list.add(itemBean);
            list.add(itemBean1);
        }
    }
}
