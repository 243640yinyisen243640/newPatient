package com.vice.bloodpressure.baseimp;

import java.io.Serializable;
import java.util.Map;

/**
 * 查看大图
 */
public interface IImageBrower extends Serializable {

    String thumbImage();

    String bigImage();

    String sourceImage();

    String imageType();

    String videoPath();

    boolean isGif();

    Map<String, Integer> widthAndHeight();
}
