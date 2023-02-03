package com.vice.bloodpressure.ui.activity.diet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;


/**
 * 作者: beautiful
 * 类名:更换饮食方案
 * 传参:
 * 描述:
 */
public class DietChangeDietActivity extends UIBaseActivity {
    /**
     * 早餐
     */
    private TextView breakfastTv;
    /**
     * 午餐
     */
    private TextView noonTv;
    /**
     * 晚餐
     */
    private TextView dinnerTv;
    /**
     * 早餐列表
     */
    private RecyclerView breakfastRv;
    /**
     * 午餐列表
     */
    private RecyclerView noonRv;
    /**
     * 晚餐列表
     */
    private RecyclerView dinnerRv;

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
        noonTv = view.findViewById(R.id.tv_diet_change_noon);
        dinnerTv = view.findViewById(R.id.tv_diet_change_dinner);
        breakfastRv = view.findViewById(R.id.rv_diet_change_breakfast);
        noonRv = view.findViewById(R.id.rv_diet_change_noon);
        dinnerRv = view.findViewById(R.id.rv_diet_change_dinner);
    }

    private void initValues() {


    }
}
