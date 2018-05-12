package com.sell.liqihao.sellsystem.module.Setting.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseActivity;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Login.ui.LoginActivity;
import com.sell.liqihao.sellsystem.module.Setting.contract.SettingContract;
import com.sell.liqihao.sellsystem.module.Setting.presenter.SettingPresenter;
import com.sell.liqihao.sellsystem.module.Util.PopUtil;
import com.sell.liqihao.sellsystem.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.view {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.AppBarLayout01)
    AppBarLayout AppBarLayout01;
    @BindView(R.id.updateAddress)
    LinearLayout updateAddress;
    @BindView(R.id.updatePass)
    LinearLayout updatePass;
    @BindView(R.id.setting_signOut)
    Button settingSignOut;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initToolBar(toolbar,true,"设置");
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.navbar_back);
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
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
    public void setPresenter(SettingPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new SettingPresenter();
    }

    @Override
    public void onShowNoMore() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.updateAddress, R.id.updatePass, R.id.setting_signOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updateAddress:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("您之前的地址为" + app.userBean.getAddress() + "是否确定更改？")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showPop();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                dialog.show();
                break;
            case R.id.updatePass:
                showPassPop();
                break;
            case R.id.setting_signOut:
                app.userBean = null;
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    EditText popEdt;
    Button popBtn;
    PopupWindow popup;
    @Override
    public void showPop() {
        if (popUp!= null)
            popUp.dismiss();
        View pop = getLayoutInflater().inflate(R.layout.pop_show,null);
        popEdt = pop.findViewById(R.id.pop_msg);
        popBtn = pop.findViewById(R.id.pop_confirm);
        popEdt.setHint("请输入新地址");
        popup = PopUtil.pop(pop);
        popup.update();
        popup.showAtLocation(updateAddress,Gravity.CENTER,0,0);
        popBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(popEdt.getText().toString()))
                    ToastUtils.showShort(getApplicationContext(),"地址为空，请重新输入");
                else presenter.doUpdateAddress(popEdt.getText().toString());
            }
        });
    }

    PopupWindow popUp;
    EditText oldText;
    EditText newText;
    Button confirm;
    @Override
    public void showPassPop() {
        if (popup!= null)
            popup.dismiss();
        View pop = getLayoutInflater().inflate(R.layout.pop_updatepass,null);
        oldText = pop.findViewById(R.id.pop_update_pass);
        newText = pop.findViewById(R.id.pop_update_newpass);
        confirm = pop.findViewById(R.id.pop_update_confirm);
        oldText.setHint("请输入现在的密码");
        newText.setHint("请输入新密码");
        popUp = PopUtil.pop(pop);
        popUp.update();
        popUp.showAtLocation(settingSignOut,Gravity.CENTER,0,0);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oldText.getText().toString().equals(app.userBean.getPass())){
                    if (TextUtils.isEmpty(newText.getText().toString()))
                        ToastUtils.showShort(getApplicationContext(), "新密码为空，请重新输入");
                    else presenter.doUpdatePass(newText.getText().toString());
                } else {
                    ToastUtils.showShort(getApplicationContext(),"密码验证错误，请重新输入");
                }
            }
        });
    }

    @Override
    public void onAddressChange(ResultBean resultBean) {
        app.userBean.setAddress(resultBean.getResult());
        ToastUtils.showShort(this,"修改成功");
        popup.dismiss();
    }

    @Override
    public void onPassChanger(ResultBean resultBean) {
        app.userBean.setPass(resultBean.getResult());
        ToastUtils.showShort(this,"修改成功");
        popUp.dismiss();
    }
}
