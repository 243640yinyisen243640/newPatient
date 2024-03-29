package com.vice.bloodpressure.basemodel;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * @类说明 分享信息
 * @注意：微信分享，imageUrl和thumpBitmap必传其一，imageUrl非空时，thumpBitmap传logo
 * @注意：微博分享网页，imageUrl和thumpBitmap必传其一，imageUrl非空时，thumpBitmap传logo
 * @注意：微博分享图片，imageUrl不传，thumpBitmap必传
 * @注意：QQ分享到好友，imageUrl和thumpBitmap必传其一，imageUrl非空时，thumpBitmap传logo，localImagePath必传
 * @注意：QQ分享到空间，imageUrl和thumpBitmap必传其一，imageUrl非空时，thumpBitmap传logo
 * @作者
 * @创建日期 2020/7/24 16:28
 */
public class XySoftShareInfo {
    private String shareTitle;//必传
    private String shareDesc;//必传
    /**
     * 分享链接，分享网页必传，分享图片可不传
     */
    private String linkUrl;
    /**
     * 微信：分享图片必传，分享网页传logo
     * QQ：分享到QQ好友时，该字段与imageUrl必传其一；分享到QZone时，
     * 微博：分享图片必传，分享网页传logo
     */
    private Bitmap thumpBitmap;
    /**
     * qq分享必传;微博分享必传
     */
    private Activity activity;
    /**
     * 微信：分享图片的网络路径
     * qq分享图片的URL或者本地路径(分享到QQ好友)
     * 微博分享网页可传
     */
    private String imageUrl;
    /**
     * qq分享保存Bitmap的文件路径，必传
     */
    private String localImagePath;
    /**
     * qq分享空间必传
     */
    private ArrayList<String> imageUrls;

    /**
     * 0分享网页；1分享图片
     */
    private int qqShareType = 0;
    /**
     * 0网页分享；1图片分享
     */
    private int wechatShareType = 0;
    /**
     * /微信分享场景；0微信；1朋友圈
     */
    private int wechatShareScene = 0;
    /**
     * 0网页分享;1单张图片分享
     * 单张图片：thumpBitmap必传
     */
    private int weiboShareType = 0;

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getWechatShareScene() {
        return wechatShareScene;
    }

    public void setWechatShareScene(int wechatShareScene) {
        this.wechatShareScene = wechatShareScene;
    }

    public String getLocalImagePath() {
        return localImagePath;
    }

    public void setLocalImagePath(String localImagePath) {
        this.localImagePath = localImagePath;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getThumpBitmap() {
        return thumpBitmap;
    }

    public void setThumpBitmap(Bitmap thumpBitmap) {
        this.thumpBitmap = thumpBitmap;
    }

    public int getQqShareType() {
        return qqShareType;
    }

    public void setQqShareType(int qqShareType) {
        this.qqShareType = qqShareType;
    }

    public int getWechatShareType() {
        return wechatShareType;
    }

    public void setWechatShareType(int wechatShareType) {
        this.wechatShareType = wechatShareType;
    }

    public int getWeiboShareType() {
        return weiboShareType;
    }

    public void setWeiboShareType(int weiboShareType) {
        this.weiboShareType = weiboShareType;
    }
}
