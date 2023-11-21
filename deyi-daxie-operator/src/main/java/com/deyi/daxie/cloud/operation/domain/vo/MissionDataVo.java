package com.deyi.daxie.cloud.operation.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deyi.daxie.cloud.operation.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Tag(name = "作业数据")
public class MissionDataVo extends BasePage {

    private String deviceNum;

    private String containerNo;

    private Integer id;

}
