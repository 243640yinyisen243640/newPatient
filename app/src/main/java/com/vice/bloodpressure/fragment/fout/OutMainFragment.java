package com.vice.bloodpressure.fragment.fout;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aout.OutDoctorEducationListActivity;
import com.vice.bloodpressure.activity.aout.OutHospitalListActivity;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;

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
    }

    private void initListener() {
        bandTextView.setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), OutHospitalListActivity.class));
        });
        educationTextView.setOnClickListener(v -> {
            //            DialogUtils.showOperDialog(getPageContext(), "", "请先绑定医生", "取消", "确定", true, (dialog, which) -> {
            //                dialog.dismiss();
            //                if (HHSoftDialogActionEnum.NEGATIVE == which) {
            //                    dialog.dismiss();
            //                } else {
            //                    startActivity(new Intent(getPageContext(), OutHospitalListActivity.class));
            //                }
            //            });

            startActivity(new Intent(getPageContext(), OutDoctorEducationListActivity.class));
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

    @Override
    protected void onPageLoad() {

    }
}
