package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
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
 * 描述:运动添加
 */
public class ServiceExerciseAddActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView timeTextView;
    private TextView typeTextView;
    private TextView valueTextView;
    private RulerView rulerView;
    private LinearLayout sureLinearLayout;
    /**
     * 运动时间
     */
    private String exerciseValue = "";
    /**
     * 运动类型
     */
    private String exerciseType = "";
    /**
     * 添加时间
     */
    private String addTime = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("运动数据");
        initView();
        initListener();

    }

    private void initListener() {
        timeTextView.setOnClickListener(this);
        typeTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
        rulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                //                valueTextView.setText(result);
                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                exerciseValue = valueTextView.getText().toString().trim();
            }

            @Override
            public void onScrollResult(String result) {
                //                valueTextView.setText(result);
                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                exerciseValue = valueTextView.getText().toString().trim();

            }
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_exercise_add, null);
        typeTextView = view.findViewById(R.id.tv_service_exercise_add_type);
        timeTextView = view.findViewById(R.id.tv_service_exercise_add_time);
        valueTextView = view.findViewById(R.id.tv_service_exercise_add_value);
        rulerView = view.findViewById(R.id.rv_service_exercise_add);
        sureLinearLayout = view.findViewById(R.id.ll_service_exercise_add_sure);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_exercise_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, true}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, object -> {
                    addTime = object.toString();
                    timeTextView.setText(object.toString());
                });
                break;
            case R.id.tv_service_exercise_add_type:

                List<String> exerciseList = new ArrayList<>();
                exerciseList.add("步行/去工作上学");
                exerciseList.add("散步走");
                exerciseList.add("自然走");
                exerciseList.add("健步走");
                exerciseList.add("慢跑");
                exerciseList.add("快跑");
                exerciseList.add("太极拳");
                exerciseList.add("自行车");
                exerciseList.add("下楼梯");
                exerciseList.add("做家务");
                exerciseList.add("舞蹈");
                exerciseList.add("瑜伽");
                exerciseList.add("游泳");
                exerciseList.add("羽毛球");
                exerciseList.add("乒乓球");
                exerciseList.add("足球");
                exerciseList.add("篮球");
                exerciseList.add("排球");
                exerciseList.add("网球");

                PickerViewUtils.showChooseSinglePicker(getPageContext(), "运动类型", exerciseList, object -> {
                            typeTextView.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
                            exerciseType = exerciseList.get(Integer.parseInt(String.valueOf(object)));
                        }
                );
                break;
            case R.id.ll_service_exercise_add_sure:
                sureToAddData();
                break;
            default:
                break;
        }
    }


    private void sureToAddData() {
        if (TextUtils.isEmpty(addTime)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择检测时间");
            return;
        }

        Call<String> requestCall = ServiceDataManager.saveMonitorSport(UserInfoUtils.getArchivesId(getPageContext()), "2", addTime, exerciseType, exerciseValue, (call, response) -> {
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
        addRequestCallToMap("saveMonitorSport", requestCall);
    }
}
