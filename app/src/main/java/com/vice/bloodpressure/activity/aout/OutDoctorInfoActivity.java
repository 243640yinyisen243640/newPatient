package com.vice.bloodpressure.activity.aout;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.vice.bloodpressure.dialog.HHSoftDialogActionEnum;
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
 * 传参: doctorId 医生id
 * 描述:医生详情
 */
public class OutDoctorInfoActivity extends UIBaseLoadActivity {
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

    /**
     * 医生id
     */
    private String doctorId;
    /**
     * 医生详情的
     */
    private DoctorInfo doctorInfoOther;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("医生详情");
        doctorId = getIntent().getStringExtra("doctorId");
        initView();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initlistener() {
        //如果我已经绑定这个医生了，就要解绑这个医生

        breakTextView.setOnClickListener(v -> {
            if (doctorInfoOther.isBindExternal()) {
                DialogUtils.showOperDialog(getPageContext(), "", "确认解绑该医生吗？", "我在想想", "确定", (dialog, which) -> {
                    dialog.dismiss();
                    if (HHSoftDialogActionEnum.POSITIVE == which) {
                        unBindDoctor();
                    }
                });
            } else {
                //如果没有绑定这个医生，那就在看看我有绑定医生嘛，我如果没有绑定医生，那就去绑定，如果绑定过了这个按钮应该隐藏的
                if (TextUtils.isEmpty(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID, ""))) {
                    DialogUtils.showOperDialog(getPageContext(), "", "确认绑定该医生吗？", "我在想想", "确定", (dialog, which) -> {
                        dialog.dismiss();
                        if (HHSoftDialogActionEnum.POSITIVE == which) {
                            bindDoctor();
                        }
                    });
                }

            }
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
                onPageLoad();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("unBindDoctor", requestCall);
    }

    private void bindDoctor() {
        Call<String> requestCall = OutDataManager.bindDoctor(UserInfoUtils.getArchivesId(getPageContext()), doctorId, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                SharedPreferencesUtils.saveInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID, doctorId);
                onPageLoad();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("bindDoctor", requestCall);
    }


    @Override
    protected void onPageLoad() {
        Call<String> requestCall = OutDataManager.getDeptDoctorInfo(doctorId, UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                doctorInfoOther = (DoctorInfo) response.object;
                initlistener();
                bindData(doctorInfoOther);
                //true已绑定/false未绑定  我有没有绑定这个医生
                //先去判断我好这个医生的关系，如果bindExternal 这个字段是true 就是说我已经绑定了这个医生，我可以解绑，
                if (doctorInfoOther.isBindExternal()) {
                    breakTextView.setVisibility(View.VISIBLE);
                    breakTextView.setText("解除绑定");
                } else {
                    //如果我没有绑定这个医生,我的医生id不空的话，就说明，我已经绑定过医生了，不能在绑定了
                    if (!TextUtils.isEmpty(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID, ""))) {
                        breakTextView.setVisibility(View.GONE);
                    } else {
                        //
                        breakTextView.setText("绑定医生");
                        breakTextView.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getDeptDoctorInfo", requestCall);
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

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_my_doctor, null);
        headImageView = view.findViewById(R.id.iv_user_my_doctor_head);
        nameTextView = view.findViewById(R.id.tv_user_my_doctor_name);
        postTextView = view.findViewById(R.id.tv_user_my_doctor_post);
        hospitalTextView = view.findViewById(R.id.tv_user_my_doctor_hos);
        introduceTextView = view.findViewById(R.id.tv_user_my_doctor_introduce);
        introduceLinearLayout = view.findViewById(R.id.ll_user_my_doctor_introduce);
        breakTextView = view.findViewById(R.id.tv_user_my_doctor_break);
        containerView().addView(view);
    }

}
