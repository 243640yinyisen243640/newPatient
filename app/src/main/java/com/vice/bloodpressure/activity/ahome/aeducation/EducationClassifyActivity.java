package com.vice.bloodpressure.activity.ahome.aeducation;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.baseui.UIBaseLoadActivity;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationClassifyActivity extends UIBaseLoadActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("分类");
        initView();
    }

    private void initView() {

    }


    @Override
    protected void onPageLoad() {

    }
}
