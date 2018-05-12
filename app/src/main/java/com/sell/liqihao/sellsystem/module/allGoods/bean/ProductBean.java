package com.sell.liqihao.sellsystem.module.allGoods.bean;

import java.io.Serializable;

public class ProductBean implements Serializable{
    private int productid;
    private String format;
    private String detail;
    private String price;
    private String img;
    private String tyoe;
    private String name;
    private String inventory;


    public ProductBean(int id,String format,String detail,String price,String img,String tyoe,String name,String inventory) {
        this.productid = id;
        this.format = format;
        this.detail = detail;
        this.price = price;
        this.img = img;
        this.tyoe = tyoe;
        this.name = name;
        this.inventory = inventory;
    }
    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
