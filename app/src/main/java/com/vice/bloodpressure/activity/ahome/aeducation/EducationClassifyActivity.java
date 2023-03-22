package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationClassifyLeftAdapter;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.fragment.fhome.education.EducationClassifySecondFragment;
import com.vice.bloodpressure.model.EducationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationClassifyActivity extends UIBaseLoadActivity {

    private ListView leftListView;
    private TextView searchTextView;
    private ViewPager2 viewPager;

    private List<EducationInfo> educationInfos;


    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("分类");
        topViewManager().lineViewVisibility(View.VISIBLE);
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        searchTextView.setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), EducationIntelligenceSearchActivity.class));
        });
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        ////true:滑动，false：禁止滑动
        viewPager.setUserInputEnabled(false);
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_education_classify, null);
        leftListView = getViewByID(view, R.id.lv_education_class_first);
        searchTextView = getViewByID(view, R.id.tv_education_class_search);
        viewPager = getViewByID(view, R.id.vp_education_class_second);
        containerView().addView(view);
    }


    @Override
    protected void onPageLoad() {
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);

        educationInfos = new ArrayList<>();
        educationInfos.add(new EducationInfo("1型", "", "1"));
        educationInfos.add(new EducationInfo("2型", "", "2"));
        fragments = new ArrayList<>();
        for (int i = 0; i < educationInfos.size(); i++) {
            fragments.add(EducationClassifySecondFragment.newInstance(i + ""));
        }
        viewPager.setAdapter(new MyFragmentStateAdapter(this, fragments) {
        });
        educationInfos.get(0).setIsCheck("1");
        viewPager.setCurrentItem(0);//默认选中项
        viewPager.setOffscreenPageLimit(fragments.size());


        EducationClassifyLeftAdapter leftAdapter = new EducationClassifyLeftAdapter(getPageContext(), educationInfos);
        leftListView.setAdapter(leftAdapter);

        leftListView.setOnItemClickListener((parent, view, position, id) -> {
            viewPager.setCurrentItem(position);

            //初始化
            for (int i = 0; i < educationInfos.size(); i++) {
                if (position == i) {
                    educationInfos.get(i).setIsCheck("1");
                } else {
                    educationInfos.get(i).setIsCheck("0");
                }
            }
            leftAdapter.notifyDataSetChanged();

        });


        //        if (classifySecondFragment == null) {
        //            classifySecondFragment = new EducationClassifySecondFragment(leftListView);
        //            Bundle bundle = new Bundle();
        //            bundle.putString("firstClassID", classfiId);
        //            //数据传递到fragment中
        //            classifySecondFragment.setArguments(bundle);
        //            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //            transaction.add(R.id.fl_education_class_second, classifySecondFragment, "fl_classify_second");
        //            transaction.show(classifySecondFragment).commit();
        //            getSupportFragmentManager().executePendingTransactions();
        //
        //        }


    }


}
