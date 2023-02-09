package com.vice.bloodpressure.basevideo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.utils.DensityUtils;

import cn.jzvd.JzvdStd;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class JZVideoPlayer extends JzvdStd {
    private onStateAutoComplete onStateAutoComplete;
    private onClickUiToggle onClickUiToggle;

    private Context context;

    /**
     * 是否可以快进
     */
    private boolean isCanForward = true;

    private boolean isFirst = false;

    private TextView textView;

    public JZVideoPlayer(Context context) {
        super(context);
        this.context = context;
        this.onStateAutoComplete = onStateAutoComplete;
        this.onClickUiToggle = onClickUiToggle;
    }

    public JZVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void onStateAutoComplete() {
        if (onStateAutoComplete != null) {
            onStateAutoComplete.onStateAutoComplete();
        }
        super.onStateAutoComplete();

    }

    @Override
    protected void touchActionDown(float x, float y) {
        isFirst = !isFirst;
        if (textView != null) {
            if (isFirst) {
                textView.setVisibility(VISIBLE);
            } else {
                textView.setVisibility(GONE);
            }
        }
        if (isCanForward) {
            super.touchActionDown(x, y);
        }

    }

    @Override
    protected void touchActionMove(float x, float y) {


        if (isCanForward) {
            super.touchActionMove(x, y);
        }


    }

    @Override
    protected void touchActionUp() {

        if (isCanForward) {
            super.touchActionUp();
        }

    }

    @Override
    public void onClickUiToggle() {
        super.onClickUiToggle();
        if (onClickUiToggle != null) {
            onClickUiToggle.onClickUiToggle();
        }
    }

    public void setOnStateAutoComplete(JZVideoPlayer.onStateAutoComplete onStateAutoComplete) {
        this.onStateAutoComplete = onStateAutoComplete;
    }

    public void setOnClickUiToggle(JZVideoPlayer.onClickUiToggle onClickUiToggle) {
        this.onClickUiToggle = onClickUiToggle;
    }

    public interface onStateAutoComplete {
        void onStateAutoComplete();
    }

    public interface onClickUiToggle {
        void onClickUiToggle();
    }

    public void setCanForward(boolean canForward) {
        isCanForward = canForward;
    }

    @Override
    public void gotoFullscreen() {
        super.gotoFullscreen();
        textView = new TextView(context);

        textView.setTextColor(Color.WHITE);
        textView.setText("重播");
        textView.setPadding(10, DensityUtils.dip2px(context, 50), DensityUtils.dip2px(context, 20), 10);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ll.gravity = Gravity.RIGHT;
        ll.setMargins(DensityUtils.dip2px(context, 50), 0, 0, 0);
        textView.setLayoutParams(ll);
        textView.setOnClickListener(view -> {
            startVideo();
        });
        if (isFirst) {
            Log.i("yys", "=====");
            textView.setVisibility(VISIBLE);
        } else {
            Log.i("yys", "=====2");

            textView.setVisibility(GONE);
        }
        ((ViewGroup) getParent()).addView(textView);

    }


}
