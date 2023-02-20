package com.vice.bloodpressure.activity.ahome.adiet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietMealPlanNumAdapter;
import com.vice.bloodpressure.adapter.home.DietMealPlanWeekAdapter;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.model.MealInfo;
import com.vice.bloodpressure.utils.DataUtils;
import com.vice.bloodpressure.view.CirclePercentView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:饮食方案详细
 */
public class DietMealPlanDetailsActivity extends UIBaseLoadActivity {
    private RecyclerView nameRc;
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(getPageContext());
        layoutManager.setOrientation(nameRc.HORIZONTAL);
        nameRc.setLayoutManager(layoutManager);
        List<MealInfo> list = new ArrayList<>();
        list.add(new MealInfo("主食", "3两"));
        list.add(new MealInfo("蔬菜", "9两"));
        list.add(new MealInfo("肉蛋白", "3两"));
        list.add(new MealInfo("浆乳类", "3两"));
        list.add(new MealInfo("盐类", "3两"));
        list.add(new MealInfo("油类", "3两"));


        DietMealPlanNumAdapter numAdapter = new DietMealPlanNumAdapter(getPageContext(), list);
        nameRc.setAdapter(numAdapter);

        sevenPlanRv.setAdapter(new DietMealPlanWeekAdapter(getPageContext(), new ArrayList<>()));
    }


    @Override
    protected void onPageLoad() {

    }

    private void initViews() {
        View view = View.inflate(getPageContext(), R.layout.activity_meal_plan_details, null);
        nameRc = view.findViewById(R.id.rc_all_name_proportion);
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
