package com.gsh.read.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mData;
    private int layoutId;
    private LayoutInflater inflater;

    public MyBaseAdapter(Context mContext, List<T> mData, int layoutId) {
        this.mContext = mContext;
        this.mData = mData;
        this.layoutId = layoutId;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<T> getmData() {
        return mData;
    }

    public void setmData(List<T> mData) {
        this.mData = mData;
    }

    public void addmData(List<T> mData) {
        this.mData.addAll(mData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
        convert(holder,getItem(position));
        return holder.getConvertView();
    }

    //将convert方法公布出去
    public abstract void convert(ViewHolder holder, T t);
}
