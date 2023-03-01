package com.vice.bloodpressure.constant;

import android.Manifest;

/**
 * 类描述：
 * 类传参：
 *
 * @date 2019/9/24
 */
public class PermissionsConstant {
    /*读写权限和定位*/
    public static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    /*相机+读写权限*/
    public static String[] PERMISSIONS_CAMERA_AND_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    /*定位权限*/
    public static String[] PERMISSIONS_LOCATION = {
            Manifest.permission.ACCESS_FINE_LOCATION};
    /*相机+读写权限*/
    public static String[] PERMISSIONS_CAMERA = {
            Manifest.permission.CAMERA};
    /*手机状态权限*/
    public static String[] PERMISSIONS_PHONE = {
            Manifest.permission.READ_PHONE_STATE};
    /**
     * 开启悬浮窗权限
     */
    public static String[] PERMISSIONS_ALERT_WINDOW = {
            Manifest.permission.SYSTEM_ALERT_WINDOW};
}
