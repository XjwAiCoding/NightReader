package com.example.pc.nightreader.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Photo;
import com.example.pc.nightreader.utils.ImageLoaderUtils;
import com.example.pc.nightreader.widget.ViewFinder;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class PhotoItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView mPhoto;
    public TextView   mDate;

    public PhotoItemViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        mPhoto= ViewFinder.getView(itemView, R.id.item_photo);
        mDate=ViewFinder.getView(itemView, R.id.item_date);

    }
    public PhotoItemViewHolder setPhoto(Context pContext, Photo pPhoto){
        ImageLoaderUtils.display(pContext, mPhoto, pPhoto.getUrl());
        return this;
    }

    public PhotoItemViewHolder setDate(Context pContext, Photo pPhoto){
        mDate.setText(pPhoto.getPublicDate());
        return this;
    }
}
