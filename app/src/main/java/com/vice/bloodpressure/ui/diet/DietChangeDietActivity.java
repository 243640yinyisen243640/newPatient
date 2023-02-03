package com.vice.bloodpressure.ui.diet;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 作者: beautiful
 * 类名:更换饮食方案
 * 传参:
 * 描述:
 */
public class DietChangeDietActivity extends UIBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.activity_diet_change_diet_plan,null);
        containerView().addView(view);
        topViewManager().titleTextView().setText(R.string.diet_activity_change_diet_top_title);
    }
}
