package com.vice.bloodpressure.activity.auser;

import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:消息通知
 */
public class UserMsgListActivity extends UIBaseListRecycleViewActivity {
    @Override
    protected void getListData(CallBack callBack) {

    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List list) {
        return null;
    }

    @Override
    protected int getPageSize() {
        return 0;
    }
}
