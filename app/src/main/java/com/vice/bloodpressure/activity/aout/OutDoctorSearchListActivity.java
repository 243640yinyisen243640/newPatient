package com.vice.bloodpressure.activity.aout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.auser.UserDoctorActivity;
import com.vice.bloodpressure.adapter.out.OutOfficeDoctorSearchAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.datamanager.OutDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:医生搜索列表
 */
public class OutDoctorSearchListActivity extends UIBaseListRecycleViewActivity<DoctorInfo> {
    private String hospitalName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

    }


    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_education_intelligence_search, null);
        ImageView backImageView = topView.findViewById(R.id.iv_education_study_search_back);
        EditText contentEditText = topView.findViewById(R.id.et_education_class_search);
        TextView searchTextView = topView.findViewById(R.id.tv_education_class_search_sure);
        backImageView.setOnClickListener(v -> finish());
        searchTextView.setOnClickListener(v -> {
            if (TextUtils.isEmpty(contentEditText.getText().toString().trim())) {
                ToastUtils.getInstance().showToast(getPageContext(), "请输入关键字");
                return;
            }
            hospitalName = contentEditText.getText().toString().trim();
            setPageIndex(1);
            onPageLoad();
        });
        return topView;
    }

    @Override
    protected void getListData(CallBack callBack) {
        String deptId = getIntent().getStringExtra("deptId");
        Call<String> requestCall = OutDataManager.getDeptDoctorList(hospitalName, "3", deptId, (call, response) -> {
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
        return new OutOfficeDoctorSearchAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.ll_office_doctor_click:
                        Intent intent = new Intent(getPageContext(), UserDoctorActivity.class);
                        intent.putExtra("type", "2");
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


}
