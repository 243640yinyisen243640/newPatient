/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vice.bloodpressure.modules.zxing.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.google.zxing.Result;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.constant.PermissionsConstant;
import com.vice.bloodpressure.modules.zxing.camera.CameraManager;
import com.vice.bloodpressure.modules.zxing.decode.DecodeThread;
import com.vice.bloodpressure.modules.zxing.utils.BeepManager;
import com.vice.bloodpressure.modules.zxing.utils.CaptureActivityHandler;
import com.vice.bloodpressure.modules.zxing.utils.InactivityTimer;
import com.vice.bloodpressure.modules.zxing.utils.QRCodeUtils;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.config.PictureConfig;
import com.vice.bloodpressure.utils.entity.LocalMedia;
import com.vice.bloodpressure.utils.widget.PictureSelector;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * This activity opens the camera and does the actual scanning on a background
 * thread. It draws a viewfinder to help the user place the barcode correctly,
 * shows feedback as the image processing is happening, and then overlays the
 * results when a scan is successful.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 * @author Sean Owen
 */
public final class CaptureActivity extends UIBaseActivity implements OnClickListener, SurfaceHolder.Callback {

    private static final String TAG = CaptureActivity.class.getSimpleName();
    private static final int REQUEST_CODE = 100;
    private static final int SCAN_RESULT = 200;
    /*控件*/
    private SurfaceView scanPreview = null;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    private ImageView scanLine;
    private ToggleButton toggleButton;

    /*功能实现*/
    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;
    /*逻辑判断*/
    private Rect mCropRect = null;
    private boolean isFlashlightOpen;
    private boolean isHasSurface = false;

