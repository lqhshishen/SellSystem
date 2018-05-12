package com.sell.liqihao.sellsystem.module.HomePage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liqihao on 2017/12/5.
 */

public class PagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> list;
    private List<String> title;
    public PagerAdapter(FragmentManager fm,List<Fragment> list,List<String> title) {
        super(fm);
        this.list = list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
