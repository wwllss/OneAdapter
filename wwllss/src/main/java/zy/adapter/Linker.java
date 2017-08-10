package zy.adapter;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

/**
 * @author zhangyuan
 * @date 2017/8/10.
 */
public interface Linker<T> {

    @NonNull
    HolderInfo[] getHolderInfoList();

    @IntRange(from = 0)
    int linkHolder(T data, int position);

}
