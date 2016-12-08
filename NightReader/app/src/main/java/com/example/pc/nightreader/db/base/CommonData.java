package com.example.pc.nightreader.db.base;

import com.example.pc.nightreader.R;

/**
 * Created by xujiawei on 2016/12/7.
 */

public class CommonData {
    ////程序意外崩溃，取出保存的上次的位置值所需要的key
    public static  final String SAVEDINSTANCESTATE_KEY="savedInstanceState_key";
    public static  final  String[] titles={"首页", "美女","视频","关注"};
    public static  final  int IconUnSelect[]={ R.mipmap.ic_home_normal,R.mipmap.ic_girl_normal,  R.mipmap.ic_video_normal,R.mipmap.ic_care_normal};
    public static final int  IconSelect[]={  R.mipmap.ic_home_selected,R.mipmap.ic_girl_selected, R.mipmap.ic_video_selected,R.mipmap.ic_care_selected};


}

