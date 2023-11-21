package com.deyi.daxie.cloud.operation.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deyi.daxie.cloud.operation.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Tag(name = "车辆状态")
public class StatusDataVo extends BasePage {


    private String deviceNum;

    private String energy;

    private Integer operationMode;


}
