package com.example.pc.nightreader.ui.adapter.viewholder;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pc.nightreader.R;

/**
 * Created by xujiawei on 2016/12/28.
 * 其他频道
 */
public  class OtherViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public OtherViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv);
    }
}