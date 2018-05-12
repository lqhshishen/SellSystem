package com.sell.liqihao.sellsystem.module.MyOrder.presenter;

import android.util.Log;

import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.MyOrder.bean.OrderBean;
import com.sell.liqihao.sellsystem.module.MyOrder.contract.MyOrderContract;
import com.sell.liqihao.sellsystem.module.MyOrder.ui.MyOrderActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyOrderPresenter extends BasePresenter<MyOrderActivity> implements MyOrderContract.presenter{

    private List<OrderBean>data = new ArrayList<>();
    @Override
    public void getOrder() {
        SellSystemApi.getInstance(app.AppContext)
                .getAllOrder()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<OrderBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<OrderBean> orderBeans) {
                        data.clear();
                        data.addAll(orderBeans);
                        sloveData(0);
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

    @Override
    public void finishOrder(int orderId) {
        SellSystemApi.getInstance(app.AppContext)
                .finish(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        baseview.onFinishOrder(resultBean);
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

    public void sloveData(int pos) {
        List<OrderBean> mData = new ArrayList<>();
        for (int i = 0;i < data.size();i ++) {
            if (pos == 0 && data.get(i).getIsfinish()==null ) {
                mData.add(data.get(i));
            } else if(pos == 1 && data.get(i).getIsfinish() != null) {
                mData.add(data.get(i));
            }
        }
        baseview.onGetOrder(mData);
    }
}
