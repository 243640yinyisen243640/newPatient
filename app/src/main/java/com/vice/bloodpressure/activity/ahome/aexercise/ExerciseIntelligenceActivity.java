package com.vice.bloodpressure.activity.ahome.aexercise;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.TurnUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:智能运动
 */
public class ExerciseIntelligenceActivity extends UIBaseLoadActivity implements View.OnClickListener {
    /**
     * 需要消耗千卡
     */
    private TextView needFireTv;
    /**
     * 千卡图
     */
    private PieChart numPc;

    /**
     * 步行
     */
    private TextView workTv;
    /**
     * 跑步
     */
    private TextView runTv;
    /**
     * 未消耗
     */
    private TextView noTv;
    /**
     * 其他
     */
    private TextView otherTv;
    /**
     * 重新制定
     */
    private TextView againTv;
    /**
     * 类型选择
     */
    private TextView exerciseChooseTv;
    /**
     * 选择类型结束后开始运动
     */
    private TextView beginTv;
    /**
     * 抗阻名称
     */
    private TextView resistanceNameTv;
    /**
     * 抗阻开始
     */
    private TextView resistanceBeginTv;
    /**
     * 柔韧性名称
     */
    private TextView flexibilityNameTv;
    /**
     * 柔韧性开始
     */
    private TextView flexibilityBeginTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("智能运动");
        topViewManager().moreTextView().setText("运动记录");
        topViewManager().moreTextView().setOnClickListener(v -> {

        });
        initView();
        initListener();
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
        showPieChart(numPc, getPieChartData(rateString, nameString));

        needFireTv.setText(setMealTextType("1", Color.parseColor("#00C27F"), 18, "今日需消耗", " 2400 ", "千卡"));
        workTv.setText(setMealTextType("2", Color.parseColor("#2A2A2A"), 14, getString(R.string.intelligence_run_work),String.format(getPageContext().getString(R.string.intelligence_run_three), "1234"), getString(R.string.intelligence_run_num_unit)));
        runTv.setText(setMealTextType("2", Color.parseColor("#2A2A2A"), 14, getString(R.string.intelligence_run_run),String.format(getPageContext().getString(R.string.intelligence_run_three), "234"), getString(R.string.intelligence_run_num_unit)));
        noTv.setText(setMealTextType("2", Color.parseColor("#2A2A2A"), 14, getString(R.string.intelligence_run_no),String.format(getPageContext().getString(R.string.intelligence_run_two), "345"), getString(R.string.intelligence_run_num_unit)));
        otherTv.setText(setMealTextType("2", Color.parseColor("#2A2A2A"), 14, getString(R.string.intelligence_run_other), String.format(getPageContext().getString(R.string.intelligence_run_one), "567"), getString(R.string.intelligence_run_num_unit)));
    }


    private void initListener() {
        againTv.setOnClickListener(this);
        exerciseChooseTv.setOnClickListener(this);
        beginTv.setOnClickListener(this);
        resistanceBeginTv.setOnClickListener(this);
        flexibilityBeginTv.setOnClickListener(this);
    }

    private SpannableStringBuilder setMealTextType(String type, int endColor, float textSize, String text1, String text2, String text3) {
        SpannableStringBuilder builder = new SpannableStringBuilder();

        builder.append(text1);
        int length1 = builder.length();
        builder.append(text2);
        int length2 = builder.length();
        builder.append(text3);
        if ("1".equals(type)) {
            builder.setSpan(new ForegroundColorSpan(endColor), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(getPageContext(), textSize)), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            builder.setSpan(new ForegroundColorSpan(endColor), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(getPageContext(), textSize)), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }


    @Override
    protected void onPageLoad() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //重新制定
            case R.id.tv_exercise_again:
                break;
            //选择运动
            case R.id.tv_exercise_choose:
                chooseSexWindow();
                break;
            //开始运动
            case R.id.tv_exercise_begin:
                break;
            //抗阻开始
            case R.id.tv_exercise_resistance_begin:
                break;
            //柔韧性开始
            case R.id.tv_exercise_flexibility_begin:
                break;
            default:
                break;
        }

    }

    /**
     * 有关饼状图
     *
     * @param pieChart
     * @param pieList
     */
    private void showPieChart(PieChart pieChart, List<PieEntry> pieList) {
        PieDataSet dataSet = new PieDataSet(pieList, "");
        int[] intArray = getResources().getIntArray(R.array.exercise_plan_colors);
        dataSet.setColors(intArray);
        PieData pieData = new PieData(dataSet);


        pieChart.setUsePercentValues(true);
        //设置使用百分比
        //设置描述
        pieChart.getDescription().setEnabled(false);
        //设置半透明圆环的半径, 0为透明
        pieChart.setTransparentCircleRadius(0f);

        pieChart.setHighlightPerTapEnabled(false);//点击是否放大

        pieChart.setCenterText("多少千卡");//设置环中的文字
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
        dataSet.setSliceSpace(1f);
        dataSet.setHighlightEnabled(true);
        // 显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        // 和四周相隔一段距离,显示数据
        pieChart.setExtraOffsets(0, 0, 0, 0);

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

    /**
     * 有关饼状图
     *
     * @param list1
     * @param list2
     * @return
     */
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

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_intelligence, null);
        needFireTv = view.findViewById(R.id.tv_exercise_need_fire);
        numPc = view.findViewById(R.id.pc_exercise_qk_num);
        workTv = view.findViewById(R.id.tv_exercise_work);
        runTv = view.findViewById(R.id.tv_exercise_run);
        noTv = view.findViewById(R.id.tv_exercise_no);
        otherTv = view.findViewById(R.id.tv_exercise_other);
        againTv = view.findViewById(R.id.tv_exercise_again);
        exerciseChooseTv = view.findViewById(R.id.tv_exercise_choose);
        beginTv = view.findViewById(R.id.tv_exercise_begin);
        resistanceNameTv = view.findViewById(R.id.tv_exercise_resistance_name);
        resistanceBeginTv = view.findViewById(R.id.tv_exercise_resistance_begin);
        flexibilityNameTv = view.findViewById(R.id.tv_exercise_flexibility_name);
        flexibilityBeginTv = view.findViewById(R.id.tv_exercise_flexibility_begin);

        containerView().addView(view);
    }

    /**
     * 选择运动类型
     */
    private void chooseSexWindow() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("快跑");
        exerciseList.add("快走");
        exerciseList.add("慢跑");
        exerciseList.add("健步走");
        exerciseList.add("羽毛球");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "有氧运动", exerciseList, object -> exerciseChooseTv.setText(exerciseList.get(Integer.parseInt(String.valueOf(object)))));
    }


}
