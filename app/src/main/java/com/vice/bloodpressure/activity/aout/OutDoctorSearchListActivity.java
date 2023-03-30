package com.vice.bloodpressure.activity.aout;

import android.content.Intent;
import android.os.Bundle;
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
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.HospitalInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:医生搜索列表
 */
public class OutDoctorSearchListActivity extends UIBaseListRecycleViewActivity<HospitalInfo> implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
//        getPageListView().setBackgroundColor(getResources().getColor(R.color.background));
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
        String content = contentEditText.getText().toString().trim();
        searchTextView.setOnClickListener(v -> {

        });
        return topView;
    }

    @Override
    protected void getListData(CallBack callBack) {
        List<HospitalInfo> list = new ArrayList<>();
        list.add(new HospitalInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "张蕙兰", "副院长", "中华医学会血液学分会青年委员，省医学会血液学分会秘书。199...年毕...", "内分泌科", ""));
        list.add(new HospitalInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "冯超杰", "主治医师", "中华医学会血液学分会青年委员，省医学会血液学分会秘书。199...年毕...", "高血压教育门诊", ""));
        list.add(new HospitalInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "刘文文", "主任医师", "暂无", "泌尿科", ""));
        callBack.callBack(list);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<HospitalInfo> list) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_warning_back:
                finish();
                break;
            case R.id.ll_out_hos_province:

                break;
            case R.id.ll_out_hos_city:


                break;

            default:
                break;
        }
    }

}
