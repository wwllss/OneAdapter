package zy.adapter;

import android.view.ViewGroup;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
public interface ItemTypePool {

    void registerType(ItemType itemType);

    <T> int getItemType(T data, int position);

    <T> BaseViewHolder<T> newInstance(ViewGroup parent, int itemViewType);

}
