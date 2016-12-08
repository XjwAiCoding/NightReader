package com.example.pc.nightreader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.preference.PreferenceManager;

/**
 * SharedPreferences数据储存
 *
 * Created by yuzhenbei.
 */
public class SPreferenceUtil {
    /** 创建一个特殊的instance变量来充当锁 */
    private final byte[] LOCK = new byte[0];
    /** SDK中使用SharedPreferences保存数据的文件名称 */
    public static final String SDK_SP_FILE_NAME = "cpu_bench_mark_sp";
    /** SharedPreferences */
    private SharedPreferences mShardPreferences = null;
    /** SPreferenceUtil */
    private volatile static SPreferenceUtil mSharePreferenceUtil = null;

    /**
     * 构造
     * @param pContext Context
     */
    public SPreferenceUtil(Context pContext){
        //使用系统的
        mShardPreferences = PreferenceManager.getDefaultSharedPreferences(pContext.getApplicationContext());
        //mShardPreferences = pContext.getApplicationContext().getSharedPreferences(pContext.getPackageName() + SDK_SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * SPreferenceUtil 单例模式调用
     *
     * @param pContext Context
     *
     * @return SPreferenceUtil
     */
    public static SPreferenceUtil getInstance(Context pContext) {
        if (mSharePreferenceUtil == null) {
            synchronized (SPreferenceUtil.class){
                if (mSharePreferenceUtil == null) {
                    mSharePreferenceUtil = new SPreferenceUtil(pContext.getApplicationContext());
                }
            }
        }
        return mSharePreferenceUtil;
    }

    /**
     * SharedPreferences
     *
     * @return SharedPreferences
     */
    private SharedPreferences getSharedPreference() {
        return mShardPreferences;
    }

    /**
     * SharedPreferences.Editor apply()
     *
     * @param editor
     */
    public static void applyToEditor(Editor editor) {
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO){
                editor.apply();
            }else {
                editor.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /**
     * get Editor
     *
     * @return
     */
    public Editor getEditor(){
        return getSharedPreference().edit();
    }

    /**
     * Save Boolean Value
     *
     * @param pKey Key
     * @param pValue Value
     */
    public void saveBooleanValue(String pKey, boolean pValue) {
        synchronized(LOCK){
            Editor _Editor = getEditor();
            _Editor.putBoolean(pKey, pValue);
            applyToEditor(_Editor);
        }
    }

    /**
     * Save Int Value
     *
     * @param pKey Key
     * @param pValue Value
     */
    public void saveIntValue(String pKey, int pValue) {
        synchronized(LOCK){
            Editor _Editor = getEditor();
            _Editor.putInt(pKey, pValue);
            applyToEditor(_Editor);
        }
    }

    /**
     * Save Float Value
     *
     * @param pKey Key
     * @param pValue Value
     */
    public void saveFloatValue(String pKey, float pValue) {
        synchronized(LOCK){
            Editor _Editor = getEditor();
            _Editor.putFloat(pKey, pValue);
            applyToEditor(_Editor);
        }
    }

    /**
     * Save Long Value
     *
     * @param pKey Key
     * @param pValue Value
     */
    public void saveLongValue(String pKey, Long pValue) {
        synchronized(LOCK){
            Editor _Editor = getEditor();
            _Editor.putLong(pKey, pValue);
            applyToEditor(_Editor);
        }
    }

    /**
     * Save String Value
     *
     * @param pKey Key
     * @param pValue Value
     */
    public void saveStringValue(String pKey, String pValue) {
        synchronized(LOCK){
            Editor _Editor = getEditor();
            _Editor.putString(pKey, pValue);
            applyToEditor(_Editor);
        }
    }

    /**
     * get Int
     *
     * @param pKey Key
     * @param pDefValue DefValue
     * @return Int
     */
    public int getIntValue(String pKey, int pDefValue) {
        return getSharedPreference().getInt(pKey, pDefValue);
    }

    /**
     * get Boolean
     *
     * @param pKey Key
     * @param pDefValue DefValue
     * @return Boolean
     */
    public boolean getBooleanValue(String pKey, boolean pDefValue) {
        return getSharedPreference().getBoolean(pKey, pDefValue);
    }

    /**
     * get Float
     *
     * @param pKey Key
     * @param pDefValue DefValue
     * @return Float
     */
    public float getFloatValue(String pKey, float pDefValue) {
        return getSharedPreference().getFloat(pKey, pDefValue);
    }

    /**
     * get Long
     *
     * @param pKey Key
     * @param pDefValue DefValue
     * @return Long
     */
    public long getLongValue(String pKey, long pDefValue) {
        return getSharedPreference().getLong(pKey, pDefValue);
    }

    /**
     * get String
     *
     * @param pKey Key
     * @param pDefValue DefValue
     * @return String
     */
    public String getStringValue(String pKey, String pDefValue){
        return getSharedPreference().getString(pKey, pDefValue);
    }

    /**
     * remove Value
     *
     * @param pKey StringKey
     */
    public void removeValue(String pKey){
        Editor _Editor = getEditor();
        _Editor.remove(pKey);
        applyToEditor(_Editor);
    }

    /**
     * contains
     *
     * @param pKey
     * @return
     */
    public boolean contains(String pKey){
        return getSharedPreference().contains(pKey);
    }

}
