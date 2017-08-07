package com.example.zhangyuan.myapplication.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangyuan.myapplication.R;

import zy.adapter.BaseViewHolder;
import zy.annotation.ViewInject;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
public class TextHolder extends BaseViewHolder<String> {

    @ViewInject(R.id.text_view)
    private TextView textView;

    public TextHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    protected void initListener() {
        getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getData(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onBindData(String data) {
        textView.setText(data);
    }
}
