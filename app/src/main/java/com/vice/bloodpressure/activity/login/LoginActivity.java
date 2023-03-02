package com.vice.bloodpressure.activity.login;

import android.content.Intent;
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
import com.vice.bloodpressure.fragment.login.LoginCodeFragment;
import com.vice.bloodpressure.fragment.login.LoginPwdFragment;

import java.util.ArrayList;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:登录
 */
public class LoginActivity extends UIBaseActivity {
    private RadioGroup radioGroup;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("登录");
        topViewManager().moreTextView().setText("注册");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), RegisterActivity.class));
        });
        initView();
        initValue();
    }

    private void initValue() {

        ArrayList<Fragment> fragments = new ArrayList<>();

        LoginPwdFragment pwdFragment = new LoginPwdFragment();
        LoginCodeFragment codeFragment = new LoginCodeFragment();
        fragments.add(pwdFragment);

        fragments.add(codeFragment);

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

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_login, null);
        radioGroup = view.findViewById(R.id.rg_login);
        viewPager = view.findViewById(R.id.vp_login);
        containerView().addView(view);
    }
}
