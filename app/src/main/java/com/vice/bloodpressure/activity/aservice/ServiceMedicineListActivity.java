package com.vice.bloodpressure.activity.aservice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.MyFragmentStateAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.fragment.fservice.ServiceMedicineRecordFragment;
import com.vice.bloodpressure.fragment.fservice.ServiceMedicineRemindFragment;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.ArrayList;


/**
 * 类名：
 * 传参：
 * 描述:用药数据
 * 作者: beauty
 * 创建日期: 2023/3/28 13:59
 */
public class ServiceMedicineListActivity extends UIBaseActivity implements View.OnClickListener {
    private ImageView backImageView;
    private TextView startTimeTextView;
    private TextView endTimeTextView;
    private RadioGroup radioGroup;
    private ViewPager2 viewPager;

    private String startTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        setTitle("用药");
        initView();
        initValue();
        initlisteber();
    }

    private void initlisteber() {
        backImageView.setOnClickListener(this);
        startTimeTextView.setOnClickListener(this);
        endTimeTextView.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_blood_data, null);
        backImageView = view.findViewById(R.id.iv_service_blood_data_back);
        TextView titleTextView = view.findViewById(R.id.tv_service_blood_data_title);
        startTimeTextView = view.findViewById(R.id.tv_service_blood_data_start_time);
        endTimeTextView = view.findViewById(R.id.tv_service_blood_data_end_time);
        radioGroup = view.findViewById(R.id.rg_service_medicine);
        viewPager = view.findViewById(R.id.vp_service_blood_data);
        radioGroup.setVisibility(View.VISIBLE);
        titleTextView.setText("用药");
        containerView().addView(view);
    }


    private void initValue() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        ServiceMedicineRecordFragment recordFragment = new ServiceMedicineRecordFragment();
        ServiceMedicineRemindFragment remindFragment = new ServiceMedicineRemindFragment();

        fragments = new ArrayList<>();
        fragments.add(recordFragment.getInstance("11"));
        fragments.add(remindFragment.getInstance("11"));

        viewPager.setAdapter(new MyFragmentStateAdapter(this, fragments));
        viewPager.setOffscreenPageLimit(fragments.size());
        radioGroup.check(radioGroup.getChildAt(0).getId());
        viewPager.setCurrentItem(0);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int index = radioGroup.indexOfChild(radioGroup.findViewById(checkedId));
            viewPager.setCurrentItem(index);
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    if (j == i) {
                        ((RadioButton) radioGroup.getChildAt(j)).setTypeface(Typeface.DEFAULT_BOLD);
                    } else {
                        ((RadioButton) radioGroup.getChildAt(j)).setTypeface(Typeface.DEFAULT);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_service_blood_data_back:
                finish();
                break;
            case R.id.tv_service_blood_data_more:

                break;
            case R.id.tv_service_blood_data_start_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        startTime = String.valueOf(object);
                        startTimeTextView.setText(object.toString());
                    }
                });
                break;
            case R.id.tv_service_blood_data_end_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        if (XyTimeUtils.compareTwoTime(startTime, object.toString())) {
                            endTimeTextView.setText(object.toString());
                        } else {
                            ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                        }
                    }
                });
                break;

            default:
                break;
        }
    }
}
