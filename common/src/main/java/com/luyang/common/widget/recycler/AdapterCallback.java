package com.luyang.common.widget.recycler;

/**
 * Created by luyang on 2018/3/6.
 */

public interface AdapterCallback<Data> {
    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder) ;
}
