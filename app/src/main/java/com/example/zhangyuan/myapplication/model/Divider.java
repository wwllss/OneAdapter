package com.example.zhangyuan.myapplication.model;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
public class Divider {
    //empty

    public final Height height;

    public Divider(Height height) {
        this.height = height;
    }

    public enum Height {
        FIVE, TEN
    }
}
