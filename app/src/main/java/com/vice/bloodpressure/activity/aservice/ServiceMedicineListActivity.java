package com.vice.bloodpressure.activity.aservice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.fragment.fservice.ServiceMedicineRecordFragment;
import com.vice.bloodpressure.fragment.fservice.ServiceMedicineRemindFragment;

import java.util.ArrayList;


/**
 * 类名：
 * 传参：
 * 描述:用药数据
 * 作者: beauty
 * 创建日期: 2023/3/28 13:59
 */
public class ServiceMedicineListActivity extends UIBaseActivity {
    private RadioGroup radioGroup;
    private ViewPager2 viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("用药");
        initView();
        initValue();
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_medicine_add, null);
        radioGroup = view.findViewById(R.id.rg_service_medicine);
        viewPager = view.findViewById(R.id.vp_service_medicine);
        containerView().addView(view);
    }


    private void initValue() {
        ArrayList<Fragment> fragments;

        ServiceMedicineRecordFragment recordFragment = new ServiceMedicineRecordFragment();
        ServiceMedicineRemindFragment remindFragment = new ServiceMedicineRemindFragment();

        fragments = new ArrayList<>();
        fragments.add(recordFragment);
        fragments.add(remindFragment);

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
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    if (j == i) {
                        ((RadioButton) radioGroup.getChildAt(j)).setTypeface(Typeface.DEFAULT_BOLD);
                    } else {
                        ((RadioButton) radioGroup.getChildAt(j)).setTypeface(Typeface.DEFAULT);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }


}
