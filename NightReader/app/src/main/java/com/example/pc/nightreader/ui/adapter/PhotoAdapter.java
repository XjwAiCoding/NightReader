package com.example.pc.nightreader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Photo;
import com.example.pc.nightreader.ui.adapter.viewholder.PhotoItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     List<Photo> mList=new ArrayList<>();
     Context mContext;
    public PhotoAdapter() {
    }

    public PhotoAdapter(List<Photo> mList, Context pContext) {
        this.mList = mList;
        mContext=pContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_photo,parent,false);
        PhotoItemViewHolder _holder=new PhotoItemViewHolder(view);
        return _holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if ( holder instanceof  PhotoItemViewHolder){
                ((PhotoItemViewHolder) holder).setPhoto(mContext,mList.get(position)).setDate(mContext,mList.get(position));

            }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
