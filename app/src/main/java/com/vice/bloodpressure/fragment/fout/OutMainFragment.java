package com.vice.bloodpressure.fragment.fout;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aout.OutDoctorEducationListActivity;
import com.vice.bloodpressure.activity.aout.OutDoctorInfoActivity;
import com.vice.bloodpressure.activity.aout.OutHospitalListActivity;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.datamanager.OutDataManager;
import com.vice.bloodpressure.dialog.HHSoftDialogActionEnum;
import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class OutMainFragment extends UIBaseLoadFragment {
    private ImageView unheadImageView;
    private LinearLayout unbandLinearLayout;
    private LinearLayout bandLinearLayout;
    private ImageView headImageView;
    private TextView bandTextView;
    private TextView nameTextView;
    private TextView postTextView;
    private TextView hospitalTextView;
    private TextView educationTextView;

    private DoctorInfo doctorInfoOther;

    @Override
    protected void onCreate() {
        topViewManager().titleTextView().setText("院外管理");
        topViewManager().backTextView().setVisibility(View.GONE);
        topViewManager().topView().setBackgroundColor(getResources().getColor(R.color.main_base_color));
        topViewManager().statusBarView().setBackgroundColor(getResources().getColor(R.color.main_base_color));
        topViewManager().titleTextView().setTextColor(getResources().getColor(R.color.text_white));
        topViewManager().lineViewVisibility(View.GONE);
        initView();
        initListener();
        if (TextUtils.isEmpty(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID))) {
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            initValues();
        } else {
            loadViewManager().changeLoadState(LoadStatus.LOADING);
        }

    }

    @Override
    protected void onPageLoad() {
        if (TextUtils.isEmpty(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID))) {
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            initValues();
        } else {
            Call<String> requestCall = OutDataManager.getDeptDoctorInfo(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID), UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
                if ("0000".equals(response.code)) {
                    loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                    doctorInfoOther = (DoctorInfo) response.object;
                    bindData(doctorInfoOther);
                } else {
                    loadViewManager().changeLoadState(LoadStatus.FAILED);
                }
            }, (call, t) -> {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            });
            addRequestCallToMap("getDeptDoctorInfo", requestCall);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        onPageLoad();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onPageLoad();
        }
    }

    private void bindData(DoctorInfo doctorInfoOther) {
        unbandLinearLayout.setVisibility(View.GONE);
        bandLinearLayout.setVisibility(View.VISIBLE);
        XyImageUtils.loadImage(getPageContext(), R.drawable.out_doctor_default_head_img, doctorInfoOther.getAvatar(), headImageView);
        nameTextView.setText(doctorInfoOther.getDoctorName());
        postTextView.setText(doctorInfoOther.getDeptName());
        hospitalTextView.setText(doctorInfoOther.getHospitalName());
        if (doctorInfoOther.isBindExternal()) {
            unbandLinearLayout.setVisibility(View.GONE);
            bandLinearLayout.setVisibility(View.VISIBLE);
        } else {
            unbandLinearLayout.setVisibility(View.VISIBLE);
            bandLinearLayout.setVisibility(View.GONE);
        }
    }

    private void initListener() {
        bandTextView.setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), OutHospitalListActivity.class));
        });
        educationTextView.setOnClickListener(v -> {
            if (TextUtils.isEmpty(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID))) {
                DialogUtils.showOperDialog(getPageContext(), "", "请先绑定医生", "取消", "确定", (dialog, which) -> {
                    dialog.dismiss();
                    if (HHSoftDialogActionEnum.POSITIVE == which) {
                        startActivity(new Intent(getPageContext(), OutHospitalListActivity.class));
                    }
                });
            } else {
                startActivity(new Intent(getPageContext(), OutDoctorEducationListActivity.class));
            }


        });
        bandLinearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), OutDoctorInfoActivity.class);
            intent.putExtra("doctorId", doctorInfoOther.getId());
            startActivity(intent);
        });

    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_out_main, null);
        unbandLinearLayout = view.findViewById(R.id.ll_out_main_doctor_unband);
        unheadImageView = view.findViewById(R.id.iv_out_main_doctor_head);
        bandTextView = view.findViewById(R.id.tv_out_main_doctor_band);

        bandLinearLayout = view.findViewById(R.id.ll_out_main_doctor_band);
        headImageView = view.findViewById(R.id.iv_out_main_doctor_band_head);
        nameTextView = view.findViewById(R.id.tv_out_doctor_name);
        postTextView = view.findViewById(R.id.tv_out_doctor_post);
        hospitalTextView = view.findViewById(R.id.tv_out_doctor_hos);
        educationTextView = view.findViewById(R.id.tv_out_main_doctor_education);
        containerView().addView(view);
    }


    private void initValues() {
        if (TextUtils.isEmpty(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID))) {
            unbandLinearLayout.setVisibility(View.VISIBLE);
            bandLinearLayout.setVisibility(View.GONE);
        } else {
            unbandLinearLayout.setVisibility(View.GONE);
            bandLinearLayout.setVisibility(View.VISIBLE);
        }
    }
}
