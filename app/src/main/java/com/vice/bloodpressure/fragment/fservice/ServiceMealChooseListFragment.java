package com.vice.bloodpressure.fragment.fservice;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServiceChooseMealListAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.MealChildInfo;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:血压列表
 */
public class ServiceMealChooseListFragment extends UIBaseLoadFragment {
    private String classId;
    private List<MealChildInfo> childInfos;

    public ServiceMealChooseListFragment(String classId) {
        this.classId = classId;
    }

    private TextView numTextView;
    private ListView contentListView;
    private TextView sureTextView;
    private ServiceChooseMealListAdapter adapter;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        contentListView.setOnItemClickListener((parent, view, position, id) -> {
            childInfos.get(position).setCheck(!childInfos.get(position).isCheck());
            adapter.notifyDataSetChanged();
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_service_meal_type, null);
        contentListView = view.findViewById(R.id.lv_service_meal_type);
        numTextView = view.findViewById(R.id.tv_service_meal_add_have_choose);
        sureTextView = view.findViewById(R.id.tv_service_meal_add_have_sure);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = ServiceDataManager.getMealTypeList(classId, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                childInfos = (List<MealChildInfo>) response.object;
                bindData();
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getMealTypeList", requestCall);
    }

    private void bindData() {
        adapter = new ServiceChooseMealListAdapter(getPageContext(), childInfos);
        contentListView.setAdapter(adapter);
    }

}
