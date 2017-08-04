package zy.adapter;

import android.support.annotation.LayoutRes;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */

public class ItemType {

    @LayoutRes
    private int layoutId;

    private Class<?> dataClass;

    private Class<? extends BaseViewHolder> holderClass;

    public ItemType(int layoutId, Class<?> dataClass, Class<? extends BaseViewHolder> holderClass) {
        this.layoutId = layoutId;
        this.dataClass = dataClass;
        this.holderClass = holderClass;
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

    @Override
    public boolean equals(Object obj) {
        return !(obj == null || !(obj instanceof ItemType)) && (obj == this || dataClass == ((ItemType) obj).dataClass);
    }
}
