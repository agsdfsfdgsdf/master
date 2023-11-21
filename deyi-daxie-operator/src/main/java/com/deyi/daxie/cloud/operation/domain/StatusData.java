package com.deyi.daxie.cloud.operation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.dto.StatusDataDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2023-05-30
 */
@Data
@ToString
@TableName("vel_status_data")
@ApiModel(value = "车辆状态", description = "")
public class StatusData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("车号，tos中车号")
    private String deviceNum;

    @ApiModelProperty("所属车队")
    private String fleet;

    @ApiModelProperty("能源模式，用油：O；用电：E；油电混合：M")
    private String energy;

    @ApiModelProperty("登录状态，登录：1；未登录：0；故障：9")
    private Integer loginStatus;

    @ApiModelProperty("作业模式，自动驾驶：0；人工驾驶：1；未登录：默认为-1")
    private Integer operationMode;

    @ApiModelProperty("版本号，当前发布的版本号")
    private String version;

    @ApiModelProperty("时间，yyyy-MM-dd HH:mm:ss")
    private String deviceTime;

    public static StatusDataDto toDto(StatusData entity) {
        if(entity == null){
            return null;
        }
        return StatusDataDto.builder()
                .deviceNum(entity.getDeviceNum())
                .deviceTime(entity.getDeviceTime())
                .energy(entity.getEnergy())
                .fleet(entity.getFleet())
                .id(entity.getId())
                .loginStatus(entity.getLoginStatus())
                .operationMode(entity.getOperationMode())
                .version(entity.getVersion())
                .build();
    }

    public static List<StatusDataDto> toDto(List<StatusData> entitys){
        List<StatusDataDto> data = new ArrayList<>();

        if(entitys == null){
            return data;
        }
        entitys.forEach(entity -> {
            if(!ObjectUtils.isEmpty(entity)){
                data.add(toDto(entity));
            }
        });
        return data;
    }

}
