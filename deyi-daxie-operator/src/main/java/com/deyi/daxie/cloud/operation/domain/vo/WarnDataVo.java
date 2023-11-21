package com.deyi.daxie.cloud.operation.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deyi.daxie.cloud.operation.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Tag(name = "报警数据")
public class WarnDataVo extends BasePage {

    /**
     * 车号
     */
    private String deviceNum;
    /**
     * 报警类型
     */
    private String type;

}
