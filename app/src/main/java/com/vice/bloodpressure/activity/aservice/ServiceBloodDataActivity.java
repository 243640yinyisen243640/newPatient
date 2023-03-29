package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.fragment.fservice.SevenAndThirtyBloodSugarListFragment;

import java.util.ArrayList;


/**
 * 类名：
 * 传参：
 * 描述:血糖数据
 * 作者: beauty
 * 创建日期: 2023/3/28 13:59
 */
public class ServiceBloodDataActivity extends UIBaseActivity {
    private ImageView backImageView;
    private TextView moreTextView;
    private TextView startTimeTextView;
    private TextView endTimeTextView;
    private RadioGroup radioGroup;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        setTitle("血糖记录");
        initView();
        initValue();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_blood_data, null);
        backImageView = view.findViewById(R.id.iv_service_blood_data_back);
        moreTextView = view.findViewById(R.id.tv_service_blood_data_more);
        startTimeTextView = view.findViewById(R.id.tv_service_blood_data_start_time);
        endTimeTextView = view.findViewById(R.id.tv_service_blood_data_end_time);
        radioGroup = view.findViewById(R.id.rg_service_blood_data);
        viewPager = view.findViewById(R.id.vp_service_blood_data);
        containerView().addView(view);
    }


    private void initValue() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        String userid = "717272";
        for (int i = 0; i < 2; i++) {
            SevenAndThirtyBloodSugarListFragment fragment = new SevenAndThirtyBloodSugarListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("userid", userid);
            fragment.setArguments(bundle);
            fragments.add(fragment);

            viewPager.setAdapter(new MyFragmentStateAdapter(this, fragments));
            viewPager.setOffscreenPageLimit(fragments.size());
            radioGroup.check(radioGroup.getChildAt(0).getId());
            viewPager.setCurrentItem(0);

            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                int index = group.indexOfChild(group.findViewById(checkedId));
                viewPager.setCurrentItem(index);
            });

            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    radioGroup.check(radioGroup.getChildAt(i).getId());

                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }


    }


}
