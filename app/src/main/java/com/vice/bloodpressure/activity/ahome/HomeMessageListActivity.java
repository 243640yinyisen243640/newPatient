package com.vice.bloodpressure.activity.ahome;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.adapter.home.HomeMessageListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.MessageInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:消息提醒 */
public class HomeMessageListActivity extends UIBaseListRecycleViewActivity<MessageInfo> {
    private List<MessageInfo> listText = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("消息提醒");
        topViewManager().moreTextView().setText("全部已读");
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        //        mRecyclerView.addItemDecoration(new UserCenterVideoGridDivider(getPageContext(), HHSoftDensityUtils.dip2px(getPageContext(), 3), R.color.white));
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), false));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

    }

    @Override
    protected void getListData(CallBack callBack) {
        listText.add(new MessageInfo("你有一条新的绑定医生通知", "绑定医生通知", "2022-07-12 12:20:23"));
        listText.add(new MessageInfo("测试内容", "测试标题", "2022-07-12 12:20:23"));
        listText.add(new MessageInfo("您已经连续多天没有测量血糖了，请保持良好 的测量习惯。", "血糖未测提醒", "2022-07-12 12:20:23"));
        listText.add(new MessageInfo("测试内容", "测试标题", "2022-07-12 12:20:23"));
        listText.add(new MessageInfo("您已经连续多天没有测量血压了，请保持良好 的测量习惯。", "血压未测提醒", "2022-07-12 12:20:23"));

        //        HomeMessageListAdapter adapter = new HomeMessageListAdapter(getPageContext(),);
        callBack.callBack(listText);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<MessageInfo> list) {
        return new HomeMessageListAdapter(getPageContext(), list);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
