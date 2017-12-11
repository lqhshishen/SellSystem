package com.sell.liqihao.sellsystem.allGoods.Adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.Util.GetContext;
import com.sell.liqihao.sellsystem.allGoods.bean.AllTypeBean;

import java.util.List;

/**
 * Created by ll on 2017/12/8.
 */

public class AllItemTypeAdapter extends RecyclerView.Adapter<AllItemTypeAdapter.ViewHolder> {
    List<AllTypeBean> data;

    public AllItemTypeAdapter(List<AllTypeBean> mData) {
        data = mData;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_type,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.type.setText(data.get(position).getType());
        holder.type.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                holder.type.setTextColor(R.color.green);
        }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        public ViewHolder(View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.all_type_adapter);
        }
    }
}
