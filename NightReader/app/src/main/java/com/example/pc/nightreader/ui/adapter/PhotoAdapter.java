package com.example.pc.nightreader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Photo;
import com.example.pc.nightreader.logic.listener.OnItemClickListener;
import com.example.pc.nightreader.ui.adapter.viewholder.PhotoItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/12/9.
 * 图片适配器
 */

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     List<Photo> mList=new ArrayList<>();
     Context mContext;
    private  OnItemClickListener mOnItemClickListener;
    public PhotoAdapter() {
    }

    public PhotoAdapter(List<Photo> pList, Context pContext) {
        mList.addAll(pList);
        mContext=pContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRootView= LayoutInflater.from(mContext).inflate(R.layout.item_photo,parent,false);
        final PhotoItemViewHolder _holder=new PhotoItemViewHolder(mRootView);
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,_holder.getAdapterPosition());
            }
        });
        return _holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if ( holder instanceof  PhotoItemViewHolder){
                //随机设置图片高度
               /* ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
                layoutParams.height = mHeights.get(position);
                holder.itemView.setLayoutParams(layoutParams);*/

                ((PhotoItemViewHolder) holder).setPhoto(mContext,mList.get(position))
                        .setDate(mContext,mList.get(position));

            }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /** 根据点击位置获取相应实体类 */
    public  Photo getItem(int position){
        return  mList.get(position);
    }

    /** 设置点击事件 */
    public void setOnItemClickListener(OnItemClickListener pOnItemClickListener) {
        this.mOnItemClickListener = pOnItemClickListener;
    }

  /* //随机获取图片大小
    public void getPhotoHeight(){
        for(int i=0; i < mList.size();i++){
            //随机的获取一个范围为200-600直接的高度
            mHeights.add((int)(300+Math.random()*400));
        }
    }*/

}
