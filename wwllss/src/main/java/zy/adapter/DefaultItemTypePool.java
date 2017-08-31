package zy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhangyuan
 * @date 2017/8/4.
 */
class DefaultItemTypePool implements ItemTypePool {

    private final List<ItemType> itemTypeList = new ArrayList<>();

    @Override
    public void registerType(@NonNull ItemType itemType) {
        removeIfExists(itemType);
        Linker<?> linker = itemType.getLinker();
        if (linker == null) {
            itemTypeList.add(itemType);
        } else {
            HolderInfo[] holderInfoList = linker.getHolderInfoList();
            for (HolderInfo holderInfo : holderInfoList) {
                ItemType newItemType = ItemType.create(
                        itemType.getDataClass(),
                        holderInfo.holderClass,
                        holderInfo.layoutId
                );
                newItemType.setLinker(linker);
                itemTypeList.add(newItemType);
            }
        }
    }

    private void removeIfExists(@NonNull ItemType itemType) {
        Iterator<ItemType> iterator = itemTypeList.iterator();
        while (iterator.hasNext()) {
            ItemType next = iterator.next();
            if (itemType.equals(next)) {
                iterator.remove();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> int getItemType(@NonNull T data, int position) {
        for (int i = 0; i < itemTypeList.size(); i++) {
            ItemType itemType = itemTypeList.get(i);
            if (itemType.getDataClass() == data.getClass()) {
                Linker<Object> linker = (Linker<Object>) itemType.getLinker();
                return i + (linker == null ? 0 : linker.linkHolder(data, position));
            }
        }
        throw new RuntimeException("error data ---> " + data.getClass().toString());
    }

    @Override
    public <T> BaseViewHolder<T> newInstance(ViewGroup parent, int itemViewType) {
        ItemType itemType = itemTypeList.get(itemViewType);
        Class<? extends BaseViewHolder> holderClass = itemType.getHolderClass();
        int layoutId = itemType.getLayoutId();
        try {
            Constructor<? extends BaseViewHolder> constructor = holderClass.getDeclaredConstructor(Context.class, View.class);
            constructor.setAccessible(true);
            Context context = parent.getContext();
            return constructor.newInstance(context, LayoutInflater.from(context).inflate(layoutId, parent, false));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
