package com.vice.bloodpressure.activity.auser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.UserEquipmentListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.dialog.HHSoftDialogActionEnum;
import com.vice.bloodpressure.model.EquipmetInfo;
import com.vice.bloodpressure.modules.zxing.activity.CaptureActivity;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:我的设备
 */
public class UserEquipmetActivity extends UIBaseListRecycleViewActivity<EquipmetInfo> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("我的设备");
        topViewManager().moreTextView().setText("新增");
        topViewManager().moreTextView().setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), CaptureActivity.class);
            intent.putExtra("isEquipmet", 1);
            startActivity(intent);
        });
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }


    @Override
    protected boolean isRefresh() {
        return false;
    }

    @Override
    protected boolean isLoadMore() {
        return false;
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = UserDataManager.getUserEquipmetList(UserInfoUtils.getArchivesId(getPageContext()), "", "", (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getUserEquipmetList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<EquipmetInfo> list) {
        return new UserEquipmentListAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.tv_user_equipment_break:
                        DialogUtils.showOperDialog(getPageContext(), "", "是否解除该设备？", "取消", "确定", (dialog, which) -> {
                            dialog.dismiss();
                            if (HHSoftDialogActionEnum.POSITIVE == which) {
                                breakEquipmet(getPageListData().get(position).getPkId(), getPageListData().get(position).getDeviceId());
                            }
                        });
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        });
    }

    private void breakEquipmet(String pkid, String deviceId) {
        Call<String> requestCall = UserDataManager.userUnbindDevice(pkid, deviceId, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                onPageLoad();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("userUnbindDevice", requestCall);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
