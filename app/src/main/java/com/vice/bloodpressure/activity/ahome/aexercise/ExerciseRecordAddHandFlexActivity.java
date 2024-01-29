package com.vice.bloodpressure.activity.ahome.aexercise;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.fragment.InputNumDialogFragment;
import com.vice.bloodpressure.model.ExerciseChildInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;
import com.vice.bloodpressure.window.JZVideoPlayer;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:type  R 抗阻  P 柔韧
 * 描述:跟着视频运动
 */
public class ExerciseRecordAddHandFlexActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private JZVideoPlayer jzVideoPlayer;
    private ImageView pauseImageView;
    private ImageView stopImageView;
    private TextView recordTextView;

    private String startOrPause = "1";
    /**
     * type  R 抗阻  P 柔韧
     */
    private String type;
    /**
     * 当前运动的ID
     */
    private String sportId;

    private ExerciseChildInfo exerciseChildInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getStringExtra("type");
        sportId = getIntent().getStringExtra("sportId");
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = HomeDataManager.pliableResistanceDetails(sportId, type, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                exerciseChildInfo = (ExerciseChildInfo) response.object;
                setData();
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("aerobicsDetails", requestCall);
    }

    private void setData() {
        topViewManager().titleTextView().setText(exerciseChildInfo.getSportName());
        jzVideoPlayer.setUp(exerciseChildInfo.getVideoUrl(), "", JzvdStd.SCREEN_NORMAL);
        jzVideoPlayer.startVideo();
        startOrPause = "2";
        XyImageUtils.loadImage(getPageContext(), R.drawable.shape_defaultbackground_0, exerciseChildInfo.getCoverUrl(), jzVideoPlayer.posterImageView);
    }

    private void initListener() {
        pauseImageView.setOnClickListener(this);
        stopImageView.setOnClickListener(this);
        recordTextView.setOnClickListener(this);

    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_video_flex, null);
        jzVideoPlayer = view.findViewById(R.id.jz_exercise_video_details_flex);
        pauseImageView = view.findViewById(R.id.iv_exercise_hand_pause_flex);
        stopImageView = view.findViewById(R.id.iv_exercise_hand_stop_flex);
        recordTextView = view.findViewById(R.id.tv_exercise_hand_record_flex);
        containerView().addView(view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_exercise_hand_pause_flex:
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
            case R.id.iv_exercise_hand_stop_flex:
                BaseDataManager.EXERCISE_IS_COMPLETE = 2;
                jzVideoPlayer.onStateAutoComplete();
                break;
            case R.id.tv_exercise_hand_record_flex:
                //  type  R 抗阻  P 柔韧
                showNumDialog();

                break;

            default:
                break;
        }
    }

    /**
     * 这个是抗阻运动的弹出  输入个数的
     */
    private void showNumDialog() {
        InputNumDialogFragment dialog = new InputNumDialogFragment(inputStr -> {
            //TODO 提交反馈信息
            submitOxygen(inputStr);
        });
        dialog.showNow(getSupportFragmentManager(), "inputRe");
    }


    /**
     * @param sportNumber
     */
    private void submitOxygen(String sportNumber) {
        Call<String> requestCall = HomeDataManager.addPliableResistanceRecord(sportId, sportNumber, type, UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("addPliableResistanceRecord", requestCall);
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



