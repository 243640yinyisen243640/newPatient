package com.vice.bloodpressure.activity.aservice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServiceHealthyHistoryAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.ServiceInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:历史评测
 */
public class ServiceHealthyHistoryListActivity extends UIBaseListRecycleViewActivity<ServiceInfo> implements View.OnClickListener {
    private TextView allTextView;
    private TextView tangTextView;
    private TextView mealTextView;
    private TextView exerciseTextView;
    private TextView weightTextView;
    private TextView emoTextView;
    private TextView badTextView;
    private TextView heartTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
        initListener();
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        allTextView.setOnClickListener(this);
        tangTextView.setOnClickListener(this);
        mealTextView.setOnClickListener(this);
        exerciseTextView.setOnClickListener(this);
        weightTextView.setOnClickListener(this);
        emoTextView.setOnClickListener(this);
        badTextView.setOnClickListener(this);
        heartTextView.setOnClickListener(this);
    }


    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_service_history_top, null);
        ImageView backImageView = topView.findViewById(R.id.iv_service_history_back);
        allTextView = topView.findViewById(R.id.tv_service_history_all);
        allTextView.setSelected(true);
        allTextView.setTypeface(Typeface.DEFAULT_BOLD);
        tangTextView = topView.findViewById(R.id.tv_service_history_tang);
        mealTextView = topView.findViewById(R.id.tv_service_history_meal);
        exerciseTextView = topView.findViewById(R.id.tv_service_history_exercise);
        weightTextView = topView.findViewById(R.id.tv_service_history_weight);
        emoTextView = topView.findViewById(R.id.tv_service_history_emo);
        badTextView = topView.findViewById(R.id.tv_service_history_bad);
        heartTextView = topView.findViewById(R.id.tv_service_history_heart);
        backImageView.setOnClickListener(v -> finish());
        return topView;
    }


    @Override
    protected void getListData(CallBack callBack) {
        List<ServiceInfo> normalInfoList = new ArrayList<>();
        normalInfoList.add(new ServiceInfo("2022-06-02", "48", "低风险", "糖尿病风险评测"));
        normalInfoList.add(new ServiceInfo("2022-06-02", "48", "低风险", "糖尿病风险评测"));
        normalInfoList.add(new ServiceInfo("2022-06-02", "48", "低风险", "糖尿病风险评测"));
        normalInfoList.add(new ServiceInfo("2022-06-02", "48", "低风险", "糖尿病风险评测"));
        normalInfoList.add(new ServiceInfo("2022-06-02", "48", "低风险", "糖尿病风险评测"));
        normalInfoList.add(new ServiceInfo("2022-06-02", "48", "低风险", "糖尿病风险评测"));
        normalInfoList.add(new ServiceInfo("2022-06-02", "48", "低风险", "糖尿病风险评测"));
        callBack.callBack(normalInfoList);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<ServiceInfo> list) {
        return new ServiceHealthyHistoryAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {

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

    private void setTextStyle(TextView textView, boolean isSelect, Typeface typeface) {
        textView.setSelected(isSelect);
        textView.setTypeface(typeface);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_history_all:
                setTextStyle(allTextView, true, Typeface.DEFAULT_BOLD);
                setTextStyle(tangTextView, false, Typeface.DEFAULT);
                setTextStyle(mealTextView, false, Typeface.DEFAULT);
                setTextStyle(exerciseTextView, false, Typeface.DEFAULT);
                setTextStyle(weightTextView, false, Typeface.DEFAULT);
                setTextStyle(emoTextView, false, Typeface.DEFAULT);
                setTextStyle(badTextView, false, Typeface.DEFAULT);
                setTextStyle(heartTextView, false, Typeface.DEFAULT);
                break;
            case R.id.tv_service_history_tang:
                setTextStyle(allTextView, false, Typeface.DEFAULT);
                setTextStyle(tangTextView, true, Typeface.DEFAULT_BOLD);
                setTextStyle(mealTextView, false, Typeface.DEFAULT);
                setTextStyle(exerciseTextView, false, Typeface.DEFAULT);
                setTextStyle(weightTextView, false, Typeface.DEFAULT);
                setTextStyle(emoTextView, false, Typeface.DEFAULT);
                setTextStyle(badTextView, false, Typeface.DEFAULT);
                setTextStyle(heartTextView, false, Typeface.DEFAULT);
                break;
            case R.id.tv_service_history_meal:
                setTextStyle(allTextView, false, Typeface.DEFAULT);
                setTextStyle(tangTextView, false, Typeface.DEFAULT);
                setTextStyle(mealTextView, true, Typeface.DEFAULT_BOLD);
                setTextStyle(exerciseTextView, false, Typeface.DEFAULT);
                setTextStyle(weightTextView, false, Typeface.DEFAULT);
                setTextStyle(emoTextView, false, Typeface.DEFAULT);
                setTextStyle(badTextView, false, Typeface.DEFAULT);
                setTextStyle(heartTextView, false, Typeface.DEFAULT);
                break;
            case R.id.tv_service_history_exercise:
                setTextStyle(allTextView, false, Typeface.DEFAULT);
                setTextStyle(tangTextView, false, Typeface.DEFAULT);
                setTextStyle(mealTextView, false, Typeface.DEFAULT);
                setTextStyle(exerciseTextView, true, Typeface.DEFAULT_BOLD);
                setTextStyle(weightTextView, false, Typeface.DEFAULT);
                setTextStyle(emoTextView, false, Typeface.DEFAULT);
                setTextStyle(badTextView, false, Typeface.DEFAULT);
                setTextStyle(heartTextView, false, Typeface.DEFAULT);
                break;
            case R.id.tv_service_history_weight:
                setTextStyle(allTextView, false, Typeface.DEFAULT);
                setTextStyle(tangTextView, false, Typeface.DEFAULT);
                setTextStyle(mealTextView, false, Typeface.DEFAULT);
                setTextStyle(exerciseTextView, false, Typeface.DEFAULT);
                setTextStyle(weightTextView, true, Typeface.DEFAULT_BOLD);
                setTextStyle(emoTextView, false, Typeface.DEFAULT);
                setTextStyle(badTextView, false, Typeface.DEFAULT);
                setTextStyle(heartTextView, false, Typeface.DEFAULT);
                break;
            case R.id.tv_service_history_emo:
                setTextStyle(allTextView, false, Typeface.DEFAULT);
                setTextStyle(tangTextView, false, Typeface.DEFAULT);
                setTextStyle(mealTextView, false, Typeface.DEFAULT);
                setTextStyle(exerciseTextView, false, Typeface.DEFAULT);
                setTextStyle(weightTextView, false, Typeface.DEFAULT);
                setTextStyle(emoTextView, true, Typeface.DEFAULT_BOLD);
                setTextStyle(badTextView, false, Typeface.DEFAULT);
                setTextStyle(heartTextView, false, Typeface.DEFAULT);
                break;
            case R.id.tv_service_history_bad:
                setTextStyle(allTextView, false, Typeface.DEFAULT);
                setTextStyle(tangTextView, false, Typeface.DEFAULT);
                setTextStyle(mealTextView, false, Typeface.DEFAULT);
                setTextStyle(exerciseTextView, false, Typeface.DEFAULT);
                setTextStyle(weightTextView, false, Typeface.DEFAULT);
                setTextStyle(emoTextView, false, Typeface.DEFAULT);
                setTextStyle(badTextView, true, Typeface.DEFAULT_BOLD);
                setTextStyle(heartTextView, false, Typeface.DEFAULT);
                break;
            case R.id.tv_service_history_heart:
                setTextStyle(allTextView, false, Typeface.DEFAULT);
                setTextStyle(tangTextView, false, Typeface.DEFAULT);
                setTextStyle(mealTextView, false, Typeface.DEFAULT);
                setTextStyle(exerciseTextView, false, Typeface.DEFAULT);
                setTextStyle(weightTextView, false, Typeface.DEFAULT);
                setTextStyle(emoTextView, false, Typeface.DEFAULT);
                setTextStyle(badTextView, false, Typeface.DEFAULT);
                setTextStyle(heartTextView, true, Typeface.DEFAULT_BOLD);
                break;
            default:
                break;
        }
    }
}
