package com.vice.bloodpressure.activity.aout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.ahome.aeducation.EducationIntelligenceSearchActivity;
import com.vice.bloodpressure.adapter.out.OutOfficeDoctorLeftAdapter;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.fragment.fout.OutOfficeDoctorListFragment;
import com.vice.bloodpressure.model.HospitalInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class OutOfficeActivity extends UIBaseLoadActivity {

    private ListView leftListView;
    private TextView searchTextView;
    private ImageView headImageView;
    private TextView nameTextView;
    private TextView introduceTextView;
    private TextView locationTextView;
    private TextView levelTextView;
    private ViewPager2 viewPager;

    private List<HospitalInfo> officeInfos;


    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("科室医生列表");
        topViewManager().lineViewVisibility(View.VISIBLE);
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initListener() {
        searchTextView.setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), EducationIntelligenceSearchActivity.class));
        });
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_out_office, null);
        searchTextView = getViewByID(view, R.id.tv_out_office_search);
        headImageView = getViewByID(view, R.id.iv_out_office_hos_img);
        nameTextView = getViewByID(view, R.id.tv_out_office_hos_name);
        introduceTextView = getViewByID(view, R.id.tv_out_office_hos_introduce);
        locationTextView = getViewByID(view, R.id.tv_out_office_hos_location);
        levelTextView = getViewByID(view, R.id.tv_out_office_hos_level);
        leftListView = getViewByID(view, R.id.lv_office_first);
        viewPager = getViewByID(view, R.id.vp_office_second);
        containerView().addView(view);
    }


    @Override
    protected void onPageLoad() {
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);

        officeInfos = new ArrayList<>();
        officeInfos.add(new HospitalInfo("泌尿科", "0"));
        officeInfos.add(new HospitalInfo("内科", "0"));
        officeInfos.add(new HospitalInfo("外科", "0"));
        officeInfos.add(new HospitalInfo("护理科", "0"));

        fragments = new ArrayList<>();
        for (int i = 0; i < officeInfos.size(); i++) {
            fragments.add(OutOfficeDoctorListFragment.newInstance(i + ""));
        }
        viewPager.setAdapter(new MyFragmentStateAdapter(this, fragments) {
        });
        officeInfos.get(0).setIsCheck("1");
        viewPager.setCurrentItem(0);//默认选中项
        viewPager.setOffscreenPageLimit(fragments.size());


        OutOfficeDoctorLeftAdapter leftAdapter = new OutOfficeDoctorLeftAdapter(getPageContext(), officeInfos);
        leftListView.setAdapter(leftAdapter);

        leftListView.setOnItemClickListener((parent, view, position, id) -> {
            viewPager.setCurrentItem(position);

            //初始化
            for (int i = 0; i < officeInfos.size(); i++) {
                if (position == i) {
                    officeInfos.get(i).setIsCheck("1");
                } else {
                    officeInfos.get(i).setIsCheck("0");
                }
            }
            leftAdapter.notifyDataSetChanged();

        });

    }


}
