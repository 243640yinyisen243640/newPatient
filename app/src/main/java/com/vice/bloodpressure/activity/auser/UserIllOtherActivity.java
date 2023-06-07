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
 * type  1:主要诊断   2其他诊断
 * 描述:其他诊断
 */
public class UserIllOtherActivity extends UIBaseLoadActivity implements View.OnClickListener {

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
     * 疾病类型的糖尿病
     */
    private TextView tangTypeTextView;
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
    /**
     * 糖尿病的类型adapter
     */
    private PerfectDiseaseAdapter tangTypeAdapter;
    /**
     * 高血压的类型adapter
     */
    private PerfectDiseaseAdapter yaTypeAdapter;
    /**
     * 高血压的等级adapter
     */
    private PerfectDiseaseAdapter levelAdapter;
    /**
     * 疾病列表
     */
    private List<BaseLocalDataInfo> tangDiseaseList = new ArrayList<>();
    private List<BaseLocalDataInfo> diseaseList = new ArrayList<>();
    private List<BaseLocalDataInfo> levelList = new ArrayList<>();


    private List<BaseLocalDataInfo> diseaseAllTypeList = new ArrayList<>();

    private String checkId = "-1";
    /**
     * 疾病类型  1, 糖尿病2, 高血压3, 糖尿病前期4,冠心病5, 脑卒中6, 慢阻肺
     */
    private String diseaseType = "";
    /**
     * 1：添加  2：编辑
     */
    private String isAdd = "";

