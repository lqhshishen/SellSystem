package com.sell.liqihao.sellsystem.module.HomePage.bean;

import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;

/**
 * Created by liqihao on 2017/12/7.
 */

public class RecommendBean  {
    private String name;


    private int productid;
    private String format;
    private String detail;
    private String price;
    private String img;
    private String tyoe;
    private String productName;
    private String inventory;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTyoe() {
        return tyoe;
    }

    public void setTyoe(String tyoe) {
        this.tyoe = tyoe;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}
