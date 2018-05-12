package com.sell.liqihao.sellsystem.module.allGoods.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseFragment;
import com.sell.liqihao.sellsystem.module.allGoods.Adapter.AllItemAdapter;
import com.sell.liqihao.sellsystem.module.allGoods.Adapter.AllItemTypeAdapter;
import com.sell.liqihao.sellsystem.module.allGoods.bean.AllItemBean;
import com.sell.liqihao.sellsystem.module.allGoods.bean.AllTypeBean;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.allGoods.contract.AllGodsContract;
import com.sell.liqihao.sellsystem.module.allGoods.presenter.AllGodsPresenter;
import com.sell.liqihao.sellsystem.module.shoppingDetail.ui.ShoppingDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liqihao on 2017/12/4.
 */

public class AllGodsFragment extends BaseFragment<AllGodsPresenter> implements AllGodsContract.view {

    AllItemAdapter itemAdapter;
//    @BindView(R.id.all_type_recycle)
//    RecyclerView allTypeRecycle;
    @BindView(R.id.all_item_recycle)
    RecyclerView allItemRecycle;
    Unbinder unbinder;
    @BindView(R.id.allgods_tab)
    TabLayout allgodsTab;
    Unbinder unbinder1;

    @Override
    public void initView() {
    }


    List<ProductBean> itemBeanList = new ArrayList<>();
    @Override
    public void getData() {
        itemAdapter = new AllItemAdapter(itemBeanList,getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(app.AppContext, 2);
        allItemRecycle.setLayoutManager(layoutManager);
        allItemRecycle.setAdapter(itemAdapter);
        presenter.getProduct();
        itemAdapter.setOnItemClickListener(new AllItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Intent intent = new Intent(getContext(), ShoppingDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("good",itemBeanList.get(pos));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.allgoodsfragment;
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
    public void setPresenter(AllGodsPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new AllGodsPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @Override
    public void onGetProduct(List<ProductBean> data) {
        itemBeanList.clear();
        itemBeanList.addAll(data);
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetType(final List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            allgodsTab.addTab(allgodsTab.newTab().setText(data.get(i)));
        }
        if (allgodsTab.getTabAt(0) != null){
            allgodsTab.getTabAt(0).select();
        }
        presenter.getTypeData(data.get(0));
        allgodsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                presenter.getTypeData(data.get(tab.getPosition()));
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
