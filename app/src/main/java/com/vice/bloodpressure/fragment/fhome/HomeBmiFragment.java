package com.vice.bloodpressure.fragment.fhome;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseFragment;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HomeBmiFragment extends UIBaseFragment {
    private TextView addTv;
    private LineChart lineChart;
    private TextView numAndStateTv;
    private TextView timeTv;

    public static HomeBmiFragment getInstance(String text) {

        HomeBmiFragment bmiFragment = new HomeBmiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        bmiFragment.setArguments(bundle);
        return bmiFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_home_bmi, null);

        addTv = view.findViewById(R.id.tv_warning_bmi_add);
        lineChart = view.findViewById(R.id.lc_warning_bmi);
        numAndStateTv = view.findViewById(R.id.tv_warning_bmi_num_state);
        timeTv = view.findViewById(R.id.tv_warning_bmi_time);
        containerView().addView(view);
    }
}
