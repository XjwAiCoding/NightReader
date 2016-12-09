package com.example.pc.nightreader.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Video;
import com.example.pc.nightreader.widget.ViewFinder;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by xujiawei on 2016/12/9.
 */

public class VideoItemViewHolder extends RecyclerView.ViewHolder {

    public JCVideoPlayer videoController;
    public  TextView  mSource;
    public TextView mCount;

    public VideoItemViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        videoController= ViewFinder.getView(itemView, R.id.videocontroller);
        mSource=ViewFinder.getView(itemView, R.id.source);
        mCount=ViewFinder.getView(itemView, R.id.count);
    }

    //设置视频标题，缩略图和视频地址
    public VideoItemViewHolder setVideo(Context pContext, Video pVideo){
        videoController.setUp(pVideo.getUrl(),null, pVideo.getTitle());
        return this;
    }

    //设置播放来源
    public VideoItemViewHolder setSource(Context pContext, Video pVideo){
           mSource.setText(pVideo.getVideosource());
        return this;
    }
    //设置播放次数
    public VideoItemViewHolder setCount(Context pContext, Video pVideo){
        mCount.setText(pVideo.getPlayCount());
        return this;
    }

}
