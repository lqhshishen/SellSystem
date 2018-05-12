package com.sell.liqihao.sellsystem.module.Login.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseActivity;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Login.contract.RegisterContract;
import com.sell.liqihao.sellsystem.module.Login.presenter.RegisterPresenter;
import com.sell.liqihao.sellsystem.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.view {


    @BindView(R.id.register_edt_username)
    EditText registerEdtUsername;
    @BindView(R.id.register_edt_password)
    EditText registerEdtPassword;
    @BindView(R.id.register_edt_confirmpassword)
    EditText registerEdtConfirmpassword;
    @BindView(R.id.register_edt_nickName)
    EditText registerEdtNickName;
    @BindView(R.id.register_edt_tel)
    EditText registerEdtTel;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.loading)
    ProgressBar loading;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShowLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(RegisterPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new RegisterPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void onRegister(ResultBean result) {
        if (result.getResult().equals("success")) {
            ToastUtils.showShort(this, "注册成功");
            finish();
        } else ToastUtils.showShort(this, "用户名已存在");


    }

    @Override
    public void judge() {
        if (TextUtils.isEmpty(registerEdtUsername.getText()) || TextUtils.isEmpty(registerEdtPassword.getText())
                || TextUtils.isEmpty(registerEdtConfirmpassword.getText()) || TextUtils.isEmpty(registerEdtNickName.getText())
                || TextUtils.isEmpty(registerEdtTel.getText())) {
            ToastUtils.showShort(this, "请输入完整的信息");
        } else if (!registerEdtConfirmpassword.getText().toString().
                equals(registerEdtPassword.getText().toString()))
            ToastUtils.showShort(this, "两次密码输入不同");
        else
            presenter.doRegister(registerEdtUsername.getText().toString(), registerEdtPassword.getText().toString()
                    , registerEdtTel.getText().toString(), registerEdtNickName.getText().toString());

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        judge();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
