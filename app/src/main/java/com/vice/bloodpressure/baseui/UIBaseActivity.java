package com.vice.bloodpressure.baseui;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.DefaultTopViewManager;
import com.vice.bloodpressure.utils.StatusBarUtils;


public abstract class UIBaseActivity extends BaseActivity {
    private LinearLayout contentView;
    private FrameLayout containerView;
    private DefaultTopViewManager topViewManager;
    private boolean isScreen = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = new LinearLayout(getPageContext());
        contentView.setOrientation(LinearLayout.VERTICAL);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (isNeedFullScreen()) {
            isScreen = StatusBarUtils.fullScreenWithStatusBarColor(this, R.color.transparent, false);
            topViewManager = new DefaultTopViewManager(this, isScreen);
        } else {
            topViewManager = new DefaultTopViewManager(this, false);
        }
        contentView.addView(topViewManager.topView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        containerView = new FrameLayout(getPageContext());

        contentView.addView(containerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        setContentView(contentView);
    }

    protected boolean isFullScreen() {
        return isScreen;
    }

    /**
     * 头部管理器
     *
     * @return
     */
    protected DefaultTopViewManager topViewManager() {
        return topViewManager;
    }


    /**
     * 父布局，包含头部
     *
     * @return
     */
    protected LinearLayout contentView() {
        return contentView;
    }

    /**
     * 内容布局，不包含头部
     *
     * @return
     */
    protected FrameLayout containerView() {
        return containerView;
    }
}
