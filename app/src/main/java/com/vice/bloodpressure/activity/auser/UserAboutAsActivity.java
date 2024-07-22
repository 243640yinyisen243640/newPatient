package com.vice.bloodpressure.activity.auser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.listener.ExceptionHandler;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.base.XyApplication;
import com.vice.bloodpressure.basemanager.ConstantParamNew;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.baseui.WebViewHelperActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.model.CheckVersionInfo;
import com.vice.bloodpressure.utils.AppUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.version.UpdateAppHttpUtil;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:关于我们
 */
public class UserAboutAsActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView versionTextView;
    private TextView privacyTextView;
    private LinearLayout updataLinearLayout;
    private TextView userAgreementTextView;
    private TextView newVersionTv;

    private String mVersion;//APK版本号

    private CheckVersionInfo checkVersionInfo;
    private String mIsNewest;
    private String mFileUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("关于我们");
        initView();
        initValues();
        initListener();
    }

    private void initValues() {
        mVersion = AppUtils.appVersionName(XyApplication.getMyApplicationContext());
        versionTextView.setText("v" + mVersion);
        //请求更新接口
        updateNewVersion(this,mVersion);

    }

    private void initListener() {
        privacyTextView.setOnClickListener(this);
        userAgreementTextView.setOnClickListener(this);
        updataLinearLayout.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_about_us, null);
        versionTextView = view.findViewById(R.id.tv_user_about_version);
        privacyTextView = view.findViewById(R.id.tv_user_about_us_privacy);
        updataLinearLayout = view.findViewById(R.id.ll_user_about_us_updata);
        userAgreementTextView = view.findViewById(R.id.tv_user_about_us_user_agreement);
        newVersionTv = view.findViewById(R.id.new_version_tv);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String token = SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ACCESS_TOKEN);
        switch (v.getId()) {
            case R.id.tv_user_about_us_privacy:
                intent = new Intent(getPageContext(), WebViewHelperActivity.class);
                intent.putExtra("title", "用户服务协议");
                intent.putExtra("url", ConstantParamNew.DOMAIN_NAME + "pagesC/pages/userAgreement?" + "token=" + token + "&type=" + "1");
                startActivity(intent);
                break;
            case R.id.tv_user_about_us_user_agreement:
                intent = new Intent(getPageContext(), WebViewHelperActivity.class);
                intent.putExtra("title", "隐私政策");
                intent.putExtra("url", ConstantParamNew.DOMAIN_NAME + "pagesC/pages/userAgreement?" + "&type=" + "2");
                startActivity(intent);
                break;
            case R.id.ll_user_about_us_updata:
                updateJudgment();
                break;
            default:
                break;
        }
    }

    /**
     * 检查软件更新升级
     * @param context
     * @param mVersion
     */
    public void updateNewVersion(Context context,String mVersion) {
//        ToastUtils.getInstance().showProgressDialog(context, R.string.waiting, false);
        Call<String> requestCall = HomeDataManager.checkVersion(mVersion,(call, response) -> {
//            ToastUtils.getInstance().dismissProgressDialog();
            if ("0000".equals(response.code)) {
                checkVersionInfo = (CheckVersionInfo) response.object;
                mIsNewest = checkVersionInfo.getIsNewest();
                mFileUrl = checkVersionInfo.getFileUrl();
                boolean isNewest = mIsNewest.equals("false");
                newVersionTv.setVisibility(isNewest ? View.VISIBLE : View.GONE);
            } else {
                ToastUtils.getInstance().showToast(context, response.msg);
            }
        }, (call, throwable) -> {
            ResponseUtils.defaultFailureCallBack(context, call);
        });

        addRequestCallToMap("checkVersionUpdate",requestCall);
    }

    /**
     * 升级判断
     */
    private void updateJudgment(){
        if ("false".equals(mIsNewest)){
            //待apk正式上线后再测通过URL更新版本
            ToastUtils.getInstance().showToast(UserAboutAsActivity.this,"当前版本需升级");
             /*       UpdateAppBean appBean = new UpdateAppBean();
                    appBean.setApkFileUrl(mFileUrl);
//                    appBean.setConstraint("1".equals(mIsNewest));
                    appBean.setOnlyWifi(false);
                    appBean.setUpdate("Yes");
//                    appBean.setNewVersion(versionModel.getVersion());
//                    appBean.setUpdateLog(versionModel.getUpdateContent());

                    updateApp(context, activity, appBean);*/
        }else {
            ToastUtils.getInstance().showToast(UserAboutAsActivity.this, R.string.new_last_version);
        }
    }

    /**
     * 升级版本
     */
    public void updateApp(Context context, Activity activity, UpdateAppBean appBean) {
        new UpdateAppManager
                .Builder()
                //当前Activity
                .setActivity(activity)
                .dismissNotificationProgress()
                .setPost(false).dismissNotificationProgress()
                //更新地址
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                .setUpdateUrl(appBean.getApkFileUrl())
                //                .setTopPic(R.mipmap.top_8)
                .setIgnoreDefParams(true)
                .setThemeColor(ContextCompat.getColor(context, R.color.main_base_color))
                //实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                .build()
                .processData("", new UpdateCallback(), appBean);
    }
}
