package com.vice.bloodpressure.fragment.fuser;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.auser.UserIllFamilyHistoryActivity;
import com.vice.bloodpressure.adapter.user.UserFilesFamilyHistoryAdapter;
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
public class UserFilesFamilyFragment extends UIBaseLoadFragment {
    private TextView addTextView;
    private NoScrollListView listView;

    private UserFilesFamilyHistoryAdapter adapter;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        initVlues();
        initListener();
    }

    private void initListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(getPageContext(), UserIllFamilyHistoryActivity.class);
            intent.putExtra("isAdd", "2");
            startActivity(intent);
        });
        addTextView.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), UserIllFamilyHistoryActivity.class);
            intent.putExtra("isAdd", "1");
            startActivity(intent);
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_family_ill, null);
        addTextView = view.findViewById(R.id.tv_user_files_ill_family_history);
        listView = view.findViewById(R.id.lv_user_files_family_history);
        containerView().addView(view);
    }

    private void initVlues() {
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("子女", "无"));
        list.add(new UserInfo("子女", "有"));
        adapter = new UserFilesFamilyHistoryAdapter(getPageContext(), list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onPageLoad() {

    }
}
