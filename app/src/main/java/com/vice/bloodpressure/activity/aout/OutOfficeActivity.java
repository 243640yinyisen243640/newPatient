package com.vice.bloodpressure.activity.aout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.out.OutOfficeDoctorLeftAdapter;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.OutDataManager;
import com.vice.bloodpressure.fragment.fout.OutOfficeDoctorListFragment;
import com.vice.bloodpressure.model.HospitalInfo;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:hospitalId  医生id
 * 描述:科室医生列表
 */
public class OutOfficeActivity extends UIBaseLoadActivity {

    private ListView leftListView;
    private TextView searchTextView;
    private LinearLayout hosClickLinearLayout;
    private ImageView headImageView;
    private TextView nameTextView;
    private TextView introduceTextView;
    private TextView locationTextView;
    private TextView levelTextView;
    private ViewPager2 viewPager;


    private List<Fragment> fragments;
    /**
     * 医生id
     */
    private String hospitalId = "";


    private String deptId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("科室医生列表");
        hospitalId = getIntent().getStringExtra("hospitalId");
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = OutDataManager.getDeptList(hospitalId, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                HospitalInfo hospitalInfo = (HospitalInfo) response.object;
                if (hospitalInfo.getDeptAppVoList() != null && hospitalInfo.getDeptAppVoList().size() > 0) {
                    deptId = hospitalInfo.getDeptAppVoList().get(0).getDeptId();
                }

                bindData(hospitalInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getDeptList", requestCall);
    }

    private void bindData(HospitalInfo hospitalInfo) {
        XyImageUtils.loadRoundImage(getPageContext(), R.drawable.out_hospital_default, hospitalInfo.getLogo(), headImageView);
        nameTextView.setText(hospitalInfo.getHospitalName());

        locationTextView.setText(hospitalInfo.getDetailedAddress());
        if (hospitalInfo.getCategory() == null) {
            levelTextView.setVisibility(View.GONE);
        } else {
            levelTextView.setText(hospitalInfo.getCategory());
        }
        if (hospitalInfo.getIntroduction() == null) {
            introduceTextView.setVisibility(View.GONE);
        } else {
            introduceTextView.setText(hospitalInfo.getIntroduction());
        }

        fragments = new ArrayList<>();
        for (int i = 0; i < hospitalInfo.getDeptAppVoList().size(); i++) {
            fragments.add(OutOfficeDoctorListFragment.newInstance(deptId));
        }
        viewPager.setAdapter(new MyFragmentStateAdapter(this, fragments) {
        });
        if (hospitalInfo.getDeptAppVoList() != null && hospitalInfo.getDeptAppVoList().size() > 0) {
            hospitalInfo.getDeptAppVoList().get(0).setIsCheck("1");
            viewPager.setCurrentItem(0);//默认选中项
            viewPager.setOffscreenPageLimit(fragments.size());
        }

        OutOfficeDoctorLeftAdapter leftAdapter = new OutOfficeDoctorLeftAdapter(getPageContext(), hospitalInfo.getDeptAppVoList());
        leftListView.setAdapter(leftAdapter);

        leftListView.setOnItemClickListener((parent, view, position, id) -> {
            viewPager.setCurrentItem(position);
            deptId = hospitalInfo.getDeptAppVoList().get(position).getDeptId();
            OutOfficeDoctorListFragment fragment = (OutOfficeDoctorListFragment) fragments.get(position);
            fragment.setDeptIdRefresh(deptId);
            //初始化
            for (int i = 0; i < hospitalInfo.getDeptAppVoList().size(); i++) {
                if (position == i) {
                    hospitalInfo.getDeptAppVoList().get(i).setIsCheck("1");

                } else {
                    hospitalInfo.getDeptAppVoList().get(i).setIsCheck("0");
                }
            }
            leftAdapter.notifyDataSetChanged();

        });
    }


    private void initListener() {
        searchTextView.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), OutDoctorSearchListActivity.class);
            intent.putExtra("deptId", deptId);
            startActivity(intent);
        });
        hosClickLinearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), OutHospitalInfoActivity.class);
            intent.putExtra("hospitalId", hospitalId);
            startActivity(intent);
        });
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        ////true:滑动，false：禁止滑动
        viewPager.setUserInputEnabled(false);
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_out_office, null);
        searchTextView = getViewByID(view, R.id.tv_out_office_search);
        hosClickLinearLayout = getViewByID(view, R.id.ll_out_office_hos_click);
        headImageView = getViewByID(view, R.id.iv_out_office_hos_img);
        nameTextView = getViewByID(view, R.id.tv_out_office_hos_name);
        introduceTextView = getViewByID(view, R.id.tv_out_office_hos_introduce);
        locationTextView = getViewByID(view, R.id.tv_out_office_hos_location);
        levelTextView = getViewByID(view, R.id.tv_out_office_hos_level);
        leftListView = getViewByID(view, R.id.lv_office_first);
        viewPager = getViewByID(view, R.id.vp_office_second);
        containerView().addView(view);
    }


}
