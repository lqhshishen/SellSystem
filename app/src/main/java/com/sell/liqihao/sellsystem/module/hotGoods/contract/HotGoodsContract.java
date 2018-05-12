package com.sell.liqihao.sellsystem.module.hotGoods.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.hotGoods.presenter.HotGoodsPresenter;

import java.util.List;

public interface HotGoodsContract {
    interface view extends BaseView<HotGoodsPresenter> {
        void onGetAllPro(List<ProductBean> data);
    }

    interface presenter{
        void getAllProduct();
    }
}
