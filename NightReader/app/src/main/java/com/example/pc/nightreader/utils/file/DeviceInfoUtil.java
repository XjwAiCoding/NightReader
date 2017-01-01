package com.example.pc.nightreader.utils.file;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.regex.Pattern;

/**
 * 获取手机设备相关信息
 *
 * Created by yuzhenbei on 2015/7/3.
 */
public class DeviceInfoUtil {

    /**
     * 获取手机是否Root
     *
     * @return
     */
    public static boolean isRoot() {
        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return  false;
        }
    }

    /**
     * 获取 AndroidId
     *
     * @param pContext Context
     *
     * @return
     */
    public static String getAndroidId(Context pContext) {
        String _AndroidID = "";
        try {
            try {
                _AndroidID = Settings.Secure.getString(pContext.getContentResolver(), Settings.Secure.ANDROID_ID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _AndroidID;
    }

    /**
     * IMEI号(DeviceId)，唯一的数字，标识了 GSM 和 UMTS网络里的唯一一个手机
     *
     * @param pContext Context
     * @return DeviceId
     */
    public static String getIMEI(Context pContext) {
        try {
            return ((TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * IESI号，唯一的数字，标识了GSM和UMTS 网络里的唯一一个用户
     *
     * @param pContext Context
     * @return IMSI
     */
    public static String getIMSI(Context pContext) {
        try {
            return ((TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取MCC
     * @param pContext Context
     * @return MCC
     */
    public static String getMCC(Context pContext) {
        String _MCC = "";
        try {
            String _networkOperator = ((TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperator();
            if (!TextUtils.isEmpty(_networkOperator)) {
                if (_networkOperator.length() >= 5) {
                    _MCC =_networkOperator.substring(0, 3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _MCC;
    }

    /**
     * 获取MNC
     * @param pContext Context
     * @return MNC
     */
    public static String getMNC(Context pContext) {
        String _MNC = "";
        try {
            String _networkOperator = ((TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperator();
            if (!TextUtils.isEmpty(_networkOperator)) {
                if (_networkOperator.length() >= 5) {
                    _MNC = _networkOperator.substring(3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _MNC;
    }

    /**
     * 获取SimMCC
     * @param pContext Context
     * @return SimMCC
     */
    public static String getSimMCC(Context pContext) {
        String _MCC = "";
        try {
            String _networkOperator = ((TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE)).getSimOperator();
            if (!TextUtils.isEmpty(_networkOperator)) {
                if (_networkOperator.length() >= 5) {
                    _MCC =_networkOperator.substring(0, 3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _MCC;
    }

    /**
     * 获取SimMNC
     * @param pContext Context
     * @return SimMNC
     */
    public static String getSimMNC(Context pContext) {
        String _MNC = "";
        try {
            String _networkOperator = ((TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE)).getSimOperator();
            if (!TextUtils.isEmpty(_networkOperator)) {
                if (_networkOperator.length() >= 5) {
                    _MNC = _networkOperator.substring(3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _MNC;
    }

    /**
     * 获取GsmCell的Lac
     *
     * @param pContext Context
     * @return GsmCell的Lac
     */
    public static String getGsmCellLac(Context pContext) {
        String _GsmCellLac  = "";
        try {
            TelephonyManager _TelephonyManager = (TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE);
            CellLocation _CellLocation = _TelephonyManager.getCellLocation();
            if (null != _CellLocation){
                if (_CellLocation instanceof GsmCellLocation){
                    GsmCellLocation _GsmCellLocation = (GsmCellLocation) _TelephonyManager.getCellLocation();
                    //_GsmCellLac = (_GsmCellLocation.getLac() & 0xffff) + "";
                    _GsmCellLac = _GsmCellLocation.getLac() + "";
                }else if (_CellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation _CdmaCellLocation = (CdmaCellLocation) _TelephonyManager.getCellLocation();
                    _GsmCellLac = _CdmaCellLocation.getNetworkId() + "";
                }
            }
        } catch (Exception e) {
            _GsmCellLac = "";
        } catch (Throwable throwable){
            _GsmCellLac = "";
        }
        return _GsmCellLac;
    }

    /**
     * 获取GsmCell的Lac
     *
     * @param pContext Context
     * @return GsmCell的Cid
     */
    public static String getGsmCellCid(Context pContext) {
        String _GsmCellCid  = "";
        try {
            TelephonyManager _TelephonyManager = (TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE);
            CellLocation _CellLocation = _TelephonyManager.getCellLocation();
            if (null != _CellLocation){
                if (_CellLocation instanceof GsmCellLocation){
                    GsmCellLocation _GsmCellLocation = (GsmCellLocation) _TelephonyManager.getCellLocation();
                    //_GsmCellCid = (_GsmCellLocation.getCid() & 0xffff) + "";
                    _GsmCellCid = _GsmCellLocation.getCid() + "";
                }else if (_CellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation _CdmaCellLocation = (CdmaCellLocation) _TelephonyManager.getCellLocation();
                    _GsmCellCid = _CdmaCellLocation.getBaseStationId() + "";
                }
            }

        } catch (Exception e) {
            _GsmCellCid = "";
        } catch (Throwable throwable){
            _GsmCellCid = "";
        }
        return _GsmCellCid;
    }

    /**
     * SDK版本(SDKVersionNumber)
     *
     * @return
     */
    public static int getSDKVersionNumber() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取系统版本号 E.g., "4.2.2"
     *
     * @return
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 手机品牌(Brand)
     *
     * @return
     */
    public static String getBrand() {
        return  Build.BRAND;
    }

    /**
     * 手机型号(MobileModel)
     *
     * @return
     */
    public static String getMobileModel() {
        return Build.MODEL;
    }

    /**
     * 手机号码(MobileNumber)
     *
     * @return
     */
    public static String getMobileNumber(Context pContext) {
        try {
            return ((TelephonyManager)pContext.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取手机CPU核心数
     * @return
     */
    public static int getCPUNumCores() {
        // Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                // Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName()))
                {
                    return true;
                }
                return false;
            }
        }

        try {
            // Get directory containing CPU info
            File _dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            File[] _files = _dir.listFiles(new CpuFilter());
            // Return the number of cores (virtual CPU devices)
            return _files.length;
        }
        catch (Exception e) {
            e.printStackTrace();
            // Default to return 1 core
            return 1;
        }
    }

    /**
     * 外部存储是否挂载
     * @return true or false
     */
    public static boolean isSDCardMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 外部存储是否存在
     * @return true or false
     */
    public static boolean hasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 外部存储是否是只读
     * @return true or false
     */
    public static boolean isSDCardMountedReadOnly() {
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState());
    }

    /**
     * 获得SDCard总大小
     * @return size  (long)
     */
    @SuppressWarnings("deprecation")
    public static long getSDCardTotalSize() {
        if (DeviceInfoUtil.hasSdcard()) {
            File _FileExternalStorage = Environment.getExternalStorageDirectory();
            StatFs _StatFs = new StatFs(_FileExternalStorage.getPath());
            long _BlockSize, _TotalBlocks;
            _BlockSize = _StatFs.getBlockSize();
            _TotalBlocks = _StatFs.getBlockCount();
            return _BlockSize * _TotalBlocks;
        }else {
            return 0;
        }
    }

    /**
     * 获得SDCard剩余容量(可用大小 )
     * @return size  (long)
     */
    @SuppressWarnings("deprecation")
    public static long getSDCardAvailableSize() {
        if (DeviceInfoUtil.hasSdcard()) {
            File _FileExternalStorage = Environment.getExternalStorageDirectory();
            StatFs _StatFs = new StatFs(_FileExternalStorage.getPath());
            long _BlockSize, _AvailableBlocks;
            _BlockSize = _StatFs.getBlockSize();
            _AvailableBlocks = _StatFs.getAvailableBlocks();
            return _BlockSize * _AvailableBlocks;
        }else {
            return 0;
        }
    }


    /**
     * 获得机身内存总大小
     * @return size  (long)
     */
    @SuppressWarnings("deprecation")
    public long getRomTotalSize() {
        File _FileDataDirectory = Environment.getDataDirectory();
        StatFs _StatFs = new StatFs(_FileDataDirectory.getPath());
        long _BlockSize, _TotalBlocks;
        _BlockSize = _StatFs.getBlockSize();
        _TotalBlocks = _StatFs.getBlockCount();
        return _BlockSize * _TotalBlocks;
    }

    /**
     * 获得机身可用内存
     * @return  size  (long)
     */
    @SuppressWarnings("deprecation")
    public static long getRomAvailableSize() {
        File _FileDataDirectory = Environment.getDataDirectory();
        StatFs _StatFs = new StatFs(_FileDataDirectory.getPath());
        long _BlockSize, _AvailableBlocks;
        _BlockSize = _StatFs.getBlockSize();
        _AvailableBlocks = _StatFs.getAvailableBlocks();
        return _BlockSize * _AvailableBlocks;
    }

    /**
     * 获得设备当前的语言。
     *
     * @param pContext Context对象，不可为null。
     * @return 当前语言的代码。如果取不到则返回长度为0的String对象。
     */
    public static String getLanguage(Context pContext) {
        try {
            return pContext.getResources().getConfiguration().locale.getLanguage();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获得设备当前的国家或地区代码。
     *
     * @param pContext Context
     * @return 当前国家或城市代码。如果取不到则返回长度为0的String对象。
     */
    public static String getCountry(Context pContext) {
        try {
            return pContext.getResources().getConfiguration().locale.getCountry();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获得设备当前的语言_国家或地区代码。
     *
     * @param pContext Context对象
     * @return 语言_国家或地区代码
     */
    public static String getLocale(Context pContext) {
        try {
            return pContext.getResources().getConfiguration().locale.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getResolution(Context pContext) {
        String _Resolution;

        try {
            _Resolution = pContext.getResources().getDisplayMetrics().widthPixels + " x " + pContext.getResources().getDisplayMetrics().heightPixels;
        } catch (Exception e) {
            _Resolution = "";
        }

        return _Resolution;
    }

    public static float getDensity(Context pContext) {
        try {
            return pContext.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getMemorySize(Context context) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        String ret = "";
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            arrayOfString = str2.split("\\s+");
            localBufferedReader.close();

            long initial_memory = Integer.valueOf(
                    arrayOfString[1]).intValue() / 1024;// 获得系统总内存，单位是KB，除以1024转换为M
            ret = String.valueOf(initial_memory);
        } catch (Exception e) {
            ret = "";
        }
        return ret;
    }
}
