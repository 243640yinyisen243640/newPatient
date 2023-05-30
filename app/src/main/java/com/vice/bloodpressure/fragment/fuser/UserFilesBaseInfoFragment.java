package com.vice.bloodpressure.fragment.fuser;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.addresspickerlib.ProvinceBean;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.popwindow.ShowCityPopupWindow;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserFilesBaseInfoFragment extends UIBaseLoadFragment implements View.OnClickListener {
    private TextView nameTv;
    private LinearLayout nameLinearLayout;
    /**
     * 身份证
     */
    private TextView idCardTv;
    private LinearLayout idCardLinearLayout;
    /**
     * 出生年月
     */
    private TextView bornTv;
    private LinearLayout bornLinearLayout;
    /**
     * 年龄
     */
    private TextView ageTv;
    private LinearLayout ageLinearLayout;
    /**
     * 性别
     */
    private TextView sexTv;
    /**
     * 籍贯
     */
    private TextView cityTv;
    /**
     * 紧急联系人
     */
    private TextView sosNameTv;
    /**
     * 紧急联系人电话
     */
    private TextView sosPhoneTv;

    private ShowCityPopupWindow cityPopupWindow;
    private List<ProvinceBean> mYwpAddressBean;

    private UserInfo userInfo;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();

        initView();
        initData();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }


    @Override
    protected void onPageLoad() {
        Call<String> requestCall = UserDataManager.getUserFilesInfo(UserInfoUtils.getArchivesId(getPageContext()), "1", (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                userInfo = (UserInfo) response.object;
                initListener();
                bindData();
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getSelectDoctorInfo", requestCall);
    }

    private void bindData() {
        nameTv.setText(userInfo.getNickName());
        idCardTv.setText(userInfo.getIdCard());
        bornTv.setText(userInfo.getBirthday());
        ageTv.setText(userInfo.getAge());
        sexTv.setText(("1".equals(userInfo.getSex()) ? "男" : "女"));
        cityTv.setText(userInfo.getNativePlace());
        sosNameTv.setText(userInfo.getEmergency());
        sosPhoneTv.setText(userInfo.getTel());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_user_base_info_name:
                showEditDialog("nickName", "1", "姓名", userInfo.getNickName());
                break;
            case R.id.ll_user_base_info_id:
                showEditDialog("idCard", "2", "身份证号", userInfo.getIdCard());
                break;

            case R.id.ll_user_base_info_born:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        editInfo("3", "birthday", object.toString());
                    }
                });
                break;
            case R.id.ll_user_base_info_age:
                showEditDialog("age", "4", "年龄", userInfo.getAge());
                break;
            case R.id.tv_user_base_info_sex:
                chooseSexWindow();
                break;
            case R.id.tv_user_base_info_city:
                cityPopupWindow = new ShowCityPopupWindow(getPageContext(),
                        (address, province, city, district) -> {
                            editInfo("6", "nativePlace", address);
                            cityPopupWindow.dismiss();
                        });
                cityPopupWindow.showAsDropDown(containerView(), 0, 0, Gravity.BOTTOM);
                break;

            case R.id.tv_user_base_info_sos_name:
                showEditDialog("emergency", "7", "紧急联系人", userInfo.getEmergency());
                break;

            case R.id.tv_user_base_info_sos_phone:
                showEditDialog("tel", "8", "联系方式", userInfo.getTel());
                break;
            default:
                break;
        }
    }

    /**
     * 初始化数据
     * 拿assets下的json文件
     */
    private void initData() {
        StringBuilder jsonSB = new StringBuilder();
        try {
            BufferedReader addressJsonStream = new BufferedReader(new InputStreamReader(getPageContext().getAssets().open("province.json")));
            String line;
            while ((line = addressJsonStream.readLine()) != null) {
                jsonSB.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将数据转换为对象
        Type type = new TypeToken<List<ProvinceBean>>() {
        }.getType();
        mYwpAddressBean = new Gson().fromJson(jsonSB.toString(), type);
    }


    /**
     * @param type  1:联系人  2：联系电话
     * @param key   调取接口的key值
     * @param title
     * @param msg
     */
    private void showEditDialog(String key, String type, String title, String msg) {
        Dialog dialog = new Dialog(getPageContext(), R.style.HuaHanSoft_Dialog_Base);
        View view = View.inflate(getPageContext(), R.layout.activity_user_info_dialog, null);
        TextView titleTextView = getViewByID(view, R.id.tv_dialog_title);
        EditText msgEditText = getViewByID(view, R.id.tv_dialog_msg);
        TextView cancelTextView = getViewByID(view, R.id.tv_dialog_cancel);
        TextView sureTextView = getViewByID(view, R.id.tv_dialog_sure);

        titleTextView.setText(title);
        if ("1".equals(type)) {
            if (TextUtils.isEmpty(msg)) {
                msgEditText.setHint("请输入姓名");
            } else {
                msgEditText.setText(msg);
                msgEditText.setSelection(msg.length());
            }
        } else if ("2".equals(type)) {
            if (TextUtils.isEmpty(msg)) {
                msgEditText.setHint("请输入身份证号");
            } else {
                msgEditText.setText(msg);
                msgEditText.setSelection(msg.length());

            }
        } else if ("4".equals(type)) {
            if (TextUtils.isEmpty(msg)) {
                msgEditText.setHint("请输入年龄");
            } else {
                msgEditText.setText(msg);
                msgEditText.setSelection(msg.length());

            }
        } else if ("7".equals(type)) {
            if (TextUtils.isEmpty(msg)) {
                msgEditText.setHint("请输入联系人姓名");
            } else {
                msgEditText.setText(msg);
                msgEditText.setSelection(msg.length());

            }
        } else {
            if (TextUtils.isEmpty(msg)) {
                msgEditText.setHint("请输入联系人电话");
            } else {
                msgEditText.setText(msg);
                msgEditText.setSelection(msg.length());

            }
        }


        //设置14个字长
        if ("1".equals(type)) {
            msgEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            msgEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        } else if ("2".equals(type)) {
            msgEditText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
            msgEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789X"));
            msgEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});

        } else if ("4".equals(type)) {
            msgEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            msgEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        } else if ("7".equals(type)) {
            msgEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            msgEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

        } else {
            msgEditText.setInputType(InputType.TYPE_CLASS_PHONE);
            msgEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        }
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

            editInfo(type, key, msgEditText.getText().toString().trim());
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

    /**
     * @param type   1姓名  2身份证号 3出生年月 4年龄 5性别 6籍贯 7紧急联系人  8紧急联系人电话
     * @param key    上传接口的key
     * @param values 上传接口的value
     */
    private void editInfo(String type, String key, String values) {
        Call<String> requestCall = UserDataManager.editUserFilesInfo(UserInfoUtils.getArchivesId(getPageContext()), key, values, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                switch (type) {
                    case "1":
                        nameTv.setText(values);
                        break;
                    case "2":
                        idCardTv.setText(values);
                        break;
                    case "3":
                        bornTv.setText(values);
                        break;
                    case "4":
                        ageTv.setText(values);
                        break;
                    case "5":
                        sexTv.setText((values.equals("1") ? "男" : "女"));
                        break;
                    case "6":
                        cityTv.setText(values);
                        break;
                    case "7":
                        sosNameTv.setText(values);
                        break;
                    case "8":
                        sosPhoneTv.setText(values);
                        break;
                    default:
                        break;
                }
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("editUserFilesInfo", requestCall);
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
                    editInfo("5", "sex", (Integer.parseInt(String.valueOf(object)) + 1) + "");
                }
        );
    }

    private void initListener() {
        nameLinearLayout.setOnClickListener(this);
        idCardLinearLayout.setOnClickListener(this);
        bornLinearLayout.setOnClickListener(this);
        ageLinearLayout.setOnClickListener(this);
        sexTv.setOnClickListener(this);
        cityTv.setOnClickListener(this);
        sosNameTv.setOnClickListener(this);
        sosPhoneTv.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_base_info, null);
        nameLinearLayout = view.findViewById(R.id.ll_user_base_info_name);
        nameTv = view.findViewById(R.id.tv_user_base_info_name);
        idCardLinearLayout = view.findViewById(R.id.ll_user_base_info_id);
        idCardTv = view.findViewById(R.id.tv_user_base_info_id);
        bornLinearLayout = view.findViewById(R.id.ll_user_base_info_born);
        bornTv = view.findViewById(R.id.tv_user_base_info_born);
        ageLinearLayout = view.findViewById(R.id.ll_user_base_info_age);
        ageTv = view.findViewById(R.id.tv_user_base_info_age);
        sexTv = view.findViewById(R.id.tv_user_base_info_sex);
        cityTv = view.findViewById(R.id.tv_user_base_info_city);
        sosNameTv = view.findViewById(R.id.tv_user_base_info_sos_name);
        sosPhoneTv = view.findViewById(R.id.tv_user_base_info_sos_phone);
        containerView().addView(view);
    }
}
