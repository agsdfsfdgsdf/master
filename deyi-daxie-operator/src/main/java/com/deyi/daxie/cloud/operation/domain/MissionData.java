package com.deyi.daxie.cloud.operation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.dto.MissionDataDto;
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
@TableName("vel_mission_data")
@ApiModel(value = "作业数据", description = "")
public class MissionData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long tableId;

    @ApiModelProperty("落箱距离误差值  集卡背箱时与标准位置的误差判断")
    private Integer containerDev;

    @ApiModelProperty("接受指令时间  yyyy-MM-dd HH:mm:ss")
    private String receivungTime;

    @ApiModelProperty("指令完成时间  yyyy-MM-dd HH:mm:ss")
    private String finishTime;

    @ApiModelProperty("集卡号")
    private String deviceNum;

    @ApiModelProperty("作业箱号")
    private String containerNo;

    @ApiModelProperty("匹配Toss任务ID")
    private Integer id;

    public static MissionDataDto toDto(MissionData entity) {
        if(entity == null){
            return null;
        }
        return MissionDataDto.builder()
                .containerDev(entity.getContainerDev())
                .containerNo(entity.getContainerNo())
                .deviceNum(entity.getDeviceNum())
                .finishTime(entity.getFinishTime())
                .id(entity.getId())
                .receivungTime(entity.getReceivungTime())
                .tableId(entity.getTableId())
                .build();
    }

    public static List<MissionDataDto> toDto(List<MissionData> entitys){
        List<MissionDataDto> data = new ArrayList<>();
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
