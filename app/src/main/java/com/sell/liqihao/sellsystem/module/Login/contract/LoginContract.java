package com.sell.liqihao.sellsystem.module.Login.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Login.bean.UserBean;
import com.sell.liqihao.sellsystem.module.Login.presenter.LoginPresenter;

public interface LoginContract {
    interface view extends BaseView<LoginPresenter> {
        void onLogin(UserBean userBean);
    }

    interface presenter {
        void doLogin(String name,String pass);
    }
}
