package zy.adapter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import zy.utils.InjectUtil;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
public abstract class BaseViewHolder<T> extends ViewHolder implements LifecycleObserver {

    private final Context context;

    private final View itemView;

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

    protected View getItemView() {
        return itemView;
    }

    public T getData() {
        return data;
    }

    protected void initListener() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {

    }

    void bindData(T data) {
        this.data = data;
        onBindData(data);
    }

    protected abstract void onBindData(T data);
}
