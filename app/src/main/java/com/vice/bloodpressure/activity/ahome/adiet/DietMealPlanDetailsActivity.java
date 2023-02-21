package com.vice.bloodpressure.activity.ahome.adiet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.utils.DataUtils;
import com.vice.bloodpressure.view.CirclePercentView;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:饮食方案详细
 */
public class DietMealPlanDetailsActivity extends UIBaseLoadActivity {
    private TextView zhushiTv, shucaiTv, roudanbaiTv, jiangruTv, youTv, yanTv;
    private PieChart allProportionRc;
    private CirclePercentView carbonProportionRc;
    private CirclePercentView proteinProportionRc;
    private CirclePercentView fatProportionRc;
    private TextView carbonTv;
    private TextView proteinTv;
    private TextView fatTv;
    private TextView moreTv;
    private RecyclerView sevenPlanRv;
    private TextView refreshTv;
    private TextView breakfastTv;
    private TextView lunchTv;
    private TextView dinnerTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("饮食方案");
        topViewManager().moreTextView().setText("重新制定");
        initViews();
        initValues();
        getData();
    }

    private void initValues() {
        Log.i("yys", "seven" + DataUtils.getSevendate());
        Log.i("yys", "week" + DataUtils.get7week());

    }


    private void getData() {


    }


    @Override
    protected void onPageLoad() {

    }

    private void initViews() {
        View view = View.inflate(getPageContext(), R.layout.activity_meal_plan_details, null);
        zhushiTv = view.findViewById(R.id.tv_zhushi_num);
        shucaiTv = view.findViewById(R.id.tv_shucai_num);
        roudanbaiTv = view.findViewById(R.id.tv_roudanbai_num);
        jiangruTv = view.findViewById(R.id.tv_jiangrulei_num);
        yanTv = view.findViewById(R.id.tv_yanlei_num);
        youTv = view.findViewById(R.id.tv_youlei_num);
        allProportionRc = view.findViewById(R.id.pc_all_proportion);
        carbonProportionRc = view.findViewById(R.id.cpv_carbon_proportion);
        proteinProportionRc = view.findViewById(R.id.cpv_protein_proportion);
        fatProportionRc = view.findViewById(R.id.cpv_fat_proportion);
        carbonTv = view.findViewById(R.id.tv_carbon_proportion);
        proteinTv = view.findViewById(R.id.tv_protein_proportion);
        fatTv = view.findViewById(R.id.tv_fat_proportion);
        moreTv = view.findViewById(R.id.tv_seven_more);
        sevenPlanRv = view.findViewById(R.id.rv_seven_plan);
        refreshTv = view.findViewById(R.id.tv_seven_refresh);
        breakfastTv = view.findViewById(R.id.tv_seven_breakfast);
        lunchTv = view.findViewById(R.id.tv_seven_lunch);
        dinnerTv = view.findViewById(R.id.tv_seven_dinner);
        containerView().addView(view);
    }
}
