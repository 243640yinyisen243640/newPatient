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
public class HomeXueYaFragment extends UIBaseFragment {
    private TextView addTv;
    private LineChart lineChartView;
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
        initValue();
    }
    //    private void setChartView(LineChartView chartView,ArrayList<ChartBean>countBeans){
    //        //Y轴数据点
    //        List<PointValue> mPointValues = new ArrayList<>();
    //        //X轴时间
    //        List<AxisValue> axisXBottomValues = new ArrayList<>();
    //        ArrayList<Integer>arrayList=new ArrayList<>();
    //        for (int i = 0; i < countBeans.size(); i++) {
    //            //数据点
    //            mPointValues.add(new PointValue(i, countBeans.get(i).getCount()));
    //            //底部数据点
    //            axisXBottomValues.add(new AxisValue(i).setLabel(countBeans.get(i).getTime()));
    //            arrayList.add(countBeans.get(i).getCount());
    //        }
    //
    //        //最大值
    //        int max= Collections.max(arrayList);
    //        int min=Collections.min(arrayList);
    //        Line line = new Line(mPointValues);
    //        //存放线条的集合
    //        List<Line> lines = new ArrayList<>();
    //        line.setHasLabels(true);//曲线的数据坐标是否加上备注
    //        line.setShape(ValueShape.DIAMOND);
    //        line.setPointRadius(3);//坐标点大小
    //
    //        line.setCubic(true);//曲线是否平滑
    //        line.setFilled(true);//是否填充曲线的面积
    //
    //        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
    //        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
    //        line.setColor(Color.parseColor("#FEB04C"));
    //        line.setStrokeWidth(1);//设置线的宽度
    //        lines.add(line);
    //
    //        LineChartData data = new LineChartData();
    //        data.setLines(lines);
    //        //设置数据的初始值，即所有的数据从baseValue开始计算，默认值为0。
    //        data.setBaseValue(Float.NEGATIVE_INFINITY);
    //
    //        //传入底部list数据
    //        Axis axisX = new Axis();
    //        //设置底部标题(自行选择) 只能设置在正中间
    //        axisX.setName("实验日期");
    //        //底部标注是否斜着显示
    //        axisX.setHasTiltedLabels(false);
    //        //字体大小
    //        axisX.setTextSize(12);
    //        //字体颜色
    //        axisX.setTextColor(Color.parseColor("#666666"));
    //        //各标签之间的距离 (0-32之间)
    //        axisX.setMaxLabelChars(0);
    //        //是否显示坐标线、如果为false 则没有曲线只有点显示
    //        axisX.setHasLines(true);
    //        axisX.setValues(axisXBottomValues);
    //        data.setAxisXBottom(axisX);
    //
    //        //左边参数设置
    //        Axis axisY = new Axis();
    //        //axisY.setMaxLabelChars(6); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数
    //        axisY.setTextSize(12);
    //        axisY.setTextColor(Color.parseColor("#666666"));
    //        axisY.setHasLines(false);
    //        //axisY.setValues(axisXLeftValues);
    //        //设置坐标轴在左边
    //        data.setAxisYLeft(axisY);
    //
    //        //设置行为属性，支持缩放、滑动以及平移
    //        chartView.setInteractive(true);
    //        //水平缩放
    //        //lineChart.setZoomType(ZoomType.HORIZONTAL);
    //        //是否可滑动
    //        chartView.setScrollEnabled(true);
    //        //放入数据源至控件中
    //        chartView.setLineChartData(data);
    //
    //        //设置最大缩放比例。默认值20
    //        chartView.setMaxZoom(10);
    //        //开始以动画的形式更新图表数据
    //        chartView.startDataAnimation();
    //        //动画时长
    //        chartView.startDataAnimation(3);
    //        //取消动画
    //        chartView.cancelDataAnimation();
    //
    //        //图表触摸事件
    //        chartView.setOnValueTouchListener(new LineChartOnValueSelectListener() {
    //            @Override
    //            public void onValueSelected(int i, int i1, PointValue pointValue) {
    //                System.out.println("onValueSelected：" + i + "\n" + i1);
    //                System.out.println("X轴：" + pointValue.getX());
    //                System.out.println("Y轴：" + pointValue.getX());
    //            }
    //            @Override
    //            public void onValueDeselected() {
    //            }
    //        });
    //
    //        //viewport必须设置在setLineChartData后面，设置一个当前viewport，再设置一个maxviewport，
    //        //就可以实现滚动，高度要设置数据的上下限
    //        //设置是否允许在动画进行中或设置完表格数据后，自动计算viewport的大小。如果禁止，则需要可以手动设置。
    //        //lineChart.setViewportCalculationEnabled(true);
    //        Viewport v = new Viewport(chartView.getMaximumViewport());
    //        //Y轴最大值为 加上5、防止显示不全
    //        v.top = max + 5;
    //        //Y轴最小值
    //        v.bottom = Math.max((min - 5), 0);//最小值
    //        //设置最大化的viewport （chartdata）后再调用
    //        //这2个属性的设置一定要在lineChart.setMaximumViewport(v);这个方法之后,不然显示的坐标数据是不能左右滑动查看更多数据的
    //        chartView.setMaximumViewport(v);
    //        //左边起始位置 轴
    //        v.left = 0;
    //        //左右滑动
    //        chartView.setCurrentViewport(v);
    //    }

    private void initValue() {
        //        lineChartView.setInteractive();
//        List<PointValue> values = new ArrayList<PointValue>();
//        values.add(new PointValue(0, 2));
//        values.add(new PointValue(1, 4));
//        values.add(new PointValue(2, 3));
//        values.add(new PointValue(3, 4));
//
//        //In most cased you can call data model methods in builder-pattern-like manner.
//        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
//        List<Line> lines = new ArrayList<Line>();
//        lines.add(line);
//
//        LineChartData data = new LineChartData();
//        data.setLines(lines);
//
//        LineChartView chart = new LineChartView(getPageContext());
//        chart.setLineChartData(data);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_home_xueya, null);
        addTv = view.findViewById(R.id.tv_home_fm_xy_add);
        lineChartView = view.findViewById(R.id.lc_home_fm_xy);
        numTv = view.findViewById(R.id.tv_home_fm_xy_num);

        containerView().addView(view);
    }
}
