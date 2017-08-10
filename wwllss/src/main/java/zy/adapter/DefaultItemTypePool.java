package zy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
class DefaultItemTypePool implements ItemTypePool {

    private final List<ItemType> itemTypeList = new ArrayList<>();

    @Override
    public void registerType(@NonNull ItemType itemType) {
        if (itemTypeList.contains(itemType)) {
            return;
        }
        itemTypeList.add(itemType);
    }

    @Override
    public <T> int getItemType(@NonNull Class<T> dataClass) {
        for (int i = 0; i < itemTypeList.size(); i++) {
            if (itemTypeList.get(i).getDataClass().isAssignableFrom(dataClass)) {
                return i;
            }
        }
        throw new RuntimeException("error data ---> " + dataClass.toString());
    }

    @Override
    public <T> BaseViewHolder<T> newInstance(LayoutInflater inflater, int itemViewType) {
        ItemType itemType = itemTypeList.get(itemViewType);
        Class<? extends BaseViewHolder> holderClass = itemType.getHolderClass();
        int layoutId = itemType.getLayoutId();
        try {
            Constructor<? extends BaseViewHolder> constructor = holderClass.getDeclaredConstructor(Context.class, View.class);
            constructor.setAccessible(true);
            return constructor.newInstance(inflater.getContext(), inflater.inflate(layoutId, null, false));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
