package com.sell.liqihao.sellsystem.module.shoppingDetail.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.shoppingDetail.presenter.ShoppingDetailPresenter;

public interface ShoppingDetailContract {
    interface view extends BaseView<ShoppingDetailPresenter> {
        void onAddToCart(ResultBean resultBean);

        void onBuy(ResultBean resultBean);
    }

    interface presenter {
        void addToCart(int productId,int userId,int number);

        void buy(int orderId,String note,String total,String time);
    }
}
