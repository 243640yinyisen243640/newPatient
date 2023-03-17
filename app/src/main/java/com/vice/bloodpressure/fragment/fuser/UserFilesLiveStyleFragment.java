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
import com.vice.bloodpressure.basemanager.DataFormatManager;
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
    /**
     * 是否妊娠
     */
    private String isPregnant;
    /**
     * 是否怀孕
     */
    private String isMarriage;
    /**
     * 是否独居
     */
    private String isAlone;
    /**
     * 是否卧床
     */
    private String isInBed;
    /**
     * 文化程度
     */
    private String culture;
    /**
     * 工作类型
     */
    private String workType;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();

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
                List<String> pregnantList = new ArrayList<>();
                pregnantList.add("是");
                pregnantList.add("否");

                chooseWindow("1", "妊娠", pregnantList);

                break;
            case R.id.tv_user_live_style_pregnant_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        String born = object.toString();
                        pregnantTimeTv.setText(object.toString());
                    }
                });
                break;
            case R.id.tv_user_live_style_marriage:
                List<String> marriageList = new ArrayList<>();
                marriageList.add("已婚");
                marriageList.add("未婚");
                marriageList.add("其他");
                chooseWindow("2", "婚姻", marriageList);
                break;
            case R.id.tv_user_live_style_alone:
                List<String> aloneList = new ArrayList<>();
                aloneList.add("是");
                aloneList.add("否");
                aloneList.add("未知");

                chooseWindow("3", "独居", aloneList);
                break;
            case R.id.tv_user_live_style_bed:

                List<String> isInBedList = new ArrayList<>();
                isInBedList.add("是");
                isInBedList.add("否");
                isInBedList.add("未知");
                chooseWindow("4", "卧床", isInBedList);
                break;
            case R.id.tv_user_live_style_culture:
                List<String> cultureList = new ArrayList<>();
                cultureList.add("研究生");
                cultureList.add("大学本科");
                cultureList.add("大学专科和专科学院");
                cultureList.add("中等专业学校");
                cultureList.add("技工学校");
                cultureList.add("高中");
                cultureList.add("初中");
                cultureList.add("小学");
                cultureList.add("文盲或半文盲");
                cultureList.add("未知");
                chooseWindow("5", "文化程度", cultureList);

                break;
            case R.id.tv_user_live_style_work:
                List<String> workList = new ArrayList<>();
                workList.add("轻体力");
                workList.add("中体力");
                workList.add("重体力");
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
     * @param type  1:是否妊娠 2是否结婚 3：是否独居 4：是否卧床 5：文化程度  6：职业情况
     * @param title
     * @param list
     */
    private void chooseWindow(String type, String title, List<String> list) {
        PickerViewUtils.showChooseSinglePicker(getPageContext(), title, list, object -> {
                    int position = Integer.parseInt(String.valueOf(object));
                    String text = list.get(Integer.parseInt(String.valueOf(object)));
                    switch (type) {
                        case "1":
                            isPregnant = position + "";
                            pregnantTv.setText(text);
                            if ("1".equals(isPregnant)) {
                                pregnantTimeLinearLayout.setVisibility(View.VISIBLE);
                                pregnantTimeView.setVisibility(View.VISIBLE);
                            } else {
                                pregnantTimeLinearLayout.setVisibility(View.GONE);
                                pregnantTimeView.setVisibility(View.GONE);
                            }

                            break;
                        case "2":
                            isMarriage = position + "";
                            marriageTv.setText(text);
                            break;
                        case "3":
                            isAlone = position + "";
                            aloneTv.setText(text);
                            break;
                        case "4":
                            isInBed = position + "";
                            bedTv.setText(text);
                            break;
                        case "5":
                            culture = position + "";
                            cultureTv.setText(text);
                            break;
                        case "6":
                            workType = position + "";
                            workTv.setText(text);
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
                    if (data!=null){
                        String isSmoke = data.getStringExtra("isCheck");
                        String smokeNum = data.getStringExtra("smokeNum");
                        if ("1".equals(isSmoke)){
                            smokeTv.setText("是 "+smokeNum+"日/支");
                        }else {
                            smokeTv.setText("否");
                        }
                    }


                    break;
                case REQUEST_CODE_FOR_DRINK_STYLE:
                    if (data!=null){
                        String isDrink = data.getStringExtra("isCheck");
                        String drinkNum = data.getStringExtra("drinkNum");
                        String drinkType = data.getStringExtra("drinkType");
                        String drinkName = data.getStringExtra("drinkName");
                        if ("1".equals(isDrink)){
                            drinkTv.setText("是 "+drinkName+" "+drinkNum+"ml/日");
                        }else {
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
