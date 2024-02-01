package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.fragment.fuser.UserCollectArticleFragment;
import com.vice.bloodpressure.fragment.fuser.UserCollectVideoFragment;

import java.util.ArrayList;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:我的收藏
 */
public class UserCollectActivity extends UIBaseActivity {
    private RadioGroup radioGroup;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("我的收藏");
        initView();
        initValue();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_collect, null);
        radioGroup = view.findViewById(R.id.rg_user_collect);
        viewPager = view.findViewById(R.id.vp_user_collect);
        containerView().addView(view);
    }

    private void initValue() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        UserCollectArticleFragment collectArticleFragment = new UserCollectArticleFragment();
        UserCollectVideoFragment videoFragment = new UserCollectVideoFragment();
//        UserCollectVideoFragment collectVideoFragment = new UserCollectVideoFragment();

        fragments = new ArrayList<>();
        fragments.add(collectArticleFragment);
        fragments.add(videoFragment);
//        fragments.add(collectVideoFragment);

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
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
