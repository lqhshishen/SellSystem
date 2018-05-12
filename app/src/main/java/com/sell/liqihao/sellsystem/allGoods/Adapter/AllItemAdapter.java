package com.sell.liqihao.sellsystem.allGoods.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.GetContext;
import com.sell.liqihao.sellsystem.allGoods.bean.AllItemBean;

import java.util.List;

/**
 * Created by ll on 2017/12/9.
 */

public class AllItemAdapter extends RecyclerView.Adapter<AllItemAdapter.ViewHolder> {
    List<AllItemBean> data;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_item,parent,false);
        return new ViewHolder(view);
    }
    public AllItemAdapter (List<AllItemBean> mdata) {
        data = mdata;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(GetContext.getContext())
                .load(data.get(position).getImg())
                .into(holder.img);
        holder.money.setText(data.get(position).getMoney());
        holder.name.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void clear() {
        data.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView money;
        TextView name;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView;
            money = itemView.findViewById(R.id.all_item_money);
            name = itemView.findViewById(R.id.all_item_name);
            img = itemView.findViewById(R.id.all_item_img);
        }
    }
}
