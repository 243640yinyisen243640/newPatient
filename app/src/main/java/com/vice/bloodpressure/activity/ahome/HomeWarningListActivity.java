package com.vice.bloodpressure.activity.ahome;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.HomeWarningListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.MessageInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:异常数据
 */
public class HomeWarningListActivity extends UIBaseListRecycleViewActivity<MessageInfo> implements View.OnClickListener {
    private List<MessageInfo> listText = new ArrayList<>();
    /**
     * 返回键
     */
    private ImageView backIm;
    /**
     * 全部已读
     */
    private TextView allTv;
    /**
     * 开始时间
     */
    private TextView startTv;
    /**
     * 结束时间
     */
    private TextView endTv;
    /**
     * 确认
     */
    private TextView sureTv;

    private String startTime="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
//        getPageListView().setBackgroundColor(getResources().getColor(R.color.background));
        initListener();
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

    }

    private void initListener() {
        backIm.setOnClickListener(this);
        allTv.setOnClickListener(this);
        startTv.setOnClickListener(this);
        endTv.setOnClickListener(this);
        sureTv.setOnClickListener(this);
    }

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_warning_message_top, null);
        backIm = topView.findViewById(R.id.iv_home_warning_back);
        allTv = topView.findViewById(R.id.tv_warning_all_read);
        startTv = topView.findViewById(R.id.tv_warning_start);
        endTv = topView.findViewById(R.id.tv_warning_end);
        sureTv = topView.findViewById(R.id.tv_warning_sure);
        return topView;
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = HomeDataManager.getHomeWarningList(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setPageIndex(1);
                onPageLoad();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getHomeWarningList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<MessageInfo> list) {
        return new HomeWarningListAdapter(getPageContext(), list);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_warning_back:
                finish();
                break;
            case R.id.tv_warning_all_read:
                ToastUtils.getInstance().showToast(getPageContext(), "测试弹出");
                break;
            case R.id.tv_warning_start:

                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        startTime = object.toString();
                        startTv.setText(object.toString());
                    }
                });
                break;
            case R.id.tv_warning_end:

                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        if (XyTimeUtils.compareTwoTime(startTime, object.toString())) {
                            endTv.setText(object.toString());
                        } else {
                            ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                        }
                    }
                });
                break;
            case R.id.tv_warning_sure:
                break;
            default:
                break;
        }
    }

}
