package com.sell.liqihao.sellsystem.module.hotGoods.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseFragment;
import com.sell.liqihao.sellsystem.module.Util.GetContext;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.hotGoods.adapter.HotItemAdapter;
import com.sell.liqihao.sellsystem.module.hotGoods.contract.HotGoodsContract;
import com.sell.liqihao.sellsystem.module.hotGoods.presenter.HotGoodsPresenter;
import com.sell.liqihao.sellsystem.module.shoppingDetail.ui.ShoppingDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liqihao on 2017/12/4.
 */

public class HotGoodsFragment extends BaseFragment<HotGoodsPresenter> implements HotGoodsContract.view {

    HotItemAdapter adapter;

    @BindView(R.id.hot_item)
    RecyclerView hotItem;
    Unbinder unbinder1;

    List<ProductBean> list = new ArrayList<>();
    @Override
    public void initView() {
        hotItem.setLayoutManager(new LinearLayoutManager(GetContext.getContext()));
        adapter = new HotItemAdapter(list,getContext());
        hotItem.setAdapter(adapter);
    }

    @Override
    public void getData() {
        presenter.getAllProduct();
        adapter.setOnItemClickListener(new HotItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Intent intent = new Intent(getContext(), ShoppingDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("good",list.get(pos));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.hotgoodsfragment;
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
    public void setPresenter(HotGoodsPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new HotGoodsPresenter();
    }

    @Override
    public void onShowNoMore() {

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

    @Override
    public void onGetAllPro(List<ProductBean> data) {
        list.clear();
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
