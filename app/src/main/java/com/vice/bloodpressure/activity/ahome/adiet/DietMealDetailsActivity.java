package com.vice.bloodpressure.activity.ahome.adiet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietMealOneMealDetailsAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.List;

import retrofit2.Call;


/**
 * 类名：一餐详情展示
 * 传参：meal
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietMealDetailsActivity extends UIBaseLoadActivity {
    /**
     * 早餐
     */
    private TextView mealTitleTv;

    /**
     * 早餐列表
     */
    private NoScrollListView mealTitleLv;

    private String titleMeal;
    private String planDate;

    private List<MealExclusiveInfo> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleMeal = getIntent().getStringExtra("meals");
        planDate = getIntent().getStringExtra("planDate");
        //        topViewManager().moreTextView().setText("换我想吃");
        topViewManager().moreTextView().setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), DietChangeDietActivity.class);
            startActivity(intent);
        });
        initView();
        topViewManager().titleTextView().setText(titleMeal.equals("breakfast") ? "早餐" : titleMeal.equals("lunch") ? "午餐" : "晚餐");
        mealTitleTv.setText(titleMeal.equals("breakfast") ? "早餐" : titleMeal.equals("lunch") ? "午餐" : "晚餐");
        initValues();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = HomeDataManager.dietDetailsToDayMeals(UserInfoUtils.getArchivesId(getPageContext()), planDate, titleMeal, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                list = (List<MealExclusiveInfo>) response.object;
                DietMealOneMealDetailsAdapter adapter = new DietMealOneMealDetailsAdapter(getPageContext(), list, "1", (position, view) -> {
                    Intent intent = new Intent(getPageContext(), DietMakeMealDetailsActivity.class);
                    intent.putExtra("recHeat", list.get(position).getRecHeat());
                    intent.putExtra("recId", list.get(position).getRecId());
                    startActivity(intent);
                });
                mealTitleLv.setAdapter(adapter);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("dietDetailsToDayMeals", requestCall);

    }

    private void getOneDayMeals() {
        Call<String> requestCall = HomeDataManager.randomMealsPlanToDay(UserInfoUtils.getArchivesId(getPageContext()), titleMeal, planDate, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                List<MealExclusiveInfo> listSecond = (List<MealExclusiveInfo>) response.object;
                DietMealOneMealDetailsAdapter adapter = new DietMealOneMealDetailsAdapter(getPageContext(), listSecond, "2",null);
                mealTitleLv.setAdapter(adapter);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("randomMealsPlanToDay", requestCall);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_mall_details, null);
        containerView().addView(view);
        mealTitleTv = view.findViewById(R.id.tv_diet_mall_details_title);
        mealTitleLv = view.findViewById(R.id.rv_diet_mall_details);

        mealTitleTv.setOnClickListener(v -> {
            getOneDayMeals();
        });
    }

    private void initValues() {

        if ("早餐".equals(titleMeal)) {
            mealTitleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.diet_bing_with_green, 0, R.drawable.diet_change_my_like, 0);
        } else if ("午餐".equals(titleMeal)) {
            mealTitleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.jitui_with_green_20, 0, R.drawable.diet_change_my_like, 0);
        } else {
            mealTitleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.diet_huacai_with_green, 0, R.drawable.diet_change_my_like, 0);
        }



    }
}
