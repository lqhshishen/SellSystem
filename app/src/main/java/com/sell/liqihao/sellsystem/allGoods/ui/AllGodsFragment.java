package com.sell.liqihao.sellsystem.allGoods.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.GetContext;
import com.sell.liqihao.sellsystem.allGoods.Adapter.AllItemAdapter;
import com.sell.liqihao.sellsystem.allGoods.Adapter.AllItemTypeAdapter;
import com.sell.liqihao.sellsystem.allGoods.bean.AllItemBean;
import com.sell.liqihao.sellsystem.allGoods.bean.AllTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/4.
 */

public class AllGodsFragment extends android.support.v4.app.Fragment{
    RecyclerView itemRecyclerView;
    RecyclerView typeRecyclerView;
    AllItemAdapter itemAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.allgoodsfragment, container, false);
        itemRecyclerView = view.findViewById(R.id.all_item_recycle);
        typeRecyclerView = view.findViewById(R.id.all_type_recycle);
        initData();
        allItemTypeAdapter = new AllItemTypeAdapter(typeBeanList);
        typeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        typeRecyclerView.setAdapter(allItemTypeAdapter);
        itemAdapter = new AllItemAdapter(itemBeanList);
        GridLayoutManager layoutManager = new GridLayoutManager(GetContext.getContext(),2);
        itemRecyclerView.setLayoutManager(layoutManager);
        itemRecyclerView.setAdapter(itemAdapter);
        return view;
    }

    AllItemTypeAdapter allItemTypeAdapter;
    List<AllItemBean> itemBeanList;
    List<AllTypeBean> typeBeanList;
    private void initData() {
        itemBeanList = new ArrayList<>();
        typeBeanList = new ArrayList<>();
        AllTypeBean typeBean1 = new AllTypeBean();
        AllTypeBean typeBean2 = new AllTypeBean();
        AllTypeBean typeBean3 = new AllTypeBean();
        AllTypeBean typeBean4 = new AllTypeBean();
        AllTypeBean typeBean5 = new AllTypeBean();
        typeBean1.setType("合页");
        typeBean2.setType("螺丝");
        typeBean3.setType("门把手");
        typeBean4.setType("万向轮");
        typeBean5.setType("封边条");
        typeBeanList.add(typeBean1);
        typeBeanList.add(typeBean2);
        typeBeanList.add(typeBean3);
        typeBeanList.add(typeBean4);
        typeBeanList.add(typeBean5);

        AllItemBean itemBean1 = new AllItemBean();
        AllItemBean itemBean2 = new AllItemBean();
        itemBean1.setImg(R.mipmap.tanhuang_heye);
        itemBean1.setMoney("￥" + "275");
        itemBean1.setName("鸿瀛弹簧合页");
        itemBean2.setName("鸿瀛三维合页");
        itemBean2.setMoney("￥" + "73");
        itemBean2.setImg(R.mipmap.sanweiketiao_heye);

        for(int i = 0; i< 10 ; i ++) {
            itemBeanList.add(itemBean1);
            itemBeanList.add(itemBean2);
        }
    }
}
