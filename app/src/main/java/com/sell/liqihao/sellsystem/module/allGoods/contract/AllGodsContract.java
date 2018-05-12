package com.sell.liqihao.sellsystem.module.allGoods.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.allGoods.presenter.AllGodsPresenter;

import java.util.List;

public interface AllGodsContract {
    interface view extends BaseView<AllGodsPresenter> {
        void onGetProduct(List<ProductBean> data);

        void onGetType(List<String> data);
    }

    interface presenter {
        void getProduct();

        void sloveProduct();
    }
}
