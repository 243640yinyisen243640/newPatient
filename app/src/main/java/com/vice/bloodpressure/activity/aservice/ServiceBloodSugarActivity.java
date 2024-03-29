package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServiceBloodSugarAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.BloodThirdInfo;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:beginTime 时间    timeType  传过来的1...8
 * # 1 空腹
 * # 2 早餐后
 * # 3 午餐前
 * # 4 午餐后
 * # 5 晚餐前
 * # 6 晚餐后
 * # 7 睡前
 * # 8 凌晨
 * 描述:血糖多条数据
 */
public class ServiceBloodSugarActivity extends UIBaseLoadActivity {
    private TextView timeTextView;
    private RecyclerView valueRecyclerView;

    /**
     * 时间
     */
    private String beginTime;
    /**
     * 时间类型
     * # 1 空腹
     * * # 2 早餐后
     * * # 3 午餐前
     * * # 4 午餐后
     * * # 5 晚餐前
     * * # 6 晚餐后
     * * # 7 睡前
     * * # 8 凌晨
     */
    private String timeType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beginTime = getIntent().getStringExtra("beginTime");
        timeType = getIntent().getStringExtra("timeType");
        Log.i("yys", "timeType==" + timeType);
        topViewManager().titleTextView().setText(beginTime);
        initView();
        initValues();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getPageContext());
        valueRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

    }

    private void initValues() {
        switch (timeType) {
            case "1":
                timeTextView.setText("空腹");
                break;
            case "2":
                timeTextView.setText("早餐后");
                break;
            case "3":
                timeTextView.setText("午餐前");
                break;
            case "4":
                timeTextView.setText("午餐后");
                break;
            case "5":
                timeTextView.setText("晚餐前");
                break;
            case "6":
                timeTextView.setText("晚餐后");
                break;
            case "7":
                timeTextView.setText("睡前");
                break;
            case "8":
                timeTextView.setText("凌晨");
                break;
            default:
                break;
        }

    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_blood_sugar_more, null);
        timeTextView = view.findViewById(R.id.tv_service_sugar_more_time_activity);
        valueRecyclerView = view.findViewById(R.id.rv_service_sugar_more);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = ServiceDataManager.dateDetail(beginTime, timeType, UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                List<BloodThirdInfo> bloodThirdInfos = (List<BloodThirdInfo>) response.object;
                bindData(bloodThirdInfos);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("dateDetail", requestCall);
    }

    private void bindData(List<BloodThirdInfo> bloodThirdInfos) {
        ServiceBloodSugarAdapter bloodSugarAdapter = new ServiceBloodSugarAdapter(getPageContext(), bloodThirdInfos);
        valueRecyclerView.setAdapter(bloodSugarAdapter);
    }
}
