package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServiceHealthyTestAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.AdvertInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:健康评测
 */
public class ServiceHealthyTestListActivity extends UIBaseListRecycleViewActivity<AdvertInfo> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("健康评测");
        topViewManager().moreTextView().setText("历史评测");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), ServiceHealthyHistoryListActivity.class));
        });
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }


    @Override
    protected void getListData(CallBack callBack) {
        List<AdvertInfo> normalInfoList = new ArrayList<>();
        normalInfoList.add(new AdvertInfo(R.drawable.service_healthy_test_tang, "糖尿病风险评测"));
        normalInfoList.add(new AdvertInfo(R.drawable.service_healthy_test_meal, "饮食测评"));
        normalInfoList.add(new AdvertInfo(R.drawable.service_healthy_test_exercise, "运动测评"));
        normalInfoList.add(new AdvertInfo(R.drawable.service_healthy_test_weight, "体重测评"));
        normalInfoList.add(new AdvertInfo(R.drawable.service_healthy_test_emotion, "抑郁测评"));
        normalInfoList.add(new AdvertInfo(R.drawable.service_healthy_test_ugly, "焦虑测评"));
        normalInfoList.add(new AdvertInfo(R.drawable.service_healthy_test_heart, "国人缺血性心血管病(ICVD) 10年发病风险评测"));
        callBack.callBack(normalInfoList);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<AdvertInfo> list) {
        return new ServiceHealthyTestAdapter(getPageContext(), list, object -> {
            switch (Integer.parseInt(String.valueOf(object))) {
                case 0:

                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }

    @Override
    protected boolean isLoadMore() {
        return false;
    }

    @Override
    protected boolean isRefresh() {
        return false;
    }
}
