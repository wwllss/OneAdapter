package com.example.zhangyuan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import zy.adapter.BaseAdapter;
import zy.adapter.ItemType;
import zy.annotation.ViewInject;
import zy.utils.InjectUtil;
import com.example.zhangyuan.myapplication.holder.DividerHolder;
import com.example.zhangyuan.myapplication.holder.ImageHolder;
import com.example.zhangyuan.myapplication.holder.TextHolder;
import com.example.zhangyuan.myapplication.model.Divider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @ViewInject(R.id.list_view)
    private RecyclerView listView;

    private BaseAdapter<Object> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtil.inject(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(manager);

        adapter = new BaseAdapter<>();
        adapter.register(new ItemType(R.layout.item_string, String.class, TextHolder.class));
        adapter.register(new ItemType(R.layout.item_image, Integer.class, ImageHolder.class));
        adapter.register(new ItemType(R.layout.item_divider, Divider.class, DividerHolder.class));
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
                data.add(new Divider());
            }
        }
        offset = adapter.notifyDataSetChanged(data, false);
    }

    public void add(View view) {
        preData();
    }
}
