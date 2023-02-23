package com.vice.bloodpressure.fragment.fhome;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.ahome.HomeMessageListActivity;
import com.vice.bloodpressure.activity.ahome.HomeWarningListActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietChangeDietActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietMealDetailsActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietMealPlanDetailsActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietProgrammeChooseActivity;
import com.vice.bloodpressure.activity.ahome.aexercise.ExercisePlanOneActivity;
import com.vice.bloodpressure.adapter.home.HomeHealthyTipAdapter;
import com.vice.bloodpressure.adapter.home.HomeMealListAdapter;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseui.UIBaseLoadRefreshFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.MealInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述: 首页
 */
public class MainHomeFragment extends UIBaseLoadRefreshFragment implements View.OnClickListener {
    //头部
    /**
     * 消息，扫一扫，异常提醒
     */
    private ImageView messageIm, scanIm, abnormalIm;


    //健康贴士
    /**
     * 健康贴士的列表
     */
    private RecyclerView healthyTipRv;
    /**
     * 健康贴士的手气展开
     */
    private ImageView spreadIm;


    /**
     * 人体中 血糖，血压，心率数据
     */
    private TextView xyValueTv, xtValueTv, xlValueTv;
    /**
     * 血管，视网膜，神经肾脏，糖尿病，症状名称
     */
    private TextView xgIllnessTv, swmIllnessTv, sjIllnessTv, szIllnessTv, tzIllnessTv;
    /**
     * 血管，视网膜，神经肾脏，糖尿病，图片
     */
    private ImageView xgIllnessIm, xgIllnesslineIm, swmIllnessIm, sjIllnessIm, szIllnessIm, tzIllnessIm;
    /**
     * 身高，体重，bmi的数量   bmi 的状态
     */
    private TextView heightNumTv, weightNumTv, bmiNumTv, bmiStateTv;
    //运动
    /**
     * 血糖，血压，bmi 的折线图
     */
    private ViewPager2 threeVp;

    /**
     * 血糖，血压，bmi 选中状态
     */
    private RadioGroup threeRg;

    //饮食
    /**
     * 重新制定计划，热量，刷新饮食，自定义饮食，饮食计划，进入详情
     */
    private TextView mealAgainTv, mealfireTv, refreshTv, yourselfTv, mealPlanTv;
    /**
     * 食物列表，
     */
    private RecyclerView recyclRv;

    private LinearLayout mealMoreLin;
    //运动
    /**
     * 制定计划，步数的数据，还需消耗多少热量，消耗了多少热量，立即完成，运动类型，需要消耗热量，运动时间，抗阻运动，柔韧性运动
     */
    private TextView makePlanTv, stepNumTv, exerciseNumTv, fireNumTv, finishTv, typeTv, needFireTv, timeTv, resistanceTv, flexibilityTv;

    //教育

    /**
     * 教育与你相关  重新制定，标题，小内容，内容
     */
    private TextView aboutYouTv, makeAgainTv, titleTv, containTv, contentTv, educationNumTv;
    /**
     * 文章封面，
     */

    private ImageView articleBgIm;

    public static MainHomeFragment getInstance() {

        MainHomeFragment mainFragment = new MainHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        topViewManager().topView().removeAllViews();
        topViewManager().statusBarView().setBackgroundColor(Color.parseColor("#00C27F"));
        initTopView();
        initView();
        initListener();
        initValues();

    }

    @Override
    protected void onPageLoad() {

    }

    private void initValues() {
        initHealthy();
        initThree();
        initMeal();
        initExercise();
        initEducation();
    }


