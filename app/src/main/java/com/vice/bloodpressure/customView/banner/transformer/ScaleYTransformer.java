package com.vice.bloodpressure.customView.banner.transformer;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by zhouwei on 17/5/26.
 */

public class ScaleYTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.96F;
    private static final float MIN_SCALE_Y = 0.9F;


    @Override
    public void transformPage(View page, float position) {

        if(position < -1){
            page.setScaleY(MIN_SCALE_Y);
        }else if(position<= 1){
            //
            float scaleX = Math.max(MIN_SCALE,1 - Math.abs(position));
            float scaleY = Math.max(MIN_SCALE_Y,1 - Math.abs(position));
            page.setScaleX(scaleX);
            page.setScaleY(scaleY);
            /*page.setScaleX(scale);

            if(position<0){
                page.setTranslationX(width * (1 - scale) /2);
            }else{
                page.setTranslationX(-width * (1 - scale) /2);
            }*/

        }else{
            float scaleX = Math.max(MIN_SCALE,1 - Math.abs(position));
            float scaleY = Math.max(MIN_SCALE_Y,1 - Math.abs(position));
            page.setScaleX(scaleX);
            page.setScaleY(scaleY);
        }
    }

}
