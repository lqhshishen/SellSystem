package com.sell.liqihao.sellsystem.module.Login.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Login.presenter.RegisterPresenter;

public interface RegisterContract {
    interface view extends BaseView<RegisterPresenter> {
        void onRegister(ResultBean result);

        void judge();
    }
    interface presenter {
        void doRegister(String name,String pass,String tel,String nickName);
    }
}
