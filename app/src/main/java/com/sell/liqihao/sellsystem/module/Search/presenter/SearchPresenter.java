package com.sell.liqihao.sellsystem.module.Search.presenter;

import android.util.Log;

import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.Search.contract.SearchContract;
import com.sell.liqihao.sellsystem.module.Search.ui.SearchActivity;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchActivity> implements SearchContract.presenter{


    @Override
    public void doSearch(String msg) {
        SellSystemApi.getInstance(app.AppContext)
                .search(msg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ProductBean> productBeans) {
                        baseview.onSearch(productBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
