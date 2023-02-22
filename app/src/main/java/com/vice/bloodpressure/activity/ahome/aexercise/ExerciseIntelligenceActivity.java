package com.vice.bloodpressure.activity.ahome.aexercise;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.github.mikephil.charting.charts.PieChart;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.model.MealInfo;
import com.vice.bloodpressure.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
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
        initView();
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
                chooseSexWindow1();
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

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_intelligence, null);
        needFireTv = view.findViewById(R.id.tv_exercise_need_fire);
        numPc = view.findViewById(R.id.pc_exercise_qk_num);
        workTv = view.findViewById(R.id.tv_exercise_work);
        runTv = view.findViewById(R.id.tv_exercise_run);
        noTv = view.findViewById(R.id.tv_exercise_no);
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

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "有氧运动", exerciseList, new CallBack() {
            @Override
            public void callBack(Object object) {
                exerciseChooseTv.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
            }
        });
    }

    /**
     * 选择运动类型
     */
    private void chooseSexWindow1() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("快跑");
        exerciseList.add("快走");
        exerciseList.add("慢跑");
        exerciseList.add("健步走");
        exerciseList.add("羽毛球");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "有氧运动", exerciseList, new CallBack() {
            @Override
            public void callBack(Object object) {
                exerciseChooseTv.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
            }
        });
    }

    private void chooseCompanyStyle() {
        List<MealInfo> list = new ArrayList<>();
        list.add(new MealInfo("快跑"));
        list.add(new MealInfo("快走"));
        list.add(new MealInfo("慢跑"));
        list.add(new MealInfo("健步走"));
        list.add(new MealInfo("健步走"));
        list.add(new MealInfo("羽毛球"));
        if (list != null && list.size() > 0) {
            OptionsPickerView optionsPickerView = new OptionsPickerBuilder(getPageContext(), (options1, options2, options3, v) -> {
                String s = list.get(options1).getColor();
                exerciseChooseTv.setText(s);
            }).setLineSpacingMultiplier(2.5f)
                    .setCancelColor(ContextCompat.getColor(getPageContext(), R.color.gray_E5))
                    .setSubmitColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color))
                    .build();
            List<String> list2 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String typeName = list.get(i).getColor();
                list2.add(typeName);
            }
            optionsPickerView.setPicker(list2);
            optionsPickerView.show();
        }
    }
}