    /**
     * 图片的路径
     */
    private String photoPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("扫一扫");
        initView();
        initValues();
        initListeners();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.zxing_activity_capture, null);
        scanPreview = getViewByID(view, R.id.capture_preview);
        scanContainer = getViewByID(view, R.id.capture_container);
        scanCropView = getViewByID(view, R.id.capture_crop_view);
        scanLine = getViewByID(view, R.id.capture_scan_line);
        toggleButton = getViewByID(view, R.id.capture_flashlight);
        containerView().addView(view);
    }

    private void initValues() {

        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);

        /*扫描动画效果*/
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                0.9f);
        animation.setDuration(4500);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        scanLine.startAnimation(animation);
    }

    private void initListeners() {
        toggleButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!checkPermission(PermissionsConstant.PERMISSIONS_CAMERA)) {
            DialogUtils.showPermissionsDeniedDialog(getPageContext(), getString(R.string.zxing_permission_apply_camera_tip), (hhSoftDialog, hhSoftDialogActionEnum) -> {
                hhSoftDialog.dismiss();
                finish();
            });
            return;
        }
        cameraManager = new CameraManager(getApplication());
        if (isHasSurface) {
            initCamera(scanPreview.getHolder());
        } else {
            scanPreview.getHolder().addCallback(this);
        }
        inactivityTimer.onResume();
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        if (inactivityTimer != null) {
            inactivityTimer.onPause();
        }
        if (beepManager != null) {
            beepManager.close();
        }
        if (cameraManager != null) {
            cameraManager.closeDriver();
        }
        if (!isHasSurface && scanPreview != null) {
            scanPreview.getHolder().removeCallback(this);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.capture_flashlight://闪光灯
                if (isFlashlightOpen) {
                    cameraManager.setTorch(false); // 关闭闪光灯
                    isFlashlightOpen = false;
                } else {
                    cameraManager.setTorch(true); // 打开闪光灯
                    isFlashlightOpen = true;
                }
                break;

            //            case R.id.iv_scan_ablum:
            //                //相册
            //                XyImageUtils.getImagePictureSelector(getPageContext(), PictureMimeType.ofImage(), 1, false);
            //                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMediaList = PictureSelector.obtainMultipleResult(data);
                    if (localMediaList != null && localMediaList.size() > 0) {
                        identificationQRCode(localMediaList.get(0).getPath());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void handleDecode(Result rawResult, Bundle bundle) {
        inactivityTimer.onActivity();
        beepManager.playBeepSoundAndVibrate();
        if (getIntent() != null) {
            QRCodeUtils.qrCodeOper(getPageContext(), rawResult.getText(), getIntent());
        } else {
            QRCodeUtils.qrCodeOper(getPageContext(), rawResult.getText());
        }

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCAN_RESULT:
                    String qrCode = (String) msg.obj;
                    if (!TextUtils.isEmpty(qrCode)) {
                        //识别成功
                        if (getIntent() != null) {
                            QRCodeUtils.qrCodeOper(getPageContext(), qrCode, getIntent());
                        } else {
                            QRCodeUtils.qrCodeOper(getPageContext(), qrCode);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 识别二维码
     *
     * @param imagePath
     */
    private void identificationQRCode(final String imagePath) {
        //		HHSoftTipUtils.getInstance().showProgressDialog(getPageContext(), R.string.dealing);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Looper.prepare();
                //                String qrCode = QRCodeUtils.identificationQRCodeByPath(getPageContext(), imagePath);
                //                Message msg = mHandler.obtainMessage();
                //                msg.what = SCAN_RESULT;
                //                msg.obj = qrCode;
                //                mHandler.sendMessage(msg);
                //                Looper.loop();
            }
        }).start();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "initCamera==");
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            cameraManager.openDriver(surfaceHolder);
            // Creating the handler starts the preview, which can also throw a
            // RuntimeException.
            if (handler == null) {
                handler = new CaptureActivityHandler(this, cameraManager, DecodeThread.ALL_MODE);
            }
            initCrop();
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
            displayFrameworkBugMessageAndExit();
        }
    }

    /**
     * 初始化截取的矩形区域
     */
    private void initCrop() {
        int cameraWidth = cameraManager.getCameraResolution().y;
        int cameraHeight = cameraManager.getCameraResolution().x;

        /** 获取布局中扫描框的位置信息 */
        int[] location = new int[2];
        scanCropView.getLocationInWindow(location);

        int cropLeft = location[0];
        int cropTop = location[1] - getStatusBarHeight();

        int cropWidth = scanCropView.getWidth();
        int cropHeight = scanCropView.getHeight();

        /** 获取布局容器的宽高 */
        int containerWidth = scanContainer.getWidth();
        int containerHeight = scanContainer.getHeight();

        /** 计算最终截取的矩形的左上角顶点x坐标 */
        int x = cropLeft * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的左上角顶点y坐标 */
        int y = cropTop * cameraHeight / containerHeight;

        /** 计算最终截取的矩形的宽度 */
        int width = cropWidth * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的高度 */
        int height = cropHeight * cameraHeight / containerHeight;

        /** 生成最终的截取的矩形 */
        mCropRect = new Rect(x, y, width + x, height + y);
    }

    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    @SuppressLint("WrongConstant")
    private boolean selfPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int targetSdkVersion = 22;
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getPageContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (PermissionChecker.checkSelfPermission(getPageContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        // camera error
        boolean isPermission = selfPermissionGranted();
        Log.i("xiao", "displayFrameworkBugMessageAndExit==" + isPermission);
        if (isPermission) {
            DialogUtils.showTipDialog(getPageContext(), getString(R.string.zxing_scan_camera_open_failed),
                    (hhSoftDialog, hhSoftDialogActionEnum) -> {
                        hhSoftDialog.dismiss();
                        finish();
                    });
        } else {
            ToastUtils.getInstance().showToast(getPageContext(), R.string.zxing_camera_permission_open);
            finish();
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            Log.i(TAG, "surfaceCreated==");
        }
        if (!isHasSurface) {
            isHasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isHasSurface = false;
        cameraManager.stopPreview();
    }

    public Handler getActivityHandler() {
        return handler;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }


    private static final int MIN_FRAME_WIDTH = 240;
    private static final int MIN_FRAME_HEIGHT = 240;
    //    private static final int MAX_FRAME_WIDTH = 1200; // = 5/8 * 1920
    //    private static final int MAX_FRAME_HEIGHT = 675; // = 5/8 * 1080

    private static int MAX_FRAME_WIDTH = 1728; // = 5/8 * 1920
    private static int MAX_FRAME_HEIGHT = 972; // = 5/8 * 1080


    private Rect framingRect;

    public synchronized Rect getFramingRectInPreview() {
        Rect framingRect = getFramingRect();
        if (framingRect == null) {
            return null;
        }
        Rect rect = new Rect(framingRect);
        Point cameraResolution = cameraManager.getCameraResolution();
        Point screenResolution = cameraManager.getScreenResolution();
        if (cameraResolution == null || screenResolution == null) {
            // Called early, before init even finished
            return null;
        }
        /*rect.left = rect.left * cameraResolution.y / screenResolution.x - HHSoftDensityUtils.dip2px(getPageContext(), 20);
        rect.right = rect.right * cameraResolution.y / screenResolution.x + HHSoftDensityUtils.dip2px(getPageContext(), 20);
        rect.top = rect.top * cameraResolution.x / screenResolution.y - HHSoftDensityUtils.dip2px(getPageContext(), 68) - HHSoftScreenUtils.statusBarHeight(getPageContext());
        rect.bottom = rect.bottom * cameraResolution.x / screenResolution.y;*/
        //        Log.e("chen", "getFramingRectInPreview==" + cameraResolution.x + "==" + cameraResolution.y);
        rect.left = rect.left * cameraResolution.y / screenResolution.x;
        rect.right = rect.right * cameraResolution.y / screenResolution.x;
        //        rect.top = rect.top * cameraResolution.x / screenResolution.y - HHSoftDensityUtils.dip2px(getPageContext(), 48) - HHSoftScreenUtils.statusBarHeight(getPageContext());
        rect.top = rect.top * cameraResolution.x / screenResolution.y;
        rect.bottom = rect.bottom * cameraResolution.x / screenResolution.y;
        //        Log.e("chen", "getFramingRectInPreview====" + rect.left + "==" + rect.right + "==" + rect.top + "==" + rect.bottom);
        return rect;
    }

    public synchronized Rect getFramingRect() {
        if (framingRect == null) {
            Point screenResolution = cameraManager.getScreenResolution();
            if (screenResolution == null) {
                // Called early, before init even finished
                return null;
            }

            //            MAX_FRAME_WIDTH = HHSoftScreenUtils.realScreenHeight(getPageContext()) / 10 * 9;
            //            MAX_FRAME_HEIGHT = HHSoftScreenUtils.screenWidth(getPageContext()) / 10 * 9;
            //            int width = findDesiredDimensionInRange(screenResolution.x, MIN_FRAME_WIDTH, MAX_FRAME_WIDTH);
            //            int height = findDesiredDimensionInRange(screenResolution.y, MIN_FRAME_HEIGHT, MAX_FRAME_HEIGHT);
            ////            Log.e("chen", "getFramingRect==" + screenResolution.x + "==" + screenResolution.y);
            ////            Log.e("chen", "getFramingRect====" + width + "==" + height);
            //            int leftOffset = (screenResolution.x - width) / 2;
            //            int topOffset = (screenResolution.y - height) / 2;
            //            framingRect = new Rect(leftOffset, topOffset, leftOffset + width, topOffset + height);
            framingRect = new Rect(0, 0, screenResolution.x, screenResolution.y);
        }

        return framingRect;
    }

    private static int findDesiredDimensionInRange(int resolution, int hardMin, int hardMax) {
        //        int dim = 5 * resolution / 8; // Target 5/8 of each dimension
        //        int dim = 9 * resolution / 10; // Target 5/8 of each dimension
        int dim = resolution; // Target 5/8 of each dimension
        if (dim < hardMin) {
            return hardMin;
        }
        return Math.min(dim, hardMax);
    }

    public Rect getCropRect() {
        return mCropRect;
    }
}