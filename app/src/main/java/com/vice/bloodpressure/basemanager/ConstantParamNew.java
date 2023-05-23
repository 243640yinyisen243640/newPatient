package com.vice.bloodpressure.basemanager;

import android.annotation.SuppressLint;
import android.os.Environment;

import com.vice.bloodpressure.utils.tools.HHSoftFileUtils;

public class ConstantParamNew {
    public static final String SERVER_VERSION = "210601";
    /**
     * ip地址
     */
//    public static final String IP = "http://192.168.31.216:9000/";
    public static final String IP = "http://192.168.31.182:9000/";


    public static final String APK_NAME = "huijiankang";
    //基本的缓存的路径
    public static final String BASE_CACHR_DIR = getBaseCacheDir();
    //缓存的文件的名字
    private static final String CACHR_DIR_NAME = APK_NAME;
    public static final String APK_DOWNLOAD_PATH = BASE_CACHR_DIR + APK_NAME + ".apk";
    public static final String IMAGE_SAVE_CACHE = BASE_CACHR_DIR + "saveImage/";
    public static final String FILE_PROVIDER = ".FileProvider";

    /**
     * 包名
     */
    public static final String PACKAGE_NAME = "com.zhengzhou.huijiankang";

    /**
     * 获取基本的缓存的路径
     */
    @SuppressLint("SdCardPath")
    private static String getBaseCacheDir() {
        if (HHSoftFileUtils.isSDExist()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CACHR_DIR_NAME + "/";
        } else {
            return "/data/data/" + PACKAGE_NAME + "/" + CACHR_DIR_NAME + "/";
        }
    }
}
