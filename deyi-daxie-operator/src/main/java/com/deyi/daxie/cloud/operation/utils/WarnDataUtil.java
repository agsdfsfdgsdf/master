package com.deyi.daxie.cloud.operation.utils;

import com.deyi.daxie.cloud.operation.domain.WarnData;
import com.deyi.daxie.cloud.operation.domain.dto.WarnDataTypeDto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: WarnDataUtil
 *
 * @author Chen Xu
 * @date 2023/5/31
 */

public class WarnDataUtil {
    private static Map<String, String> typeField = new HashMap<>();

    static {
        typeField.put("warningTurnlight", "灯光");
        typeField.put("warningSpeaker", "喇叭");
        typeField.put("warningTirepress", "胎压");
        typeField.put("warningSeatbelt", "安全带");
        typeField.put("warningBacklight", "拖挂灯控");
        typeField.put("warningPress", "拖挂气路");
        typeField.put("warningWiper", "雨刷");
        typeField.put("warningFrontlight", "大灯");
        typeField.put("warningLed", "LED屏");
        typeField.put("warningAlarm", "警示灯");
        typeField.put("warningBreak", "无底层制动反馈信息");
        typeField.put("warningAcc", "无底层油门反馈信息");
        typeField.put("warningTurn", "无底层转角反馈信息");
        typeField.put("laserLag", "激光雷达有无数据");
        typeField.put("singlelaser", "激光雷达单帧点数异常");
        typeField.put("multilaser", "激光雷达噪点异常");
        typeField.put("cameradata", "摄像头无数据");
        typeField.put("cameraLag", "摄像头数据异常-画面静止");
        typeField.put("mmradarLag", "毫米波雷达无数据");
        typeField.put("mmraderBug", "毫米波雷达数据异常-障碍物信息异常");
        typeField.put("wheelspeedLag", "轮速计无数据");
        typeField.put("wheelspeedSd", "轮速计标准差过大");
        typeField.put("anglearsensorLag", "转角传感器无数据");
        typeField.put("warningHardbrake", "急刹");
        typeField.put("warningLwt", "长时间等待");
        typeField.put("turnMainstreet", "转角阈值");
        typeField.put("turnInbay", "转角阈值");
        typeField.put("turnDock", "转角阈值");
        typeField.put("turnCharge", "转角阈值");
        typeField.put("turnCurve", "转角阈值");
        typeField.put("gnssData", "GNSS定位无数据");
        typeField.put("gnssLag", "GNSS定位信号弱");
        typeField.put("imuData", "IMU无数据");
        typeField.put("imuSd", "IMU帧间方差过大");
        typeField.put("locationSd", "融合定位误差过大");
    }

    public static Map<String, String> getTypeField() {
        return typeField;
    }

    public static void getTypeList(List<WarnDataTypeDto> ls, List<WarnData> data){
        data.stream().forEach(it ->{
            Class clazz = it.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.getType() == Boolean.class && (Boolean)field.get(it)){
                        ls.add(new WarnDataTypeDto(it.getId(), it.getDeviceNum(), it.getDeviceTime(), typeField.get(field.getName())));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
