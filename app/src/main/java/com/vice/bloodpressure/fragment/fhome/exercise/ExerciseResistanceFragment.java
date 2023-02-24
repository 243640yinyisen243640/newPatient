package com.vice.bloodpressure.fragment.fhome.exercise;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
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
public class ExerciseResistanceFragment extends UIBaseListRecycleViewFragment<ExerciseChildInfo> {
    private List<ExerciseChildInfo> listText = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        getPageListView().setBackgroundColor(getResources().getColor(R.color.background));
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void getListData(CallBack callBack) {
        listText.add(new ExerciseChildInfo("深蹲", "2022-03-26", "30", "未达标"));
        listText.add(new ExerciseChildInfo("深蹲", "2022-03-26", "10", "完成"));
        listText.add(new ExerciseChildInfo("深蹲", "2022-03-26", "100", "超标"));

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
