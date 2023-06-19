package com.vice.bloodpressure.activity.ahome;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.ahome.aeducation.EducationIntelligenceActivity;
import com.vice.bloodpressure.activity.aservice.ServiceBloodListActivity;
import com.vice.bloodpressure.activity.aservice.ServicePressureListActivity;
import com.vice.bloodpressure.activity.auser.UserDoctorActivity;
import com.vice.bloodpressure.adapter.home.HomeMessageListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.MessageInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:消息提醒
 */
public class HomeMessageListActivity extends UIBaseListRecycleViewActivity<MessageInfo> {
    private HomeMessageListAdapter messageListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("消息提醒");
        topViewManager().moreTextView().setText("全部已读");
        topViewManager().moreTextView().setOnClickListener(v -> {
            readAllMessage();
        });
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), false));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

    }

    /**
     * 全部已读
     */
    private void readAllMessage() {
        Call<String> requestCall = UserDataManager.readMessageList(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setPageIndex(1);
                onPageLoad();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("readMessageList", requestCall);
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = UserDataManager.getMessageList(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getMessageList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<MessageInfo> list) {
        return messageListAdapter = new HomeMessageListAdapter(getPageContext(), list, (position, view) -> {
            switch (view.getId()) {
                case R.id.fl_message_click:
                    Intent intent;
                    getPageListData().get(position).setStatus("1");
                    messageListAdapter.notifyDataSetChanged();
                    readOneMessage(position);
                    //  //1. 绑定医生 2. 血糖 3. 血压  4 智能教育
                    switch (getPageListData().get(position).getType()) {
                        case "1":
                            intent = new Intent(getPageContext(), UserDoctorActivity.class);
                            startActivity(intent);
                            break;
                        case "2":
                            intent = new Intent(getPageContext(), ServiceBloodListActivity.class);
                            startActivity(intent);
                            break;
                        case "3":
                            intent = new Intent(getPageContext(), ServicePressureListActivity.class);
                            startActivity(intent);
                            break;
                        case "4":
                            intent = new Intent(getPageContext(), EducationIntelligenceActivity.class);
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }


        });
    }

    /**
     * 读某一条消息
     *
     * @param position
     */
    private void readOneMessage(int position) {
        Call<String> requestCall = UserDataManager.readOneMessage(getPageListData().get(position).getId(), (call, response) -> {
            if ("0000".equals(response.code)) {

            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("readOneMessage", requestCall);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
