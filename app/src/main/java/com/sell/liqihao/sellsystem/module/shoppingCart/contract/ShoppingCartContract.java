package com.sell.liqihao.sellsystem.module.shoppingCart.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.shoppingCart.bean.CartBean;
import com.sell.liqihao.sellsystem.module.shoppingCart.presenter.ShoppingCartPresenter;
import com.sell.liqihao.sellsystem.module.shoppingDetail.contract.ShoppingDetailContract;

import java.util.List;

public interface ShoppingCartContract {
    interface view extends BaseView<ShoppingCartPresenter> {
        void onGetCart(List<CartBean> data);

        void onDeleteCart(ResultBean resultBean);

        void onBuy(ResultBean resultBean);
    }

    interface presenter {
        void getCart(int id);

        void deleteCart(int cartid);

        void buy(int orderid,String note,String time,String total);
    }

}
