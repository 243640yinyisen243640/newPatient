package com.vice.bloodpressure.activity.aout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.out.OutHospitalListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.datamanager.OutDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.HospitalInfo;
import com.vice.bloodpressure.model.ProvinceInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:医院列表
 */
public class OutHospitalListActivity extends UIBaseListRecycleViewActivity<HospitalInfo> implements View.OnClickListener {
    /**
     * 返回键
     */
    private ImageView backIm;
    /**
     * 搜索
     */
    private EditText searchEditText;
    /**
     * 选择省份
     */
    private LinearLayout provinceLinearLayout;
    /**
     * 省份显示
     */
    private TextView provinceTv;
    /**
     * 城市
     */
    private LinearLayout cityLinearLayout;
    /**
     * 显示城市
     */
    private TextView cityTextView;


    private List<ProvinceInfo> provinceList;
    private List<ProvinceInfo> cityList;

    private String provinceID = "";
    private String cityID = "";

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


    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = OutDataManager.gethospitalList(provinceID, cityID, (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("gethospitalList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<HospitalInfo> list) {
        return new OutHospitalListAdapter(getPageContext(), list, (position, view) -> {
            switch (view.getId()) {
                case R.id.ll_out_hos_click:
                    startActivity(new Intent(getPageContext(), OutOfficeActivity.class));
                    break;

                default:
                    break;
            }
        });
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
            case R.id.ll_out_hos_province:
                getProvinceList();
                break;
            case R.id.ll_out_hos_city:
                if (TextUtils.isEmpty(provinceID)) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请先选择省份");
                    return;
                }
                getCityList();

                break;

            default:
                break;
        }
    }

    /**
     * 获取省份的
     */
    private void getProvinceList() {
        Call<String> requestCall = OutDataManager.getProvinceList((call, response) -> {
            if ("0000".equals(response.code)) {
                provinceList = (List<ProvinceInfo>) response.object;
                List<String> list = new ArrayList<>();
                if (provinceList != null && provinceList.size() > 0) {
                    for (int i = 0; i < provinceList.size(); i++) {
                        String typeName = provinceList.get(i).getProvinceName();
                        list.add(typeName);
                    }
                }
                chooseTypeWindow(list, "选择省份");
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getProvinceList", requestCall);
    }

    /**
     * 获取省份下的城市
     */
    private void getCityList() {
        Call<String> requestCall = OutDataManager.getCityList(provinceID, (call, response) -> {
            if ("0000".equals(response.code)) {
                cityList = (List<ProvinceInfo>) response.object;
                List<String> list = new ArrayList<>();
                if (cityList != null && cityList.size() > 0) {
                    for (int i = 0; i < cityList.size(); i++) {
                        String typeName = cityList.get(i).getCityName();
                        list.add(typeName);
                    }
                }
                chooseTypeWindow(list, "选择城市");
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getCityList", requestCall);
    }

    /**
     * 选择品牌名称
     */
    private void chooseTypeWindow(List<String> stringList, String title) {
        PickerViewUtils.showChooseSinglePicker(getPageContext(), title, stringList, object -> {
            if ("选择省份".equals(title)) {
                provinceID = provinceList.get(Integer.parseInt(String.valueOf(object))).getProvinceId();
                provinceTv.setText(provinceList.get(Integer.parseInt(String.valueOf(object))).getProvinceName());
            } else {
                cityID = cityList.get(Integer.parseInt(String.valueOf(object))).getCityId();
                cityTextView.setText(cityList.get(Integer.parseInt(String.valueOf(object))).getCityName());
                setPageIndex(1);
                onPageLoad();
            }

        });
    }


    private void initListener() {
        backIm.setOnClickListener(this);
        provinceLinearLayout.setOnClickListener(this);
        cityLinearLayout.setOnClickListener(this);
    }

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_out_hospital_list_top, null);
        backIm = topView.findViewById(R.id.iv_out_hos_back);
        searchEditText = topView.findViewById(R.id.et_out_hos_search);
        provinceLinearLayout = topView.findViewById(R.id.ll_out_hos_province);
        provinceTv = topView.findViewById(R.id.tv_out_hos_province);
        cityLinearLayout = topView.findViewById(R.id.ll_out_hos_city);
        cityTextView = topView.findViewById(R.id.tv_out_hos_city);
        return topView;
    }
}
