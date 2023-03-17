package com.vice.bloodpressure.fragment.fuser;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.user.UserCollectVideoAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.VideoInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserCollectVideoFragment extends UIBaseListRecycleViewFragment<VideoInfo> {
    private List<VideoInfo> videoInfos = new ArrayList<>();

    @Override
    protected void onCreate() {
        super.onCreate();
        //设置每一个item间距
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 2);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

//        getPageListView().setLayoutManager();
        getPageListView().setBackground(ContextCompat.getDrawable(getPageContext(), R.drawable.shape_bg_white_10));

    }

    @Override
    protected void getListData(CallBack callBack) {
        videoInfos.add(new VideoInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "韭菜鸡蛋包子"));
        videoInfos.add(new VideoInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "银耳薏仁枸杞莲子荞麦..."));
        videoInfos.add(new VideoInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "西红柿炒鸡蛋"));
        videoInfos.add(new VideoInfo("http://img.wxcha.com/m00/f0/f5/5e3999ad5a8d62188ac5ba8ca32e058f.jpg", "西红柿炒鸡蛋"));
        callBack.callBack(videoInfos);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<VideoInfo> list) {
        return new UserCollectVideoAdapter(getPageContext(), videoInfos);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }
}
