package com.sell.liqihao.sellsystem.Main.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sell.liqihao.sellsystem.Main.Fragment.AllGodsFragment;
import com.sell.liqihao.sellsystem.Main.Fragment.HomePageFragment;
import com.sell.liqihao.sellsystem.Main.Fragment.HotGoodsFragment;
import com.sell.liqihao.sellsystem.Main.Fragment.shoppingCartFragment;
import com.sell.liqihao.sellsystem.R;

import java.util.List;

import butterknife.ButterKnife;

public class FirstActivity extends AppCompatActivity {
    List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
        addFragment();
    }

    private void addFragment() {
        fragmentList.clear();
        fragmentList.add(new AllGodsFragment());
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new HotGoodsFragment());
        fragmentList.add(new shoppingCartFragment());
    }

    private void showFragment(int showIndex,int hideIndex) {
        Fragment showFragment  = fragmentList.get(showIndex);
        Fragment hideIndent = fragmentList.get(hideIndex);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!showFragment.isAdded()) {
            ft.add(R.id.Hom)
        }
    }
}
