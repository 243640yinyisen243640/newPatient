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
public class HomeXueTangFragment extends UIBaseFragment {
    private TextView addTv;
    private LineChart xuetangCh;
    private TextView warningTv;
    private TextView numTv;


    public static HomeXueTangFragment getInstance(String text) {

        HomeXueTangFragment xueTangFragment = new HomeXueTangFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        xueTangFragment.setArguments(bundle);
        return xueTangFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        initValues();
    }

    private void initValues() {
//        LineChartUtils.initChart(xuetangCh, true, true, false);
//        LineChartUtils.setXAxis(xuetangCh, 1, 56, 1);
//        List<Entry> values1 = new ArrayList<>();
//        LineChartUtils.notifyDataSetChanged(xuetangCh,values1, );
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_home_xuetang, null);
        addTv = view.findViewById(R.id.tv_home_fm_xt_add);
        xuetangCh = view.findViewById(R.id.lc_home_fm_xt);
        warningTv = view.findViewById(R.id.tv_home_fm_xt_warning);
        numTv = view.findViewById(R.id.tv_home_fm_xt_num);
        containerView().addView(view);
    }

}
