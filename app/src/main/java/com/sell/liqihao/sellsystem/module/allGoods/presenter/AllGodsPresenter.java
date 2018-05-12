package com.sell.liqihao.sellsystem.module.allGoods.presenter;

import android.util.Log;

import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.allGoods.contract.AllGodsContract;
import com.sell.liqihao.sellsystem.module.allGoods.ui.AllGodsFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AllGodsPresenter extends BasePresenter<AllGodsFragment> implements AllGodsContract.presenter {

    private List<ProductBean> data = new ArrayList<>();
    int i = 0;
    @Override
    public void getProduct() {
        SellSystemApi.getInstance(app.AppContext)
                .getProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProductBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ProductBean> productBeanS) {
                        data.addAll(productBeanS);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.toString());
                    }

                    @Override
                    public void onComplete() {
                        sloveProduct();
                    }
                });


    }
    private List<String> stringData = new ArrayList<>();
    @Override
    public void sloveProduct() {
        String x = "";
        for (int i = 0;i < data.size();i++) {
                if (!x.equals(data.get(i).getTyoe())){
                    x = data.get(i).getTyoe();
                    stringData.add(x);
                }
        }
        baseview.onGetType(stringData);
    }
    private List<ProductBean> typeData = new ArrayList<>();
    public void getTypeData(String type) {
        typeData.clear();
        for (int i = 0;i < data.size();i ++) {
            if (data.get(i).getTyoe().equals(type))
                typeData.add(data.get(i));
        }
        baseview.onGetProduct(typeData);
    }
}
