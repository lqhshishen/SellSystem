package com.sell.liqihao.sellsystem.module.Login.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseActivity;
import com.sell.liqihao.sellsystem.module.Login.bean.UserBean;
import com.sell.liqihao.sellsystem.module.Login.contract.LoginContract;
import com.sell.liqihao.sellsystem.module.Login.presenter.LoginPresenter;
import com.sell.liqihao.sellsystem.module.Main.Activity.FirstActivity;
import com.sell.liqihao.sellsystem.module.Util.ProgressDia;
import com.sell.liqihao.sellsystem.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.view {

    Activity mContext;
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    @BindView(R.id.login_btn_register)
    Button loginBtnRegister;
    @BindView(R.id.login_findPass)
    TextView loginFindPass;
    @BindView(R.id.login_loading)
    ProgressBar loginLoading;


    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void getData() {
//        ProgressDia.pp(this).show();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onShowLoading() {
        loginLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoading() {
        loginLoading.setVisibility(View.GONE);
    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new LoginPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @OnClick({R.id.login_btn_login, R.id.login_btn_register, R.id.login_findPass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn_login:
                presenter.doLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.login_btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_findPass:
                startActivity(new Intent(this, FindPassActivity.class));
                break;
        }
    }

    @Override
    public void onLogin(UserBean userBean) {
        if (userBean.getResult().equals("success")) {
            Log.e("userBean", userBean.getMoney());
            app.userBean = userBean;
            startActivity(new Intent(this, FirstActivity.class));
            finish();
        } else ToastUtils.showShort(this, "密码账号错误");
    }


    @OnClick(R.id.login_findPass)
    public void onViewClicked() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
