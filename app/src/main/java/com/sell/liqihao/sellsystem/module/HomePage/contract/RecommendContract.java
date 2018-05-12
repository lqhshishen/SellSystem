package com.sell.liqihao.sellsystem.module.HomePage.contract;

import com.sell.liqihao.sellsystem.base.BaseView;
import com.sell.liqihao.sellsystem.module.HomePage.bean.RecommendBean;
import com.sell.liqihao.sellsystem.module.HomePage.presenter.RecommendPresenter;

import java.util.List;

public interface RecommendContract {
    interface view extends BaseView<RecommendPresenter> {
        void onGetAllRecomm(List<RecommendBean> data);
    }
    interface presenter {
        void getAllRecomm();
    }
}
