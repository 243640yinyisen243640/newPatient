package com.vice.bloodpressure.activity.ahome.adiet;

import android.graphics.Typeface;
import android.os.Bundle;
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
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.basevideo.JZVideoPlayer;
import com.vice.bloodpressure.fragment.fhome.diet.DietHeatProportionFragment;
import com.vice.bloodpressure.fragment.fhome.diet.DietMakeMealDetailsFragment;
import com.vice.bloodpressure.fragment.fhome.diet.DietResourceProportionFragment;
import com.vice.bloodpressure.model.VideoInfo;
import com.vice.bloodpressure.utils.ScreenUtils;

import java.util.ArrayList;

import cn.jzvd.Jzvd;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:制作饮食  可有视频可无视频
 */
public class DietMakeMealDetailsActivity extends UIBaseLoadActivity {
    private JZVideoPlayer videoPlayer;
    private FrameLayout videoFl;
    private TextView controlTv;
    private TextView mealNameTv;
    private RadioGroup mealVideoGg;
    private ViewPager2 mealVideoVp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制作饮食");
        containerView().addView(initView());
        initValue();
        initListener();
//        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        videoPlayer.setOnStateAutoComplete(() -> {
            long currentPositionWhenPlaying = videoPlayer.getCurrentPositionWhenPlaying();
        });

        videoPlayer.setOnClickUiToggle(() -> {

        });

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

    }


    private void initValue() {

        ArrayList<Fragment> fragments = new ArrayList<>();

        /**
         * 原料做法
         */
        DietMakeMealDetailsFragment detailsFragment = new DietMakeMealDetailsFragment();
        DietResourceProportionFragment resourceProportionFragment = new DietResourceProportionFragment();
        DietHeatProportionFragment heatProportionFragment = new DietHeatProportionFragment();

        fragments = new ArrayList<>();
        fragments.add(detailsFragment.getInstance("11"));
        fragments.add(resourceProportionFragment.getInstance("11"));
        fragments.add(heatProportionFragment.getInstance("11"));

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
     * @param videoInfo
     */
    private void setVideoInfo(VideoInfo videoInfo) {
        int width = ScreenUtils.screenWidth(getPageContext());
        int height = width * 9 / 16;
        FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(width, height);
        videoPlayer.setLayoutParams(ll);
        Jzvd.SAVE_PROGRESS = true;
        //        videoPlayer.setUp(videoInfo.getVideoUrl(), "");
        //        XyImageUtils.loadImage(getPageContext(), R.drawable.default_img_16_9, courseChapter.getVideoCover(), jzvdStd.posterImageView);

        //        if ("1".equals(videoInfo.getIsFirst())) {
        //            videoPlayer.setCanForward(true);
        //            videoPlayer.progressBar.setOnTouchListener(new View.OnTouchListener() {
        //                @Override
        //                public boolean onTouch(View view, MotionEvent motionEvent) {
        //
        //                    return false;
        //                }
        //            });
        //        } else {
        //            videoPlayer.setCanForward(false);
        //            videoPlayer.progressBar.setOnTouchListener(new View.OnTouchListener() {
        //                @Override
        //                public boolean onTouch(View view, MotionEvent motionEvent) {
        //
        //                    return true;
        //                }
        //            });
        //
        //        }
    }
}
