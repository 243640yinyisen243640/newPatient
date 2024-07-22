package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServiceOneMealDetailsAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.ToastUtils;
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

    private MealExclusiveInfo allInfo;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("视频详情");
        mealId = getIntent().getStringExtra("mealId");
        containerView().addView(initView());
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    /**
     * @param isCollect 1收藏/2取消收藏
     */
    private void collectOperate(String isCollect) {
        Call<String> requestCall = ServiceDataManager.mealCollectOperate(UserInfoUtils.getArchivesId(getPageContext()), mealId == null ? "" : mealId, "3", isCollect, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if (response.data) {
                if ("1".equals(isCollect)) {
                    allInfo.setCollect(true);
                    collectTextView.setText("已收藏");
                    collectTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.service_video_collect, 0, 0, 0);
                } else {
                    allInfo.setCollect(false);
                    collectTextView.setText("收藏");
                    collectTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.service_video_uncollect, 0, 0, 0);
                }
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("mealCollectOperate", requestCall);
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
        Call<String> requestCall = ServiceDataManager.mealDetails(UserInfoUtils.getArchivesId(getPageContext()), mealId == null ? "" : mealId, "3", (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                allInfo = (MealExclusiveInfo) response.object;
                bindData();
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("medicineLook", requestCall);
    }

    private void bindData() {
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
        XyImageUtils.loadImage(getPageContext(), R.drawable.shape_defaultbackground_0, allInfo.getPic(), videoPlayer.posterImageView);
        int width = ScreenUtils.screenWidth(getPageContext());
        int height = width * 9 / 16;
        FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(width, height);
        videoPlayer.setLayoutParams(ll);
        Jzvd.SAVE_PROGRESS = false;
        videoPlayer.setUp(allInfo.getVid(), "");

        ServiceOneMealDetailsAdapter resourceAdapter = new ServiceOneMealDetailsAdapter(getPageContext(), true, allInfo.getMaterialList());
        resourceListView.setAdapter(resourceAdapter);


        ServiceOneMealDetailsAdapter seasoningAdapter = new ServiceOneMealDetailsAdapter(getPageContext(), false, allInfo.getSeasoningList());
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
