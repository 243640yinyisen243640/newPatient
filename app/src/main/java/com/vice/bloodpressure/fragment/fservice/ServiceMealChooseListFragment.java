package com.vice.bloodpressure.fragment.fservice;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServiceChooseMealListAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.model.ServiceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:血压列表
 */
public class ServiceMealChooseListFragment extends UIBaseLoadFragment {

    private TextView numTextView;
    private RecyclerView recyclerView;
    private TextView sureTextView;
    private ServiceChooseMealListAdapter adapter;
    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getPageContext());
        recyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_service_meal_type, null);
        recyclerView = view.findViewById(R.id.rv_service_meal_type);
        numTextView = view.findViewById(R.id.tv_service_meal_add_have_choose);
        sureTextView = view.findViewById(R.id.tv_service_meal_add_have_sure);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        List<ServiceInfo> oxygenList = new ArrayList<>();
        oxygenList.add(new ServiceInfo("胡萝卜", "22", "100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22", "100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22", "100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22", "100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22", "100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22", "100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22", "100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22", "100"));
        adapter = new ServiceChooseMealListAdapter(getPageContext(), oxygenList, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.ll_service_meal_choose_click:
                        oxygenList.get(position).setCheck(!oxygenList.get(position).isCheck());
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        });
        recyclerView.setAdapter(adapter);
    }

}
