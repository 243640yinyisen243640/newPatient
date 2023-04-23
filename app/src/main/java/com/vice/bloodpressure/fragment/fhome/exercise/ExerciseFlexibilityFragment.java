package com.vice.bloodpressure.fragment.fhome.exercise;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.adapter.home.ExerciseResistanceListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.ExerciseChildInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ExerciseFlexibilityFragment extends UIBaseListRecycleViewFragment<ExerciseChildInfo> {
    private List<ExerciseChildInfo> listText = new ArrayList<>();

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
        listText.add(new ExerciseChildInfo("深蹲", "10", "1260", "1"));
        listText.add(new ExerciseChildInfo("深蹲", "30", "1260", "2"));

        callBack.callBack(listText);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<ExerciseChildInfo> list) {
        return new ExerciseResistanceListAdapter(getPageContext(), list);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
