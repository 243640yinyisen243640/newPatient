package com.vice.bloodpressure.fragment.fservice;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
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

import static android.app.Activity.RESULT_OK;


/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/3/28 13:33
 */
public class SevenAndThirtyBloodSugarListFragment extends UIBaseLoadFragment {
    private static final int REQUEST_CODE_FOR_REFRESH = 1;
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
        sureLinearLayout.setOnClickListener(v ->
        {
            Intent intent = new Intent(getPageContext(), ServiceBloodAddActivity.class);
            startActivityForResult(intent, REQUEST_CODE_FOR_REFRESH);
        });
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

            }

            @Override
            public void adapterClickListener(int position, int index, View view) {
                Log.i("yys", "position==" + position + "===" + "index===" + index);
                BloodChildInfo bloodChildInfo = bloodAllInfo.getRecords().get(position);
                if (Integer.parseInt(bloodChildInfo.getValue().get(index).getBgCount()) > 1) {
                    Intent intent = new Intent(getPageContext(), ServiceBloodSugarActivity.class);
                    intent.putExtra("beginTime", bloodAllInfo.getRecords().get(position).getDate());
                    intent.putExtra("timeType", bloodChildInfo.getValue().get(index).getType());
                    startActivity(intent);
                }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_FOR_REFRESH) {

                onPageLoad();
            }
        }
    }
}
