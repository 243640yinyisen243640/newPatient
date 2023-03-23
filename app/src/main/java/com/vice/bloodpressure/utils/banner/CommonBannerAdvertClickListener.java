package com.vice.bloodpressure.utils.banner;

import android.content.Context;
import android.view.View;

import com.vice.bloodpressure.customView.banner.view.BannerView;
import com.vice.bloodpressure.model.AdvertInfo;

import java.util.List;


/**
 * 类描述：
 * 类传参：
 *
 * @author android.yys
 * @date 2020/11/30
 */
public class CommonBannerAdvertClickListener implements BannerView.BannerPageClickListener {

    private Context context;
    private List<AdvertInfo> list;

    public CommonBannerAdvertClickListener(Context context, List<AdvertInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onPageClick(View view, int position) {


    }

}

