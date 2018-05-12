package com.sell.liqihao.sellsystem.App.api;

import android.content.Context;

import com.sell.liqihao.sellsystem.App.app;
import com.sell.liqihao.sellsystem.contents.Constant;
import com.sell.liqihao.sellsystem.module.HomePage.bean.RecommendBean;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Login.bean.UserBean;
import com.sell.liqihao.sellsystem.module.MyOrder.bean.OrderBean;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.shoppingCart.bean.CartBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liqihao on 2018/1/10.
 */


public class SellSystemApi {
    public static SellSystemApi instance;

    private SellSystemiService service;


    private Context mContext;

    private JSONObject jsonObject = new JSONObject();

    Retrofit retrofit;

    public SellSystemApi(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SellSystemiService.class);
        mContext = context;
    }

    public static SellSystemApi getInstance(Context context) {
        if (instance == null)
            instance = new SellSystemApi(context);
        return instance;
    }

    private RequestBody handleBody(JSONObject jsonObject1) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8")
                ,jsonObject1.toString());
    }


    public Call getTest(String name,String password) {
        try {
            jsonObject.put("username",name);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return service.getTest(handleBody(jsonObject));
    }

    public RequestBody getHnadle(String name,String pass) {
        try {
            jsonObject.put("username",name);
            jsonObject.put("password",pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return handleBody(jsonObject);
    }

    public Observable<ResultBean> doRegister(String name, String pass, String tel, String nickName) {
        return service.doRegister(name,pass,nickName,tel);
    }

    public Observable<UserBean> doLogin(String username, String pass) {
        return service.doLogin(username,pass);
    }

    public Observable<List<ProductBean>> getProduct() {
        return service.getProduct();
    }

    public Observable<ResultBean>addToCart(int productId,int userId,int number) {
        return service.addToCart(productId,userId,number);
    }

    public Observable<List<CartBean>> getCartById(int id) {
        return service.getCart(id);
    }


    public Observable<ResultBean> deleteCart(int cartid) {
        return service.deleteCart(cartid);
    }

    public Observable<List<ProductBean>> search(String msg) {
        return service.search(msg);
    }


    public Observable<ResultBean> updatePass(String pass) {
        return service.updatepass(app.userBean.getUserId(),pass);
    }

    public Observable<ResultBean> updateAddress(String address) {
        return service.updateAddress(app.userBean.getUserId(),address);
    }

    public Observable<ResultBean> buy(int orderid,String note,String total,String time) {
        return service.buy(app.userBean.getUserId(),orderid,note,total,time);
    }

    public Observable<List<RecommendBean>> getHomePage() {
        return service.getHomePage();
    }

    public Observable<List<OrderBean>> getAllOrder() {
        return service.allOrder(app.userBean.getUserId());
    }

    public Observable<ResultBean> finish(int orderId) {
        return service.finish(orderId);
    }

    public Observable<ResultBean> forgetPass(String userName,String pass) {
        return service.forgetPass(userName, pass);
    }












    public static String md5(String string) {//MD5加密
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}

