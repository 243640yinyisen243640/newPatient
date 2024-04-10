package com.vice.bloodpressure.fragment.fuser;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.activity.ahome.HomeMessageListActivity;
import com.vice.bloodpressure.activity.auser.UserCollectActivity;
import com.vice.bloodpressure.activity.auser.UserDoctorActivity;
import com.vice.bloodpressure.activity.auser.UserEquipmetActivity;
import com.vice.bloodpressure.activity.auser.UserFilesActivity;
import com.vice.bloodpressure.activity.auser.UserOrderListActivity;
import com.vice.bloodpressure.activity.auser.UserQRCodeActivity;
import com.vice.bloodpressure.activity.auser.UserSetActivity;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.dialog.XySoftDialogActionEnum;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

import static android.app.Activity.RESULT_OK;


/**
 * 类描述： 我的
 * 类传参：
 * 一、登录判断
 * 二、登录：先加载本地数据；静默加载数据，刷新数据、页面
 */
public class UserCenterFragment extends UIBaseFragment implements View.OnClickListener {

    private static final int REQUEST_CODE_FOR_UN_BIND_DOCTOR = 1;
    /**
     * 头像
     */
    private ImageView avatarImageView;
    /**
     * 昵称+性别
     */
    private TextView nickNameTextView;
    /**
     * 年龄
     */
    private TextView ageTextView;
    /**
     * 病种
     */
    private TextView illTextView;


    /**
     * qrcode
     */
    private TextView qrCodeTextView;


    /**
     * 档案
     */
    private TextView filesTextView;
    /**
     * 收藏
     */
    private TextView collectTextView;
    /**
     * 收藏
     */
    private TextView equipmetTextView;
    /**
     * 收藏
     */
    private TextView doctorTextView;

    /**
     * 待付款
     */
    private FrameLayout payFrameLayout;
    private TextView payCountTextView;
    /**
     * 待发货
     */
    private FrameLayout pushFrameLayout;
    private TextView pushCountTextView;
    /**
     * 待收货
     */
    private FrameLayout receivedFrameLayout;
    private TextView receivedCountTextView;
    /**
     * 已完成
     */
    private FrameLayout finishFrameLayout;
    private TextView finishCountTextView;

    /**
     * 消息通知
     */
    private TextView messageTextView;
    /**
     * 设置
     */
    private TextView settingsTextView;

    private UserInfo userInfo;


