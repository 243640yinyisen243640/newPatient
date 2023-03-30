package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServiceBloodOxygenAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.ServiceInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:血氧列表
 */
public class ServiceBloodOxygenListActivity extends UIBaseListRecycleViewForBgActivity<ServiceInfo> implements View.OnClickListener {
    private ImageView backImageView;
    private LinearLayout addLinearLayout;
    private TextView startTextView;
    private TextView endTextView;
    private TextView lowTextView;
    private TextView highTextView;

    private String startTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
        setPublicBottom();
        initListener();
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), false));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

    }

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_service_top_with_time, null);
        startTextView = topView.findViewById(R.id.tv_service_blood_data_start_time);
        endTextView = topView.findViewById(R.id.tv_service_blood_data_end_time);
        TextView titleTextView = topView.findViewById(R.id.tv_service_blood_data_title);
        backImageView = topView.findViewById(R.id.iv_service_blood_data_back);
        LinearLayout allLinearLayout = topView.findViewById(R.id.ll_service_blood_oxygen_all);
        lowTextView = topView.findViewById(R.id.tv_service_blood_oxygen_low);
        highTextView = topView.findViewById(R.id.tv_service_blood_oxygen_high);
        titleTextView.setText("糖化血红蛋白");
        allLinearLayout.setVisibility(View.VISIBLE);
        return topView;
    }

    private void setPublicBottom() {

        View view = View.inflate(getPageContext(), R.layout.include_save_bottom, null);
        addLinearLayout = view.findViewById(R.id.ll_service_base_bottom_sure);
        TextView textTextView = view.findViewById(R.id.tv_service_base_bottom_text);
        FrameLayout.LayoutParams f2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textTextView.setText("添加糖化血红蛋白数据");

        f2.gravity = Gravity.BOTTOM;
        containerView().addView(view, f2);


    }

    private void initListener() {
        startTextView.setOnClickListener(this);
        endTextView.setOnClickListener(this);
        backImageView.setOnClickListener(this);
        addLinearLayout.setOnClickListener(this);
    }

    @Override
    protected void getListData(CallBack callBack) {
        List<ServiceInfo> oxygenList = new ArrayList<>();
        oxygenList.add(new ServiceInfo("2022-05-06", "60%"));
        oxygenList.add(new ServiceInfo("2022-05-06", "60%"));
        oxygenList.add(new ServiceInfo("2022-05-06", "60%"));
        oxygenList.add(new ServiceInfo("2022-05-06", "60%"));
        callBack.callBack(oxygenList);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<ServiceInfo> list) {
        return new ServiceBloodOxygenAdapter(getPageContext(), list);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_blood_data_start_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        startTime = String.valueOf(object);
                        startTextView.setText(object.toString());
                    }
                });
                break;
            case R.id.tv_service_blood_data_end_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        XyTimeUtils.compareTwoTime(startTime, object.toString());
                        endTextView.setText(object.toString());
                    }
                });
                break;
            case R.id.iv_service_blood_data_back:
                finish();
                break;
            case R.id.ll_service_base_bottom_sure:
                startActivity(new Intent(getPageContext(), ServiceBloodOxygenAddActivity.class));
                break;
            default:
                break;
        }
    }
}
