package com.deyi.daxie.cloud.vehicle.query.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description: 摄像机信息实体类
 * @author Huang ShuYing
 * @date 2022/9/6
 */
@Getter
@Setter
@ToString
public class CameraInfo implements Serializable {

    private static final long serialVersionUID = 5591044573576641673L;
    /**
     * Description: 相机id
     * @date 2022/9/6
     */
    private String cameraId;

    /**
     * Description: 车辆id
     * @date 2022/9/6
     */
    private String vin;

    /**
     * Description: 相机位置
     * @date 2022/9/6
     */
    private String cameraPosition;

    /**
     * Description: 密码
     * @date 2022/9/6
     */
    private String password;

    /**
     * Description: 授权码
     * @date 2022/9/6
     */
    private String accessToken;

    /**
     * Description: 更新id
     * @date 2022/9/6
     */
    private int accessUpdateId;

    /**
     * Description: 创建时间
     * @date 2022/9/6
     */
    private String createTime;
    /**
     * Description: 更新时间
     * @date 2022/9/6
     */
    private String updateTime;

}
