package com.vice.bloodpressure.fragment.fservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aservice.ServiceMedicineRecordAddActivity;
import com.vice.bloodpressure.adapter.service.ServiceMedicineAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.ServiceInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ServiceMedicineRecordFragment extends UIBaseListRecycleViewForBgFragment<ServiceInfo> {
    public static ServiceMedicineRecordFragment getInstance(String text) {

        ServiceMedicineRecordFragment recordFragment = new ServiceMedicineRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        recordFragment.setArguments(bundle);
        return recordFragment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), false));
        mRecyclerView.setLayoutManager(layoutManager);
        setPublicBottom();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void getListData(CallBack callBack) {
        List<ServiceInfo> oxygenList = new ArrayList<>();
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长展示12个字"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长展示12"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长展示12个"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长展示"));
        callBack.callBack(oxygenList);
    }

    private void setPublicBottom() {
        View view = View.inflate(getPageContext(), R.layout.include_save_bottom, null);
        LinearLayout addLinearLayout = view.findViewById(R.id.ll_service_base_bottom_sure);
        TextView textTextView = view.findViewById(R.id.tv_service_base_bottom_text);
        FrameLayout.LayoutParams f2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textTextView.setText("添加新记录");
        addLinearLayout.setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), ServiceMedicineRecordAddActivity.class));
        });
        f2.gravity = Gravity.BOTTOM;
        containerView().addView(view, f2);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<ServiceInfo> list) {
        return new ServiceMedicineAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.tv_item_service_medicine_edit:
                        break;
                    case R.id.tv_item_service_medicine_look:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        });
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
