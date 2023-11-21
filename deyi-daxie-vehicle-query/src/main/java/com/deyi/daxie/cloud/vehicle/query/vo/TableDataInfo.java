package com.deyi.daxie.cloud.vehicle.query.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


/**
 * Description:
 *
 * @author Huang ShuYing
 * @date 2022/9/25
 */
@Getter
@Setter
public class TableDataInfo implements Serializable {

    private static final long serialVersionUID = -7580769950999994660L;
    /**
     * Description: 总记录数
     * @date 2022/9/25
     */
    private long total;

    /**
     * Description:列表数据
     * @date 2022/9/25
     */
    private List<?> rows;

    /**
     * Description: 消息状态码
     * @date 2022/9/25
     */
    private int code;

    /**
     * Description:消息内容
     * @date 2022/9/25
     */
    private String msg;

    /**
     * Description: 表格数据对象
     * @date 2022/9/25
     */
    public TableDataInfo(int code, String msg,long total,List<?> rows) {
        this.code = code;
        this.msg = msg;
        this.rows = rows;
        this.total = total;
    }
}