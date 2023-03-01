package com.vice.bloodpressure.fragment.fhome.education;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.ScrollBoundaryDecider;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.ahome.aeducation.EducationClassifyActivity;
import com.vice.bloodpressure.adapter.home.EducationClassifyRightAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationClassifySecondFragment extends UIBaseLoadFragment {

    private EducationClassifyRightAdapter mAdapter;

    private String mark;
    private String topicID;

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<EducationInfo> mList;
    private int mPageIndex = 1, mPageSize = 15, mPageCount = 0;
    private boolean mIsLoading = false;
    private NestedScrollView presentNestedSrcollView;
    private TextView stateTextView;
    private ListView listView;

    private String firstClassID;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
        mark = getArguments().getString("mark");
        topicID = getArguments().getString("topicID");
        topViewManager().topView().removeAllViews();
        initView();
        initValue();
        initListener();
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(getPageContext(), R.color.text_white));
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        loadViewManager().setOnClickListener(LoadStatus.NODATA, view -> loadViewManager().changeLoadState(LoadStatus.LOADING));

    }

    public EducationClassifySecondFragment(ListView listView) {
        this.listView = listView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
    }


    @Override
    protected void onPageLoad() {
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        getChildList();
    }

    private void getChildList() {
        List<EducationInfo> childList = new ArrayList<>();
        childList.add(new EducationInfo("基础知识", ""));
        childList.add(new EducationInfo("异常指标", ""));
        EducationClassifyRightAdapter rightAdapter = new EducationClassifyRightAdapter(getPageContext(), childList, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                //跳转页面
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        });
        mRecyclerView.setAdapter(rightAdapter);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_classify_right, null);
        mRefreshLayout = getViewByID(view, R.id.refreshLayout_classify);
        mRecyclerView = getViewByID(view, R.id.rv_classify);
        presentNestedSrcollView = getViewByID(view, R.id.nsv_present_nodate_classify);
        stateTextView = getViewByID(view, R.id.tv_no_data_classify);
        containerView().addView(view);

    }

    private void changeLoadUI(int responseCode) {
        presentNestedSrcollView.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        if (getActivity() instanceof EducationClassifyActivity) {
            if (1 == mPageIndex) {
                if (100 != responseCode) {
                    if (101 == responseCode) {
                        stateTextView.setText(getString(R.string.huahansoft_load_state_no_data));
                    } else {
                        stateTextView.setText(getString(R.string.huahansoft_net_error));
                    }
                    loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                    mRefreshLayout.setVisibility(View.GONE);
                    presentNestedSrcollView.setVisibility(View.VISIBLE);
                }
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

    private void initValue() {
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initListener() {
        //设置下拉刷新和上拉加载监听
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                mPageIndex = 1;
                onPageLoad();
            }
        });
        mRefreshLayout.setEnableLoadMore(true);
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDecider() {
            @Override
            public boolean canRefresh(View content) {
                return false;
            }

            @Override
            public boolean canLoadMore(View content) {
                return mPageCount == mPageSize && !mIsLoading;
            }
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {

            mPageIndex++;
            onPageLoad();
        });
    }

    /**
     * 切换类别刷新数据
     */
    public void refresh(String firstClassID) {
        this.firstClassID = firstClassID;
//        listView.setEnabled(false);

        mPageIndex = 1;
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    //
    //    private void getListData() {
    //        if (mIsLoading) {
    //            return;
    //        }
    //        mIsLoading = true;
    //        Call<String> requestCall = CommunityDataManager.getCommentList(mPageIndex, mPageSize, topicID, mark, UserInfoUtils.getUserID(getPageContext()), order,
    //                (call, response) -> {
    //                    mIsLoading = false;
    //                    if (1 != mPageIndex) {
    //                        mRefreshLayout.finishLoadMore();
    //                    } else {
    //                        mRefreshLayout.finishRefresh();
    //                    }
    //                    if (100 == response.code) {
    //                        List<EducationInfo> mTempList = (List<EducationInfo>) response.object;
    //                        mPageCount = mTempList == null ? 0 : mTempList.size();
    //                        if (1 == mPageIndex) {
    //                            if (mList == null) {
    //                                mList = new ArrayList<>();
    //                            } else {
    //                                mList.clear();
    //                            }
    //                            mList.addAll(mTempList);
    //                            if (mAdapter == null) {
    //                                mAdapter = new EducationClassifyRightAdapter(getPageContext(), mList, new OnItemClickListener());
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
    //                            HHSoftTipUtils.getInstance().showToast(getPageContext(), R.string.huahansoft_load_state_no_more_data);
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
    //        addRequestCallToMap("getCommentList", requestCall);
    //    }
}
