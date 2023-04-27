package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.window.JZVideoPlayer;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:跟着视频运动
 */
public class ExerciseRecordAddHandActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private JZVideoPlayer jzVideoPlayer;
    private TextView timeTv;
    private TextView fireTv;
    private ImageView pauseImageView;
    private ImageView stopImageView;


    private String startOrPause = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText(getIntent().getStringExtra("title"));
        topViewManager().moreTextView().setText("手动添加记录");
        topViewManager().moreTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(), ExercisePlanAddRecordActivity.class)));
        initView();
        initListener();
        initValues();
    }

    private void initValues() {
        jzVideoPlayer.setUp("https://vd3.bdstatic.com/mda-mcjm50zbmckqbcwt/haokan_t/dash/1659566940889437712/mda-mcjm50zbmckqbcwt-1.mp4", "");
    }

    private void initListener() {
        pauseImageView.setOnClickListener(this);
        stopImageView.setOnClickListener(this);
        jzVideoPlayer.setOnStateAutoComplete(() -> {
            //点击直接播放完

        });
        jzVideoPlayer.setOnStateStart(() -> {

        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_video_oxy, null);
        jzVideoPlayer = view.findViewById(R.id.jz_exercise_video_details_oxy);
        timeTv = view.findViewById(R.id.tv_exercise_hand_time_oxy);
        fireTv = view.findViewById(R.id.tv_exercise_hand_fire_oxy);
        pauseImageView = view.findViewById(R.id.iv_exercise_hand_pause_oxy);
        stopImageView = view.findViewById(R.id.iv_exercise_hand_stop_oxy);

        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_exercise_hand_pause_oxy:
                //首先第一次进来  点击是开始 然后再次点击就是暂停了，暂停状态下，再点击是继续播放，不是重新开始，所以有三个数值
                Log.i("yys", "startOrPause==" + startOrPause);
                if ("1".equals(startOrPause)) {
                    startOrPause = "2";
                    pauseImageView.setImageResource(R.drawable.exercise_hand_pause);
                    jzVideoPlayer.startVideo();
                } else if ("2".equals(startOrPause)) {
                    startOrPause = "3";
                    pauseImageView.setImageResource(R.drawable.exercise_hand_star);
                    jzVideoPlayer.mediaInterface.pause();
                    jzVideoPlayer.onStatePause();
                } else {
                    startOrPause = "2";
                    pauseImageView.setImageResource(R.drawable.exercise_hand_pause);
                    jzVideoPlayer.mediaInterface.start();
                    jzVideoPlayer.onStatePlaying();
                }

                //                if ("1".equals(startOrPause)) {
                //                    startOrPause = "2";
                //                    pauseImageView.setImageResource(R.drawable.exercise_hand_pause);
                //                    jzVideoPlayer.startVideo();
                //                } else {
                //                    startOrPause = "1";
                //                    //                    pauseImageView.setImageResource(R.drawable.exercise_hand_star);
                //                    jzVideoPlayer.onStatePause();
                //                    //                    jzVideoPlayer.mediaInterface.pause();
                //                }

                break;
            case R.id.iv_exercise_hand_stop_oxy:
                BaseDataManager.EXERCISE_IS_COMPLETE = 2;
                jzVideoPlayer.onStateAutoComplete();
                break;

            default:
                break;
        }
    }


}



