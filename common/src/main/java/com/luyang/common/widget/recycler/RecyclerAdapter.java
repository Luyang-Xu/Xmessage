package com.luyang.common.widget.recycler;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luyang.common.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by luyang on 2018/3/6.
 */

@SuppressWarnings({"unused", "unchecked"})
public abstract class RecyclerAdapter<Data> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>> implements View.OnClickListener, View.OnLongClickListener, AdapterCallback {
    private final List<Data> mDataList;
    private AdapterListener mListener;


//构造函数
    public RecyclerAdapter(){
        this(null);
    }

    public RecyclerAdapter(AdapterListener mListener) {
        this(new ArrayList<Data>(),null);
    }

    public RecyclerAdapter(List<Data> mDataList, AdapterListener mListener) {
        this.mDataList = mDataList;
        this.mListener = mListener;
    }

//recyclerView的三个重要的重写方法
    @Override
    public void onBindViewHolder(ViewHolder<Data> holder, int position) {
        Data data = mDataList.get(position);
        holder.bind(data);
    }


    @Override
    public ViewHolder<Data> onCreateViewHolder(ViewGroup parent, int viewType) {
        // 得到LayoutInflater用于把XML初始化为View
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // 把XML id为viewType的文件初始化为一个root View
        View root = inflater.inflate(viewType, parent, false);
        // 通过子类必须实现的方法，得到一个ViewHolder
        ViewHolder<Data> holder = onCreateViewHolder(root, viewType);


        // 设置View的Tag为ViewHolder，进行双向绑定
        root.setTag(R.id.tag_recycler_holder, holder);
        // 设置事件点击
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        // 进行界面注解绑定
        holder.unbinder = ButterKnife.bind(holder, root);
        // 绑定callback
        holder.callback = this;

        return holder;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    /**
     * 得到一个新的ViewHolder
     *
     * @param root     根布局
     * @param viewType 布局类型，其实就是XML的ID
     * @return ViewHolder
     */
    protected abstract ViewHolder<Data> onCreateViewHolder(View root, int viewType);

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position, mDataList.get(position));
    }

    @LayoutRes
    protected abstract int getItemViewType(int position, Data data);


    //增加对集合的操作

    /**
     * 插入一条数据并通知插入
     *
     * @param data Data
     */
    public void add(Data data) {
        mDataList.add(data);
        notifyItemInserted(mDataList.size() - 1);
    }

    /**
     * 插入一堆数据，并通知这段集合更新
     *
     * @param dataList Data
     */
    public void add(Data... dataList) {
        if (dataList != null && dataList.length > 0) {
            int startPos = mDataList.size();
            Collections.addAll(mDataList, dataList);
            notifyItemRangeInserted(startPos, dataList.length);
        }
    }

    /**
     * 插入一堆数据，并通知这段集合更新
     *
     * @param dataList Data
     */
    public void add(Collection<Data> dataList) {
        if (dataList != null && dataList.size() > 0) {
            int startPos = mDataList.size();
            mDataList.addAll(dataList);
            notifyItemRangeInserted(startPos, dataList.size());
        }
    }

    /**
     * 删除操作
     */
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 替换为一个新的集合，其中包括了清空
     *
     * @param dataList 一个新的集合
     */
    public void replace(Collection<Data> dataList) {
        mDataList.clear();
        if (dataList == null || dataList.size() == 0)
            return;
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag(R.id.tag_recycler_holder);
        if (this.mListener != null) {
            // 得到ViewHolder当前对应的适配器中的坐标
            int pos = holder.getAdapterPosition();
            // 回掉方法
            this.mListener.onItemClick(holder, mDataList.get(pos));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag(R.id.tag_recycler_holder);
        if (holder != null) {
            int pos = holder.getAdapterPosition();
            this.mListener.onItemLongClick(holder, mDataList.get(pos));
            return true;
        }
        return false;
    }

    public void setListener(AdapterListener<Data> listener) {
        this.mListener = listener;
    }

    public interface AdapterListener<Data> {
        // 当Cell点击的时候触发
        void onItemClick(RecyclerAdapter.ViewHolder holder, Data data);

        // 当Cell长按时触发
        void onItemLongClick(RecyclerAdapter.ViewHolder holder, Data data);
    }


    public abstract static class ViewHolder<Data> extends RecyclerView.ViewHolder {

        private Unbinder unbinder;
        private AdapterCallback<Data> callback;
        protected Data mData;


        public ViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * 用于绑定数据的触发
         *
         * @param data 绑定的数据
         */
        void bind(Data data) {
            this.mData = data;
            onBind(data);
        }

        /**
         * 当触发绑定数据的时候，的回掉；必须复写
         *
         * @param data 绑定的数据
         */
        protected abstract void onBind(Data data);

        /**
         * Holder自己对自己对应的Data进行更新操作
         *
         * @param data Data数据
         */
        public void updateData(Data data) {
            if (this.callback != null) {
                this.callback.update(data, this);
            }
        }


    }
}
