package com.deyi.daxie.cloud.operation.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deyi.daxie.cloud.operation.base.BasePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Tag(name = "对位数据")
public class AligningDataVo extends BasePage {


    private String containerDev;

    /**
     * 集卡号
     */
    private String TruckNo;

    /**
     * 设备号
     */
    private String deviceNo;


}
