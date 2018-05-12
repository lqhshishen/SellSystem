package com.sell.liqihao.sellsystem.module.shoppingDetail.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.R;
import com.sell.liqihao.sellsystem.base.BaseActivity;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.shoppingDetail.contract.ShoppingDetailContract;
import com.sell.liqihao.sellsystem.module.shoppingDetail.presenter.ShoppingDetailPresenter;
import com.sell.liqihao.sellsystem.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingDetail extends BaseActivity<ShoppingDetailPresenter> implements ShoppingDetailContract.view {


    @BindView(R.id.shopping_detail_img)
    ImageView shoppingDetailImg;
    @BindView(R.id.shopping_detail_toolbar)
    Toolbar shoppingDetailToolbar;
    @BindView(R.id.shopping_detail_collapsing)
    CollapsingToolbarLayout shoppingDetailCollapsing;
    @BindView(R.id.shoppingDetail_appBar)
    AppBarLayout shoppingDetailAppBar;
    @BindView(R.id.shoppingDetail_detail)
    TextView shoppingDetailDetail;
    @BindView(R.id.shopping_detail_addToCart)
    Button shoppingDetailAddToCart;
    @BindView(R.id.shopping_detail_buy)
    Button shoppingDetailBuy;

    int number = 1;
    @BindView(R.id.shopping_detail_number)
    TextView shoppingDetailNumber;
    @BindView(R.id.shopping_detail_add)
    FloatingActionButton shoppingDetailAdd;
    @BindView(R.id.shopping_detail_cut)
    FloatingActionButton shoppingDetailCut;
    @BindView(R.id.shoppingDetail_price)
    TextView shoppingDetailPrice;
    @BindView(R.id.shopping_detail_inventory)
    TextView shoppingDetailInventory;
    @BindView(R.id.shopping_detail_productName)
    TextView shoppingDetailProductName;

    @Override
    public void initView() {

        ButterKnife.bind(this);


    }

    ProductBean product;

    @SuppressLint("SetTextI18n")
    @Override
    public void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        product = (ProductBean) bundle.get("good");
        Glide.with(this)
                .load(product.getImg())
                .into(shoppingDetailImg);
        setSupportActionBar(shoppingDetailToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        shoppingDetailProductName.setText("商品名称：" + product.getName());
        shoppingDetailCollapsing.setTitle("");
        shoppingDetailCollapsing.setExpandedTitleColor(this.getResources().getColor(R.color.black));
        shoppingDetailDetail.setText(product.getDetail());
        shoppingDetailNumber.setText(String.valueOf(number));
        shoppingDetailPrice.setText("¥" + product.getPrice());
        shoppingDetailInventory.setText("库存：" + product.getInventory() + "件");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_shopping_detail;
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(ShoppingDetailPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new ShoppingDetailPresenter();
    }

    @Override
    public void onShowNoMore() {

    }
    JSONArray jsa;
    @OnClick({R.id.shopping_detail_addToCart, R.id.shopping_detail_buy, R.id.shopping_detail_add, R.id.shopping_detail_cut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopping_detail_addToCart:
                presenter.addToCart(product.getProductid(), app.userBean.getUserId(),number);
                shoppingDetailNumber.setText(String.valueOf(number = 1));
                EventBus.getDefault().post("x");
                break;
            case R.id.shopping_detail_buy:
                doBuy();
                break;
            case R.id.shopping_detail_add:
                if (number + 1 < Integer.parseInt(product.getInventory())) {
                    shoppingDetailNumber.setText(String.valueOf(++number));
                } else ToastUtils.showShort(this, "超出库存总量");
                break;
            case R.id.shopping_detail_cut:
                if (number - 1 > 0) {
                    shoppingDetailNumber.setText(String.valueOf(--number));
                }
                break;
        }
    }
    int price;
    void doBuy() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("您当前的余额为：" + app.userBean.getMoney() + "是否确定购买？")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int x) {
                        getTime();
                        jsa = new JSONArray();
                        JSONObject js = new JSONObject();
                        try {
                            js.put("name", product.getName());
                            js.put("number", number);
                            jsa.put(js);
                        } catch (JSONException e) {
                            Log.e("error", e.toString());
                        }
                        String id = year + sec + app.userBean.getUserId() + "1";
                        int trueId = Integer.parseInt(id);
                        String time = year + month + day + hour;
                        String note = jsa.toString();
                        price = Integer.parseInt(product.getPrice()) * number;
                        presenter.buy(trueId, note,String.valueOf(price),time);
                        }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        dialog.show();
    }

    @Override
    public void onAddToCart(ResultBean resultBean) {

        if (resultBean.getResult().equals("success"))
            ToastUtils.showShort(this,"添加成功");
    }

    @Override
    public void onBuy(ResultBean resultBean) {
        Log.e("test",resultBean.getResult());
        if (resultBean.getResult().equals("success")){
            int money = Integer.parseInt(app.userBean.getMoney()) - price;
            app.userBean.setMoney(String.valueOf(money));
            ToastUtils.showShort(this,"购买成功");
            EventBus.getDefault().post(new ResultBean("fuckU"));
        }
        else
            ToastUtils.showShort(this,"余额不足,购买失败");
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post("xx");
        super.onDestroy();
    }

    Calendar cal;
    String year;
    String month;
    String day;
    String hour;
    String min;
    String sec;

    public void getTime() {
        long mTime = System.currentTimeMillis();
        cal = Calendar.getInstance();
        cal.setTimeInMillis(mTime);
        year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        day = String.valueOf(cal.get(Calendar.DATE));
        if (cal.get(Calendar.AM_PM) == 0)
            hour = String.valueOf(cal.get(Calendar.HOUR));
        else
            hour = String.valueOf(cal.get(Calendar.HOUR) + 12);
        min = String.valueOf(cal.get(Calendar.MINUTE));
        sec = String.valueOf(cal.get(Calendar.SECOND));


    }
}
