package com.sell.liqihao.sellsystem.module.shoppingCart.presenter;

import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.shoppingCart.bean.CartBean;
import com.sell.liqihao.sellsystem.module.shoppingCart.contract.ShoppingCartContract;
import com.sell.liqihao.sellsystem.module.shoppingCart.ui.ShoppingCartFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingCartPresenter extends BasePresenter<ShoppingCartFragment> implements ShoppingCartContract.presenter {
    @Override
    public void getCart(int id) {
        SellSystemApi.getInstance(app.AppContext)
                .getCartById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CartBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        baseview.onShowLoading();
                    }

                    @Override
                    public void onNext(List<CartBean> cartBeans) {
                        baseview.onGetCart(cartBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        baseview.onHideLoading();
                    }
                });
    }

    @Override
    public void deleteCart(int cartid) {
        SellSystemApi.getInstance(app.AppContext)
                .deleteCart(cartid)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        baseview.onShowLoading();
                    }
                    @Override
                    public void onNext(ResultBean resultBean) {
                        baseview.onDeleteCart(resultBean);
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getCart(app.userBean.getUserId());
                    }
                });
    }

    public void buy(int orderid, String note, String time, String total) {
        SellSystemApi.getInstance(app.AppContext)
                .buy(orderid,note,total,time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        baseview.onShowLoading();
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
                        getCart(app.userBean.getUserId());
                    }
                });
    }
}
