package com.vice.bloodpressure.activity.ahome.aexercise;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.math.BigDecimal;
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

    private String exerciseTime;

    private List<BaseLocalDataInfo> sportList;

    private String sportId = "-1";

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
                exerciseTime = result;
                timeChooseTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                timeTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));

                fireTv.setText("");
            }

            @Override
            public void onScrollResult(String result) {
                timeChooseTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                timeTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });
    }

    private void initListener() {
        typeTv.setOnClickListener(v -> {
            chooseTypeWindow();
        });
        sureTextView.setOnClickListener(v -> {
            sureToAdd();
        });
    }

    private void sureToAdd() {
        Call<String> requestCall = HomeDataManager.addAerobicsRecord(sportId, "", "", UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);

        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("addAerobicsRecord", requestCall);
    }

    /**
     * 选择运动类型
     */
    private void chooseTypeWindow() {
        Call<String> requestCall = HomeDataManager.getSportAerobics((call, response) -> {
            if ("100".equals(response.code)) {
                sportList = (List<BaseLocalDataInfo>) response.object;

            }
        }, (Call, t) -> {

        });


        PickerViewUtils.showChooseSinglePicker(getPageContext(), "选择运动", sportList, object ->
        {
            sportId = sportList.get(Integer.parseInt(String.valueOf(object))).getId();
            typeTv.setText(sportList.get(Integer.parseInt(String.valueOf(object))).getName());
            if (TextUtils.equals(sportList.get(Integer.parseInt(String.valueOf(object))).getName(), "太极拳")) {
                taijiLi.setVisibility(View.VISIBLE);
                tiaoshengLl.setVisibility(View.GONE);
            } else if (TextUtils.equals(sportList.get(Integer.parseInt(String.valueOf(object))).getName(), "跳绳")) {
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
