package com.sell.liqihao.sellsystem.module.Setting.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Setting.presenter.SettingPresenter;

public interface SettingContract {
    interface view extends BaseView<SettingPresenter> {
        void showPop();

        void showPassPop();

        void onAddressChange(ResultBean resultBean);

        void onPassChanger(ResultBean resultBean);

    }
    interface presenter {

        void doUpdateAddress(String address);

        void doUpdatePass(String pass);

    }
}
