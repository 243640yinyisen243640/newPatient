package com.vice.bloodpressure.activity.auser;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.fragment.fuser.UserFilesBaseInfoFragment;
import com.vice.bloodpressure.fragment.fuser.UserFilesFamilyFragment;
import com.vice.bloodpressure.fragment.fuser.UserFilesIllFragment;
import com.vice.bloodpressure.fragment.fuser.UserFilesLiveStyleFragment;

import java.util.ArrayList;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:我的档案
 */
public class UserFilesActivity extends UIBaseActivity {
    private RadioGroup radioGroup;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.activity_user_center_files, null);
        radioGroup = view.findViewById(R.id.rg_user_files);
        viewPager = view.findViewById(R.id.vp_user_files_info);
        containerView().addView(view);
        initValue();
    }

    private void initValue() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        UserFilesBaseInfoFragment infoFragment = new UserFilesBaseInfoFragment();
        UserFilesLiveStyleFragment liveStyleFragment = new UserFilesLiveStyleFragment();
        UserFilesIllFragment illFragment = new UserFilesIllFragment();
        UserFilesFamilyFragment familyFragment = new UserFilesFamilyFragment();
        fragments.add(infoFragment);
        fragments.add(liveStyleFragment);
        fragments.add(illFragment);
        fragments.add(familyFragment);

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
