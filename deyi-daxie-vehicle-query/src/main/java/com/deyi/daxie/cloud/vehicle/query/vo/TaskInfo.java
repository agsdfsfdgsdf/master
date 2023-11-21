package com.deyi.daxie.cloud.vehicle.query.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description: 任务信息实体类
 * @author Huang ShuYing
 * @date 2022/9/6
 */
@Getter
@Setter
@ToString
public class TaskInfo implements Serializable {

    private static final long serialVersionUID = 4228112933165567883L;
    /**
     * Description: 任务id
     * @date 2022/10/9
     */
    private String taskId;

    /**
     * Description: 车辆vin
     * @date 2022/10/9
     */
    private String vin;

    /**
     * Description: 站点名称
     * @date 2022/10/13
     */
    private String stationName;
    /**
     * Description: 目标站点经度
     * @date 2022/10/9
     */
    private double stationLongitude;

    /**
     * Description: 目标站点纬度
     * @date 2022/10/9
     */
    private double stationLatitude;

    /**
     * Description: 规划路线坐标点集
     * @date 2022/10/9
     */
    private String points;

    /**
     * Description: 规划路线长度，单位km
     * @date 2022/10/9
     */
    private float length;

    /**
     * Description: 任务状态  -1-等待车辆应答 0-拒绝  1-执行中 2-提前结束  3-任务完成
     * @date 2022/10/9
     */
    private int status;

    /**
     * Description: 创建时间
     * @date 2022/10/9
     */
    private String createTime;

    /**
     * Description: 修改时间
     * @date 2022/10/9
     */
    private String updateTime;

    private String locus;
}
