package com.vice.bloodpressure.baseui;

import android.view.View;
import android.widget.FrameLayout;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.vice.bloodpressure.R;

/**
 * @author android_wjh
 * @date 2020/12/2
 */
public abstract class UIBaseLoadRefreshFragment extends UIBaseLoadFragment {

    private FrameLayout containerView;
    private SmartRefreshLayout refreshLayout;


    @Override
    protected void onCreate() {
        View view = View.inflate(getPageContext(), R.layout.base_activity_load_refresh, null);
        super.containerView().addView(view);
        containerView = view.findViewById(R.id.fl_base_load_refresh_container);
        refreshLayout = view.findViewById(R.id.srl_base_load_refresh);
    }

    @Override
    public FrameLayout containerView() {
        return containerView;
    }

    public SmartRefreshLayout refreshLayout() {
        return refreshLayout;
    }
}
