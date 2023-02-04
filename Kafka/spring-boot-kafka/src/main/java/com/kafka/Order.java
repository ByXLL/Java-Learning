package com.kafka;

import java.io.Serializable;

/**
 * 订单
 * @author By-Lin
 */
public class Order implements Serializable {
    private int id;
    private int orderPrice;
    private String orderName;

    public Order(int id, int orderPrice, String orderName) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.orderName = orderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderPrice=" + orderPrice +
                ", orderName='" + orderName + '\'' +
                '}';
    }
}
