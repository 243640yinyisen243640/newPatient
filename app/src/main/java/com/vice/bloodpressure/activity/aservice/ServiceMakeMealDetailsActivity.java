package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietOneMealDetailsAdapter;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.model.MealIngMapInfo;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:视频详情
 */
public class ServiceMakeMealDetailsActivity extends UIBaseLoadActivity {
    private JzvdStd videoPlayer;
    /**
     * 菜名
     */
    private TextView nameTextView;
    /**
     * 去收藏
     */
    private TextView collectTextView;
    /**
     * 材料
     */
    private NoScrollListView resourceListView;
    /**
     * 调料
     */
    private NoScrollListView seasoningListView;
    /**
     * 做法
     */
    private TextView practiceTextView;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("视频详情");
        containerView().addView(initView());
        setVideoInfo();
        initValues();
        initListener();
        //        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        collectTextView.setOnClickListener(v -> {

        });
    }

    private void initValues() {
        List<MealIngMapInfo> resourcelist = new ArrayList<>();
        MealIngMapInfo mapInfo = new MealIngMapInfo();
        mapInfo.setName("猪肉");
        mapInfo.setIngK("60");
        resourcelist.add(mapInfo);
        MealIngMapInfo mapInfo1 = new MealIngMapInfo();
        mapInfo1.setName("猪肉");
        mapInfo1.setIngK("60");
        resourcelist.add(mapInfo1);
        DietOneMealDetailsAdapter resourceAdapter = new DietOneMealDetailsAdapter(getPageContext(), resourcelist);
        resourceListView.setAdapter(resourceAdapter);

        List<MealIngMapInfo> resourcelist1 = new ArrayList<>();
        MealIngMapInfo mapInfo2 = new MealIngMapInfo();
        mapInfo2.setName("猪肉");
        mapInfo2.setIngK("60");
        resourcelist1.add(mapInfo2);
        MealIngMapInfo mapInfo3 = new MealIngMapInfo();
        mapInfo3.setName("猪肉");
        mapInfo3.setIngK("60");
        resourcelist1.add(mapInfo3);
        DietOneMealDetailsAdapter seasoningAdapter = new DietOneMealDetailsAdapter(getPageContext(), resourcelist1);
        seasoningListView.setAdapter(seasoningAdapter);
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_make_meal_details, null);
        videoPlayer = view.findViewById(R.id.jz_service_meal_video_details);
        nameTextView = view.findViewById(R.id.tv_service_meal_name);
        collectTextView = view.findViewById(R.id.tv_service_meal_collect);
        resourceListView = view.findViewById(R.id.lv_service_meal_resource);
        seasoningListView = view.findViewById(R.id.lv_service_meal_seasoning);
        practiceTextView = view.findViewById(R.id.tv_service_meal_make);
        return view;
    }

    @Override
    protected void onPageLoad() {

    }


    /**
     * 视频信息
     *
     * @param
     */
    private void setVideoInfo() {
        int width = ScreenUtils.screenWidth(getPageContext());
        int height = width * 9 / 16;
        FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(width, height);
        videoPlayer.setLayoutParams(ll);
        Jzvd.SAVE_PROGRESS = true;
        videoPlayer.setUp("https://vd3.bdstatic.com/mda-mcjm50zbmckqbcwt/haokan_t/dash/1659566940889437712/mda-mcjm50zbmckqbcwt-1.mp4", "");
        // XyImageUtils.loadImage(getPageContext(), R.drawable.default_img_16_9, courseChapter.getVideoCover(), jzvdStd.posterImageView);
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}
