package com.sell.liqihao.sellsystem.module.allGoods.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.module.Util.GetContext;
import com.sell.liqihao.sellsystem.module.allGoods.bean.AllItemBean;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;

import java.util.List;

/**
 * Created by ll on 2017/12/9.
 */

public class AllItemAdapter extends RecyclerView.Adapter<AllItemAdapter.ViewHolder> {
    private List<ProductBean> data;

    private Context context;



    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int pos);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_item,parent,false);
        return new ViewHolder(view);
    }
    public AllItemAdapter (List<ProductBean> mdata,Context context) {
        data = mdata;
        this.context = context;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context)
                .load(data.get(position).getImg())
                .into(holder.img);
        holder.money.setText("¥" + data.get(position).getPrice());
        holder.name.setText(data.get(position).getName());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,holder.getAdapterPosition());
            }
        });
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
