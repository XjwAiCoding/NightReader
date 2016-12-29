package com.example.pc.nightreader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pc.nightreader.R;
import com.example.pc.nightreader.ui.activity.base.BaseActivity;
import com.example.pc.nightreader.widget.ViewFinder;

public class NightSettingActivity extends BaseActivity  implements View.OnClickListener{

    private ImageView mBack;//返回键
    private  ImageView mDayOrNight;//日夜间模式切换
    private  ImageView mTimeSwitch;
    private LinearLayout mStartTime;
    private LinearLayout mEndTime;
    private  boolean isSwitchOn=false;//是否自动开启了夜间模式
    private  boolean isClickNightChange=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_setting);
        initData();
        initView();
        registerListener();
    }

   /** 注册监听器 */
    private void registerListener() {
        mBack.setOnClickListener(this);
        mDayOrNight.setOnClickListener(this);

        mStartTime.setOnClickListener(this);
        mEndTime.setOnClickListener(this);
        mTimeSwitch.setOnClickListener(this);
    }

    @Override
    public void initView() {
        mBack= ViewFinder.getView(this, R.id.back);
        mDayOrNight=ViewFinder.getView(this, R.id.DayOrNight);
        mStartTime=ViewFinder.getView(this, R.id.startTime);
        mEndTime=ViewFinder.getView(this, R.id.endTime);
        mTimeSwitch=ViewFinder.getView(this, R.id.timeSwitch);

    }

    @Override
    public void initData() {

    }


    /** 入口 */
    public  static  Intent getIntent(Context pContext){
        Intent _Intent=new Intent(pContext,NightSettingActivity.class);
        return  _Intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.DayOrNight:
                //日夜间模式切换
                if (isClickNightChange==false){
                    mDayOrNight.setImageResource(R.mipmap.ic_action_button_theme_night);
                    isClickNightChange=true;
                }else {
                    mDayOrNight.setImageResource(R.mipmap.ic_action_button_theme_day);
                    isClickNightChange=false;
                }
                Toast.makeText(this,"开启夜间模式",Toast.LENGTH_SHORT).show();

                break;
            case R.id.timeSwitch:
                //设置自动夜间模式切换
                if (isSwitchOn==false) {
                    mTimeSwitch.setImageResource(R.mipmap.ic_switch_on);
                    mStartTime.setVisibility(View.VISIBLE);
                    mEndTime.setVisibility(View.VISIBLE);
                    isSwitchOn=true;
                } else {
                    mTimeSwitch.setImageResource(R.mipmap.ic_switch_off);
                    mStartTime.setVisibility(View.GONE);
                    mEndTime.setVisibility(View.GONE);
                    isSwitchOn=false;
                }
                break;
            case R.id.startTime:
                //设置夜间模式开始时间
                break;
            case R.id.endTime:
                //设置夜间模式结束时间
                break;
        }
    }
}
