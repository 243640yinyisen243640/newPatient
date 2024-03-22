package com.vice.bloodpressure.fragment.fout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aout.OutDoctorInfoActivity;
import com.vice.bloodpressure.adapter.out.OutOfficeDoctorRightAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.datamanager.OutDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class OutOfficeDoctorListFragment extends UIBaseListRecycleViewFragment<DoctorInfo> {

    public static OutOfficeDoctorListFragment newInstance(String deptId) {
        Bundle bundle = new Bundle();
        bundle.putString("deptId", deptId);
        OutOfficeDoctorListFragment fragment = new OutOfficeDoctorListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 科室id
     */
    private String deptId = "";

    @Override
    protected void onCreate() {
        super.onCreate();
        deptId = getArguments().getString("deptId");
        getPageListView().setBackgroundColor(getResources().getColor(R.color.text_white));
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = OutDataManager.getDeptDoctorList("", "", deptId, (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getDeptDoctorList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<DoctorInfo> list) {
        return new OutOfficeDoctorRightAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.ll_office_doctor_click:
                        Intent intent = new Intent(getPageContext(), OutDoctorInfoActivity.class);
                        intent.putExtra("doctorId", getPageListData().get(position).getId());
                        startActivity(intent);
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


    public void setDeptIdRefresh(String deptId) {
        this.deptId = deptId;
        setPageIndex(1);
        onPageLoad();
    }
}
