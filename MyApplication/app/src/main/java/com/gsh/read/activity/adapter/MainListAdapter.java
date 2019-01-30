package com.gsh.read.activity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.gsh.read.R;
import com.gsh.read.common.vo.response.BookFormVo;

import java.util.List;

public class MainListAdapter extends MyBaseAdapter<BookFormVo> {

    public MainListAdapter(Context mContext, List<BookFormVo> mData) {
        super(mContext, mData,R.layout.item_main_layout);
    }

    @Override
    public void convert(ViewHolder holder, BookFormVo vo) {
        /****/
         ((TextView) holder.getView(R.id.tv_cname)).setText("户名："+vo.getUserName());
        ((TextView) holder.getView(R.id.tv_ccode)).setText("户号："+vo.getConsNo());
        ((TextView) holder.getView(R.id.tv_bcname)).setText("表册："+vo.getBcName());
        ((TextView) holder.getView(R.id.tv_address)).setText("抄表线路："+vo.getSbAddr());
        if(vo.getConsNo().equals("001646")){
            ((TextView) holder.getView(R.id.tv_status)).setVisibility(View.GONE);
        }else{
            ((TextView) holder.getView(R.id.tv_status)).setText("已抄");
        }
    }
}
