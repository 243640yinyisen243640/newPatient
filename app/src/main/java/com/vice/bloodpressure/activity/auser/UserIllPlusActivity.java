package com.vice.bloodpressure.activity.auser;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
 * diseaseType 疾病类型
 * diagnosticType 诊断类型 1主要 2其他
 * 描述:合并症
 */
public class UserIllPlusActivity extends UIBaseLoadActivity {
    /**
     * 1：添加  2：编辑
     */
    private String isAdd;

    private FlexboxLayout typeFl;
    private HHAtMostGridView typeGridView;
    private TextView typeTextView;
    private TextView timeTv;
    private TextView saveTv;

    /**
     * 选中的id
     */
    private String checkId = "-1";


    private String addTime = "";

    /**
     * 等级的adapter
     */

    private PerfectDiseaseAdapter adapter;
    /**
     * 疾病类型
     */
    private String diseaseType;
    /**
     * 诊断类型 1主要 2其他
     */
    private String diagnosticType;
    /**
     * 合并症类型
     */
    private String complicationType;
    /**
     * 疾病类型的列表
     */
    private List<BaseLocalDataInfo> diseaseAllTypeList = new ArrayList<>();
    /**
     * 等级的列表
     */
    private List<BaseLocalDataInfo> levelList = new ArrayList<>();

