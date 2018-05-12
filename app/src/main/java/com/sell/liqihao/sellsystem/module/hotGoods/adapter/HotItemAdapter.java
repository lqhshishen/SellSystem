package com.sell.liqihao.sellsystem.module.hotGoods.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.module.Util.GetContext;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/7.
 */

public class HotItemAdapter extends RecyclerView.Adapter<HotItemAdapter.ViewHolder> {
    private List<ProductBean> data = new ArrayList<>();

    private Context context;

    private OnItemClickListener mListener;
    public HotItemAdapter(List<ProductBean> mdata, Context context) {
        data = mdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate
               (R.layout.hot_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(context)
                .load(data.get(position).getImg())
                .into(holder.img);
        holder.money.setText("¥" + data.get(position).getPrice());
        holder.name.setText(data.get(position).getName());
        holder.hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(view,holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView money;
        LinearLayout hot;
        public ViewHolder(View itemView) {
            super(itemView);
            hot = itemView.findViewById(R.id.hot_item);
            img = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_name);
            money = itemView.findViewById(R.id.item_money);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view,int pos);
    }
}
