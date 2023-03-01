package com.vice.bloodpressure.activity.ahome.aeducation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
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
import com.vice.bloodpressure.view.X5WebView;

import cn.jzvd.JzvdStd;

/**
 * 作者: beauty
 * 类名:
 * 传参:1:视频  2：音频
 * 描述:教育详情
 */
public class EducationDetailsActivity extends UIBaseLoadActivity {

    private LinearLayout audioLinearLayout;
    private TextView titleTextView;
    private SeekBar audioSeekBar;
    private TextView startTimeTextView;
    private TextView allTimeTextView;
    private ImageView startImageView;
    private JzvdStd videoJz;
    private X5WebView webView;
    private ProgressBar progressBar;
    /**
     * 1:视频  2：音频
     */
    private String type;

    private int clickCount;

    private TimerTaskManager taskManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("详情");
        type = getIntent().getStringExtra("type");
        initView();
        initAudioProgress();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_education_details, null);
        audioLinearLayout = view.findViewById(R.id.ll_education_details_audio);
        titleTextView = view.findViewById(R.id.tv_education_details_audio_title);
        audioSeekBar = view.findViewById(R.id.sb_education_details_audio);
        startTimeTextView = view.findViewById(R.id.tv_education_details_audio_start_time);
        allTimeTextView = view.findViewById(R.id.tv_education_details_audio_all_time);
        startImageView = view.findViewById(R.id.iv_education_details_audio_start);
        videoJz = view.findViewById(R.id.jz_education_details_video);
        webView = view.findViewById(R.id.web_education_details_web);
        progressBar = view.findViewById(R.id.pb_education_details_web);
        containerView().addView(view);
        if ("1".equals(type)) {
            audioLinearLayout.setVisibility(View.GONE);
            videoJz.setVisibility(View.VISIBLE);
        } else {
            audioLinearLayout.setVisibility(View.VISIBLE);
            videoJz.setVisibility(View.GONE);
        }
        startImageView.setOnClickListener(v -> {
            setAudioClick();
        });
    }


    private void setAudioClick() {
        audioSeekBar.setEnabled(true);
        clickCount = clickCount + 1;
        AnimationDrawable am = (AnimationDrawable) startImageView.getBackground();
        if (isOddNumber(clickCount)) {
            am.start();
            int id = getIntent().getExtras().getInt("id", 0);
            String audioUrl = getIntent().getExtras().getString("link");
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
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        setWebViewData(webView, "http://chronics.xiyuns.cn/index/caseapp");
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
        taskManager.setUpdateProgressTask(new Runnable() {
            @Override
            public void run() {
                //设置进度条
                //总时长
                long duration = StarrySky.with().getDuration();
                //播放位置
                long position = StarrySky.with().getPlayingPosition();
                //缓冲位置
                long buffered = StarrySky.with().getBufferedPosition();
                if (audioSeekBar.getMax() != duration) {
                    audioSeekBar.setMax((int) duration);
                }
                audioSeekBar.setProgress((int) position);
                audioSeekBar.setSecondaryProgress((int) buffered);

                //设置进度文字
                startTimeTextView.setText(formatMusicTime(position));
            }
        });


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
//        StarrySky.with().stopMusic();
        taskManager.removeUpdateProgressTask();
    }
}
