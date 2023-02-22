package com.vice.bloodpressure.fragment.fhome;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
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
public class HomeXueYaFragment extends UIBaseFragment {
    private TextView addTv;
    private LineChart lineChart;
    private TextView numTv;

    public static HomeXueYaFragment getInstance(String text) {

        HomeXueYaFragment xueYaFragment = new HomeXueYaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        xueYaFragment.setArguments(bundle);
        return xueYaFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        initValues();
    }

    private ArrayList<Entry> yValues1;
    private ArrayList<Entry> yValues2;
    private int color1 = Color.parseColor("#FF5B54");
    private int color2 = Color.parseColor("#458FE3");

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


        yValues2 = new ArrayList<>();
        yValues2.add(new Entry(6, 1));//这里可以添加Object  Entry(float x, float y, Object data)
        yValues2.add(new Entry(3, 4));
        yValues2.add(new Entry(4, 5));
        yValues2.add(new Entry(5, 6));
        yValues2.add(new Entry(5, 5));
        yValues2.add(new Entry(6, 9));
        yValues2.add(new Entry(9,9));
        yValues2.add(new Entry(9, 10));

        new ChartUtils().setLineCharts(lineChart, max, min, yValues1, color1, yValues2, color2);

        XueYaMarkView mv = new XueYaMarkView(getPageContext());
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
        SpannableStringBuilder stringBuilder = setTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), "共测量", "次数", "次;");
        SpannableStringBuilder stringBuilder1 = setTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), "有", "次数", "偏高;");
        SpannableStringBuilder stringBuilder2 = setTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), "", "次数", "正常;");

        numTv.setText(stringBuilder.append(stringBuilder1).append(stringBuilder2));
    }

    private SpannableStringBuilder setTextType(int startColor, int endColor, String text1, String text2, String text3) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text1);
        int length1 = builder.length();
        builder.append(text2);
        int length2 = builder.length();
        builder.append(text3);
        builder.setSpan(new ForegroundColorSpan(startColor), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan(endColor), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), length1, length2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }



    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_home_xueya, null);
        addTv = view.findViewById(R.id.tv_home_fm_xy_add);
        lineChart = view.findViewById(R.id.lc_home_fm_xy);
        numTv = view.findViewById(R.id.tv_home_fm_xy_num);

        containerView().addView(view);
    }


    public class XueYaMarkView extends MarkerView {
        private TextView tvData1;
        private TextView tvData1_;
        private TextView tvData2;
        private TextView tvDataUnit;
        private TextView tvDataTime;

        public XueYaMarkView(Context context) {
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
