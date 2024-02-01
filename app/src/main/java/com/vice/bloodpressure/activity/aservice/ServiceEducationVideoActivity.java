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
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgTopActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:服务教育视频
 */
public class ServiceEducationVideoActivity extends UIBaseListRecycleViewForBgTopActivity<EducationInfo> {

    private String cid = "";

    private TextView typeTextView;

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
        Call<String> requestCall = ServiceDataManager.getEduVideoPage("", getPageIndex() + "", BaseDataManager.PAGE_SIZE + "", cid, (call, response) -> {
            if ("0000".equals(response.code)) {
                EducationInfo educationInfo = (EducationInfo) response.object;
                callBack.callBack(educationInfo.getRecords());
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getEduVideoPage", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<EducationInfo> list) {
        return new UserCollectVideoAdapter(getPageContext(), list, (position, view) -> {
            switch (view.getId()) {
                case R.id.ll_user_collect_video_click:
                    Intent intent = new Intent(getPageContext(), ServiceMakeEducationDetailsActivity.class);
                    intent.putExtra("essayId",list.get(position).getEssayId());
                    startActivity(intent);
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

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_service_education_top, null);
        ImageView backImageView = topView.findViewById(R.id.iv_service_video_back);
        TextView titleTextView = topView.findViewById(R.id.tv_service_video_title);
        TextView searchTextView = topView.findViewById(R.id.tv_service_video_search);
         typeTextView = topView.findViewById(R.id.tv_service_video_type);
        titleTextView.setText("教育视频");
        backImageView.setOnClickListener(v -> finish());
        searchTextView.setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), ServiceEducationVideoSearchActivity.class));
        });
        typeTextView.setOnClickListener(v -> {
            chooseTypeWindow();
        });
        return topView;
    }

    /**
     * 选择教育视频类型
     */
    private void chooseTypeWindow() {
        List<String> typeList = new ArrayList<>();
        typeList.add("1型");
        typeList.add("2型");
        typeList.add("妊娠");
        typeList.add("其他");
        typeList.add("高血压");
        PickerViewUtils.showChooseSinglePicker(getPageContext(), "分类", typeList, object -> {
            cid = Integer.parseInt(String.valueOf(object)) + 1 + "";
            typeTextView.setText(typeList.get(Integer.parseInt(String.valueOf(object))));
            setPageIndex(1);
            onPageLoad();
        });
    }
}
