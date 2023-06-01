package com.vice.bloodpressure.activity.auser;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.flexbox.FlexboxLayout;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.login.PerfectDiseaseAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.model.DiseaseInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.HHAtMostGridView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:isAdd 1：添加  2：编辑
 * type  1:主要诊断   2其他诊断
 * 描述:其他诊断
 */
public class UserIllOtherActivity extends UIBaseLoadActivity implements View.OnClickListener {
    /**
     * 1：添加  2：编辑
     */
    private String isAdd;
    /**
     * 尖箭头
     */
    private ImageView arrowImageView;

    /**
     * 灰色的背景
     */
    private LinearLayout bgLinearLayout;
    /**
     * 疾病类型
     */
    private FlexboxLayout typeFl;
    /**
     * 疾病类型的高血压的程度
     */
    private TextView allTypeTextView;
    /**
     * 疾病类型的糖尿病
     */
    private HHAtMostGridView tangTypeGridView;
    /**
     * 疾病类型的高血压的程度
     */
    private HHAtMostGridView typeGridView;
    /**
     * 疾病类型的高血压的等级
     */
    private HHAtMostGridView levelGridView;
    /**
     * 时间
     */
    private TextView timeTv;
    /**
     * 保存按钮
     */
    private TextView saveTv;
    /**
     * 1:主要诊断   2其他诊断
     */
    private String type;
    /**
     * 添加时间
     */
    private String addTime = "";

    private PerfectDiseaseAdapter tangTypeAdapter;
    private PerfectDiseaseAdapter typeAdapter;
    private PerfectDiseaseAdapter levelAdapter;
    /**
     * 疾病列表
     */
    private List<BaseLocalDataInfo> tangDiseaseList;
    private List<BaseLocalDataInfo> diseaseList;
    private List<BaseLocalDataInfo> levelList;

