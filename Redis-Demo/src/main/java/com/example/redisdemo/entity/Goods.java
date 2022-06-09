package com.example.redisdemo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author By-Lin
 */
public class Goods implements Serializable {
    private int id;
    private String goodsName;
    private BigDecimal price;

    public Goods() {
    }

    public Goods(int id, String goodsName, BigDecimal price) {
        this.id = id;
        this.goodsName = goodsName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
