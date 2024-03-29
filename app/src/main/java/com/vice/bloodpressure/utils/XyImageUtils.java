package com.vice.bloodpressure.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IImageBrower;
import com.vice.bloodpressure.basemanager.ConstantParamNew;
import com.vice.bloodpressure.baseui.PictureBrowserActivity;
import com.vice.bloodpressure.utils.config.PictureConfig;
import com.vice.bloodpressure.utils.glide.CustomRoundedCorners;
import com.vice.bloodpressure.utils.luban.CompressionPredicate;
import com.vice.bloodpressure.utils.luban.Luban;
import com.vice.bloodpressure.utils.luban.OnCompressListener;
import com.vice.bloodpressure.utils.luban.OnRenameListener;
import com.vice.bloodpressure.utils.tools.XySoftFileUtils;
import com.vice.bloodpressure.utils.widget.PictureSelector;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;

/**
 * @类说明 图片加载工具类
 * @作者
 * @创建日期 2019/8/21 16:14
 * 注意：
 * 一、在Android P的系统上，所有Http的请求都被默认阻止了，导致glide在9.0加载不出来图片
 * 解决方案：在清单文件中
 * <application
 * ********
 * android:usesCleartextTraffic="true"
 * **********
 * >
 */
public class XyImageUtils {
    /**
     * 加载矩形图片
     *
     * @param context
     * @param defaultImageResourceId 占位图片
     * @param imagePath              图片路径
     * @param imageView              ImageView对象
     */
    public static void loadImage(Context context, int defaultImageResourceId, String imagePath, ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load(imagePath)
                .placeholder(defaultImageResourceId)
                .error(defaultImageResourceId)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param defaultImageResourceId 占位图片
     * @param imagePath              图片路径
     * @param imageView              ImageView对象
     */
    public static void loadRoundImage(Context context, int defaultImageResourceId, String imagePath, ImageView imageView) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(DensityUtils.dip2px(context, 5));
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = new RequestOptions().transform(new CenterCrop(), roundedCorners);
        Glide.with(context)
                .asBitmap()
                .load(imagePath)
                .placeholder(defaultImageResourceId)
                .error(defaultImageResourceId)
                .apply(options)
                .into(imageView);

    }

    /**
     * 加载自定义圆角图片
     *
     * @param context
     * @param defaultImageResourceId 占位图片
     * @param imagePath              图片路径
     * @param imageView              ImageView对象
     * @param radius                 图片圆角数组、按照左、上、右、下的顺序添加，偿长度是4，单位是dp
     */
    public static void loadCustomuRoundImage(Context context, int defaultImageResourceId, String imagePath, ImageView imageView, int[] radius) {
        if (radius == null || radius.length != 4) {
            loadRoundImage(context, defaultImageResourceId, imagePath, imageView);
        } else {
            int leftTopRadius = DensityUtils.dip2px(context, radius[0]);
            int rightTopRadius = DensityUtils.dip2px(context, radius[1]);
            int leftBottomRadius = DensityUtils.dip2px(context, radius[2]);
            int rightBottomRadius = DensityUtils.dip2px(context, radius[3]);
            CustomRoundedCorners roundedCorners = new CustomRoundedCorners(leftTopRadius, rightTopRadius, leftBottomRadius, rightBottomRadius);
            RequestOptions options = new RequestOptions().transform(new CenterCrop(), roundedCorners);
            Glide.with(context)
                    .asBitmap()
                    .load(imagePath)
                    .placeholder(defaultImageResourceId)
                    .error(defaultImageResourceId)
                    .apply(options)
                    .into(imageView);
        }
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param defaultImageResourceId 占位图片
     * @param imagePath              图片路径
     * @param imageView              ImageView对象
     */
    public static void loadCircleImage(Context context, int defaultImageResourceId, String imagePath, ImageView imageView) {
        RequestOptions options = RequestOptions.circleCropTransform();
        //                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
        //                .skipMemoryCache(true);//不做内存缓存
        Glide.with(context)
                .asBitmap()
                .load(imagePath)
                .placeholder(defaultImageResourceId)
                .error(defaultImageResourceId)
                .apply(options)
                .into(imageView);
    }

    public static  void getFirst(String url,ImageView imageView){
        RequestOptions options = new RequestOptions();
        options.skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL)
                .frame(1000000)
                .centerCrop();

        Glide.with(imageView)
                .setDefaultRequestOptions(options)
                .load(url)
                .into(imageView);

    }

