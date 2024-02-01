package com.vice.bloodpressure.fragment.fhome;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.lyd.libsteps.StepUtil;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.ahome.HomeMessageListActivity;
import com.vice.bloodpressure.activity.ahome.HomeWarningListActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietMealDetailsActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietMealPlanDetailsActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietProgrammeBeginActivity;
import com.vice.bloodpressure.activity.ahome.adiet.DietProgrammeChooseActivity;
import com.vice.bloodpressure.activity.ahome.aeducation.EducationIntelligenceActivity;
import com.vice.bloodpressure.activity.ahome.aeducation.EducationQuestionInvestigateBeginActivity;
import com.vice.bloodpressure.activity.ahome.aexercise.ExerciseCountdownActivity;
import com.vice.bloodpressure.activity.ahome.aexercise.ExerciseIntelligenceActivity;
import com.vice.bloodpressure.activity.ahome.aexercise.ExercisePlanOneActivity;
import com.vice.bloodpressure.activity.aservice.ServiceBloodListActivity;
import com.vice.bloodpressure.activity.aservice.ServiceMedicineListActivity;
import com.vice.bloodpressure.activity.aservice.ServicePressureListActivity;
import com.vice.bloodpressure.adapter.home.HomeHealthyTipsAdapter;
import com.vice.bloodpressure.adapter.home.HomeMealListAdapter;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseLoadRefreshFragment;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.model.HomeAllInfo;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.model.MessageInfo;
import com.vice.bloodpressure.modules.zxing.activity.CaptureActivity;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述: 首页
 */
public class MainHomeFragment extends UIBaseLoadRefreshFragment implements View.OnClickListener {
    //更多切换
    private final List<MessageInfo> messageInfos = new ArrayList<>();
    //总列表
    private List<MessageInfo> messageInfoList;

    //头部
    /**
     * 消息，扫一扫，异常提醒
     */
    private ImageView messageIm, scanIm, abnormalIm;


    //健康贴士
    /**
     * 健康贴士的列表
     */
    private NoScrollListView healthyTipRv;
    private LinearLayout healthyTipLl;
    /**
     * 健康贴士的手气展开
     */
    private ImageView spreadIm;
    /**
     * 人体
     */
    private ImageView shapeIm;
    /**
     * 人体中 血糖，血压，心率数据  没有这些病种的话整体隐藏
     */
    private LinearLayout xyLinearLayout, xtLinearLayout, xlLinearLayout;
    /**
     * 人体中 血糖，血压，心率数据 展示的值
     */
    private TextView xyValueTv, xtValueTv, xlValueTv;
    /**
     * 人体中 血糖，血压，心率数据 展示的时间
     */
    private TextView xyTimeTv, xtTimeTv, xlTimeTv;
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
    private TextView heightNumTv, weightNumTv, bmiNumTv, bmiStateTv, bmiNormalTv;
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
     * 没有答题时去开启答题，重新制定计划，热量，刷新饮食，自定义饮食，饮食计划，进入详情
     */
    private TextView mealOpenTv, mealAgainTv, mealfireTv, refreshTv, yourselfTv, mealPlanTv;
    /**
     * 食物列表，
     */
    private RecyclerView recyclRv;
    /**
     * 没有答题的布局
     */
    private LinearLayout mealNoOpenLin, mealHaveOpenLin, mealMoreLin;
    //运动
    /**
     * 没有答题时的开启，制定计划，重新制定运动计划，步数的数据，还需消耗多少热量，消耗了多少热量，立即完成，运动类型，需要消耗热量，运动时间，抗阻运动，柔韧性运动
     */
    private TextView exerciseOpenTv, makePlanTv, makeExerciseAgainPlanTv, stepNumTv, exerciseNumTv, fireNumTv, finishTv, exerciseTypeTv, needFireTv, timeTv, resistanceTv, flexibilityTv;

