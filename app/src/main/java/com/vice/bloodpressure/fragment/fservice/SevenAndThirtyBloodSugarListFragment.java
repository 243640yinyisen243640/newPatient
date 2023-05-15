package com.vice.bloodpressure.fragment.fservice;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aservice.ServiceBloodAddActivity;
import com.vice.bloodpressure.activity.aservice.ServiceBloodSugarActivity;
import com.vice.bloodpressure.adapter.service.SevenBottomAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.BloodAllInfo;
import com.vice.bloodpressure.model.BloodChildInfo;
import com.vice.bloodpressure.utils.UserInfoUtils;

import retrofit2.Call;


/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/3/28 13:33
 */
public class SevenAndThirtyBloodSugarListFragment extends UIBaseLoadFragment {
    private TextView lowTextView;
    private TextView lowTextTextView;
    private TextView averTextView;
    private TextView averTextTextView;
    private TextView highTextView;
    private TextView highTextTextView;
    private RecyclerView recyclerView;
    private TextView tipsTextView;
    private LinearLayout sureLinearLayout;
    /**
     * 开始时间
     */
    private String beginTime = "";
    /**
     * 结束时间
     */
    private String endTime = "";
    private String dateType = "";

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        dateType = getArguments().getString("dateType");
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        sureLinearLayout.setOnClickListener(v -> startActivity(new Intent(getPageContext(), ServiceBloodAddActivity.class)));
    }

    private void initValue(BloodAllInfo bloodAllInfo) {
        lowTextView.setText(bloodAllInfo.getMin());
        lowTextTextView.setText(bloodAllInfo.getRecords().size() + "日血糖最低值");
        highTextView.setText(bloodAllInfo.getMax());
        highTextTextView.setText(bloodAllInfo.getRecords().size() + "日血糖最高值");
        averTextView.setText(bloodAllInfo.getAvg());
        averTextTextView.setText(bloodAllInfo.getRecords().size() + "日血糖平均值");


        tipsTextView.setText(String.format(getString(R.string.service_blood_data_tips_format), bloodAllInfo.getRecords().size() + "", bloodAllInfo.getBgSize(), bloodAllInfo.getLimosisCount(), bloodAllInfo.getUnLimosisCount()));

        recyclerView.setLayoutManager(new LinearLayoutManager(getPageContext()));

        recyclerView.setAdapter(new SevenBottomAdapter(getPageContext(), bloodAllInfo.getRecords(), new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                BloodChildInfo bloodChildInfo = bloodAllInfo.getRecords().get(position);
                for (int i = 0; i < bloodChildInfo.getValue().size(); i++) {
                    if (Integer.parseInt(bloodChildInfo.getValue().get(i).getBgCount()) > 1) {
                        Intent intent = new Intent(getPageContext(), ServiceBloodSugarActivity.class);
                        intent.putExtra("beginTime", bloodAllInfo.getRecords().get(i).getDate());
                        intent.putExtra("timeType", (i + 1) + "");
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        }));
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_service_blood_week_month, null);
        lowTextView = view.findViewById(R.id.tv_service_blood_seven_low);
        lowTextTextView = view.findViewById(R.id.tv_service_blood_seven_low_text);
        averTextView = view.findViewById(R.id.tv_service_blood_seven_aver);
        averTextTextView = view.findViewById(R.id.tv_service_blood_seven_aver_text);
        highTextView = view.findViewById(R.id.tv_service_blood_seven_high);
        highTextTextView = view.findViewById(R.id.tv_service_blood_seven_high_text);
        recyclerView = view.findViewById(R.id.rv_service_blood_seven);
        tipsTextView = view.findViewById(R.id.tv_service_blood_seven_tips);
        sureLinearLayout = view.findViewById(R.id.ll_service_blood_seven_sure);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

        Call<String> requestCall = ServiceDataManager.getExceptionData(beginTime, endTime, dateType, UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                BloodAllInfo bloodAllInfo = (BloodAllInfo) response.object;
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                initValue(bloodAllInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getExceptionData", requestCall);
    }

    public void refresh(String beginTime, String endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        dateType = "";

        onPageLoad();
    }
}
