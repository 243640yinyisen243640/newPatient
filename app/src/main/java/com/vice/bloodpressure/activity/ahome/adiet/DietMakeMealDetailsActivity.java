package com.vice.bloodpressure.activity.ahome.adiet;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.fragment.fhome.diet.DietHeatProportionFragment;
import com.vice.bloodpressure.fragment.fhome.diet.DietMakeMealDetailsFragment;
import com.vice.bloodpressure.fragment.fhome.diet.DietResourceProportionFragment;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.ArrayList;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:制作饮食  可有视频可无视频
 */
public class DietMakeMealDetailsActivity extends UIBaseLoadActivity {
    private JzvdStd videoPlayer;
    private FrameLayout videoFl;
    private TextView controlTv;
    private TextView mealNameTv;
    private RadioGroup mealVideoGg;
    private ViewPager2 mealVideoVp;

    private String recHeat;
    private String recId;

    private MealExclusiveInfo mealExclusiveInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制作饮食");
        recHeat = getIntent().getStringExtra("recHeat");
        recId = getIntent().getStringExtra("recId");
        containerView().addView(initView());
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }


    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_make_meal_details, null);
        videoPlayer = view.findViewById(R.id.jz_video_details);
        videoFl = view.findViewById(R.id.fl_video_details);
        controlTv = view.findViewById(R.id.tv_meal_begin_pause);
        mealNameTv = view.findViewById(R.id.tv_meal_name);
        mealVideoGg = view.findViewById(R.id.rg_meal_video);
        mealVideoVp = view.findViewById(R.id.vp_meal_video);
        return view;
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = HomeDataManager.dietDetailsByRec(recId, recHeat, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                mealExclusiveInfo = (MealExclusiveInfo) response.object;
                bindData();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("dietDetailsByRec", requestCall);
    }

    private void bindData() {
        if (!TextUtils.isEmpty(mealExclusiveInfo.getCoverUrl())) {
            videoFl.setVisibility(View.VISIBLE);
            setVideoInfo();
        } else {
            videoFl.setVisibility(View.GONE);
        }
        mealNameTv.setText(mealExclusiveInfo.getRecName());
        initValue();
    }


    private void initValue() {
        ArrayList<Fragment> fragments;

        /**
         * 原料做法
         */
        DietMakeMealDetailsFragment detailsFragment = new DietMakeMealDetailsFragment();
        DietResourceProportionFragment resourceProportionFragment = new DietResourceProportionFragment();
        DietHeatProportionFragment heatProportionFragment = new DietHeatProportionFragment();

        fragments = new ArrayList<>();
        fragments.add(detailsFragment.getInstance(mealExclusiveInfo));
        fragments.add(resourceProportionFragment.getInstance(mealExclusiveInfo));
        fragments.add(heatProportionFragment.getInstance(mealExclusiveInfo));

        mealVideoVp.setAdapter(new MyFragmentStateAdapter(this, fragments));
        mealVideoVp.setOffscreenPageLimit(fragments.size());
        mealVideoGg.check(mealVideoGg.getChildAt(0).getId());
        mealVideoVp.setCurrentItem(0);

        mealVideoGg.setOnCheckedChangeListener((group, checkedId) -> {
            int index = mealVideoGg.indexOfChild(mealVideoGg.findViewById(checkedId));
            mealVideoVp.setCurrentItem(index);
        });

        mealVideoVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mealVideoGg.check(mealVideoGg.getChildAt(i).getId());
                for (int j = 0; j < mealVideoGg.getChildCount(); j++) {
                    if (j == i) {
                        ((RadioButton) mealVideoGg.getChildAt(j)).setTypeface(Typeface.DEFAULT_BOLD);
                    } else {
                        ((RadioButton) mealVideoGg.getChildAt(j)).setTypeface(Typeface.DEFAULT);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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
        Jzvd.SAVE_PROGRESS = false;
        videoPlayer.setUp(mealExclusiveInfo.getVideoUrl(), "");
        XyImageUtils.loadImage(getPageContext(), R.drawable.default_img_1_1, mealExclusiveInfo.getCoverUrl(), videoPlayer.posterImageView);
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
