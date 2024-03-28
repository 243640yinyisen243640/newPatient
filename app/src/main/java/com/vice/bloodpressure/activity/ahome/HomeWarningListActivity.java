package com.vice.bloodpressure.activity.ahome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aservice.ServiceBloodListActivity;
import com.vice.bloodpressure.adapter.home.HomeWarningListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.dialog.HHSoftDialogActionEnum;
import com.vice.bloodpressure.model.MessageInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.DialogUtils;
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
     * 1 全部 2 血压偏高 3 血压偏低 4 血糖偏高 5 血糖偏低 6 血糖全部 7 血压全部
     */
    private TextView warningTypeTv;
    /**
     * 确认
     */
    private TextView sureTv;


    private String startTime = "";
    private String endTime = "";
    private String exceptionType = "";

    private HomeWarningListAdapter warningListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
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
        warningTypeTv.setOnClickListener(this);
        sureTv.setOnClickListener(this);
    }

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_warning_message_top, null);
        backIm = topView.findViewById(R.id.iv_home_warning_back);
        allTv = topView.findViewById(R.id.tv_warning_all_read);
        startTv = topView.findViewById(R.id.tv_warning_start);
        endTv = topView.findViewById(R.id.tv_warning_end);
        warningTypeTv = topView.findViewById(R.id.tv_warning_warning_type);
        sureTv = topView.findViewById(R.id.tv_warning_sure);
        return topView;
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = UserDataManager.getHomeWarningList(UserInfoUtils.getArchivesId(getPageContext()), startTime, endTime, exceptionType, (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getHomeWarningList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<MessageInfo> list) {
        return warningListAdapter = new HomeWarningListAdapter(getPageContext(), list, (position, view) -> {

            readOneWarning(position);
            switch (view.getId()) {
                //删除
                case R.id.tv_warning_delete:
                    DialogUtils.showOperDialog(getPageContext(), "", "确认要删除吗？", "我在想想", "确定", (dialog, which) -> {
                        dialog.dismiss();
                        if (HHSoftDialogActionEnum.POSITIVE == which) {
                            deleteOneMessage(position);
                        }
                    });

                    break;
                //获取更多数据
                case R.id.tv_warning_more:
                    Intent intent = new Intent(getPageContext(), ServiceBloodListActivity.class);
                    startActivity(intent);
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
    private void readOneWarning(int position) {
        Call<String> requestCall = UserDataManager.readOneWarning(getPageListData().get(position).getMsgId(), (call, response) -> {
            if ("0000".equals(response.code)) {
                getPageListData().get(position).setReadStatus("1");
                warningListAdapter.notifyDataSetChanged();
                //                //0未读 1 已读
                //                getPageListData().get(position).setReadStatus("1");
                //                warningListAdapter.notifyDataSetChanged();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("readOneMessage", requestCall);
    }

    /**
     * 删除消息
     *
     * @param position
     */
    private void deleteOneMessage(int position) {
        Call<String> requestCall = UserDataManager.deleteOneWarning(getPageListData().get(position).getMsgId(), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                //                getPageListData().remove(position);
                //                warningListAdapter.notifyDataSetChanged();

                onPageLoad();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("deleteOneWarning", requestCall);
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
                readAllMessage();
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
                            endTime = object.toString();
                            endTv.setText(object.toString());
                            if (!TextUtils.isEmpty(endTime)) {
                                setPageIndex(1);
                                onPageLoad();
                            }
                        } else {
                            ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                        }
                    }
                });
                break;
            case R.id.tv_warning_warning_type:
                List<String> typeList = new ArrayList<>();
                typeList.add("全部");
                typeList.add("血压偏高");
                typeList.add("血压偏低");
                typeList.add("血糖偏高");
                typeList.add("血糖偏低");
                typeList.add("血糖偏低");
                typeList.add("血糖全部");
                typeList.add("血压全部");
                PickerViewUtils.showChooseSinglePicker(getPageContext(), "异常类型", typeList, object -> {
                    warningTypeTv.setText(typeList.get(Integer.parseInt(String.valueOf(object))));
                    exceptionType = Integer.parseInt(String.valueOf(object)) + 1 + "";
                    setPageIndex(1);
                    onPageLoad();

                });

                break;
            case R.id.tv_warning_sure:
                setPageIndex(1);
                onPageLoad();
                break;
            default:
                break;
        }
    }

    private void readAllMessage() {
        Call<String> requestCall = UserDataManager.readWarningList(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setPageIndex(1);
                onPageLoad();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("readWarningList", requestCall);
    }

}
