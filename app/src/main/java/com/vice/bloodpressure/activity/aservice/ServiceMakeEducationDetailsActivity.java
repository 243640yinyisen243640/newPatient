package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:essayId 章节id
 * 描述:教育视频详情
 */
public class ServiceMakeEducationDetailsActivity extends UIBaseLoadActivity {
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
     * 药品ID
     */
    private String essayId = "";

    private EducationInfo allInfo;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("视频详情");
        essayId = getIntent().getStringExtra("essayId");
        containerView().addView(initView());
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    /**
     * @param isCollect 1收藏/2取消收藏
     */
    private void collectOperate(String isCollect) {
        Call<String> requestCall = ServiceDataManager.mealCollectOperate(UserInfoUtils.getArchivesId(getPageContext()), essayId == null ? "" : essayId, "2", isCollect, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if (response.data) {
                //0:收藏，1：未收藏
                if ("1".equals(isCollect)) {
                    allInfo.setCollectOrNot("0");
                    collectTextView.setText("已收藏");
                    collectTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.service_video_collect, 0, 0, 0);
                } else {
                    allInfo.setCollectOrNot("1");
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
        View view = View.inflate(getPageContext(), R.layout.activity_service_make_education_details, null);
        videoPlayer = view.findViewById(R.id.jz_service_education_video_details);
        nameTextView = view.findViewById(R.id.tv_service_education_name);
        collectTextView = view.findViewById(R.id.tv_service_education_collect);
        return view;
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = ServiceDataManager.educationDetails(UserInfoUtils.getArchivesId(getPageContext()), essayId == null ? "" : essayId, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                allInfo = (EducationInfo) response.object;
                bindData();
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("educationDetails", requestCall);
    }

    private void bindData() {
        collectTextView.setOnClickListener(v -> {
            //0:收藏，1：未收藏
            collectOperate("0".equals(allInfo.getCollectOrNot()) ? "2" : "1");
        });

        nameTextView.setText(allInfo.getEssayName());
        if ("0".equals(allInfo.getCollectOrNot())) {
            collectTextView.setText("已收藏");
            collectTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.service_video_collect, 0, 0, 0);
        } else {
            collectTextView.setText("收藏");
            collectTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.service_video_uncollect, 0, 0, 0);
        }
        XyImageUtils.loadImage(getPageContext(), R.drawable.shape_defaultbackground_0, allInfo.getCoverUrl(), videoPlayer.posterImageView);
        int width = ScreenUtils.screenWidth(getPageContext());
        int height = width * 9 / 16;
        FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(width, height);
        videoPlayer.setLayoutParams(ll);
        Jzvd.SAVE_PROGRESS = false;
        videoPlayer.setUp(allInfo.getVideoUrl(), "");
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
