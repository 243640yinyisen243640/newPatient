package com.vice.bloodpressure.fragment.fuser;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.auser.UserDrinkActivity;
import com.vice.bloodpressure.activity.auser.UserPayStyleActivity;
import com.vice.bloodpressure.activity.auser.UserSmokeActivity;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

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
     * 怀孕时间
     */
    private LinearLayout pregnantTimeLinearLayout;
    /**
     * 怀孕时间
     */
    private View pregnantTimeView;
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

    private UserInfo userInfo;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
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
        smokeTv.setText(("Y".equals(userInfo.getSmokes()) ? "是" : "否"));
        drinkTv.setText(("Y".equals(userInfo.getWine()) ? "是" : "否"));
        pregnantTv.setText(("Y".equals(userInfo.getPregnancy()) ? "是" : "否"));
        if ("1".equals(userInfo.getMarital())) {
            marriageTv.setText("已婚");
        } else if ("2".equals(userInfo.getMarital())) {
            marriageTv.setText("未婚");
        } else {
            marriageTv.setText("其他");
        }
        aloneTv.setText(("Y".equals(userInfo.getLiveAlone()) ? "是" : "否"));
        bedTv.setText(("Y".equals(userInfo.getBedridden()) ? "是" : "否"));

        cultureTv.setText(userInfo.getEducation());
        //1:社会医疗保险 2:新型农村合作医疗保险 3:商业保险 4:城镇居民医疗保险 5:公费医疗 6:自费医疗 7:其他
        if ("1".equals(userInfo.getMedicalPay())) {
            paystyleTv.setText("社会医疗保险");
        } else if ("2".equals(userInfo.getMarital())) {
            paystyleTv.setText("新型农村合作医疗保险");
        } else if ("3".equals(userInfo.getEducation())) {
            paystyleTv.setText("商业保险");
        } else if ("4".equals(userInfo.getMedicalPay())) {
            paystyleTv.setText("城镇居民医疗保险");
        } else if ("5".equals(userInfo.getMedicalPay())) {
            paystyleTv.setText("公费医疗");
        } else if ("6".equals(userInfo.getMedicalPay())) {
            paystyleTv.setText("自费医疗");
        } else {
            paystyleTv.setText("其他");
        }
        hosCardTv.setText(userInfo.getMedicalCard());

    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_live_style, null);
        smokeTv = view.findViewById(R.id.tv_user_live_style_smoke);
        drinkTv = view.findViewById(R.id.tv_user_live_style_drink);
        pregnantTv = view.findViewById(R.id.tv_user_live_style_pregnant);
        pregnantTimeLinearLayout = view.findViewById(R.id.ll_user_live_style_pregnant_time);
        pregnantTimeView = view.findViewById(R.id.view_user_live_style_pregnant_time_line);
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
                intent = new Intent(getPageContext(), UserSmokeActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_SMOKE_STYLE);
                break;
            case R.id.tv_user_live_style_drink:
                intent = new Intent(getPageContext(), UserDrinkActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_DRINK_STYLE);
                break;
            case R.id.tv_user_live_style_pregnant:
                List<BaseLocalDataInfo> pregnantList = new ArrayList<>();
                pregnantList.add(new BaseLocalDataInfo("是", "Y"));
                pregnantList.add(new BaseLocalDataInfo("否", "N"));

                chooseWindow("1", "妊娠", pregnantList);

                break;
            case R.id.tv_user_live_style_pregnant_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        editInfo("4", "pregnancyTime", object.toString(), object.toString());
                    }
                });
                break;
            case R.id.tv_user_live_style_marriage:
                List<BaseLocalDataInfo> marriageList = new ArrayList<>();
                marriageList.add(new BaseLocalDataInfo("已婚", "1"));
                marriageList.add(new BaseLocalDataInfo("未婚", "2"));
                marriageList.add(new BaseLocalDataInfo("其他", "3"));
                chooseWindow("2", "婚姻", marriageList);
                break;
            case R.id.tv_user_live_style_alone:
                List<BaseLocalDataInfo> aloneList = new ArrayList<>();
                aloneList.add(new BaseLocalDataInfo("是", "Y"));
                aloneList.add(new BaseLocalDataInfo("否", "N"));
                aloneList.add(new BaseLocalDataInfo("未知", "3"));

                chooseWindow("3", "独居", aloneList);
                break;
            case R.id.tv_user_live_style_bed:

                List<BaseLocalDataInfo> isInBedList = new ArrayList<>();
                isInBedList.add(new BaseLocalDataInfo("是", "Y"));
                isInBedList.add(new BaseLocalDataInfo("否", "N"));
                isInBedList.add(new BaseLocalDataInfo("未知", "3"));
                chooseWindow("4", "卧床", isInBedList);
                break;
            case R.id.tv_user_live_style_culture:
                List<BaseLocalDataInfo> cultureList = new ArrayList<>();
                cultureList.add(new BaseLocalDataInfo("研究生", "1"));
                cultureList.add(new BaseLocalDataInfo("大学本科", "2"));
                cultureList.add(new BaseLocalDataInfo("大学专科和专科学院", "3"));
                cultureList.add(new BaseLocalDataInfo("中等专业学校", "4"));
                cultureList.add(new BaseLocalDataInfo("技工学校", "5"));
                cultureList.add(new BaseLocalDataInfo("高中", "6"));
                cultureList.add(new BaseLocalDataInfo("初中", "7"));
                cultureList.add(new BaseLocalDataInfo("小学", "8"));
                cultureList.add(new BaseLocalDataInfo("文盲或半文盲", "9"));
                cultureList.add(new BaseLocalDataInfo("未知", "10"));

                chooseWindow("5", "文化程度", cultureList);

                break;
            case R.id.tv_user_live_style_work:
                List<BaseLocalDataInfo> workList = new ArrayList<>();
                workList.add(new BaseLocalDataInfo("轻体力", "1"));
                workList.add(new BaseLocalDataInfo("中体力", "2"));
                workList.add(new BaseLocalDataInfo("重体力", "3"));
                chooseWindow("6", "职业情况", workList);
                break;
            case R.id.tv_user_live_style_pay:
                intent = new Intent(getPageContext(), UserPayStyleActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_user_live_style_hos_card:
                showEditDialog("就诊卡号", "请输入就诊卡号");
                break;
            default:
                break;
        }
    }

    /**
     * @param type  3:是否妊娠 4妊娠时间 5是否结婚 6：是否独居 7：是否卧床 8：文化程度  9：职业情况
     * @param title
     * @param list
     */
    private void chooseWindow(String type, String title, List<BaseLocalDataInfo> list) {
        PickerViewUtils.showChooseSinglePicker(getPageContext(), title, list, object -> {
                    switch (type) {
                        case "3":
                            editInfo("3", "pregnancy", list.get(Integer.parseInt(String.valueOf(object))).getName(), list.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "4":
                            editInfo("3", "pregnancy", list.get(Integer.parseInt(String.valueOf(object))).getName(), list.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "5":
                            editInfo("4", "marital", list.get(Integer.parseInt(String.valueOf(object))).getName(), list.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "6":
                            editInfo("5", "liveAlone", list.get(Integer.parseInt(String.valueOf(object))).getName(), list.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "7":
                            editInfo("6", "bedridden", list.get(Integer.parseInt(String.valueOf(object))).getName(), list.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "8":
                            editInfo("7", "education", list.get(Integer.parseInt(String.valueOf(object))).getName(), list.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "9":
                            editInfo("8", "profession", list.get(Integer.parseInt(String.valueOf(object))).getName(), list.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        default:
                            break;

                    }
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
            String content = msgEditText.getText().toString().trim();
            if (TextUtils.isEmpty(content)) {
                ToastUtils.getInstance().showToast(getPageContext(), msg);
                return;
            }
            editInfo("11", "medicalCard", content, content);
            dialog.dismiss();
        });
        dialog.show();
        new Handler().postDelayed(() -> showInputMethod(), 100);

    }

    /**
     * @param type   3:是否妊娠 4妊娠时间 5是否结婚 6：是否独居 7：是否卧床 8：文化程度  9：职业情况 11就诊卡号
     * @param key
     * @param values
     */
    private void editInfo(String type, String key, String content, String values) {
        Call<String> requestCall = UserDataManager.editUserFilesInfo(UserInfoUtils.getArchivesId(getPageContext()), key, values, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                switch (type) {
                    case "3":
                        pregnantTv.setText(content);
                        if ("Y".equals(values)) {
                            pregnantTimeLinearLayout.setVisibility(View.VISIBLE);
                            pregnantTimeView.setVisibility(View.VISIBLE);
                        } else {
                            pregnantTimeLinearLayout.setVisibility(View.GONE);
                            pregnantTimeView.setVisibility(View.GONE);
                        }
                        break;
                    case "4":
                        pregnantTimeTv.setText(values);
                        break;
                    case "5":
                        marriageTv.setText(values);
                        break;
                    case "6":
                        aloneTv.setText(values);
                        break;
                    case "7":
                        bedTv.setText(values);
                        break;
                    case "8":
                        cultureTv.setText(values);
                        break;
                    case "9":
                        workTv.setText(values);
                        break;
                    case "11":
                        hosCardTv.setText(values);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FOR_SMOKE_STYLE:
                    if (data != null) {
                        String isSmoke = data.getStringExtra("isCheck");
                        String smokeNum = data.getStringExtra("smokeNum");
                        if ("1".equals(isSmoke)) {
                            smokeTv.setText("是 " + smokeNum + "日/支");
                        } else {
                            smokeTv.setText("否");
                        }
                    }


                    break;
                case REQUEST_CODE_FOR_DRINK_STYLE:
                    if (data != null) {
                        String isDrink = data.getStringExtra("isCheck");
                        String drinkNum = data.getStringExtra("drinkNum");
                        String drinkType = data.getStringExtra("drinkType");
                        String drinkName = data.getStringExtra("drinkName");
                        if ("1".equals(isDrink)) {
                            drinkTv.setText("是 " + drinkName + " " + drinkNum + "ml/日");
                        } else {
                            drinkTv.setText("否");
                        }
                    }
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
