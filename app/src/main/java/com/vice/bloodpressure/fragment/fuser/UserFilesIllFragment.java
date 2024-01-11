package com.vice.bloodpressure.fragment.fuser;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.auser.UserIllOtherActivity;
import com.vice.bloodpressure.activity.auser.UserIllPlusActivity;
import com.vice.bloodpressure.adapter.user.UserFilesPlusAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.NoScrollListView;

import retrofit2.Call;

import static android.app.Activity.RESULT_OK;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserFilesIllFragment extends UIBaseLoadFragment implements View.OnClickListener {
    private static final int REQUEST_CODE_FOR_ILL_REFRESH = 1;
    /**
     * 主要诊断
     */
    private TextView importantAddTextView;
    private NoScrollListView importantListView;
    /**
     * 其他诊断
     */
    private TextView otherAddTextView;
    private NoScrollListView otherListView;
    /**
     * 合并症
     */
    private LinearLayout plusLinearLayout;
    private TextView plusAddTextView;
    private NoScrollListView plusListView;

    private UserInfo userInfo;


    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        importantAddTextView.setOnClickListener(this);
        plusAddTextView.setOnClickListener(this);
        otherAddTextView.setOnClickListener(this);
    }


    @Override
    protected void onPageLoad() {
        Call<String> requestCall = UserDataManager.getUserFilesInfo(UserInfoUtils.getArchivesId(getPageContext()), "3", (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                userInfo = (UserInfo) response.object;
                initListener();
                bindData(userInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getSelectDoctorInfo", requestCall);
    }

    private void bindData(UserInfo userInfo) {
        //主要诊断
        if (userInfo.getMainDiagnosis() != null && userInfo.getMainDiagnosis().size() > 0) {
            UserFilesPlusAdapter importantAdapter = new UserFilesPlusAdapter(getPageContext(), userInfo.getMainDiagnosis(), "2", (position, view) -> {
                switch (view.getId()) {
                    case R.id.ll_disease_click:
                        Intent intent = new Intent(getPageContext(), UserIllOtherActivity.class);
                        intent.putExtra("diagnosticType", "1");
                        intent.putExtra("isAdd", "2");
                        intent.putExtra("diseaseType", userInfo.getMainDiagnosis().get(position).getDiseaseType());
                        intent.putExtra("diagnosticType", userInfo.getMainDiagnosis().get(position).getDiagnosticType());
                        startActivityForResult(intent, REQUEST_CODE_FOR_ILL_REFRESH);
                        break;
                    default:
                        break;
                }
            });
            importantListView.setAdapter(importantAdapter);
        }
        //其他诊断
        if (userInfo.getOtherDiagnosis() != null && userInfo.getOtherDiagnosis().size() > 0) {

            UserFilesPlusAdapter otherAdapter = new UserFilesPlusAdapter(getPageContext(), userInfo.getOtherDiagnosis(), "3", (position, view) -> {
                switch (view.getId()) {
                    case R.id.ll_disease_click:
                        Intent intent = new Intent(getPageContext(), UserIllOtherActivity.class);
                        intent.putExtra("isAdd", "2");
                        intent.putExtra("diagnosticType", "2");
                        intent.putExtra("diseaseType", userInfo.getOtherDiagnosis().get(position).getDiseaseType());
                        intent.putExtra("diagnosticType", userInfo.getOtherDiagnosis().get(position).getDiagnosticType());
                        startActivityForResult(intent, REQUEST_CODE_FOR_ILL_REFRESH);
                        break;
                    default:
                        break;
                }
            });
            otherListView.setAdapter(otherAdapter);
        }
        //合并症
        if ("0".equals(userInfo.getIsDiabetesExists())) {
            plusLinearLayout.setVisibility(View.VISIBLE);
            if (userInfo.getComplication() != null && userInfo.getComplication().size() > 0) {
                UserFilesPlusAdapter plusAdapter = new UserFilesPlusAdapter(getPageContext(), userInfo.getComplication(), "1", (position, view) -> {
                    switch (view.getId()) {
                        case R.id.ll_disease_click:
                            Intent intent = new Intent(getPageContext(), UserIllPlusActivity.class);
                            intent.putExtra("isAdd", "2");
                            intent.putExtra("diseaseType", userInfo.getComplication().get(position).getDiseaseType());
                            intent.putExtra("diagnosticType", userInfo.getComplication().get(position).getDiagnosticType());
                            intent.putExtra("complicationType", userInfo.getComplication().get(position).getComplicationType());
                            startActivityForResult(intent, REQUEST_CODE_FOR_ILL_REFRESH);
                            break;
                        default:
                            break;
                    }
                });
                plusListView.setAdapter(plusAdapter);
            }

        } else {
            plusLinearLayout.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_user_files_ill_important_add:
                intent = new Intent(getPageContext(), UserIllOtherActivity.class);
                intent.putExtra("isAdd", "1");
                intent.putExtra("diagnosticType", "1");
                startActivityForResult(intent, REQUEST_CODE_FOR_ILL_REFRESH);
                break;
            case R.id.tv_user_files_ill_other_add:
                intent = new Intent(getPageContext(), UserIllOtherActivity.class);
                intent.putExtra("isAdd", "1");
                intent.putExtra("diagnosticType", "2");
                startActivityForResult(intent, REQUEST_CODE_FOR_ILL_REFRESH);
                break;
            case R.id.tv_user_files_ill_plus_add:
                intent = new Intent(getPageContext(), UserIllPlusActivity.class);
                intent.putExtra("isAdd", "1");
                startActivityForResult(intent, REQUEST_CODE_FOR_ILL_REFRESH);
                break;

            default:
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FOR_ILL_REFRESH:
                    onPageLoad();
                    break;

                default:
                    break;
            }
        }
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_ill, null);
        importantAddTextView = view.findViewById(R.id.tv_user_files_ill_important_add);
        importantListView = view.findViewById(R.id.lv_user_files_ill_important);

        otherAddTextView = view.findViewById(R.id.tv_user_files_ill_other_add);
        otherListView = view.findViewById(R.id.lv_user_files_ill_other);

        plusLinearLayout = view.findViewById(R.id.ll_user_files_ill_plus);
        plusAddTextView = view.findViewById(R.id.tv_user_files_ill_plus_add);
        plusListView = view.findViewById(R.id.lv_user_files_ill_plus);


        containerView().addView(view);
    }
}
