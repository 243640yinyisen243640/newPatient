package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationIntelligenceAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationAllInfo;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:智能学习
 */
public class EducationIntelligenceActivity extends UIBaseListRecycleViewActivity<EducationInfo> {
    private List<EducationInfo> educationInfos;
    private EducationIntelligenceAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        initTopView();
        noDataText("抱歉，没有找到相关课程");
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        loadViewManager().setOnClickListener(LoadStatus.NODATA, view -> loadViewManager().changeLoadState(LoadStatus.LOADING));

    }

    @Override
    protected boolean isLoadMore() {
        return false;
    }

    @Override
    protected boolean isRefresh() {
        return false;
    }

    private void initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_education_intelligence_study, null);
        ImageView backImageView = topView.findViewById(R.id.iv_education_study_back);
        TextView classifyTextView = topView.findViewById(R.id.tv_education_study_classify);
        backImageView.setOnClickListener(v -> finish());
        classifyTextView.setOnClickListener(v -> startActivity(new Intent(getPageContext(), EducationClassifyActivity.class)));
        topViewManager().topView().addView(topView);
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = HomeDataManager.teachSeriesListIndex(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ARCHIVES_ID), (call, response) -> {
            if ("0000".equals(response.code)) {
                EducationAllInfo educationAllInfo = (EducationAllInfo) response.object;
                callBack.callBack(educationAllInfo.getIndexList());
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });

        addRequestCallToMap("teachSeriesListIndex", requestCall);

    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<EducationInfo> list) {
        return adapter = new EducationIntelligenceAdapter(getPageContext(), list, "1", new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                //那个按钮的展示状态 0展开 1收起状态 2没有数据隐藏
                switch (view.getId()) {
                    case R.id.ll_education_study_click:
                        if (list.get(position).getIsExpand() == 1) {
                            list.get(position).setIsExpand(0);
                        } else if (list.get(position).getIsExpand() == 0) {
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
                        Intent intent = new Intent(getPageContext(), EducationDetailsActivity.class);
                        intent.putExtra("type", list.get(position).getTeachEssayAppVos().get(index).getType());
                        intent.putExtra("sid", list.get(position).getSid());
                        intent.putExtra("essayId", list.get(position).getTeachEssayAppVos().get(index).getEssayId());
                        intent.putExtra("fromWhere", "1");
                        startActivity(intent);
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
