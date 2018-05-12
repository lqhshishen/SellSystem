package com.sell.liqihao.sellsystem.module.MyOrder.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.MyOrder.bean.OrderBean;
import com.sell.liqihao.sellsystem.module.MyOrder.presenter.MyOrderPresenter;

import java.util.List;

public interface MyOrderContract {
    interface view extends BaseView<MyOrderPresenter> {
        void onGetOrder(List<OrderBean> data);

        void onFinishOrder(ResultBean resultBean);
    }

    interface presenter {
        void getOrder();

        void finishOrder(int orderId);
    }
}
