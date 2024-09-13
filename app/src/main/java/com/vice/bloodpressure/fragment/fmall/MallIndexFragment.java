package com.vice.bloodpressure.fragment.fmall;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadRefreshFragment;
import com.vice.bloodpressure.customView.banner.view.BannerView;
import com.vice.bloodpressure.model.ActivityBaseInfo;
import com.vice.bloodpressure.utils.banner.CommonBannerAdvertViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MallIndexFragment extends UIBaseLoadRefreshFragment {


    /**
     * 搜索
     */
    private TextView searchTextView;
    private ImageView customImageView;
    private FrameLayout customFrameLayout;
    private ImageView cartImageView;


    /**
     * 轮播图
     */
    private BannerView bannerView;

    private TextView instrumentTextView;
    private TextView foodTextView;
    private TextView paperTextView;
    private TextView otherTextView;

    private TextView moreGoodsTextView;
    private RecyclerView goodsRecyclerView;


    @Override
    protected void onCreate() {
        super.onCreate();
        topViewManager().topView().removeAllViews();
        initTopView();
        initView();
        initListener();
        List<ActivityBaseInfo> advertInfos =new ArrayList<>();
        ActivityBaseInfo activityBaseInfo1 =new ActivityBaseInfo();
        activityBaseInfo1.setImg("https://video.xiyuns.cn/witmed/diet-image/6E228AB0A1FD29F0EFA08935AFDD0B97.png");
        activityBaseInfo1.setLinkUrl("https://video.xiyuns.cn/witmed/diet-image/6E228AB0A1FD29F0EFA08935AFDD0B97.png");
        advertInfos.add(activityBaseInfo1);
        ActivityBaseInfo activityBaseInfo2 = new ActivityBaseInfo();
        activityBaseInfo2.setImg("https://video.xiyuns.cn/witmed/edu-image/FBBC7ECA2CF645908A189D66E2403472.png");
        activityBaseInfo2.setLinkUrl("https://video.xiyuns.cn/witmed/diet-image/6E228AB0A1FD29F0EFA08935AFDD0B97.png");
        advertInfos.add(activityBaseInfo2);
        setBannerView(advertInfos);
        //        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_mall_top_view, null);
        searchTextView = topView.findViewById(R.id.tv_mall_index_search);
        customFrameLayout = topView.findViewById(R.id.fl_mall_index_customer);
        customImageView = topView.findViewById(R.id.iv_mall_index_customer);
        cartImageView = topView.findViewById(R.id.iv_mall_index_cart);
        topViewManager().topView().addView(topView);
    }

    private void initListener() {
        refreshLayout().setOnRefreshListener(refreshLayout -> onPageLoad());
    }

    @Override
    protected void onPageLoad() {


    }

    @Override
    protected boolean isNeedFullScreen() {
        return false;
    }

    /**
     * 轮播图
     */
    private void setBannerView(List<ActivityBaseInfo> advertInfos) {
        if (advertInfos == null || advertInfos.size() == 0) {
            bannerView.setVisibility(View.GONE);
            return;
        }
//        //设置轮播图
//        int width = ScreenUtils.screenWidth(getPageContext()) - DensityUtils.dip2px(getPageContext(), 20);
//        int height = width / 2;
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
//        bannerView.setLayoutParams(lp);
        if (advertInfos == null) {
            advertInfos = new ArrayList<>();
        }
        if (0 == advertInfos.size()) {
            ActivityBaseInfo activityInfo = new ActivityBaseInfo();

            activityInfo.setLinkUrl("");
            activityInfo.setImg("");
            advertInfos.add(activityInfo);
        }
        bannerView.setIndicatorVisible(true);
        bannerView.setIndicatorRes(R.drawable.shape_little_green_6_6_circle, R.drawable.shape_green_20_6_3);
        bannerView.setIndicatorAlign(BannerView.IndicatorAlign.CENTER);
        bannerView.setPages(advertInfos, () -> new CommonBannerAdvertViewHolder());
        bannerView.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

//        if (advertInfos.size() > 1) {
//            bannerView.start();
//        } else {
//            bannerView.pause();
//        }

    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_main_mall, null);

        bannerView = getViewByID(view, R.id.bv_mall_index_banner);
        instrumentTextView = getViewByID(view, R.id.tv_mall_index_instrument);
        foodTextView = getViewByID(view, R.id.tv_mall_index_food);
        paperTextView = getViewByID(view, R.id.tv_mall_index_text_paper);
        otherTextView = getViewByID(view, R.id.tv_mall_index_main_other);
        moreGoodsTextView = getViewByID(view, R.id.tv_mall_index_goods);
        goodsRecyclerView = getViewByID(view, R.id.rv_mall_index_goods);

        //        searchTextView.setOnClickListener(v -> {
        //                        Intent intent = new Intent(getPageContext(), MallSearchActivity.class);
        //                        startActivity(intent);
        //        });
        moreGoodsTextView.setOnClickListener(view1 -> {
            //            Intent intent = new Intent(getPageContext(), MallMoreGoodActivity.class);
            //            startActivity(intent);
        });

        containerView().addView(view);
    }


}

