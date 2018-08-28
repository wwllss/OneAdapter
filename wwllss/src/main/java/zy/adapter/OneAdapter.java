package zy.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
public class OneAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private final List<T> dataList = new ArrayList<>();

    private final ItemTypePool itemTypePool;

    public OneAdapter() {
        itemTypePool = new DefaultItemTypePool();
    }

    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder<T> holder = itemTypePool.newInstance(parent, viewType);
        Context context = parent.getContext();
        if (context instanceof LifecycleOwner) {
            ((LifecycleOwner) context).getLifecycle().addObserver(holder);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        holder.bindData(getItem(position));
    }

    public List<T> getDataList() {
        return dataList;
    }

    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return itemTypePool.getItemType(getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public int notifyDataSetChanged(List<T> dataList) {
        return notifyDataSetChanged(dataList, true);
    }

    public int notifyDataSetChanged(List<T> dataList, boolean isRefresh) {
        if (dataList == null) {
            return this.dataList.size();
        }
        if (isRefresh) {
            this.dataList.clear();
        }
        this.dataList.addAll(dataList);
        super.notifyDataSetChanged();
        return this.dataList.size();
    }

    public void register(
            @NonNull Class<?> dataClass,
            @NonNull Class<? extends BaseViewHolder> holderClass,
            @LayoutRes int layoutId) {
        itemTypePool.registerType(ItemType.create(dataClass, holderClass, layoutId));
    }

    public <D> void register(
            @NonNull Class<D> dataClass, @NonNull Linker<D> linker) {
        itemTypePool.registerType(ItemType.create(dataClass, linker));
    }

}
