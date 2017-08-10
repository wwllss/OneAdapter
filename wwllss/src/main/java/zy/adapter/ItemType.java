package zy.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */

class ItemType {

    private int layoutId;

    private Class<?> dataClass;

    private Class<? extends BaseViewHolder> holderClass;

    private Linker<?> linker;

    private ItemType(@NonNull Class<?> dataClass, @NonNull Class<? extends BaseViewHolder> holderClass, @LayoutRes int layoutId) {
        this.dataClass = dataClass;
        this.holderClass = holderClass;
        this.layoutId = layoutId;
    }

    private ItemType(@NonNull Class<?> dataClass, Linker<?> linker) {
        this.dataClass = dataClass;
        this.linker = linker;
    }

    static ItemType create(@NonNull Class<?> dataClass, @NonNull Class<? extends BaseViewHolder> holderClass, @LayoutRes int layoutId) {
        return new ItemType(dataClass, holderClass, layoutId);
    }

    static <T> ItemType create(@NonNull Class<T> dataClass, @NonNull Linker<T> linker) {
        return new ItemType(dataClass, linker);
    }

    int getLayoutId() {
        return layoutId;
    }

    Class<?> getDataClass() {
        return dataClass;
    }

    Class<? extends BaseViewHolder> getHolderClass() {
        return holderClass;
    }

    Linker<?> getLinker() {
        return linker;
    }

    void setLinker(Linker<?> linker) {
        this.linker = linker;
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj == null || !(obj instanceof ItemType)) && (obj == this || dataClass == ((ItemType) obj).dataClass);
    }
}
