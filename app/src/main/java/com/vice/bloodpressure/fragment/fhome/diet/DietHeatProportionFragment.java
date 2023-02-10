package com.vice.bloodpressure.fragment.fhome.diet;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.utils.TurnUtils;
import com.vice.bloodpressure.view.HHAtMostGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DietHeatProportionFragment extends UIBaseFragment {
    private PieChart proportionPc;
    private HHAtMostGridView proportionGv;

    public static DietHeatProportionFragment getInstance(String text) {

        DietHeatProportionFragment proportionFragment = new DietHeatProportionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        proportionFragment.setArguments(bundle);
        return proportionFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        containerView().addView(initView());

        initValues();
    }

    private void initValues() {

        List<String> nameString = new ArrayList<>();
        nameString.add("小米");
        nameString.add("鸡蛋");
        nameString.add("蔬菜");
        nameString.add("饮品");
        List<String> rateString = new ArrayList<>();
        rateString.add("50");
        rateString.add("20");
        rateString.add("10");
        rateString.add("20");


        showPieChart(proportionPc, getPieChartData(rateString, nameString));
    }

    private List<PieEntry> getPieChartData(List<String> list1, List<String> list2) {
        List<PieEntry> mPie = new ArrayList<>();
        if (list1 != null && list2 != null) {
            for (int i = 0; i < list1.size(); i++) {
                // 参数1为 value，参数2为 data。
                // 如 PieEntry(0.15F, "90分以上");  表示90分以上的人占比15%。
                PieEntry pieEntry = new PieEntry(TurnUtils.getFloat(list1.get(i), 0), list2.get(i));
                mPie.add(pieEntry);
            }
        }
        return mPie;
    }

    private void showPieChart(PieChart pieChart, List<PieEntry> pieList) {
        PieDataSet dataSet = new PieDataSet(pieList, "");
        int[] intArray = getResources().getIntArray(R.array.diet_plan_colors);
        dataSet.setColors(intArray);
        PieData pieData = new PieData(dataSet);


        pieChart.setUsePercentValues(true);
        //设置使用百分比
        //设置描述
        pieChart.getDescription().setEnabled(false);
        //设置半透明圆环的半径, 0为透明
        pieChart.setTransparentCircleRadius(0f);

        pieChart.setHighlightPerTapEnabled(false);//点击是否放大

        pieChart.setCenterText("菜的名字");//设置环中的文字
        pieChart.setCenterTextSize(15f);//设置环中文字的大小
        pieChart.setDrawCenterText(true);//设置绘制环中文字
        //设置初始旋转角度
        pieChart.setRotationAngle(-15);

        //设置这个数字，越大，这个饼越细，越小，饼越胖
        pieChart.setHoleRadius(78f);
        //设置内圆和外圆的一个交叉园的半径，这样会凸显内外部的空间
        pieChart.setTransparentCircleRadius(31f);


        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(80f);

        // 设置饼块之间的间隔
        dataSet.setSliceSpace(0f);
        dataSet.setHighlightEnabled(true);
        // 显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        // 和四周相隔一段距离,显示数据
        pieChart.setExtraOffsets(26, 5, 26, 5);

        // 设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(false);
        // 设置piecahrt图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(false);
        // 设置pieChart图表展示动画效果，动画运行1.4秒结束
        pieChart.animateY(1400, Easing.EaseInOutQuad);
        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(false);
        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(false);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);
        // 更新 piechart 视图
        pieChart.postInvalidate();
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_diet_heat_proportion, null);
        proportionPc = view.findViewById(R.id.pc_heat_proportion);
        proportionGv = view.findViewById(R.id.gv_heat_proportion);
        return view;
    }


}
