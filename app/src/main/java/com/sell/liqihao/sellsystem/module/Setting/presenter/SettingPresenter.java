package com.sell.liqihao.sellsystem.module.Setting.presenter;


import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Setting.contract.SettingContract;
import com.sell.liqihao.sellsystem.module.Setting.ui.SettingActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SettingPresenter extends BasePresenter<SettingActivity> implements SettingContract.presenter {
    @Override
    public void doUpdateAddress(final String address) {
        SellSystemApi.getInstance(app.AppContext)
                .updateAddress(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        resultBean.setResult(address);
                        baseview.onAddressChange(resultBean);
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
    public void doUpdatePass(final String pass) {
        SellSystemApi.getInstance(app.AppContext)
                .updatePass(pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        resultBean.setResult(pass);
                        baseview.onPassChanger(resultBean);
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
