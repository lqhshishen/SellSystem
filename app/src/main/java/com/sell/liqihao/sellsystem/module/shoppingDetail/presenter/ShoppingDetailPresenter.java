package com.sell.liqihao.sellsystem.module.shoppingDetail.presenter;

import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.shoppingDetail.contract.ShoppingDetailContract;
import com.sell.liqihao.sellsystem.module.shoppingDetail.ui.ShoppingDetail;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingDetailPresenter extends BasePresenter<ShoppingDetail> implements ShoppingDetailContract.presenter {
    @Override
    public void addToCart(int productId,int userId,int number) {
        SellSystemApi.getInstance(app.AppContext)
                .addToCart(productId, userId, number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        baseview.onAddToCart(resultBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void buy(int orderId, String note, String total, String time) {
        SellSystemApi.getInstance(app.AppContext)
                .buy(orderId,note,total,time)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        baseview.onBuy(resultBean);
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
