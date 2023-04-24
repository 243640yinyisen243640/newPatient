package com.vice.bloodpressure.activity.ahome.adiet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import retrofit2.Call;


/**
 * 类名：饮食方案列表
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietMealPlanListActivity extends UIBaseLoadActivity {
    /**
     * 早餐
     */
    private TextView breakfastTitleTv;
    private RecyclerView breakFastRv;
    /**
     * 午餐
     */
    private TextView lunchTitleTv;
    private RecyclerView lunchFastRv;

    /**
     * 晚餐
     */
    private TextView dinnerTitleTv;
    private RecyclerView dinnerFastRv;


    private String titleMeal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("饮食方案列表");
        topViewManager().moreTextView().setText("换我想吃");
        topViewManager().moreTextView().setOnClickListener(v -> {

        });
        initView();
        initValues();
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


    }
}
