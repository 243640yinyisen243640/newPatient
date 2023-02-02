package com.vice.bloodpressure.ui.diet;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.ui.adapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.ui.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;
/**
 * 类名：
 * 传参：meal
 * 描述: 自定义饮食
 * 作者: beautiful
 * 创建日期: 2023/2/1 14:59
 */

public class DietProgrammeChooseMealActivity extends UIBaseActivity {
    private EditText etSearch;

    private List<Fragment> fragmentList;
    private final String[] titles = {"主食", "肉类", "蔬菜", "饮品类", "其他"};
    private final int[] tabDrawable = {R.drawable.selector_choose_meal_one, R.drawable.selector_choose_meal_two,
            R.drawable.selector_choose_meal_three, R.drawable.selector_choose_meal_fore, R.drawable.selector_choose_meal_five};
    private ViewPager2 viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.diet_programme_choose_meal, null);
        containerView().addView(view);
        initData();
        top();
        search();

        tabLayout = findViewById(R.id.tl_diet_programme_choose);
        viewPager = findViewById(R.id.vp_diet_programme_choose);
        //禁用预加载
        //        viewPager.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        //设置滑动方向
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(new MyFragmentStateAdapter(this, fragmentList));
        new TabLayoutMediator(tabLayout, viewPager, true, false, (tab, position) -> setTab(tab, position)).attach();
        ////true:滑动，false：禁止滑动
        viewPager.setUserInputEnabled(true);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    TabLayout.Tab tabAt = tabLayout.getTabAt(i);
                    LinearLayout customView = (LinearLayout) tabAt.getCustomView();
                    TextView tvTab = customView.findViewById(R.id.tv_choose_meal_tab);
                    tvTab.setTypeface(Typeface.DEFAULT);
                    tvTab.setTextColor(Color.parseColor("#8A8A8A"));
                    if (i == position) {
                        tvTab.setTypeface(Typeface.DEFAULT_BOLD);
                        tvTab.setTextColor(Color.parseColor("#242424"));
                    }
                }
            }
        });
    }

    private void setTab(TabLayout.Tab tab, int position) {
        LinearLayout customView = (LinearLayout) View.inflate(getPageContext(), R.layout.tab_choose_meal, null);
        ImageView ivTab = customView.findViewById(R.id.iv_choose_meal_tab);
        TextView tvTab = customView.findViewById(R.id.tv_choose_meal_tab);
        ImageView ivIndicator = customView.findViewById(R.id.iv_choose_meal_tab_indicator);
        tvTab.setText(titles[position]);
        ivTab.setImageDrawable(getDrawable(tabDrawable[position]));
        ivIndicator.setImageDrawable(getDrawable(R.drawable.selector_choose_meal_indicator));
        tab.setCustomView(customView);
    }

    private void initData() {
        fragmentList = new ArrayList();
        fragmentList.add(MainFragment.getInstance());
        fragmentList.add(MainFragment.getInstance());
        fragmentList.add(MainFragment.getInstance());
        fragmentList.add(MainFragment.getInstance());
        fragmentList.add(MainFragment.getInstance());
    }

    private void top() {
        topViewManager().titleTextView().setText(getIntent().getStringExtra("meal"));
        topViewManager().moreTextView().setText("保存");
        topViewManager().moreTextView().setTextSize(17);
        topViewManager().moreTextView().setTextColor(Color.parseColor("#00C27F"));
        topViewManager().moreTextView().setOnClickListener(v -> {
            //保存

        });
    }

    private void search() {
        etSearch = findViewById(R.id.et_choose_meal_search);
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String content = etSearch.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    //提示

                    return true;
                }
                //开始搜索

                return true;
            }
            return false;
        });
    }
}
