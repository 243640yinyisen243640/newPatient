package com.vice.bloodpressure.fragment.fuser;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationIntelligenceAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 类名：
 * 传参：
 * 描述: 我的收藏文章
 * 作者: beauty
 * 创建日期: 2023/2/28 14:46
 */
public class UserCollectArticleFragment extends UIBaseListRecycleViewFragment<EducationInfo> {

    private final static int REQUEST_CODE_FOE_REFRESH = 10;
    private EducationIntelligenceAdapter adapter;
    private List<EducationInfo> educationInfos;

    @Override
    protected void onCreate() {
        super.onCreate();
        topViewManager().topView().removeAllViews();
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), true));
        mRecyclerView.setLayoutManager(layoutManager);
        getPageListView().setBackgroundColor(ContextCompat.getColor(getPageContext(), R.color.background));
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        loadViewManager().setOnClickListener(LoadStatus.NODATA, view -> loadViewManager().changeLoadState(LoadStatus.LOADING));

    }

    @Override
    protected void getListData(CallBack callBack) {

        educationInfos = new ArrayList<>();
        educationInfos.add(new EducationInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "系列一：高血压的基础知识", "由于生活环境和生活条件的影响，导致越高血压基础知识 来越多的人患...高...", "学习中", "5"));
        educationInfos.add(new EducationInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "系列二：高血压的基础知识", "非药物治疗是高血压治疗的基础方法。", "学习中", "6"));
        educationInfos.add(new EducationInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "系列三：高血压的基础知识", "由于生活环境和生活条件的影响，导致越高血压基础知识。", "学习中", "7"));

        List<EducationInfo> childList = new ArrayList<>();
        childList.add(new EducationInfo("第一节:知晓血压，了解血压!", "学习中"));
        childList.add(new EducationInfo("第二节:高血压，隐形的杀手!", "学习中"));
        for (int i = 0; i < educationInfos.size(); i++) {
            educationInfos.get(i).setChildList(childList);
        }
        callBack.callBack(educationInfos);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<EducationInfo> list) {
        return adapter = new EducationIntelligenceAdapter(getPageContext(), educationInfos, "2", new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                //那个按钮的展示状态 0展开 1收起状态 2没有数据隐藏
                switch (view.getId()) {
                    case R.id.ll_education_study_click:
                        if (educationInfos.get(position).getIsExpand() == 1) {
                            educationInfos.get(position).setIsExpand(0);
                        } else if (educationInfos.get(position).getIsExpand() == 0) {
                            educationInfos.get(position).setIsExpand(1);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        });
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FOE_REFRESH:

                    break;
                default:
                    break;
            }
        }
    }


}
