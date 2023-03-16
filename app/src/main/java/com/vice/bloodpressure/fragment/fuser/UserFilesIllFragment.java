package com.vice.bloodpressure.fragment.fuser;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.auser.UserIllImportantActivity;
import com.vice.bloodpressure.activity.auser.UserIllOtherActivity;
import com.vice.bloodpressure.activity.auser.UserIllPlusActivity;
import com.vice.bloodpressure.adapter.user.UserFilesPiusAdapter;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserFilesIllFragment extends UIBaseLoadFragment implements View.OnClickListener {
    private LinearLayout importantLinearLayout;
    private TextView importantTypeTextView;
    private TextView importantTimeTextView;
    private TextView importantAddTextView;
    private TextView plusAddTextView;
    private NoScrollListView listView;
    private LinearLayout otherLinearLayout;
    private TextView otherAddTextView;
    private TextView otherNameTextView;
    private TextView otherTimeTextView;

    private UserFilesPiusAdapter adapter;


    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        initListener();
        initVlues();
    }

    private void initListener() {
        importantLinearLayout.setOnClickListener(this);
        importantAddTextView.setOnClickListener(this);
        plusAddTextView.setOnClickListener(this);
        otherLinearLayout.setOnClickListener(this);
        otherAddTextView.setOnClickListener(this);
    }

    private void initVlues() {
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("2020-10-27", "糖尿病"));
        list.add(new UserInfo("2020-10-27", "糖尿病"));
        adapter = new UserFilesPiusAdapter(getPageContext(), list);
        listView.setAdapter(adapter);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_ill, null);
        importantLinearLayout = view.findViewById(R.id.ll_user_files_ill_important);
        importantTypeTextView = view.findViewById(R.id.tv_user_files_ill_important_type);
        importantTimeTextView = view.findViewById(R.id.tv_user_files_ill_important_time);
        importantAddTextView = view.findViewById(R.id.tv_user_files_ill_important_add);


        plusAddTextView = view.findViewById(R.id.tv_user_files_ill_plus_add);
        listView = view.findViewById(R.id.lv_user_files_ill_plus);

        otherLinearLayout = view.findViewById(R.id.ll_user_files_ill_other);
        otherNameTextView = view.findViewById(R.id.tv_user_files_ill_other_name);
        otherTimeTextView = view.findViewById(R.id.tv_user_files_ill_other_time);
        otherAddTextView = view.findViewById(R.id.tv_user_files_ill_other_add);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_user_files_ill_important:
                intent = new Intent(getPageContext(), UserIllImportantActivity.class);
                intent.putExtra("isAdd", "2");
                startActivity(intent);
                break;
            case R.id.tv_user_files_ill_important_add:
                intent = new Intent(getPageContext(), UserIllImportantActivity.class);
                intent.putExtra("isAdd", "1");
                startActivity(intent);
                break;
            case R.id.ll_user_files_ill_other:

                break;
            case R.id.tv_user_files_ill_other_add:
                intent = new Intent(getPageContext(), UserIllOtherActivity.class);
                intent.putExtra("isAdd", "1");
                startActivity(intent);
                break;
            case R.id.tv_user_files_ill_plus_add:
                intent = new Intent(getPageContext(), UserIllPlusActivity.class);
                intent.putExtra("isAdd", "1");
                startActivity(intent);
                break;

            default:
                break;

        }
    }
}
