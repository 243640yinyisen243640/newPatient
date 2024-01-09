package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.popwindow.AnswerForPopupWindow;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:首页答题页面
 */
public class AnswerBeginActivity extends UIBaseActivity {
    private AnswerForPopupWindow forPopupWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        topViewManager().backTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(),MainActivity.class)));
        topViewManager().moreTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(), MainActivity.class)));
        initView();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_anwser_begin, null);
        TextView sureTv = view.findViewById(R.id.tv_answer_begin_sure);
        sureTv.setOnClickListener(v ->
        {
            if (forPopupWindow == null) {
                forPopupWindow = new AnswerForPopupWindow(getPageContext(),"1",
                        other -> {
                            startActivity(new Intent(getPageContext(), AnswerFirstActivity.class));
                        },
                        self -> {
                            startActivity(new Intent(getPageContext(), AnswerFirstActivity.class));
                        });
            }
            forPopupWindow.showAtLocation(containerView(), 0, 0, Gravity.CENTER);


        });
        containerView().addView(view);
    }
}
