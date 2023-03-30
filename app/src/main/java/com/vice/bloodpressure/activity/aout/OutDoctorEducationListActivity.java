package com.vice.bloodpressure.activity.aout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.OutDoctorEducationListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
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
 * 描述:消息提醒
 */
public class OutDoctorEducationListActivity extends UIBaseListRecycleViewActivity<MessageInfo> {
    private List<MessageInfo> listText = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("医生宣教");
        topViewManager().moreTextView().setText("全部已读");
//        getPageListView().setBackgroundColor(getResources().getColor(R.color.background));
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

    }

    @Override
    protected void getListData(CallBack callBack) {
        listText.add(new MessageInfo("宣教内容展示两行，超出的部分用... 表示宣教内容展示两行，超出的部分用... 表...。宣教内容展示两行，超出的部分用...", "标题", "2022-07-12 12:20:23"));
        listText.add(new MessageInfo("宣教内容展示两行，超出的部分用... 表示宣教内容展示两行，超出的部分用... 表...。宣教内容展示两行，超出的部分用...", "糖尿病遗传的概率有多大？", "2022-07-12 12:20:23"));

        callBack.callBack(listText);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<MessageInfo> list) {
        return new OutDoctorEducationListAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.ll_doctor_education_click:
                        Intent intent = new Intent(getPageContext(), OutDoctorEducationInfoActivity.class);
                        intent.putExtra("type", "1");
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
}
