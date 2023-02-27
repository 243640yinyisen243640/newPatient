package com.vice.bloodpressure.window;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.BaseDataManager;

import cn.jzvd.JzvdStd;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class JZVideoPlayer extends JzvdStd {
    private onStateAutoComplete onStateAutoComplete;
    private onStateStart onStateStart;

    private Context mContext;
    private LinearLayout startLinearLayout;
    private LinearLayout retryLinearLayout;
    private ImageView startImageView;
    private TextView retryTextView;


    public JZVideoPlayer(Context context) {
        super(context);
        this.mContext = context;
        this.onStateAutoComplete = onStateAutoComplete;
        this.onStateStart = onStateStart;
    }

    public JZVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    //
    //
    @Override
    public int getLayoutId() {
        return R.layout.activity_jzplayer;
    }

    @Override
    public void init(Context context) {
        super.init(context);


        ImageView postImageView = findViewById(R.id.poster);
        startLinearLayout = findViewById(R.id.start_layout);
        retryLinearLayout = findViewById(R.id.retry_layout);
        startImageView = findViewById(R.id.start);
        retryTextView = findViewById(R.id.replay_text);
        postImageView.setOnClickListener(null);

    }

    @Override
    public void startVideo() {
        if (onStateStart != null) {
            onStateStart.onStateStart();
        }
        super.startVideo();

    }

    @Override
    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro, int posterImg, int bottomPro, int retryLayout) {
        super.setAllControlsVisiblity(topCon, bottomCon, startBtn, loadingPro, posterImg, bottomPro, retryLayout);
        startLinearLayout.setVisibility(View.GONE);
        retryTextView.setVisibility(View.GONE);
        retryLinearLayout.setVisibility(View.GONE);
    }

    //    @Override
    //    public void setUp(String url, String title) {
    //        super.setUp(url, title);
    //        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    //        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    //    }

    @Override
    public void onStateAutoComplete() {
        if (onStateAutoComplete != null) {
            onStateAutoComplete.onStateAutoComplete();
        }
        super.onStateAutoComplete();
        //如果想要持续的继续播放，在这个里面写
        //        startVideo
        if (BaseDataManager.EXERCISE_IS_COMPLETE == 1) {
            startVideo();
        }

    }

    public void setOnStateAutoComplete(JZVideoPlayer.onStateAutoComplete onStateAutoComplete) {
        this.onStateAutoComplete = onStateAutoComplete;
    }

    public void setOnStateStart(JZVideoPlayer.onStateStart onStateStart) {
        this.onStateStart = onStateStart;
    }

    public interface onStateAutoComplete {
        void onStateAutoComplete();
    }

    public interface onStateStart {
        void onStateStart();
    }

}