    /**
     * 给血糖血压bmi三个轮播赋值
     */
    private void initThree() {
        //血压
        setTextType(xyValueTv, Color.parseColor("#2A2A2A"), Color.parseColor("#ED4646"), "血压值\n", "130/80 ", "mmHg\n", "2022-02-05 15:20");
        //血糖
        setTextType(xtValueTv, Color.parseColor("#2A2A2A"), Color.parseColor("#ED4646"), "血糖值\n", "4.6 ", "mol/L\n", "2022-02-05 15:20");
        //心率
        setTextType(xlValueTv, Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), "心率\n", "60 ", "次/分\n", "2022-02-05 15:20");


    }


    /**
     * 给小人赋值
     */
    private void initHealthy() {
        GridLayoutManager layoutManager1 = new GridLayoutManager(getPageContext(), 1);
        healthyTipRv.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), false));
        healthyTipRv.setLayoutManager(layoutManager1);
        List<MealInfo> list1 = new ArrayList<>();
        list1.add(new MealInfo("血糖目标：空腹血糖目标：4.7—7.2 mmol/L； 非空腹血糖目标：4.7—10.0 mmol/L 。"));
        list1.add(new MealInfo("血糖目标：空腹血糖目标：4.7—7.2 mmol/L； 非空腹血糖目标：4.7—10.0 mmol/L 。"));
        list1.add(new MealInfo("血糖目标：空腹血糖目标：4.7—7.2 mmol/L； 非空腹血糖目标：4.7—10.0 mmol/L 。"));
        HomeHealthyTipAdapter healthyTipAdapter = new HomeHealthyTipAdapter(getPageContext(), list1);
        healthyTipRv.setAdapter(healthyTipAdapter);

        setTextTypeHeight(heightNumTv, Color.parseColor("#2A2A2A"), Color.parseColor("#2A2A2A"), "身高", "  cm\n", "178");
        setTextTypeHeight(weightNumTv, Color.parseColor("#2A2A2A"), Color.parseColor("#2A2A2A"), "体重", "  Kg\n", "70");

    }

    /**
     * 给饮食赋值
     */
    private void initMeal() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getPageContext());
        layoutManager.setOrientation(recyclRv.HORIZONTAL);
        recyclRv.setLayoutManager(layoutManager);
        List<MealInfo> list = new ArrayList<>();
        list.add(new MealInfo("https://seopic.699pic.com/photo/50236/0275.jpg_wh1200.jpg", "这个是标题"));
        list.add(new MealInfo("https://seopic.699pic.com/photo/50236/0275.jpg_wh1200.jpg", "这个是标题"));
        list.add(new MealInfo("https://seopic.699pic.com/photo/50236/0275.jpg_wh1200.jpg", "这个是标题"));
        HomeMealListAdapter adapter = new HomeMealListAdapter(getPageContext(), list);
        recyclRv.setAdapter(adapter);
        mealfireTv.setText(setMealTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), 18, "今日需要摄入总热量", " 1700 ", "千卡"));
    }

    /**
     * 给运动赋值
     */
    private void initExercise() {
        exerciseNumTv.setText(setMealTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), 18, "今日需要消耗热量", " 1700 ", "千卡"));
        stepNumTv.setText(setMealTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), 15, "步行", " 2000 ", "步"));
        fireNumTv.setText(setMealTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#F98515"), 15, "消耗热量 ", " 90 ", "千卡"));

    }

    /**
     * 给教育赋值
     */
    private void initEducation() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("本节共");
        int length = builder.length();
        builder.append("字数");
        int length1 = builder.length();
        builder.append("字，阅读时间约");
        int length2 = builder.length();
        builder.append("分钟数");
        int length3 = builder.length();
        builder.append("分钟");
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#00C27F")), length, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#00C27F")), length2, length3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        educationNumTv.setText(builder);
    }

    /**
     * 设置字体的大小颜色
     *
     * @param textView   展示的控件
     * @param startColor 开始的颜色
     * @param endColor   结束的颜色
     * @param text1      第一段字体
     * @param text2      第二段字体
     * @param text3      第三段字体
     * @param text4      第四段字体
     */
    private void setTextType(TextView textView, int startColor, int endColor, String text1, String text2, String text3, String text4) {

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text1);
        int length1 = builder.length();
        builder.append(text2);
        int length2 = builder.length();
        builder.append(text3);
        int length3 = builder.length();
        builder.append(text4);
        builder.setSpan(new ForegroundColorSpan(startColor), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(getPageContext(), 16)), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan(endColor), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(getPageContext(), 15)), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0, length1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), length1, length2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

    /**
     * 身高体重的样式
     *
     * @param textView
     * @param startColor
     * @param endColor
     * @param text1
     * @param text2
     * @param text3
     */
    private void setTextTypeHeight(TextView textView, int startColor, int endColor, String text1, String text2, String text3) {

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text1);
        int length1 = builder.length();
        builder.append(text2);
        int length2 = builder.length();
        builder.append(text3);
        int length3 = builder.length();
        builder.setSpan(new ForegroundColorSpan(startColor), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(getPageContext(), 15)), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan(endColor), length2, length3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(getPageContext(), 22)), length2, length3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0, length1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), length2, length3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

    /**
     * 设置饮食的颜色
     *
     * @param
     * @param startColor
     * @param endColor
     * @param text1
     * @param text2
     * @param text3
     */
    private SpannableStringBuilder setMealTextType(int startColor, int endColor, float textSize, String text1, String text2, String text3) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text1);
        int length1 = builder.length();
        builder.append(text2);
        int length2 = builder.length();
        builder.append(text3);
        builder.setSpan(new ForegroundColorSpan(startColor), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(getPageContext(), textSize)), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan(endColor), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), length1, length2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    private void initListener() {
        messageIm.setOnClickListener(this);
        scanIm.setOnClickListener(this);
        abnormalIm.setOnClickListener(this);

        spreadIm.setOnClickListener(this);

        makePlanTv.setOnClickListener(this);
        finishTv.setOnClickListener(this);
        resistanceTv.setOnClickListener(this);
        flexibilityTv.setOnClickListener(this);
        mealAgainTv.setOnClickListener(this);
        refreshTv.setOnClickListener(this);
        yourselfTv.setOnClickListener(this);
        mealPlanTv.setOnClickListener(this);
        mealMoreLin.setOnClickListener(this);

        aboutYouTv.setOnClickListener(this);
        makeAgainTv.setOnClickListener(this);
        ArrayList<Fragment> fragments = new ArrayList<>();

        HomeXueTangFragment detailsFragment = new HomeXueTangFragment();
        HomeXueYaFragment resourceProportionFragment = new HomeXueYaFragment();
        HomeBmiFragment bmiFragment = new HomeBmiFragment();

        fragments = new ArrayList<>();
        fragments.add(detailsFragment.getInstance("11"));
        fragments.add(resourceProportionFragment.getInstance("11"));
        fragments.add(bmiFragment.getInstance("11"));

        threeVp.setAdapter(new MyFragmentStateAdapter(getActivity(), fragments));
        threeVp.setOffscreenPageLimit(fragments.size());
        threeRg.check(threeRg.getChildAt(0).getId());
        threeVp.setCurrentItem(0);

        threeRg.setOnCheckedChangeListener((group, checkedId) -> {
            int index = threeRg.indexOfChild(threeRg.findViewById(checkedId));
            threeVp.setCurrentItem(index);
        });

        threeVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                threeRg.check(threeRg.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //系统消息
            case R.id.iv_home_message:
                intent = new Intent(getPageContext(), HomeMessageListActivity.class);
                startActivity(intent);
                break;
            //扫一扫
            case R.id.iv_home_data_scan:
                break;
            //异常提醒
            case R.id.iv_home_data_abnormal:
                intent = new Intent(getPageContext(), HomeWarningListActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_home_healthy_tips_spread:
                break;
            //重新制定运动计划
            case R.id.tv_exercise_make_plan:

                intent = new Intent(getPageContext(), ExercisePlanOneActivity.class);
                startActivity(intent);
                //                intent = new Intent(getPageContext(), ExerciseIntelligenceActivity.class);
                //                startActivity(intent);
                break;
            //立即完成 运动
            case R.id.tv_exercise_finish:
                break;
            //抗阻运动
            case R.id.tv_exercise_resistance:
                break;
            //柔韧性运动
            case R.id.tv_exercise_flexibility:
                break;
            //重新制定 饮食
            case R.id.tv_meal_make_again:
                intent = new Intent(getPageContext(), DietChangeDietActivity.class);
                startActivity(intent);
                break;
            //三餐刷新
            case R.id.tv_meal_refresh:
                intent = new Intent(getPageContext(), DietMealDetailsActivity.class);
                startActivity(intent);
                break;
            //自定义饮食
            case R.id.tv_meal_make_yourself:
                intent = new Intent(getPageContext(), DietProgrammeChooseActivity.class);
                startActivity(intent);
                break;
            //饮食方案
            case R.id.tv_meal_plan:
                intent = new Intent(getPageContext(), DietMealPlanDetailsActivity.class);
                startActivity(intent);
                break;
            //三参列表点击进入详情
            case R.id.ll_meal_more:
                break;
            //教育关于你
            case R.id.tv_education_about_you:
                break;
            //教育重新制定
            case R.id.tv_education_make_again:
                break;
            default:
                break;
        }
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_main_home_first, null);
        healthyTipRv = view.findViewById(R.id.cv_home_healthy_tip);
        spreadIm = view.findViewById(R.id.tv_home_healthy_tips_spread);
        xyValueTv = view.findViewById(R.id.tv_home_xy_value);
        xtValueTv = view.findViewById(R.id.tv_home_xt_value);
        xlValueTv = view.findViewById(R.id.tv_home_xl_value);

        xgIllnessTv = view.findViewById(R.id.tv_home_xg_illness);
        xgIllnessIm = view.findViewById(R.id.iv_home_xg_img);
        xgIllnesslineIm = view.findViewById(R.id.iv_home_xg_img_line);

        swmIllnessTv = view.findViewById(R.id.tv_home_swm_illness);
        swmIllnessIm = view.findViewById(R.id.iv_home_swm_img);

        sjIllnessTv = view.findViewById(R.id.tv_home_sj_illness);
        sjIllnessIm = view.findViewById(R.id.iv_home_sj_img);

        szIllnessTv = view.findViewById(R.id.tv_home_sz_illness);
        szIllnessIm = view.findViewById(R.id.iv_home_sz_img);

        tzIllnessTv = view.findViewById(R.id.tv_home_tz_illness);
        tzIllnessIm = view.findViewById(R.id.iv_home_tz_img);

        heightNumTv = view.findViewById(R.id.tv_home_height_num);
        weightNumTv = view.findViewById(R.id.tv_home_weight_num);
        bmiNumTv = view.findViewById(R.id.tv_home_bmi_num);
        bmiStateTv = view.findViewById(R.id.tv_home_bmi_state);

        threeVp = view.findViewById(R.id.vp_home_three_fragment);
        threeRg = view.findViewById(R.id.rg_home_xt_xy_bmi);


        mealAgainTv = view.findViewById(R.id.tv_meal_make_again);
        mealfireTv = view.findViewById(R.id.tv_meal_need_fire);
        refreshTv = view.findViewById(R.id.tv_meal_refresh);
        yourselfTv = view.findViewById(R.id.tv_meal_make_yourself);
        mealPlanTv = view.findViewById(R.id.tv_meal_plan);
        recyclRv = view.findViewById(R.id.rv_meal_recycl);
        mealMoreLin = view.findViewById(R.id.ll_meal_more);


        makePlanTv = view.findViewById(R.id.tv_exercise_make_plan);
        exerciseNumTv = view.findViewById(R.id.tv_home_exercise_need_fire);
        stepNumTv = view.findViewById(R.id.tv_exercise_step_num);
        fireNumTv = view.findViewById(R.id.tv_exercise_fire_num);
        finishTv = view.findViewById(R.id.tv_exercise_finish);
        typeTv = view.findViewById(R.id.tv_exercise_type);
        needFireTv = view.findViewById(R.id.tv_main_exercise_need_fire);
        timeTv = view.findViewById(R.id.tv_exercise_time);
        resistanceTv = view.findViewById(R.id.tv_exercise_resistance);
        flexibilityTv = view.findViewById(R.id.tv_exercise_flexibility);


        aboutYouTv = view.findViewById(R.id.tv_education_about_you);
        makeAgainTv = view.findViewById(R.id.tv_education_make_again);
        articleBgIm = view.findViewById(R.id.iv_education_article_bg);
        titleTv = view.findViewById(R.id.tv_education_article_title);
        containTv = view.findViewById(R.id.tv_education_article_contain);
        contentTv = view.findViewById(R.id.tv_education_article_content);
        educationNumTv = view.findViewById(R.id.tv_education_article_text_num);

        containerView().addView(view);
    }

    private void initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_main_home_topview, null);
        messageIm = topView.findViewById(R.id.iv_home_message);
        scanIm = topView.findViewById(R.id.iv_home_data_scan);
        abnormalIm = topView.findViewById(R.id.iv_home_data_abnormal);
        topViewManager().topView().addView(topView);
    }
}
