package com.deyi.daxie.cloud.vehicle.query.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description: 授权对象
 * @author Huang ShuYing
 * @date 2022/9/6
 */
@Getter
@Setter
@ToString
public class AccessInfo implements Serializable {

    private static final long serialVersionUID = -8350623661619789200L;
    /**
     * Description: 相机id
     * @date 2022/9/6
     */
    private int accessUpdateId;

    /**
     * Description: url
     * @date 2022/9/6
     */
    private String url;

    /**
     * Description: method
     * @date 2022/9/6
     */
    private String method;

    /**
     * Description: header
     * @date 2022/9/6
     */
    private String header;

    /**
     * Description: appKey
     * @date 2022/9/6
     */
    private String appKey;

    /**
     * Description: appSecret
     * @date 2022/9/6
     */
    private String appSecret;

}
