package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
    private String addExerciseType = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        initView();
        initListener();
        initValue();
    }

    private void initValue() {

        ArrayList<Fragment> fragments = new ArrayList<>();


        fragments = new ArrayList<>();
        fragments.add(ExerciseOxygenFragment.newInstance("1"));
        fragments.add(ExerciseResistanceFragment.newInstance("P"));
        fragments.add(ExerciseFlexibilityFragment.newInstance("R"));

        viewPager.setAdapter(new MyFragmentStateAdapter(this, fragments));
        viewPager.setOffscreenPageLimit(fragments.size());
        radioGroup.check(radioGroup.getChildAt(0).getId());
        viewPager.setCurrentItem(0);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int index = radioGroup.indexOfChild(radioGroup.findViewById(checkedId));
            viewPager.setCurrentItem(index);
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
                Log.i("yys", "onPageSelected==" + i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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
        addRecordTv.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), ExercisePlanAddRecordActivity.class);
            intent.putExtra("type", "");
            startActivity(intent);
        });
    }

}
