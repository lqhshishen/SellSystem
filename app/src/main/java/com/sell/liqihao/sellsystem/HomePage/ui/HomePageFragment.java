package com.sell.liqihao.sellsystem.HomePage.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.HomePage.adapter.PagerAdapter;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liqihao on 2017/12/4.
 */

public class HomePageFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.fragment_homepage)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    List<android.support.v4.app.Fragment>fragmentList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepagefragment, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    List<String>title;
    @Override
    public void onViewCreated(View view,Bundle saveInstanceState) {
        super.onViewCreated(view,saveInstanceState);
        fragmentList = new ArrayList<>();
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new ActiveFragment());
        fragmentList.add(new DiscountItemFragment());
//        addFragment();
        title = new ArrayList<>();
        title.add("今日推荐");
        title.add("活动商品");
        title.add("折扣商品");
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(),fragmentList,title);
//        LogUtil.e("dsadas", String.valueOf(fragmentList.size()));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


//
//    private void addFragment(){
//
//    }

}
