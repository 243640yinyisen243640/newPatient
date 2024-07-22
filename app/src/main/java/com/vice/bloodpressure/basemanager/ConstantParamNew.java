package com.vice.bloodpressure.basemanager;

import android.annotation.SuppressLint;
import android.os.Environment;

import com.vice.bloodpressure.utils.tools.XySoftFileUtils;

public class ConstantParamNew {
    public static final String SERVER_VERSION = "210601";
    /**
     * ip地址
     */
//    public static final String IP = "http://192.168.31.134:9500/";
    /**
     * 线上环境
     */
    public static final String IP = "http://39.106.101.129:9500/";
    /**
     * 本地访问地址 李昂
     */
//    public static final String IP = "http://192.168.0.54:9500/";
    /**
     * 本地访问地址 赵亚鹏
     */
//    public static final String IP = "http://192.168.0.58:9500/";
    /**
     * 域名 访问用户服务协议、隐私政策
     */
    public static final String DOMAIN_NAME = "http://java.xiyuns.cn/";


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
        if (XySoftFileUtils.isSDExist()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CACHR_DIR_NAME + "/";
        } else {
            return "/data/data/" + PACKAGE_NAME + "/" + CACHR_DIR_NAME + "/";
        }
    }
}
