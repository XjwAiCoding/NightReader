package com.example.pc.nightreader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Video;
import com.example.pc.nightreader.ui.adapter.viewholder.VideoItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/12/9.
 * 视频适配器
 */

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private   View mRootView;
    private Context mContext;
    private List<Video> mList = new ArrayList<>();

    public VideoAdapter() {
    }

    public VideoAdapter(Context pContext, List<Video> pList) {
        mContext = mContext;
        this.mList.addAll(pList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        VideoItemViewHolder _holder = new VideoItemViewHolder(mRootView);
        return _holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoItemViewHolder) {
            //设置视频相关属性
            ((VideoItemViewHolder) holder).setVideo(mContext, mList.get(position)).setSource(mContext, mList.get(position)).setCount(mContext, mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
