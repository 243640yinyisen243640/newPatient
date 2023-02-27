package com.vice.bloodpressure.activity.ahome.aeducation;

import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewActivity;
import com.vice.bloodpressure.model.EducationInfo;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationIntelligenceStudyActivity extends UIBaseListRecycleViewActivity<EducationInfo> {
    @Override
    protected void getListData(CallBack callBack) {


    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<EducationInfo> list) {
        return null;
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
