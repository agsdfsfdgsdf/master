package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;

/**
 * Description: 车辆位置监控数据
 *
 * @author Chen Xu
 * @date 2023/6/5
 */
@Data
public class LocationDto {

    // 车号
    private String deviceNum;

    // 作业模式
    private String operationMode;

    // tos 登录，未登录
    private String tos;

    // 当前速度
    private Double speed;

    // 作业箱号
    private String containerNo;

    // 纬度
    private Double latitude;

    // 经度
    private Double longitude;

    // 定位时间
    private String timestamp;


    public void setOperationMode(Integer mode) {
        if(mode == null){
            this.operationMode = "未登录";
        }
        switch (mode){
            case 0:
                this.operationMode = "自动驾驶";
                break;
            case 1:
                this.operationMode = "人工驾驶";
                break;
            default:
                this.operationMode = "未登录";
        }
    }
}
