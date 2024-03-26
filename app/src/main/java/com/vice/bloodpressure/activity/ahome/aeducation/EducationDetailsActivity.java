package com.vice.bloodpressure.activity.ahome.aeducation;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import androidx.core.content.ContextCompat;

import com.lzx.starrysky.SongInfo;
import com.lzx.starrysky.StarrySky;
import com.lzx.starrysky.utils.TimerTaskManager;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.TurnUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;
import com.vice.bloodpressure.view.X5WebView;

import java.io.IOException;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:type 1视频 2图文 3音频
 * fromWhere  1:智能学习详情  2：智能学习搜索  3：我的收藏
 * 描述:教育详情
 */
public class EducationDetailsActivity extends UIBaseLoadActivity {
    /**
     * 倒计时
     */
    private CountDownTimer timer;

    private LinearLayout audioLinearLayout;
    private TextView titleTextView;
    private SeekBar audioSeekBar;
    private TextView startTimeTextView;
    private TextView allTimeTextView;
    private ImageView startImageView;
    private JzvdStd videoJz;
    private X5WebView webView;
    private ProgressBar progressBar;
    private TextView countdownTextView;
    private ImageView collectImageView;
    private LinearLayout countdownCollectLl;
    /**
     * 1视频 2图文 3音频
     */
    private String type;
    /**
     * 1:智能学习详情  2：智能学习搜索  3：我的收藏
     */
    private String fromWhere;

    private int clickCount;

    private TimerTaskManager taskManager;

    private EducationInfo educationInfo;
    ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("详情");
        type = getIntent().getStringExtra("type");
        fromWhere = getIntent().getStringExtra("fromWhere");

        Log.i("yys", "type===" + type + "fromWhere==" + fromWhere);
        initView();
        initAudioProgress();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void onPageLoad() {
        if ("1".equals(fromWhere)) {
            editStudyStates("1");
        } else {
            getEducationInfo();
        }
    }


    private void editStudyStates(String status) {
        String sid = getIntent().getStringExtra("sid");
        String essayId = getIntent().getStringExtra("essayId");
        Call<String> requestCall = HomeDataManager.teachEssayAddOrUpdate(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ARCHIVES_ID), sid, essayId, status, (call, response) -> {
            if (response.data) {
                if ("2".equals(status)) {
                    countdownTextView.setBackground(ContextCompat.getDrawable(getPageContext(), R.drawable.education_details_start_unbg));
                } else {
                    getEducationInfo();
                }

            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, throwable) -> loadViewManager().changeLoadState(LoadStatus.FAILED));
        addRequestCallToMap("teachEssayAddOrUpdate", requestCall);
    }

    private void getEducationInfo() {
        String sid = getIntent().getStringExtra("sid");
        String essayId = getIntent().getStringExtra("essayId");
        Call<String> requestCall = HomeDataManager.teachEssayInfo(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ARCHIVES_ID), sid, essayId, (call, response) -> {
            if ("0000".equals(response.code)) {
                educationInfo = (EducationInfo) response.object;
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                setData();
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, throwable) -> loadViewManager().changeLoadState(LoadStatus.FAILED));
        addRequestCallToMap("teachEssayInfo", requestCall);
    }

    private void setData() {
        long millisInFuture = TurnUtils.getInt(educationInfo.getVideoTime(), 0) * 1000;
        timer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //                long time = (millisInFuture - millisUntilFinished) / 1000;
                long time = (millisUntilFinished) / 1000;
                countdownTextView.setText("我已学习(" + time + "s)");
            }

