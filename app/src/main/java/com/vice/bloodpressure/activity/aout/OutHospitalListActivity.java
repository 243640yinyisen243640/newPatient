package com.vice.bloodpressure.activity.aout;

import android.content.Intent;
import android.os.Bundle;
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
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.HospitalInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:医院列表
 */
public class OutHospitalListActivity extends UIBaseListRecycleViewActivity<HospitalInfo> implements View.OnClickListener {
    private List<HospitalInfo> listText = new ArrayList<>();
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
        searchEditText.setOnClickListener(this);
        provinceLinearLayout.setOnClickListener(this);
        provinceTv.setOnClickListener(this);
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

    @Override
    protected void getListData(CallBack callBack) {
        listText.add(new HospitalInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "河南省人民医院", "郑州市第一人民医院(新乡医学院郑州第一附属医院)始建于1942年，是一所集医疗、教...、科研、预防、保健、康复为一体的综合性", "郑州市金水区东十里铺社区", "三甲医院"));
        listText.add(new HospitalInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "郑州大学第一附属医院", "郑州市第一人民医院(新乡医学院郑州第一附属医院)始建于1942年，是一所集医疗、教...、科研、预防、保健、康复为一体的综合性", "郑州市金水区东十里铺社区", "三甲医院"));
        listText.add(new HospitalInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "河南省肿瘤医院", "郑州市第一人民医院(新乡医学院郑州第一附属医院)始建于1942年，是一所集医疗、教...、科研、预防、保健、康复为一体的综合性", "郑州市金水区东十里铺社区", "三甲医院"));

        //        HomeMessageListAdapter adapter = new HomeMessageListAdapter(getPageContext(),);
        callBack.callBack(listText);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<HospitalInfo> list) {
        return new OutHospitalListAdapter(getPageContext(), list, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.ll_out_hos_click:
                        startActivity(new Intent(getPageContext(), OutOfficeActivity.class));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_warning_back:
                finish();
                break;
            case R.id.ll_out_hos_province:

                break;
            case R.id.ll_out_hos_city:


                break;

            default:
                break;
        }
    }

}
