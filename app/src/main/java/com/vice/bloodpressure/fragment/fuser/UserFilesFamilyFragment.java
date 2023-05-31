package com.vice.bloodpressure.fragment.fuser;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.auser.UserIllFamilyHistoryActivity;
import com.vice.bloodpressure.adapter.user.UserFilesFamilyHistoryAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.List;

import retrofit2.Call;

import static android.app.Activity.RESULT_OK;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserFilesFamilyFragment extends UIBaseLoadFragment {
    private static final int REQUEST_CODE_FOR_REFRESH = 1;
    private TextView addTextView;
    private NoScrollListView listView;

    private UserFilesFamilyHistoryAdapter adapter;
    private UserInfo userInfo;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        initListener();
    }


    @Override
    protected void onPageLoad() {
        Call<String> requestCall = UserDataManager.getUserFilesInfoForFamily(UserInfoUtils.getArchivesId(getPageContext()), "4", (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                adapter = new UserFilesFamilyHistoryAdapter(getPageContext(), (List<UserInfo>) response.object);
                listView.setAdapter(adapter);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getUserFilesInfoForFamily", requestCall);
    }


    private void initListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getPageContext(), UserIllFamilyHistoryActivity.class);
            startActivityForResult(intent, REQUEST_CODE_FOR_REFRESH);
        });
        addTextView.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), UserIllFamilyHistoryActivity.class);
            startActivityForResult(intent, REQUEST_CODE_FOR_REFRESH);
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_family_ill, null);
        addTextView = view.findViewById(R.id.tv_user_files_ill_family_history);
        listView = view.findViewById(R.id.lv_user_files_family_history);
        containerView().addView(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FOR_REFRESH:
                    onPageLoad();
                    break;
                default:
                    break;
            }
        }
    }
}
