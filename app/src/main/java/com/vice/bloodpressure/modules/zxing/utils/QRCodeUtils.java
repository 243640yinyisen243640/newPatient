package com.vice.bloodpressure.modules.zxing.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.modules.zxing.decode.BitmapDecoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 二维码处理结果
 * NMI  店铺二维码
 * NMP  收款二维码
 * NMD  餐桌二维码
 * Created by xiao on 2017/5/18.
 */

public class QRCodeUtils {
    private static boolean isShop = false;
    private static String shop_id = "";
    private static Context mContext;

    public static void qrCodeOper(Context context, String content, Intent intent) {
        isShop = intent.getBooleanExtra("input", false);
        shop_id = intent.getStringExtra("id");
        qrCodeOper(context, content);
    }

    /**
     * 二维码结果操作
     *
     * @param context
     * @param content
     */
    public static void qrCodeOper(Context context, String content) {
        mContext = context;
        Log.i("yys", "qrCodeOper====" + content);
        dealQRCode(context, content, null);
    }

    public static void qrCodeOper(Context context, String content, CallBack callBack) {
        dealQRCode(context, content, callBack);
    }

    /**
     * 二维码结果处理
     * http://web.diaodiaoyu.com/download.html?mark=1&ud=30
     * 其中key是一个json的拼接   {"type":"NMS","values":"NMS338D2E53","token":"个推的推送KEY"}
     */
    private static void dealQRCode(Context context, String content, CallBack callBack) {

    }

    /* 华信应用内个人、群组二维码、认证*/
    private static void handleQrcodeJump(Context context, String content) {
        // 格式：http://116.255.236.229:8495/register.html?mark=2&content=31
        //返回的是Url的话 要分割取出对应的字段和值
        Log.i("yys", "content==" + content);
        //截取从最后一个问号，取出来paraStr = mark=1&ud=30
        String paraStr = content.substring(content.lastIndexOf("?") + 1);
        //paraStrs = 【0】mark = 1； 【1】ud = 30 这样的一个数组
        //mark=2是群二维码  mark=1 是用户二维码
        String[] paraStrs = paraStr.split("&");

    }

    /**
     * 长按识别二维码
     *
     * @param bigImage
     * @return
     */
    public static String identificationQRCodeByImageView(Context context, ImageView bigImage) {
        Bitmap bitmap = ((BitmapDrawable) (bigImage).getDrawable()).getBitmap();
        //        int sourceWidth=bitmap.getWidth();
        //        int sourceHeight=bitmap.getHeight();
        //        HHLog.i("xiao", "identificationQRCodeByImageView==bitmap==" + sourceWidth+"=="+sourceHeight);
        //        BitmapDecoder decoder = new BitmapDecoder(context);
        //        Result result = decoder.getRawResult(bitmap);
        //        HHLog.i("xiao", "identificationQRCodeByImageView==result==" + result);
        //        if (result != null) {
        //            return ResultParser.parseResult(result)
        //                    .toString();
        //        }


        //
        //        int desWidth=sourceWidth;
        //        int desHeight=sourceHeight;
        //        if (sourceWidth>=480||sourceHeight>=800){
        //            final int heightRatio = Math.round((float) sourceHeight/ (float) 800);
        //            final int widthRatio = Math.round((float) sourceWidth / (float) 480);
        //            int inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        //            desWidth=sourceWidth/inSampleSize;
        //            desHeight=sourceHeight/inSampleSize;
        //        }
        //        Bitmap obmp=Bitmap.createScaledBitmap(bitmap,desWidth,desHeight,true);
        //        BitmapDecoder decoder1 = new BitmapDecoder(context);
        //        Result result1 = decoder1.getRawResult(obmp);
        //        HHLog.i("xiao", "identificationQRCodeByImageView==result1==" + result1);
        //        if (result1 != null) {
        //            obmp.recycle();
        //            return ResultParser.parseResult(result1).toString();
        //        }
        //        obmp.recycle();


        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] data = new int[width * height];
        bitmap.getPixels(data, 0, width, 0, 0, width, height);

        Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

        RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result2 = null;
        try {
            result2 = reader.decode(bitmap1, hints);
            Log.i("xiao", "identificationQRCodeByImageView==result2==" + result2);
            return result2.getText();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("xiao", "error==" + Log.getStackTraceString(e));
        }
        return "";
    }

    /**
     * 长按识别二维码
     *
     * @return
     */
    public static String identificationQRCodeByPath(Context context, String photoPath) {
        Bitmap img = BitmapUtils.getCompressedBitmap(photoPath);
        BitmapDecoder decoder = new BitmapDecoder(context);
        Result result = decoder.getRawResult(img);
        if (result != null) {
            return ResultParser.parseResult(result)
                    .toString();
        }
        return "";
    }
}
