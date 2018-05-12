package com.sell.liqihao.sellsystem.module.shoppingCart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.module.Util.GetContext;
import com.sell.liqihao.sellsystem.module.allGoods.Adapter.AllItemAdapter;
import com.sell.liqihao.sellsystem.module.shoppingCart.bean.CartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ll on 2017/12/10.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private List<CartBean> data;

    private boolean flag[];

    private Context context;

    private List<Integer>positionList = new ArrayList<>();

    public ShoppingCartAdapter(List<CartBean> mData, Context context) {
        data = mData;
        flag = new boolean[data.size()];
        for (int i = 0; i < mData.size();i ++) {
            flag[i] = false;
        }
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        int price = Integer.parseInt(data.get(position).getPrice())
                * Integer.parseInt(data.get(position).getNumber());
        holder.name.setText(data.get(position).getName());
        holder.number.setText("数量：" + data.get(position).getNumber());
        holder.money.setText("￥" + String.valueOf(price));
        holder.size.setText("规格:" + data.get(position).getFormat());
        holder.checkBox.setOnCheckedChangeListener(null);
//        holder.checkBox.setChecked(flag[position]);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    positionList.add(holder.getAdapterPosition());
                else {
                    for (int i = 0; i < positionList.size(); i++) {
                        if (positionList.get(i) == holder.getAdapterPosition())
                            positionList.remove(i);
                    }
                }
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view);
            }
        });
        Glide.with(app.AppContext).load(data.get(position).getImg()).into(holder.img);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longListener.onItemLongClickListener(view,holder.getAdapterPosition());
                return true;
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
        TextView size;
        TextView money;
        TextView number;
        CheckBox checkBox;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cart_cardView);
            checkBox = itemView.findViewById(R.id.checkBox);
            img = itemView.findViewById(R.id.cart_img);
            name = itemView.findViewById(R.id.cart_name);
            size = itemView.findViewById(R.id.cart_size);
            money = itemView.findViewById(R.id.cart_money);
            number = itemView.findViewById(R.id.cart_number);
        }
    }

    public void clearCheckBox() {
        positionList.clear();
    }






    private OnLongClickListener longListener;

    private OnClickListener listener;

    public void setOnLongClickListener(OnLongClickListener longListener) {
        this.longListener = longListener;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public List<Integer> positionList() {
        return positionList;
    }


    public interface OnClickListener{
        void onItemClick(View view);
    }

    public interface OnLongClickListener{
        public void onItemLongClickListener(View view,int pos);
    }


}