    private DiseaseInfo dataInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("诊断");
        isAdd = getIntent().getStringExtra("isAdd");
        type = getIntent().getStringExtra("type");
        diseaseType = getIntent().getStringExtra("diseaseType");
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
     * 诊断详情的接口
     */
    private void getDetailsData() {
        Call<String> requestCall = UserDataManager.getDiseaseImportantDetails(UserInfoUtils.getArchivesId(getPageContext()), diseaseType, type, (call, response) -> {
            if ("0000".equals(response.code)) {
                initListener();
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                dataInfo = (DiseaseInfo) response.object;
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
        addTime = dataInfo.getDiagnoseDate();
        diseaseAllTypeList.get(Integer.parseInt(dataInfo.getDiseaseType()) - 1).setCheck(true);
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

        tangTypeGridView.setVisibility(View.GONE);
        tangTypeTextView.setVisibility(View.GONE);
        allTypeTextView.setVisibility(View.GONE);
        levelGridView.setVisibility(View.GONE);
        typeGridView.setVisibility(View.GONE);
        if ("1".equals(dataInfo.getDiseaseType())) {
            tangTypeTextView.setVisibility(View.VISIBLE);
            tangTypeGridView.setVisibility(View.VISIBLE);
            tangTypeAdapter.setClickPosition(Integer.parseInt(dataInfo.getDiseaseType()) - 1);
        }


        if ("2".equals(dataInfo.getDiseaseType())) {
            tangTypeGridView.setVisibility(View.GONE);
            tangTypeTextView.setVisibility(View.VISIBLE);
            allTypeTextView.setVisibility(View.VISIBLE);
            levelGridView.setVisibility(View.VISIBLE);
            typeGridView.setVisibility(View.VISIBLE);
            yaTypeAdapter.setClickPosition(Integer.parseInt(dataInfo.getDiseaseType()) - 1);
            levelAdapter.setClickPosition(Integer.parseInt(dataInfo.getDiseaseType()) - 1);
        }
        timeTv.setText(dataInfo.getDiagnoseDate());
    }

    /**
     * 添加时，调的接口，查看疾病是否已经被选择过
     */
    private void getAddData() {
        Call<String> requestCall = UserDataManager.lookDiseaseImportant(UserInfoUtils.getArchivesId(getPageContext()), type, (call, response) -> {
            if ("0000".equals(response.code)) {
                initListener();
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                DiseaseInfo dataInfo = (DiseaseInfo) response.object;
                bindData(dataInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("lookDiseaseImportant", requestCall);
    }

    /**
     * 绑定数据
     *
     * @param diseaseInfo
     */
    private void bindData(DiseaseInfo diseaseInfo) {
        //设置疾病类型
        setAllRtpeList(diseaseInfo);

    }

    /**
     * 设置疾病类型
     *
     * @param diseaseInfo
     */
    private void setAllRtpeList(DiseaseInfo diseaseInfo) {
        diseaseAllTypeList.get(0).setIsSelect(diseaseInfo.getDiseaseType1());
        diseaseAllTypeList.get(1).setIsSelect(diseaseInfo.getDiseaseType2());
        diseaseAllTypeList.get(2).setIsSelect(diseaseInfo.getDiseaseType3());
        diseaseAllTypeList.get(3).setIsSelect(diseaseInfo.getDiseaseType4());
        diseaseAllTypeList.get(4).setIsSelect(diseaseInfo.getDiseaseType5());
        diseaseAllTypeList.get(5).setIsSelect(diseaseInfo.getDiseaseType6());

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

                tangTypeGridView.setVisibility(View.GONE);
                tangTypeTextView.setVisibility(View.GONE);
                allTypeTextView.setVisibility(View.GONE);
                levelGridView.setVisibility(View.GONE);
                typeGridView.setVisibility(View.GONE);
                if ("1".equals(v.getTag())) {
                    tangTypeTextView.setVisibility(View.VISIBLE);
                    tangTypeGridView.setVisibility(View.VISIBLE);
                }


                if ("2".equals(v.getTag())) {
                    tangTypeGridView.setVisibility(View.GONE);
                    tangTypeTextView.setVisibility(View.VISIBLE);
                    allTypeTextView.setVisibility(View.VISIBLE);
                    levelGridView.setVisibility(View.VISIBLE);
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

    private void initValues() {
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病", "1"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("高血压", "2"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("糖尿病前期", "3"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("冠心病", "4"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("脑卒中", "5"));
        diseaseAllTypeList.add(new BaseLocalDataInfo("慢性阻塞性肺疾病", "6"));

        //设置糖尿病
        tangDiseaseList.add(new BaseLocalDataInfo("1型糖尿病", "1"));
        tangDiseaseList.add(new BaseLocalDataInfo("2型糖尿病", "2"));
        tangDiseaseList.add(new BaseLocalDataInfo("妊娠糖尿病", "3"));
        tangTypeAdapter = new PerfectDiseaseAdapter(getPageContext(), tangDiseaseList);
        tangTypeGridView.setAdapter(tangTypeAdapter);

        diseaseList.add(new BaseLocalDataInfo("1级高血压", "1"));
        diseaseList.add(new BaseLocalDataInfo("2级高血压", "2"));
        diseaseList.add(new BaseLocalDataInfo("3级高血压", "3"));

        yaTypeAdapter = new PerfectDiseaseAdapter(getPageContext(), diseaseList);
        typeGridView.setAdapter(yaTypeAdapter);

        levelList.add(new BaseLocalDataInfo("低危", "1"));
        levelList.add(new BaseLocalDataInfo("中危", "2"));
        levelList.add(new BaseLocalDataInfo("高危", "3"));
        levelList.add(new BaseLocalDataInfo("很高危", "4"));

        levelAdapter = new PerfectDiseaseAdapter(getPageContext(), levelList);
        levelGridView.setAdapter(levelAdapter);

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
                saveTv.setClickable(false);
                if ("1".equals(isAdd)) {
                    sureToAddData();
                } else {
                    sureToEditData();
                }

                break;
            default:
                break;
        }
    }


    /**
     * 确定上传编辑数据
     */
    private void sureToEditData() {
        String diseaseRisk = "";
        String diseaseChildType = "";
        if ("1".equals(dataInfo.getDiseaseType())) {
            diseaseChildType = tangTypeAdapter.getClickPosition() + 1 + "";
        } else if ("2".equals(dataInfo.getDiseaseType())) {
            diseaseRisk = levelList.get(levelAdapter.getClickPosition()).getId();
            diseaseChildType = yaTypeAdapter.getClickPosition() + 1 + "";
        } else {
            diseaseRisk = "";
        }
        Call<String> requestCall = UserDataManager.editDiseaseImportant(UserInfoUtils.getArchivesId(getPageContext()), type, dataInfo.getDiagnosticType(), diseaseChildType, diseaseRisk, addTime, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setResult(RESULT_OK);
                finish();
            } else {
                saveTv.setClickable(true);
            }
        }, (call, t) -> {
            saveTv.setClickable(true);
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("putDiseaseImportant", requestCall);
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
        String diseaseRisk = "";
        String diseaseChildType = "";
        if ("1".equals(checkId)) {
            diseaseChildType = tangTypeAdapter.getClickPosition() + 1 + "";
        } else {
            diseaseRisk = levelList.get(levelAdapter.getClickPosition()).getId();
            diseaseChildType = yaTypeAdapter.getClickPosition() + 1 + "";
        }
        Call<String> requestCall = UserDataManager.putDiseaseImportant(UserInfoUtils.getArchivesId(getPageContext()), type, checkId, diseaseChildType, diseaseRisk, addTime, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setResult(RESULT_OK);
                finish();
            } else {
                saveTv.setClickable(true);
            }
        }, (call, t) -> {
            saveTv.setClickable(true);
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("putDiseaseImportant", requestCall);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_other, null);
        tangTypeTextView = view.findViewById(R.id.tv_user_ill_other_type_tang);
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
