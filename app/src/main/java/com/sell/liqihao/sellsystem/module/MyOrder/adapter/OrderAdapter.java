package com.sell.liqihao.sellsystem.module.MyOrder.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.module.MyOrder.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderBean> data = new ArrayList<>();

    private Context context;

    public OrderAdapter(List<OrderBean> data,Context context) {
        this.data = data;
        this.context = context;
        this.context = context;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view,int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.listener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.order_item,null);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).orderid.setText("订单号为:" + data.get(position).getOrderid());
            ((ViewHolder) holder).tans.setText("物流信息为:" + data.get(position).getTrans());
            if (data.get(position).getIsfinish() == null) {
                ((ViewHolder) holder).finish.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).isFinish.setText("未完成");
                ((ViewHolder) holder).isFinish.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                ((ViewHolder) holder).finish.setVisibility(View.GONE);
                ((ViewHolder) holder).isFinish.setText("已完成");
                ((ViewHolder) holder).isFinish.setTextColor(context.getResources().getColor(R.color.green));
            }
            ((ViewHolder) holder).time.setText("订单时间为:" + data.get(position).getTime());
            ((ViewHolder) holder).total.setText("总金额为:" + data.get(position).getTotal());
            ((ViewHolder) holder).finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context)
                            .setTitle("提示")
                            .setMessage("是否确定收到货物")
                            .setCancelable(false)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    listener.onItemClick(view,holder.getAdapterPosition());
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    dialog.show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderid;
        TextView isFinish;
        TextView tans;
        TextView total;
        TextView time;
        Button finish;
        public ViewHolder(View itemView) {
            super(itemView);
            orderid = itemView.findViewById(R.id.order_orderid);
            isFinish = itemView.findViewById(R.id.order_isFinish);
            tans = itemView.findViewById(R.id.order_trans);
            total = itemView.findViewById(R.id.order_total);
            time = itemView.findViewById(R.id.order_time);
            finish = itemView.findViewById(R.id.order_finish);


        }
    }
}
