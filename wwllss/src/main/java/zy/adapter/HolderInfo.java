package zy.adapter;

import android.support.annotation.LayoutRes;

/**
 * @author zhangyuan
 * @date 2017/8/10.
 */
public class HolderInfo {

    @LayoutRes
    final int layoutId;

    final Class<? extends BaseViewHolder> holderClass;

    private HolderInfo(Class<? extends BaseViewHolder> holderClass, int layoutId) {
        this.holderClass = holderClass;
        this.layoutId = layoutId;
    }

    public static HolderInfo info(Class<? extends BaseViewHolder> holderClass, int layoutId) {
        return new HolderInfo(holderClass, layoutId);
    }
}
