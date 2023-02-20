package com.vice.bloodpressure.fragment.fhome;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.utils.ChartUtils;

import java.util.ArrayList;

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
        initValues();
    }

    private ArrayList<Entry> yValues1;
    private int color1 = Color.parseColor("#00C27F");

    float max = 20;
    float min = 0;

    private void initValues() {
        yValues1 = new ArrayList<>();
        yValues1.add(new Entry(1, 5));//这里可以添加Object  Entry(float x, float y, Object data)
        yValues1.add(new Entry(2, 7));
        yValues1.add(new Entry(3, 10));
        yValues1.add(new Entry(4, 16));
        yValues1.add(new Entry(5, 5));
        yValues1.add(new Entry(6, 9));
        yValues1.add(new Entry(7, 5));
        yValues1.add(new Entry(8, 12));

        new ChartUtils().setLineCharts(lineChart, max, min, yValues1, color1, null, 0);

        BmiMarkView mv = new BmiMarkView(getPageContext());
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_home_bmi, null);

        addTv = view.findViewById(R.id.tv_warning_bmi_add);
        lineChart = view.findViewById(R.id.lc_warning_bmi);
        numAndStateTv = view.findViewById(R.id.tv_warning_bmi_num_state);
        timeTv = view.findViewById(R.id.tv_warning_bmi_time);
        containerView().addView(view);
    }

    public class BmiMarkView extends MarkerView {
        private TextView tvData1;
        private TextView tvData1_;
        private TextView tvData2;
        private TextView tvDataUnit;
        private TextView tvDataTime;

        public BmiMarkView(Context context) {
            super(context, R.layout.my_marker_view);
            tvData1 = findViewById(R.id.mv_data1);
            tvData1_ = findViewById(R.id.mv_data1_);
            tvData2 = findViewById(R.id.mv_data2);
            tvDataUnit = findViewById(R.id.mv_data_unit);
            tvDataTime = findViewById(R.id.mv_data_time);
            tvData1_.setVisibility(GONE);
            tvData2.setVisibility(GONE);
        }

        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            tvData1.setText(String.valueOf(e.getY()));
            //            e.getData()  //这里获取上面放进去的数据在这里获取需要展示的数据

            super.refreshContent(e, highlight);
        }
    }
}
