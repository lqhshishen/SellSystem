package com.sell.liqihao.sellsystem.hotGoods.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.GetContext;
import com.sell.liqihao.sellsystem.hotGoods.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2017/12/7.
 */

public class HotItemAdapter extends RecyclerView.Adapter<HotItemAdapter.ViewHolder> {
    private List<ItemBean> data = new ArrayList<>();

    private OnItemClickListener mListener;
    public HotItemAdapter(List<ItemBean> mdata) {
        data = mdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate
               (R.layout.hot_item,parent,false);

        return new ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView money;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_name);
            money = itemView.findViewById(R.id.item_money);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void clearData(){
        data.clear();
    }
    public interface OnItemClickListener {
        void onItemClick(ItemBean re);
    }
}
