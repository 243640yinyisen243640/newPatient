package com.vice.bloodpressure.customView.banner.holder;

/**
 * Created by zhouwei on 17/5/26.
 */

public interface BannerHolderCreator<VH extends BannerViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}
