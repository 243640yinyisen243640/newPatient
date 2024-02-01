package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class VideoInfo implements IClassInfo{
    private String id;
    private String dishName;

    /**
     * 视频类型，2：教育视频，3：饮食视频
     */
    private String type;

    private String vid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }



    @Override
    public String getImplMealPic() {
        return vid;
    }

    @Override
    public String getImplDashName() {
        return dishName;
    }
}