    private String checkId = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("诊断");
        isAdd = getIntent().getStringExtra("isAdd");
        type = getIntent().getStringExtra("type");
        if ("1".equals(isAdd)) {
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        } else {
            loadViewManager().changeLoadState(LoadStatus.LOADING);
        }
        initView();
        initListener();
        initValues();
    }

    private void initValues() {
        setAllRtpeList(new DiseaseInfo());
        tangDiseaseList = new ArrayList<>();
        tangDiseaseList.add(new BaseLocalDataInfo("1型糖尿病", "1"));
        tangDiseaseList.add(new BaseLocalDataInfo("2型糖尿病", "2"));
        tangDiseaseList.add(new BaseLocalDataInfo("妊娠糖尿病", "3"));
        tangTypeAdapter = new PerfectDiseaseAdapter(getPageContext(), tangDiseaseList);
        tangTypeGridView.setAdapter(tangTypeAdapter);

        diseaseList = new ArrayList<>();
        diseaseList.add(new BaseLocalDataInfo("1级高血压", "1"));
        diseaseList.add(new BaseLocalDataInfo("2级高血压", "2"));
        diseaseList.add(new BaseLocalDataInfo("3级高血压", "3"));

        typeAdapter = new PerfectDiseaseAdapter(getPageContext(), diseaseList);
        typeGridView.setAdapter(typeAdapter);

        levelList = new ArrayList<>();
        BaseLocalDataInfo levelInfo1 = new BaseLocalDataInfo("低危", "1");
        levelList.add(levelInfo1);
        BaseLocalDataInfo levelInfo2 = new BaseLocalDataInfo("中危", "2");
        levelList.add(levelInfo2);
        BaseLocalDataInfo levelInfo3 = new BaseLocalDataInfo("高危", "3");
        levelList.add(levelInfo3);
        BaseLocalDataInfo levelInfo4 = new BaseLocalDataInfo("很高危", "4");
        levelList.add(levelInfo4);

        levelAdapter = new PerfectDiseaseAdapter(getPageContext(), levelList);
        levelGridView.setAdapter(levelAdapter);
    }

    private void setAllRtpeList(DiseaseInfo diseaseInfo) {
        List<BaseLocalDataInfo> diseaseAllTypeList = new ArrayList();
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病", "1", diseaseInfo.getDiseaseType1()));
        diseaseAllTypeList.add(new BaseLocalDataInfo("高血压", "2", diseaseInfo.getDiseaseType2()));
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病前期", "3", diseaseInfo.getDiseaseType3()));
        diseaseAllTypeList.add(new BaseLocalDataInfo("冠心病", "4", diseaseInfo.getDiseaseType4()));
        diseaseAllTypeList.add(new BaseLocalDataInfo("脑卒中", "5", diseaseInfo.getDiseaseType5()));
        diseaseAllTypeList.add(new BaseLocalDataInfo("慢性阻塞性肺疾病", "6", diseaseInfo.getDiseaseType6()));

        diseaseAllTypeList.get(0).setCheck(true);
        for (int i = 0; i < diseaseAllTypeList.size(); i++) {
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, DensityUtils.dip2px(getPageContext(), 10f), DensityUtils.dip2px(getPageContext(), 10f), 0);
            TextView checkTextView = new TextView(getPageContext());
            checkTextView.setTextSize(15f);
            if ("0".equals(diseaseAllTypeList.get(i).getIsSelect())) {
                if (diseaseAllTypeList.get(i).isCheck()) {
                    checkTextView.setBackgroundResource(R.drawable.shape_bg_main_gra_90);
                    checkTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_white));
                } else {
                    checkTextView.setBackgroundResource(R.drawable.shape_bg_white_black_90_1);
                    checkTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
                }
            } else {
                checkTextView.setBackgroundResource(R.drawable.shape_defaultbackground_90);
                checkTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
            }


            checkTextView.setGravity(Gravity.CENTER);
            checkTextView.setText(diseaseAllTypeList.get(i).getName());
            checkTextView.setMaxLines(1);
            checkTextView.setPadding(DensityUtils.dip2px(getPageContext(), 15f), DensityUtils.dip2px(getPageContext(), 8f), DensityUtils.dip2px(getPageContext(), 15f), DensityUtils.dip2px(getPageContext(), 8f));

            checkTextView.setTag(diseaseAllTypeList.get(i).getId());
            typeFl.addView(checkTextView, lp);
            int finalI = i;
            checkTextView.setOnClickListener(v -> {
                if ("1".equals(diseaseAllTypeList.get(finalI).getIsSelect())) {
                    ToastUtils.getInstance().showToast(getPageContext(), "已经添加过此病种了");
                    return;
                }
                checkId = v.getTag().toString();
                arrowImageView.setVisibility(View.GONE);
                allTypeTextView.setVisibility(View.GONE);
                levelGridView.setVisibility(View.GONE);
                bgLinearLayout.setVisibility(View.GONE);
                typeGridView.setVisibility(View.GONE);
                if ("1".equals(v.getTag())) {
                    arrowImageView.setVisibility(View.VISIBLE);
                    bgLinearLayout.setVisibility(View.VISIBLE);
                    tangTypeGridView.setVisibility(View.VISIBLE);
                }


                if ("2".equals(v.getTag())) {
                    arrowImageView.setVisibility(View.VISIBLE);
                    tangTypeGridView.setVisibility(View.GONE);
                    bgLinearLayout.setVisibility(View.VISIBLE);
                    allTypeTextView.setVisibility(View.VISIBLE);
                    levelGridView.setVisibility(View.VISIBLE);
                    typeGridView.setVisibility(View.VISIBLE);
                }
                for (int j = 0; j < diseaseAllTypeList.size(); j++) {
                    if (v.getTag().equals(diseaseAllTypeList.get(j).getId())) {
                        //设置选中
                        diseaseAllTypeList.get(j).setCheck(true);
                        typeFl.getChildAt(j).setBackgroundResource(R.drawable.shape_bg_main_gra_90);
                        ((TextView) typeFl.getChildAt(j)).setTextColor(Color.parseColor("#FFFFFF"));
                    } else {
                        //设置不选中
                        diseaseAllTypeList.get(j).setCheck(false);
                        typeFl.getChildAt(j).setBackgroundResource(R.drawable.shape_bg_white_black_90_1);
                        ((TextView) typeFl.getChildAt(j)).setTextColor(Color.parseColor("#242424"));
                    }
                }
            });
        }
    }


    /**
     * 确定上传数据
     */
    private void sureToAddData() {
        String diseaseRisk = "";
        if ("2".equals(checkId)) {
            diseaseRisk = levelList.get(levelAdapter.getClickPosition()).getId();
        }
        Call<String> requestCall = UserDataManager.putDiseaseImportant(UserInfoUtils.getArchivesId(getPageContext()), type, checkId, diseaseList.get(typeAdapter.getClickPosition()).getId(), diseaseRisk, addTime, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setResult(RESULT_OK);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("putDiseaseImportant", requestCall);
    }


    @Override
    protected void onPageLoad() {
        Call<String> requestCall = UserDataManager.lookDiseaseImportant(UserInfoUtils.getArchivesId(getPageContext()), type, (call, response) -> {

            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                DiseaseInfo dataInfo = (DiseaseInfo) response.object;
                setAllRtpeList(dataInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("lookDiseaseImportant", requestCall);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_user_ill_other:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, object -> {
                    addTime = object.toString();
                    timeTv.setText(object.toString());
                });
                break;
            case R.id.tv_user_ill_other_save:
                if (TextUtils.isEmpty(addTime)) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请选择时间");
                    return;
                }
                sureToAddData();
                break;
            default:
                break;
        }
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_other, null);
        arrowImageView = view.findViewById(R.id.iv_user_ill_other);
        bgLinearLayout = view.findViewById(R.id.ll_user_ill_other);
        typeFl = view.findViewById(R.id.fl_user_ill_other);
        allTypeTextView = view.findViewById(R.id.tv_user_ill_other_level);
        tangTypeGridView = view.findViewById(R.id.gv_user_ill_other_type_tang);
        typeGridView = view.findViewById(R.id.gv_user_ill_other_type);
        levelGridView = view.findViewById(R.id.gv_user_ill_other_level);
        timeTv = view.findViewById(R.id.tv_user_ill_other);
        saveTv = view.findViewById(R.id.tv_user_ill_other_save);
        containerView().addView(view);
    }

    private void initListener() {
        timeTv.setOnClickListener(this);
        saveTv.setOnClickListener(this);
    }
}
