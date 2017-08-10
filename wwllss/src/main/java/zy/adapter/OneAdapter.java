package zy.adapter;

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
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return itemTypePool.newInstance(LayoutInflater.from(parent.getContext()), viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
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
            return 0;
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
