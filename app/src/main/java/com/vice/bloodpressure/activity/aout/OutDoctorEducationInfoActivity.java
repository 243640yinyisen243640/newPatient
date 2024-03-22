package com.vice.bloodpressure.activity.aout;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lzx.starrysky.SongInfo;
import com.lzx.starrysky.StarrySky;
import com.lzx.starrysky.utils.TimerTaskManager;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.OutDataManager;
import com.vice.bloodpressure.model.MessageInfo;
import com.vice.bloodpressure.utils.XyImageUtils;
import com.vice.bloodpressure.view.X5WebView;

import java.io.IOException;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:type :宣教类型:1->图文;2->音频;3->视频;
 * 描述:宣教详情
 */
public class OutDoctorEducationInfoActivity extends UIBaseLoadActivity implements View.OnClickListener {

    private LinearLayout audioLinearLayout;
    private TextView titleTextView;
    private TextView timeTextView;
    private SeekBar audioSeekBar;
    private TextView startTimeTextView;
    private TextView allTimeTextView;
    private ImageView startImageView;
    private JzvdStd videoJz;
    private X5WebView webView;
    private ProgressBar progressBar;
    /**
     * 1->图文;2->音频;3->视频;
     */
    private String type;

    private int clickCount;

    private TimerTaskManager taskManager;

    private MessageInfo messageInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("宣教详情");
        type = getIntent().getStringExtra("type");

        initView();
        initListener();
        initAudioProgress();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        startImageView.setOnClickListener(this);

        //设置滑动监听
        audioSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                StarrySky.with().seekTo(seekBar.getProgress(), true);
            }
        });
    }

    /**
     * 视频信息
     *
     * @param
     */
    private void setVideoInfo() {
        //        int width = ScreenUtils.screenWidth(getPageContext());
        //        int height = width * 9 / 16;
        //        FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(width, height);
        //        videoJz.setLayoutParams(ll);
        Jzvd.SAVE_PROGRESS = false;
        videoJz.setUp(messageInfo.getFileUrl(), "");
        XyImageUtils.getFirst(messageInfo.getFileUrl(), videoJz.posterImageView);

        //视频高度充满
        Jzvd.setTextureViewRotation(90);   //视频旋转90度

        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);

        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_ORIGINAL);  //原始大小

    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_doctor_education_info, null);
        audioLinearLayout = view.findViewById(R.id.ll_education_details_audio_doctor);
        titleTextView = view.findViewById(R.id.tv_education_details_audio_title_doctor);
        timeTextView = view.findViewById(R.id.tv_education_details_audio_time_doctor);
        audioSeekBar = view.findViewById(R.id.sb_education_details_audio_doctor);
        startTimeTextView = view.findViewById(R.id.tv_education_details_audio_start_time_doctor);
        allTimeTextView = view.findViewById(R.id.tv_education_details_audio_all_time_doctor);
        startImageView = view.findViewById(R.id.iv_education_details_audio_start_doctor);
        videoJz = view.findViewById(R.id.jz_education_details_video_doctor);
        webView = view.findViewById(R.id.web_education_details_web_doctor);
        progressBar = view.findViewById(R.id.pb_education_details_web_doctor);
        containerView().addView(view);


    }


    private void setAudioClick() {
        audioSeekBar.setEnabled(true);
        clickCount = clickCount + 1;
        AnimationDrawable am = (AnimationDrawable) startImageView.getBackground();
        Boolean oddNumber = isOddNumber(clickCount);
        if (oddNumber) {
            am.start();
            int id = 2;
            String audioUrl = messageInfo.getFileUrl();
            //开始播放
            SongInfo info = new SongInfo();
            info.setSongId(id + "");
            info.setSongUrl(audioUrl);
            StarrySky.with().playMusicByInfo(info);
            //开始更新进度
            taskManager.startToUpdateProgress(1000);
        } else {
            am.stop();
            am.selectDrawable(0);
            //暂停播放
            StarrySky.with().pauseMusic();
            //结束更新进度
            taskManager.stopToUpdateProgress();
        }
    }

    @Override
    protected void onPageLoad() {
        String pkid = getIntent().getStringExtra("pkid");
        Call<String> requestCall = OutDataManager.getDoctorEducationInfo(pkid, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                messageInfo = (MessageInfo) response.object;
                bindData();
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getDoctorEducationInfo", requestCall);

    }

    private void bindData() {
        //宣教类型:1->图文;2->音频;3->视频;
        titleTextView.setText(messageInfo.getTitle());
        timeTextView.setText(messageInfo.getSendTime());

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(messageInfo.getFileUrl());
            mediaPlayer.prepare();
            int duration = mediaPlayer.getDuration();
            if (0 != duration) {
                int s = duration / 1000;
                //设置文件时长，单位 "分:秒" 格式
                String total = s / 60 + ":" + s % 60;
                allTimeTextView.setText(total);
                //记得释放资源
                mediaPlayer.release();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if ("1".equals(messageInfo.getType())) {
            audioLinearLayout.setVisibility(View.GONE);
            videoJz.setVisibility(View.GONE);
            setWebViewData(webView, messageInfo.getIframeUrl());
        } else if ("2".equals(messageInfo.getType())) {
            audioLinearLayout.setVisibility(View.VISIBLE);
            videoJz.setVisibility(View.GONE);
            setWebViewData(webView, messageInfo.getIframeUrl());
        } else {
            audioLinearLayout.setVisibility(View.GONE);
            videoJz.setVisibility(View.VISIBLE);
            setVideoInfo();
            setWebViewData(webView, messageInfo.getIframeUrl());
        }


    }

    protected void setWebViewData(final X5WebView webView, final String url) {
        initHardwareAccelerate();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // H5页面加载不出图片
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setBlockNetworkImage(false);
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失

                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
                super.onProgressChanged(webView, newProgress);
            }
        });
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        } catch (Exception e) {
        }
    }

    private void initAudioProgress() {
        //未开始播放
        audioSeekBar.setEnabled(false);
        taskManager = new TimerTaskManager();
        taskManager.setUpdateProgressTask(() -> {
            //设置进度条
            //总时长
            long duration = StarrySky.with().getDuration();
            Log.i("yys", "duration==" + duration);
            //播放位置
            long position = StarrySky.with().getPlayingPosition();
            Log.i("yys", "position==" + position);
            //缓冲位置
            long buffered = StarrySky.with().getBufferedPosition();
            Log.i("yys", "buffered==" + buffered);
            if (audioSeekBar.getMax() != duration) {
                audioSeekBar.setMax((int) duration);
            }
            audioSeekBar.setProgress((int) position);
            audioSeekBar.setSecondaryProgress((int) buffered);

            //设置进度文字
            startTimeTextView.setText(formatMusicTime(position));
        });


    }

    /**
     * 格式化毫秒为时长
     *
     * @param time
     * @return
     */
    public static String formatMusicTime(long time) {
        String min = (time / (1000 * 60)) + "";
        String second = (time % (1000 * 60) / 1000) + "";
        if (min.length() < 2) {
            min = 0 + min;
        }
        if (second.length() < 2) {
            second = 0 + second;
        }
        return min + ":" + second;
    }

    /**
     * 判断是否是奇数
     *
     * @param number 数值
     * @return
     */
    public Boolean isOddNumber(int number) {
        if ((number & 1) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 停止播放
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        StarrySky.with().stopMusic();
        taskManager.removeUpdateProgressTask();
        videoJz.onStatePause();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_education_details_audio_start_doctor:
                setAudioClick();
                break;
            default:
                break;
        }
    }
}
