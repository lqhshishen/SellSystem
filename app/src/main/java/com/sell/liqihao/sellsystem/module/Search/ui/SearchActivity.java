package com.sell.liqihao.sellsystem.module.Search.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseActivity;
import com.sell.liqihao.sellsystem.module.Search.contract.SearchContract;
import com.sell.liqihao.sellsystem.module.Search.presenter.SearchPresenter;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.hotGoods.adapter.HotItemAdapter;
import com.sell.liqihao.sellsystem.module.shoppingDetail.ui.ShoppingDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.view {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.AppBarLayout01)
    AppBarLayout AppBarLayout01;
    @BindView(R.id.search_search)
    RecyclerView searchSearch;

    String title;
    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("msg");
        initToolBar(toolbar,true,title);
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.navbar_back);
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


    HotItemAdapter adapter;
    List<ProductBean> data = new ArrayList<>();
    @Override
    public void getData() {
        adapter = new HotItemAdapter(data,this);
        presenter.doSearch(title);
        searchSearch.setLayoutManager(new LinearLayoutManager(this));
        searchSearch.setAdapter(adapter);
        adapter.setOnItemClickListener(new HotItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Intent intent = new Intent(getApplication(), ShoppingDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("good",data.get(pos));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }



    @Override
    public int getLayout() {
        return R.layout.activity_search;
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
    public void setPresenter(SearchPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new SearchPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void onSearch(List<ProductBean> data) {
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
