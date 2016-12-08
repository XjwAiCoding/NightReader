package com.example.pc.nightreader.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.utils.ImageLoaderUtils;


/**
 * Created by xujiawei on 2016/10/10.
 * 列表项布局
 */

public class NewsItemViewHolder extends RecyclerView.ViewHolder  {

    public TextView mTitle;
    public ImageView mNewsImg;

    public NewsItemViewHolder(View itemView) {
        super(itemView);
        initView();

    }


    public void initView() {
        mTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        mNewsImg = (ImageView) itemView.findViewById(R.id.ivNews);

    }

      public NewsItemViewHolder setImage(Context pContext, News pNews){
        ImageLoaderUtils.display(pContext, mNewsImg, pNews.getPicSmall());
          return this;
       }

     public NewsItemViewHolder setTitle(Context pContext, News pNews){
          mTitle.setText(pNews.getName());
        return this;
    }
}