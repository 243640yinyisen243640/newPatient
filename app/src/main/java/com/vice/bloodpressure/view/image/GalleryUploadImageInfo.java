package com.vice.bloodpressure.view.image;


import com.vice.bloodpressure.baseimp.IImageBrower;

import java.io.Serializable;
import java.util.Map;


public class GalleryUploadImageInfo implements Serializable, IImageBrower {

    private String galleryId = "0";
    private String thumbImage = "";
    private String bigImage = "";
    private String sourceImage = "";


    public GalleryUploadImageInfo() {
        super();
    }


    public GalleryUploadImageInfo(String add) {
        this.thumbImage = add;
    }

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }


    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public void setSourceImage(String sourceImage) {
        this.sourceImage = sourceImage;
    }

    @Override
    public String thumbImage() {
        return thumbImage;
    }

    @Override
    public String bigImage() {
        return bigImage;
    }

    @Override
    public String sourceImage() {
        return sourceImage;
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


    public String getBigImage() {
        return bigImage;
    }

    public String getThumbImage() {
        return thumbImage;
    }

}
