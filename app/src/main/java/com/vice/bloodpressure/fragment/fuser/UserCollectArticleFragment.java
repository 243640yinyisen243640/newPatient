package com.vice.bloodpressure.fragment.fuser;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.ahome.aeducation.EducationDetailsActivity;
import com.vice.bloodpressure.adapter.home.EducationIntelligenceAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.List;

import retrofit2.Call;

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

    @Override
    protected void onCreate() {
        super.onCreate();
        topViewManager().topView().removeAllViews();
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), true));
        mRecyclerView.setLayoutManager(layoutManager);
        //        getPageListView().setBackgroundColor(ContextCompat.getColor(getPageContext(), R.color.background));
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        loadViewManager().setOnClickListener(LoadStatus.NODATA, view -> loadViewManager().changeLoadState(LoadStatus.LOADING));

    }

    @Override
    protected boolean isRefresh() {
        return false;
    }

    @Override
    protected boolean isLoadMore() {
        return false;
    }

    @Override
    protected void getListData(CallBack callBack) {
        //（1，文章；2，视频（教育视频、饮食视频）；3，商品）
        Call<String> requestCall = UserDataManager.getCollectForArtList(UserInfoUtils.getArchivesId(getPageContext()), "1", (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getCollectForArtList", requestCall);

    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<EducationInfo> list) {
        return adapter = new EducationIntelligenceAdapter(getPageContext(), list, "3", new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                //那个按钮的展示状态 0展开 1收起状态 2没有数据隐藏
                switch (view.getId()) {
                    case R.id.ll_education_study_click:
                        if (list.get(position).getIsExpand() == 1) {
                            list.get(position).setIsExpand(0);
                        } else if (list.get(position).getIsExpand() == 0) {
                            list.get(position).setIsExpand(1);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {
                switch (view.getId()) {
                    case R.id.ll_education_study_child_click:
                        Intent intent = new Intent(getPageContext(), EducationDetailsActivity.class);
                        intent.putExtra("type", list.get(position).getTeachEssayAppVos().get(index).getType());
                        intent.putExtra("sid", list.get(position).getSid());
                        intent.putExtra("essayId", list.get(position).getTeachEssayAppVos().get(index).getEssayId());
                        intent.putExtra("fromWhere", "3");
                        startActivity(intent);
                        break;
                    default:
                        break;

                }
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

    @Override
    public void onResume() {
        super.onResume();
        onPageLoad();
    }
}
