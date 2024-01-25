package com.vice.bloodpressure.fragment.fhome.education;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationClassifyRightAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationClassifySecondFragment extends UIBaseListRecycleViewFragment<EducationInfo> {

    private String typeId;


    public static EducationClassifySecondFragment newInstance(String typeId) {
        Bundle bundle = new Bundle();
        bundle.putString("typeId", typeId);
        EducationClassifySecondFragment fragment = new EducationClassifySecondFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        typeId = getArguments().getString("typeId");
        topViewManager().topView().removeAllViews();
        noDataText("暂时没有内容，换个关键字试试吧");
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(getPageContext(), R.color.text_white));
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        loadViewManager().setOnClickListener(LoadStatus.NODATA, view -> loadViewManager().changeLoadState(LoadStatus.LOADING));


    }

    @Override
    protected void getListData(CallBack callBack) {
//        Call<String> requestCall = HomeDataManager.teachSeriesList(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ARCHIVES_ID), typeId, "", (call, response) -> {
//            if ("0000".equals(response.code)) {
//                callBack.callBack(response.object);
//            } else {
//                callBack.callBack(null);
//            }
//        }, (call, t) -> {
//            callBack.callBack(null);
//        });
//
//        addRequestCallToMap("teachSeriesList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<EducationInfo> list) {
        return new EducationClassifyRightAdapter(getPageContext(), list, (position, view) -> {
//            Intent intent = new Intent(getPageContext(), EducationInteCatalogueListActivity.class);
//            startActivity(intent);
        });
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }


    /**
     * 切换类别刷新数据
     */
    public void refresh() {
        setPageIndex(1);
        onPageLoad();
    }

    @Override
    protected boolean isLoadMore() {
        return false;
    }

    @Override
    protected boolean isRefresh() {
        return false;
    }
}
