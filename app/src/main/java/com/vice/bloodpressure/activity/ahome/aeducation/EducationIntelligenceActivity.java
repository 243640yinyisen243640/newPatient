package com.vice.bloodpressure.activity.ahome.aeducation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationIntelligenceAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:智能学习 这里还有一个页面EducationIntelligenceListActivity 等真正用的时候要换成这个页面，背景图不是公共的
 */
public class EducationIntelligenceActivity extends UIBaseListRecycleViewActivity<EducationInfo> {
    private List<EducationInfo> listText;
    private EducationIntelligenceAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        initTopView();
        getPageListView().setBackgroundColor(getResources().getColor(R.color.background));
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        //        mRecyclerView.addItemDecoration(new UserCenterVideoGridDivider(getPageContext(), HHSoftDensityUtils.dip2px(getPageContext(), 3), R.color.white));
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

    }

    private void initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_education_intelligence_study, null);
        topViewManager().topView().addView(topView);
    }

    @Override
    protected void getListData(CallBack callBack) {
        listText = new ArrayList<>();
        listText.add(new EducationInfo("", "系列一：高血压的基础知识", "由于生活环境和生活条件的影响，导致越高血压基础知识 来越多的人患...高...", "学习中", "5"));
        listText.add(new EducationInfo("", "系列二：高血压的基础知识", "非药物治疗是高血压治疗的基础方法。", "学习中", "6"));
        listText.add(new EducationInfo("", "系列三：高血压的基础知识", "由于生活环境和生活条件的影响，导致越高血压基础知识。", "学习中", "7"));

        List<EducationInfo> childList = new ArrayList<>();
        childList.add(new EducationInfo("第一节:知晓血压，了解血压!", "学习中"));
        childList.add(new EducationInfo("第二节:高血压，隐形的杀手!", "学习中"));
        for (int i = 0; i < listText.size(); i++) {
            listText.get(i).setChildList(childList);
        }
        callBack.callBack(listText);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<EducationInfo> list) {
        return adapter = new EducationIntelligenceAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                Log.i("yys", "click===");
                //那个按钮的展示状态 0展开 1收起状态 2没有数据隐藏
                switch (view.getId()) {
                    case R.id.ll_education_study_click:
                        if (list.get(position).getIsExpand() == 1) {
                            list.get(position).setIsExpand(0);
                        }else if (list.get(position).getIsExpand() == 0){
                            list.get(position).setIsExpand(1);
                        }
                        adapter.notifyDataSetChanged();
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
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
