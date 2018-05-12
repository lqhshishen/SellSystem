package com.sell.liqihao.sellsystem.module.HomePage.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseFragment;
import com.sell.liqihao.sellsystem.module.HomePage.adapter.RecommendAdapter;
import com.sell.liqihao.sellsystem.module.HomePage.bean.RecommendBean;
import com.sell.liqihao.sellsystem.module.HomePage.contract.RecommendContract;
import com.sell.liqihao.sellsystem.module.HomePage.presenter.RecommendPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liqihao on 2017/12/5.
 */

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.view {


    @BindView(R.id.recomm_list)
    RecyclerView recommList;
    Unbinder unbinder;


    List<RecommendBean> mData = new ArrayList<>();
    List<RecommendBean> mData2 = new ArrayList<>();
    List<RecommendBean> mData3 = new ArrayList<>();
    @BindView(R.id.recomm_list2)
    RecyclerView recommList2;
    @BindView(R.id.recomm_list3)
    RecyclerView recommList3;


    RecommendAdapter recommendAdapter;
    RecommendAdapter recommendAdapter2;
    RecommendAdapter recommendAdapter3;
    @Override
    public void initView() {
        recommendAdapter = new RecommendAdapter(mData, getContext());
        recommendAdapter2 = new RecommendAdapter(mData2, getContext());
        recommendAdapter3 = new RecommendAdapter(mData3, getContext());
        recommList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recommList.setAdapter(recommendAdapter);
        recommList2.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recommList2.setAdapter(recommendAdapter);
        recommList3.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recommList3.setAdapter(recommendAdapter);

    }

    @Override
    public void getData() {
        presenter.getAllRecomm();
    }

    @Override
    public int getLayout() {
        return R.layout.recommendfragment;
    }


    List<RecommendBean> list = new ArrayList<>();


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
    public void setPresenter(RecommendPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new RecommendPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onGetAllRecomm(List<RecommendBean> data) {
        mData.clear();
        mData2.clear();
        mData2.clear();
        for (int i = 0;i < 6;i ++) {
            mData.add(data.get(i));
        }
        for (int i = 6;i < 12;i ++) {
            mData2.add(data.get(i));
        }
        for (int i = 12;i < 18;i ++) {
            mData3.add(data.get(i));
        }
        recommendAdapter.notifyDataSetChanged();
        recommendAdapter2.notifyDataSetChanged();
        recommendAdapter3.notifyDataSetChanged();
    }
}
