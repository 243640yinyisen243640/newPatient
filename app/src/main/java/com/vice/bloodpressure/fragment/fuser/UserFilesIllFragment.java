package com.vice.bloodpressure.fragment.fuser;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserFilesIllFragment extends UIBaseLoadFragment implements View.OnClickListener {
    private LinearLayout importantLinearLayout;
    private TextView importantAddTextView;
    private LinearLayout otherLinearLayout;
    private TextView otherAddTextView;


    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        View view = View.inflate(getPageContext(), R.layout.fragment_user_files_ill, null);
        importantLinearLayout = view.findViewById(R.id.ll_user_files_ill_important);
        importantAddTextView = view.findViewById(R.id.tv_user_files_ill_important_add);
        otherLinearLayout = view.findViewById(R.id.ll_user_files_ill_other);
        otherAddTextView = view.findViewById(R.id.tv_user_files_ill_other_add);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

    @Override
    public void onClick(View v) {

    }
}