            @Override
            public void onFinish() {
                editStudyStates("2");
            }
        };
        timer.start();
        //        CountDownTask.getInstence().showTimer(allTimeTextView, TurnUtils.getInt(educationInfo.getVideoTime(),0), getPageContext());
        //1视频 2图文 3音频
        if ("1".equals(type)) {
            setVideoInfo();
            setWebViewData(webView, educationInfo.getIframeUrl());
        } else if ("2".equals(type)) {
            setWebViewData(webView, educationInfo.getIframeUrl());
        } else {
            setAudioClick();
            setWebViewData(webView, educationInfo.getIframeUrl());
        }
        //0:收藏，1：未收藏
        if ("0".equals(educationInfo.getCollectOrNot())) {
            collectImageView.setImageDrawable(ContextCompat.getDrawable(getPageContext(), R.drawable.education_audio_star_check));
        } else {
            collectImageView.setImageDrawable(ContextCompat.getDrawable(getPageContext(), R.drawable.education_audio_star_uncheck));
        }
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
        videoJz.setUp(educationInfo.getVideoUrl(), "");
        XyImageUtils.loadImage(getPageContext(), R.drawable.shape_defaultbackground_0, educationInfo.getCoverUrl(), videoJz.posterImageView);
    }


    private void setAudioClick() {
        Log.i("yys", "setAudioClick");
        audioSeekBar.setEnabled(true);
        clickCount = clickCount + 1;
        AnimationDrawable am = (AnimationDrawable) startImageView.getBackground();
        Boolean oddNumber = isOddNumber(clickCount);
        Log.i("yys", "oddNumber==" + oddNumber);
        if (oddNumber) {
            Log.i("yys", "oddNumber=====");
            am.start();
            //            int id = getIntent().getExtras().getInt("id", 1);
            int id = 2;
            //            String audioUrl = getIntent().getExtras().getString("url");
            String audioUrl = educationInfo.getVideoUrl();
            //开始播放
            SongInfo info = new SongInfo();
            info.setSongId(id + "");
            info.setSongUrl(audioUrl);
            Log.i("yys", "info==" + info == null ? "1" : "0");
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
        titleTextView.setText(educationInfo.getEssayName());
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(educationInfo.getVideoUrl());
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
                //                if (newProgress == 100) {
                //                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                //
                //                } else {
                //                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                //                    progressBar.setProgress(newProgress);//设置进度值
                //                }
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
        countdownTextView = view.findViewById(R.id.tv_education_details_study_time);
        collectImageView = view.findViewById(R.id.iv_education_details_study_collect);
        countdownCollectLl = view.findViewById(R.id.ll_education_details_study_time);
        containerView().addView(view);
        // 1视频 2图文 3音频
        if ("1".equals(type)) {
            audioLinearLayout.setVisibility(View.GONE);
            videoJz.setVisibility(View.VISIBLE);
        } else if ("2".equals(type)) {
            audioLinearLayout.setVisibility(View.GONE);
            videoJz.setVisibility(View.GONE);
        } else {
            audioLinearLayout.setVisibility(View.VISIBLE);
            videoJz.setVisibility(View.GONE);
        }

        if ("1".equals(fromWhere)) {
            countdownTextView.setVisibility(View.VISIBLE);
            collectImageView.setVisibility(View.VISIBLE);
        } else if ("2".equals(fromWhere)) {
            countdownTextView.setVisibility(View.GONE);
            collectImageView.setVisibility(View.GONE);
        } else {
            countdownTextView.setVisibility(View.GONE);
            collectImageView.setVisibility(View.VISIBLE);
        }
        startImageView.setOnClickListener(v -> {
            setAudioClick();
        });
        collectImageView.setOnClickListener(v -> {
            //操作1 收藏  2取消收藏
            // collectOrNot 0:收藏，1：未收藏
            collectOperate("0".equals(educationInfo.getCollectOrNot()) ? "2" : "1");
        });
    }

    /**
     * @param isCollect 1收藏/2取消收藏
     */
    private void collectOperate(String isCollect) {
        Call<String> requestCall = ServiceDataManager.mealCollectOperate(UserInfoUtils.getArchivesId(getPageContext()), educationInfo.getEssayId(), "1", isCollect, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if (response.data) {
                if ("1".equals(isCollect)) {
                    // 0:收藏，1：未收藏
                    educationInfo.setCollectOrNot("0");
                    collectImageView.setImageDrawable(ContextCompat.getDrawable(getPageContext(), R.drawable.education_audio_star_check));
                } else {
                    educationInfo.setCollectOrNot("1");
                    collectImageView.setImageDrawable(ContextCompat.getDrawable(getPageContext(), R.drawable.education_audio_star_uncheck));
                }
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("mealCollectOperate", requestCall);
    }

    /**
     * 停止播放
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        StarrySky.with().stopMusic();
        taskManager.removeUpdateProgressTask();
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


}
