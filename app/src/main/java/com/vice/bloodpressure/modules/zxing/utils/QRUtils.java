package com.vice.bloodpressure.modules.zxing.utils;

import android.text.TextUtils;

import com.vice.bloodpressure.basemanager.ConstantParamNew;
import com.vice.bloodpressure.utils.TurnUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiao on 2017/8/25.
 */

public class QRUtils {

    /*生成二维码地址*/
    public static String createQrCodeUrl(String type, String content) {
        String qrcodeUrl = "";
        qrcodeUrl = ConstantParamNew.IP + "qrcode?" + content;
        return qrcodeUrl;
    }

    /**
     * qrCodeId 11位
     */
    public static String createQrCodeId(String qrCodeId, String userId) {
        if (TextUtils.isEmpty(qrCodeId) || qrCodeId.length() != 11 || TextUtils.isEmpty(userId)) {
            return "";
        }
        String icon = qrCodeId.substring(0, 3);
        String userIds = userId.length() + userId;
        StringBuilder builder = new StringBuilder();
        char[] insert = userIds.toCharArray();
        char[] inserted = new StringBuffer(qrCodeId.substring(3)).reverse().toString().toCharArray();

        if (inserted.length > insert.length) {
            for (int i = 0; i < inserted.length; i++) {
                if (i < insert.length) {
                    builder.append(insert[i]);
                    builder.append(inserted[i]);
                } else {
                    builder.append(inserted[i]);
                }
            }
        } else {
            for (int i = 0; i < insert.length; i++) {
                if (i < inserted.length) {
                    builder.append(insert[i]);
                    builder.append(inserted[i]);
                } else {
                    builder.append(insert[i]);
                }
            }
        }
        builder.reverse();
        builder.insert(0, icon);
        return builder.toString();
    }

    public static Map<String, String> parseQrCodeId(String qrCodeId) {
        if (TextUtils.isEmpty(qrCodeId)) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        if (qrCodeId.length() <= 11) {
            map.put("user_id", "");
            map.put("qr_code", qrCodeId);
        } else {
            String icon = qrCodeId.substring(0, 3);
            int userIdLength = TurnUtils.getInt(qrCodeId.substring(qrCodeId.length() - 1), 0);
            String content = qrCodeId.substring(3, qrCodeId.length() - 1);
            if (userIdLength == 0) {
                map.put("user_id", "");
                map.put("qr_code", qrCodeId);
            } else {
                int qrCodeLength = content.length() - userIdLength;
                //第一位是qrcode
                char[] parsed = new StringBuffer(content).reverse().toString().toCharArray();
                StringBuilder userBuilser = new StringBuilder();
                StringBuilder qrCodeBuilder = new StringBuilder();
                for (int i = 0; i < parsed.length; i++) {
                    if (userIdLength > qrCodeLength) {
                        if (i < (qrCodeLength * 2 - 1)) {
                            if (i % 2 == 0) {
                                qrCodeBuilder.append(parsed[i]);
                            } else {
                                userBuilser.append(parsed[i]);
                            }
                        } else {
                            userBuilser.append(parsed[i]);
                        }
                    } else {
                        if (i < userIdLength * 2) {
                            if (i % 2 == 0) {
                                qrCodeBuilder.append(parsed[i]);
                            } else {
                                userBuilser.append(parsed[i]);
                            }
                        } else {
                            qrCodeBuilder.append(parsed[i]);
                        }
                    }
                }
                map.put("user_id", userBuilser.toString());
                map.put("qr_code", icon + qrCodeBuilder.reverse());
            }
        }
        return map;
    }
}
