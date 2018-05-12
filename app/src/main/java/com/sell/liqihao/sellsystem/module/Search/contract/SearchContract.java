package com.sell.liqihao.sellsystem.module.Search.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.Search.presenter.SearchPresenter;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;

import java.util.List;

public interface SearchContract {
    interface view extends BaseView<SearchPresenter> {
        void onSearch(List<ProductBean> data);
    }
    interface presenter {
        void doSearch(String msg);
    }
}
