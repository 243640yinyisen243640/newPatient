package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.user.UserCollectVideoAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgTopActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:服务饮食视频
 */
public class ServiceMealVideoActivity extends UIBaseListRecycleViewForBgTopActivity<MealExclusiveInfo> {
    /**
     * 这个是筛选分类的ID
     */
    private String cid = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 2);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = ServiceDataManager.getMealVideoList(cid, BaseDataManager.PAGE_SIZE + "", getPageIndex() + "", "", (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getMealVideoList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<MealExclusiveInfo> list) {
        return new UserCollectVideoAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.ll_user_collect_video_click:
                        Intent intent = new Intent(getPageContext(), ServiceMakeMealDetailsActivity.class);
                        startActivity(intent);
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

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_service_education_top, null);
        ImageView backImageView = topView.findViewById(R.id.iv_service_video_back);
        TextView titleTextView = topView.findViewById(R.id.tv_service_video_title);
        TextView searchTextView = topView.findViewById(R.id.tv_service_video_search);
        TextView typeTextView = topView.findViewById(R.id.tv_service_video_type);
        titleTextView.setText("饮食视频");
        backImageView.setOnClickListener(v -> finish());
        searchTextView.setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), ServiceMealVideoSearchActivity.class));
        });
        typeTextView.setOnClickListener(v -> {
            chooseTypeWindow(typeTextView);
        });
        return topView;
    }

    /**
     * 选择运动类型
     */
    private void chooseTypeWindow(TextView typeTextView) {
        List<String> typeList = new ArrayList<>();
        typeList.add("谷类");
        typeList.add("肉类");
        typeList.add("蔬菜");
        typeList.add("饮品");
        typeList.add("其他类");
        PickerViewUtils.showChooseSinglePicker(getPageContext(), "分类", typeList, object -> {
            typeTextView.setText(typeList.get(Integer.parseInt(String.valueOf(object))));
            cid = Integer.parseInt(String.valueOf(object)) + 1 + "";
            setPageIndex(1);
            onPageLoad();
        });
    }
}
