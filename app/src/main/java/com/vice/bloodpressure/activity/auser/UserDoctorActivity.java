package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.OutDataManager;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.dialog.XySoftDialogActionEnum;
import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:我的医生
 */
public class UserDoctorActivity extends UIBaseLoadActivity {
    /**
     * 头像
     */
    private ImageView headImageView;
    /**
     * 姓名
     */
    private TextView nameTextView;
    /**
     * 科室
     */
    private TextView postTextView;
    /**
     * 医院
     */
    private TextView hospitalTextView;
    /**
     * 简介
     */
    private TextView introduceTextView;
    private LinearLayout introduceLinearLayout;

    private TextView breakTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("我的医生");
        initView();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initlistener() {
        breakTextView.setOnClickListener(v -> {
            DialogUtils.showOperDialog(getPageContext(), "", "确认解绑该医生吗？", "我在想想", "确定", (dialog, which) -> {
                dialog.dismiss();
                if (XySoftDialogActionEnum.POSITIVE == which) {
                    unBindDoctor();
                }
            });
        });
    }

    /**
     * 解绑医生
     */
    private void unBindDoctor() {
        Call<String> requestCall = OutDataManager.unBindDoctor(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                SharedPreferencesUtils.saveInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID, "");
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("unBindDoctor", requestCall);
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_my_doctor, null);
        headImageView = view.findViewById(R.id.iv_user_my_doctor_head);
        nameTextView = view.findViewById(R.id.tv_user_my_doctor_name);
        postTextView = view.findViewById(R.id.tv_user_my_doctor_post);
        hospitalTextView = view.findViewById(R.id.tv_user_my_doctor_hos);
        introduceTextView = view.findViewById(R.id.tv_user_my_doctor_introduce);
        introduceLinearLayout = view.findViewById(R.id.ll_user_my_doctor_introduce);
        breakTextView = view.findViewById(R.id.tv_user_my_doctor_break);
        breakTextView.setText("解除绑定");
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = UserDataManager.getSelectDoctorInfo(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                DoctorInfo doctorInfo = (DoctorInfo) response.object;
                initlistener();
                bindData(doctorInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getSelectDoctorInfo", requestCall);
    }


    private void bindData(DoctorInfo doctorInfo) {
        XyImageUtils.loadCircleImage(getPageContext(), R.drawable.out_doctor_default_head_img, doctorInfo.getAvatar(), headImageView);
        nameTextView.setText(doctorInfo.getDoctorName());
        postTextView.setText(doctorInfo.getDeptName());
        hospitalTextView.setText(doctorInfo.getHospitalName());
        if (doctorInfo.getProfile() == null) {
            introduceLinearLayout.setVisibility(View.GONE);
        } else {
            introduceLinearLayout.setVisibility(View.VISIBLE);
            introduceTextView.setText(doctorInfo.getProfile());
        }
    }


}