    @Override
    protected void onCreate() {
        topViewManager().titleTextView().setText("个人中心");
        topViewManager().backTextView().setVisibility(View.GONE);
        initView();
        initValue();
        initListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadUserCenterInfo();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            loadUserCenterInfo();
        }
    }

    /**
     * 获取用户中心信息
     */
    private void loadUserCenterInfo() {
        UserDataManager.getUserInfo(UserInfoUtils.getArchivesId(getPageContext()),
                (call, response) -> {
                    if ("0000".equals(response.code)) {
                        userInfo = (UserInfo) response.object;
                        UserInfoUtils.saveUserInfo(getPageContext(), userInfo);
                        setData();
                    }
                }, (call, throwable) -> {
                });
    }

    /**
     * 已登录
     */
    private void setData() {
           //昵称
        nickNameTextView.setText(userInfo.getNickName());
        ageTextView.setText(String.format(getString(R.string.user_center_age), userInfo.getAge()));
        if ("1".equals(userInfo.getSex())) {
            nickNameTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_center_male, 0, 0, 0);
            //头像
            XyImageUtils.loadCircleImage(getPageContext(), R.drawable.user_center_default_head_img, userInfo.getAvatar(), avatarImageView);
        } else {
            nickNameTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.use_center_female, 0, 0, 0);
            //头像
            XyImageUtils.loadCircleImage(getPageContext(), R.drawable.user_center_default_head_img, userInfo.getAvatar(), avatarImageView);
        }

        if (!TextUtils.isEmpty(userInfo.getDiabetesType()) && !TextUtils.isEmpty(userInfo.getHypertensionType())) {
            illTextView.setText("主要诊断：" + userInfo.getDiabetesType() + " | " + userInfo.getHypertensionType());
        } else if (!TextUtils.isEmpty(userInfo.getDiabetesType())) {
            illTextView.setText("主要诊断：" + userInfo.getDiabetesType());
        } else if (!TextUtils.isEmpty(userInfo.getHypertensionType())) {
            illTextView.setText("主要诊断：" + userInfo.getHypertensionType());
        } else {
            illTextView.setVisibility(View.GONE);
        }

        //        //待付款
        //        int orderUnreadCount = TurnUtils.getInt(userInfo.getConfirmationCount(), 0);
        //        if (orderUnreadCount > 0) {
        //            payCountTextView.setVisibility(View.VISIBLE);
        //            if (orderUnreadCount > 99) {
        //                payCountTextView.setBackground(getResources().getDrawable(R.drawable.shape_red_3_3_circle));
        //                payCountTextView.setText("99+");
        //            } else {
        //                payCountTextView.setBackground(getResources().getDrawable(R.drawable.shape_red_3_3_circle));
        //                payCountTextView.setText(orderUnreadCount + "");
        //            }
        //        } else {
        //            payCountTextView.setVisibility(View.GONE);
        //        }


    }


    /**
     * 未登录设置
     */
    private void initValue() {
        //先取本地数据
        String avatar = SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.HEAD_IMG);
        String name = SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.NICK_NAME);
        if ("1".equals(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.SEX))) {
            nickNameTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_center_male, 0, 0, 0);
        } else {
            nickNameTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.use_center_female, 0, 0, 0);
        }
        XyImageUtils.loadCircleImage(getPageContext(), R.drawable.user_center_default_head_img, avatar, avatarImageView);
        nickNameTextView.setText(name);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_center, null);
        containerView().addView(view);
        avatarImageView = view.findViewById(R.id.iv_user_center_avatar);
        qrCodeTextView = view.findViewById(R.id.tv_user_center_qrcode);
        nickNameTextView = view.findViewById(R.id.tv_user_center_nickname);
        ageTextView = view.findViewById(R.id.tv_user_center_age);
        illTextView = view.findViewById(R.id.tv_user_center_ill);


        filesTextView = view.findViewById(R.id.tv_user_center_files);
        collectTextView = view.findViewById(R.id.tv_user_center_collect);
        equipmetTextView = view.findViewById(R.id.tv_user_center_equipmet);
        doctorTextView = view.findViewById(R.id.tv_user_center_doctor);

        payFrameLayout = view.findViewById(R.id.fl_user_center_wait_pay);
        payCountTextView = view.findViewById(R.id.tv_user_center_wait_pay_num);
        pushFrameLayout = view.findViewById(R.id.fl_user_center_push);
        pushCountTextView = view.findViewById(R.id.tv_user_center_push_num);
        receivedFrameLayout = view.findViewById(R.id.fl_user_center_to_be_received);
        receivedCountTextView = view.findViewById(R.id.tv_user_center_received_num);
        finishFrameLayout = view.findViewById(R.id.fl_user_center_order_finish);
        finishCountTextView = view.findViewById(R.id.tv_user_center_order_finish_num);

        messageTextView = view.findViewById(R.id.tv_user_center_message);
        settingsTextView = view.findViewById(R.id.tv_user_center_set);
    }


    private void initListener() {
        qrCodeTextView.setOnClickListener(this);
        filesTextView.setOnClickListener(this);
        collectTextView.setOnClickListener(this);
        filesTextView.setOnClickListener(this);
        equipmetTextView.setOnClickListener(this);
        doctorTextView.setOnClickListener(this);
        payFrameLayout.setOnClickListener(this);
        pushFrameLayout.setOnClickListener(this);
        receivedFrameLayout.setOnClickListener(this);
        finishFrameLayout.setOnClickListener(this);
        messageTextView.setOnClickListener(this);
        settingsTextView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //qrcode
            case R.id.tv_user_center_qrcode:
                // 跳二维码页面

                intent = new Intent(getPageContext(), UserQRCodeActivity.class);
                intent.putExtra("archivesId", userInfo.getArchivesId());
                intent.putExtra("nickName", userInfo.getNickName());
                intent.putExtra("phoneNumber", userInfo.getPhoneNumber());
                intent.putExtra("avatar", userInfo.getAvatar());
                startActivity(intent);
                break;
            //我的档案
            case R.id.tv_user_center_files:
                startActivity(new Intent(getPageContext(), UserFilesActivity.class));
                break;
            //跳收藏
            case R.id.tv_user_center_collect:
                startActivity(new Intent(getPageContext(), UserCollectActivity.class));
                break;
            //我的设备
            case R.id.tv_user_center_equipmet:
                startActivity(new Intent(getPageContext(), UserEquipmetActivity.class));
                break;
            //我的医生
            case R.id.tv_user_center_doctor:
                if (userInfo != null) {
                    Log.i("yys", "isBindExternal==" + userInfo.isBindDoctorFlag());
                    if (userInfo.isBindDoctorFlag()) {
                        intent = new Intent(getPageContext(), UserDoctorActivity.class);
                        intent.putExtra("type", "1");
                        startActivityForResult(intent, REQUEST_CODE_FOR_UN_BIND_DOCTOR);
                    } else {
                        DialogUtils.showOperDialog(getPageContext(), "", "您还没有绑定医生", "我在想想", "去绑定", (dialog, which) -> {
                            dialog.dismiss();
                            if (XySoftDialogActionEnum.POSITIVE == which) {
                                if (getActivity() != null) {
                                    Intent mainIntent = new Intent(getPageContext(), MainActivity.class);
                                    mainIntent.putExtra("checkId", 2);
                                    startActivity(mainIntent);
                                }
                            }
                        });
                    }
                }


                break;
            //订单
            case R.id.ll_user_center_order:
                intent = new Intent(getPageContext(), UserOrderListActivity.class);
                intent.putExtra("position", 0);
                startActivity(intent);
                break;
            //待付款
            case R.id.fl_user_center_wait_pay:
                intent = new Intent(getPageContext(), UserOrderListActivity.class);
                intent.putExtra("position", 1);
                startActivity(intent);
                break;
            //待发货
            case R.id.fl_user_center_push:
                intent = new Intent(getPageContext(), UserOrderListActivity.class);
                intent.putExtra("position", 2);
                startActivity(intent);
                break;
            //待收货
            case R.id.fl_user_center_to_be_received:
                intent = new Intent(getPageContext(), UserOrderListActivity.class);
                intent.putExtra("position", 3);
                startActivity(intent);
                break;
            //待完成
            case R.id.fl_user_center_order_finish:
                intent = new Intent(getPageContext(), UserOrderListActivity.class);
                intent.putExtra("position", 4);
                startActivity(intent);
                break;

            //消息通知
            case R.id.tv_user_center_message:
                intent = new Intent(getPageContext(), HomeMessageListActivity.class);
                startActivity(intent);
                break;
            //设置
            case R.id.tv_user_center_set:
                startActivity(new Intent(getPageContext(), UserSetActivity.class));
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_FOR_UN_BIND_DOCTOR) {
                SharedPreferencesUtils.saveInfo(getPageContext(), SharedPreferencesConstant.DOCTOR_ID, "");
            }
        }
    }
}

