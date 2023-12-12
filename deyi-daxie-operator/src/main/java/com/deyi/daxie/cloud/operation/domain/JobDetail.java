package com.deyi.daxie.cloud.operation.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName job_detail
 */
@Data
public class JobDetail implements Serializable {
    /**
     * 主键，自增
     */
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 生成文件路径
     */
    private String dict;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 集卡号
     */
    private String deviceNum;

    /**
     * 解析文件标识
     */
    private Integer flag;

    private static final long serialVersionUID = 1L;

}