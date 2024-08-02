package com.vice.bloodpressure.activity.auser;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.fragment.fuser.UserFilesBaseInfoFragment;
import com.vice.bloodpressure.fragment.fuser.UserFilesFamilyFragment;
import com.vice.bloodpressure.fragment.fuser.UserFilesIllFragment;
import com.vice.bloodpressure.fragment.fuser.UserFilesLiveStyleFragment;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.XyImageUtils;
import com.vice.bloodpressure.view.NestRadioGroup;

import java.util.ArrayList;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:我的档案
 */
public class UserFilesActivity extends UIBaseActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private NestRadioGroup radioGroup;
    private ViewPager2 viewPager;
    private ImageView imgImageView;
    private TextView nameTextView;

    private String sex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().setBackgroundColor(getResources().getColor(R.color.main_base_color));
        topViewManager().statusBarView().setBackgroundColor(getResources().getColor(R.color.main_base_color));
        topViewManager().titleTextView().setTextColor(getResources().getColor(R.color.text_white));
        topViewManager().backTextView().setCompoundDrawablesWithIntrinsicBounds(R.drawable.base_top_back_white, 0, 0, 0);
        topViewManager().titleTextView().setText("我的档案");
        topViewManager().lineViewVisibility(View.GONE);
        sex = getIntent().getStringExtra("sex");
        initView();
        initValue();
    }

    public ArrayList getFragments(){
        return fragments;
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_center_files, null);
        imgImageView = view.findViewById(R.id.iv_user_files_avatar);
        nameTextView = view.findViewById(R.id.tv_user_files_nickname);
        radioGroup = view.findViewById(R.id.rg_user_files);
        viewPager = view.findViewById(R.id.vp_user_files_info);
        containerView().addView(view);
    }


    private void initValue() {
        nameTextView.setText(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.NICK_NAME, ""));

        if ("1".equals(sex)) {
            XyImageUtils.loadRoundImage(getPageContext(), R.drawable.default_male_head_circle, "", imgImageView);
        } else {
            XyImageUtils.loadRoundImage(getPageContext(), R.drawable.default_female_head_circle, "", imgImageView);
        }


        UserFilesBaseInfoFragment infoFragment = new UserFilesBaseInfoFragment();
        UserFilesLiveStyleFragment liveStyleFragment = new UserFilesLiveStyleFragment();
        UserFilesIllFragment illFragment = new UserFilesIllFragment();
        UserFilesFamilyFragment familyFragment = new UserFilesFamilyFragment();
        fragments.add(infoFragment);
        fragments.add(liveStyleFragment);
        fragments.add(illFragment);
        fragments.add(familyFragment);

        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(new MyFragmentStateAdapter(this, fragments));
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
