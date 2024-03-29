package com.vice.bloodpressure.fragment.fuser;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
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
    private static final int REQUEST_CODE_FOR_PAY_STYLE = 3;
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
        Call<String> requestCall = UserDataManager.getUserFilesInfo(UserInfoUtils.getArchivesId(getPageContext()), "2", (call, response) -> {
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
        addRequestCallToMap("getUserFilesInfo", requestCall);
    }

    private void bindData() {
        if (userInfo.getSmokes() == null) {
            smokeTv.setText("请选择");
        } else {
            if ("Y".equals(userInfo.getSmokes())) {
                smokeTv.setText("是 " + userInfo.getSmokesNum() + "支/日");
            } else {
                smokeTv.setText("否");
            }
        }


        //饮酒类型:1->红酒;2->啤酒;3->白酒; 4 黄酒
        if (userInfo.getWine() == null) {
            drinkTv.setText("请选择");
        } else {
            if ("Y".equals(userInfo.getWine())) {
                if ("1".equals(userInfo.getWineType())) {
                    drinkTv.setText("是 " + " 红酒" + userInfo.getWineDose() + "ml/日");
                } else if ("2".equals(userInfo.getWineType())) {
                    drinkTv.setText("是 " + " 啤酒" + userInfo.getWineDose() + "ml/日");
                } else if ("3".equals(userInfo.getWineType())) {
                    drinkTv.setText("是 " + " 白酒" + userInfo.getWineDose() + "ml/日");
                } else {
                    drinkTv.setText("是 " + " 黄酒" + userInfo.getWineDose() + "ml/日");
                }
            } else {
                drinkTv.setText("否");
            }
        }
        if (userInfo.getPregnancy() == null) {
            pregnantTv.setText("请选择");
            pregnantTimeLinearLayout.setVisibility(View.GONE);
        } else {
            if ("Y".equals(userInfo.getPregnancy())) {
                pregnantTv.setText("是");
                pregnantTimeLinearLayout.setVisibility(View.VISIBLE);
                pregnantTimeTv.setText(userInfo.getPregnancyTime());
            } else {
                pregnantTv.setText("否");
                pregnantTimeLinearLayout.setVisibility(View.GONE);
            }
        }
        if (userInfo.getMarital() == null) {
            marriageTv.setText("请选择");
        } else {
            if ("1".equals(userInfo.getMarital())) {
                marriageTv.setText("已婚");
            } else if ("2".equals(userInfo.getMarital())) {
                marriageTv.setText("未婚");
            } else {
                marriageTv.setText("其他");
            }
        }

        if (userInfo.getLiveAlone() == null) {
            aloneTv.setText("请选择");
        } else {
            aloneTv.setText(("Y".equals(userInfo.getLiveAlone()) ? "是" : "否"));
        }

        if (userInfo.getBedridden() == null) {
            bedTv.setText("请选择");
        } else {
            bedTv.setText(("Y".equals(userInfo.getBedridden()) ? "是" : "否"));
        }

        if (userInfo.getEducation() == null) {
            cultureTv.setText("请选择");
        } else {
            //1:研究生 2:大学本科 3大学专科和专科学院 4中等专业学校5:技工学校6:高中7:初中8:小学9:文盲或半文盲10:未知
            if ("1".equals(userInfo.getEducation())) {
                cultureTv.setText("研究生");
            } else if ("2".equals(userInfo.getEducation())) {
                cultureTv.setText("大学本科");
            } else if ("3".equals(userInfo.getEducation())) {
                cultureTv.setText("大学专科和专科学院");
            } else if ("4".equals(userInfo.getEducation())) {
                cultureTv.setText("中等专业学校5");
            } else if ("5".equals(userInfo.getEducation())) {
                cultureTv.setText("技工学校");
            } else if ("6".equals(userInfo.getEducation())) {
                cultureTv.setText("高中");
            } else if ("7".equals(userInfo.getEducation())) {
                cultureTv.setText("初中");
            } else if ("8".equals(userInfo.getEducation())) {
                cultureTv.setText("小学");
            } else if ("9".equals(userInfo.getEducation())) {
                cultureTv.setText("文盲或半文盲");
            } else {
                cultureTv.setText("未知");
            }
        }
        if (userInfo.getProfession() == null) {
            workTv.setText("请选择");
        } else {
            if ("1".equals(userInfo.getProfession())) {
                workTv.setText("轻体力");
            } else if ("2".equals(userInfo.getProfession())) {
                workTv.setText("中体力");
            } else {
                workTv.setText("重体力");
            }
        }

        if (userInfo.getMedicalPay() == null) {
            paystyleTv.setText("请选择");
        } else {
            //1:社会医疗保险 2:新型农村合作医疗保险 3:商业保险 4:城镇居民医疗保险 5:公费医疗 6:自费医疗 7:其他
            if ("1".equals(userInfo.getMedicalPay())) {
                paystyleTv.setText("社会医疗保险");
            } else if ("2".equals(userInfo.getMedicalPay())) {
                paystyleTv.setText("新型农村合作医疗保险");
            } else if ("3".equals(userInfo.getMedicalPay())) {
                paystyleTv.setText("商业保险");
            } else if ("4".equals(userInfo.getMedicalPay())) {
                paystyleTv.setText("城镇居民医疗保险");
            } else if ("5".equals(userInfo.getMedicalPay())) {
                paystyleTv.setText("公费医疗");
            } else if ("6".equals(userInfo.getMedicalPay())) {
                paystyleTv.setText("自费医疗");
            } else {
                paystyleTv.setText("其他险");
            }
        }
        if (userInfo.getMedicalCard() == null) {
            hosCardTv.setText("请输入就诊卡号");
        } else {
            hosCardTv.setText(userInfo.getMedicalCard());
        }

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
                List<BaseLocalDataInfo> allPregnantList = new ArrayList<>();
                allPregnantList.add(new BaseLocalDataInfo("是", "Y"));
                allPregnantList.add(new BaseLocalDataInfo("否", "N"));
                allPregnantList.add(new BaseLocalDataInfo("未知", "3"));
                List<String> pregnantList = new ArrayList<>();
                for (int i = 0; i < allPregnantList.size(); i++) {
                    pregnantList.add(allPregnantList.get(i).getName());
                }

                chooseWindow("3", "妊娠", allPregnantList, pregnantList);

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
                List<BaseLocalDataInfo> allMarriageList = new ArrayList<>();
                allMarriageList.add(new BaseLocalDataInfo("已婚", "1"));
                allMarriageList.add(new BaseLocalDataInfo("未婚", "2"));
                allMarriageList.add(new BaseLocalDataInfo("未知", "3"));
                List<String> marriageList = new ArrayList<>();
                for (int i = 0; i < allMarriageList.size(); i++) {
                    marriageList.add(allMarriageList.get(i).getName());
                }

                chooseWindow("5", "婚姻", allMarriageList, marriageList);
                break;
            case R.id.tv_user_live_style_alone:
                List<BaseLocalDataInfo> allAloneList = new ArrayList<>();
                allAloneList.add(new BaseLocalDataInfo("是", "Y"));
                allAloneList.add(new BaseLocalDataInfo("否", "N"));
                allAloneList.add(new BaseLocalDataInfo("未知", "3"));
                List<String> aloneList = new ArrayList<>();
                for (int i = 0; i < allAloneList.size(); i++) {
                    aloneList.add(allAloneList.get(i).getName());
                }

                chooseWindow("6", "独居", allAloneList, aloneList);
                break;
            case R.id.tv_user_live_style_bed:

                List<BaseLocalDataInfo> allIsInBedList = new ArrayList<>();
                allIsInBedList.add(new BaseLocalDataInfo("是", "Y"));
                allIsInBedList.add(new BaseLocalDataInfo("否", "N"));
                allIsInBedList.add(new BaseLocalDataInfo("未知", "3"));

                List<String> isInBedList = new ArrayList<>();
                for (int i = 0; i < allIsInBedList.size(); i++) {
                    isInBedList.add(allIsInBedList.get(i).getName());
                }
                chooseWindow("7", "卧床", allIsInBedList, isInBedList);
                break;
            case R.id.tv_user_live_style_culture:
                List<BaseLocalDataInfo> allCultureList = new ArrayList<>();
                allCultureList.add(new BaseLocalDataInfo("研究生", "1"));
                allCultureList.add(new BaseLocalDataInfo("大学本科", "2"));
                allCultureList.add(new BaseLocalDataInfo("大学专科和专科学院", "3"));
                allCultureList.add(new BaseLocalDataInfo("中等专业学校", "4"));
                allCultureList.add(new BaseLocalDataInfo("技工学校", "5"));
                allCultureList.add(new BaseLocalDataInfo("高中", "6"));
                allCultureList.add(new BaseLocalDataInfo("初中", "7"));
                allCultureList.add(new BaseLocalDataInfo("小学", "8"));
                allCultureList.add(new BaseLocalDataInfo("文盲或半文盲", "9"));
                allCultureList.add(new BaseLocalDataInfo("未知", "10"));
                List<String> cultureList = new ArrayList<>();
                for (int i = 0; i < allCultureList.size(); i++) {
                    cultureList.add(allCultureList.get(i).getName());
                }


                chooseWindow("8", "文化程度", allCultureList, cultureList);

                break;
            case R.id.tv_user_live_style_work:
                List<BaseLocalDataInfo> allWorkList = new ArrayList<>();
                allWorkList.add(new BaseLocalDataInfo("轻体力", "1"));
                allWorkList.add(new BaseLocalDataInfo("中体力", "2"));
                allWorkList.add(new BaseLocalDataInfo("重体力", "3"));
                List<String> workList = new ArrayList<>();
                for (int i = 0; i < allWorkList.size(); i++) {
                    workList.add(allWorkList.get(i).getName());
                }
                chooseWindow("9", "职业情况", allWorkList, workList);
                break;
            case R.id.tv_user_live_style_pay:
                intent = new Intent(getPageContext(), UserPayStyleActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_PAY_STYLE);
                break;
            case R.id.tv_user_live_style_hos_card:
                showEditDialog("就诊卡号", userInfo.getMedicalCard());
                break;
            default:
                break;
        }
    }

    /**
     * @param type  3:是否妊娠 5是否结婚 6：是否独居 7：是否卧床 8：文化程度  9：职业情况
     * @param title
     * @param list
     */
    private void chooseWindow(String type, String title, List<BaseLocalDataInfo> allList, List<String> list) {
        PickerViewUtils.showChooseSinglePicker(getPageContext(), title, list, object -> {
                    switch (type) {
                        case "3":
                            editInfo("3", "pregnancy", allList.get(Integer.parseInt(String.valueOf(object))).getName(), allList.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;

                        case "5":
                            editInfo("5", "marital", allList.get(Integer.parseInt(String.valueOf(object))).getName(), allList.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "6":
                            editInfo("6", "liveAlone", allList.get(Integer.parseInt(String.valueOf(object))).getName(), allList.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "7":
                            editInfo("7", "bedridden", allList.get(Integer.parseInt(String.valueOf(object))).getName(), allList.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "8":
                            editInfo("8", "education", allList.get(Integer.parseInt(String.valueOf(object))).getName(), allList.get(Integer.parseInt(String.valueOf(object))).getId());
                            break;
                        case "9":
                            editInfo("9", "profession", allList.get(Integer.parseInt(String.valueOf(object))).getName(), allList.get(Integer.parseInt(String.valueOf(object))).getId());
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
        if (TextUtils.isEmpty(msg)) {
            msgEditText.setHint("请输入就诊卡号");
        } else {
            msgEditText.setText(msg);
            msgEditText.setSelection(msg.length());
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
                Log.i("yys", "type====" + type + "key===" + key + "content==" + content + "values==" + values);
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
                        marriageTv.setText(content);
                        break;
                    case "6":
                        aloneTv.setText(content);
                        break;
                    case "7":
                        bedTv.setText(content);
                        break;
                    case "8":
                        cultureTv.setText(content);
                        break;
                    case "9":
                        workTv.setText(content);
                        break;
                    case "11":
                        hosCardTv.setText(content);
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
                        if ("Y".equals(isSmoke)) {
                            smokeTv.setText("是 " + smokeNum + "支/日");
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
                        if ("Y".equals(isDrink)) {
                            drinkTv.setText("是 " + drinkName + " " + drinkNum + "ml/日");
                        } else {
                            drinkTv.setText("否");
                        }
                    }
                    break;
                case REQUEST_CODE_FOR_PAY_STYLE:
                    if (data != null) {
                        String checkName = data.getStringExtra("checkName");
                        paystyleTv.setText(checkName);
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
