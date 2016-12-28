package com.example.pc.nightreader.ui.adapter.viewholder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pc.nightreader.R;

/**
 * Created by xujiawei on 2016/12/28.
 * 我的频道  标题部分
 */
public  class MyChannelHeaderViewHolder extends RecyclerView.ViewHolder {
    public  TextView tvBtnEdit;

    public MyChannelHeaderViewHolder(View itemView) {
        super(itemView);
        tvBtnEdit = (TextView) itemView.findViewById(R.id.tv_btn_edit);
    }
}