package com.example.pc.nightreader.utils.file;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 读取Assets下文件
 * @author Yuzhenbei
 *
 */
public class FileAssetsUtil {

	/**
	 * 获取数据
	 * @param pContext   上下文
	 * @param pFileName  地址/名称
	 * @return          String
	 */
	public static String getStringFromAssert(Context pContext, String pFileName){
        String _Content = "";
        try{
            if (Arrays.asList(pContext.getResources().getAssets().list("")).contains(pFileName)){
                InputStream _InputStream = pContext.getResources().getAssets().open(pFileName);
                int _Length = 0;
                ByteArrayOutputStream _OutputStream = new ByteArrayOutputStream();
                while((_Length = _InputStream.read())!= -1){
                    _OutputStream.write(_Length);
                }
                byte[] _Data = _OutputStream.toByteArray();
                _Content = new String(_Data);
                if (_OutputStream != null) _OutputStream.close();
                if (_InputStream != null) _InputStream.close();
            }
         }catch(Exception e){
        	 e.printStackTrace();
        	 _Content = "";
         }
        return _Content; 
	}

	/**
	 * 获取数据
	 * @param pContext  上下文
	 * @param pFileName 地址/名称
	 * @return          String
	 */
	public static InputStream getInputStreamFromAssert(Context pContext, String pFileName){
        InputStream _InputStream = null;
        try{
            if (Arrays.asList(pContext.getResources().getAssets().list("")).contains(pFileName)){
                _InputStream = pContext.getResources().getAssets().open(pFileName);
            }
         }catch(Exception e){
        	 e.printStackTrace();
         }
        return _InputStream;
	}

}
