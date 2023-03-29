package com.vice.bloodpressure.fragment.fservice;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.SevenBottomAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.model.ServiceInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/3/28 13:33
 */
public class SevenAndThirtyBloodSugarListFragment extends UIBaseLoadFragment {
    private TextView lowTextView;
    private TextView averTextView;
    private TextView highTextView;
    private RecyclerView recyclerView;
    private LinearLayout sureLinearLayout;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        initValue();
    }

    private void initValue() {
        List<ServiceInfo> list = new ArrayList<>();
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        list.add(new ServiceInfo("2/22", "6.5"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getPageContext()));

        recyclerView.setAdapter(new SevenBottomAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {

            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        }));
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_service_blood_week_month, null);
        lowTextView = view.findViewById(R.id.tv_service_blood_seven_low);
        averTextView = view.findViewById(R.id.tv_service_blood_seven_aver);
        highTextView = view.findViewById(R.id.tv_service_blood_seven_high);
        recyclerView = view.findViewById(R.id.rv_service_blood_seven);
        sureLinearLayout = view.findViewById(R.id.ll_service_blood_seven_sure);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }
}
