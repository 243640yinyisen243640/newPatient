package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.SerciveDataShowAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.AdvertInfo;
import com.vice.bloodpressure.view.HHAtMostGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ServiceHealthyDataActivity extends UIBaseActivity {
    private HHAtMostGridView normalGridView;
    private HHAtMostGridView otherGridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("健康数据");
        initView();
        initValues();
    }

    private void initValues() {
        List<AdvertInfo> normalInfoList = new ArrayList<>();
        normalInfoList.add(new AdvertInfo(R.drawable.healthy_data_xuetang, "血糖"));
        normalInfoList.add(new AdvertInfo(R.drawable.healthy_data_tanghua, "糖化血红蛋白"));
        normalInfoList.add(new AdvertInfo(R.drawable.healthy_data_xueya, "血压"));
        normalInfoList.add(new AdvertInfo(R.drawable.healthy_data_bmi, "BMI"));
        normalInfoList.add(new AdvertInfo(R.drawable.healthy_data_check, "检验检查"));
        normalInfoList.add(new AdvertInfo(R.drawable.healthy_data_tiwen, "体温"));
        normalInfoList.add(new AdvertInfo(R.drawable.healthy_data_xueyang, "血氧"));
        SerciveDataShowAdapter adapter = new SerciveDataShowAdapter(getPageContext(), normalInfoList, "2", object -> {
            switch (Integer.parseInt(String.valueOf(object))) {
                case 0:
                    startActivity(new Intent(getPageContext(), ServiceBloodListActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getPageContext(), ServiceBloodScleroproteinListActivity.class));
                    break;
                case 2:

                    startActivity(new Intent(getPageContext(), ServicePressureListActivity.class));
                    break;
                case 3:

                    startActivity(new Intent(getPageContext(), ServiceBmiListActivity.class));
                    break;
                case 4:

                    startActivity(new Intent(getPageContext(), ServiceCheckListActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(getPageContext(), ServiceTemperatureListActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(getPageContext(), ServiceBloodOxygenListActivity.class));
                    break;
                default:
                    break;
            }
        });
        normalGridView.setAdapter(adapter);


        List<AdvertInfo> otherInfoList = new ArrayList<>();
        otherInfoList.add(new AdvertInfo(R.drawable.healthy_data_yundong, "运动"));
        otherInfoList.add(new AdvertInfo(R.drawable.healthy_data_yinshi, "饮食"));
        otherInfoList.add(new AdvertInfo(R.drawable.healthy_data_yongyao, "用药"));
        otherInfoList.add(new AdvertInfo(0, ""));
        SerciveDataShowAdapter otherAdapter = new SerciveDataShowAdapter(getPageContext(), otherInfoList, "2", object -> {
            switch (Integer.parseInt(String.valueOf(object))) {
                case 0:
                    startActivity(new Intent(getPageContext(), ServiceBloodExerciseListActivity.class));
                    break;
                case 1:

                    break;
                case 2:
                    startActivity(new Intent(getPageContext(), ServiceMedicineListActivity.class));
                    break;

                default:
                    break;
            }
        });
        otherGridView.setAdapter(otherAdapter);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_healthy_data, null);
        normalGridView = view.findViewById(R.id.gv_healthy_data_normal);
        otherGridView = view.findViewById(R.id.gv_healthy_data_other);
        containerView().addView(view);
    }
}
