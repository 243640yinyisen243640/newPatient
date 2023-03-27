package com.vice.bloodpressure.baseui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IImageBrower;
import com.vice.bloodpressure.basemanager.ConstantParamNew;
import com.vice.bloodpressure.utils.longimage.ImageSource;
import com.vice.bloodpressure.utils.longimage.ImageViewState;
import com.vice.bloodpressure.utils.longimage.SubsamplingScaleImageView;
import com.vice.bloodpressure.utils.photoview.PhotoView;
import com.vice.bloodpressure.utils.tools.HHSoftFileUtils;
import com.vice.bloodpressure.utils.widget.HHSoftPictureVideoPlayActivity;
import com.vice.bloodpressure.utils.widget.PreviewViewPager;

import java.io.File;
import java.util.List;

/**
 * @author hhsoft
 */
public class PictureBrowserActivity extends UIBaseActivity {
    /**
     * 显示的图片的列表,参数FLAG_IMAGE_LIST表示的图片的列表的大小不允许为null或者size为0；
     * 如果传入的参数FLAG_IMAGE_POSITION小于0或者大于列表的大小，则自动为0，且默认为0；
     */
    public static final String FLAG_IMAGE_LIST = "flag_image_list";

    /**
     * 首次进入的时候应该显示那张图片，位置从0开始
     */
    public static final String FLAG_IMAGE_POSITION = "flag_image_position";
    private PreviewViewPager viewPager;
    private TextView backTextView;
    private TextView titleTextView;
    private TextView downloadTextView;
    private List<? extends IImageBrower> imageList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
        initView();
        initValue();
        initLinstener();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.hhsoft_base_activity_picture_browser, null);
        viewPager = view.findViewById(R.id.viewpage);
        backTextView = view.findViewById(R.id.tv_browser_back);
        titleTextView = view.findViewById(R.id.tv_browser_position);
        downloadTextView = view.findViewById(R.id.tv_browser_download);
        containerView().addView(view);
    }

    private void initValue() {
        topViewManager().topView().removeAllViews();
        imageList = (List<? extends IImageBrower>) getIntent().getSerializableExtra(FLAG_IMAGE_LIST);
        for (int i = 0; i < imageList.size(); i++) {
            //判断是不是本地路径
            if (!HHSoftFileUtils.isHttpUrl(imageList.get(i).bigImage())) {
                downloadTextView.setVisibility(View.GONE);
            } else {
                downloadTextView.setVisibility(View.VISIBLE);
            }
        }
        if (imageList == null || imageList.size() == 0) {
            throw new RuntimeException("please agree_un_check flag FLAG_IMAGE_LIST,and the image list can not be null or size is 0");
        }
        int position = getIntent().getIntExtra(FLAG_IMAGE_POSITION, 0);
        if (position < 0 || position > imageList.size() - 1) {
            position = 0;
        }
        setTitleText(position + 1);

        viewPager.setAdapter(new PictureBrowserAdapter(getPageContext(), imageList));
        viewPager.setCurrentItem(position, true);
    }

    private void initLinstener() {
        backTextView.setOnClickListener(v -> {
            finish();
        });
//        downloadTextView.setOnClickListener(v -> {
//            HHSoftFileUtils.createDirectory(ConstantParamNew.IMAGE_SAVE_CACHE);
//            String savePath = ConstantParamNew.IMAGE_SAVE_CACHE + System.currentTimeMillis() + ".jpg";
//            String downloadImagePath = imageList.get(viewPager.getCurrentItem()).sourceImage();
//            BaseDataManager.downloadFile(downloadImagePath, savePath, new HHSoftDownloadListener() {
//                @Override
//                public void onProgress(int progress) {
//
//                }
//
//                @Override
//                public void onCompleted() {
//                    HHSoftTipUtils.getInstance().showToast(getPageContext(), getString(R.string.down_finish_save_to) + savePath);
//                    updateImageForAlbum(getPageContext(), savePath);
//                }
//
//                @Override
//                public void onError(String msg) {
//                    HHSoftTipUtils.getInstance().showToast(getPageContext(), getString(R.string.down_image_failed));
//                }
//            });
//
//        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setTitleText(i + 1);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void updateImageForAlbum(Context context, String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            Uri imageUri;
            File file = new File(imagePath);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                imageUri = FileProvider.getUriForFile(context, context.getPackageName() + ConstantParamNew.FILE_PROVIDER, file);
            } else {
                imageUri = Uri.fromFile(file);
            }
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, imageUri);
            context.sendBroadcast(intent);
        }
    }

    /**
     * 设置标题
     *
     * @param position 当前图片图纸
     */
    private void setTitleText(int position) {
        String title = String.format(getString(R.string.selection_img), position + "", imageList.size() + "");
        titleTextView.setText(title);
    }
}

