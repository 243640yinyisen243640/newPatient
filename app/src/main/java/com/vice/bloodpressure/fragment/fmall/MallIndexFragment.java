package com.vice.bloodpressure.fragment.fmall;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.mall.MallIndexAdapter;
import com.vice.bloodpressure.baseui.UIBaseLoadRefreshFragment;
import com.vice.bloodpressure.customView.banner.view.BannerView;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.ActivityBaseInfo;
import com.vice.bloodpressure.model.GoodsInfo;
import com.vice.bloodpressure.utils.DensityUtils;
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
        List<ActivityBaseInfo> advertInfos = new ArrayList<>();
        ActivityBaseInfo activityBaseInfo1 = new ActivityBaseInfo();
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
        List<GoodsInfo> list = new ArrayList<>();
        GoodsInfo info = new GoodsInfo();

        info.setPrice("10.36");
        info.setCover("https://inews.gtimg.com/om_bt/O6SG7dHjdG0kWNyWz6WPo2_3v6A6eAC9ThTazwlKPO1qMAA/641");
        info.setTitle("舒可唯NB-loT5G血糖 仪 ");
        GoodsInfo info1 = new GoodsInfo();

        info1.setPrice("10.36");
        info1.setCover("https://img-blog.csdnimg.cn/166e183e84094c44bbc8ad66500cef5b.jpeg");
        info1.setTitle("糖友厨房饼干零食无糖 精粗尿粮孕妇啊啊啊啊啊");
        GoodsInfo info2 = new GoodsInfo();

        info2.setPrice("10.36");
        info2.setCover("https://wxls-cms.oss-cn-hangzhou.aliyuncs.com/online/2024-04-18/218da022-f4bf-456a-99af-5cb8e157f7b8.jpg");
        info2.setTitle("舒可唯NB-loT5G血糖 仪 ");
        GoodsInfo info3 = new GoodsInfo();

        info3.setPrice("10.36");
        info3.setCover("https://video.xiyuns.cn/witmed/edu-image/FBBC7ECA2CF645908A189D66E2403472.png");
        info3.setTitle("舒可唯NB-loT5G血糖 仪 ");
        GoodsInfo info4 = new GoodsInfo();

        info4.setPrice("10.36");
        info4.setCover("https://video.xiyuns.cn/witmed/edu-image/FBBC7ECA2CF645908A189D66E2403472.png");
        info4.setTitle("舒可唯NB-loT5G血糖 仪 ");

        list.add(info);
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);

        MallIndexAdapter adapter = new MallIndexAdapter(getPageContext(), list, (position, view) -> {

        });
        goodsRecyclerView.setAdapter(adapter);

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
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        goodsRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        goodsRecyclerView.setLayoutManager(layoutManager);


        goodsRecyclerView.setHasFixedSize(true);
        goodsRecyclerView.setNestedScrollingEnabled(false);



        containerView().addView(view);
    }


}

