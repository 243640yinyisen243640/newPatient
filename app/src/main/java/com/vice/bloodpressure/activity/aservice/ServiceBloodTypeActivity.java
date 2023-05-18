package com.vice.bloodpressure.activity.aservice;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.utils.ChartUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.ArrayList;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ServiceBloodTypeActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private ImageView backImageView;
    private TextView startTextView;
    private TextView endTextView;
    private LineChart lineChart;
    float max = 20;
    float min = 0;
    private int color1 = Color.parseColor("#87CA8B");
    private int color2 = Color.parseColor("#FFC371");
    private int color3 = Color.parseColor("#FFA176");
    private int color4 = Color.parseColor("#FD716D");
    private int color5 = Color.parseColor("#6BA9F6");
    private int color6 = Color.parseColor("#5291FF");
    private int color7 = Color.parseColor("#3AD0BD");
    private int color8 = Color.parseColor("#7485E5");
    private String startTime="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        initView();
        initListener();
        initValues(setList(), setList(), setList(), setList(), setList(), setList(), setList(), setList());
    }

    private void initListener() {
        backImageView.setOnClickListener(this);
        startTextView.setOnClickListener(this);
        endTextView.setOnClickListener(this);
    }

    @Override
    protected void onPageLoad() {

    }

    private ArrayList<Entry> setList() {
        ArrayList<Entry> yValues1 = new ArrayList<>();
        yValues1.add(new Entry(1, 5));
        yValues1.add(new Entry(2, 7));
        yValues1.add(new Entry(3, 10));
        yValues1.add(new Entry(4, 16));
        yValues1.add(new Entry(5, 5));
        yValues1.add(new Entry(6, 9));
        yValues1.add(new Entry(7, 5));
        yValues1.add(new Entry(8, 12));
        yValues1.add(new Entry(9, 13));
        yValues1.add(new Entry(10, 14));
        yValues1.add(new Entry(11, 15));
        yValues1.add(new Entry(12, 16));
        return yValues1;
    }

    private void initValues(ArrayList<Entry> yValues1, ArrayList<Entry> yValues2, ArrayList<Entry> yValues3, ArrayList<Entry> yValues4,
                            ArrayList<Entry> yValues5, ArrayList<Entry> yValues6, ArrayList<Entry> yValues7, ArrayList<Entry> yValues8) {

        new ChartUtils().setLineCharts(lineChart, max, min, yValues1, color1, yValues2, color2, yValues3, color3, yValues4, color4,
                yValues5, color5, yValues6, color6, yValues7, color7, yValues8, color8);

        XueTangMarkView mv = new XueTangMarkView(getPageContext());
        mv.setChartView(lineChart);
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_xuetang, null);
        startTextView = view.findViewById(R.id.tv_service_blood_data_start_time);
        endTextView = view.findViewById(R.id.tv_service_blood_data_end_time);
        lineChart = view.findViewById(R.id.lc_service_xuetang);
        TextView titleTextView = view.findViewById(R.id.tv_service_blood_data_title);
        backImageView = view.findViewById(R.id.iv_service_blood_data_back);
        titleTextView.setText("血糖分析图");
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_blood_data_start_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        startTime = String.valueOf(object);
                        startTextView.setText(object.toString());
                    }
                });
                break;
            case R.id.tv_service_blood_data_end_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        if (XyTimeUtils.compareTwoTime(startTime, object.toString())) {
                            endTextView.setText(object.toString());
                        } else {
                            ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                        }
                    }
                });
                break;
            case R.id.iv_service_blood_data_back:
                finish();
                break;
            default:
                break;
        }
    }


    public class XueTangMarkView extends MarkerView {
        private TextView tvData1;
        private TextView tvData1_;
        private TextView tvData2;
        private TextView tvDataUnit;
        private TextView tvDataTime;

        public XueTangMarkView(Context context) {
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
