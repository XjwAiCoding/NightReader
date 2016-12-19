package com.example.pc.nightreader.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pc.nightreader.R;


/**
 * Created by xujiawei on 2016/10/10.
 */
public class ImageLoaderUtils {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
     }

    /**三个参数分别是：上下文对象，Imaged对象，图片来源Url*/
    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }

      Glide.with(context).load(url).placeholder(R.mipmap.jiazai)
                .error(R.mipmap.shibai).crossFade().into(imageView);
    }

    public static void displayPhoto(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
      Glide.with(context).load(url).crossFade().into(imageView);

    }

}
