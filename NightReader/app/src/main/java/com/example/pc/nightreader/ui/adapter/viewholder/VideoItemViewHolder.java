package com.example.pc.nightreader.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.Video;
import com.example.pc.nightreader.widget.ViewFinder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;



/**
 * Created by xujiawei on 2016/12/9.
 */

public class VideoItemViewHolder extends RecyclerView.ViewHolder {

    public JCVideoPlayer videoController;//播放器
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
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(pContext));//这一步必须要加，不然jcvideoplayer-lib项目无法播放视频
        videoController.setUp(pVideo.getUrl(),pVideo.getCover(), pVideo.getTitle());
        /* videoController.setUp("http://2449.vod.myqcloud.com/2449_ded7b566b37911e5942f0b208e48548d.f20.mp4",
                "http://p.qpic.cn/videoyun/0/2449_ded7b566b37911e5942f0b208e48548d_2/640",
                "一行代码实现视频播放");*/

        return this;
    }

    //设置播放来源
    public VideoItemViewHolder setSource(Context pContext, Video pVideo){
           mSource.setText(pVideo.getVideosource());
        return this;
    }
    //设置播放次数
    public VideoItemViewHolder setCount(Context pContext, Video pVideo){
        mCount.setText("已播放"+pVideo.getPlayCount()+"次");
        return this;
    }

}
