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
public class AdvertInfo implements IImageBrower , Serializable {
    private String sourceImg;
    private String adverType;
    private String text;

    private int imgType;

    public AdvertInfo(int imgType, String text) {
        this.imgType = imgType;
        this.text = text;
    }

    public AdvertInfo(String sourceImg, String adverType, String text) {
        this.sourceImg = sourceImg;
        this.adverType = adverType;
        this.text = text;
    }

    public AdvertInfo(String sourceImg) {
        this.sourceImg = sourceImg;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSourceImg() {
        return sourceImg;
    }

    public void setSourceImg(String sourceImg) {
        this.sourceImg = sourceImg;
    }

    public String getAdverType() {
        return adverType;
    }

    public void setAdverType(String adverType) {
        this.adverType = adverType;
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
