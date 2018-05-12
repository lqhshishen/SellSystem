package com.sell.liqihao.sellsystem.module.hotGoods.presenter;

import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.hotGoods.contract.HotGoodsContract;
import com.sell.liqihao.sellsystem.module.hotGoods.ui.HotGoodsFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HotGoodsPresenter extends BasePresenter<HotGoodsFragment> implements HotGoodsContract.presenter {
    @Override
    public void getAllProduct() {
        SellSystemApi.getInstance(app.AppContext)
                .getProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ProductBean> productBeans) {
                        baseview.onGetAllPro(productBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
