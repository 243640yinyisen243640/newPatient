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
import com.vice.bloodpressure.adapter.service.ServiceBloodScleroproteinAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.HealthyDataAllInfo;
import com.vice.bloodpressure.model.HealthyDataChildInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:血红蛋白列表
 */
public class ServiceBloodScleroproteinListActivity extends UIBaseListRecycleViewForBgActivity<HealthyDataChildInfo> implements View.OnClickListener {
    private static final int REQUEST_CODE_FOR_REFRESH = 1;
    private ImageView backImageView;
    private LinearLayout addLinearLayout;
    private TextView startTextView;
    private TextView endTextView;
    private TextView lowTextView;
    private TextView highTextView;
    /**
     * 筛选的开始时间
     */
    private String startTime = "";
    /**
     * 筛选的结束时间
     */
    private String endTime = "";

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

    private void initListener() {
        startTextView.setOnClickListener(this);
        endTextView.setOnClickListener(this);
        backImageView.setOnClickListener(this);
        addLinearLayout.setOnClickListener(this);
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
        textTextView.setText("添加血氧数据");
        f2.gravity = Gravity.BOTTOM;
        containerView().addView(view, f2);
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = ServiceDataManager.getBloodScleroproteinList(UserInfoUtils.getArchivesId(getPageContext()), getPageIndex() + "",BaseDataManager.PAGE_SIZE+"", "9", startTime, endTime, (call, response) -> {
            if ("0000".equals(response.code)) {
                HealthyDataAllInfo scleroproteinInfo = (HealthyDataAllInfo) response.object;
                lowTextView.setText(null == scleroproteinInfo.getHbMin() ? "" : scleroproteinInfo.getHbMin());
                highTextView.setText(null == scleroproteinInfo.getHbMax() ? "" : scleroproteinInfo.getHbMax());
                callBack.callBack(scleroproteinInfo.getValue());
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("selectMonitorHtnList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<HealthyDataChildInfo> list) {
        return new ServiceBloodScleroproteinAdapter(getPageContext(), list);
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
                            endTime = String.valueOf(object);
                            endTextView.setText(object.toString());
                            setPageIndex(1);
                            onPageLoad();
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
                Intent intent = new Intent(getPageContext(), ServiceSaccharifyAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_REFRESH);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_FOR_REFRESH) {
                setPageIndex(1);
                onPageLoad();
            }
        }
    }
}
