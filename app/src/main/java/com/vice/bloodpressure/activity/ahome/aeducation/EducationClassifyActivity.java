package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationClassifyLeftAdapter;
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

    private List<EducationInfo> educationInfos;

    private EducationClassifySecondFragment classifySecondFragment;

    private String classfiId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("分类");
        topViewManager().lineViewVisibility(View.VISIBLE);
        initView();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_education_classify, null);
        leftListView = getViewByID(view, R.id.lv_education_class_first);
        searchTextView = getViewByID(view, R.id.tv_education_class_search);
        searchTextView.setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), EducationIntelligenceSearchActivity.class));
        });
        containerView().addView(view);
    }


    @Override
    protected void onPageLoad() {
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        educationInfos = new ArrayList<>();
        educationInfos.add(new EducationInfo("1型", "", "1"));
        educationInfos.add(new EducationInfo("2型", "", "2"));
        EducationClassifyLeftAdapter leftAdapter = new EducationClassifyLeftAdapter(getPageContext(), educationInfos);
        leftListView.setAdapter(leftAdapter);
        educationInfos.get(0).setIsCheck("1");
        leftListView.setOnItemClickListener((parent, view, position, id) -> {
            Log.i("yys", "setOnItemClickListenerposition===" + position);
            //初始化
            for (int i = 0; i < educationInfos.size(); i++) {
                if (position == i) {
                    educationInfos.get(i).setIsCheck("1");
                } else {
                    educationInfos.get(i).setIsCheck("0");
                }
            }
            leftAdapter.notifyDataSetChanged();
            //获取当前选中的item
            if (classifySecondFragment != null) {
                classifySecondFragment.refresh(educationInfos.get(position).getClassifyId());
            }
        });
        if (classifySecondFragment == null) {
            classifySecondFragment = new EducationClassifySecondFragment(leftListView);
            Bundle bundle = new Bundle();
            bundle.putString("firstClassID", classfiId);
            //数据传递到fragment中
            classifySecondFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_education_class_second, classifySecondFragment, "fl_classify_second");
            transaction.show(classifySecondFragment).commit();
            getSupportFragmentManager().executePendingTransactions();

        }


    }


}
