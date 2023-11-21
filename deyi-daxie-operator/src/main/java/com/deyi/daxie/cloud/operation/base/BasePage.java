package com.deyi.daxie.cloud.operation.base;

import lombok.Setter;

/**
 * Description: 分页参数
 *
 * @author Chen Xu
 * @date 2023/5/30
 */
@Setter
public class BasePage {

    private Integer current;
    private Integer pageSize;

    public Integer getCurrent() {
        if(current == null || current < 1){
            return 1;
        }
        return current;
    }

    public Integer getPageSize() {
        if(pageSize == null || pageSize < 1){
            return 10;
        }
        return pageSize;
    }
}
