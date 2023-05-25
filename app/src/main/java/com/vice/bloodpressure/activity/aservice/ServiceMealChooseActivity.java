package com.vice.bloodpressure.activity.aservice;

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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.fragment.fservice.ServiceMealChooseListFragment;
import com.vice.bloodpressure.model.MealChildInfo;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 类名：
 * 传参：meal
 * 描述: 食物类型
 * 作者: beauty
 * 创建日期: 2023/2/1 14:59
 */

public class ServiceMealChooseActivity extends UIBaseLoadActivity {
    private EditText etSearch;
    private List<Fragment> fragmentList;
    private List<MealChildInfo> titles = new ArrayList<>();
    private final int[] tabDrawable = {R.drawable.selector_choose_meal_one, R.drawable.selector_choose_meal_two,
            R.drawable.selector_choose_meal_three, R.drawable.selector_choose_meal_fore, R.drawable.selector_choose_meal_five};
    private ViewPager2 viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("食物类型");
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = ServiceDataManager.getMealType((call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                titles = (List<MealChildInfo>) response.object;
                setTab();
            } else {

            }
        }, (call, t) -> {

        });
        addRequestCallToMap("bgTargetByType", requestCall);
    }

    private void initListener() {
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

    private void setTab() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            ServiceMealChooseListFragment fragment = new ServiceMealChooseListFragment(titles.get(i).getId());
            fragmentList.add(fragment);
        }
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
                    ImageView ivTab = customView.findViewById(R.id.iv_choose_meal_tab);
                    tvTab.setTypeface(Typeface.DEFAULT);
                    tvTab.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_8a));
                    if (i == position) {
                        XyImageUtils.loadRoundImage(getPageContext(), R.drawable.shape_defaultbackground_5, titles.get(i).getImgurl(), ivTab);
                        tvTab.setTypeface(Typeface.DEFAULT_BOLD);
                        tvTab.setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
                    }else {
                        XyImageUtils.loadRoundImage(getPageContext(), R.drawable.shape_defaultbackground_5, titles.get(i).getGreyImgUrl(), ivTab);
                    }
                }
            }
        });
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_meal_choose, null);
        etSearch = view.findViewById(R.id.et_service_meal_choose_search);
        tabLayout = view.findViewById(R.id.tl_service_meal_choose);
        viewPager = view.findViewById(R.id.vp_service_meal_choose);
        containerView().addView(view);
    }

    private void setTab(TabLayout.Tab tab, int position) {
        LinearLayout customView = (LinearLayout) View.inflate(getPageContext(), R.layout.tab_choose_meal, null);
        TextView tvTab = customView.findViewById(R.id.tv_choose_meal_tab);
        ImageView ivIndicator = customView.findViewById(R.id.iv_choose_meal_tab_indicator);
        tvTab.setText(titles.get(position).getFoodlei());

        ivIndicator.setImageDrawable(getDrawable(R.drawable.selector_choose_meal_indicator));
        tab.setCustomView(customView);
    }


}
