package com.vice.bloodpressure.fragment.fhome.exercise;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.adapter.home.ExerciseRecordListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.ExerciseChildInfo;
import com.vice.bloodpressure.model.ExerciseInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ExerciseOxygenFragment extends UIBaseListRecycleViewFragment<ExerciseInfo> {
    private List<ExerciseInfo> listText;

    public static ExerciseOxygenFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        ExerciseOxygenFragment fragment = new ExerciseOxygenFragment();
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
        //        Call<String> requestCall = HomeDataManager.getSportAerobics();
        listText = new ArrayList<>();
        listText.add(new ExerciseInfo("3360", "2022-07-12 12:20:23", "1260"));
        listText.add(new ExerciseInfo("3860", "2022-07-12 12:20:23", "1160"));

        List<ExerciseChildInfo> childList = new ArrayList<>();
        childList.add(new ExerciseChildInfo("步行", "24min12s", "1260"));
        childList.add(new ExerciseChildInfo("羽毛球", "30min", "1260"));

        for (int i = 0; i < listText.size(); i++) {
            listText.get(i).setList(childList);
        }
        callBack.callBack(listText);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<ExerciseInfo> list) {
        return new ExerciseRecordListAdapter(getPageContext(), list);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }

    public void refresh() {
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }
}
