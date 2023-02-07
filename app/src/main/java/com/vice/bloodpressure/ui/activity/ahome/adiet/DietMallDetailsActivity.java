package com.vice.bloodpressure.ui.activity.ahome.adiet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.view.NoScrollListView;


/**
 * 类名：一餐展示
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietMallDetailsActivity extends UIBaseActivity {
    /**
     * 早餐
     */
    private TextView mallTitleTv;

    /**
     * 早餐列表
     */
    private NoScrollListView mallTitleNlv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().moreTextView().setText("换我想吃");
        initView();
        topViewManager().titleTextView().setText(R.string.diet_activity_change_diet_top_title);
        initValues();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_mall_details, null);
        containerView().addView(view);
        mallTitleTv = view.findViewById(R.id.tv_diet_mall_details_title);
        mallTitleNlv = view.findViewById(R.id.nlv_diet_mall_details);
    }

    private void initValues() {


    }
}
