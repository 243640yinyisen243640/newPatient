package com.vice.bloodpressure.activity.ahome.aexercise;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:添加运动  跳绳 太极拳  跑步
 */
public class ExercisePlanAddRecordActivity extends UIBaseActivity {
    private TextView timeTv;
    private TextView fireTv;
    private TextView typeTv;
    private TextView timeChooseTv;
    private RulerView timeRv;
    private LinearLayout tiaoshengLl;
    private LinearLayout taijiLi;
    private TextView sureTextView;


    private List<BaseLocalDataInfo> sportList;

    private String sportId = "-1";
    /**
     * 单位时间内某个运动消耗的卡路里
     */
    private String calorieString = "";

    private String exerciseTime = "";

    private String finishCalorieString = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加运动记录");
        initView();
        initListener();
        initValue();
    }

    private void initValue() {
        //运动消耗热量的公式=体重(kg)*时间(min)*千卡/1kg/1min  步行 0.06
        timeRv.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                if (TextUtils.equals(sportId, "-1")) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请先选择运动类型");
                    return;
                }
                exerciseTime = String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP));
                timeChooseTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                timeTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));

                String weight = UserInfoUtils.getUserInfo(getPageContext(), SharedPreferencesConstant.WEIGHT);
                double calorieDouble = Double.parseDouble(calorieString);
                finishCalorieString = String.valueOf(new BigDecimal(String.valueOf(Double.parseDouble(weight) * calorieDouble * Double.parseDouble(result))).setScale(0, BigDecimal.ROUND_HALF_UP));
                fireTv.setText(finishCalorieString);
            }

            @Override
            public void onScrollResult(String result) {
                if (TextUtils.equals(sportId, "-1")) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请先选择运动类型");
                    return;
                }
                exerciseTime = String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP));
                timeChooseTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                timeTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                String weight = UserInfoUtils.getUserInfo(getPageContext(), SharedPreferencesConstant.WEIGHT);
                double calorieDouble = Double.parseDouble(calorieString);
                finishCalorieString = String.valueOf(new BigDecimal(String.valueOf(Double.parseDouble(weight) * calorieDouble * Double.parseDouble(result))).setScale(0, BigDecimal.ROUND_HALF_UP));
                fireTv.setText(finishCalorieString);
            }
        });
    }

    private void initListener() {
        typeTv.setOnClickListener(v -> {
            getOxygenData();
        });
        sureTextView.setOnClickListener(v -> {
            sureToAdd();
        });
    }

    private void getOxygenData() {
        Call<String> requestCall = HomeDataManager.getSportAerobics((call, response) -> {
            if ("0000".equals(response.code)) {
                sportList = (List<BaseLocalDataInfo>) response.object;
                List<String> list = new ArrayList<>();
                if (sportList != null && sportList.size() > 0) {
                    for (int i = 0; i < sportList.size(); i++) {
                        String typeName = sportList.get(i).getSportName();
                        list.add(typeName);
                    }
                }
                chooseTypeWindow(list);
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getSportAerobics", requestCall);
    }

    private void sureToAdd() {
        Call<String> requestCall = HomeDataManager.addAerobicsRecord(sportId, exerciseTime, finishCalorieString, UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
                setResult(RESULT_OK);
                finish();
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }

        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("addAerobicsRecord", requestCall);
    }

    /**
     * 选择运动类型
     */
    private void chooseTypeWindow(List<String> stringList) {
        PickerViewUtils.showChooseSinglePicker(getPageContext(), "选择运动", stringList, object -> {
            sportId = sportList.get(Integer.parseInt(String.valueOf(object))).getId();
            calorieString = sportList.get(Integer.parseInt(String.valueOf(object))).getCalorie();
            typeTv.setText(sportList.get(Integer.parseInt(String.valueOf(object))).getSportName());
            if (TextUtils.equals(sportList.get(Integer.parseInt(String.valueOf(object))).getSportName(), "太极拳")) {
                taijiLi.setVisibility(View.VISIBLE);
                tiaoshengLl.setVisibility(View.GONE);
            } else if (TextUtils.equals(sportList.get(Integer.parseInt(String.valueOf(object))).getSportName(), "跳绳")) {
                taijiLi.setVisibility(View.GONE);
                tiaoshengLl.setVisibility(View.VISIBLE);
            } else {
                taijiLi.setVisibility(View.GONE);
                tiaoshengLl.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_record_add, null);
        timeTv = view.findViewById(R.id.tv_exercise_record_add_time);
        fireTv = view.findViewById(R.id.tv_exercise_record_add_fire);
        typeTv = view.findViewById(R.id.tv_exercise_record_add_type);
        timeChooseTv = view.findViewById(R.id.tv_exercise_record_add_time_choose);
        timeRv = view.findViewById(R.id.rv_exercise_record_add_time);
        tiaoshengLl = view.findViewById(R.id.ll_exercise_record_add_tiaosheng);
        taijiLi = view.findViewById(R.id.ll_exercise_record_add_taiji);
        sureTextView = view.findViewById(R.id.tv_exercise_record_add_sure);
        containerView().addView(view);
    }


}
