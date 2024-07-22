package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.utils.RxTimerUtils;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:智能运动倒计时  R 抗阻  P 柔韧  O:有氧
 */
public class ExerciseCountdownActivity extends UIBaseActivity {
    private TextView timeTv;

    private String type;
    private RxTimerUtils rxTimerUtils;

    private int count = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        type = getIntent().getStringExtra("type");
        initView();
        setTimeCountDown();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_countdown, null);
        timeTv = view.findViewById(R.id.tv_exercise_countdown_num);
        containerView().addView(view);
    }

    private void setTimeCountDown() {
        setCountDownView(count);

        rxTimerUtils = new RxTimerUtils();
        rxTimerUtils.intervalInRange(1000, 3000, 1000, new RxTimerUtils.RxAction() {
            @Override
            public void action(long number) {
                count --;
                setCountDownView(count);
                if (count == 1){
                    //三秒以后跳转页面  R 抗阻  P 柔韧  O:有氧
                    finish();
                    String sportId = getIntent().getStringExtra("sportId");
                    Intent intent;
                    if ("O".equals(type)) {
                        intent = new Intent(getPageContext(), ExerciseRecordAddHandActivity.class);
                    } else {
                        intent = new Intent(getPageContext(), ExerciseRecordAddHandFlexActivity.class);
                        intent.putExtra("type", type);
                    }
                    intent.putExtra("sportId", sportId);
                    startActivity(intent);
                }

            }
        });

    }


    private void setCountDownView(int mCount) {
        ScaleAnimation animation = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置持续时间
        animation.setDuration(1000);
        //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
        animation.setFillAfter(true);
        //设置循环次数，0为1次
        animation.setRepeatCount(0);
        timeTv.setText(String.valueOf(mCount));
        timeTv.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        rxTimerUtils.cancel();
    }
}
