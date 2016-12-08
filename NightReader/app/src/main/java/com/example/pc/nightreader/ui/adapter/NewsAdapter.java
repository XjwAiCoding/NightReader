package com.example.pc.nightreader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.ui.adapter.viewholder.NewsItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by xujiawei on 2016/7/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;//点击事件接口
    List<News> mData=new ArrayList<>();
    public NewsAdapter(Context context, List<News> pData) {
        this.mContext = context;
        this.mData=pData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_news,parent,false);
        NewsItemViewHolder _holder= new NewsItemViewHolder(view);
        return _holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsItemViewHolder) {
            News _news=mData.get(position);
            //加载图片
         ((NewsItemViewHolder) holder).setTitle(mContext,_news).setImage(mContext,_news);

        }

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public News getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
         this.mOnItemClickListener = mOnItemClickListener;
    }

    //点击事件接口
    public interface  OnItemClickListener {
       void  onItemClick(View view, int position);

    }


}



