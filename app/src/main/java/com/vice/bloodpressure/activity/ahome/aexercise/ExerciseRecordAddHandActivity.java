package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.fragment.InputNumDialogFragment;
import com.vice.bloodpressure.popwindow.ExercisePlanHandAddPopupWindow;
import com.vice.bloodpressure.window.JZVideoPlayer;

/**
 * 作者: beauty
 * 类名:
 * 传参:type 1:抗阻和柔韧性   2：有氧
 * 描述:跟着视频运动
 */
public class ExerciseRecordAddHandActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private ExercisePlanHandAddPopupWindow addPopupWindow;
    private JZVideoPlayer jzVideoPlayer;
    private LinearLayout timeAndFireLiner;
    private TextView timeTv;
    private TextView fireTv;
    private ImageView pauseImageView;
    private ImageView stopImageView;
    private TextView recordTextView;
    private LinearLayout recordLinearLayout;
    /**
     * 1:抗阻和柔韧性   2：有氧
     */
    private String type;


    private String startOrPause = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getStringExtra("type");
        topViewManager().titleTextView().setText(getIntent().getStringExtra("title"));
        if ("2".equals(type)) {
            topViewManager().moreTextView().setText("手动添加记录");

        }
        topViewManager().moreTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(), ExercisePlanAddRecordActivity.class)));
        initView();
        initListener();
    }

    private void initListener() {
        pauseImageView.setOnClickListener(this);
        stopImageView.setOnClickListener(this);
        recordTextView.setOnClickListener(this);
        jzVideoPlayer.setOnStateAutoComplete(() -> {
            //点击直接播放完

        });
        jzVideoPlayer.setOnStateStart(() -> {

        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_video, null);
        jzVideoPlayer = view.findViewById(R.id.jz_exercise_video_details);
        timeAndFireLiner = view.findViewById(R.id.ll_exercise_hand_fire);
        timeTv = view.findViewById(R.id.tv_exercise_hand_time);
        fireTv = view.findViewById(R.id.tv_exercise_hand_fire);
        pauseImageView = view.findViewById(R.id.iv_exercise_hand_pause);
        stopImageView = view.findViewById(R.id.iv_exercise_hand_stop);
        recordLinearLayout = view.findViewById(R.id.ll_exercise_hand_record);
        recordTextView = view.findViewById(R.id.tv_exercise_hand_record);
        //1:抗阻和柔韧性   2：有氧
        if ("1".equals(type)) {
            timeAndFireLiner.setVisibility(View.VISIBLE);
            recordLinearLayout.setVisibility(View.VISIBLE);
        } else {
            timeAndFireLiner.setVisibility(View.GONE);
            recordLinearLayout.setVisibility(View.GONE);
        }
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

    private void showPop() {
        if (addPopupWindow == null) {
            addPopupWindow = new ExercisePlanHandAddPopupWindow(getPageContext(),

                    chooseView -> {
                        //我自己选
                        EditText editText = addPopupWindow.showContent();
                        String editContent = editText.getText().toString().trim();
                    });
        }

        addPopupWindow.showAsDropDown(containerView(), 0, 0, Gravity.CENTER);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_exercise_hand_pause:
                if ("1".equals(startOrPause)) {
                    Log.i("yys", "click===");
                    startOrPause = "2";
                    pauseImageView.setImageResource(R.drawable.exercise_hand_pause);
                    jzVideoPlayer.setUp("https://fd.aigei.com/src/vdo/mp4/9b/9b538220b0df4aa2b1f19b6ff69164d4.mp4?e=1677496740&token=P7S2Xpzfz11vAkASLTkfHN7Fw-oOZBecqeJaxypL:jh_bbcUpY-PmSmPHrlNl9AtggIU=", "");
                    jzVideoPlayer.startVideo();
                } else {
                    Log.i("yys", "onStatePause==");
                    //                    pauseImageView.setImageResource(R.drawable.exercise_hand_star);
                    //                    jzVideoPlayer.onStatePause();
                    jzVideoPlayer.mediaInterface.pause();
                }

                break;
            case R.id.iv_exercise_hand_stop:
                BaseDataManager.EXERCISE_IS_COMPLETE = 2;
                jzVideoPlayer.onStateAutoComplete();
                break;
            case R.id.tv_exercise_hand_record:
                showNumDialog();
                break;

            default:
                break;
        }
    }


    private void showNumDialog() {
        InputNumDialogFragment dialog = new InputNumDialogFragment(inputStr -> {
            //TODO 提交反馈信息
        });
        dialog.showNow(getSupportFragmentManager(), "input");
    }

}



