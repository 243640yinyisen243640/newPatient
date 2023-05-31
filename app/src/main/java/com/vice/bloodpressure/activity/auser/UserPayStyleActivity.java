package com.vice.bloodpressure.activity.auser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.user.UserPayStyleAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 类名：
 * 传参：
 * 描述:  支付方式
 * 作者: beauty
 * 创建日期: 2023/3/16 11:30
 */
public class UserPayStyleActivity extends UIBaseActivity {
    private ListView listView;
    private UserPayStyleAdapter adapter;
    private List<BaseLocalDataInfo> list = new ArrayList<>();

    private LinearLayout sureLinearLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intView();
        initValues();
        topViewManager().titleTextView().setText("医疗支付方式");
    }

    private void intView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_pay_style, null);
        listView = view.findViewById(R.id.lv_user_files_investigate);
        sureLinearLayout = view.findViewById(R.id.ll_user_files_sure);
        containerView().addView(view);
        sureLinearLayout.setOnClickListener(v -> {
            //            StringBuilder builder = new StringBuilder();
            //            for (int i = 0; i < list.size(); i++) {
            //                if (list.get(i).isCheck()) {
            //                    builder.append(list.get(i).getId());
            //                    builder.append(",");
            //                }
            //            }
            //            builder.deleteCharAt(builder.length() - 1);
            //            if (builder.length() == 0) {
            //                ToastUtils.getInstance().showToast(getPageContext(), "请选择答案");
            //                return;
            //            }

            sureToAddData(list.get(adapter.getClickPosition()).getId());
        });
    }

    private void sureToAddData(String id) {
        Call<String> requestCall = UserDataManager.editUserFilesInfo(UserInfoUtils.getArchivesId(getPageContext()), "medicalPay", id, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                Intent intent = new Intent();
                intent.putExtra("checkName", list.get(adapter.getClickPosition()).getName());
                setResult(RESULT_OK, intent);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("editUserFilesInfo", requestCall);
    }

    private void initValues() {
        list.add(new BaseLocalDataInfo("社会医疗保险", "1"));
        list.add(new BaseLocalDataInfo("新型农村医疗合作保险", "2"));
        list.add(new BaseLocalDataInfo("商业保险", "3"));
        list.add(new BaseLocalDataInfo("城镇居民医疗保险", "4"));
        list.add(new BaseLocalDataInfo("公费医疗", "5"));
        list.add(new BaseLocalDataInfo("自费医疗", "6"));
        list.add(new BaseLocalDataInfo("其他险", "7"));

        adapter = new UserPayStyleAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            //            if (list.get(position).getText().equals("其他险")) {
            //                for (int i = 0; i < list.size(); i++) {
            //                    list.get(i).setCheck(false);
            //                }
            //                list.get(position).setCheck(true);
            //            } else {
            //                for (int i = 0; i < list.size(); i++) {
            //                    if (list.get(i).getText().equals("其他险")) {
            //                        list.get(i).setCheck(false);
            //                    }
            //                }
            //                list.get(position).setCheck(!list.get(position).isCheck());
            //            }
            adapter.setClickPosition(position);
        });
    }

}
