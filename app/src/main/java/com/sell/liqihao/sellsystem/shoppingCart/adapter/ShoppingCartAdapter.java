package com.sell.liqihao.sellsystem.shoppingCart.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.GetContext;
import com.sell.liqihao.sellsystem.shoppingCart.bean.CartBean;

import java.util.List;

/**
 * Created by ll on 2017/12/10.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private List<CartBean> data;

    public ShoppingCartAdapter(List<CartBean> mData) {
        data = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.number.setText("数量：" + data.get(position).getNumber());
        holder.money.setText("￥" + data.get(position).getMoney());
        holder.size.setText("规格:" + data.get(position).getSize());
        Glide.with(GetContext.getContext()).load(data.get(position).getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView size;
        TextView money;
        TextView number;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.cart_img);
            name = itemView.findViewById(R.id.cart_name);
            size = itemView.findViewById(R.id.cart_size);
            money = itemView.findViewById(R.id.cart_money);
            number = itemView.findViewById(R.id.cart_number);
        }
    }
}
