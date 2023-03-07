package com.vice.bloodpressure.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.fragment.MainFragment;
import com.vice.bloodpressure.fragment.fhome.MainHomeFragment;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.version.VersionUtils;


public class MainActivity extends UIBaseActivity implements View.OnClickListener {
    private long exitTime;
    private FragmentManager mFragManager;
    private Fragment mCurrentFragment;

    private TextView homeTextView;
    private TextView serviceTextView;
    private TextView outHospitalTextView;
    private TextView mallTextView;
    private TextView myTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        containerView().addView(initView());
        initValue();
        initListener();

        VersionUtils.getInstance().updateNewVersion(getPageContext(), this, false);
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_main, null);
        homeTextView = view.findViewById(R.id.tv_main_home_page);
        serviceTextView = view.findViewById(R.id.tv_main_home_service);
        outHospitalTextView = view.findViewById(R.id.tv_main_home_out_hospital);
        mallTextView = view.findViewById(R.id.tv_main_home_mall);
        myTextView = view.findViewById(R.id.tv_main_home_my);
        return view;
    }

    private void initValue() {

        mFragManager = getSupportFragmentManager();
        checkFragment(R.id.tv_main_home_page);
        setTextViewColor(R.id.tv_main_home_page);
    }

    private void initListener() {
        homeTextView.setOnClickListener(this);
        serviceTextView.setOnClickListener(this);
        outHospitalTextView.setOnClickListener(this);
        mallTextView.setOnClickListener(this);
        myTextView.setOnClickListener(this);
    }

    /**
     * 设置选中的Fragment
     *
     * @param checkId
     */
    private void checkFragment(@IdRes int checkId) {
        FragmentTransaction transaction = mFragManager.beginTransaction();
        Fragment fragment = mFragManager.findFragmentByTag(checkId + "");
        if (fragment == null) {
            if (checkId == R.id.tv_main_home_page) {
                //首页
                fragment = new MainHomeFragment();
            } else if (checkId == R.id.tv_main_home_service) {
                fragment = new MainFragment();
            } else if (checkId == R.id.tv_main_home_out_hospital) {
                fragment = new MainFragment();
            } else if (checkId == R.id.tv_main_home_mall) {
                fragment = new MainFragment();
            } else {
                fragment = new MainFragment();
            }
            transaction.add(R.id.fl_main, fragment, checkId + "");
        }
        if (mCurrentFragment != null) {
            mCurrentFragment.onPause();
            transaction.hide(mCurrentFragment);
        }
        mCurrentFragment = fragment;
        transaction.show(mCurrentFragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 选中的id
     *
     * @param checkedId
     */
    private void setTextViewColor(@IdRes int checkedId) {
        if (checkedId == R.id.tv_main_home_page) {
            homeTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
            homeTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_home_selected, 0, 0);

            serviceTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            serviceTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_service_unselected, 0, 0);

            outHospitalTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            outHospitalTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_home_out_hospital_unselected, 0, 0);

            mallTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            mallTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_mall_unselected, 0, 0);

            myTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            myTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_me_unselected, 0, 0);
        } else if (checkedId == R.id.tv_main_home_service) {
            homeTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            homeTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_home_unselected, 0, 0);

            serviceTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
            serviceTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_service_selected, 0, 0);

            outHospitalTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            outHospitalTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_home_out_hospital_unselected, 0, 0);

            mallTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            mallTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_mall_unselected, 0, 0);

            myTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            myTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_me_unselected, 0, 0);
        } else if (checkedId == R.id.tv_main_home_out_hospital) {
            homeTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            homeTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_home_unselected, 0, 0);

            serviceTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            serviceTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_service_unselected, 0, 0);

            outHospitalTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
            outHospitalTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_home_out_hospital_selected, 0, 0);

            mallTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            mallTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_mall_unselected, 0, 0);

            myTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            myTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_me_unselected, 0, 0);
        } else if (checkedId == R.id.tv_main_home_mall) {
            homeTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            homeTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_home_unselected, 0, 0);

            serviceTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            serviceTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_service_unselected, 0, 0);

            outHospitalTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            outHospitalTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_service_unselected, 0, 0);

            mallTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
            mallTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_mall_selected, 0, 0);

            myTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            myTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_me_unselected, 0, 0);
        } else {
            homeTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            homeTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_home_unselected, 0, 0);

            serviceTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            serviceTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_service_unselected, 0, 0);

            outHospitalTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            outHospitalTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_service_unselected, 0, 0);

            mallTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.gray_98));
            mallTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_mall_unselected, 0, 0);

            myTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
            myTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.main_me_selected, 0, 0);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_main_home_page:
            case R.id.tv_main_home_service:
            case R.id.tv_main_home_out_hospital:
            case R.id.tv_main_home_mall:
            case R.id.tv_main_home_my:
                setTextViewColor(view.getId());
                checkFragment(view.getId());

                break;
            default:
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
            dialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void dialog() {
        long nowTime = System.currentTimeMillis();
        if (nowTime - exitTime > 3000) {
            ToastUtils.getInstance().showToast(getPageContext(), "再按一次退出应用");
            exitTime = nowTime;
        } else {
            finish();
        }
    }
}