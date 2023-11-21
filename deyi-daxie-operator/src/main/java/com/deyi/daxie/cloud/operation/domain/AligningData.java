package com.deyi.daxie.cloud.operation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.dto.AligningDataDto;
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
@TableName("vel_aligning_data")
@ApiModel(value = "对位数据", description = "")
public class AligningData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("对位设备类型,激光对位，视觉对位，龙门吊对位 ")
    private String containerDev;

    @ApiModelProperty("时间戳,yyyy-MM-dd HH:mm:ss ")
    private String Timestamp;

    @ApiModelProperty("设备号")
    @TableField(value = "DeviceNo")
    private String DeviceNo;

    @ApiModelProperty("集卡号, ")
    @TableField(value = "TruckNo")
    private String TruckNo;

    @ApiModelProperty("具体移动的物理值, ")
    private Integer containerNo;

    @ApiModelProperty("具体移动的百分比,匹配Toss任务ID ")
    private Double Rate;

    @ApiModelProperty("操作模式,True自动对位 False 人工对位 ")
    private Boolean ControlMode;


    public static AligningDataDto toDto(AligningData entity){
        if(entity == null){
            return null;
        }
        return AligningDataDto.builder()
                .id(entity.getId())
                .containerDev(entity.getContainerDev())
                .containerNo(entity.getContainerNo())
                .ControlMode(entity.getControlMode())
                .DeviceNo(entity.getDeviceNo())
                .Rate(entity.getRate())
                .Timestamp(entity.getTimestamp())
                .TruckNo(entity.getTruckNo())
                .build();
    }

    public static List<AligningDataDto> toDto(List<AligningData> entitys){
        List<AligningDataDto> data = new ArrayList<>();
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
