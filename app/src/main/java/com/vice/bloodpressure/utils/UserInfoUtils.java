package com.vice.bloodpressure.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.model.UserInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * @类说明 UserInfoUtils
 * @作者 hhsoft
 * @创建日期 2018/12/4 15:05
 */
public class UserInfoUtils {

    /**
     * CLIENT_ID
     *
     * @param context
     * @return
     */
    public static String getClientID(Context context) {
        String clientID = SharedPreferencesUtils.getInfo(context, SharedPreferencesConstant.CLIENT_ID);
        return TextUtils.isEmpty(clientID) ? HHSoftSystemUtils.deviceToken(context) : clientID;
    }


    /**
     * 获取用户ID，默认是0
     */
    public static String getUserID(Context context) {
        String userID = SharedPreferencesUtils.getInfo(context, SharedPreferencesConstant.USER_ID);
        return TextUtils.isEmpty(userID) ? "0" : userID;
    }

    /**
     * 判断用户是否登录
     */
    public static boolean isLogin(Context context) {
        String userID = SharedPreferencesUtils.getInfo(context, SharedPreferencesConstant.ARCHIVES_ID);
        return (TurnUtils.getInt(userID, 0) > 0);
    }

    /**
     * 获取archives_id，默认是0
     */
    public static String getArchivesId(Context context) {
        String archivesId = SharedPreferencesUtils.getInfo(context, SharedPreferencesConstant.ARCHIVES_ID);
        return TextUtils.isEmpty(archivesId) ? "" : archivesId;
    }

    /**
     * 重置信息--用户退出登录，重置某些信息
     */
    public static void resetUserInfo(Context context) {
        Map<String, String> map = new HashMap<>();
        map.put(SharedPreferencesConstant.USER_ID, "0");
        map.put(SharedPreferencesConstant.ARCHIVES_ID, "0");
        map.put(SharedPreferencesConstant.ACCESS_TOKEN, "");

        SharedPreferencesUtils.saveInfo(context, map);

    }

    /**
     * 退出登录
     * CLIENTID 不需要清空
     * 单点登录： BaseCallBack、Handler传null
     */
    public static void outlog(final Context context, final CallBack callBack, final Handler handler) {
        if (callBack == null) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancelAll();
            resetUserInfo(context);
        } else {
            resetUserInfo(context);
            if (handler != null) {
                handler.post(() -> callBack.callBack(null));
            }
        }
    }

    /**
     * 请求验证token
     */
    public static String getAcceToken(Context context) {
        String accessToken = SharedPreferencesUtils.getInfo(context, SharedPreferencesConstant.ACCESS_TOKEN, "");
        if (TextUtils.isEmpty(accessToken)) {
            accessToken = "";
        }
        return accessToken;
    }


    public static String getUserInfo(Context context, String infoKey) {
        return SharedPreferencesUtils.getInfo(context, infoKey);
    }


    /**
     * 获取个人资料
     */

    public static UserInfo getUserInfo(Context context) {
        Map<String, String> map = new HashMap<>();
        //        map.put(SharedPreferencesConstant.LOGIN_NAME, "0");


        Map<String, String> resultMap = SharedPreferencesUtils.getInfo(context, map);

        UserInfo userInfo = new UserInfo();
        //        userInfo.setLoginName(resultMap.get(SharedPreferencesConstant.LOGIN_NAME));

        //待完善
        return userInfo;
    }

    /**
     * 保存个人中心信息
     */
    public static void saveUserInfo(Context context, UserInfo userInfo) {

        Map<String, String> map = new HashMap<>();
        //        map.put(SharedPreferencesConstant.USER_ID, userInfo.getUserId());
        map.put(SharedPreferencesConstant.ARCHIVES_ID, userInfo.getArchivesId());
        map.put(SharedPreferencesConstant.NICK_NAME, userInfo.getNickName());
        map.put(SharedPreferencesConstant.SEX, userInfo.getSex());

        map.put(SharedPreferencesConstant.AGE, userInfo.getAge());

        map.put(SharedPreferencesConstant.DIABETES_TYPE, userInfo.getDiabetesType());
        map.put(SharedPreferencesConstant.HYPERTENSION_TYPE, userInfo.getHypertensionType());

        SharedPreferencesUtils.saveInfo(context, map);
    }

    /**
     * 保存用户登录信息
     */
    public static void saveLoginInfo(Context context, UserInfo userInfo) {
        Map<String, String> map = new HashMap<>();
        map.put(SharedPreferencesConstant.USER_ID, userInfo.getUserId());
        map.put(SharedPreferencesConstant.ACCESS_TOKEN, userInfo.getAccess_token());
        map.put(SharedPreferencesConstant.ARCHIVES_ID, userInfo.getArchivesId());
        SharedPreferencesUtils.saveInfo(context, map);
    }

    /**
     * 保存单个字段信息
     *
     * @param context
     * @param filedKey
     * @param filedValue
     */
    public static void saveUserInfo(Context context, String filedKey, String filedValue) {
        Map<String, String> map = new HashMap<>();
        map.put(filedKey, filedValue);
        SharedPreferencesUtils.saveInfo(context, map);
    }
}
