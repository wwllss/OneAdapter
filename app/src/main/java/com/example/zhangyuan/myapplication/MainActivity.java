package com.example.zhangyuan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhangyuan.myapplication.holder.DividerHolder;
import com.example.zhangyuan.myapplication.holder.DividerTenHolder;
import com.example.zhangyuan.myapplication.holder.ImageHolder;
import com.example.zhangyuan.myapplication.holder.TextHolder;
import com.example.zhangyuan.myapplication.model.Divider;

import java.util.ArrayList;
import java.util.List;

import zy.adapter.HolderInfo;
import zy.adapter.Linker;
import zy.adapter.OneAdapter;
import zy.annotation.ViewInject;
import zy.utils.InjectUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @ViewInject(R.id.list_view)
    private RecyclerView listView;

    private OneAdapter<Object> adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtil.inject(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(manager);

        adapter = new OneAdapter<>();
        adapter.register(String.class, TextHolder.class, R.layout.item_string);
        adapter.register(Integer.class, ImageHolder.class, R.layout.item_image);
        adapter.register(Divider.class, new Linker<Divider>() {
            @NonNull
            @Override
            public HolderInfo[] getHolderInfoList() {
                return new HolderInfo[]{
                        HolderInfo.info(DividerHolder.class, R.layout.item_divider),
                        HolderInfo.info(DividerTenHolder.class, R.layout.item_divider_ten)
                };
            }

            @Override
            public int linkHolder(Divider data, int position) {
                return data.height == Divider.Height.TEN ? 1 : 0;
            }
        });
        listView.setAdapter(adapter);

        preData();

    }

    private int offset = 0;

    private void preData() {
        List<Object> data = new ArrayList<>();
        for (int i = offset; i < offset + 50; i++) {
            if (i % 3 == 0) {
                data.add(String.valueOf(i));
            } else if (i % 3 == 1) {
                data.add(R.mipmap.ic_launcher);
            } else {
                data.add(new Divider(
                        i % 2 == 0 ? Divider.Height.FIVE : Divider.Height.TEN
                ));
            }
        }
        offset = adapter.notifyDataSetChanged(data, false);
    }

    public void add(View view) {
        preData();
    }
}
