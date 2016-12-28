package com.example.pc.nightreader.ui.adapter.viewholder;

/**
 * Created by xujiawei on 2016/12/28.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.logic.listener.OnDragVHListener;

/**
 * 我的频道
 */
public  class MyViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener {
    public TextView textView;
    public ImageView imgEdit;

    public MyViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv);
        imgEdit = (ImageView) itemView.findViewById(R.id.img_edit);
    }

    /**
     * item 被选中时
     */
    @Override
    public void onItemSelected() {
        textView.setBackgroundResource(R.drawable.bg_channel_p);
    }

    /**
     * item 取消选中时
     */
    @Override
    public void onItemFinish() {
        textView.setBackgroundResource(R.drawable.bg_channel);
    }
}