    /**
     * 获取视频的第一帧
     *
     * @param context
     * @param uri
     * @param imageView
     */
    public static void loadVideoScreenshot(final Context context, String uri, ImageView imageView) {
        RequestOptions requestOptions = RequestOptions.frameOf(0);
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
        requestOptions.transform(new BitmapTransformation() {
            @Override
            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                return toTransform;
            }

            @Override
            public void updateDiskCacheKey(MessageDigest messageDigest) {
                try {
                    messageDigest.update((context.getPackageName() + "RotateTransform").getBytes("utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Glide.with(context).load(uri).apply(requestOptions).into(imageView);
    }


    /**
     * 加载动画，只播放一次
     *
     * @param context
     * @param defaultImageResourceId
     * @param imagePath
     * @param imageView
     */
    public static void loadGifImage(Context context, int defaultImageResourceId, String imagePath, ImageView imageView) {
        Glide.with(context)
                .asGif()
                .load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(defaultImageResourceId)
                .error(defaultImageResourceId)
                .into(new SimpleTarget<GifDrawable>() {
                    @Override
                    public void onResourceReady(@NonNull GifDrawable resource, @Nullable Transition<? super GifDrawable> transition) {
                        if (resource instanceof GifDrawable) {
                            GifDrawable gifDrawable = resource;
                            gifDrawable.setLoopCount(2);
                            imageView.setImageDrawable(resource);
                            gifDrawable.start();
                        }
                    }
                });
    }

    /**
     * 清除图片磁盘缓存
     *
     * @param context
     */
    public static void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     *
     * @param context
     */
    public static void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { // 只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空Glide缓存
     *
     * @param context
     */
    public static void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
        String imageExternalCatchDir = context.getExternalCacheDir()
                + ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
        XySoftFileUtils.deleteFolder(imageExternalCatchDir);
    }

    /**
     * 获取Glide造成的缓存大小
     *
     * @param context
     * @return 返回字节长度，获取失败返回0
     */
    public static long cacheSize(Context context) {
        try {
            String filePath = context.getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
            return XyFileUtils.fileSize(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取格式化的Glide造成的缓存大小
     *
     * @param context
     * @return 格式话字符串，以Byte、KB、MB、GB、TB结尾
     */
    public static String formatCacheSize(Context context) {
        return getFormatSize(cacheSize(context));
    }


    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 图片压缩：异步单张压缩图片
     *
     * @param context
     * @param sourceImagePath 原图片路径
     * @param targetDirPath   目标文件夹路径eg:/storage/emulated/0/XySoftLib/
     * @param callBack        压缩回调，压缩成功返回压缩后的路径eg:/storage/emulated/0/XySoftLib/1566460459501169.jpeg；压缩失败返回原路径sourceImagePath
     */
    public static void compressAsync(Context context, final String sourceImagePath, final String targetDirPath, final Consumer<String> callBack) {
        Luban.with(context)
                .load(sourceImagePath)
                .ignoreBy(200)//不压缩的阈值，单位为K
                .setTargetDir(targetDirPath)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || XyFileUtils.FileType.IMAGE_GIF == XyFileUtils.fileTypeForImageData(context, sourceImagePath));
                    }
                })
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        return System.currentTimeMillis() + ".jpg";
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.i("chen", "onStart==");
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.i("chen", "onSuccess==" + file.getAbsolutePath());
                        callBack.accept(file.getAbsolutePath());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("chen", "onError==" + Log.getStackTraceString(e));
                        callBack.accept(sourceImagePath);
                    }
                }).launch();
    }

    /**
     * 图片压缩：异步多张压缩图片
     *
     * @param context
     * @param sourceImages  原图片路径集合
     * @param targetDirPath 目标文件夹路径eg:/storage/emulated/0/XySoftLib/
     * @param callBack      压缩回调，返回集合，压缩成功返回压缩后的路径eg:/storage/emulated/0/XySoftLib/1566460459501169.jpeg；压缩失败返回原路径sourceImagePath
     */
    public static void compressListAsync(Context context, final List<String> sourceImages, String targetDirPath, final Consumer<List<String>> callBack) {
        final List<String> compressImageList = new ArrayList<>();
        final int[] position = {0};
        Luban.with(context)
                .load(sourceImages)
                .ignoreBy(200)
                .setTargetDir(targetDirPath)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || XySoftFileUtils.FileType.IMAGE_GIF == XySoftFileUtils.fileTypeForImageData(context, path)) || XySoftFileUtils.isHttpUrl(path);
                    }
                })
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        return System.currentTimeMillis() + ".jpg";
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        position[0]++;
                    }

                    @Override
                    public void onSuccess(File file) {
                        compressImageList.add(file.getAbsolutePath());
                        if (compressImageList.size() == sourceImages.size()) {
                            callBack.accept(compressImageList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        compressImageList.add(sourceImages.get(position[0]));
                        if (compressImageList.size() == sourceImages.size()) {
                            callBack.accept(compressImageList);
                        }
                    }
                }).launch();
    }

    /**
     * 图片压缩：同步单张压缩，避免在UI线程使用，阻塞线程
     *
     * @param context
     * @param sourceImagePath 原图片路径
     * @param targetDirPath   目标文件夹路径eg:/storage/emulated/0/XySoftLib/
     * @return 压缩成功返回压缩后的路径eg:/storage/emulated/0/XySoftLib/1566460459501169.jpeg；压缩失败返回原路径sourceImagePath
     */
    public static String compressSync(Context context, String sourceImagePath, String targetDirPath) {
        try {
            return Luban.with(context)
                    .load(sourceImagePath)
                    .ignoreBy(200)
                    .setTargetDir(targetDirPath)
                    .filter(new CompressionPredicate() {
                        @Override
                        public boolean apply(String path) {
                            return !(TextUtils.isEmpty(path) || XySoftFileUtils.FileType.IMAGE_GIF == XySoftFileUtils.fileTypeForImageData(context, path));
                        }
                    })
                    .setRenameListener(new OnRenameListener() {
                        @Override
                        public String rename(String filePath) {
                            return System.currentTimeMillis() + ".jpg";
                        }
                    }).get(sourceImagePath).getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceImagePath;
    }

    /**
     * 图片压缩：同步多张压缩，避免在UI线程使用，阻塞线程
     *
     * @param context
     * @param sourceImageList 原图片路径集合
     * @param targetDirPath   目标文件夹路径eg:/storage/emulated/0/XySoftLib/
     * @return 图片路径集合，压缩成功返回压缩后的路径eg:/storage/emulated/0/XySoftLib/1566460459501169.jpeg；压缩失败返回原路径sourceImagePath
     */
    public static List<String> compressListSync(Context context, List<String> sourceImageList, String targetDirPath) {
        try {
            List<File> files = Luban.with(context)
                    .load(sourceImageList)
                    .ignoreBy(200)
                    .setTargetDir(targetDirPath)
                    .filter(new CompressionPredicate() {
                        @Override
                        public boolean apply(String path) {
                            return !(TextUtils.isEmpty(path) || XySoftFileUtils.FileType.IMAGE_GIF == XySoftFileUtils.fileTypeForImageData(context, path));
                        }
                    })
                    .setRenameListener(new OnRenameListener() {
                        @Override
                        public String rename(String filePath) {
                            return System.currentTimeMillis() + ".jpg";
                        }
                    }).get();

            List<String> compressList = new ArrayList<>();
            for (File file : files) {
                compressList.add(file.getAbsolutePath());
            }
            return compressList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceImageList;
    }

    /**
     * 加载长矩形图片
     *
     * @param context
     * @param defaultImageResourceId 占位图片
     * @param imagePath              图片路径
     * @param imageView              ImageView对象
     */
    public static void loadBigImage(Context context, int defaultImageResourceId, String imagePath, ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load(imagePath)
                .placeholder(defaultImageResourceId)
                .error(defaultImageResourceId)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(imageView);
    }

    /**
     * @param context
     * @param mimeType   回调code
     * @param maxCount
     * @param isCompress
     */
    public static void getImagePictureSelector(Context context, int mimeType, int maxCount, boolean isCompress) {
        XySoftFileUtils.createDirectory(ConstantParamNew.IMAGE_SAVE_CACHE);
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create((Activity) context).openGallery(mimeType)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.app_picture_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(maxCount)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(false)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(true) // 是否可播放音频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                //                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .setOutputCameraPath(ConstantParamNew.IMAGE_SAVE_CACHE)// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(isCompress)// 是否压缩
                .original(false)//是否原图
                .synOrAsy(false)//同步true或异步false 压缩 默认同步
                .compressSavePath(ConstantParamNew.IMAGE_SAVE_CACHE)//压缩图片保存地址
                .minimumCompressSize(200)// 小于200kb的图片不压缩
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(true)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .openClickSound(false)// 是否开启点击声音
                .videoMaxSecond(15).videoMinSecond(1).forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    /**
     * 查看大图
     *
     * @param context
     * @param position
     * @param list
     */
    public static void lookBigImage(Context context, int position, ArrayList<? extends IImageBrower> list) {
        Intent intent = new Intent(context, PictureBrowserActivity.class);
        intent.putExtra(PictureBrowserActivity.FLAG_IMAGE_POSITION, position);
        intent.putExtra(PictureBrowserActivity.FLAG_IMAGE_LIST, list);
        context.startActivity(intent);
    }
}
