package zy.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */

public class ItemType {

    private int layoutId;

    private Class<?> dataClass;

    private Class<? extends BaseViewHolder> holderClass;

    public ItemType(@LayoutRes int layoutId, @NonNull Class<?> dataClass, @NonNull Class<? extends BaseViewHolder> holderClass) {
        this.layoutId = layoutId;
        this.dataClass = dataClass;
        this.holderClass = holderClass;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public Class<?> getDataClass() {
        return dataClass;
    }

    public Class<? extends BaseViewHolder> getHolderClass() {
        return holderClass;
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj == null || !(obj instanceof ItemType)) && (obj == this || dataClass == ((ItemType) obj).dataClass);
    }
}
