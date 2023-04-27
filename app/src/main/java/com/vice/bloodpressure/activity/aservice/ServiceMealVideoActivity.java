package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.model.VideoInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:服务饮食视频
 */
public class ServiceMealVideoActivity extends UIBaseListRecycleViewForBgActivity<VideoInfo> {
    private List<MealExclusiveInfo> videoInfos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        Log.i("yys", "status==" + topViewManager().statusBarView().getLayoutParams().height);
        topViewManager().topView().addView(initTopView());
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 2);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void getListData(CallBack callBack) {
        videoInfos.add(new MealExclusiveInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "韭菜鸡蛋包子"));
        videoInfos.add(new MealExclusiveInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "银耳薏仁枸杞莲子荞麦..."));
        videoInfos.add(new MealExclusiveInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "西红柿炒鸡蛋"));
        videoInfos.add(new MealExclusiveInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "清炒豌豆莲子"));
        callBack.callBack(videoInfos);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<VideoInfo> list) {
        return new UserCollectVideoAdapter(getPageContext(), videoInfos, new IAdapterViewClickListener() {
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

        });
        return topView;
    }
}
