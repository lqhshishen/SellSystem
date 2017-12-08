package com.sell.liqihao.sellsystem.HomePage.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.HomePage.adapter.RecommendAdapter;
import com.sell.liqihao.sellsystem.Util.GetContext;
import com.sell.liqihao.sellsystem.hotGoods.adapter.HotItemAdapter;
import com.sell.liqihao.sellsystem.HomePage.bean.RecommendBean;
import com.sell.liqihao.sellsystem.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/5.
 */

public class RecommendFragment extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommendfragment,container,false);
        recyclerView = view.findViewById(R.id.recomm_list);
        initData();
        return view;
    }
    RecommendAdapter recommendAdapter;
    List<RecommendBean>list = new ArrayList<>();
    RecommendBean recommendBean;
    RecommendBean recommendBean2;


    private void initData() {
        recommendBean = new RecommendBean();
        recommendBean.setImg(R.mipmap.tanhuang_heye);
        recommendBean.setMoney("￥" + "275");
        recommendBean.setName("鸿瀛弹簧合页");
        recommendBean2 = new RecommendBean();
        recommendBean2.setName("鸿瀛三维合页");
        recommendBean2.setMoney("￥" + "73");
        recommendBean2.setImg(R.mipmap.sanweiketiao_heye);
        for(int i = 0;i < 10 ;i++) {
            list.add(recommendBean);
            list.add(recommendBean2);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(GetContext.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recommendAdapter = new RecommendAdapter(list);
        recyclerView.setAdapter(recommendAdapter);}
}
