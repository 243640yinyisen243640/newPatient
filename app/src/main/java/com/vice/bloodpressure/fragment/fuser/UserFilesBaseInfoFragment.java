package com.vice.bloodpressure.fragment.fuser;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserFilesBaseInfoFragment extends UIBaseLoadFragment implements View.OnClickListener {
    private TextView nameTv;
    private TextView idCardTv;
    private TextView bornTv;
    private TextView ageTv;
    private TextView sexTv;
    private TextView cityTv;
    private TextView sosNameTv;
    private TextView sosPhoneTv;

    @Override
    protected void onCreate() {
        initView();
        initListener();
    }

    private void initListener() {
        sexTv.setOnClickListener(this);
        cityTv.setOnClickListener(this);
        sosNameTv.setOnClickListener(this);
        sosPhoneTv.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_base_info, null);
        nameTv = view.findViewById(R.id.tv_user_base_info_name);
        idCardTv = view.findViewById(R.id.tv_user_base_info_id);
        bornTv = view.findViewById(R.id.tv_user_base_info_born);
        ageTv = view.findViewById(R.id.tv_user_base_info_age);
        sexTv = view.findViewById(R.id.tv_user_base_info_sex);
        cityTv = view.findViewById(R.id.tv_user_base_info_city);
        sosNameTv = view.findViewById(R.id.tv_user_base_info_sos_name);
        sosPhoneTv = view.findViewById(R.id.tv_user_base_info_sos_phone);
        containerView().addView(view);
    }


    @Override
    protected void onPageLoad() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_user_base_info_sex:
                chooseSexWindow();
                break;
            case R.id.tv_user_base_info_city:
                break;
            case R.id.tv_user_base_info_sos_name:
                showEditDialog("紧急联系人", "请输入紧急联系人名称");
                break;
            case R.id.tv_user_base_info_sos_phone:
                showEditDialog("联系方式", "请输入联系方式");
                break;
            default:
                break;
        }
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showInputMethod();
            }
        }, 100);

    }


    private void showInputMethod() {
        //自动弹出键盘
        InputMethodManager inputManager = (InputMethodManager) getPageContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //强制隐藏Android输入法窗口
        // inputManager.hideSoftInputFromWindow(edit.getWindowToken(),0);
    }

    /**
     * 选择运动类型
     */
    private void chooseSexWindow() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("男");
        exerciseList.add("女");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "性别", exerciseList, object -> {
                    sexTv.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
                    String sex = exerciseList.get(Integer.parseInt(String.valueOf(object)));
                }
        );
    }
}
