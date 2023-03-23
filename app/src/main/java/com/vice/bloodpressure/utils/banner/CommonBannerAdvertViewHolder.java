package com.vice.bloodpressure.utils.banner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IImageBrower;
import com.vice.bloodpressure.customView.banner.holder.BannerViewHolder;
import com.vice.bloodpressure.utils.XyImageUtils;


/**
 * 类描述：
 * 类传参：
 *
 * @author android.yys
 * @date 2020/11/30
 */
public class CommonBannerAdvertViewHolder implements BannerViewHolder<IImageBrower> {
    private ImageView mImageView;
    private int type;

    public CommonBannerAdvertViewHolder() {
    }

    public CommonBannerAdvertViewHolder(int type) {
        this.type = type;
    }

    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item_normal, null);
        mImageView = view.findViewById(R.id.banner_normal_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, IImageBrower data) {
        int defaultResID = R.drawable.text1;
        Log.i("yys", "data==" + data.sourceImage());
        XyImageUtils.loadRoundImage(context, defaultResID, data.sourceImage(), mImageView);

    }

}
