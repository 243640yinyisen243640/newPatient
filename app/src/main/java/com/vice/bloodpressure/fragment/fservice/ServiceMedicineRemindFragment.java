package com.vice.bloodpressure.fragment.fservice;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aservice.ServiceMedicineRemindAddActivity;
import com.vice.bloodpressure.adapter.service.ServiceMedicineAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgFragment;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.dialog.XySoftDialogActionEnum;
import com.vice.bloodpressure.model.HealthyDataChildInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.List;

import retrofit2.Call;

import static android.app.Activity.RESULT_OK;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:用药提醒
 */
public class ServiceMedicineRemindFragment extends UIBaseListRecycleViewForBgFragment<HealthyDataChildInfo> implements View.OnClickListener {
    private static final int REQUEST_CODE_FOR_FREFRESH = 1;
    private TextView startTimeTextView;
    private TextView endTimeTextView;

    private String startTime = "";
    private String endTime = "";

    @Override
    protected void onCreate() {
        super.onCreate();
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), false));
        mRecyclerView.setLayoutManager(layoutManager);
        setPublicBottom();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_service_top_with_time_only, null);
        startTimeTextView = topView.findViewById(R.id.tv_service_medicine_start_time);
        endTimeTextView = topView.findViewById(R.id.tv_service_medicine_end_time);
        return topView;
    }

    private void initListener() {
        startTimeTextView.setOnClickListener(this);
        endTimeTextView.setOnClickListener(this);
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = ServiceDataManager.getDrugWarnAppList(UserInfoUtils.getArchivesId(getPageContext()), getPageIndex() + "", BaseDataManager.PAGE_SIZE + "", startTime, endTime, (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getMedicineRecordList", requestCall);
    }

    private void setPublicBottom() {
        View view = View.inflate(getPageContext(), R.layout.include_save_bottom, null);
        LinearLayout addLinearLayout = view.findViewById(R.id.ll_service_base_bottom_sure);
        TextView textTextView = view.findViewById(R.id.tv_service_base_bottom_text);
        FrameLayout.LayoutParams f2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textTextView.setText("添加新提醒");
        addLinearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), ServiceMedicineRemindAddActivity.class);
            intent.putExtra("type", "3");
            intent.putExtra("pkId", "");
            startActivityForResult(intent, REQUEST_CODE_FOR_FREFRESH);
        });
        f2.gravity = Gravity.BOTTOM;
        containerView().addView(view, f2);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<HealthyDataChildInfo> list) {
        return new ServiceMedicineAdapter(getPageContext(), list, "2", (position, view) -> {
            Intent intent;
            switch (view.getId()) {
                case R.id.tv_item_service_medicine_delete:
                    DialogUtils.showOperDialog(getPageContext(), "", "确定要删除吗？", "取消", "确定", (dialog, which) -> {
                        dialog.dismiss();
                        if (XySoftDialogActionEnum.POSITIVE == which) {
                            deleteData(position);
                        }
                    });
                    break;
                case R.id.tv_item_service_medicine_edit:
                    intent = new Intent(getPageContext(), ServiceMedicineRemindAddActivity.class);
                    intent.putExtra("type", "1");
                    intent.putExtra("pkId", getPageListData().get(position).getPkId());
                    startActivity(intent);
                    break;
                case R.id.tv_item_service_medicine_look:
                    intent = new Intent(getPageContext(), ServiceMedicineRemindAddActivity.class);
                    intent.putExtra("type", "2");
                    intent.putExtra("pkId", getPageListData().get(position).getPkId());
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        });
    }

    private void deleteData(int position) {
        Call<String> requestCall = ServiceDataManager.drugWarnAppDelete(getPageListData().get(position).getPkId(), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setPageIndex(1);
                onPageLoad();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("drugWarnAppDelete", requestCall);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_medicine_start_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        startTime = String.valueOf(object);
                        startTimeTextView.setText(object.toString());
                        if (!TextUtils.isEmpty(endTime)) {
                            setPageIndex(1);
                            onPageLoad();
                        }
                    }
                });
                break;
            case R.id.tv_service_medicine_end_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        if (XyTimeUtils.compareTwoTime(startTime, object.toString())) {
                            endTime = object.toString();
                            endTimeTextView.setText(object.toString());
                            setPageIndex(1);
                            onPageLoad();
                        } else {
                            ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_FOR_FREFRESH) {
                setPageIndex(1);
                onPageLoad();
            }
        }
    }
}
