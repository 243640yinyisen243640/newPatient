package com.vice.bloodpressure.fragment.fservice;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServiceChooseMealListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.ServiceInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:血压列表
 */
public class ServiceMealChooseListFragment extends UIBaseListRecycleViewForBgFragment<ServiceInfo> {

    private TextView numTextView;

    @Override
    protected void onCreate() {
        super.onCreate();
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), false));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        setPublicBottom();
    }

    private void setPublicBottom() {
        View view = View.inflate(getPageContext(), R.layout.include_service_bottom, null);
        numTextView = view.findViewById(R.id.tv_service_meal_add_have_choose);
        TextView sureTextView = view.findViewById(R.id.tv_service_meal_add_have_sure);
        FrameLayout.LayoutParams f2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        f2.gravity = Gravity.BOTTOM;
        sureTextView.setOnClickListener(v -> {

        });
        containerView().addView(view, f2);
    }


    @Override
    protected void getListData(CallBack callBack) {
        List<ServiceInfo> oxygenList = new ArrayList<>();
        oxygenList.add(new ServiceInfo("胡萝卜", "22","100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22","100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22","100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22","100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22","100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22","100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22","100"));
        oxygenList.add(new ServiceInfo("胡萝卜", "22","100"));
        callBack.callBack(oxygenList);

    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<ServiceInfo> list) {
        return new ServiceChooseMealListAdapter(getPageContext(), list);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }

}
