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
import com.vice.bloodpressure.baseimp.IAdapterViewClickOneListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgTopActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.VideoInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:服务教育视频
 */
public class ServiceEducationVideoActivity extends UIBaseListRecycleViewForBgTopActivity<VideoInfo> {
    private List<VideoInfo> videoInfos = new ArrayList<>();

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
        videoInfos.add(new VideoInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "血糖高的症状及危害"));
        videoInfos.add(new VideoInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "糖尿病人能吃什么水果..."));
        videoInfos.add(new VideoInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "糖尿病怎么饮食？"));
        videoInfos.add(new VideoInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "胖是因为湿气太重吗？"));
        callBack.callBack(videoInfos);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<VideoInfo> list) {
        return new UserCollectVideoAdapter(getPageContext(), videoInfos, new IAdapterViewClickOneListener() {
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
        titleTextView.setText("教育视频");
        backImageView.setOnClickListener(v -> finish());
        searchTextView.setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), ServiceEducationVideoSearchActivity.class));
        });
        typeTextView.setOnClickListener(v -> {

        });
        return topView;
    }
}
