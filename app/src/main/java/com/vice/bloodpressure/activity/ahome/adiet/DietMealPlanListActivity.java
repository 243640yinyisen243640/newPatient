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
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.NoScrollListView;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;


/**
 * 类名：饮食方案列表
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietMealPlanListActivity extends UIBaseLoadActivity implements AdapterView.OnItemClickListener {
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("饮食方案列表");
        topViewManager().moreTextView().setText("换我想吃");
        breakLsit = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("breaklist");
        lunchLsit = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("lunchlist");
        dinnerLsit = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("dinnerlist");
        topViewManager().moreTextView().setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), DietChangeDietActivity.class);
            intent.putExtra("breaklist", (Serializable) breakLsit);
            intent.putExtra("lunchlist", (Serializable) lunchLsit);
            intent.putExtra("dinnerlist", (Serializable) dinnerLsit);
            startActivity(intent);
        });
        initView();
        initValues();
        initListener();
    }

    private void initListener() {
        breakFastRv.setOnItemClickListener(this);
        lunchFastRv.setOnItemClickListener(this);
        dinnerFastRv.setOnItemClickListener(this);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = HomeDataManager.getDietPlan(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {

                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }

        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getDietPlan", requestCall);
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

    private void initValues() {

        DietMealOneMealDetailsAdapter breakAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), breakLsit);
        breakFastRv.setAdapter(breakAdapter);


        DietMealOneMealDetailsAdapter lunchAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), lunchLsit);
        lunchFastRv.setAdapter(lunchAdapter);


        DietMealOneMealDetailsAdapter dinnerAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), dinnerLsit);
        dinnerFastRv.setAdapter(dinnerAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.rv_diet_plan_breakfast:

            case R.id.rv_diet_plan_lunch:

            case R.id.rv_diet_plan_dinner:
                Intent intent = new Intent(getPageContext(), DietMakeMealDetailsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
