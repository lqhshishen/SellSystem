package com.sell.liqihao.sellsystem.module.HomePage.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.module.HomePage.bean.RecommendBean;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.shoppingDetail.ui.ShoppingDetail;

import java.util.List;

/**
 * Created by liqihao on 2017/12/8.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RecommendBean> data;

    private Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommend_item, parent, false);
            return new itemHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof itemHolder) {
            Glide.with(context)
                    .load(data.get(position).getImg())
                    .into(((itemHolder) holder).img);
            ((itemHolder) holder).name.setText(data.get(position).getProductName());
            ((itemHolder) holder).money.setText("¥" + data.get(position).getPrice());
            ((itemHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ShoppingDetail.class);
                    Bundle bundle = new Bundle();
                    ProductBean pro = new ProductBean(data.get(holder.getAdapterPosition()).getProductid()
                    ,data.get(holder.getAdapterPosition()).getFormat()
                    ,data.get(holder.getAdapterPosition()).getDetail()
                    ,data.get(holder.getAdapterPosition()).getPrice()
                    ,data.get(holder.getAdapterPosition()).getImg()
                    ,data.get(holder.getAdapterPosition()).getTyoe()
                    ,data.get(holder.getAdapterPosition()).getName()
                    ,data.get(holder.getAdapterPosition()).getInventory());
                    bundle.putSerializable("good",pro);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }

    }



    public RecommendAdapter(List < RecommendBean > mData, Context context) {
            data = mData;
            this.context = context;
        }

        @Override
        public int getItemCount () {
            return data.size();
        }

        class itemHolder extends RecyclerView.ViewHolder {
            TextView title;
            CardView cardView;
            ImageView img;
            TextView name;
            TextView money;

            public itemHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.hot_item_title);
                cardView = (CardView) itemView;
                img = itemView.findViewById(R.id.hot_item_img);
                name = itemView.findViewById(R.id.hot_item_name);
                money = itemView.findViewById(R.id.hot_item_money);
                if (money == null) {
                    Log.e("是否为空", "yes");
                }
            }
        }

    }
