package com.vice.bloodpressure.fragment.fuser;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.auser.UserSmokeDrinkActivity;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserFilesLiveStyleFragment extends UIBaseLoadFragment implements View.OnClickListener {
    /**
     * 抽烟
     */
    private static final int REQUEST_CODE_FOR_SMOKE_STYLE = 1;
    /**
     * 喝酒
     */
    private static final int REQUEST_CODE_FOR_DRINK_STYLE = 2;
    /**
     * 是否吸烟
     */
    private TextView smokeTv;
    /**
     * 是否喝酒
     */
    private TextView drinkTv;
    /**
     * 是否怀孕
     */
    private TextView pregnantTv;
    /**
     * 怀孕时间
     */
    private TextView pregnantTimeTv;
    /**
     * 是否结婚
     */
    private TextView marriageTv;
    /**
     * 是否独居
     */
    private TextView aloneTv;
    /**
     * 是否卧床
     */
    private TextView bedTv;
    /**
     * 文化程度
     */
    private TextView cultureTv;
    /**
     * 职业情况
     */
    private TextView workTv;
    /**
     * 支付方式
     */
    private TextView paystyleTv;
    /**
     * 就诊卡号
     */
    private TextView hosCardTv;

    @Override
    protected void onCreate() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_live_style, null);
        containerView().addView(view);

        initView();
        initListener();
    }

    private void initListener() {
        smokeTv.setOnClickListener(this);
        drinkTv.setOnClickListener(this);
        pregnantTv.setOnClickListener(this);
        pregnantTimeTv.setOnClickListener(this);
        marriageTv.setOnClickListener(this);
        aloneTv.setOnClickListener(this);
        bedTv.setOnClickListener(this);
        cultureTv.setOnClickListener(this);
        workTv.setOnClickListener(this);
        paystyleTv.setOnClickListener(this);
        hosCardTv.setOnClickListener(this);
    }


    @Override
    protected void onPageLoad() {

    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_live_style, null);
        smokeTv = view.findViewById(R.id.tv_user_live_style_smoke);
        drinkTv = view.findViewById(R.id.tv_user_live_style_drink);
        pregnantTv = view.findViewById(R.id.tv_user_live_style_pregnant);
        pregnantTimeTv = view.findViewById(R.id.tv_user_live_style_pregnant_time);
        marriageTv = view.findViewById(R.id.tv_user_live_style_marriage);
        aloneTv = view.findViewById(R.id.tv_user_live_style_alone);
        bedTv = view.findViewById(R.id.tv_user_live_style_bed);
        cultureTv = view.findViewById(R.id.tv_user_live_style_culture);
        workTv = view.findViewById(R.id.tv_user_live_style_work);
        paystyleTv = view.findViewById(R.id.tv_user_live_style_pay);
        hosCardTv = view.findViewById(R.id.tv_user_live_style_hos_card);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_user_live_style_smoke:
                intent = new Intent(getPageContext(), UserSmokeDrinkActivity.class);
                intent.putExtra("type", "1");
                startActivityForResult(intent, REQUEST_CODE_FOR_SMOKE_STYLE);
                break;
            case R.id.tv_user_live_style_drink:
                intent = new Intent(getPageContext(), UserSmokeDrinkActivity.class);
                intent.putExtra("type", "2");
                startActivityForResult(intent, REQUEST_CODE_FOR_DRINK_STYLE);
                break;
            case R.id.tv_user_live_style_pregnant:
                showEditDialog("紧急联系人", "请输入紧急联系人名称");
                break;
            case R.id.tv_user_live_style_pregnant_time:
                showEditDialog("联系方式", "请输入联系方式");
                break;
            case R.id.tv_user_live_style_marriage:
                showEditDialog("联系方式", "请输入联系方式");
                break;
            case R.id.tv_user_live_style_alone:
                showEditDialog("联系方式", "请输入联系方式");
                break;
            case R.id.tv_user_live_style_bed:
                showEditDialog("联系方式", "请输入联系方式");
                break;
            case R.id.tv_user_live_style_culture:
                showEditDialog("联系方式", "请输入联系方式");
                break;
            case R.id.tv_user_live_style_work:
                showEditDialog("联系方式", "请输入联系方式");
                break;
            case R.id.tv_user_live_style_pay:
                showEditDialog("联系方式", "请输入联系方式");
                break;
            case R.id.tv_user_live_style_hos_card:
                showEditDialog("联系方式", "请输入联系方式");
                break;

            default:
                break;
        }
    }

    /**
     *
     */
    private void chooseSexWindow() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("是");
        exerciseList.add("否");
        PickerViewUtils.showChooseSinglePicker(getPageContext(), "有氧运动", exerciseList, object -> {
                    //sexTv.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
                    String sex = exerciseList.get(Integer.parseInt(String.valueOf(object)));
                }
        );
    }

    /**
     * 显示编辑框
     */
    private void showEditDialog(String title, String msg) {

        Dialog dialog = new Dialog(getPageContext(), R.style.HuaHanSoft_Dialog_Base);
        View view = View.inflate(getPageContext(), R.layout.activity_user_info_dialog, null);
        TextView titleTextView = getViewByID(view, R.id.tv_dialog_title);
        EditText msgEditText = getViewByID(view, R.id.tv_dialog_msg);
        TextView cancelTextView = getViewByID(view, R.id.tv_dialog_cancel);
        TextView sureTextView = getViewByID(view, R.id.tv_dialog_sure);

        titleTextView.setText(title);
        msgEditText.setText(msg);
        //  msgEditText.setSelection(msg.length());
        //设置14个字长
        msgEditText.setMaxWidth(11);
        dialog.setContentView(view);
        android.view.WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = ScreenUtils.screenWidth(getPageContext()) - ScreenUtils.dip2px(getPageContext(), 1);
        dialog.getWindow().setAttributes(attributes);
        cancelTextView.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            dialog.dismiss();
        });
        sureTextView.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            String nick = msgEditText.getText().toString().trim();
            if (TextUtils.isEmpty(nick)) {
                ToastUtils.getInstance().showToast(getPageContext(), msg);
                return;
            }
            dialog.dismiss();
        });
        dialog.show();
        new Handler().postDelayed(() -> showInputMethod(), 100);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FOR_SMOKE_STYLE:

                    break;
                case REQUEST_CODE_FOR_DRINK_STYLE:
                    break;
                default:
                    break;
            }
        }
    }



    private void showInputMethod() {
        //自动弹出键盘
        InputMethodManager inputManager = (InputMethodManager) getPageContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //强制隐藏Android输入法窗口
        // inputManager.hideSoftInputFromWindow(edit.getWindowToken(),0);
    }
}
