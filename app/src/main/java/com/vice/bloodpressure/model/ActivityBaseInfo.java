package com.vice.bloodpressure.model;

import com.vice.bloodpressure.baseimp.IImageBrower;

import java.util.Map;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ActivityBaseInfo implements IImageBrower {
    private String linkUrl;

    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public String thumbImage() {
        return null;
    }

    @Override
    public String bigImage() {
        return null;
    }

    @Override
    public String sourceImage() {
        return img;
    }

    @Override
    public String imageType() {
        return null;
    }

    @Override
    public String videoPath() {
        return null;
    }

    @Override
    public boolean isGif() {
        return false;
    }

    @Override
    public Map<String, Integer> widthAndHeight() {
        return null;
    }
}
