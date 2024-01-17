package com.vice.bloodpressure.modules.zxing.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.vice.bloodpressure.activity.auser.UserAddEquipmentActivity;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.modules.zxing.decode.BitmapDecoder;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 二维码处理结果
 * NMI  店铺二维码
 * NMP  收款二维码
 * NMD  餐桌二维码
 * Created by xiao on 2017/5/18.
 */

public class QRCodeUtils {
    private static Context mContext;

    public static void qrCodeOper(Context context, String content, Intent intent) {
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
     * 二维码处理结果
     *
     * @param context
     * @param content
     * @param callBack
     */
    private static void dealQRCode(Context context, String content, CallBack callBack) {
        Intent intent = new Intent(context, UserAddEquipmentActivity.class);
        intent.putExtra("deviceCode", content);
        intent.putExtra("type", "2");
        context.startActivity(intent);
        ((Activity) context).finish();
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


    /**
     * 创建二维码位图
     *
     * @param content 字符串内容(支持中文)
     * @param width 位图宽度(单位:px)
     * @param height 位图高度(单位:px)
     * @return
     */
    @Nullable
    public static Bitmap createQRCodeBitmap(String content, int width, int height){
        return createQRCodeBitmap(content, width, height, "UTF-8", "H", "2", Color.BLACK, Color.WHITE);
    }

    /**
     * 创建二维码位图 (支持自定义配置和自定义样式)
     *
     * @param content 字符串内容
     * @param width 位图宽度,要求>=0(单位:px)
     * @param height 位图高度,要求>=0(单位:px)
     * @param character_set 字符集/字符转码格式 (支持格式:{@link CharacterSetECI })。传null时,zxing源码默认使用 "ISO-8859-1"
     * @param error_correction 容错级别 (支持级别:{@link ErrorCorrectionLevel })。传null时,zxing源码默认使用 "L"
     * @param margin 空白边距 (可修改,要求:整型且>=0), 传null时,zxing源码默认使用"4"。
     * @param color_black 黑色色块的自定义颜色值
     * @param color_white 白色色块的自定义颜色值
     * @return
     */
    @Nullable
    public static Bitmap createQRCodeBitmap(String content, int width, int height,
                                            @Nullable String character_set, @Nullable String error_correction, @Nullable String margin,
                                            @ColorInt int color_black, @ColorInt int color_white){

        /** 1.参数合法性判断 */
        if(TextUtils.isEmpty(content)){ // 字符串内容判空
            return null;
        }

        if(width < 0 || height < 0){ // 宽和高都需要>=0
            return null;
        }

        try {
            /** 2.设置二维码相关配置,生成BitMatrix(位矩阵)对象 */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();

            if(!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set); // 字符转码格式设置
            }

            if(!TextUtils.isEmpty(error_correction)){
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction); // 容错级别设置
            }

            if(!TextUtils.isEmpty(margin)){
                hints.put(EncodeHintType.MARGIN, margin); // 空白边距设置
            }
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    if(bitMatrix.get(x, y)){
                        pixels[y * width + x] = color_black; // 黑色色块像素设置
                    } else {
                        pixels[y * width + x] = color_white; // 白色色块像素设置
                    }
                }
            }

            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,之后返回Bitmap对象 */
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return null;
    }
}
