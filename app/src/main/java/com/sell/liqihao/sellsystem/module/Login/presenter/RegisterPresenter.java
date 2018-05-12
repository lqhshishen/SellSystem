package com.sell.liqihao.sellsystem.module.Login.presenter;

import android.util.Log;

import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Login.contract.RegisterContract;
import com.sell.liqihao.sellsystem.module.Login.ui.RegisterActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterActivity> implements RegisterContract.presenter {

    @Override
    public void doRegister(String name, String pass, String tel, String nickName) {
        SellSystemApi.getInstance(app.AppContext)
                .doRegister(name,pass,tel,nickName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        baseview.onShowLoading();
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        baseview.onRegister(resultBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.toString());
                    }

                    @Override
                    public void onComplete() {
                        baseview.onHideLoading();
                    }
                });
    }
}