    /**
     * 获取详情时得到的数据
     */
    private DiseaseInfo editDataInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("合并症");
        isAdd = getIntent().getStringExtra("isAdd");
        diagnosticType = getIntent().getStringExtra("diagnosticType");
        if ("2".equals(isAdd)) {
            diseaseType = getIntent().getStringExtra("diseaseType");
            complicationType = getIntent().getStringExtra("complicationType");
        }
        initView();
        initValues();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }


    @Override
    protected void onPageLoad() {
        if ("1".equals(isAdd)) {
            getAddData();
        } else {
            getDetailsData();
        }
    }

    /**
     * 确定上传编辑数据
     */
    private void sureToEditData() {
        String level = "";
        if ("1".equals(editDataInfo.getComplicationType())) {
            level = levelList.get(adapter.getClickPosition()).getId();
        } else {
            level = "";
        }
        Call<String> requestCall = UserDataManager.editDiseasePlus(UserInfoUtils.getArchivesId(getPageContext()), diagnosticType, diseaseType, editDataInfo.getComplicationType(), level, addTime, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setResult(RESULT_OK);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("editDiseasePlus", requestCall);
    }

    /**
     * 确定上传数据
     */
    private void sureToAddData() {

        if ("-1".equals(checkId)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择疾病类型");
            return;
        }
        if (TextUtils.isEmpty(addTime)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择时间");
            return;
        }
        String level = "";
        if ("1".equals(checkId)) {
            level = levelList.get(adapter.getClickPosition()).getId();
        } else {
            level = "";
        }

        Call<String> requestCall = UserDataManager.putDiseasePlus(UserInfoUtils.getArchivesId(getPageContext()), checkId, levelList.get(adapter.getClickPosition()).getId(), level, addTime, (call, response) -> {
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


    /**
     * 诊断详情的接口
     */
    private void getDetailsData() {
        Call<String> requestCall = UserDataManager.getDiseasePlusDetails(UserInfoUtils.getArchivesId(getPageContext()), diagnosticType, diseaseType, complicationType, (call, response) -> {
            if ("0000".equals(response.code)) {
                initListener();
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                editDataInfo = (DiseaseInfo) response.object;
                bindDataDetailsData();
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getDiseaseImportantDetails", requestCall);
    }

    /**
     * 查看详情
     */
    private void bindDataDetailsData() {
        //调取详情，疾病类型不能修改，疾病类型下的类型程度是可以修改的
        typeFl.setClickable(false);
        addTime = editDataInfo.getComplicationDate();
        diseaseAllTypeList.get(Integer.parseInt(editDataInfo.getComplicationType()) - 1).setCheck(true);
        for (int i = 0; i < diseaseAllTypeList.size(); i++) {
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, DensityUtils.dip2px(getPageContext(), 10f), DensityUtils.dip2px(getPageContext(), 10f), 0);
            TextView checkTextView = new TextView(getPageContext());
            checkTextView.setTextSize(15f);
            if (diseaseAllTypeList.get(i).isCheck()) {
                checkTextView.setBackgroundResource(R.drawable.shape_bg_main_gra_90);
                checkTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_white));
            } else {
                checkTextView.setBackgroundResource(R.drawable.shape_bg_white_black_90_1);
                checkTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
            }
            checkTextView.setGravity(Gravity.CENTER);
            checkTextView.setText(diseaseAllTypeList.get(i).getName());
            checkTextView.setMaxLines(1);
            checkTextView.setPadding(DensityUtils.dip2px(getPageContext(), 15f), DensityUtils.dip2px(getPageContext(), 8f), DensityUtils.dip2px(getPageContext(), 15f), DensityUtils.dip2px(getPageContext(), 8f));
            typeFl.addView(checkTextView, lp);

        }
        typeTextView.setVisibility(View.GONE);
        typeGridView.setVisibility(View.GONE);
        if ("1".equals(editDataInfo.getComplicationType())) {
            typeTextView.setVisibility(View.VISIBLE);
            typeGridView.setVisibility(View.VISIBLE);

        }
        if ("1".equals(editDataInfo.getComplicationType())) {
            levelList.get(Integer.parseInt(editDataInfo.getLevel()) - 1).setCheck(true);
        }

        timeTv.setText(editDataInfo.getComplicationDate());
    }

    /**
     * 获取添加数据   哪些病种添加过
     */
    private void getAddData() {
        Call<String> requestCall = UserDataManager.lookDiseasePlus(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                initListener();
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                DiseaseInfo addDataInfo = (DiseaseInfo) response.object;
                bindAddData(addDataInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("lookDiseaseImportant", requestCall);
    }

    /**
     * 绑定
     *
     * @param addDataInfo
     */
    private void bindAddData(DiseaseInfo addDataInfo) {
        //设置疾病类型
        diseaseAllTypeList.get(0).setIsSelect(addDataInfo.getComplicationType1());
        diseaseAllTypeList.get(1).setIsSelect(addDataInfo.getComplicationType2());
        diseaseAllTypeList.get(2).setIsSelect(addDataInfo.getComplicationType3());
        diseaseAllTypeList.get(3).setIsSelect(addDataInfo.getComplicationType4());
        diseaseAllTypeList.get(4).setIsSelect(addDataInfo.getComplicationType5());
        diseaseAllTypeList.get(5).setIsSelect(addDataInfo.getComplicationType6());
        diseaseAllTypeList.get(6).setIsSelect(addDataInfo.getComplicationType7());
        for (int i = 0; i < diseaseAllTypeList.size(); i++) {
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, DensityUtils.dip2px(getPageContext(), 10f), DensityUtils.dip2px(getPageContext(), 10f), 0);
            TextView checkTextView = new TextView(getPageContext());
            checkTextView.setTextSize(15f);
            if ("1".equals(diseaseAllTypeList.get(i).getIsSelect())) {
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
                if ("0".equals(diseaseAllTypeList.get(finalI).getIsSelect())) {
                    ToastUtils.getInstance().showToast(getPageContext(), "已经添加过此病种了");
                    return;
                }

                checkId = v.getTag().toString();

                typeTextView.setVisibility(View.GONE);
                typeGridView.setVisibility(View.GONE);
                if ("1".equals(v.getTag())) {
                    typeTextView.setVisibility(View.VISIBLE);
                    typeGridView.setVisibility(View.VISIBLE);
                }


                for (int j = 0; j < diseaseAllTypeList.size(); j++) {
                    if ("0".equals(diseaseAllTypeList.get(j).getIsSelect())) {
                        typeFl.getChildAt(j).setBackgroundResource(R.drawable.shape_defaultbackground_90);
                        ((TextView) typeFl.getChildAt(j)).setTextColor(ContextCompat.getColor(getPageContext(), R.color.black_24));
                    } else {
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
                }

            });

        }
    }

    private void initListener() {
        timeTv.setOnClickListener(v -> {
            PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, object -> {
                addTime = object.toString();
                timeTv.setText(object.toString());
            });
        });
        saveTv.setOnClickListener(v -> {
            if ("1".equals(isAdd)) {
                sureToAddData();
            } else {
                sureToEditData();
            }
        });
    }

    private void initValues() {
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病肾病", "1"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病视网膜病变", "2"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病神经病变", "3"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病下肢血管病变", "4"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病足", "5"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病酮症酸中毒", "6"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("高渗性高血糖", "7"));


        levelList = new ArrayList<>();
        levelList.add(new BaseLocalDataInfo("1期", "1"));
        levelList.add(new BaseLocalDataInfo("2期", "2"));
        levelList.add(new BaseLocalDataInfo("3a期", "3"));
        levelList.add(new BaseLocalDataInfo("3b期", "4"));
        levelList.add(new BaseLocalDataInfo("4期", "5"));
        levelList.add(new BaseLocalDataInfo("5期", "6"));
        adapter = new PerfectDiseaseAdapter(getPageContext(), levelList);
        typeGridView.setAdapter(adapter);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_plus, null);
        typeFl = view.findViewById(R.id.fl_user_ill_plus);
        typeTextView = view.findViewById(R.id.tv_user_ill_plus_type_title);
        typeGridView = view.findViewById(R.id.gv_user_ill_plus);
        timeTv = view.findViewById(R.id.tv_user_ill_plus);
        saveTv = view.findViewById(R.id.tv_user_ill_plus_save);
        containerView().addView(view);
    }

}
