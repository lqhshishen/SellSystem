package com.sell.liqihao.sellsystem.module.UserCenter.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseActivity;
import com.sell.liqihao.sellsystem.module.UserCenter.contract.UserCenterContract;
import com.sell.liqihao.sellsystem.module.UserCenter.presenter.UserCenterPresenter;
import com.sell.liqihao.sellsystem.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserCenterActivity extends BaseActivity<UserCenterPresenter> implements UserCenterContract.view {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.AppBarLayout01)
    AppBarLayout AppBarLayout01;
    @BindView(R.id.usercenter_user)
    TextView usercenterUser;
    @BindView(R.id.usercenter_nickName)
    TextView usercenterNickName;
    @BindView(R.id.usercenter_tel)
    TextView usercenterTel;
    @BindView(R.id.usercenter_money)
    TextView usercenterMoney;
    @BindView(R.id.usercenter_chongzhi)
    Button usercenterChongzhi;
    @BindView(R.id.usercenter_address)
    TextView usercenterAddress;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initToolBar(toolbar, true, "个人中心");
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.navbar_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getData() {
        usercenterUser.setText(app.userBean.getUser());
        usercenterNickName.setText("李琪浩");
        usercenterTel.setText(app.userBean.getTel());
        usercenterMoney.setText("余额:" + app.userBean.getMoney());
        usercenterAddress.setText(app.userBean.getAddress());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_user_center;
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(UserCenterPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new UserCenterPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @OnClick(R.id.usercenter_chongzhi)
    public void onViewClicked() {
        ToastUtils.showShort(this,"因政策原因，暂不开放充值，请联系管理员");
    }
}
