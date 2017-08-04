package com.example.zhangyuan.myapplication.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import zy.adapter.BaseViewHolder;
import zy.annotation.ViewInject;
import com.example.zhangyuan.myapplication.R;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
public class ImageHolder extends BaseViewHolder<Integer> {

    @ViewInject(R.id.image_view)
    private ImageView imageView;

    public ImageHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    protected void onBindData(Integer data) {
        imageView.setImageResource(data);
    }
}
