package com.sell.liqihao.sellsystem.module.shoppingCart.bean;

/**
 * Created by ll on 2017/12/10.
 */

public class CartBean {
    private int cartid;
    private int productid;
    private String format;
    private String detail;
    private String price;
    private String img;
    private String tyoe;
    private String name;
    private String number;


    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
}
