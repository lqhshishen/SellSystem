package com.sell.liqihao.sellsystem.module.Login.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseActivity;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Login.contract.FindPassContract;
import com.sell.liqihao.sellsystem.module.Login.presenter.FindPassPresenter;
import com.sell.liqihao.sellsystem.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPassActivity extends BaseActivity<FindPassPresenter> implements FindPassContract.view {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.AppBarLayout01)
    AppBarLayout AppBarLayout01;
    @BindView(R.id.findpass_edt_username)
    EditText findpassEdtUsername;
    @BindView(R.id.findPass_edt_password)
    EditText findPassEdtPassword;
    @BindView(R.id.register_edt_confirmpassword)
    EditText registerEdtConfirmpassword;
    @BindView(R.id.register_confirmpassword)
    LinearLayout registerConfirmpassword;
    @BindView(R.id.findPass_sure)
    Button findPassSure;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initToolBar(toolbar,true,"忘记密码页面");
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

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_find_pass;
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
    public void setPresenter(FindPassPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new FindPassPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @OnClick(R.id.findPass_sure)
    public void onViewClicked() {
        judge();
    }

    @Override
    public void onForgetPass(ResultBean resultBean) {
        if (resultBean.getResult().equals("success")) {
            ToastUtils.showShort(this,"修改成功");
            finish();
        } else ToastUtils.showShort(this,"修改失败");
    }

    @Override
    public void judge() {
        String userName = findpassEdtUsername.getText().toString();
        String pass = findPassEdtPassword.getText().toString();
        String comfirmPass = registerEdtConfirmpassword.getText().toString();
        if (TextUtils.isEmpty(userName) | TextUtils.isEmpty(pass) | TextUtils.isEmpty(comfirmPass))
            ToastUtils.showShort(this,"输入为空，请检查之后再确认");
        else if (pass.equals(comfirmPass))
            presenter.forgetPass(userName,pass);
        else if (!pass.equals(comfirmPass))
            ToastUtils.showShort(this,"两次密码输入不正确，请检查");
    }
}
