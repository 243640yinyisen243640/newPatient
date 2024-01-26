package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.fragment.fhome.exercise.ExerciseFlexibilityFragment;
import com.vice.bloodpressure.fragment.fhome.exercise.ExerciseOxygenFragment;
import com.vice.bloodpressure.fragment.fhome.exercise.ExerciseResistanceFragment;

import java.util.ArrayList;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:有氧运动
 */
public class ExerciseRecordListActivity extends UIBaseActivity {

    private static final int REQUEST_CODE_FOR_REFRESH = 1;
    /**
     * 返回键
     */
    private ImageView backIm;
    /**
     * 添加记录
     */
    private TextView addRecordTv;
    private RadioGroup radioGroup;
    private ViewPager2 viewPager;

    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        initView();
        initListener();
        initValue();
    }

    private void initValue() {
        fragments = new ArrayList<>();
        fragments.add(ExerciseOxygenFragment.newInstance("1"));
        fragments.add(ExerciseResistanceFragment.newInstance("P"));
        fragments.add(ExerciseFlexibilityFragment.newInstance("R"));

        viewPager.setAdapter(new MyFragmentStateAdapter(this, fragments));
        viewPager.setOffscreenPageLimit(fragments.size());
        radioGroup.check(radioGroup.getChildAt(0).getId());
        viewPager.setCurrentItem(0);
        setCurrentPosiStatus(0);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int index = radioGroup.indexOfChild(radioGroup.findViewById(checkedId));
            viewPager.setCurrentItem(index);
            if (index == 0) {
                addRecordTv.setVisibility(View.VISIBLE);
            } else {
                addRecordTv.setVisibility(View.GONE);
            }
            setCurrentPosiStatus(radioGroup.indexOfChild(radioGroup.findViewById(checkedId)));
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                radioGroup.check(radioGroup.getChildAt(i).getId());
                if (i == 0) {
                    addRecordTv.setVisibility(View.VISIBLE);
                } else {
                    addRecordTv.setVisibility(View.GONE);
                }
                setCurrentPosiStatus(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setCurrentPosiStatus(int position) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton button = (RadioButton) radioGroup.getChildAt(i);
            if (position == i) {
                button.setTextSize(18);
            } else {
                button.setTextSize(16);
            }
        }
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_oxygen_record, null);
        backIm = view.findViewById(R.id.iv_exercise_record_back);
        addRecordTv = view.findViewById(R.id.tv_exercise_record_add);
        radioGroup = view.findViewById(R.id.rg_exercise_record);
        viewPager = view.findViewById(R.id.vp_exercise_record);
        containerView().addView(view);
    }

    private void initListener() {
        backIm.setOnClickListener(v -> finish());
//        addRecordTv.setOnClickListener(v -> {
//            Intent intent = new Intent(getPageContext(), ExercisePlanAddRecordActivity.class);
//            startActivityForResult(intent, REQUEST_CODE_FOR_REFRESH);
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FOR_REFRESH:
                    ExerciseOxygenFragment fragment = (ExerciseOxygenFragment) fragments.get(0);
                    fragment.refresh();
                    break;
                default:
                    break;
            }
        }
    }
}
