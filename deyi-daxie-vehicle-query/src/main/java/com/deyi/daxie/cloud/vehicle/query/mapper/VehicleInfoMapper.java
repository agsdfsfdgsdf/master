package com.deyi.daxie.cloud.vehicle.query.mapper;

import com.deyi.daxie.cloud.vehicle.query.vo.VehicleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * @date 2022/9/25
 * @author Huang ShuYing
 */
@Mapper
public interface VehicleInfoMapper {

    /**
     * Description:
     * @param start 开始位置
     * @param end 结束位置
     * @param company 所属组织
     * @param protocol 协议
     * @param vehicleType 类型
     * @param plateNumber 车牌
     * @param vehicleStatus 状态
     * @param deviceNum 车号
     * @date 2022/9/25
     * @return List<VehicleInfo>
     * @author Huang ShuYing
     */
    List<VehicleInfo> list(@Param("start") int start, @Param("end") int end,@Param("vin")String vin, @Param("plateNumber")String plateNumber, @Param("company")String company, @Param("protocol")String protocol, @Param("vehicleType")String vehicleType, @Param("vehicleStatus")String vehicleStatus,@Param("deviceNum") String deviceNum);

    /**
     * Description:
     * @date 2022/9/25
     ** @return int
     * @author Huang ShuYing
     * @param company 所属组织
     * @param protocol 协议
     * @param vehicleType 类型
     * @param plateNumber 车牌
     * @param vehicleStatus 状态
     */
    int total(@Param("vin")String vin,@Param("plateNumber")String plateNumber, @Param("company")String company, @Param("protocol")String protocol, @Param("vehicleType")String vehicleType, @Param("vehicleStatus")String vehicleStatus, @Param("deviceNum") String deviceNum);

    /**
     * Description:
     * @param vin 车vin
     * @return VehicleInfo
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    VehicleInfo info(@Param("vin")String vin);

    /**
     * Description:
     * @param vehicleInfo 车辆信息对象
     * @return int
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    int add(VehicleInfo vehicleInfo);

    /**
     * Description:
     * @param vehicleInfo 车辆信息对象
     * @return int
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    int edit(VehicleInfo vehicleInfo);

    /**
     * Description:
     * @param vin 车辆id
     * @return int
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    int remove(@Param("vin")String[] vin);

    /**
     * 根据车号模糊查询，获取车号列表
     * @param deviceNum 车号
     * @return 车号列表
     */
    Set<String> getDeviceNum(String deviceNum);

    /**
     * 根据车号获取车辆信息
     * @param deviceNum 车号
     * @return 车辆信息
     */
    VehicleInfo selectByDeviceNum(String deviceNum);
}
