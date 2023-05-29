package com.vice.bloodpressure.fragment.fuser;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.addresspickerlib.ProvinceBean;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.popwindow.ShowCityPopupWindow;
import com.vice.bloodpressure.utils.PickerViewUtils;
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
    /**
     * 身份证
     */
    private TextView idCardTv;
    /**
     * 出生年月
     */
    private TextView bornTv;
    /**
     * 年龄
     */
    private TextView ageTv;
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

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();

        initView();
        initData();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }


    @Override
    protected void onPageLoad() {
        Call<String> requestCall = UserDataManager.getUserFilesInfo(UserInfoUtils.getArchivesId(getPageContext()), "1", (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                UserInfo userInfo = (UserInfo) response.object;
                bindData(userInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getSelectDoctorInfo", requestCall);
    }

    private void bindData(UserInfo userInfo) {
        nameTv.setText(userInfo.getNickName());
        idCardTv.setText(userInfo.getIdCard());
        bornTv.setText(userInfo.getBedridden());
        ageTv.setText(userInfo.getAge());
        sexTv.setText(("1".equals(userInfo.getSex()) ? "男" : "女"));
        cityTv.setText(userInfo.getNativePlace());
        sosNameTv.setText(userInfo.getEmergency());
        sosPhoneTv.setText(userInfo.getTel());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_user_base_info_sex:
                chooseSexWindow();
                break;
            case R.id.tv_user_base_info_city:
                cityPopupWindow = new ShowCityPopupWindow(getPageContext(),
                        (address, province, city, district) -> {

                            Log.i("yys", "address==" + address);
                            Log.i("yys", "provinceCode==" + province);
                            Log.i("yys", "cityCode==" + city);
                            Log.i("yys", "districtCode==" + district);
                        });
                cityPopupWindow.showAsDropDown(containerView(), 0, 0, Gravity.BOTTOM);
                break;
            case R.id.tv_user_base_info_sos_name:
                showEditDialog("1", "紧急联系人", "");
                break;
            case R.id.tv_user_base_info_sos_phone:
                showEditDialog("2", "联系方式", "");
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
     * @param title
     * @param msg
     */
    private void showEditDialog(String type, String title, String msg) {
        Dialog dialog = new Dialog(getPageContext(), R.style.HuaHanSoft_Dialog_Base);
        View view = View.inflate(getPageContext(), R.layout.activity_user_info_dialog, null);
        TextView titleTextView = getViewByID(view, R.id.tv_dialog_title);
        EditText msgEditText = getViewByID(view, R.id.tv_dialog_msg);
        TextView cancelTextView = getViewByID(view, R.id.tv_dialog_cancel);
        TextView sureTextView = getViewByID(view, R.id.tv_dialog_sure);

        titleTextView.setText(title);
        if ("1".equals(type)) {
            if (TextUtils.isEmpty(msgEditText.getText())) {
                msgEditText.setHint("请输入联系人姓名");
            } else {
                msgEditText.setSelection(msg.length());
                msgEditText.setText(msg);
            }
        } else {
            if (TextUtils.isEmpty(msgEditText.getText())) {
                msgEditText.setHint("请输入联系人电话");
            } else {
                msgEditText.setSelection(msg.length());
                msgEditText.setText(msg);
            }
        }


        //设置14个字长
        if ("1".equals(type)) {
            msgEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            msgEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        }
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
}
