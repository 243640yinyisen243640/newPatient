package com.vice.bloodpressure.activity.ahome.aeducation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationCatalogueAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述: 目录
 * 作者: beauty
 * 创建日期: 2023/2/28 14:46
 */
public class EducationInteCatalogueListActivity extends UIBaseLoadActivity {

    private EducationCatalogueAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private List<EducationInfo> educationInfos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("目录");
        initView();
        initValue();
        //        mRecyclerView.setBackgroundColor(ContextCompat.getColor(getPageContext(), R.color.background));
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        loadViewManager().setOnClickListener(LoadStatus.NODATA, view -> loadViewManager().changeLoadState(LoadStatus.LOADING));
    }


    private void initValue() {
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), false));
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_education_catalogue, null);
        mRecyclerView = getViewByID(view, R.id.rv_catalogue);
        containerView().addView(view);
    }


    @Override
    protected void onPageLoad() {
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        educationInfos = new ArrayList<>();
        educationInfos.add(new EducationInfo("第二节:高血压，隐形的杀手!", "学习中"));
        educationInfos.add(new EducationInfo("第二节:高血压，隐形的杀手!", "学习中"));
        educationInfos.add(new EducationInfo("第二节:高血压，隐形的杀手!", "未完成"));

        mAdapter = new EducationCatalogueAdapter(getPageContext(), educationInfos, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.ll_education_catalogue_click:
                        //跳到详情

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {
                switch (view.getId()) {
                    case R.id.ll_education_study_child_click:
                        break;
                    default:
                        break;

                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

}
