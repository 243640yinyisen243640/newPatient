package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lzx.starrysky.StarrySky;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.dialog.XySoftDialogActionEnum;
import com.vice.bloodpressure.model.ExerciseChildInfo;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.TurnUtils;
import com.vice.bloodpressure.utils.XyImageUtils;
import com.vice.bloodpressure.window.JZVideoPlayer;

import cn.jzvd.Jzvd;
import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:跟着视频运动
 */
public class ExerciseRecordAddHandActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private static final int START_TIME = 10;
    private JZVideoPlayer jzVideoPlayer;
    private TextView timeTv;
    private TextView fireTv;
    private ImageView pauseImageView;
    private ImageView stopImageView;

    private long allTime = 0;


    private String startOrPause = "1";

    private ExerciseChildInfo exerciseChildInfo;
    /**
     * 运动时间
     */
    private int minuteTime;
    /**
     * 运动时间消耗的热量
     */
    private String fireNum = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().moreTextView().setText("手动添加记录");

        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }


    private void initListener() {
        pauseImageView.setOnClickListener(this);
        stopImageView.setOnClickListener(this);
        jzVideoPlayer.setOnStateAutoComplete(() -> {
            //点击直接播放完

        });
        //        jzVideoPlayer.setOnStateStart(() -> {
        //
        //        });
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
    protected void processHandlerMsg(Message msg) {
        super.processHandlerMsg(msg);
        if (msg.what == START_TIME) {
            mHandler.sendEmptyMessageDelayed(START_TIME, 1_000);
            if (TextUtils.equals(startOrPause, "2")) {
                allTime = allTime + 1_000;
                Log.i("yys", "allTime==" + allTime);
                if (allTime % (1_000 * 60) == 0) {
                    //更新ui
                    minuteTime = (int) (allTime / 1000);
                    Log.i("yys", "minuteTime==" + minuteTime);
                    //TurnUtils.getDouble(minuteTime + "", 0) * TurnUtils.stringToDoubleFour(exerciseChildInfo.getCalorie()) * TurnUtils.stringToDoubleOne(exerciseChildInfo.getWeight())
                    fireNum = String.valueOf((int) (TurnUtils.getDouble(minuteTime + "", 0) * TurnUtils.stringToDoubleFour(exerciseChildInfo.getCalorie()) * TurnUtils.stringToDoubleOne(exerciseChildInfo.getWeight())));
                    timeTv.setText(String.valueOf(minuteTime / 60));
                    fireTv.setText(fireNum);
                }
            }
        }
    }

    /**
     *
     */
    private void setData() {
        topViewManager().titleTextView().setText(exerciseChildInfo.getSportName());
        Log.i("yys", "exerciseChildInfo.getVideoUrl()==" + exerciseChildInfo.getVideoUrl());
        //        jzVideoPlayer.setUp("https://vd3.bdstatic.com/mda-mcjm50zbmckqbcwt/haokan_t/dash/1659566940889437712/mda-mcjm50zbmckqbcwt-1.mp4", "");
        //        jzVideoPlayer.setUp("http://120.55.60.103:9000/educational/c331a553f39c3453781474bbe22061b5.mp4", "");
        //        jzVideoPlayer.setUp("http://120.55.60.103:9000/diet/2fb763bb78694982a610806038e86f0f.mp4", "");
        jzVideoPlayer.setUp(exerciseChildInfo.getVideoUrl(), "");
        XyImageUtils.loadImage(getPageContext(), R.drawable.shape_defaultbackground_0, exerciseChildInfo.getCoverUrl(), jzVideoPlayer.posterImageView);
        jzVideoPlayer.startVideo();
        startOrPause = "2";
        topViewManager().moreTextView().setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), ExercisePlanAddRecordActivity.class);
            intent.putExtra("weight", exerciseChildInfo.getWeight());
            startActivity(intent);
        });
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
                break;
            case R.id.iv_exercise_hand_stop_oxy:
                DialogUtils.showOperDialog(getPageContext(), "", "确定要结束运动吗？", "取消", "确定", (dialog, which) -> {
                    dialog.dismiss();
                    if (XySoftDialogActionEnum.POSITIVE == which) {
                        stopExercise();
                    }
                });
                break;

            default:
                break;
        }
    }

    private void stopExercise() {
        String id = getIntent().getStringExtra("sportId");
        Call<String> requestCall = HomeDataManager.endSport(minuteTime + "", SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ARCHIVES_ID), fireNum, id,(call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if (response.code.equals("0000")) {
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("endSport", requestCall);
    }

    /**
     * 停止播放
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        StarrySky.with().stopMusic();
        jzVideoPlayer.onStatePause();
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
    protected void onResume() {
        super.onResume();
        String id = getIntent().getStringExtra("sportId");
        Call<String> requestCall = HomeDataManager.aerobicsDetails(id, SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ARCHIVES_ID), (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                exerciseChildInfo = (ExerciseChildInfo) response.object;
                setData();
                mHandler.sendEmptyMessage(START_TIME);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("aerobicsDetails", requestCall);
    }
}