class PictureBrowserAdapter extends PagerAdapter {
    private Context context;
    private List<? extends IImageBrower> images;

    public PictureBrowserAdapter(Context context, List<? extends IImageBrower> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.hhsoft_base_item_picture_browser, null);
        PhotoView photoView = view.findViewById(R.id.photoview);
        SubsamplingScaleImageView longImgView = view.findViewById(R.id.longImg);
        ImageView playImageView = view.findViewById(R.id.iv_video_play);
        ProgressBar progressBar = view.findViewById(R.id.progress_browser);
        IImageBrower image = images.get(position);
        String bigImagePath = image.bigImage();
        if (HHSoftFileUtils.isHttpUrl(bigImagePath)) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if ("2".equals(image.imageType())) {
            playImageView.setVisibility(View.VISIBLE);
        }
        boolean isGif = image.isGif();
        final boolean eqLongImg = isLongImg(image);
        photoView.setVisibility(eqLongImg && !isGif ? View.GONE : View.VISIBLE);
        longImgView.setVisibility(eqLongImg && !isGif ? View.VISIBLE : View.GONE);
        if (isGif) {
            loadGif(bigImagePath, progressBar, photoView);
        } else {
            loadImage(bigImagePath, progressBar, photoView, longImgView, eqLongImg);
        }
        photoView.setOnClickListener(v -> {
            ((Activity) context).finish();
        });
        longImgView.setOnClickListener(v -> {
            ((Activity) context).finish();
        });
        photoView.setOnLongClickListener(v -> {
//            String qrCode = QRCodeUtils.identificationQRCodeByImageView(mActivity, photoView);
//            if (!TextUtils.isEmpty(qrCode)) {
//                showQRCodeDialog(qrCode);
//            }
            return true;
        });
        longImgView.setOnLongClickListener(v -> {
//            String qrCode = QRCodeUtils.identificationQRCodeByImageView(mActivity, longImgView);
//            if (!TextUtils.isEmpty(qrCode)) {
//                showQRCodeDialog(qrCode);
//            }
            return true;
        });
        playImageView.setOnClickListener(v -> {
            pictureVideoPlay(context, image.videoPath());
        });
        container.addView(view);
        return view;
    }

    public static void pictureVideoPlay(Context context, String videoPath) {
        Intent intent = new Intent(context, HHSoftPictureVideoPlayActivity.class);
        intent.putExtra("video_path", videoPath);
        context.startActivity(intent);
    }

    private void loadImage(String path, ProgressBar progressBar, PhotoView photoView, SubsamplingScaleImageView longImgView, boolean isLongImg) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).asBitmap().load(path).apply(options).into(new SimpleTarget<Bitmap>(480, 800) {
            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                progressBar.setVisibility(View.INVISIBLE);
                if (isLongImg) {
                    displayLongPic(resource, longImgView);
                } else {
                    Log.i("wu", "resouceWidth==" + resource.getWidth());
                    Log.i("wu", "resouceHeight==" + resource.getHeight());
                    photoView.setImageBitmap(resource);
                }
            }
        });
    }

    private void loadGif(String path, ProgressBar progressBar, PhotoView photoView) {
        RequestOptions gifOptions = new RequestOptions().override(480, 800).priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context).asGif().apply(gifOptions).load(path).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.INVISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.INVISIBLE);
                return false;
            }
        }).into(photoView);
    }

    /**
     * 加载长图
     *
     * @param bmp
     * @param longImg
     */
    private void displayLongPic(Bitmap bmp, SubsamplingScaleImageView longImg) {
        longImg.setQuickScaleEnabled(true);
        longImg.setZoomEnabled(true);
        longImg.setPanEnabled(true);
        longImg.setDoubleTapZoomDuration(100);
        longImg.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
        longImg.setDoubleTapZoomDpi(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER);
        longImg.setImage(ImageSource.cachedBitmap(bmp), new ImageViewState(0, new PointF(0, 0), 0));
    }

    public static boolean isLongImg(IImageBrower image) {
        if (null != image && image.widthAndHeight() != null) {
            int width = image.widthAndHeight().get("width");
            int height = image.widthAndHeight().get("height");
            int h = width * 3;
            return height > h;
        } else {
            return false;
        }
    }
}
