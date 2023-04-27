package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class VideoInfo implements IClassInfo{
    private String img;
    private String title;

    public VideoInfo(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getImplClassID() {
        return null;
    }

    @Override
    public String getImplClassName() {
        return title;
    }

    @Override
    public String getImplClassImg() {
        return img;
    }
}
