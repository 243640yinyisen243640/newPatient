package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.fragment.fservice.SevenAndThirtyBloodSugarListFragment;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 类名：
 * 传参：
 * 描述:血糖数据
 * 作者: beauty
 * 创建日期: 2023/3/28 13:59
 */
public class ServiceBloodListActivity extends UIBaseActivity implements View.OnClickListener {
    private ImageView backImageView;
    private TextView moreTextView;
    private TextView startTimeTextView;
    private TextView endTimeTextView;
    private RadioGroup radioGroup;
    private ViewPager2 viewPager;

    private String startTime="";
    private String endTime="";


    private ArrayList<Fragment> fragments;
    private int checkPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        initView();
        initValue();
        initlisteber();
    }

    private void initlisteber() {
        backImageView.setOnClickListener(this);
        moreTextView.setOnClickListener(this);
        startTimeTextView.setOnClickListener(this);
        endTimeTextView.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_blood_data, null);
        backImageView = view.findViewById(R.id.iv_service_blood_data_back);
        TextView titleTextView = view.findViewById(R.id.tv_service_blood_data_title);
        moreTextView = view.findViewById(R.id.tv_service_blood_data_more);
        startTimeTextView = view.findViewById(R.id.tv_service_blood_data_start_time);
        endTimeTextView = view.findViewById(R.id.tv_service_blood_data_end_time);
        radioGroup = view.findViewById(R.id.rg_service_blood_data);
        viewPager = view.findViewById(R.id.vp_service_blood_data);
        radioGroup.setVisibility(View.VISIBLE);
        moreTextView.setVisibility(View.GONE);

        titleTextView.setText("血糖数据");
        containerView().addView(view);
    }

    public List<Fragment> getFragments(){
        return fragments;
    }

    private void initValue() {
        fragments = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            SevenAndThirtyBloodSugarListFragment fragment = new SevenAndThirtyBloodSugarListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("dateType", i + "");
            fragment.setArguments(bundle);
            fragments.add(fragment);

            viewPager.setAdapter(new MyFragmentStateAdapter(this, fragments));
            viewPager.setOffscreenPageLimit(fragments.size());
            radioGroup.check(radioGroup.getChildAt(0).getId());
            viewPager.setCurrentItem(0);

            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                int index = group.indexOfChild(group.findViewById(checkedId));
                viewPager.setCurrentItem(index);
            });

            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    checkPosition = i;
                    radioGroup.check(radioGroup.getChildAt(i).getId());

                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_service_blood_data_back:
                finish();
                break;
            case R.id.tv_service_blood_data_more:
                startActivity(new Intent(getPageContext(), ServiceBloodTypeActivity.class));
                break;
            case R.id.tv_service_blood_data_start_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        startTime = String.valueOf(object);
                        startTimeTextView.setText(object.toString());
                        if (!TextUtils.isEmpty(endTime)) {
                            SevenAndThirtyBloodSugarListFragment fragment = (SevenAndThirtyBloodSugarListFragment) fragments.get(checkPosition);
                            fragment.refresh(startTime, object.toString());
                        }
                    }
                });
                break;
            case R.id.tv_service_blood_data_end_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, object -> {
                    if (XyTimeUtils.compareTwoTime(startTime, object.toString())) {
                        endTimeTextView.setText(object.toString());
                        endTime = object.toString();
                        SevenAndThirtyBloodSugarListFragment fragment = (SevenAndThirtyBloodSugarListFragment) fragments.get(checkPosition);
                        fragment.refresh(startTime, object.toString());
                    } else {
                        ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                    }
                });
                break;

            default:
                break;
        }
    }
}
