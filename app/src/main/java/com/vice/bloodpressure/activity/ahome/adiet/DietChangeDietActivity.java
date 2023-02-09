package com.vice.bloodpressure.activity.ahome.adiet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.view.NoScrollListView;


/**
 * 类名：更换饮食方案
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietChangeDietActivity extends UIBaseActivity {
    /**
     * 早餐
     */
    private TextView breakfastTv;
    /**
     * 午餐
     */
    private TextView lunchTv;
    /**
     * 晚餐
     */
    private TextView dinnerTv;
    /**
     * 早餐列表
     */
    private NoScrollListView breakfastNlv;
    /**
     * 午餐列表
     */
    private NoScrollListView lunchNlv;
    /**
     * 晚餐列表
     */
    private NoScrollListView dinnerNlv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        topViewManager().titleTextView().setText(R.string.diet_activity_change_diet_top_title);
        initValues();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_change_diet_plan, null);
        containerView().addView(view);
        breakfastTv = view.findViewById(R.id.tv_diet_change_breakfast);
        lunchTv = view.findViewById(R.id.tv_diet_change_lunch);
        dinnerTv = view.findViewById(R.id.tv_diet_change_dinner);
        breakfastNlv = view.findViewById(R.id.nlv_diet_change_breakfast);
        lunchNlv = view.findViewById(R.id.nlv_diet_change_lunch);
        dinnerNlv = view.findViewById(R.id.nlv_diet_change_dinner);
    }

    private void initValues() {


    }
}
