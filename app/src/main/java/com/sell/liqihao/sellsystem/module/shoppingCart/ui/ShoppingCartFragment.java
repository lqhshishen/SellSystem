package com.sell.liqihao.sellsystem.module.shoppingCart.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseFragment;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.shoppingCart.adapter.ShoppingCartAdapter;
import com.sell.liqihao.sellsystem.module.shoppingCart.bean.CartBean;
import com.sell.liqihao.sellsystem.module.shoppingCart.contract.ShoppingCartContract;
import com.sell.liqihao.sellsystem.module.shoppingCart.presenter.ShoppingCartPresenter;
import com.sell.liqihao.sellsystem.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liqihao on 2017/12/4.
 */

public class ShoppingCartFragment extends BaseFragment<ShoppingCartPresenter> implements ShoppingCartContract.view {
    ShoppingCartAdapter mAdapter;
    List<CartBean> data = new ArrayList<>();

    Unbinder unbinder1;
    @BindView(R.id.shopping_cart_number)
    TextView shoppingCartNumber;
    @BindView(R.id.shopping_cart_money)
    TextView shoppingCartMoney;
    @BindView(R.id.shopping_cart_goToOrder)
    Button shoppingCartGoToOrder;

    @BindView(R.id.cart_recycler)
    XRecyclerView cartRecycler;
    @BindView(R.id.loading)
    ProgressBar loading;


    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        mAdapter = new ShoppingCartAdapter(data, getContext());
        cartRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        cartRecycler.setAdapter(mAdapter);
        cartRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.getCart(app.userBean.getUserId());
                        cartRecycler.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                cartRecycler.loadMoreComplete();
            }
        });
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRefresh(String event) {
        presenter.getCart(app.userBean.getUserId());
    }

    @Override
    public void getData() {

        presenter.getCart(app.userBean.getUserId());
        mAdapter = new ShoppingCartAdapter(this.data, getContext());
        cartRecycler.setAdapter(mAdapter);

    }

    int price = 0;

    @Override
    public int getLayout() {
        return R.layout.shoppingcartfragment;
    }

    @Override
    public void onShowLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(ShoppingCartPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new ShoppingCartPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void onGetCart(List<CartBean> mdata) {
        this.data.clear();
        this.data.addAll(mdata);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnLongClickListener(new ShoppingCartAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClickListener(View view, final int pos) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage("是否删除此购物车信息")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.e("position", String.valueOf(pos));
                                Log.e("productid", String.valueOf(data.get(pos - 1).getProductid()));
                                presenter.deleteCart(data.get(pos - 1).getCartid());
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                dialog.show();
            }
        });
        mAdapter.setOnClickListener(new ShoppingCartAdapter.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(View view) {
                price = 0;
                shoppingCartNumber.setText("已选" + String.valueOf(mAdapter.positionList().size()) + "件商品");
                for (int i = 0; i < mAdapter.positionList().size(); i++) {
                    price += (Integer.parseInt(data.get(mAdapter.positionList().get(i) - 1).getPrice()) * Integer.parseInt(data.get(mAdapter.positionList().get(i) - 1).getNumber()));
                }
                shoppingCartMoney.setText(String.valueOf(price) + "元");
            }
        });
    }

    @Override
    public void onDeleteCart(ResultBean resultBean) {
        presenter.getCart(app.userBean.getUserId());
        mAdapter.clearCheckBox();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBuy(ResultBean resultBean) {
        List<CartBean> mData = new ArrayList<>();
        if (resultBean.getResult().equals("success")) {
            for (int i = 0; i < mAdapter.positionList().size(); i++) {
                mData.add(data.get(mAdapter.positionList().get(i) - 1));
            }
            for (int i = 0; i < mData.size(); i++) {
                presenter.deleteCart(mData.get(i).getCartid());
            }
            int money = Integer.parseInt(app.userBean.getMoney());
            app.userBean.setMoney(String.valueOf(money - price));
            ToastUtils.showShort(getContext(), "购买成功");

        } else ToastUtils.showShort(getContext(), "购买失败，余额不足");
        EventBus.getDefault().post(new ResultBean("change"));
        shoppingCartMoney.setText("0" + "元");
        shoppingCartNumber.setText("已选" + "0" + "件商品");
        mAdapter.clearCheckBox();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    JSONArray jsa = new JSONArray();

    @OnClick(R.id.shopping_cart_goToOrder)
    public void onViewClicked() {
        buy();
    }


    public void buy() {
        getTime();
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage("您当前的余额为：" + app.userBean.getMoney() + "是否确定购买？")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int x) {
                        if (price == 0) {
                            ToastUtils.showShort(getContext(), "请先选择商品");
                        } else {
                            for (int i = 0; i < mAdapter.positionList().size(); i++) {
                                JSONObject js = new JSONObject();
                                try {
                                    js.put("productid", data.get(mAdapter.positionList().get(i) - 1).getProductid());
                                    js.put("name", data.get(mAdapter.positionList().get(i) - 1).getName());
                                    js.put("number", data.get(mAdapter.positionList().get(i) - 1).getNumber());
                                    jsa.put(js);
                                } catch (JSONException e) {
                                    Log.e("error", e.toString());
                                }
                            }
                            String id = year + month + sec + app.userBean.getUserId() + mAdapter.positionList().size();
                            int trueId = Integer.parseInt(id);
                            String time = year + month + day + hour;
                            String note = jsa.toString();
                            presenter.buy(trueId, note, time, String.valueOf(price));
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        dialog.show();


    }

    Calendar cal;
    String year;
    String month;
    String day;
    String hour;
    String min;
    String sec;

    public void getTime() {
        long mTime = System.currentTimeMillis();
        cal = Calendar.getInstance();
        cal.setTimeInMillis(mTime);
        year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        day = String.valueOf(cal.get(Calendar.DATE));
        if (cal.get(Calendar.AM_PM) == 0)
            hour = String.valueOf(cal.get(Calendar.HOUR));
        else
            hour = String.valueOf(cal.get(Calendar.HOUR) + 12);
        min = String.valueOf(cal.get(Calendar.MINUTE));
        sec = String.valueOf(cal.get(Calendar.SECOND));


    }

}
