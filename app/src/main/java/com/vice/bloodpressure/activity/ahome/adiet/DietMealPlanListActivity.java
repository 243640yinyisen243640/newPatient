package com.vice.bloodpressure.activity.ahome.adiet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietMealOneMealDetailsAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.model.MealChildInfo;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.List;

import retrofit2.Call;


/**
 * 类名：饮食方案列表
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietMealPlanListActivity extends UIBaseLoadActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    /**
     * 早餐
     */
    private TextView breakfastTitleTv;
    private NoScrollListView breakFastRv;
    /**
     * 午餐
     */
    private TextView lunchTitleTv;
    private NoScrollListView lunchFastRv;

    /**
     * 晚餐
     */
    private TextView dinnerTitleTv;
    private NoScrollListView dinnerFastRv;

    private List<MealExclusiveInfo> breakLsit;
    private List<MealExclusiveInfo> lunchLsit;
    private List<MealExclusiveInfo> dinnerLsit;


    private String planDate = "";

    private MealChildInfo mealInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        topViewManager().moreTextView().setText("换我想吃");
        //        breakLsit = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("breaklist");
        //        lunchLsit = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("lunchlist");
        //        dinnerLsit = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("dinnerlist");
        //        topViewManager().moreTextView().setOnClickListener(v -> {
        //            Intent intent = new Intent(getPageContext(), DietChangeDietActivity.class);
        //            intent.putExtra("breaklist", (Serializable) breakLsit);
        //            intent.putExtra("lunchlist", (Serializable) lunchLsit);
        //            intent.putExtra("dinnerlist", (Serializable) dinnerLsit);
        //            startActivity(intent);
        //        });
        topViewManager().titleTextView().setText("饮食方案列表");
        planDate = getIntent().getStringExtra("planDate");
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        breakFastRv.setOnItemClickListener(this);
        lunchFastRv.setOnItemClickListener(this);
        dinnerFastRv.setOnItemClickListener(this);

        breakfastTitleTv.setOnClickListener(this);
        lunchTitleTv.setOnClickListener(this);
        dinnerTitleTv.setOnClickListener(this);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = HomeDataManager.getDietPlanList(UserInfoUtils.getArchivesId(getPageContext()), planDate, (call, response) -> {
            if ("0000".equals(response.code)) {
                mealInfo = (MealChildInfo) response.object;
                bindData();
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }

        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getDietPlanList", requestCall);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_plan_list, null);
        breakfastTitleTv = view.findViewById(R.id.tv_diet_plan_breakfast);
        breakFastRv = view.findViewById(R.id.rv_diet_plan_breakfast);
        lunchTitleTv = view.findViewById(R.id.tv_diet_plan_lunch);
        lunchFastRv = view.findViewById(R.id.rv_diet_plan_lunch);
        dinnerTitleTv = view.findViewById(R.id.tv_diet_plan_dinner);
        dinnerFastRv = view.findViewById(R.id.rv_diet_plan_dinner);
        containerView().addView(view);
    }

    private void bindData() {

        DietMealOneMealDetailsAdapter breakAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), mealInfo.getBreakfast(), "1", (position, view) -> {
            Intent intent = new Intent(getPageContext(), DietMakeMealDetailsActivity.class);
            intent.putExtra("recHeat", mealInfo.getBreakfast().get(position).getRecHeat());
            intent.putExtra("recId", mealInfo.getBreakfast().get(position).getRecId());
            startActivity(intent);
        });
        breakFastRv.setAdapter(breakAdapter);


        DietMealOneMealDetailsAdapter lunchAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), mealInfo.getLunch(), "1",(position, view) -> {
            Intent intent = new Intent(getPageContext(), DietMakeMealDetailsActivity.class);
            intent.putExtra("recHeat", mealInfo.getLunch().get(position).getRecHeat());
            intent.putExtra("recId", mealInfo.getLunch().get(position).getRecId());
            startActivity(intent);
        });
        lunchFastRv.setAdapter(lunchAdapter);


        DietMealOneMealDetailsAdapter dinnerAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), mealInfo.getDinner(), "1",(position, view) -> {
            Intent intent = new Intent(getPageContext(), DietMakeMealDetailsActivity.class);
            intent.putExtra("recHeat", mealInfo.getDinner().get(position).getRecHeat());
            intent.putExtra("recId", mealInfo.getDinner().get(position).getRecId());
            startActivity(intent);
        });
        dinnerFastRv.setAdapter(dinnerAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.rv_diet_plan_breakfast:
                getOneDayMeals("breakfast");
            case R.id.rv_diet_plan_lunch:
                getOneDayMeals("lunch");
            case R.id.rv_diet_plan_dinner:
                getOneDayMeals("dinner");
                break;
            default:
                break;
        }
    }

    private void getOneDayMeals(String meals) {
        Call<String> requestCall = HomeDataManager.randomMealsPlanToDay(UserInfoUtils.getArchivesId(getPageContext()), meals, planDate, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                List<MealExclusiveInfo> list = (List<MealExclusiveInfo>) response.object;
                if ("breakfast".equals(meals)) {
                    DietMealOneMealDetailsAdapter breakAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), list, "4",null);
                    breakFastRv.setAdapter(breakAdapter);
                } else if ("lunch".equals(meals)) {
                    DietMealOneMealDetailsAdapter lunchAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), list, "4",null);
                    lunchFastRv.setAdapter(lunchAdapter);
                } else {
                    DietMealOneMealDetailsAdapter dinnerAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), list, "4",null);
                    dinnerFastRv.setAdapter(dinnerAdapter);
                }

            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("randomMealsPlanToDay", requestCall);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_diet_plan_breakfast:
                getOneDayMeals("breakfast");
            case R.id.tv_diet_plan_lunch:
                getOneDayMeals("lunch");
            case R.id.tv_diet_plan_dinner:
                getOneDayMeals("dinner");
                break;
            default:
                break;
        }
    }
}
