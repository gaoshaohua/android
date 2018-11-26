package com.gsh.read.activity.adapter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.gsh.read.R;

import java.util.List;

public class MainListAdapter extends MyBaseAdapter<JSON> {

    public MainListAdapter(Context mContext, List<JSON> mData) {
        super(mContext, mData,R.layout.item_main_layout);
    }

    @Override
    public void convert(ViewHolder holder, JSON json) {
        /**
         ((TextView) holder.getView(R.id.titleTv)).setText(bean.getTitle());
        ((TextView) holder.getView(R.id.descTv)).setText(bean.getDesc());
        ((TextView) holder.getView(R.id.timeTv)).setText(bean.getTime());
        ((TextView) holder.getView(R.id.phoneTv)).setText(bean.getPhone());
         **/
    }
}