    private LinearLayout exerciseNoLinearLayout, exerciseHaveLinearLayout;
    //教育

    /**
     * 教育没有答题时去开启，教育与你相关  重新制定，标题，小内容，内容
     */
    private TextView educationOpenTv, aboutYouTv, makeAgainTv, titleTv, containTv, contentTv, educationNumTv;
    /**
     * 文章封面，
     */
    private ImageView articleBgIm;
    /**
     *
     */
    private LinearLayout educationHaveLinearLayout, educationNoLinearLayout;


    private HomeHealthyTipsAdapter healthyTipAdapter;

    private HomeAllInfo allInfo;


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
        //        topViewManager().statusBarView().setBackgroundColor(Color.parseColor("#00C27F"));
        //        StatusBarUtils.statusBarColor(getActivity(),R.color.defaultToastBg);
        initTopView();
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        requestMotionPermission();
    }

    private String[] perms = {Manifest.permission.ACTIVITY_RECOGNITION};

    //请求运动权限
    private void requestMotionPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (checkPermission(perms)) {
                //获取首页运动
                upSteps();
            } else {
                requestPermission("获取健身运动信息", perms);
            }
        } else {
            //获取首页运动
            upSteps();
        }
    }

    @Override
    protected void permissionsGranted() {
        //获取首页运动  权限请求成功
        upSteps();
    }

    @Override
    protected void permissionsDenied(List<String> perms) {
        //权限请求失败
        getHomeData();
    }

    @Override
    protected void onPageLoad() {
        getHomeData();
    }

    private void getHomeData() {
        Call<String> requestCall = HomeDataManager.getHomeData(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if (refreshLayout().isRefreshing()) {
                refreshLayout().finishRefresh();
            }
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                allInfo = (HomeAllInfo) response.object;
                setData();
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }

        }, (call, t) -> {
            if (refreshLayout().isRefreshing()) {
                refreshLayout().finishRefresh();
            }
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getHomeData", requestCall);
    }


    private void upSteps() {
        int todayStep = StepUtil.getTodayStep(getPageContext());
        Call<String> requestCall = HomeDataManager.saveSportWalk(UserInfoUtils.getArchivesId(getPageContext()), todayStep + "", (call, response) -> {
            if (response.data) {
                getHomeData();
            }

        }, (call, t) -> {
        });
        addRequestCallToMap("saveSportWalk", requestCall);
    }

    private void setData() {
        initHealthy();
        setBaseInfo();
        initViewPager();
        initMeal();
        initExercise();
        initEducation();
    }


    /**
     * 给赋值
     */
    private void initHealthy() {
        //血糖目标：1
        //血压目标：2
        //用药提醒：3
        //运动目标：4
        if ("1".equals(allInfo.getHealthyStatus())) {
            healthyTipLl.setVisibility(View.VISIBLE);
            messageInfoList = allInfo.getHealthyTag();
            if (messageInfoList.size() <= 3) {
                spreadIm.setVisibility(View.GONE);
                messageInfos.clear();
                messageInfos.addAll(messageInfoList);
            } else {
                spreadIm.setVisibility(View.VISIBLE);
                messageInfos.clear();
                for (int i = 0; i < 3; i++) {
                    messageInfos.add(messageInfoList.get(i));
                }
            }
            healthyTipAdapter = new HomeHealthyTipsAdapter(getPageContext(), messageInfos, (position, view) -> {
                Intent intent;
                switch (messageInfos.get(position).getTagType()) {
                    //1:血糖目标 2:血压目标： 3:用药提醒 4:运动目标
                    case "1":
                        intent = new Intent(getPageContext(), ServiceBloodListActivity.class);
                        startActivity(intent);
                        break;
                    case "2":
                        startActivity(new Intent(getPageContext(), ServicePressureListActivity.class));
                        break;
                    case "3":
                        startActivity(new Intent(getPageContext(), ServiceMedicineListActivity.class));   
                        break;
                    case "4":
                        intent = new Intent(getPageContext(), ExerciseIntelligenceActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            });
            healthyTipRv.setAdapter(healthyTipAdapter);

        } else {
            healthyTipLl.setVisibility(View.GONE);
        }
    }

    //展示更多
    public void notifyWithLimitItemNumb() {
        spreadIm.setVisibility(View.GONE);
        messageInfos.clear();
        messageInfos.addAll(messageInfoList);
        healthyTipAdapter.notifyDataSetChanged();
    }

    private void setBaseInfo() {
        //bmi  1 偏瘦 2 正常 3 超重 4 肥胖
        String bmiStatus = allInfo.getBasicInfo().getBmiStatus();
        bmiStateTv.setVisibility(View.VISIBLE);
        if ("1".equals(bmiStatus)) {
            shapeIm.setImageDrawable(ContextCompat.getDrawable(getPageContext(), R.drawable.home_thin_all_person));
            bmiNumTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.blue_2a));
            bmiStateTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.blue_2a));
            //            bmiStateTv.setText("偏瘦");
            bmiStateTv.setBackground(ContextCompat.getDrawable(getPageContext(), R.drawable.shape_bg_white_blue_31_17_2));
        } else if ("2".equals(bmiStatus)) {
            shapeIm.setImageDrawable(ContextCompat.getDrawable(getPageContext(), R.drawable.home_normal_all_person));
            bmiNumTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
            bmiStateTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
            //            bmiStateTv.setText("正常");
            bmiStateTv.setBackground(ContextCompat.getDrawable(getPageContext(), R.drawable.shape_bg_white_green_31_17_2));
        } else if ("3".equals(bmiStatus)) {
            shapeIm.setImageDrawable(ContextCompat.getDrawable(getPageContext(), R.drawable.home_more_fat_all_person));
            bmiNumTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.orange_ff));
            bmiStateTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.orange_ff));
            //            bmiStateTv.setText("超重");
            bmiStateTv.setBackground(ContextCompat.getDrawable(getPageContext(), R.drawable.shape_bg_white_orange_31_17_2));
        } else if ("4".equals(bmiStatus)) {
            shapeIm.setImageDrawable(ContextCompat.getDrawable(getPageContext(), R.drawable.home_fat_all_person));
            bmiNumTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.red_E5));
            //            bmiStateTv.setText("肥胖");
            bmiStateTv.setBackground(ContextCompat.getDrawable(getPageContext(), R.drawable.shape_bg_white_red_31_17_2));
        } else {
            shapeIm.setImageDrawable(ContextCompat.getDrawable(getPageContext(), R.drawable.home_normal_all_person));
            bmiNumTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
            bmiStateTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
            bmiStateTv.setVisibility(View.GONE);
        }

        heightNumTv.setText(allInfo.getBasicInfo().getHeight());
        weightNumTv.setText(allInfo.getBasicInfo().getWeight());
        bmiNumTv.setText(allInfo.getBasicInfo().getBmi());
        bmiStateTv.setText(allInfo.getBasicInfo().getBmiTag());
        bmiNormalTv.setText("正常BMI参考值:" + allInfo.getBasicInfo().getBmiNormal());
        xyValueTv.setText(allInfo.getBasicInfo().getSbp() + "/" + allInfo.getBasicInfo().getDbp());

        //血压状态 1 偏低2 正常3 偏高
        String bpStatus = allInfo.getBasicInfo().getBpStatus();
        if ("1".equals(bpStatus)) {
            xyValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.blue_2a));
        } else if ("2".equals(bpStatus)) {
            xyValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
        } else if ("3".equals(bpStatus)) {
            xyValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.red_ED));
        } else {
            xyValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
        }
        //血压值
        xyValueTv.setText(allInfo.getBasicInfo().getSbp() + "/" + allInfo.getBasicInfo().getDbp());
        xyTimeTv.setText(allInfo.getBasicInfo().getBpDate());
        //血糖值
        xtValueTv.setText(allInfo.getBasicInfo().getBgValue());
        xtTimeTv.setText(allInfo.getBasicInfo().getBgDate());
        //血糖状态1 偏低  2 正常  3 偏高
        String bgStatus = allInfo.getBasicInfo().getBgStatus();
        if ("1".equals(bgStatus)) {
            xtValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.blue_2a));
        } else if ("2".equals(bgStatus)) {
            xtValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
        } else if ("3".equals(bgStatus)) {
            xtValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.red_ED));
        } else {
            xtValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
        }

        //心率值
        xlValueTv.setText(allInfo.getBasicInfo().getHr());
        xlTimeTv.setText(allInfo.getBasicInfo().getHrDate());
        //心率状态1 偏低  2 正常  3 偏高
        String hrStatus = allInfo.getBasicInfo().getHrStatus();
        if ("1".equals(hrStatus)) {
            xlValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.blue_2a));
        } else if ("2".equals(hrStatus)) {
            xlValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
        } else if ("3".equals(hrStatus)) {
            xlValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.red_ED));
        } else {
            xlValueTv.setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
        }
        //肾脏病变
        if ("1".equals(allInfo.getBasicInfo().getDn())) {
            szIllnessTv.setVisibility(View.VISIBLE);
            szIllnessIm.setVisibility(View.VISIBLE);
        } else {
            szIllnessTv.setVisibility(View.GONE);
            szIllnessIm.setVisibility(View.GONE);
        }
        //视网膜病变
        if ("1".equals(allInfo.getBasicInfo().getDr())) {
            swmIllnessTv.setVisibility(View.VISIBLE);
            swmIllnessIm.setVisibility(View.VISIBLE);
        } else {
            swmIllnessTv.setVisibility(View.GONE);
            swmIllnessIm.setVisibility(View.GONE);
        }
        //神经病变
        if ("1".equals(allInfo.getBasicInfo().getDpn())) {
            sjIllnessTv.setVisibility(View.VISIBLE);
            sjIllnessIm.setVisibility(View.VISIBLE);
        } else {
            sjIllnessTv.setVisibility(View.GONE);
            sjIllnessIm.setVisibility(View.GONE);
        }
        //下肢血管病变
        if ("1".equals(allInfo.getBasicInfo().getLead())) {
            xgIllnessTv.setVisibility(View.VISIBLE);
            xgIllnessIm.setVisibility(View.VISIBLE);
            xgIllnesslineIm.setVisibility(View.VISIBLE);
        } else {
            xgIllnessTv.setVisibility(View.GONE);
            xgIllnessIm.setVisibility(View.GONE);
            xgIllnesslineIm.setVisibility(View.GONE);
        }
        //糖尿病足
        if ("1".equals(allInfo.getBasicInfo().getDf())) {
            tzIllnessIm.setVisibility(View.VISIBLE);
            tzIllnessTv.setVisibility(View.VISIBLE);
        } else {
            tzIllnessIm.setVisibility(View.GONE);
            tzIllnessTv.setVisibility(View.GONE);
        }


    }

    /**
     * 给饮食赋值
     */
    private void initMeal() {
        //饮食是否开启   1开启  0未开启
        SharedPreferencesUtils.saveInfo(getPageContext(), SharedPreferencesConstant.IS_OPEN_MEAL, allInfo.getDietModuleStatus());
        if ("1".equals(allInfo.getDietModuleStatus())) {
            mealHaveOpenLin.setVisibility(View.VISIBLE);
            mealNoOpenLin.setVisibility(View.GONE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getPageContext());
            layoutManager.setOrientation(recyclRv.HORIZONTAL);
            recyclRv.setLayoutManager(layoutManager);

            HomeMealListAdapter adapter = new HomeMealListAdapter(getPageContext(), allInfo.getDietModule().getDietPlan());
            recyclRv.setAdapter(adapter);
            mealfireTv.setText(setMealTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), 18, "今日需要摄入总热量", " " + allInfo.getDietModule().getSumCalories() + " ", "千卡"));
        } else {
            mealHaveOpenLin.setVisibility(View.GONE);
            mealNoOpenLin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 给运动赋值
     */
    private void initExercise() {
        SharedPreferencesUtils.saveInfo(getPageContext(), SharedPreferencesConstant.IS_OPEN_EXERCISE, allInfo.getSportModuleStatus());

        if ("1".equals(allInfo.getSportModuleStatus())) {
            exerciseNoLinearLayout.setVisibility(View.GONE);
            exerciseHaveLinearLayout.setVisibility(View.VISIBLE);
            exerciseNumTv.setText(setMealTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), 18, "今日需要消耗热量", " " + allInfo.getSportModule().getNeedConsumeCalories() + " ", "千卡"));
            stepNumTv.setText(setMealTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), 15, "步行", " " + allInfo.getSportModule().getWalkNum() + " ", "步"));
            fireNumTv.setText(setMealTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#F98515"), 15, "消耗热量", " " + allInfo.getSportModule().getWalkCalories() + " ", "千卡"));
            exerciseTypeTv.setText(allInfo.getSportModule().getAerobicsName());
            needFireTv.setText(allInfo.getSportModule().getAerobicsNeedCalories());
            timeTv.setText(allInfo.getSportModule().getAerobicsTime());
        } else {
            exerciseNoLinearLayout.setVisibility(View.VISIBLE);
            exerciseHaveLinearLayout.setVisibility(View.GONE);
        }

    }

    /**
     * 给教育赋值
     */
    private void initEducation() {
        SharedPreferencesUtils.saveInfo(getPageContext(), SharedPreferencesConstant.IS_OPEN_EDUCATION, allInfo.getTodayArticleModuleStatus());
        if ("1".equals(allInfo.getTodayArticleModuleStatus())) {
            educationNoLinearLayout.setVisibility(View.GONE);
            educationHaveLinearLayout.setVisibility(View.VISIBLE);
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append("本节共");
            int length = builder.length();
            //            builder.append(allInfo.getTodayArticle().getWordNumber());
            builder.append("100");
            int length1 = builder.length();
            builder.append("字，阅读时间约");
            int length2 = builder.length();
            //            builder.append(allInfo.getTodayArticle().getReadTime());
            builder.append("20");
            int length3 = builder.length();
            builder.append("分钟");

            builder.setSpan(new ForegroundColorSpan(Color.parseColor("#00C27F")), length, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(Color.parseColor("#00C27F")), length2, length3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            educationNumTv.setText(builder);
            titleTv.setText(allInfo.getTodayArticle().getEssayName());
            containTv.setText(allInfo.getTodayArticle().getSname());
            contentTv.setText(allInfo.getTodayArticle().getEssayProfile());
            XyImageUtils.loadRoundImage(getPageContext(), R.drawable.shape_defaultbackground_5, allInfo.getTodayArticle().getCoverUrl(), articleBgIm);
        } else {
            educationNoLinearLayout.setVisibility(View.VISIBLE);
            educationHaveLinearLayout.setVisibility(View.GONE);
        }

    }

    /**
     * 设置血糖血压bmi
     */
    private void initViewPager() {
        ArrayList<Fragment> fragments;

        HomeXueTangH5Fragment detailsFragment = new HomeXueTangH5Fragment();
        HomeXueYaH5Fragment resourceProportionFragment = new HomeXueYaH5Fragment();
        HomeBmiH5Fragment bmiFragment = new HomeBmiH5Fragment();

        fragments = new ArrayList<>();
        fragments.add(detailsFragment.getInstance(allInfo.getBgModule()));
        fragments.add(resourceProportionFragment.getInstance(allInfo.getBpModule()));
        fragments.add(bmiFragment.getInstance(allInfo.getBmiModule()));

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
    public void onResume() {
        super.onResume();
        getHomeData();
    }

    private void getOneDayMeals() {
        Call<String> requestCall = HomeDataManager.randomMealsPlanToDay(UserInfoUtils.getArchivesId(getPageContext()), allInfo.getDietModule().getMeals(), allInfo.getDietModule().getPlanDate(), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                List<MealExclusiveInfo> list = (List<MealExclusiveInfo>) response.object;
                HomeMealListAdapter adapter = new HomeMealListAdapter(getPageContext(), list);
                recyclRv.setAdapter(adapter);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("randomMealsPlanToDay", requestCall);
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
                intent = new Intent(getPageContext(), CaptureActivity.class);
                startActivity(intent);
                break;
            //异常提醒
            case R.id.iv_home_data_abnormal:
                intent = new Intent(getPageContext(), HomeWarningListActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_home_healthy_tips_spread:
                notifyWithLimitItemNumb();
                break;
            //重新制定    //运动快来开启
            case R.id.tv_exercise_make_plan_again:
            case R.id.tv_main_no_answer_exercise:
                intent = new Intent(getPageContext(), ExercisePlanOneActivity.class);
                startActivity(intent);
                break;
            //运动计划
            case R.id.tv_exercise_make_plan:
                intent = new Intent(getPageContext(), ExerciseIntelligenceActivity.class);
                startActivity(intent);
                break;
            //立即完成 运动 R 抗阻  P 柔韧  O:有氧
            case R.id.tv_exercise_finish:
                intent = new Intent(getPageContext(), ExerciseCountdownActivity.class);
                intent.putExtra("type", "O");
                intent.putExtra("sportId", allInfo.getSportModule().getAerobicsId());
                startActivity(intent);
                break;
            //抗阻运动
            case R.id.tv_exercise_resistance:
                intent = new Intent(getPageContext(), ExerciseCountdownActivity.class);
                intent.putExtra("type", "R");
                intent.putExtra("sportId", allInfo.getSportModule().getResistanceId());
                startActivity(intent);
                break;
            //柔韧性运动
            case R.id.tv_exercise_flexibility:
                intent = new Intent(getPageContext(), ExerciseCountdownActivity.class);
                intent.putExtra("type", "P");
                intent.putExtra("sportId", allInfo.getSportModule().getPliableId());
                startActivity(intent);
                break;
            //重新制定 饮食
            case R.id.tv_meal_make_again:
                //去开启饮食方案
            case R.id.tv_main_no_answer_meal:
                intent = new Intent(getPageContext(), DietProgrammeBeginActivity.class);
                startActivity(intent);
                break;
            //三餐刷新
            case R.id.tv_meal_refresh:
                getOneDayMeals();
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
                intent = new Intent(getPageContext(), DietMealDetailsActivity.class);
                intent.putExtra("meals", allInfo.getDietModule().getMeals());
                intent.putExtra("planDate", allInfo.getDietModule().getPlanDate());
                startActivity(intent);
                break;
            //教育关于你
            case R.id.tv_education_about_you:
                intent = new Intent(getPageContext(), EducationIntelligenceActivity.class);
                startActivity(intent);
                break;
            //教育重新制定
            case R.id.tv_education_make_again:
            case R.id.tv_main_no_answer_education:
                intent = new Intent(getPageContext(), EducationQuestionInvestigateBeginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_main_home_first, null);
        healthyTipRv = view.findViewById(R.id.lv_home_healthy_tip);
        healthyTipLl = view.findViewById(R.id.ll_home_healthy_tip);
        spreadIm = view.findViewById(R.id.tv_home_healthy_tips_spread);
        shapeIm = view.findViewById(R.id.iv_home_xy_people_shape);
        xyLinearLayout = view.findViewById(R.id.ll_home_xy_people_value);
        xyValueTv = view.findViewById(R.id.tv_home_xy_people_value);
        xyTimeTv = view.findViewById(R.id.tv_home_xy_people_time);

        xtLinearLayout = view.findViewById(R.id.ll_home_xt_people_value);
        xtValueTv = view.findViewById(R.id.tv_home_xt_people_value);
        xtTimeTv = view.findViewById(R.id.tv_home_xt_people_time);

        xlLinearLayout = view.findViewById(R.id.ll_home_xl_people_value);
        xlValueTv = view.findViewById(R.id.tv_home_xl_people_value);
        xlTimeTv = view.findViewById(R.id.tv_home_xl_people_time);

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
        bmiNormalTv = view.findViewById(R.id.tv_home_bmi_normal);

        threeVp = view.findViewById(R.id.vp_home_three_fragment);
        threeRg = view.findViewById(R.id.rg_home_xt_xy_bmi);


        mealNoOpenLin = view.findViewById(R.id.ll_main_no_answer_meal);
        mealHaveOpenLin = view.findViewById(R.id.ll_main_have_answer_meal);
        mealOpenTv = view.findViewById(R.id.tv_main_no_answer_meal);
        mealAgainTv = view.findViewById(R.id.tv_meal_make_again);
        mealfireTv = view.findViewById(R.id.tv_meal_need_fire);
        refreshTv = view.findViewById(R.id.tv_meal_refresh);
        yourselfTv = view.findViewById(R.id.tv_meal_make_yourself);
        mealPlanTv = view.findViewById(R.id.tv_meal_plan);
        recyclRv = view.findViewById(R.id.rv_meal_recycl);
        mealMoreLin = view.findViewById(R.id.ll_meal_more);


        exerciseOpenTv = view.findViewById(R.id.tv_main_no_answer_exercise);
        exerciseNoLinearLayout = view.findViewById(R.id.ll_main_no_answer_exercise);
        exerciseHaveLinearLayout = view.findViewById(R.id.ll_main_have_answer_exercise);
        makeExerciseAgainPlanTv = view.findViewById(R.id.tv_exercise_make_plan_again);
        makePlanTv = view.findViewById(R.id.tv_exercise_make_plan);
        exerciseNumTv = view.findViewById(R.id.tv_home_exercise_need_fire);
        stepNumTv = view.findViewById(R.id.tv_exercise_step_num);
        fireNumTv = view.findViewById(R.id.tv_exercise_fire_num);
        finishTv = view.findViewById(R.id.tv_exercise_finish);
        exerciseTypeTv = view.findViewById(R.id.tv_exercise_type);
        needFireTv = view.findViewById(R.id.tv_main_exercise_need_fire);
        timeTv = view.findViewById(R.id.tv_exercise_time);
        resistanceTv = view.findViewById(R.id.tv_exercise_resistance);
        flexibilityTv = view.findViewById(R.id.tv_exercise_flexibility);


        educationOpenTv = view.findViewById(R.id.tv_main_no_answer_education);
        educationNoLinearLayout = view.findViewById(R.id.ll_main_no_answer_education);
        educationHaveLinearLayout = view.findViewById(R.id.ll_main_have_answer_education);
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

        makeExerciseAgainPlanTv.setOnClickListener(this);
        makePlanTv.setOnClickListener(this);
        finishTv.setOnClickListener(this);
        resistanceTv.setOnClickListener(this);
        flexibilityTv.setOnClickListener(this);
        mealAgainTv.setOnClickListener(this);
        refreshTv.setOnClickListener(this);
        yourselfTv.setOnClickListener(this);
        mealPlanTv.setOnClickListener(this);
        mealMoreLin.setOnClickListener(this);
        mealOpenTv.setOnClickListener(this);

        exerciseOpenTv.setOnClickListener(this);

        educationOpenTv.setOnClickListener(this);

        aboutYouTv.setOnClickListener(this);
        makeAgainTv.setOnClickListener(this);

        refreshLayout().setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getHomeData();
            }
        });

    }

}
