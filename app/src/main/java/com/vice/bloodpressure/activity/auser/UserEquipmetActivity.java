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
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.dialog.HHSoftDialogActionEnum;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.modules.zxing.activity.CaptureActivity;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:我的设备
 */
public class UserEquipmetActivity extends UIBaseListRecycleViewActivity<UserInfo> {
    private List<UserInfo> equipmentInfos = new ArrayList<>();

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


    private void initView() {
        //        View view = View.inflate(getPageContext(), R.layout.activity_user_equipment, null);
        //        recyclerView = view.findViewById(R.id.rv_user_equipment);
        //        containerView().addView(view);
    }


    @Override
    protected void getListData(CallBack callBack) {
        equipmentInfos = new ArrayList<>();
        equipmentInfos.add(new UserInfo("微策血糖仪", "2020-10-29", "血糖仪", "http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg"));
        equipmentInfos.add(new UserInfo("小糖医血糖仪", "2020-10-29", "血糖仪", "http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg"));
        equipmentInfos.add(new UserInfo("微策血糖仪", "2020-10-29", "血糖仪", "http://img.wxcha.com/m00/f0/f5/5e3999d5a8d62188ac5ba8ca32e058f.jpg"));

        callBack.callBack(equipmentInfos);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<UserInfo> list) {
        return new UserEquipmentListAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.tv_user_equipment_break:
                        DialogUtils.showOperDialog(getPageContext(), "", "是否解除该设备？", "取消", "确定", (dialog, which) -> {
                            dialog.dismiss();
                            if (HHSoftDialogActionEnum.NEGATIVE == which) {
                                dialog.dismiss();
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

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
