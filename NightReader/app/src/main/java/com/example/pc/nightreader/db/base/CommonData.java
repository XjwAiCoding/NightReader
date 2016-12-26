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


    /**
     * 社会新闻： http://api.tianapi.com/social/?key=6e64995fdca7d5438533a964ae38b486&num=50
     国内新闻   http://api.tianapi.com/guonei/?key=6e64995fdca7d5438533a964ae38b486&num=50
     国际新闻： http://api.tianapi.com/world/?key=6e64995fdca7d5438533a964ae38b486&num=50

     娱乐新闻   http://api.tianapi.com/huabian/?key=6e64995fdca7d5438533a964ae38b486&num=50
     体育新闻   http://api.tianapi.com/tiyu/?key=6e64995fdca7d5438533a964ae38b486&num=50
     NBA新闻    http://api.tianapi.com/nba/?key=6e64995fdca7d5438533a964ae38b486&num=50

     足球新闻   http://api.tianapi.com/football/?6e64995fdca7d5438533a964ae38b486&num=50
     科技新闻   http://api.tianapi.com/keji/?key=6e64995fdca7d5438533a964ae38b486&num=50
     创业新闻   http://api.tianapi.com/startup/?key=6e64995fdca7d5438533a964ae38b486&num=50

     苹果新闻   http://api.tianapi.com/apple/?key=6e64995fdca7d5438533a964ae38b486&num=50
     移动互联   http://api.tianapi.com/mobile/?key=6e64995fdca7d5438533a964ae38b486&num=50
     旅游资讯   http://api.tianapi.com/travel/?key=6e64995fdca7d5438533a964ae38b486&num=50
     健康知识   http://api.tianapi.com/health/?key=6e64995fdca7d5438533a964ae38b486&num=50

     奇闻异事   http://api.tianapi.com/qiwen/?key=6e64995fdca7d5438533a964ae38b486&num=50
     VR科技     http://api.tianapi.com/vr/?key=6e64995fdca7d5438533a964ae38b486&num=50
     IT资讯     http://api.tianapi.com/it/?key=6e64995fdca7d5438533a964ae38b486&num=50
     *
     */

}

