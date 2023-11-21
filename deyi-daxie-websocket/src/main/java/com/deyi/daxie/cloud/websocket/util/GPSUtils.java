package com.deyi.daxie.cloud.websocket.util;

import lombok.Data;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.text.DecimalFormat;

/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/6/6
 */
@Data
public class GPSUtils {

    /**
     * 根据经纬度，计算两点间的距离
     * @param longitudeFrom  第一个点的经度
     * @param latitudeFrom  第一个点的纬度
     * @param longitudeTo 第二个点的经度
     * @param latitudeTo  第二个点的纬度
     * @return 返回距离 单位米
     */
    public static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);
        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }
    /**
     * 单位换算
     * @param distance
     * @return
     */
    public static String format(double distance) {
        if (Math.abs(distance) < 1000) {
            DecimalFormat df = new DecimalFormat("#");
            return df.format(distance) + "m";
        } else {
            double km = distance / 1000.0;
            return String.format("%.1f", km) + "km";
        }
    }

    public static String format(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        double distance = getDistance(longitudeFrom, latitudeFrom, longitudeTo, latitudeTo);
        if (distance < 1000) {
            DecimalFormat df = new DecimalFormat("#");
            return df.format(distance) + "m";
        } else {
            double km = distance / 1000.0;
            return String.format("%.1f", km) + "km";
        }
    }

}
