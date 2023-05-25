package com.vice.bloodpressure.retrofit;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.vice.bloodpressure.activity.login.LoginActivity;
import com.vice.bloodpressure.base.XyApplication;
import com.vice.bloodpressure.basemanager.ConstantParamNew;
import com.vice.bloodpressure.utils.UserInfoUtils;

import org.json.JSONObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.BiConsumer;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.HttpException;

public class BaseNetworkUtils {


    private static final String TAG = "HHSoftNetworkUtils";
    private static final String AccessTokenKey = "Authorization";


    /**
     * 需要把返回数据 解析F成的对应类型
     * NONE 不需要解析数据
     * JSON_OBJECT 需要把数据解析成model
     * JSON_ARRAY 需要把数据解析成list
     */
    public static final int NONE = 0;
    public static final int JSON_OBJECT = 1;
    public static final int JSON_ARRAY = 2;

    @IntDef({NONE, JSON_OBJECT, JSON_ARRAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface JsonParseMode {
    }

    /**
     * 网络请求
     *
     * @param requestType     请求方法，可参考：HHSoftNetReqUtils.RequestType.PUT
     * @param jsonParseMode   解析数据类型，可取值BaseNetworkUtils.NONE、BaseNetworkUtils.JSON_OBJECT、BaseNetworkUtils.JSON_ARRAY
     * @param methodName
     * @param paramMap
     * @param successCallBack
     * @param failureCallBack
     * @param accessToken     未保存token到本地之前，如果需要传token，此参数不能为空
     * @return
     */
    public static Call<String> networkRequest(boolean isNeedAccessToken, String accessToken, NetReqUtils.RequestType requestType, NetReqUtils.RequestBodyType requestBodyType, @JsonParseMode int jsonParseMode, Class clazz, String methodName, Map<String, String> paramMap, List<MultipartBody.Part> files, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", UserInfoUtils.getAcceToken(XyApplication.getMyApplicationContext()));
        headerMap.put("Client-Tag", "app");
        return new NetReqUtils.Builder()
                //IP
                .baseUrl(ConstantParamNew.IP)
                //方法名称
                .methodName(methodName)
                //请求方式
                .requestType(requestType)
                //
                .requestBodyType(requestBodyType)
                //请求头
                .headerMap(headerMap)
                //请求参数
                .paramMap(paramMap)
                //传递的文件参数
                .fileMap(files)
                //请求成功
                .successCallBack((call, result) -> processJsonParse(call, result, jsonParseMode, clazz, successCallBack))
                //请求失败
                .failureCallBack((call, t) -> {
                    processFailureCallBack(requestType, requestBodyType, isNeedAccessToken, jsonParseMode, clazz, ConstantParamNew.IP, methodName, paramMap, files, call, t, successCallBack, failureCallBack);
                }).build();
    }

    /**
     * 请求接口401的处理 待实现
     *
     * @param requestType
     * @param requestBodyType
     * @param isNeedAccessToken
     * @param parseMode
     * @param clazz
     * @param ip
     * @param methodName
     * @param paramMap
     * @param files
     * @param failureCall
     * @param failureThrowable
     * @param successCallBack
     * @param failureCallBack
     * @throws Exception
     */
    public static void processFailureCallBack(NetReqUtils.RequestType requestType, NetReqUtils.RequestBodyType requestBodyType, boolean isNeedAccessToken, @JsonParseMode int parseMode, Class clazz, String ip, String methodName, Map<String, String> paramMap, List<MultipartBody.Part> files, Call<String> failureCall, Throwable failureThrowable, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) throws Exception {
        Log.i(TAG, "failureCallBack: ");
        if (failureThrowable instanceof HttpException && 401 == ((HttpException) failureThrowable).response().code()) {
            if (UserInfoUtils.isLogin(XyApplication.getMyApplicationContext())) {
                UserInfoUtils.resetUserInfo(XyApplication.getMyApplicationContext());
                Intent intent = new Intent(XyApplication.getMyApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                XyApplication.getMyApplicationContext().startActivity(intent);
            }
        } else {
            if (failureCallBack != null) {
                failureCallBack.accept(failureCall, failureThrowable);
            }
        }
    }

    /**
     * 解析数据
     *
     * @param call
     * @param result
     * @param parseMode
     * @param clazz
     * @param successCallBack
     * @throws Exception
     */
    private static void processJsonParse(Call<String> call, String result, @JsonParseMode int parseMode, Class clazz, BiConsumer<Call<String>, BaseResponse> successCallBack) throws Exception {
        BaseResponse response = new BaseResponse();
        JSONObject jsonObject = new JSONObject(result);
        response.code = jsonObject.optString("code");
        response.msg = jsonObject.optString("msg");
        response.result = jsonObject.optString("data");
        if (JSON_OBJECT == parseMode) {
            if ("0000".equals(response.code)) {
                response.object = new Gson().fromJson(response.result, clazz);
            }
        } else if (JSON_ARRAY == parseMode) {
            if ("0000".equals(response.code)) {
                response.object = fromJsonToList(response.result, clazz);
            } else if ("1009".equals(response.code)) {
                response.object = new ArrayList<>();
            }
        } else {
            response.data = jsonObject.optBoolean("data", false);
        }
        successCallBack.accept(call, response);
    }

    public static Call<String> getRequest(boolean isNeedAccessToken, @JsonParseMode int jsonParseMode, Class clazz, String methodName, Map<String, String> paramMap, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        return networkRequest(isNeedAccessToken, "", NetReqUtils.RequestType.GET, NetReqUtils.RequestBodyType.MULTIPART, jsonParseMode, clazz, methodName, paramMap, null, successCallBack, failureCallBack);
    }

    public static Call<String> getRequest(boolean isNeedAccessToken, String accessToken, @JsonParseMode int jsonParseMode, Class clazz, String methodName, Map<String, String> paramMap, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        return networkRequest(isNeedAccessToken, accessToken, NetReqUtils.RequestType.GET, NetReqUtils.RequestBodyType.MULTIPART, jsonParseMode, clazz, methodName, paramMap, null, successCallBack, failureCallBack);
    }


    public static Call<String> postRequest(boolean isNeedAccessToken, @JsonParseMode int jsonParseMode, Class clazz, String methodName, Map<String, String> paramMap, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        return networkRequest(isNeedAccessToken, "", NetReqUtils.RequestType.POST, NetReqUtils.RequestBodyType.BODY_JSON, jsonParseMode, clazz, methodName, paramMap, null, successCallBack, failureCallBack);
    }

    public static Call<String> postRequest(boolean isNeedAccessToken, String accessToken, @JsonParseMode int jsonParseMode, Class clazz, String methodName, Map<String, String> paramMap, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        return networkRequest(isNeedAccessToken, accessToken, NetReqUtils.RequestType.POST, NetReqUtils.RequestBodyType.BODY_JSON, jsonParseMode, clazz, methodName, paramMap, null, successCallBack, failureCallBack);
    }

    public static Call<String> putRequest(boolean isNeedAccessToken, @JsonParseMode int jsonParseMode, Class clazz, String methodName, Map<String, String> paramMap, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        return networkRequest(isNeedAccessToken, "", NetReqUtils.RequestType.PUT, NetReqUtils.RequestBodyType.MULTIPART, jsonParseMode, clazz, methodName, paramMap, null, successCallBack, failureCallBack);
    }


    public static Call<String> deleteRequest(boolean isNeedAccessToken, @JsonParseMode int jsonParseMode, Class clazz, String methodName, Map<String, String> paramMap, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        return networkRequest(isNeedAccessToken, "", NetReqUtils.RequestType.DELETE, NetReqUtils.RequestBodyType.BODY_JSON, jsonParseMode, clazz, methodName, paramMap, null, successCallBack, failureCallBack);
    }

    public static Call<String> uploadImgRequest(boolean isNeedAccessToken, @JsonParseMode int jsonParseMode, Class clazz, String methodName, Map<String, String> paramMap, List<MultipartBody.Part> files, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        return networkRequest(isNeedAccessToken, "", NetReqUtils.RequestType.POST, NetReqUtils.RequestBodyType.MULTIPART, jsonParseMode, clazz, methodName, paramMap, files, successCallBack, failureCallBack);
    }


    /**
     * 将json字符串转换为ArrayList
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list = new Gson().fromJson(json, type);
        return list;
    }

    private static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        private Class clazz;

        public ParameterizedTypeImpl(Class clazz) {
            this.clazz = clazz;
        }

        @NonNull
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @NonNull
        @Override
        public Type getRawType() {
            return List.class;
        }

        @Nullable
        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}

