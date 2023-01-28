package com.vice.bloodpressure.baseui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.DefaultTopViewManager;
import com.vice.bloodpressure.utils.StatusBarUtils;


public abstract class UIBaseFragment extends BaseFragment {
    private LinearLayout contentView;
    private FrameLayout containerView;
    private DefaultTopViewManager topViewManager;

    private boolean isScreen=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = new LinearLayout(getPageContext());
        contentView.setOrientation(LinearLayout.VERTICAL);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (isNeedFullScreen()) {
            isScreen = StatusBarUtils.fullScreenWithStatusBarColor(getActivity(), R.color.transparent, false);
            topViewManager = new DefaultTopViewManager(getActivity(), isScreen);
        } else {
            topViewManager = new DefaultTopViewManager(getActivity(), false);
        }
        contentView.addView(topViewManager.topView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        containerView = new FrameLayout(getPageContext());
        contentView.addView(containerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        onCreate();
        return contentView;
    }
    protected boolean isFullScreen() {
        return isScreen;
    }
    protected abstract void onCreate();

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
