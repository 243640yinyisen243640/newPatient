package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationClassifyLeftAdapter;
import com.vice.bloodpressure.adapter.home.EducationClassifyRightAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationClassifyActivity extends UIBaseLoadActivity {

    private ListView leftListView;
    private TextView searchTextView;
    private RecyclerView secondRv;
    private TextView noDataTextView;

    private final List<EducationInfo> rightList = new ArrayList<>();

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

    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_education_classify, null);
        leftListView = getViewByID(view, R.id.lv_education_class_first);
        searchTextView = getViewByID(view, R.id.tv_education_class_search);
        secondRv = getViewByID(view, R.id.rv_education_class_second);
        noDataTextView = getViewByID(view, R.id.tv_education_class_no_data);
        containerView().addView(view);
    }


    @Override
    protected void onPageLoad() {

        Call<String> requestCall = HomeDataManager.teachTypeList((call, response) -> {
            if ("0000".equals(response.code)) {
                List<EducationInfo> educationInfos = (List<EducationInfo>) response.object;
                bindData(educationInfos);

                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }

        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("teachTypeList", requestCall);
    }

    private void bindData(List<EducationInfo> educationInfos) {
        if (educationInfos != null && educationInfos.size() > 0) {
            educationInfos.get(0).setIsCheck("1");
            rightList.clear();
            rightList.addAll(educationInfos.get(0).getTeachTypeDomains());
            if (rightList.size() == 0) {
                noDataTextView.setVisibility(View.VISIBLE);
                secondRv.setVisibility(View.GONE);
            } else {
                noDataTextView.setVisibility(View.GONE);
                secondRv.setVisibility(View.VISIBLE);
            }
            EducationClassifyLeftAdapter leftAdapter = new EducationClassifyLeftAdapter(getPageContext(), educationInfos);
            leftListView.setAdapter(leftAdapter);

            EducationClassifyRightAdapter rightAdapter = new EducationClassifyRightAdapter(getPageContext(), rightList, (position, view) -> {
                Intent intent = new Intent(getPageContext(), EducationIntelligenceSearchActivity.class);
                intent.putExtra("typeId", rightList.get(position).getTypeId());
                startActivity(intent);
            });
            GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
            secondRv.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
            secondRv.setLayoutManager(layoutManager);
            secondRv.setAdapter(rightAdapter);


            leftListView.setOnItemClickListener((parent, view, position, id) -> {
                rightList.clear();
                rightList.addAll(educationInfos.get(position).getTeachTypeDomains());
                if (rightList.size() == 0) {
                    noDataTextView.setVisibility(View.VISIBLE);
                    secondRv.setVisibility(View.GONE);
                } else {
                    noDataTextView.setVisibility(View.GONE);
                    secondRv.setVisibility(View.VISIBLE);
                }
                //初始化
                for (int i = 0; i < educationInfos.size(); i++) {
                    if (position == i) {
                        educationInfos.get(i).setIsCheck("1");
                    } else {
                        educationInfos.get(i).setIsCheck("0");
                    }
                }
                leftAdapter.notifyDataSetChanged();

                rightAdapter.notifyDataSetChanged();

            });
        }

    }

}
