package com.vice.bloodpressure.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
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
public class InputNumDialogFragment extends DialogFragment {

    private Dialog dialog;
    private EditText editText;
    private View contentView;
    private Handler handler = new Handler();
    private IInputFinishCallback callback;
    private ImageView cancel;
    private TextView submit;
    private TextView titleTv;

    private String titleMsg;
    private static InputMethodManager imm;

    public interface IInputFinishCallback {
        void sendStr(String inputStr);
    }

    public InputNumDialogFragment(String mTitle,IInputFinishCallback callback) {
        this.callback = callback;
        this.titleMsg = mTitle;
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
            String trim = editText.getText().toString().trim();
            if (!TextUtils.isEmpty(trim) && trim.length() <= 200) {
                hideSoftKeyboard();
                callback.sendStr(trim);
                dialog.dismiss();
            } else {
                ToastUtils.getInstance().showToast(getContext(), "输入为空");
            }
        });
        cancel.setOnClickListener(v -> {
            hideSoftKeyboard();
            dialog.dismiss();
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public InputMethodManager mInputMethodManager;

            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideSoftKeyboard();
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
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
    }

    private void initView() {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_exercise_plan_hand_add, null);
        editText = contentView.findViewById(R.id.et_exercise_hand_num);
        cancel = contentView.findViewById(R.id.iv_hand_add_close);
        submit = contentView.findViewById(R.id.tv_exercise_hand_sure);
        titleTv = contentView.findViewById(R.id.tv_exercise_success_content);
        titleTv.setText(titleMsg);
        dialog.setContentView(contentView);
    }

    public static void showKeyboard(View view) {
        imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

  /*  public void hideSoftkeyboard() {
        try {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {
            Log.e("LYY","LYY---错误信息：" + e.getMessage());
        }
    }*/

    /**
     * 隐藏软键盘
     */
    public void hideSoftKeyboard() {
        editText.requestFocus();
        if (editText.hasFocus()) {
            // 只有当EditText有焦点时才尝试隐藏键盘
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } else {
            Log.w("LYY", "LYY--- EditText doesn't have focus when trying to hide keyboard.");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showKeyboard(editText);
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
