package com.vice.bloodpressure.model;

import com.vice.bloodpressure.baseimp.IImageBrower;

import java.io.Serializable;
import java.util.Map;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class GalleryInfo implements Serializable, IImageBrower {

    private String thumbImg;
    private String bigImg;
    private String sourceImg;
    /**
     * 检验检查图片路径
     */
    private String url;
    /**
     * 检验检查图片名称
     */
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getBigImg() {
        return bigImg;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    public String getSourceImg() {
        return sourceImg;
    }

    public void setSourceImg(String sourceImg) {
        this.sourceImg = sourceImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String thumbImage() {
        return thumbImg;
    }

    @Override
    public String bigImage() {
        return bigImg;
    }

    @Override
    public String sourceImage() {
        return sourceImg;
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
