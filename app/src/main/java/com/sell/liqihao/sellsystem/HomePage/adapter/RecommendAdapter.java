package com.sell.liqihao.sellsystem.HomePage.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.HomePage.bean.RecommendBean;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.GetContext;
import com.sell.liqihao.sellsystem.Util.LogUtil;

import java.util.List;

/**
 * Created by liqihao on 2017/12/8.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder>{
    List<RecommendBean>data;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommend_item,parent,false);
        return new ViewHolder(view);
    }

    public RecommendAdapter(List<RecommendBean> mData) {
        data = mData;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecommendBean itemBean = data.get(position);
//        LogUtil.e("测试数据",itemBean.getMoney());
        holder.money.setText(itemBean.getMoney());
        holder.name.setText(itemBean.getName());
        Glide.with(GetContext.getContext())
                .load(itemBean.getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView img;
        TextView name;
        TextView money;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            img = itemView.findViewById(R.id.hot_item_img);
            name = itemView.findViewById(R.id.hot_item_name);
            money = itemView.findViewById(R.id.hot_item_money);
            if(money == null) {
                Log.e("是否为空","yes");
            }
        }
    }
}
