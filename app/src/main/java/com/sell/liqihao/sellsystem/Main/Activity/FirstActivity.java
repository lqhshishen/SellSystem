package com.sell.liqihao.sellsystem.Main.Activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.User.bean.UserBean;
import com.sell.liqihao.sellsystem.allGoods.ui.AllGodsFragment;
import com.sell.liqihao.sellsystem.HomePage.ui.HomePageFragment;
import com.sell.liqihao.sellsystem.hotGoods.ui.HotGoodsFragment;
import com.sell.liqihao.sellsystem.shoppingCart.ui.shoppingCartFragment;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    List<android.support.v4.app.Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        fragmentList = new ArrayList<>();
        ButterKnife.bind(this);
        initUser();
        addFragment();
        initView();
    }

    UserBean user;
    private void initUser() {
        user = new UserBean();
        user.setImgUrl(R.mipmap.headpic);
        user.setMoney("1024");
        user.setTel("13218021383");
        user.setNickname("MirageLe");
        app.user = user;
    }

    private void addFragment() {
        if (fragmentList != null) {
            fragmentList.clear();
        }
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new HotGoodsFragment());
        fragmentList.add(new AllGodsFragment());
        fragmentList.add(new shoppingCartFragment());
    }

    private void showFragment(int showIndex,int hideIndex) {
        android.support.v4.app.Fragment showFragment  = fragmentList.get(showIndex);
        android.support.v4.app.Fragment hideFragment = fragmentList.get(hideIndex);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!showFragment.isAdded()) {
            ft.add(R.id.fragment_main,showFragment);
        }
        if (showIndex == hideIndex) {
            ft.show(showFragment).commit();
        } else {
            ft.show(showFragment).hide(hideFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.shoucang:
                Toast.makeText(this, "你点击了收藏按钮", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    RelativeLayout bg;
    TextView nickName;
    TextView money;
    TextView tel;
    CircleImageView headerView;
    private int previousIndex;

    private void initView() {
        View headView = naviView.getHeaderView(0);
        bg = headView.findViewById(R.id.bg_color);
        headerView = headView.findViewById(R.id.head_view);
        nickName = headView.findViewById(R.id.user_nickname);
        money = headView.findViewById(R.id.user_money);
        tel = headView.findViewById(R.id.user_tel);
        nickName.setText("昵称:" + app.user.getNickname());
        money.setText("余额:" + app.user.getMoney());
        tel.setText("电话:" + app.user.getTel());
        Glide.with(this).load(app.user.getImgUrl()).into(headerView);

//        设置toolbar
        setSupportActionBar(mainToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.content);
        }
//        设置radioButton
        final RadioButton rb = (RadioButton) activityMainRp.getChildAt(0);
        rb.setChecked(true);
        previousIndex = 0;
        showFragment(0,previousIndex);
        activityMainRp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                LogUtil.e("qwer", String.valueOf(checkedId));
                int index = group.indexOfChild(rb);
                showFragment(index,previousIndex);
                previousIndex = index;
            }
        });
    }
}
