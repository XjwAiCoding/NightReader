package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.entity.News;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;
import com.example.pc.nightreader.utils.ImageLoaderUtils;
import com.example.pc.nightreader.widget.ViewFinder;


public class DetailActivity extends BaseActivity  implements View.OnClickListener {
    ImageView mImageView;
    TextView mTextView;
    News mNews;
    int position;
    ImageView mDetailBack;
    FloatingActionButton mDatailShare;//分享按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initData();
        initView();
    }

    @Override
    public void initView() {
        mDetailBack=ViewFinder.getView(this, R.id.detail_back);
        mDatailShare=ViewFinder.getView(this, R.id.detail_share);
        mImageView= ViewFinder.getView(this, R.id.detail_photo);
        mTextView=ViewFinder.getView(this, R.id.detail_text);
        mDetailBack.setOnClickListener(this);
        mDatailShare.setOnClickListener(this);
        //加载图片和新闻内容
        ImageLoaderUtils.displayPhoto(this,mImageView,mNews.getPicSmall());
        mTextView.setText(mNews.getDescription()+"\n1.使用权重的前提一般是给View的宽或者高的大小设置为0dp，然后系统根据上面的权重规则来计算View应该占据的空间。但是很多情况下，如果给View设置了match_parent的属性，那么上面计算权重时则不是通常的正比，而是反比，也就是权重值大的反而占据空间小\n" +
                "2.对于所有的View默认的权重是0，如果只设置了一个View的权重大于0，则该View将占据除去别的View本身占据的空间的所有剩余空间。因此这里设置EditText的权重为1，使其能够占据除了按钮之外的所有空间。\n"+"1.使用权重的前提一般是给View的宽或者高的大小设置为0dp，然后系统根据上面的权重规则来计算View应该占据的空间。但是很多情况下，如果给View设置了match_parent的属性，那么上面计算权重时则不是通常的正比，而是反比，也就是权重值大的反而占据空间小\n" +
                "2.对于所有的View默认的权重是0，如果只设置了一个View的权重大于0，则该View将占据除去别的View本身占据的空间的所有剩余空间。因此这里设置EditText的权重为1，使其能够占据除了按钮之外的所有空间。\n"+"1.使用权重的前提一般是给View的宽或者高的大小设置为0dp，然后系统根据上面的权重规则来计算View应该占据的空间。但是很多情况下，如果给View设置了match_parent的属性，那么上面计算权重时则不是通常的正比，而是反比，也就是权重值大的反而占据空间小\n" +
                "2.对于所有的View默认的权重是0，如果只设置了一个View的权重大于0，则该View将占据除去别的View本身占据的空间的所有剩余空间。因此这里设置EditText的权重为1，使其能够占据除了按钮之外的所有空间。\n");
    }

    @Override
    public void initData() {
       mNews= getIntent().getParcelableExtra("news");
       position=getIntent().getIntExtra("position",0);
    }


    /** 入口 */
    public static  Intent getIntent(Context pContext){
        Intent _intent = new Intent(pContext, DetailActivity.class);
        return _intent;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_back:
                finish();
                break;
            case R.id.detail_share:
                break;
        }
    }
}
