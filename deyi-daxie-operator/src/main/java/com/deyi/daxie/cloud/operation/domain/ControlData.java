package com.deyi.daxie.cloud.operation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.dto.ControlDataDto;
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
@TableName("vel_control_data")
@ApiModel(value = "控制数据", description = "")
public class ControlData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("车号, TOS中车号")
    private String deviceNum;

    @ApiModelProperty("实际经度")
    private Double longitudeR;

    @ApiModelProperty("实际纬度")
    private Double latitudeR;

    @ApiModelProperty("规划经度")
    private Double longitudeD;

    @ApiModelProperty("规划纬度")
    private Double latitudeD;

    @ApiModelProperty("实际速度    单位km/h")
    private Double speedR;

    @ApiModelProperty("规划速度    单位km/h")
    private Integer speedD;

    @ApiModelProperty("区域限速    单位km/h")
    private Integer speedL;

    @ApiModelProperty("油门控制    取值范围0.0～1.0，代表期望油门的百分比。0.0对应0%不踩油门，1.0对应100%满油。油门期望/控制与反馈量和决策目标值差量之间统一度量衡")
    private Double throttleR;

    @ApiModelProperty("油门期望")
    private Double throttleD;

    @ApiModelProperty("底层油门反馈量与决策目标量差值")
    private Double throttleS;

    @ApiModelProperty("刹车控制    取值范围0.0～1.0，代表期望刹车的百分比。0.0对应0%不踩刹车，1.0对应100%踩死")
    private Double brakeR;

    @ApiModelProperty("刹车期望")
    private Double brakeD;

    @ApiModelProperty("底层制动反馈量与决策目标量差值")
    private Double brakeS;

    @ApiModelProperty("转向控制    取值范围-1.0~1.0，代表反馈方向盘转角的百分比。-1.0对应向左打满100%，0.0代表无转向，1.0代表向右打满100%")
    private Double wheelR;

    @ApiModelProperty("转向期望")
    private Double wheelD;

    @ApiModelProperty("底层转向反馈信息与决策目标量差值")
    private Double wheelS;

    @ApiModelProperty("挡位控制    取值范围：D、R、P、N，代表前进挡、倒车档、驻车档、空挡")
    private String gearR;

    @ApiModelProperty("挡位期望")
    private String gearD;

    @ApiModelProperty("车灯控制")
    private String lightR;

    @ApiModelProperty("车灯期望  取值范围：L、R、E、N，代表左转向、右转向、双闪、无")
    private String lightD;

    @ApiModelProperty("是否对位  对位中1，非对位0")
    private Integer isCp;

    @ApiModelProperty("是否对位开始  距目标点5米时触发；是：1，否：0")
    private Integer cpStart;

    @ApiModelProperty("是否对位结束  无人集卡确认对位完成；是：1，否：0")
    private Integer cpEnd;

    @ApiModelProperty("时间  yyyy-MM-dd HH:mm:ss")
    private String deviceTime;

    @ApiModelProperty("所在位置   1、集中装卸锁站 2、0W查验场地 3、熏蒸场地 4、CFS过磅场地 5、H986查验场地 6、调箱门场地 7、其它指定点位")
    private Integer position;

    @ApiModelProperty("车道中心线偏移")
    private Integer devDistance;

    public static ControlDataDto toDto(ControlData entity){
        if(entity == null){
            return null;
        }
        return ControlDataDto.builder()
                .id(entity.getId())
                .deviceNum(entity.getDeviceNum())
                .longitudeR(entity.getLongitudeR())
                .latitudeR(entity.getLatitudeR())
                .longitudeD(entity.getLongitudeD())
                .latitudeD(entity.getLatitudeD())
                .speedR(entity.getSpeedR())
                .speedD(entity.getSpeedD())
                .speedL(entity.getSpeedL())
                .throttleR(entity.getThrottleR())
                .throttleD(entity.getThrottleD())
                .throttleS(entity.getThrottleS())
                .brakeR(entity.getBrakeR())
                .brakeD(entity.getBrakeD())
                .brakeS(entity.getBrakeS())
                .wheelR(entity.getWheelR())
                .wheelD(entity.getWheelD())
                .wheelS(entity.getWheelS())
                .gearR(entity.getGearR())
                .gearD(entity.getGearD())
                .lightR(entity.getLightR())
                .lightD(entity.getLightD())
                .isCp(entity.getIsCp())
                .cpStart(entity.getCpStart())
                .cpEnd(entity.getCpEnd())
                .deviceTime(entity.getDeviceTime())
                .position(entity.getPosition())
                .devDistance(entity.getDevDistance())
                .build();
    }

    public static List<ControlDataDto> toDto(List<ControlData> entitys){
        List<ControlDataDto> data = new ArrayList<>();
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
