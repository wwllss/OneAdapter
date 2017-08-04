package zy.adapter;

import android.view.LayoutInflater;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
public interface ItemTypePool {

    void registerType(ItemType itemType);

    int getItemType(Class<?> dataClass);

    <T> BaseViewHolder<T> newInstance( LayoutInflater inflater,int itemViewType);

}
