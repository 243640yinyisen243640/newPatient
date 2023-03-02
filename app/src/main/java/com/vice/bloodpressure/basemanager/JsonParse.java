package com.vice.bloodpressure.basemanager;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParse {
    /**
     * 获取服务器返回的结果码 网络错误时返回-1
     *
     * @return
     */
    public static int getResponceCode(String result) {
        return getResponceCode(result, "code");
    }

    /**
     * 获取服务器返回的结果码 网络错误时返回-1
     *
     * @param result
     * @param codeName 结果码的标识
     * @return
     */
    public static int getResponceCode(String result, String codeName) {
        int code = -1;
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                code = Integer.valueOf(jsonObject.getString(codeName));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return code;
    }


    /**
     * 解析JsonObject单个字段
     *
     * @param data
     * @param paramName
     * @return
     */
    public static String parseField(String data, String paramName) {
        if (!TextUtils.isEmpty(data)) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String result = jsonObject.optString(paramName);
                if (TextUtils.isEmpty(result)) {
                    return "";
                }
                return result;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String getParamInfo(String data, String paramName) {
        if (!TextUtils.isEmpty(data)) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String result = jsonObject.optString(paramName);
                if (TextUtils.isEmpty(result)) {
                    return "";
                }
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
