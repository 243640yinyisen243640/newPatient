package com.vice.bloodpressure.fragment.fservice;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aservice.ServiceHealthyDataActivity;
import com.vice.bloodpressure.adapter.service.SerciveDataShowAdapter;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;
import com.vice.bloodpressure.customView.banner.view.BannerView;
import com.vice.bloodpressure.model.AdvertInfo;
import com.vice.bloodpressure.utils.banner.CommonBannerAdvertClickListener;
import com.vice.bloodpressure.utils.banner.CommonBannerAdvertViewHolder;
import com.vice.bloodpressure.view.HHAtMostGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:服务的首页
 */
public class ServiceMainFragment extends UIBaseLoadFragment {
    private BannerView bannerView;
    private HHAtMostGridView gridView;
    private TextView mallAreaTextView;
    private TextView videoAreaTextView;

    @Override
    protected void onCreate() {
        topViewManager().titleTextView().setText("服务");
        topViewManager().backTextView().setVisibility(View.GONE);
        topViewManager().topView().setBackgroundColor(getResources().getColor(R.color.main_base_color));
        topViewManager().statusBarView().setBackgroundColor(getResources().getColor(R.color.main_base_color));
        topViewManager().titleTextView().setTextColor(getResources().getColor(R.color.text_white));
        topViewManager().lineViewVisibility(View.GONE);
        initView();
        initValues();
    }

    private void initValues() {
        bindBannerViewData();
        initDataArea();
    }

    private void initDataArea() {
        List<AdvertInfo> advertInfos = new ArrayList<>();
        advertInfos.add(new AdvertInfo(R.drawable.service_health_data, "健康数据"));
        advertInfos.add(new AdvertInfo(R.drawable.service_mall_data, "智能饮食"));
        advertInfos.add(new AdvertInfo(R.drawable.service_education_data, "智能教育"));
        advertInfos.add(new AdvertInfo(R.drawable.service_exercise_data, "智能运动"));
        advertInfos.add(new AdvertInfo(R.drawable.service_health_text, "健康评测"));
        SerciveDataShowAdapter adapter = new SerciveDataShowAdapter(getPageContext(), advertInfos, "1", object -> {
            switch (Integer.parseInt(String.valueOf(object))) {
                case 0:
                    startActivity(new Intent(getPageContext(), ServiceHealthyDataActivity.class));
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:
                    break;
            }
        });
        gridView.setAdapter(adapter);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_main_service, null);
        bannerView = view.findViewById(R.id.bv_service_banner);
        gridView = view.findViewById(R.id.gv_service_type);
        mallAreaTextView = view.findViewById(R.id.tv_service_mall_area);
        videoAreaTextView = view.findViewById(R.id.tv_service_video_area);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

    /**
     * 轮播图
     */
    private void bindBannerViewData() {
        //        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) bannerView.getLayoutParams();
        //        params.height = (ScreenUtils.screenWidth(getPageContext()) - DensityUtils.dip2px(getPageContext(), 15) * 2) / 2;
        List<AdvertInfo> advertInfos = new ArrayList<>();
        advertInfos.add(new AdvertInfo(""));
        //        if (advertInfos == null) {
        //            advertInfos = new ArrayList<>();
        //        }
        //        if (advertInfos.size() == 0) {
        //            AdvertInfo advertInfo = new AdvertInfo();
        //            //            advertInfo.setSourceImg("");
        //            //            advertInfo.setAdvertType("1");
        //            advertInfos.add(advertInfo);
        //        }
        bannerView.setIndicatorVisible(true);
        bannerView.setIndicatorRes(R.drawable.shape_little_green_6_6_circle, R.drawable.shape_green_20_6_3);
        bannerView.setIndicatorAlign(BannerView.IndicatorAlign.CENTER);
        bannerView.setBannerPageClickListener(new CommonBannerAdvertClickListener(getPageContext(), advertInfos));
        bannerView.setPages(advertInfos, () -> new CommonBannerAdvertViewHolder());
        if (advertInfos.size() > 1) {
            bannerView.start();
        } else {
            bannerView.pause();
        }
    }
}
