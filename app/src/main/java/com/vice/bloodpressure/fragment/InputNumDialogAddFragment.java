package com.vice.bloodpressure.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.utils.ToastUtils;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class InputNumDialogAddFragment extends DialogFragment {

    private Dialog dialog;
    private EditText hourEditText;
    private EditText minEditText;
    private EditText secondEditText;
    private View contentView;
    private Handler handler = new Handler();
    private IInputFinishCallback callback;
    private ImageView cancel;
    private TextView submit;

    public interface IInputFinishCallback {
        void sendStr(String inputStr);
    }

    public InputNumDialogAddFragment(IInputFinishCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.Dialog_NoTitle);
        initView();
        setDialgParms();
        initEvent();
        return dialog;
    }

    private void initEvent() {
        submit.setOnClickListener(v -> {
            String hour = hourEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(hour) && hour.length() <= 200) {
                callback.sendStr(hour);
                dialog.dismiss();
            } else {
                ToastUtils.getInstance().showToast(getContext(), "请输入小时");
            }
            String min = minEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(min) && min.length() <= 200) {
                callback.sendStr(min);
                dialog.dismiss();
            } else {
                ToastUtils.getInstance().showToast(getContext(), "请输入分钟");
            }
            String second = secondEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(second) && second.length() <= 200) {
                callback.sendStr(second);
                dialog.dismiss();
            } else {
                ToastUtils.getInstance().showToast(getContext(), "请输入秒");
            }
        });
        cancel.setOnClickListener(v -> {

            dialog.dismiss();
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public InputMethodManager mInputMethodManager;

            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideSoftkeyboard();
                    }
                }, 200);
            }
        });
    }

    private void setDialgParms() {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wml = window.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        wml.width = dm.widthPixels;
        wml.gravity = Gravity.BOTTOM;
        window.setAttributes(wml);
        hourEditText.setFocusable(true);
        hourEditText.setFocusableInTouchMode(true);
    }

    private void initView() {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_exercise_plan_hand_flexibility_add, null);
        hourEditText = contentView.findViewById(R.id.et_exercise_hand_hour);
        minEditText = contentView.findViewById(R.id.et_exercise_hand_min);
        secondEditText = contentView.findViewById(R.id.et_exercise_hand_second);
        cancel = contentView.findViewById(R.id.iv_hand_add_close);
        submit = contentView.findViewById(R.id.tv_exercise_hand_sure);
        dialog.setContentView(contentView);
    }

    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

    public void hideSoftkeyboard() {
        try {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showKeyboard(contentView);
            }
        }, 300);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        if (callback != null) {
            callback = null;
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
