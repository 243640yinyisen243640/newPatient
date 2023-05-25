package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietOneMealDetailsAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;
import com.vice.bloodpressure.view.NoScrollListView;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:mealId 饮食id
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
    /**
     * 药品ID
     */
    private String mealId = "";

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("视频详情");
        mealId = getIntent().getStringExtra("mealId");
        containerView().addView(initView());
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }


    private void collectOperate(String isCollect) {
        Call<String> requestCall = ServiceDataManager.mealCollectOperate(UserInfoUtils.getArchivesId(getPageContext()), mealId == null ? "" : mealId, "1", isCollect, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                MealExclusiveInfo allInfo = (MealExclusiveInfo) response.object;
                bindData(allInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("medicineLook", requestCall);
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
        Call<String> requestCall = ServiceDataManager.mealDetails(UserInfoUtils.getArchivesId(getPageContext()), mealId == null ? "" : mealId, "1", (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                MealExclusiveInfo allInfo = (MealExclusiveInfo) response.object;
                bindData(allInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("medicineLook", requestCall);
    }

    private void bindData(MealExclusiveInfo allInfo) {
        collectTextView.setOnClickListener(v -> {
            collectOperate(allInfo.isCollect() ? "2" : "1");
        });

        nameTextView.setText(allInfo.getDishName());
        practiceTextView.setText(allInfo.getPractice());
        if (allInfo.isCollect()) {
            collectTextView.setText("已收藏");
            collectTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.service_video_collect, 0, 0, 0);
        } else {
            collectTextView.setText("收藏");
            collectTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.service_video_uncollect, 0, 0, 0);
        }
        XyImageUtils.loadImage(getPageContext(), R.drawable.shape_defaultbackground_5, allInfo.getPic(), videoPlayer.posterImageView);
        int width = ScreenUtils.screenWidth(getPageContext());
        int height = width * 9 / 16;
        FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(width, height);
        videoPlayer.setLayoutParams(ll);
        Jzvd.SAVE_PROGRESS = true;
        videoPlayer.setUp(allInfo.getVid(), "");

        DietOneMealDetailsAdapter resourceAdapter = new DietOneMealDetailsAdapter(getPageContext(), allInfo.getMaterialList());
        resourceListView.setAdapter(resourceAdapter);


        DietOneMealDetailsAdapter seasoningAdapter = new DietOneMealDetailsAdapter(getPageContext(), allInfo.getSeasoningList());
        seasoningListView.setAdapter(seasoningAdapter);
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
