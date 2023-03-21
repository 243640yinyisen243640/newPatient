package com.vice.bloodpressure.fragment.fout;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.adapter.out.OutOfficeDoctorRightAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.model.HospitalInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class OutOfficeDoctorListFragment extends UIBaseListRecycleViewFragment<HospitalInfo> {

    public static OutOfficeDoctorListFragment newInstance(String firstClassID) {
        Bundle bundle = new Bundle();
        bundle.putString("firstClassID", firstClassID);
        OutOfficeDoctorListFragment fragment = new OutOfficeDoctorListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getListData(CallBack callBack) {
        List<HospitalInfo> list = new ArrayList<>();
        list.add(new HospitalInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "张蕙兰", "副院长", "中华医学会血液学分会青年委员，省医学会血液学分会秘书。199...年毕..."));
        list.add(new HospitalInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "冯超杰", "主治医师", "中华医学会血液学分会青年委员，省医学会血液学分会秘书。199...年毕..."));
        list.add(new HospitalInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "刘文文", "主任医师", "中华医学会血液学分会青年委员，省医学会血液学分会秘书。199...年毕..."));
        callBack.callBack(list);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<HospitalInfo> list) {
        return new OutOfficeDoctorRightAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {

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
