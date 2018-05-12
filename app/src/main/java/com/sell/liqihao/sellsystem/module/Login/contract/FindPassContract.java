package com.sell.liqihao.sellsystem.module.Login.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Login.presenter.FindPassPresenter;

public interface FindPassContract {
    interface view extends BaseView<FindPassPresenter> {
        void onForgetPass(ResultBean resultBean);

        void judge();
    }
    interface presenter {
        void forgetPass(String userName,String pass);
    }
}
