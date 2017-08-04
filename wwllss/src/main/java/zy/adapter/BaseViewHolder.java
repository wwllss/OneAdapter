package zy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import zy.utils.InjectUtil;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */

public abstract class BaseViewHolder<T> extends ViewHolder {

    private final Context context;

    protected final View itemView;

    private T data;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        InjectUtil.inject(this, itemView);
        initListener();
    }

    public Context getContext() {
        return context;
    }

    public T getData() {
        return data;
    }

    protected void initListener() {

    }

    public void setData(T data) {
//        try {
        this.data = data;
        onBindData(data);
//        } catch (ClassCastException e) {
//            e.printStackTrace();
//        }
    }

    protected abstract void onBindData(T data);
}
