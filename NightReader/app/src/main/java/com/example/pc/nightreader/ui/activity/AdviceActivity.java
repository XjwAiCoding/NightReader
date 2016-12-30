package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;
import com.example.pc.nightreader.widget.ViewFinder;

public class AdviceActivity extends BaseActivity  implements View.OnClickListener{
    private ImageView mBack;//返回键
    private  ImageView mDayOrNight;//日夜间模式切换
    private  boolean isClickNightChange=false;//是否点击了切换夜间模式

    private EditText mFeedEdit;//反馈框
    private EditText mNumberEdit;//反馈框
    private Button mFeedCommit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        initData();
        initView();
        registerListener();
    }

    @Override
    public void initView() {
        mBack= ViewFinder.getView(this, R.id.back);
        mDayOrNight=ViewFinder.getView(this, R.id.DayOrNight);
        mFeedEdit=ViewFinder.getView(this, R.id.feedEdit);
        mNumberEdit=ViewFinder.getView(this, R.id.numberEdit);
        mFeedCommit=ViewFinder.getView(this, R.id.feedCommit);
    }

    @Override
    public void initData() {

    }

    /** 注册监听器 */
    private void registerListener()
    {
        mBack.setOnClickListener(this);
        mDayOrNight.setOnClickListener(this);
        mFeedCommit.setOnClickListener(this);
    }

    /** 入口 */
    public  static Intent getIntent(Context pContext){
        Intent _Intent=new Intent(pContext,AdviceActivity.class);
        return  _Intent;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
               finish();
                break;
            case R.id.DayOrNight:
                Toast.makeText(AdviceActivity.this, "日夜间模式切换",Toast.LENGTH_SHORT).show();
                if (isClickNightChange==false){
                    mDayOrNight.setImageResource(R.mipmap.ic_action_button_theme_night);
                    isClickNightChange=true;
                }else {
                    mDayOrNight.setImageResource(R.mipmap.ic_action_button_theme_day);
                    isClickNightChange=false;
                }
                break;
            case R.id.feedCommit:
                feedCommit();
                break;
        }
    }

    /** 反馈提交判断 */
    private void feedCommit() {
        if (mFeedEdit.getText().toString().equals("")&&mNumberEdit.getText().toString().equals("")){
            Toast.makeText(this,"亲，您一个字还没动额！",Toast.LENGTH_SHORT).show();
        }else if (mNumberEdit.getText().toString().equals("")){
            Toast.makeText(this,"亲，您的联系方式忘填了额！",Toast.LENGTH_SHORT).show();
        }else if (mFeedEdit.getText().toString().equals("")){
            Toast.makeText(this,"亲，您的反馈意见忘填了额！",Toast.LENGTH_SHORT).show();
        }else if(!mFeedEdit.getText().equals("")&&!mNumberEdit.getText().equals("")) {
            Toast.makeText(this,"亲，您的反馈意见已提交了，请耐心等耐，我们会在第一时间联系您！",Toast.LENGTH_SHORT).show();
            finish();
        }
    }


}
