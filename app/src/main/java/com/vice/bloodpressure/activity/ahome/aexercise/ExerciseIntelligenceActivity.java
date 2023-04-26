package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
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
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.model.ExerciseInfo;
import com.vice.bloodpressure.popwindow.AnswerForPopupWindow;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.TurnUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:智能运动
 */
public class ExerciseIntelligenceActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private AnswerForPopupWindow answerForPopupWindow;
    /**
     * 消耗的千卡总数
     */
    private TextView allQiankaTextView;
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
    /**
     * 运动类型
     */
    private String exerciseType;

    private ExerciseInfo info;
    private List<BaseLocalDataInfo> sportList;

    private String oxygenSportId = "-1";
    private String resistanceSportId = "-1";
    private String flexSportId = "-1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("智能运动");
        topViewManager().moreTextView().setText("运动记录");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), ExerciseRecordListActivity.class));
        });
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
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
            builder.setSpan(new ForegroundColorSpan(endColor), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new StyleSpan(Typeface.BOLD), length1, length2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            builder.setSpan(new ForegroundColorSpan(endColor), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(getPageContext(), textSize)), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }


    @Override
    protected void onPageLoad() {
        Call<String> requestCall = HomeDataManager.getSportPlan(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                info = (ExerciseInfo) response.object;
                bindData();
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }

        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getDietPlan", requestCall);
    }

    private void bindData() {
        oxygenSportId = info.getSportAerobics().getId();
        exerciseType = info.getSportAerobics().getSportName();
        resistanceSportId = info.getSportResistance().getId();
        flexSportId = info.getSportPliable().getId();
        resistanceNameTv.setText(info.getSportResistance().getSportName());
        flexibilityNameTv.setText(info.getSportPliable().getSportName());
        String workString = "";
        String runString = "";
        String noString = "";
        String otherString = "";
        //这里的逻辑是  ：  比如今日需消耗的是100 已经消耗的是50，是跑步 步行 和其他的和
        //  100-50=50是未消耗的，
        if ("0".equals(info.getConsumeCalories())) {
            workString = "0";
            runString = "0";
            noString = "100";
            otherString = "0";
        } else {
            //已消耗热量
            double haveAll = Double.parseDouble(info.getConsumeCalories());
            //今日所需热量
            double need = Double.parseDouble(info.getNeedConsumeCalories());

            //未消耗
            double haveNo = need - haveAll;

            double work = Double.parseDouble(info.getWalkCalories()) / haveAll;
            double run = Double.parseDouble(info.getRunCalories()) / haveAll;
            double other = Double.parseDouble(info.getOtherSportConsumed()) / haveAll;


            workString = String.valueOf(work);
            runString = String.valueOf(run);
            noString = String.valueOf(haveNo);
            otherString = String.valueOf(other);
        }

        List<String> nameString = new ArrayList<>();
        nameString.add("");
        nameString.add("");
        nameString.add("");
        nameString.add("");
        List<String> rateString = new ArrayList<>();
        rateString.add(workString);
        rateString.add(runString);
        rateString.add(noString);
        rateString.add(otherString);
        showPieChart(numPc, getPieChartData(rateString, nameString));
        allQiankaTextView.setText(info.getConsumeCalories());
        needFireTv.setText(setMealTextType("1", Color.parseColor("#00C27F"), 18, "今日需消耗 ", info.getNeedConsumeCalories(), " 千卡"));
        workTv.setText(setMealTextType("2", Color.parseColor("#2A2A2A"), 14, getString(R.string.intelligence_run_work), String.format(getPageContext().getString(R.string.intelligence_run_three), info.getWalkCalories()), getString(R.string.intelligence_run_num_unit)));
        runTv.setText(setMealTextType("2", Color.parseColor("#2A2A2A"), 14, getString(R.string.intelligence_run_run), String.format(getPageContext().getString(R.string.intelligence_run_three), info.getRunCalories()), getString(R.string.intelligence_run_num_unit)));
        noTv.setText(setMealTextType("2", Color.parseColor("#2A2A2A"), 14, getString(R.string.intelligence_run_no), String.format(getPageContext().getString(R.string.intelligence_run_two), info.getNotConsumed()), getString(R.string.intelligence_run_num_unit)));
        otherTv.setText(setMealTextType("2", Color.parseColor("#2A2A2A"), 14, getString(R.string.intelligence_run_other), String.format(getPageContext().getString(R.string.intelligence_run_one), info.getOtherSportConsumed()), getString(R.string.intelligence_run_num_unit)));
        exerciseChooseTv.setText(info.getSportAerobics().getSportName());
    }

    private void showType() {
        //        if (answerForPopupWindow == null) {
        //            answerForPopupWindow = new AnswerForPopupWindow(getPageContext(), "2",
        //                    other -> {
        //                        Intent intent = new Intent(getPageContext(), ExercisePlanOneActivity.class);
        //                        startActivity(intent);
        //                    },
        //                    self -> {
        //                        Intent intent = new Intent(getPageContext(), ExercisePlanOneActivity.class);
        //                        startActivity(intent);
        //                    });
        //            answerForPopupWindow.dismiss();
        //        }
        //        answerForPopupWindow.showAsDropDown(containerView(), 0, 0, Gravity.CENTER);

        Intent intent = new Intent(getPageContext(), ExercisePlanOneActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //重新制定
            case R.id.tv_exercise_again:
                showType();
                break;
            //选择运动
            case R.id.tv_exercise_choose:
                getOxygenData();
                break;
            //开始运动
            case R.id.tv_exercise_begin:
                intent = new Intent(getPageContext(), ExerciseRecordAddHandActivity.class);
                intent.putExtra("title", exerciseType);
                intent.putExtra("sportId", oxygenSportId);
                startActivity(intent);
                break;
            //抗阻开始
            case R.id.tv_exercise_resistance_begin:
                intent = new Intent(getPageContext(), ExerciseRecordAddHandFlexActivity.class);
                intent.putExtra("title", info.getSportResistance().getSportName());
                intent.putExtra("sportId", resistanceSportId);
                intent.putExtra("type", "R");
                startActivity(intent);
                break;
            //柔韧性开始
            case R.id.tv_exercise_flexibility_begin:
                intent = new Intent(getPageContext(), ExerciseRecordAddHandFlexActivity.class);
                intent.putExtra("title", info.getSportPliable().getSportName());
                intent.putExtra("sportId", flexSportId);
                intent.putExtra("type", "P");
                startActivity(intent);
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

        pieChart.setCenterText("");//设置环中的文字
        pieChart.setCenterTextSize(15f);//设置环中文字的大小
        pieChart.setDrawCenterText(false);//设置绘制环中文字
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
        allQiankaTextView = view.findViewById(R.id.tv_exercise_qiank_all);
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

    private void getOxygenData() {
        Call<String> requestCall = HomeDataManager.getSportAerobics((call, response) -> {
            if ("0000".equals(response.code)) {
                sportList = (List<BaseLocalDataInfo>) response.object;
                List<String> list = new ArrayList<>();
                if (sportList != null && sportList.size() > 0) {
                    for (int i = 0; i < sportList.size(); i++) {
                        String typeName = sportList.get(i).getSportName();
                        list.add(typeName);
                    }
                }
                chooseTypeWindow(list);
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getSportAerobics", requestCall);
    }

    /**
     * 选择运动类型
     */
    private void chooseTypeWindow(List<String> stringList) {
        PickerViewUtils.showChooseSinglePicker(getPageContext(), "选择运动", stringList, object -> {
            oxygenSportId = sportList.get(Integer.parseInt(String.valueOf(object))).getId();
            exerciseType = sportList.get(Integer.parseInt(String.valueOf(object))).getSportName();
            exerciseChooseTv.setText(sportList.get(Integer.parseInt(String.valueOf(object))).getSportName());
        });
    }


}
