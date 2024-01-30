package com.vice.bloodpressure.fragment.fhome.exercise;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.adapter.home.ExerciseFlexibilityListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.ExerciseInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ExerciseFlexibilityFragment extends UIBaseListRecycleViewFragment<ExerciseInfo> {

    public static ExerciseFlexibilityFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        ExerciseFlexibilityFragment fragment = new ExerciseFlexibilityFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void getListData(CallBack callBack) {
        String type = getArguments().getString("type");
        Call<String> requestCall = HomeDataManager.getSportRecord(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ARCHIVES_ID), type, (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getSportRecord", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<ExerciseInfo> list) {
        return new ExerciseFlexibilityListAdapter(getPageContext(), list);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }


    @Override
    protected boolean isRefresh() {
        return false;
    }

    @Override
    protected boolean isLoadMore() {
        return false;
    }
}
