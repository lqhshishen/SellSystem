package com.sell.liqihao.sellsystem.module.HomePage.presenter;

import android.util.Log;

import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.HomePage.bean.RecommendBean;
import com.sell.liqihao.sellsystem.module.HomePage.contract.RecommendContract;
import com.sell.liqihao.sellsystem.module.HomePage.ui.RecommendFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecommendPresenter extends BasePresenter<RecommendFragment> implements RecommendContract.presenter {


    @Override
    public void getAllRecomm() {
        SellSystemApi.getInstance(app.AppContext)
                .getHomePage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RecommendBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<RecommendBean> recommendBeans) {
                        baseview.onGetAllRecomm(recommendBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onerror",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
