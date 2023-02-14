package com.vice.bloodpressure.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.ahome.adiet.DietChangeDietActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietMakeMealDetailsActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietMealDetailsActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietMealPlanDetailsActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietProgrammeBeginActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietProgrammeChooseActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietProgrammeChooseMealActivity;
import com.vice.bloodpressure.activity.ahome.aeducation.EducationQuestionInvestigateBeginActivity;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MainFragment extends UIBaseLoadFragment implements View.OnClickListener {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;

    public static MainFragment getInstance() {

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    protected void onCreate() {

        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
    }

    private void initListener() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
    }

    private void initView() {

        View view = View.inflate(getPageContext(), R.layout.fragment_main_home, null);
        tv1 = view.findViewById(R.id.text1);
        tv2 = view.findViewById(R.id.text2);
        tv3 = view.findViewById(R.id.text3);
        tv4 = view.findViewById(R.id.text4);
        tv5 = view.findViewById(R.id.text5);
        tv6 = view.findViewById(R.id.text6);
        tv7 = view.findViewById(R.id.text7);
        tv8 = view.findViewById(R.id.text8);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.text1:
                intent = new Intent(getPageContext(), DietProgrammeBeginActivity.class);
                startActivity(intent);
                break;
            case R.id.text2:
                intent = new Intent(getPageContext(), EducationQuestionInvestigateBeginActivity.class);
                startActivity(intent);
                break;
            case R.id.text3:
                intent = new Intent(getPageContext(), DietProgrammeChooseActivity.class);
                startActivity(intent);
                break;
            case R.id.text4:
                intent = new Intent(getPageContext(), DietProgrammeChooseMealActivity.class);
                startActivity(intent);
                break;
            case R.id.text5:
                intent = new Intent(getPageContext(), DietChangeDietActivity.class);
                startActivity(intent);
                break;
            case R.id.text6:
                intent = new Intent(getPageContext(), DietMealDetailsActivity.class);
                intent.putExtra("meal", "早餐");
                startActivity(intent);
                break;
            case R.id.text7:
                intent = new Intent(getPageContext(), DietMakeMealDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.text8:
                intent = new Intent(getPageContext(), DietMealPlanDetailsActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
