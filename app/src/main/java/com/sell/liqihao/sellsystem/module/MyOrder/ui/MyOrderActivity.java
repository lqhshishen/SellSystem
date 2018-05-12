package com.sell.liqihao.sellsystem.module.MyOrder.ui;

import android.content.DialogInterface;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;


import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseActivity;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.MyOrder.adapter.OrderAdapter;
import com.sell.liqihao.sellsystem.module.MyOrder.bean.OrderBean;
import com.sell.liqihao.sellsystem.module.MyOrder.contract.MyOrderContract;
import com.sell.liqihao.sellsystem.module.MyOrder.presenter.MyOrderPresenter;
import com.sell.liqihao.sellsystem.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends BaseActivity<MyOrderPresenter> implements MyOrderContract.view {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.AppBarLayout01)
    AppBarLayout AppBarLayout01;
    @BindView(R.id.order_tab)
    TabLayout orderTab;
    @BindView(R.id.order_recycle)
    RecyclerView orderRecycle;

    OrderAdapter adapter;
    List<OrderBean> data = new ArrayList<>();
    @Override
    public void initView() {
        ButterKnife.bind(this);
        initToolBar(toolbar,true,"我的订单");
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.navbar_back);
        orderRecycle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderAdapter(data,this);
        orderRecycle.setAdapter(adapter);
        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                presenter.finishOrder(data.get(pos).getOrderid());
            }
        });
    }

    @Override
    public void getData() {
        presenter.getOrder();
        orderTab.addTab(orderTab.newTab().setText("未完成"));
        orderTab.addTab(orderTab.newTab().setText("已完成"));
        orderTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                presenter.sloveData(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_my_order;
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(MyOrderPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new MyOrderPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetOrder(List<OrderBean> data) {
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFinishOrder(ResultBean resultBean) {
        presenter.getOrder();
        ToastUtils.showShort(getApplicationContext(),"订单完成");
    }

}
