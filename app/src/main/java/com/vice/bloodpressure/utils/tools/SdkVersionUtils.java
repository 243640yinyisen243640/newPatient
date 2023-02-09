package com.vice.bloodpressure.utils.tools;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

/**
 * @author：luck
 * @date：2019-07-17 15:12
 * @describe：Android Sdk版本判断
 */
public class SdkVersionUtils {

    /**
     * 判断是否是Android Q版本
     *
     * @return
     */
    public static boolean checkedAndroid_Q() {
//        return false;
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }

    /**
     * 判断是否是Android R版本
     *
     * @return
     */
    public static boolean checkedAndroid_R() {
//        return false;
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R;
    }

    /**
     * 判断Uri是否是content
     *
     * @return
     */
    public static boolean checkedUriScheme_CONTENT(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if (!TextUtils.isEmpty(scheme) && "content".equalsIgnoreCase(scheme)) {
            return true;
        }
        return false;
    }

    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
}
