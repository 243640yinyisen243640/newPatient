package com.vector.update_app;

import android.text.TextUtils;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * @类说明 BaseModel
 * @作者
 * @创建日期 2018/12/4 15:04
 */
public abstract class BaseModel<T> {
    private int code;
    private String msg;
    public String result;

    public BaseModel() {

    }

    public BaseModel(String json) {
        parseJson(json);
    }

    /**
     * 根据接口返回的json 获取 code msg result
     *
     * @param json
     */
    private void parseJson(String json) {
        if (TextUtils.isEmpty(json)) {
            code = -1;
            msg = "";
            result = "";
        } else {
            try {
                JSONObject jsonObject = new JSONObject(json);
                code = jsonObject.optInt("code");
                msg = jsonObject.optString("msg");
                result = jsonObject.optString("result", "");

            } catch (JSONException e) {
                code = -1;
                msg = "";
                result = "";
            }
        }
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 解析字段
     *
     * @return
     */
    public String decodeField(String value) {
        return HHEncryptUtils.decodeBase64(value);
    }

    /**
     * 解析字段
     *
     * @param jsonObject
     * @param fieldName  字段名称
     * @return
     */
    public String decodeField(JSONObject jsonObject, String fieldName) {
        return HHEncryptUtils.decodeBase64(jsonObject.optString(fieldName));
    }

}
