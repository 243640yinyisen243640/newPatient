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
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.fragment.InputNumDialogAddFragment;
import com.vice.bloodpressure.fragment.InputNumDialogFragment;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import cn.jzvd.JzvdStd;
import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:type  R 抗阻  P 柔韧
 * 描述:跟着视频运动
 */
public class ExerciseRecordAddHandFlexActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private JzvdStd jzVideoPlayer;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getStringExtra("type");
        sportId = getIntent().getStringExtra("sportId");
        topViewManager().titleTextView().setText(getIntent().getStringExtra("title"));
        topViewManager().moreTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(), ExercisePlanAddRecordActivity.class)));
        initView();
        initListener();
        initValues();
    }

    private void initListener() {
        pauseImageView.setOnClickListener(this);
        stopImageView.setOnClickListener(this);
        recordTextView.setOnClickListener(this);

    }

    private void initValues() {
        jzVideoPlayer.setUp("https://vd3.bdstatic.com/mda-mcjm50zbmckqbcwt/haokan_t/dash/1659566940889437712/mda-mcjm50zbmckqbcwt-1.mp4", "", JzvdStd.SCREEN_NORMAL);
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
    protected void onPageLoad() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_exercise_hand_pause_flex:
                if ("1".equals(startOrPause)) {
                    startOrPause = "2";
                    pauseImageView.setImageResource(R.drawable.exercise_hand_pause);
                    jzVideoPlayer.setUp("https://vd3.bdstatic.com/mda-mcjm50zbmckqbcwt/haokan_t/dash/1659566940889437712/mda-mcjm50zbmckqbcwt-1.mp4", "");
                    jzVideoPlayer.startVideo();
                } else {
                    Log.i("yys", "onStatePause==");
                    //                    pauseImageView.setImageResource(R.drawable.exercise_hand_star);
                    //                    jzVideoPlayer.onStatePause();
                    jzVideoPlayer.mediaInterface.pause();
                }

                break;
            case R.id.iv_exercise_hand_stop_flex:
                BaseDataManager.EXERCISE_IS_COMPLETE = 2;
                jzVideoPlayer.onStateAutoComplete();
                break;
            case R.id.tv_exercise_hand_record_flex:
                //  type  R 抗阻  P 柔韧
                if ("R".equals(type)) {
                    showNumDialog();
                } else {
                    showFlexibilityNumDialog();
                }

                break;

            default:
                break;
        }
    }

    private void showNumDialog() {
        InputNumDialogFragment dialog = new InputNumDialogFragment(inputStr -> {
            //TODO 提交反馈信息
            submitOxygen(inputStr);
        });
        dialog.showNow(getSupportFragmentManager(), "inputFlex");
    }

    private void showFlexibilityNumDialog() {
        InputNumDialogAddFragment dialog = new InputNumDialogAddFragment(inputStr -> {
            //TODO 提交反馈信息
            submitOxygen(inputStr);
        });
        dialog.showNow(getSupportFragmentManager(), "inputFlex");
    }

    /**
     * @param inputStr
     */
    private void submitOxygen(String inputStr) {
        Call<String> requestCall = HomeDataManager.addPliableResistanceRecord(sportId, inputStr, type, UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("addPliableResistanceRecord", requestCall);
    }


}



