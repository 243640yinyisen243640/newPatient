package com.vice.bloodpressure.fragment.fuser;

import android.view.View;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserFilesFamilyFragment extends UIBaseLoadFragment {
    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_family_ill, null);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }
}
