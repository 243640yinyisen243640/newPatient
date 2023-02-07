package com.vice.bloodpressure.ui.activity.service.education;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.HHSoftLoadStatus;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationVideoInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationVideoListActivity extends UIBaseListRecycleViewActivity<EducationVideoInfo> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 3);
        //        mRecyclerView.addItemDecoration(new UserCenterVideoGridDivider(getPageContext(), HHSoftDensityUtils.dip2px(getPageContext(), 3), R.color.white));
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 6), false));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(HHSoftLoadStatus.LOADING);
    }

    @Override
    protected void getListData(CallBack callBack) {

    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<EducationVideoInfo> list) {
        return null;
    }

    @Override
    protected int getPageSize() {
        return 0;
    }
}
