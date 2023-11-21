package com.deyi.daxie.cloud.vehicle.query.service;

import com.deyi.daxie.cloud.vehicle.query.util.Result;
import com.deyi.daxie.cloud.vehicle.query.vo.TableDataInfo;
import com.deyi.daxie.cloud.vehicle.query.vo.VehicleInfo;

import java.util.Set;

/**
 * Description: 车辆接口
 * @date 2022/9/25
 * @author Huang ShuYing
 */
public interface VehicleInfoService {
    /**
     * Description:
     * @param company 所属组织
     * @param pageNum 页码
     * @param pageSize 页长
     * @param protocol 协议
     * @param vehicleStatus 状态
     * @param vehicleType 类型
     * @param plateNumber 车牌
     * @param deviceNum 车号
     * @return TableDataInfo 分页列表
     * @date 2022/9/26
     * @author Huang ShuYing
     */
    TableDataInfo list(int pageSize, int pageNum,String vin, String plateNumber,String company, String protocol, String vehicleType, String vehicleStatus, String deviceNum);

    /**
     * Description:
     * @param vin 车vin
     * @return Result 统一返回对象
     * @date 2022/9/26
     * @author Huang ShuYing
     */
    Result info(String vin);

    /**
     * Description:
     * @param vehicleInfo 车对象
     * @return Result 统一返回对象
     * @date 2022/9/26
     * @author Huang ShuYing
     */
    Result add(VehicleInfo vehicleInfo);

    /**
     * Description:
     * @param vehicleInfo 车对象
     * @return Result 统一返回对象
     * @date 2022/9/26
     * @author Huang ShuYing
     */
    Result edit(VehicleInfo vehicleInfo);
    /**
     * Description:
     * @param vin 车vin
     * @return Result 统一返回对象
     * @date 2022/9/26
     * @author Huang ShuYing
     */
    Result remove(String vin);

    /**
     * Description: 车辆总数查询
     * @return Result 统一返回对象
     * @date 2022/9/26
     * @author Huang ShuYing
     */
    int vehicleTotal();


    /**
     * 根据车号获取车辆列表
     * @param deviceNum 车号
     * @return 车辆列表
     */
    Set<String> getDeviceNum(String deviceNum);

    /**
     * 根据车号获取车辆密码
     * @param deviceNum 车号
     * @return 车辆密码
     */
    VehicleInfo selectByDeviceNum(String deviceNum);
}
