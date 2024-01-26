package com.vice.bloodpressure.fragment.fuser;

import android.content.Intent;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aservice.ServiceMakeMealDetailsActivity;
import com.vice.bloodpressure.adapter.user.UserCollectVideoAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.VideoInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserCollectVideoFragment extends UIBaseListRecycleViewFragment<VideoInfo> {

    @Override
    protected void onCreate() {
        super.onCreate();
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 2);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);
        getPageListView().setBackground(ContextCompat.getDrawable(getPageContext(), R.drawable.shape_bg_white_10));

    }

    @Override
    protected void getListData(CallBack callBack) {
        //（1，文章；2，视频（教育视频、饮食视频）；3，商品）
        Call<String> requestCall = UserDataManager.getCollectMealList(UserInfoUtils.getArchivesId(getPageContext()), "2", (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getCollectMealList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<VideoInfo> list) {
        return new UserCollectVideoAdapter(getPageContext(), list, (position, view) -> {
            switch (view.getId()) {
                case R.id.ll_user_collect_video_click:
                    Intent intent = new Intent(getPageContext(), ServiceMakeMealDetailsActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;

            }
        });
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
