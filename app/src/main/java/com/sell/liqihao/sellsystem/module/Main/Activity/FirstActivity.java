package com.sell.liqihao.sellsystem.module.Main.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.module.HomePage.ui.HomePageFragment;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.MyOrder.ui.MyOrderActivity;
import com.sell.liqihao.sellsystem.module.Search.ui.SearchActivity;
import com.sell.liqihao.sellsystem.module.Setting.ui.SettingActivity;
import com.sell.liqihao.sellsystem.module.UserCenter.ui.UserCenterActivity;
import com.sell.liqihao.sellsystem.module.allGoods.ui.AllGodsFragment;
import com.sell.liqihao.sellsystem.module.hotGoods.ui.HotGoodsFragment;
import com.sell.liqihao.sellsystem.module.shoppingCart.ui.ShoppingCartFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FirstActivity extends AppCompatActivity {


    @BindView(R.id.activity_main_rp)
    RadioGroup activityMainRp;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.user_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navi_view)
    NavigationView naviView;

    List<Fragment> fragmentList;
    @BindView(R.id.first_searchEdt)
    EditText firstSearchEdt;
    @BindView(R.id.first_search)
    TextView firstSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        fragmentList = new ArrayList<>();
        ButterKnife.bind(this);
        addFragment();
        initView();

    }


    private void addFragment() {
        if (fragmentList != null) {
            fragmentList.clear();
        }
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new HotGoodsFragment());
        fragmentList.add(new AllGodsFragment());
        fragmentList.add(new ShoppingCartFragment());
    }

    private void showFragment(int showIndex, int hideIndex) {
        Fragment showFragment = fragmentList.get(showIndex);
        Fragment hideFragment = fragmentList.get(hideIndex);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!showFragment.isAdded()) {
            ft.add(R.id.fragment_main, showFragment);
        }
        if (showIndex == hideIndex) {
            ft.show(showFragment).commit();
        } else {
            ft.show(showFragment).hide(hideFragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    LinearLayout bg;
    TextView nickName;
    TextView money;
    TextView tel;
    CircleImageView headerView;
    private int previousIndex;


    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetEvent(ResultBean resultBean) {
        money.setText("余额:" + app.userBean.getMoney());
    }


    @SuppressLint("SetTextI18n")
    private void initView() {

        View headView = naviView.getHeaderView(0);
        bg = headView.findViewById(R.id.bg_color);
//        headerView = headView.findViewById(R.id.;
        nickName = headView.findViewById(R.id.user_nickname);
        money = headView.findViewById(R.id.user_money);
        tel = headView.findViewById(R.id.user_tel);
        nickName.setText("昵称:" + "李琪浩");
        money.setText("余额:" + app.userBean.getMoney());
        Log.e("{money",app.userBean.getMoney());
        tel.setText("电话:" + app.userBean.getTel());
//        Glide.with(this).load(app.user.getImgUrl()).into(headerView);

//        设置toolbar
        setSupportActionBar(mainToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.navbar_more);
        }
//        设置radioButton
        final RadioButton rb = (RadioButton) activityMainRp.getChildAt(0);
        rb.setChecked(true);
        previousIndex = 0;
        showFragment(0, previousIndex);
        activityMainRp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                int index = group.indexOfChild(rb);
                showFragment(index, previousIndex);
                previousIndex = index;
            }
        });

        naviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.user_detail:
                        startActivity(new Intent(getApplicationContext(), UserCenterActivity.class));
                        break;
                    case R.id.user_order_detail:
                        startActivity(new Intent(getApplication(), MyOrderActivity.class));
                        break;
                    case R.id.user_setting:
                        startActivity(new Intent(getApplication(), SettingActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    @OnClick(R.id.first_search)
    public void onViewClicked() {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("msg",firstSearchEdt.getText().toString());
        startActivity(intent);
    }
}
