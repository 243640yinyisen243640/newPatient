package com.vice.bloodpressure.baseui;

import android.view.View;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.ScrollBoundaryDecider;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class UIBaseListRecycleViewFragment<T> extends UIBaseLoadFragment {
    protected SmartRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected NestedScrollView mNestedScrollView;
    private TextView mLoadingTextView;
    private List<T> mList;
    private List<T> mTempList;
    private RecyclerView.Adapter mAdapter;
    protected boolean mIsLoading = false;
    protected boolean mIsLoadMore = true;
    protected boolean mIsRefresh = true;
    //当前获取的是第几页的数据，当前可见的数据的数量，当前页获取的数据的条数
    private int mPageIndex = 1, mPageSize = 15, mVisibleCount = 0, mPageCount = 0;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        mIsLoadMore = isLoadMore();
        mRefreshLayout.setEnableLoadMore(mIsLoadMore);
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mIsRefresh = isRefresh();
        mRefreshLayout.setEnableRefresh(mIsRefresh);
        if (mIsRefresh) {
            mRefreshLayout.setOnRefreshListener(refreshLayout -> {
                mPageIndex = 1;
                onPageLoad();
            });
        }
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPageIndex++;
            onPageLoad();
        });
        mRefreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDecider() {
            @Override
            public boolean canRefresh(View content) {
                return mIsRefresh;
            }

            @Override
            public boolean canLoadMore(View content) {
                return mIsLoadMore && mPageCount == mPageSize && !mIsLoading;
            }
        });
        mLoadingTextView.setOnClickListener(v -> {
            mPageIndex = 1;
            mRefreshLayout.setVisibility(View.GONE);
            mNestedScrollView.setVisibility(View.GONE);
            loadViewManager().changeLoadState(LoadStatus.LOADING);
        });
    }

    @Override
    protected void onPageLoad() {
        if (mIsLoading) {
            return;
        }
        mIsLoading = true;
        getListData(tempList -> {
            mIsLoading = false;
            mTempList = (List<T>) tempList;
            mPageCount = mTempList == null ? 0 : mTempList.size();
            if (1 != mPageIndex) {
                mRefreshLayout.finishLoadMore();
            } else {
                mRefreshLayout.finishRefresh();
            }
            if (mTempList == null) {
                if (1 == mPageIndex) {
                    mRefreshLayout.setVisibility(View.GONE);
                    mNestedScrollView.setVisibility(View.VISIBLE);
                    mLoadingTextView.setText(R.string.huahansoft_net_error);
                    loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                } else {
                    ToastUtils.getInstance().showToast(getPageContext(), R.string.huahansoft_net_error);
                }
            } else if (mTempList.size() == 0) {
                if (mPageIndex == 1) {
                    if (mList == null) {
                        mList = new ArrayList<T>();
                    } else {
                        mList.clear();
                    }
                    mRefreshLayout.setVisibility(View.GONE);
                    mNestedScrollView.setVisibility(View.VISIBLE);
                    mLoadingTextView.setText(R.string.huahansoft_load_state_no_data);
                    loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                } else {
                    ToastUtils.getInstance().showToast(getPageContext(), R.string.huahansoft_load_state_no_more_data);
                }
            } else {
                mRefreshLayout.setVisibility(View.VISIBLE);
                mNestedScrollView.setVisibility(View.GONE);
                if (1 == mPageIndex) {
                    if (mList == null) {
                        mList = new ArrayList<>();
                    } else {
                        mList.clear();
                    }
                    mList.addAll(mTempList);
                    if (mAdapter == null) {
                        mAdapter = instanceAdapter(mList);
                        mRecyclerView.setAdapter(mAdapter);
                    } else {
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    mList.addAll(mTempList);
                    mAdapter.notifyDataSetChanged();
                }
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            }
        });
    }

    /**
     * 获取页面集合
     *
     * @param callBack
     */
    protected abstract void getListData(CallBack callBack);

    /**
     * 设置适配器
     *
     * @param list
     * @return
     */
    protected abstract RecyclerView.Adapter instanceAdapter(List<T> list);

    /**
     * 获取页面加载数量
     *
     * @return
     */
    protected abstract int getPageSize();

    /**
     * 设置页面是否可以加载更多
     *
     * @return
     */
    protected boolean isLoadMore() {
        return true;
    }

    /**
     * 设置页面是否允许下拉刷新
     *
     * @return
     */
    protected boolean isRefresh() {
        return true;
    }

    protected int getPageIndex() {
        return mPageIndex;
    }

    /**
     * 获取ListView对象
     *
     * @return
     */
    protected RecyclerView getPageListView() {
        return mRecyclerView;
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.ui_fragment_base_list_recycle_view, null);
        mRefreshLayout = getViewByID(view, R.id.refresh_layout);
        mRecyclerView = getViewByID(view, R.id.recycler_view);
        mNestedScrollView = getViewByID(view, R.id.nsv_present_load);
        mLoadingTextView = getViewByID(view, R.id.tv_load_status);
        containerView().addView(view);
    }

}
