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
import com.vice.bloodpressure.adapter.service.ServiceMealAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.ServiceInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:饮食数据列表
 */
public class ServiceMealListActivity extends UIBaseListRecycleViewActivity<ServiceInfo> implements View.OnClickListener {
    private ImageView backImageView;
    private LinearLayout addLinearLayout;
    private TextView startTextView;
    private TextView endTextView;

    private String startTime="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

        setPublicBottom();
        initListener();
    }

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_service_top_with_time, null);
        startTextView = topView.findViewById(R.id.tv_service_blood_data_start_time);
        endTextView = topView.findViewById(R.id.tv_service_blood_data_end_time);
        TextView titleTextView = topView.findViewById(R.id.tv_service_blood_data_title);
        backImageView = topView.findViewById(R.id.iv_service_blood_data_back);
        titleTextView.setText("饮食数据");
        return topView;
    }

    private void setPublicBottom() {
        View view = View.inflate(getPageContext(), R.layout.include_save_bottom, null);
        addLinearLayout = view.findViewById(R.id.ll_service_base_bottom_sure);
        TextView textTextView = view.findViewById(R.id.tv_service_base_bottom_text);
        FrameLayout.LayoutParams f2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textTextView.setText("添加饮食数据");
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
        List<ServiceInfo> listText = new ArrayList<>();
        listText.add(new ServiceInfo("2022-05-06", "午餐", "1260"));
        listText.add(new ServiceInfo("2022-05-07", "早餐", "1260"));

        List<ServiceInfo> childList = new ArrayList<>();
        childList.add(new ServiceInfo("米饭", "118", "100"));
        childList.add(new ServiceInfo("米饭", "118", "100"));
        childList.add(new ServiceInfo("米饭", "118", "100"));

        for (int i = 0; i < listText.size(); i++) {
            listText.get(i).setList(childList);
        }
        callBack.callBack(listText);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<ServiceInfo> list) {
        return new ServiceMealAdapter(getPageContext(), list);
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
                        if (XyTimeUtils.compareTwoTime(startTime, object.toString())) {
                            endTextView.setText(object.toString());
                        } else {
                            ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                        }
                    }
                });
                break;
            case R.id.iv_service_blood_data_back:
                finish();
                break;
            case R.id.ll_service_base_bottom_sure:
                startActivity(new Intent(getPageContext(), ServiceMealAddActivity.class));
                break;
            default:
                break;
        }
    }
}
