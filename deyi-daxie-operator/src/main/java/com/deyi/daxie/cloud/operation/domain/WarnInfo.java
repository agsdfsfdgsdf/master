package com.deyi.daxie.cloud.operation.domain;

import lombok.Data;

import java.io.Serializable;
/**
* 
* @TableName warn_info
*/
@Data
public class WarnInfo implements Serializable {

    /**
    * 主键，自增
    */
    private Long id;
    /**
    * 告警时间
    */
    private String deviceTime;
    /**
    * 发出请求的集卡号
    */
    private String deviceNum;
    /**
    * 类型
    */
    private String type;
    /**
    * 等级
    */
    private String grade;
    /**
    * 告警信息
    */
    private String content;
    /**
    * 创建时间
    */
    private String createTime;

    private String lat;

    private String lon;

    private Integer typeCount;
}
