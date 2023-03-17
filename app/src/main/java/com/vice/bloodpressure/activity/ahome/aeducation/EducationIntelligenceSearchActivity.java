package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationIntelligenceAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述: 智能学习 分类 搜索
 * 作者: beauty
 * 创建日期: 2023/2/28 14:46
 */
public class EducationIntelligenceSearchActivity extends UIBaseLoadActivity {

    private final static int REQUEST_CODE_FOE_REFRESH = 10;
    private EducationIntelligenceAdapter mAdapter;

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<EducationInfo> mList;
    private int mPageIndex = 1, mPageSize = 15, mPageCount = 0;
    private boolean mIsLoading = false;
    private NestedScrollView presentNestedSrcollView;
    private TextView stateTextView;

    private EducationInfo allInfo;

    private List<EducationInfo> mTempList;

    private List<EducationInfo> educationInfos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().lineViewVisibility(View.VISIBLE);
        initTopView();
        initView();
        initValue();
        initListener();
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(getPageContext(), R.color.background));
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        loadViewManager().setOnClickListener(LoadStatus.NODATA, view -> loadViewManager().changeLoadState(LoadStatus.LOADING));
    }

    private void initListener() {
        //设置下拉刷新和上拉加载监听
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {

            mPageIndex = 1;
            onPageLoad();
        });
        mRefreshLayout.setEnableLoadMore(true);
        mRefreshLayout.setEnableRefresh(true);

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPageIndex++;
            onPageLoad();
        });
    }

    private void initValue() {
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_education_intelligence_study, null);
        mRefreshLayout = getViewByID(view, R.id.refreshLayout_education);
        mRecyclerView = getViewByID(view, R.id.rv_education);
        presentNestedSrcollView = getViewByID(view, R.id.nsv_present_nodate_education);
        stateTextView = getViewByID(view, R.id.tv_no_data_education);
        containerView().addView(view);
    }

    private void changeLoadUI(int responseCode) {
        presentNestedSrcollView.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        if (1 == mPageIndex) {
            if (100 != responseCode) {
                if (101 == responseCode) {
                    stateTextView.setText(getString(R.string.huahansoft_load_state_no_data));
                } else {
                    stateTextView.setText(getString(R.string.huahansoft_net_error));
                }
                stateTextView.setOnClickListener(view -> {
                    loadViewManager().changeLoadState(LoadStatus.LOADING);
                });
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                mRefreshLayout.setVisibility(View.GONE);
                presentNestedSrcollView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void handleRequestFailure() {
        mPageCount = 0;
        mIsLoading = false;
        if (1 != mPageIndex) {
            mRefreshLayout.finishLoadMore();
        } else {
            mRefreshLayout.finishRefresh();
        }
        if (1 == mPageIndex) {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        } else {
            ToastUtils.getInstance().showToast(getPageContext(), R.string.net_error);
        }
        changeLoadUI(-1);
    }


    @Override
    protected void onPageLoad() {
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
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
        mAdapter = new EducationIntelligenceAdapter(getPageContext(), educationInfos,"1", new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                Log.i("yys", "click===");
                //那个按钮的展示状态 0展开 1收起状态 2没有数据隐藏
                switch (view.getId()) {
                    case R.id.ll_education_study_click:
                        if (educationInfos.get(position).getIsExpand() == 1) {
                            educationInfos.get(position).setIsExpand(0);
                        } else if (educationInfos.get(position).getIsExpand() == 0) {
                            educationInfos.get(position).setIsExpand(1);
                        }
                        mAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {
                switch (view.getId()) {
                    case R.id.ll_education_study_child_click:
                        break;
                    default:
                        break;

                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }


    private void getListData() {
        //        if (mIsLoading) {
        //            return;
        //        }
        //        mIsLoading = true;
        //        Call<String> requestCall = GoodsDataManager.discountGoodsMarketList(mPageIndex + "", mPageSize + "", "",
        //                (call, response) -> {
        //                    mIsLoading = false;
        //                    if (1 != mPageIndex) {
        //                        mRefreshLayout.finishLoadMore();
        //                    } else {
        //                        mRefreshLayout.finishRefresh();
        //                    }
        //                    if (100 == response.code) {
        //                        allInfo = (EducationInfo) response.object;
        //                        mTempList = allInfo.getList();
        //                        mPageCount = mTempList == null ? 0 : mTempList.size();
        //                        if (1 == mPageIndex) {
        //                            if (mList == null) {
        //                                mList = new ArrayList<>();
        //                            } else {
        //                                mList.clear();
        //                            }
        //                            mList.addAll(mTempList);
        //                            if (mAdapter == null) {
        //                                mAdapter = new EducationIntelligenceAdapter(getPageContext(), mList, new OnItemClickListener());
        //                                mRecyclerView.setAdapter(mAdapter);
        //                            } else {
        //                                mAdapter.notifyDataSetChanged();
        //                            }
        //                        } else {
        //                            mList.addAll(mTempList);
        //                            mAdapter.notifyDataSetChanged();
        //                        }
        //                        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        //                    } else if (101 == response.code) {
        //                        mPageCount = 0;
        //                        if (1 == mPageIndex) {
        //                            loadViewManager().changeLoadState(LoadStatus.NODATA);
        //                        } else {
        //                            ToastUtils.getInstance().showToast(getPageContext(), R.string.huahansoft_load_state_no_more_data);
        //                        }
        //                    } else {
        //                        mPageCount = 0;
        //                        if (1 == mPageIndex) {
        //                            loadViewManager().changeLoadState(LoadStatus.FAILED);
        //                        } else {
        //                            ToastUtils.getInstance().showToast(getPageContext(), R.string.net_error);
        //                        }
        //                    }
        //                    changeLoadUI(response.code);
        //                }, (call, t) -> {
        //                    handleRequestFailure();
        //                });
        //        addRequestCallToMap("discountGoodsMarketList", requestCall);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FOE_REFRESH:
                    mPageIndex = 1;
                    onPageLoad();
                    break;
                default:
                    break;
            }
        }
    }

    private class OnItemClickListener implements IAdapterViewClickListener {
        @Override
        public void adapterClickListener(int position, View view) {
            switch (view.getId()) {
                case R.id.ll_education_study_click:
                    if (mList.get(position).getIsExpand() == 1) {
                        mList.get(position).setIsExpand(0);
                    } else if (mList.get(position).getIsExpand() == 0) {
                        mList.get(position).setIsExpand(1);
                    }
                    mAdapter.notifyDataSetChanged();
                    break;


                default:
                    break;


            }
        }

        @Override
        public void adapterClickListener(int position, int index, View view) {

        }
    }

    private void initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_education_intelligence_search, null);
        ImageView backImageView = topView.findViewById(R.id.iv_education_study_search_back);
        EditText contentEditText = topView.findViewById(R.id.et_education_class_search);
        TextView searchTextView = topView.findViewById(R.id.tv_education_class_search_sure);
        backImageView.setOnClickListener(v -> finish());
        String content = contentEditText.getText().toString().trim();
        searchTextView.setOnClickListener(v -> {
        });
        topViewManager().topView().addView(topView);
    }
}
