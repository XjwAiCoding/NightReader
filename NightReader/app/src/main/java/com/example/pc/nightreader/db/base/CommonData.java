package com.example.pc.nightreader.db.base;

import com.example.pc.nightreader.R;

import java.util.ArrayList;

/**
 * Created by xujiawei on 2016/12/7.
 */

public class CommonData {
    ////程序意外崩溃，取出保存的上次的位置值所需要的key
    public static  final String SAVEDINSTANCESTATE_KEY="savedInstanceState_key";
    public static  final  String[] titles={"首页", "美女","视频","设置"};
    public static  final  int IconUnSelect[]={ R.mipmap.ic_home_normal,R.mipmap.ic_girl_normal,  R.mipmap.ic_video_normal,R.mipmap.ic_care_normal};
    public static final int  IconSelect[]={  R.mipmap.ic_home_selected,R.mipmap.ic_girl_selected, R.mipmap.ic_video_selected,R.mipmap.ic_care_selected};
    public static  final  String url1="http://api.tianapi.com/social/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url2="http://api.tianapi.com/guonei/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url3="http://api.tianapi.com/world/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url4="http://api.tianapi.com/huabian/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url5="http://api.tianapi.com/tiyu/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url6="http://api.tianapi.com/nba/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url7="http://api.tianapi.com/football/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url8="http://api.tianapi.com/keji/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url9="http://api.tianapi.com/startup/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url10="http://api.tianapi.com/apple/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url11="http://api.tianapi.com/mobile/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url12="http://api.tianapi.com/travel/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url13="http://api.tianapi.com/health/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url14="http://api.tianapi.com/qiwen/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url15="http://api.tianapi.com/vr/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String url16="http://api.tianapi.com/it/?key=6e64995fdca7d5438533a964ae38b486&num=50";
    public static  final  String[] urls={url1,url2,url3,url4,url5,url6,url7,url8,url9,url10,url11,url12,url13,url14,url15};
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
    public static ArrayList<String> getUrlList() {
        ArrayList<String> _urlList=new ArrayList<>();
        for (int i=0;i<CommonData.urls.length;i++){
            _urlList.add(CommonData.urls[i]);
         }
        return _urlList;
    }
}

