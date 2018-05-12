package com.sell.liqihao.sellsystem.module.Login.presenter;

import android.util.Log;

import com.sell.liqihao.sellsystem.App.api.SellSystemApi;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.base.BasePresenter;
import com.sell.liqihao.sellsystem.module.Login.bean.UserBean;
import com.sell.liqihao.sellsystem.module.Login.contract.LoginContract;
import com.sell.liqihao.sellsystem.module.Login.ui.LoginActivity;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.presenter{

    private Observer<UserBean> ob = new Observer<UserBean>() {
        @Override
        public void onSubscribe(Disposable d) {
            baseview.onShowLoading();
        }

        @Override
        public void onNext(UserBean userBean) {
            baseview.onLogin(userBean);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("LoginError",e.toString());
        }

        @Override
        public void onComplete() {
            baseview.onHideLoading();
        }
    };

    private Observable<UserBean> obs;
    @Override
    public void doLogin(String name, String pass) {
            obs = SellSystemApi.getInstance(app.AppContext)
                    .doLogin(name,pass);
            obs.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(ob);
    }

    public void onCancel() {
        obs.unsubscribeOn(AndroidSchedulers.mainThread());
    }
}
