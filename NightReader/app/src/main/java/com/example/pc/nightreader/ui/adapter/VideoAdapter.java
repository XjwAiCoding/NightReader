package com.example.pc.nightreader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.pc.nightreader.entity.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiawei on 2016/12/9.
 * 视频适配器
 */

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
