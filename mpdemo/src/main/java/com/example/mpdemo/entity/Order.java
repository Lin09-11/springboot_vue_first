package com.example.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.lang.ref.SoftReference;

@TableName("t_order")
public class Order {
    private String id;
    private String orderTime;
    private String total;
    private String uid;

    @TableField(exist = false)
    private User user;

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", total='" + total + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
